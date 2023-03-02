/** SmartPlug is a subclass of SmartObject class and it implements Programmable interface.
 * It allows the plug to be turned on or off in different conditions.*/

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmartPlug extends SmartObject implements Programmable {
    private boolean status;
    private Calendar programTime;
    private boolean programAction;

    public SmartPlug(String alias, String macId) {
        super.setAlias(alias);
        super.setMacId(macId);

    }

    public void turnOn() { //turn on the plug by printing suitable message.
        Calendar calendar = new GregorianCalendar();
        if (super.isConnectionStatus()) {
            if (status) {
                System.out.println("Smart Plug - " + super.getAlias() + " has been already turned on");
            } else {
                System.out.println("Smart Plug - " + super.getAlias() + " is turned on now (Current time: " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
            }
        }
        setStatus(true);
    }

    public void turnOff() { //turn off the plug by printing suitable message.
        Calendar calendar = new GregorianCalendar();
        if (!super.isConnectionStatus()) {
            if (!status) {
                System.out.println("Smart Plug - " + super.getAlias() + " has been already turned off");
            }
        } else {
            System.out.println("Smart Plug - " + super.getAlias() + " is turned off now (" + "Current time: " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
        }
        setStatus(false);

    }

    @Override
    public boolean testObject() { //// Test the functionalities of the smart light by invoking methods
        System.out.println("Test is starting for SmartPlug");
        super.SmartObjectToString();
        turnOn();
        turnOff();
        System.out.println("Test completed for SmartPlug\n");
        if (!super.isConnectionStatus()) {
            return false;
        } else
            return true;
    }

    @Override
    public boolean shutDownObject() { ////turn off the plug (if it has been already turned on)
        if (super.isConnectionStatus()) {
            if (status) {
                super.SmartObjectToString();
                setStatus(false);
            }
            return true;
        } else
            return false;
    }

    @Override
    public void setTimer(int seconds) { ////setTimer should set the timer of a smart plug with the given amount of seconds.
        Calendar calendar = new GregorianCalendar();
        Calendar changedCalendar = new GregorianCalendar();
        changedCalendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + seconds);
        if (super.isConnectionStatus()) {
            if (status) {
                System.out.println("Smart Plug - " + super.getAlias() + " will be turned off " + seconds + " seconds later!" + "(" + "Current time: " +
                        calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
            } else {
                System.out.println("Smart Plug - " + super.getAlias() + " will be turned on " + seconds + " seconds later!" + "(" + "Current time: " +
                        calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
            }
        }
        setProgramTime(changedCalendar);
    }

    @Override
    public void cancelTimer() { //// it should cancel the timer of a smart light by assigning the value of null to the programTime property.
        if (super.isConnectionStatus()) {
            setProgramTime(null);
        } else
            setProgramTime(null);
    }

    @Override
    public void runProgram() { /**it should either turn on or turn off the plug by checking the programAction property of the smart plug
     if the programTime value equals to the current time. **/
        if (programTime != null) {
            Calendar calendar = new GregorianCalendar();
            if (programTime.get(Calendar.SECOND) == calendar.get(Calendar.SECOND) &&
                    programTime.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE) &&
                    programTime.get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY)) {
                setProgramAction(true);
            } else
                setProgramAction(false);
        } else
            setProgramAction(false);

        if (super.isConnectionStatus()) {
            if (programAction) {
                if (status) {
                    System.out.println("RunProgram -> Smart Plug - " + super.getAlias());
                    turnOff();
                    setProgramTime(null);
                } else {
                    System.out.println("RunProgram -> Smart Plug - " + super.getAlias());
                    turnOn();
                    setProgramTime(null);
                }
            }
        }
    }

    public boolean isProgramAction() {
        return programAction;
    }

    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }

    public Calendar getProgramTime() {
        return programTime;
    }

    public void setProgramTime(Calendar programTime) {
        this.programTime = programTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
