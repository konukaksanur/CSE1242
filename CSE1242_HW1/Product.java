/**Each Product has a name, saleDate and price data fields. There are setter/getter and toString() methods **/
import java.util.Calendar;

public class Product {
    private String productName;
    private Calendar saleDate;
    private double price;

    public Product (String sName, Calendar sDate, double price){
        this.productName = sName;
        this.saleDate = sDate;
        this.price = price;
    }

    public String toString() {
        return "Product [" + "productName=" + productName + ", transactionDate=" + saleDate.get(Calendar.DATE)+"/" +  saleDate.get(Calendar.MONTH)+"/" + saleDate.get(Calendar.YEAR)   + ", price=" + price + "]";
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Calendar getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Calendar saleDate) {
        this.saleDate = saleDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
