package com.company.controller;

import com.company.dao.CoinDao;
import com.company.dao.ProductDao;
import com.company.model.Coin;
import com.company.model.Product;
import com.company.view.TerminalManager;

import java.text.DecimalFormat;

public class Controller {

    private final TerminalManager console = new TerminalManager();
    private final DecimalFormat decimalFormat = new DecimalFormat();

    private final CoinDao coinDAO;
    private final ProductDao productDAO;

    double cost;
    double currentTotalCost;
    double coinValue;
    double totalInput;
    Product productType;
    int numProdPurchased;
    boolean needMoreChange = false;
    boolean validCoin;
    Coin coinRealName = null;
    int numOfCoinType = 0;

    public Controller(CoinDao coinDAO, ProductDao productDAO) {
        this.coinDAO = coinDAO;
        this.productDAO = productDAO;
    }

    public void run() {
        console.displayMenu();

        boolean keepRunning = true;
        do {
            int userChoice;
            try {
                userChoice = console.queryUserIntRange("Please enter a number: ", 1, 3);

                switch (userChoice) {
                    case 1:
                        console.displayUserString("\n ___ Available products: _____\n");
                        displayAllProducts();
                        console.displayUserString("\n _________________________________ \n ");
                        break;
                    case 2:
                        displayProductAvailability();
                        purchaseAProduct();
                        console.displayUserString("\n ------ Payment methods ------ \n");
                        methodsOfPayment();
                        selectPaymentMethod();
                        console.displayUserString("\nTransaction approved!\n");
                        totalInput = 0;
                        numProdPurchased = 0;
                        break;
                    case 3:
                        console.displayUserString("Goodbye! ");
                        keepRunning = false;
                        break;
                    default:
                        console.displayUserString("Invalid option. \n Please try again. ");
                }
            } catch (Exception e) {
                console.displayUserString("Exception caught, please try again.");
                keepRunning = false;
            }
        } while (keepRunning);
    }

    public void purchaseAProduct() {
        boolean sufficientProduct;
        boolean onceAgain;
        do {
            String resp = console.queryUserString("\nPlease enter name of Your selected product!  ");
            switch (resp.toUpperCase()) {
                case "CHIPS":
                    productType = Product.CHIPS;
                    cost = productType.getProductCost();
                    currentTotalCost = cost + currentTotalCost;
                    numProdPurchased++;
                    break;
                case "COLA":
                    productType = Product.COLA;
                    cost = productType.getProductCost();
                    currentTotalCost = cost + currentTotalCost;
                    numProdPurchased++;
                    break;
                case "CANDY":
                    productType = Product.CANDY;
                    cost = productType.getProductCost();
                    currentTotalCost = cost + currentTotalCost;
                    numProdPurchased++;
                    break;
                default:
                    console.displayUserString("Invalid option. Please try again.");
            }
            sufficientProduct = checkProdInventory(productType);
            onceAgain = respondWithProdInventoryAndIterate(sufficientProduct, currentTotalCost, numProdPurchased);
        } while (onceAgain);
    }

    public boolean respondWithProdInventoryAndIterate(boolean enoughProduct, double totalCost, int numProdPurchased) {
        boolean continueOn = true;
        if (enoughProduct) {
            decimalFormat.applyPattern("#0.00");
            console.displayUserString("Current total is: " + "$ " + decimalFormat.format(totalCost) + "\nTotal Items To Purchase " + numProdPurchased);
            String keepGoing = console.queryUserString("Do You want to buy something else? (y/n)");
            if (keepGoing.equalsIgnoreCase("Y")) {
                continueOn = true;
            } else if (keepGoing.equalsIgnoreCase("N")) {
                continueOn = false;
            }
        } else {
            console.displayUserString("Product not available, probably SOLD OUT. \nSelect something else today.");
            continueOn = true;
        }
        return continueOn;
    }

    public void displayAllProducts() {
        Product[] prodsAvail = productDAO.getProductTypes();
        for (Product currentProd : prodsAvail) {
            decimalFormat.applyPattern("#0.00");
            console.displayUserString("\t" + currentProd.toString() + " ===>  COST : $ " + decimalFormat.format(currentProd.getProductCost()));
        }
    }

    public void displayProductAvailability() {
        Product[] prods = productDAO.getProductTypes();
        for (Product currentProd : prods) {
            if (currentProd.getProductInventory() > 0) {
                console.displayUserString("\t" + currentProd.toString() + "   is available");
            } else {
                console.displayUserString("\t" + currentProd.toString() + " is currently unavailable. Sorry!");
            }
        }
    }

    public void methodsOfPayment() {
        Coin[] coins = coinDAO.getCoinTypes();
        for (Coin currentCoin : coins) {
            console.displayUserString("\t" + currentCoin.toString());
        }
        console.displayUserString("\n  ");
    }

    public void selectPaymentMethod() {
        console.displayUserString("\nC - to cancel order.");
        do {
            setCustomerCoinSelection();
            coinValue = coinRealName.getCoinValue();
            console.displayUserString("Coin value ===> $ " + coinValue);
            boolean sufficientCoins = checkCoinInventory(coinRealName);
            if (sufficientCoins) {
                numOfCoinType = console.queryUserInt("How many coins will You use?");
                if (acceptableRangeForCoins(numOfCoinType)) {
                    console.displayUserString("Coins returned.");
                    numOfCoinType = 0;
                }
                calculatePaymentTotal(coinValue, coinRealName, numOfCoinType);
            } else {
                console.displayUserString("Not enough coins in machine");
                needMoreChange = true;
            }
        } while (needMoreChange);
    }

