//SmartHome represents a smart house containing several smart objects (smartObjects are kept in smartObjectList).

import java.util.ArrayList;
import java.util.Arrays;

public class SmartHome {
    private ArrayList<SmartObject> smartObjectList = new ArrayList<>();

    public SmartHome() {

    }

    public boolean addSmartObject(SmartObject smartObject) { //The addSmartObject method adds the given smartObject to the smartObjectList.

        smartObjectList.add(smartObject);
        int newIP = 0;
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObjectList.get(i).equals(smartObject)) {
                //if(smartObject.getIP().equals(smartObjectList.get(i).getIP())){
                newIP = (100 + i);
            }
        }
        smartObject.setIP("10.0.0." + newIP);
        System.out.println("---------------------------------------------------------------------------\n"
                + "---------------------------------------------------------------------------\n"
                + "Adding new SmartObject\n"
                + "---------------------------------------------------------------------------");
        smartObject.connect(smartObject.getIP());
        smartObject.testObject(); //nasıl oldu???
        return true;
    }

    public boolean removeSmartObject(SmartObject smartObject) { //The removeSmartObject method removes the given smartObject from the smartObjectList
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObject.getIP().equals(smartObjectList.get(i))) {
                smartObjectList.remove(smartObjectList.get(i));
            }
        }
        return true;
    }

    public void controlLocation(boolean onCome) { /**The controlLocation method should traverse the smartObjectList and if it finds an object
     implementing LocationControl interface, then, it should invoke either onCome or onLeave method of it by checking the onCome boolean parameter.
     If onCome boolean parameter is true, then it should invoke onCome method, and onLeave method otherwise. **/
        System.out.println("---------------------------------------------------------------------------\n"
                + "---------------------------------------------------------------------------\n"
                + "LocationControl : OnCome" + "\n"
                + "---------------------------------------------------------------------------");
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObjectList.get(i) instanceof SmartLight) {
                if (onCome) {
                    ((SmartLight) smartObjectList.get(i)).onCome();
                } else {
                    ((SmartLight) smartObjectList.get(i)).onLeave();
                }
            }
        }

    }

    public void controlMotion(boolean hasMotion, boolean isDay) { /**The controlMotion method should traverse the smartObjectList and if it finds an object
     implementing MotionControl interface, then, it should invoke its method controlMotion by sending hasMotion and isDay boolean parameters.**/
        System.out.println("---------------------------------------------------------------------------\n"
                + "---------------------------------------------------------------------------\n"
                + "MotionControl: HasMotion, isDay\n"
                + "---------------------------------------------------------------------------");
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObjectList.get(i) instanceof SmartCamera) {
                ((SmartCamera) smartObjectList.get(i)).controlMotion(hasMotion, isDay);
            }
        }
    }

    public void controlProgrammable() { /**SmartLight, SmartPlug //The controlProgrammable method should traverse the smartObjectList and if it finds an object
     implementing Programmable interface, then, it should invoke its method runProgram.**/

        System.out.println("---------------------------------------------------------------------------\n"
                + "---------------------------------------------------------------------------\n"
                + "Programmable: runProgram\n"
                + "---------------------------------------------------------------------------");
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObjectList.get(i) instanceof SmartLight) {
                ((SmartLight) smartObjectList.get(i)).runProgram();
            } else if (smartObjectList.get(i) instanceof SmartPlug) {
                ((SmartPlug) smartObjectList.get(i)).runProgram();
            }
        }

    }

    public void controlTimer(int seconds) { /**The controlTimer method should traverse the smartObjectList and it should search for a smart object
     implementing Programmable interface. In case of finding such an object, it should invoke setTimer method of it if the given seconds value is greater than 0,
     and it should invoke cancelTimer method if the given seconds value is equal to 0.**/

        System.out.println("---------------------------------------------------------------------------\n"
                + "---------------------------------------------------------------------------\n\n"
                + "Programmable: Timer =" + seconds + " seconds\n"
                + "---------------------------------------------------------------------------");
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObjectList.get(i) instanceof SmartLight) {
                if (seconds > 0) {
                    ((SmartLight) smartObjectList.get(i)).setTimer(seconds);
                } else if (seconds == 0) {
                    ((SmartLight) smartObjectList.get(i)).cancelTimer();
                }
            } else if (smartObjectList.get(i) instanceof SmartPlug) {
                if (seconds > 0) {
                    ((SmartPlug) smartObjectList.get(i)).setTimer(seconds);
                } else if (seconds == 0) {
                    ((SmartPlug) smartObjectList.get(i)).cancelTimer();
                }
            }
        }
    }

    public void controlTimerRandomly() {  /**The controlTimerRandomly method should traverse the smartObjectList and it should search for a smart object
     implementing Programmable interface. In case of finding such an object, it should invoke its method setTimer with the value of 5 or 10 seconds randomly.
     If the random number is 0, then it should invoke cancelTimer method. Here, the random number should be 0, 5, or 10.**/
        System.out.println("---------------------------------------------------------------------------\n"
                + "---------------------------------------------------------------------------\n"
                + "Programmable: Timer = 0, 5 or 10 seconds randomly\n"
                + "---------------------------------------------------------------------------");
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObjectList.get(i) instanceof SmartLight) {
                int randomSecond = (int) (Math.random() * 3) * 5;
                if (randomSecond == 0) {
                    ((SmartLight) smartObjectList.get(i)).cancelTimer();
                } else
                    ((SmartLight) smartObjectList.get(i)).setTimer(randomSecond);
            } else if (smartObjectList.get(i) instanceof SmartPlug) {
                int randomSecond = (int) (Math.random() * 3) * 5;
                if (randomSecond == 0) {
                    ((SmartPlug) smartObjectList.get(i)).cancelTimer();
                } else
                    ((SmartPlug) smartObjectList.get(i)).setTimer(randomSecond);
            }
        }
    }

    public void sortCameras() { /**SmartCamera //The sortCameras method should traverse the smartObjectList and it should search for smart cameras
     implementing Comparable interface. Then, it should invoke Arrays.sort method to sort smart cameras based on the battery life.**/
        System.out.println("---------------------------------------------------------------------------\n" +
                "---------------------------------------------------------------------------\n" +
                "Sort Smart Cameras\n" +
                "---------------------------------------------------------------------------");
        int indexOfArray = 0;
        for (int i = 0; i < smartObjectList.size(); i++) {
            if (smartObjectList.get(i) instanceof SmartCamera) {
                indexOfArray++;
            }
        }
        int[] listOfSmartCamera = new int[indexOfArray];
        int index = 0;
        for (int j = 0; j < smartObjectList.size(); j++) {
            if (smartObjectList.get(j) instanceof SmartCamera) {
                listOfSmartCamera[index] = ((SmartCamera) smartObjectList.get(j)).getBatteryLife();
                index++;
            }
        }
        Arrays.sort(listOfSmartCamera);
        for (int i = 0; i < listOfSmartCamera.length; i++) {
            for (int j = 0; j < smartObjectList.size(); j++) {
                if (smartObjectList.get(j) instanceof SmartCamera) {
                    if (listOfSmartCamera[i] == ((SmartCamera) (smartObjectList.get(j))).getBatteryLife()) {
                        System.out.println(((SmartCamera) smartObjectList.get(j)).toString());
                    }
                }
            }
        }
    }

    public ArrayList<SmartObject> getSmartObjectList() {
        return smartObjectList;
    }

    public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
        this.smartObjectList = smartObjectList;
    }
}
