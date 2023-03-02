import java.util.ArrayList;
import java.util.Calendar;

public class Manager extends  Employee{
    private ArrayList<RegularEmployee>regularEmployees = new ArrayList<RegularEmployee>();
    private double bonusBudget;

    public Manager(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department,double bonusBudget){
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate,department);
        this.bonusBudget = bonusBudget;
    }

    public Manager(Employee employee, double bonusBudget){
        super(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(), employee.getSalary(), employee.getHireDate(), employee.getDepartment());
        this.bonusBudget = bonusBudget;
    }
    //In addEmployee method, you should add the given RegularEmployee e to the list of regularEmployees.
    public void addEmployee(RegularEmployee e){
        regularEmployees.add(e);
    }
    //In removeEmployee method, you should remove the given RegularEmployee e from the list of regularEmployees
    public void removeEmployee(RegularEmployee e){
        regularEmployees.remove(e);
    }

    //Each Manager has bonusBudget to distribute it to the regular employees working in his/her department. The distribution will be based on the given formula:
    public void distributeBonusBudget(){
        double totalMultiplyOfSalary_Performancescore = 0;
        for(int i= 0; i< regularEmployees.size(); i++){
            totalMultiplyOfSalary_Performancescore += (regularEmployees.get(i).getPerformanceScore() * regularEmployees.get(i).getSalary());
        }
        double unit = this.bonusBudget / totalMultiplyOfSalary_Performancescore;

        for(int i= 0; i< regularEmployees.size(); i++){
            regularEmployees.get(i).setBonus(unit * regularEmployees.get(i).getSalary() * regularEmployees.get(i).getPerformanceScore());
        }

    }

    public double getBonusBudget() {
        return bonusBudget;
    }

    public void setBonusBudget(double bonusBudget) {
        this.bonusBudget = bonusBudget;
    }

    public ArrayList<RegularEmployee> getRegularEmployees() {
        return regularEmployees;
    }

    public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) {
        this.regularEmployees = regularEmployees;
    }

    @Override
    public String toString() {
        return "	" +"Manager[" + "id= " + super.getId() +  ", " + super.getFirstName() + " " + super.getLastName() + "\n" + "		" +"# of Employees: " + regularEmployees.size() + ']';
    }
}
