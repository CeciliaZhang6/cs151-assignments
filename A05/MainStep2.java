import java.io.IOException;
import java.util.ArrayList;

/* 
   * Desc:
   *    The main driver program for Assignment 5 Step 2.
   *    This program reads the price files and trades file based on the given String array of 
   *    company names. It stores each stock company's prices into an array list and save it into 
   *    a map. It also stores each stock company's trades info (company name, amount of shares, 
   *    date) into a stack and save each stack into another map. When trading and the amount is 
   *    negative, this program calculates the capital gain of this single trade. Then, it will 
   *    print out trade info and net gain. After reading all trades, it will print out total 
   *    capital gain and remaining shares. 
   * 
   * @author Cecilia Zhang
   * Modified: Mar 15, 2022
   * Modified: Mar 16, 2022
   * Modified: Mar 17, 2022
   */

public class MainStep2 {
    /* 
    The Controller method of this assignment. See Description above for details. 
    No param or return. 
    */
    public static void main(String[] args){
        /* Holds the company name, amount of shares, and date of current stock's trade */ 
        Stock stock;
        /* Holds the stocks we are buying in */ 
        ArrayStack<Stock> tradeStack = new ArrayStack<Stock>();
        /* Holds the prices of a company's stocks */ 
        ArrayList<Double> priceList = new ArrayList<Double>();
        /* Holds all companies' prices */ 
        Map151<String, ArrayList<Double>> priceMap = new Map151<String, ArrayList<Double>>();
        /* Holds all companies' trades */ 
        Map151<String, ArrayStack<Stock>> tradeMap = new Map151<String, ArrayStack<Stock>>();
        /* Holds net gain of current trade */ 
        double capitalGain = 0;
        /* Holds total gain of a company's stock trades */ 
        double totalCapitalGain = 0;
        /* Holds the total amount of shares remaining after all trades */
        int totalAmount = 0;
        /* Holds the names of all companies */
        String[] companies = {"A", "B", "C"};
        /* Holds price file */
        ReadCSV priceFile;
        /* Holds trade file */
        ReadCSV tradeFile;

        try {
            //read each price files by looping through each company
            for (int i = 0; i < companies.length; i++){
                priceFile = new ReadCSV("https://cs.brynmawr.edu/cs151/Data/Hw5/" + companies[i] + ".csv");
                //skip title line
                priceFile.getLine();
                //make an array list for each company's prices
                while (priceFile.hasNext()){
                    String price = priceFile.getLine()[4];
                    priceList.add(Double.parseDouble(price));
                }
                //put current company's price list into a map
                priceMap.put(companies[i], priceList);
                //create a new, empty stack for trade records for current company
                tradeStack = new ArrayStack<Stock>();
                //put current company's trade stack into a map
                tradeMap.put(companies[i], tradeStack);
            }
            //read trade file
            tradeFile = new ReadCSV("https://cs.brynmawr.edu/cs151/Data/Hw5/tradesABC.csv");
        }
        catch (IOException ioe) { 
            //when the given file is not readable, print error message & end program
            System.err.println("Cannot read. " + ioe.toString());
            return;
        }
        catch (NumberFormatException nfe){
            //when given price file cannot be converted to double, print error message & end program
            System.err.println("Cannot read price file. " + nfe.toString());
            return;
        }
        
        try{
            //try to read trade file and store each trade's information
            while (tradeFile.hasNext()){
                String[] lineInfo = tradeFile.getLine();
                String company = lineInfo[0];
                int amount = Integer.parseInt(lineInfo[1]);
                int date = Integer.parseInt(lineInfo[2]);
                //add current amount of trade for total count
                totalAmount += amount;
                //put all information into a Stock Object
                stock = new Stock(company, amount, date);
                //find the trade stack for the current company we are trading
                tradeStack = tradeMap.get(company);
                if (amount > 0){ //when buying in, push the stock to our stack
                    tradeStack.push(stock);
                }
                if (amount < 0){ //when selling
                    /* Holds the reamining amount we still need to sell */
                    int sales = Math.abs(amount);
                    //check which company we are trading and use this company's price list & trade stack
                    priceList = priceMap.get(company);
                    while (sales > 0){ //keep selling until reach sales goal
                        //reset net gain for the current trade
                        capitalGain = 0;
                        /* Holds the latest-purchased stock we are going to sell */
                        Stock targetStock = tradeStack.peek();

                        if (targetStock.getAmount() > sales){ //When current stock has more shares than sales goal
                            capitalGain = (priceList.get(date - 1) - priceList.get(targetStock.getDate() - 1)) * sales;
                            int newAmount = Math.abs(targetStock.getAmount()) - sales;
                            /* Holds the updated infomration of current stock after selling */
                            Stock newStock = new Stock(targetStock.getCompany(), newAmount, targetStock.getDate());
                            //remove current stock (outdated)
                            tradeStack.pop();
                            //update current stock's information
                            tradeStack.push(newStock);
                            System.out.println(date + " SELL " + Math.abs(sales) + " net " + capitalGain);
                            sales = 0; //break loop
                        }else if (targetStock.getAmount() == sales){ //When current stock has the same amount as sales goal
                            capitalGain = (priceList.get(date - 1) - priceList.get(targetStock.getDate() - 1)) * amount;
                            //remove current stock
                            tradeStack.pop();
                            System.out.println(date + " Sell " + Math.abs(sales) + " net " + capitalGain);
                            sales = 0; //break loop
                        }else if (targetStock.getAmount() < sales){ //When current stock has fewer shares than sales goal
                            //update the sales goal
                            sales -= targetStock.getAmount();
                            capitalGain = (priceList.get(date - 1) - priceList.get(targetStock.getDate() - 1)) * Math.abs(targetStock.getAmount());
                            //remove current stock
                            tradeStack.pop();
                            System.out.println(date + " Sell " + Math.abs(targetStock.getAmount()) + " net " + capitalGain);
                        }
                        //add current net gain for total capital gain
                        totalCapitalGain += capitalGain;
                    }
                }
            }
            int totalPurchase = 0;
            for (int i = 0; i < companies.length; i++){
                totalPurchase += tradeMap.get(companies[i]).size();
            }
            System.out.println("Total net Capital Gains = " + totalCapitalGain);
            System.out.println("Shares remaining: " + totalAmount + " from " + totalPurchase + " separate purchases");
        }
        catch (IOException ioe2) {
            //when the file's lines cannot be read, print error message & end program
            System.err.println("Problem while reading file. " + ioe2);
            return;
        }

    }
}
