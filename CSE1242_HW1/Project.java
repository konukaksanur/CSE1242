/** Each Project has a name, startDate and state data fields. If the Project is open, state should be true; otherwise, false. **/
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Project {
    private String projectName;
    private Calendar startDate;
    private boolean state;

    public Project(String pName, Calendar startDate, String state){
        this.projectName = pName;
        this.startDate = startDate;
        setState(state);
    }
    //In setState method, a string value (�Open� or �Close�) is given, and the method should set the state as true or false.
    public void setState(String state){
        if(state.equals("Open")){
            this.state = true;
        }
        else if(state.equals("Close")){
            this.state = false;
        }
    }
    // In getState method, a string value (�Open� or �Close�) should be returned based on the state value.
    public String getState(){
        if (this.state){
            return "Open";
        }
        else
            return "Close";
    }
    //In close method, you should close the project if it is open.
    public void close(){
        if(this.state)
            this.state = false;
    }


    public String toString() {
        Date dt = startDate.getTime();
        dt.setMonth((dt.getMonth() -1 ));
        SimpleDateFormat date = new SimpleDateFormat("d/M/yyyy");
        String originalDate = date.format(dt);
        return "Project [" +"projectName=" + projectName +  ", startDate=" + originalDate +", state=" + state +  ']';
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}