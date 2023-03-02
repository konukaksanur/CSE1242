/** Each Developer has a set of projects that the developer works on and a numberOfDevelopers data fields. Each Developer can be created with one of the given two constructors. **/
import java.util.ArrayList;
import java.util.Calendar;
public class Developer extends RegularEmployee{
    private  ArrayList<Project> projects = new ArrayList<Project>();;
    public static int numberOfDevelopers;

    public Developer(int id, String firstName, String lastName, String gender,Calendar birthDate, String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department, double pScore, ArrayList<Project> p){
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department, pScore);
        this.projects = p;
        numberOfDevelopers++;
    }

    public  Developer(RegularEmployee re, ArrayList<Project> p){
        super(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(), re.getMaritalStatus(), re.getHasDriverLicence(), re.getSalary(), re.getHireDate(), re.getDepartment(), re.getPerformanceScore());
        this.projects = p;
        numberOfDevelopers++;
    }
    //In addProject method, you should add the given Projects s to the list of projects
    public boolean addProject(Project s){
        return projects.add(s);
    }
    //In removeProject method, you should remove the given Product s from the list of projects.
    public boolean removeProject(Project s){
        return projects.remove(s);
    }

    public static int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    public static void setNumberOfDevelopers(int numberOfDevelopers) {
        Developer.numberOfDevelopers = numberOfDevelopers;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "" +  projects ;
    }
}
