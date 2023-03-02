// Each person should have an id, a name, a surname, a gender (1: woman, 2: man), birthDate (05/05/2000), maritalStatus (1: single, 2: married) and hasDriverLicense attributes. Person is the superclass
// of Customer and Employee classes. Person class has several data fields, getter/setter and toString methods.
import java.util.Calendar;
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private byte gender;
    private Calendar birthDate;
    private byte maritalStatus;
    private boolean hasDriverLicence;

    public Person(int id, String firstName, String lastName, String gender , Calendar birthDate, String maritalStatus, String hasDriverLicence) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        setGender(gender);
        setBirthDate(birthDate);
        setMaritalStatus(maritalStatus);
        setHasDriverLicence(hasDriverLicence);

    }
    //In setGender method, a string value (�Man� or �Woman�) is given, and the method should set the gender as 1 or 2.
    public void setGender(String gender) {
        if (gender.equals("Man")) {
            this.gender = 2;
        } else if (gender.equals("Woman"))
            this.gender = 1;
    }
    //In getGender method, a string value (�Man� or �Woman�) should be returned based on the gender value.
    public String getGender() {
        if (this.gender == 2) {
            return "Man";
        } else
            return "Woman";
    }
    //In setMaritalStatus method, a string value (�Single� or �Married�) is given, and the method should set the maritalStatus as 1 or 2
    public void setMaritalStatus(String status) {
        if (status.equals("Single")) {
            this.maritalStatus = 1;
        } else if (status.equals("Married"))
            this.maritalStatus = 2;
    }
    //In getMaritalStatus method, a string value (�Single� or �Married�) should be returned based on the maritalStatus value.
    public String getMaritalStatus() {
        if (this.maritalStatus == 1) {
            return "Single";
        } else
            return "Married";
    }
    //In setHasDriverLicence method, a string value (�Yes� or �No�) is given, and the method should set the hasDriverLicence as true or false.
    public void setHasDriverLicence(String info) {
        if (info.equals("Yes")) {
            this.hasDriverLicence = true;
        } else if (info.equals("No"))
            this.hasDriverLicence = false;
    }
     //In getHasDriverLicence method, a string value (�Yes� or �No�) should be returned based on the hasDriverLicence value
    public String getHasDriverLicence() {
        if (this.hasDriverLicence) {
            return "Yes";
        } else
            return "No";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public java.util.Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String toString() {
        return "Person [" +"id=" + id + ", firstName=" + firstName +", lastName=" + lastName +", gender=" + getGender() + ", birthDate=" + birthDate.get(Calendar.DATE)+ "/" + birthDate.get(Calendar.MONTH)+ "/"+ birthDate.get(Calendar.YEAR) +", maritalStatus=" + getMaritalStatus()+ ", hasDriverLicence=" + getHasDriverLicence() + ']';
    }
}
