import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Employee extends Person{
    private double salary;
    private Calendar hireDate;
    private Department department;
    public static int numberofEmployees;

    public Employee(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence,double salary, Calendar hireDate, Department department){
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
        numberofEmployees++;
    }

    public Employee(Person person, double salary, Calendar hireDate, Department department){
        super(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthDate(), person.getMaritalStatus(), person.getHasDriverLicence());
        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
        numberofEmployees++;
    }
    //There are two overloaded implementations of raiseSalary method.
    // In the first one, take the increment value as a double (0 >= percent >= 1) and raise the salary value based on the percentage value. For example, if the percent value is 0.5, increment the salary of the employee by 50%.
    // In the second one, raise the salary of the employee by the given fixed amount.
    public double raiseSalary(double percent){
        this.setSalary(this.getSalary()*( 1.0 + percent));
        return this.getSalary();
    }

    public double raiseSalary(int amount){
        this.salary += amount;
        return salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Calendar getHireDate() {
        return hireDate;
    }

    public void setHireDate(Calendar hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    @Override
    public String toString() {
        Date dt = hireDate.getTime();
        dt.setMonth((dt.getMonth() -1 ));
        SimpleDateFormat date = new SimpleDateFormat("d/M/yyyy");
        String originalDate = date.format(dt);
        return "Employee Info[" + "salary=" + salary + ", hireDate=" +originalDate + ']';
    }
}
