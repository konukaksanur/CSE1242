import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        //put all subclasses to Arraylist with person polymorphically.
        ArrayList<Person> people = new ArrayList<Person>();
        ArrayList<Department> departments = new ArrayList<Department>();
        ArrayList<Project> projects = new ArrayList<Project>();
        ArrayList<Product> products = new ArrayList<Product>();

        // read input from a file and create new objects based on the line read.
        File file = new File("input.txt");
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String str = input.nextLine();
            String parts[] = str.split(" ");

            if (parts[0].equals("Department")) {
                int departmentId = Integer.parseInt(parts[1]);
                String departmentName = parts[2];
                departments.add(new Department(departmentId, departmentName));
            } else if (parts[0].equals("Project")) {
                String projectName = parts[1];
                Calendar startDate = parseCalendar(parts[2]);
                String state = parts[3];
                projects.add(new Project(projectName, startDate, state));
            } else if (parts[0].equals("Product")) {
                String productName = parts[1];
                Calendar saleDate = parseCalendar(parts[2]);
                double price = Double.parseDouble(parts[3]);
                products.add(new Product(productName, saleDate, price));
            }
            else if (parts[0].equals("Person")) {
                int id = Integer.parseInt(parts[3]);
                String firstName = parts[1];
                String lastName = parts[2];
                String gender = parts[4];
                Calendar birthDate = parseCalendar(parts[5]);
                String maritalStatus = parts[6];
                String hasDriverLicence = parts[7];
                Person person = new Person(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
                people.add(person);
            } else if (parts[0].equals("Employee")) {
                int id = Integer.parseInt(parts[1]);
                double salary = Double.parseDouble(parts[2]);
                Calendar hireDate = parseCalendar(parts[3]);
                String departmentName = parts[4];
                Person person = null;
                for (int i = 0; i < people.size(); i++) {
                    Person p = people.get(i);
                    if (people.get(i).getId() == id) {
                        person = people.get(i);
                    }
                }
                Department department = null;
                for (int i = 0; i < people.size(); i++) {
                    for (int j = 0; j < departments.size(); j++) {
                        if (departments.get(j).getDepartmentName().equals(departmentName)) {
                            department = departments.get(j);
                        }
                    }
                }
                Employee emp = new Employee(person, salary, hireDate, department);
                people.add(emp);
            } else if (parts[0].equals("RegularEmployee")) {
                int id = Integer.parseInt(parts[1]);
                double performanceScore = Integer.parseInt(parts[2]);
                Person person = null;
                for (int i = 0; i < people.size(); i++) {
                    if (people.get(i).getId() == id) {
                        person = people.get(i);
                    }
                }
                Employee emp = (Employee) person;
                RegularEmployee rE = new RegularEmployee(emp, performanceScore);
                people.add(rE);
            } else if (parts[0].equals("Developer")) {
                int id = Integer.parseInt(parts[1]);
                ArrayList<Project> p = new ArrayList<Project>();
                for (int i = 2; i < parts.length; i++) {
                    String projectName = parts[i];
                    for (int j = 0; j < projects.size(); j++) {
                        if (projects.get(j).getProjectName().equals(projectName)) {
                            p.add(projects.get(j));
                        }
                    }
                }
                Person person = null;
                for (int i = 0; i < people.size(); i++) {
                    if (people.get(i).getId() == id) {
                        person = people.get(i);
                    }
                }
                RegularEmployee re = (RegularEmployee) person;
                Developer d = new Developer(re, p);
                people.add(new Developer(re, p));
            } else if (parts[0].equals("Customer")) {
                int id = Integer.parseInt(parts[1]);
                ArrayList<Product> pr = new ArrayList<Product>();
                for (int i = 2; i < parts.length; i++) {
                    String productsName = parts[i];
                    for (int j = 0; j < products.size(); j++) {
                        if (products.get(j).getProductName().equals(productsName)) {
                            pr.add(products.get(j));
                        }
                    }
                }
                Person p = null;
                for (int i = 2; i < people.size(); i++) {
                    if (people.get(i).getId() == id) {
                        p = people.get(i);
                    }
                }
                Customer customer = new Customer(p, pr);
                people.add(customer);
            } else if (parts[0].equals("Manager")) {
                int id = Integer.parseInt(parts[1]);
                double bonusBudget = Double.parseDouble(parts[2]);
                Person p = null;
                for (int i = 0; i < people.size(); i++) {
                    if (people.get(i).getId() == id) {
                        p = people.get(i);
                    }
                }
                Employee re = (Employee) p;
                Manager m = new Manager(re, bonusBudget);
                people.add(m);
            } else if (parts[0].equals("SalesEmployee")) {
                int id = Integer.parseInt(parts[1]);
                ArrayList<Product> p = new ArrayList<Product>();
                for (int i = 2; i < parts.length; i++) {
                    String productName = parts[i];
                    for (int j = 0; j < products.size(); j++) {
                        if (products.get(j).getProductName().equals(productName)) {
                            p.add(products.get(j));
                        }
                    }
                }
                Person person = null;
                for (int i = 0; i < people.size(); i++) {
                    if (people.get(i).getId() == id) {
                        person = people.get(i);
                    }
                }
                RegularEmployee re = (RegularEmployee) person;
                SalesEmployee sE = new SalesEmployee(re, p);
                people.add(sE);
            }
        }

        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);

            if (p instanceof Manager) {
                ((Manager) p).distributeBonusBudget();
                ((Manager) p).raiseSalary(0.2);
            }
            if (p instanceof RegularEmployee) {
                ((RegularEmployee) p).raiseSalary(0.3);
            }
            if (p instanceof Developer) {
                ((Developer) p).raiseSalary(0.23);
            }
            if (p instanceof SalesEmployee) {
                ((SalesEmployee) p).raiseSalary(0.18);
                ((SalesEmployee) p).raiseSalary(1000);
            }
        }
        for(int j = 0; j<departments.size();j++) {
            System.out.println("************************************************");
            System.out.println(departments.get(j).toString());
            for (int i = 0; i < people.size(); i++)  {
                if (people.get(i) instanceof Employee) {
                    if (((Employee) people.get(i)).getDepartment().getDepartmentName().equals(departments.get(j).getDepartmentName())) {
                        //  System.out.println(((Manager) people.get(i)).toString());
                        if (people.get(i) instanceof Manager) {
                            System.out.println(((Manager) people.get(i)).toString());

                            ArrayList<RegularEmployee> re = new ArrayList<RegularEmployee>();
                            for (int b = 0; b < people.size(); b++) {
                                if (people.get(b) instanceof RegularEmployee && ((Employee) people.get(b)).getDepartment().getDepartmentName().equals(departments.get(j).getDepartmentName())) {
                                    re.add((RegularEmployee) people.get(b));
                                }
                            }
                            for (int a = 0; a < people.size(); a++) {
                                if ( people.get(a) instanceof Developer &&((Employee) people.get(a)).getDepartment().getDepartmentName().equals(departments.get(j).getDepartmentName())) {
                                    Developer d = (Developer) people.get(a);
                                    System.out.println("			1. Developer");
                                    System.out.println("				" + "Person Info [id=" + d.getId() + ", firstName=" + d.getFirstName() +", lastName=" + d.getLastName() +", gender=" + d.getGender() );
                                    System.out.println("				" + "Employee Info [salary=" + d.getSalary() + ", hireDate=" + d.getHireDate().get(Calendar.DATE) +"/"+ d.getHireDate().get(Calendar.MONTH) +"/"+ d.getHireDate().get(Calendar.YEAR) ); System.out.println("				" + "RegularEmployee Info [performanceScore=" + d.getPerformanceScore() + ", bonus=" + d.getBonus() );
                                    System.out.println("				" +( d.toString()));
                                    re.remove((RegularEmployee) people.get(a) );
                                    for(int c= 0; c<re.size();c++){
                                        if(re.get(c).getId()== people.get(a).getId()){
                                            re.remove(re.get(c) );
                                        }
                                    }
                                }
                            }
                            for (int a = 0; a < people.size(); a++) {
                                if (people.get(a) instanceof SalesEmployee && ((Employee) people.get(a)).getDepartment().getDepartmentName().equals(departments.get(j).getDepartmentName())) {
                                    SalesEmployee s = (SalesEmployee) people.get(a);
                                    System.out.println("			" + "2. SalesEmployee");
                                    System.out.println("				" + "Person Info [id=" + s.getId() + ", firstName=" + s.getFirstName() + ", lastName=" + s.getLastName() + ", gender=" + s.getGender());
                                    System.out.println("				" + "Employee Info [salary=" + s.getSalary() + ", hireDate=" + s.getHireDate().get(Calendar.DATE) + "/" + s.getHireDate().get(Calendar.MONTH) + "/" + s.getHireDate().get(Calendar.YEAR));
                                    System.out.println("				" + "RegularEmployee Info [performanceScore=" + s.getPerformanceScore() + ", bonus=" + s.getBonus());
                                    System.out.println("				" + (s.toString()));
                                    re.remove((RegularEmployee) people.get(a) );
                                    for(int c= 0; c<re.size();c++){
                                        if(re.get(c).getId()== people.get(a).getId()){
                                            re.remove(re.get(c) );
                                        }
                                    }
                                }
                            }
                            for (int a = 0; a < re.size(); a++) {
                                System.out.println("			" + "3. RegularEmployee");
                                System.out.println("				" + "Person Info [id=" + re.get(a).getId() + ", firstName=" + re.get(a).getFirstName() + ", lastName=" + re.get(a).getLastName() + ", gender=" + re.get(a).getGender());
                                System.out.println("				" + "Employee Info [salary=" + re.get(a).getSalary() + ", hireDate=" + re.get(a).getHireDate().get(Calendar.DATE) + "/" + re.get(a).getHireDate().get(Calendar.MONTH) + "/" + re.get(a).getHireDate().get(Calendar.YEAR));
                                System.out.println("				" + re.toString() + "\n");
                            }
                        }
                    }
                }
            }
        }
        System.out.println("\n" + "\n" + "**********************CUSTOMERS************************");
        for(int i= 0; i< people.size(); i++) {
            if (people.get(i) instanceof Customer) {
                System.out.println(people.get(i));
            }
        }

        System.out.println("\n" + "\n" +"**********************PEOPLE************************");
        ArrayList<Customer> cs = new ArrayList<>();
        ArrayList<Employee> emp = new ArrayList <>();
        ArrayList<Person> p = people;
        ArrayList<Person> pRemoved = new ArrayList<>();

        for(int i=0; i<p.size(); i++){
            if((p.get(i) instanceof Customer) ) {
                cs.add((Customer) p.get(i));
            }
            if((p.get(i) instanceof Employee)) {
                emp.add((Employee) p.get(i));
            }
        }
        for(int i=0; i<p.size(); i++) {
            for (int j = 0; j < cs.size(); j++) {
                if ((p.get(i).getId() == cs.get(j).getId())) {
                    pRemoved.add(p.get(i));
                }
            }
        }
        for(int i=0; i<p.size(); i++){
            for(int j=0; j<emp.size(); j++){
                if(p.get(i).getId() == emp.get(j).getId()) {
                    pRemoved.add(p.get(i));
                }
            }
        }
        for(int i=0; i<pRemoved.size(); i++){
            people.remove(pRemoved.get(i));
        }

        for(int i=0; i<cs.size(); i++){
            people.remove(cs.get(i));
        }
        for(int i=0; i<emp.size(); i++){
            people.remove(emp.get(i));
        }

        for(int i=0; i<p.size(); i++){
            System.out.println(p.get(i).toString());
        }
    }
    private static Calendar parseCalendar (String date){
        String[] splittedDate = date.split("/");
        int DAY = Integer.parseInt(splittedDate[0]);
        int MONTH = Integer.parseInt(splittedDate[1]) ;
        int YEAR = Integer.parseInt(splittedDate[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR,MONTH,DAY);
        return calendar;
    }
}