    public void makeChange(int qCount, int dCount, int nCount) {
        double remainder = totalInput - currentTotalCost;
        console.displayUserString("You get ===> $" + decimalFormat.format(remainder));
        double numDimesReturned;
        double numNickelsReturned;
        double numQrtrsReturned;

        remainder = remainder * 100;
        remainder = Math.round(remainder);

        numQrtrsReturned = remainder / 25;
        remainder = remainder % 25;
        numDimesReturned = remainder / 10;
        remainder = remainder % 10;
        numNickelsReturned = remainder / 5;
        decimalFormat.applyPattern("#0");
        console.displayUserString(decimalFormat.format(numQrtrsReturned) + " Quarters back\n" + decimalFormat.format(numDimesReturned) + " Dimes back \n" + decimalFormat.format(numNickelsReturned) + " Nickels back");
        resetCoinInv(qCount, dCount, nCount, ((int) numQrtrsReturned), ((int) numDimesReturned), ((int) numNickelsReturned));
    }

    public boolean acceptableRangeForCoins(int coinsIn) {
        boolean tooMany = false;
        if (coinsIn < 1 || coinsIn > 10) {
            console.displayUserString("Please lower your input to less than 10.");
            tooMany = true;
        }
        return tooMany;
    }

    public boolean checkProdInventory(Product prodT) {
        boolean sufficientProd = true;
        int initialInv = prodT.getProductInventory();
        if (initialInv <= 0) {
            sufficientProd = false;
        }
        int resetInventory = initialInv - 1;
        prodT.setProductInventory(resetInventory);
        return sufficientProd;
    }

    public boolean checkCoinInventory(Coin coinT) {
        boolean sufficientCoin = true;
        int initialCoins = coinT.getCoinInventory();
        if (initialCoins <= 4) {
            sufficientCoin = false;
        }
        return sufficientCoin;
    }

    public void resetCoinInv(int numQGiven, int numDGiven, int numNGiven, int numQRtrn, int numDRtrn, int numNRtrn) {
        int quarterReset;
        int dimeReset;
        int nickelReset;
        int qInitialInv = Coin.QUARTER.getCoinInventory();
        int nInitialInv = Coin.NICKEL.getCoinInventory();
        int dInitialInv = Coin.DIME.getCoinInventory();
        quarterReset = qInitialInv + numQGiven - numQRtrn;
        Coin.QUARTER.setCoinInventory(quarterReset);

        dimeReset = dInitialInv + numDGiven - numDRtrn;
        Coin.DIME.setCoinInventory(dimeReset);

        nickelReset = nInitialInv + numNGiven - numNRtrn;
        Coin.NICKEL.setCoinInventory(nickelReset);
    }


    public void setCustomerCoinSelection() {
        do {
            String coinType = console.queryUserString("\nWhich coin would You like to use?");
            switch (coinType.toUpperCase()) {
                case "Q":
                    coinRealName = Coin.QUARTER;
                    validCoin = true;
                    break;
                case "D":
                    coinRealName = Coin.DIME;
                    validCoin = true;
                    break;
                case "N":
                    coinRealName = Coin.NICKEL;
                    validCoin = true;
                    break;
                case "C":
                    console.displayUserString("Order canceled. All coins are being returned. ");
                    coinValue = 0;
                    validCoin = false;
                    break;
                default:
                    validCoin = false;
            }

            if (!validCoin) {
                console.displayUserString("Sorry, we can't accept that. Please try again!");
                validCoin = false;
            }
        } while (!validCoin);
    }

    public void calculatePaymentTotal(double customerInput, Coin coinToUse, int numOfCoinsSelected) {
        int quarterCount = 0;
        int nickelCount = 0;
        int dimeCount = 0;
        switch (coinToUse) {
            case QUARTER:
                quarterCount = numOfCoinsSelected;
                break;
            case NICKEL:
                nickelCount = numOfCoinsSelected;
                break;
            case DIME:
                dimeCount = numOfCoinsSelected;
                break;
        }
        double thisInput = customerInput * numOfCoinsSelected;
        totalInput = thisInput + totalInput;
        decimalFormat.applyPattern("#0.00");
        console.displayUserString("You provided ===> " + numOfCoinsSelected + " " + coinToUse + "S\n Value of inserted coins ===> $" + decimalFormat.format(thisInput));
        console.displayUserString("Total customer coins value is ===> $" + decimalFormat.format(totalInput) + "\nYour total cost is ===> $" + decimalFormat.format(currentTotalCost));


        if (totalInput < currentTotalCost) {
            console.displayUserString("More coins needed!");
            needMoreChange = true;
        } else if (totalInput > currentTotalCost) {
            console.displayUserString("Thanks for shopping! Here is Your change. ");
            needMoreChange = false;
            makeChange(quarterCount, dimeCount, nickelCount);
            currentTotalCost = 0;
        } else if (currentTotalCost == totalInput) {
            needMoreChange = false;
            currentTotalCost = 0;
        }
    }
}
