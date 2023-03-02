/** Each SalesEmloyee has a set of sales that contains a product list that the SalesEmployee sells and a numberOfSalesEmployees data fields. Each SalesEmloyee can be created with one of the given
 two constructors. */
import java.util.ArrayList;
import java.util.Calendar;
public class SalesEmployee extends RegularEmployee{
    private ArrayList<Product> sales = new ArrayList<Product>();;
    public static  int numberOfSalesEmployees;

    public SalesEmployee(int id, String firstName, String lastName, String gender,  Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department, double pScore, ArrayList<Product> s){
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department, pScore);
        this.sales =s;
        numberOfSalesEmployees++;
    }
    public SalesEmployee(RegularEmployee re, ArrayList<Product> s){
        super(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(), re.getMaritalStatus(), re.getHasDriverLicence(), re.getSalary(), re.getHireDate(), re. getDepartment(), re.getPerformanceScore());
        this.sales = s;
        numberOfSalesEmployees++;
    }
    //In addSale method, you should add the given Product s to the list of sales.
    public boolean addSale(Product s){
        return sales.add(s);
    }
    //In removeSale method, you should remove the given Product s from the list of sales.
    public boolean removeSale(Product s){
        return sales.remove(s);
    }

    public static int getNumberOfSalesEmployees() {
        return numberOfSalesEmployees;
    }

    public static void setNumberOfSalesEmployees(int numberOfSalesEmployees) {
        SalesEmployee.numberOfSalesEmployees = numberOfSalesEmployees;
    }

    public ArrayList<Product> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Product> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return  "" + sales ;
    }
}
