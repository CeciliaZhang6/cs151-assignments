/* 
   * Desc:
   *    An object class called Stock that contains a stock's company, amount of shares, 
   *    and date of trade. Accessors and toString() methods are also included.
   *    
   * @author Cecilia Zhang
   * Modified: Mar 15, 2022
   * Modified: Mar 16, 2022
   * Modified: Mar 17, 2022
   */
public class Stock {
    /* Holds the stock's company name */
    private String company; 
    /* Holds the amount of shares traded */
    private int amount; 
    /* Holds the trading date */
    private int date; 

    /**
          * Creates a Stock with company name, amount of shares, and date of trade
          * @param company a String of company name
          * @param amount an integer of amount of shares
          * @param date an integer of date when trade takes place
    */
    public Stock(String company, int amount, int date){
        this.company = company;
        this.amount = amount;
        this.date = date;
    }

    //Accessors
    public String getCompany(){
        return company;
    }
    public int getAmount(){
        return amount;
    }
    public int getDate(){
        return date;
    }

    public String toString(){
        return company + " " + date + " SELL " + amount;
    }
}
