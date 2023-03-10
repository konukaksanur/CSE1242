/**RegularEmployee is the superclass of SalesEmployee and Developer classes. Each RegularEmployee has a performanceScore and an amount of bonus, which will be given by his/her manager based on the
  performance score. Each RegularEmployee can be created with one of the given two constructors. **/
import java.util.Calendar;
public class RegularEmployee extends Employee{
    private double performanceScore;
    private double bonus;

    public RegularEmployee(int id, String firstName, String lastName, String  gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department, double performanceScore){
        super(id, firstName, lastName,  gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department);
        this.performanceScore = performanceScore;
    }

    RegularEmployee(Employee employee, double perfScore){
        super(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(), employee.getSalary(), employee.getHireDate(), employee.getDepartment());
        this.performanceScore = perfScore;
    }

    public double getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(double performanceScore) {
        this.performanceScore = performanceScore;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    @Override
    public String toString() {
        return "RegularEmployee{" + "performanceScore=" + performanceScore + ", bonus=" + bonus + '}';
    }
}
