/**SmartLight is a subclass of SmartObject class and it implements LocationControl and Programmable interfaces.
 * It allows the light to be turned on or off in different conditions. */

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmartLight extends SmartObject implements LocationControl, Programmable {
    private boolean hasLightTurned;
    private Calendar programTime;
    private boolean programAction;

    public SmartLight(String alias, String macId) {
        super.setAlias(alias);
        super.setMacId(macId);
    }

    public void turnOnLight() { //turn on the light by printing suitable message.
        Calendar calendar = new GregorianCalendar();
        if (super.isConnectionStatus()) {
            if (hasLightTurned) {
                System.out.println("Smart Light - " + super.getAlias() + " has been already turned on");
            } else {
                System.out.println("Smart Light - " + super.getAlias() + " is turned on now (Current time: " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
            }
            setHasLightTurned(true);
        }
    }

    public void turnOffLight() { //turn off the light by printing suitable message.
        Calendar calendar = new GregorianCalendar();
        if (!super.isConnectionStatus()) {
            if (!hasLightTurned) {
                System.out.println("Smart Light - " + super.getAlias() + " has been already turned off");
            }
        } else {
            System.out.println("Smart Light - " + super.getAlias() + " is turned off now (" + "Current time: " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
        }
        setHasLightTurned(false);

    }

    @Override
    public boolean testObject() { // Test the functionalities of the smart light by invoking methods.
        System.out.println("Test is starting for SmartLight");
        super.SmartObjectToString();
        turnOnLight();
        turnOffLight();
        System.out.println("Test completed for SmartLight\n");
        if (!super.isConnectionStatus()) {
            return false;
        } else
            return true;
    }

    @Override
    public boolean shutDownObject() { //turn off the light (if it has been already turned on)
        if (super.isConnectionStatus()) {
            if (isHasLightTurned()) {
                super.SmartObjectToString();
                setHasLightTurned(false);
            }
            return true;
        } else
            return false;

    }

    @Override
    public void onLeave() { //onLeave should check the connection of a smart light firstly and then it should turn off the light.
        Calendar calendar = new GregorianCalendar();
        if (super.isConnectionStatus()) {
            setHasLightTurned(false);
            System.out.println("On Leave -> Smart Light - " + super.getAlias() + "\nSmart Light - " + super.getAlias() + " is turned off now (Current time: "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
        }
    }

    @Override
    public void onCome() { //The method onCome should check the connection of a smart light firstly, and then it should  turn on the light.
        Calendar calendar = new GregorianCalendar();
        if (super.isConnectionStatus()) {
            setHasLightTurned(true);
            System.out.println("On Come -> Smart Light - " + super.getAlias() + "\nSmart Light - " + super.getAlias() + " is turned on now (Current time: "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
        }
    }

    @Override
    public void setTimer(int seconds) { //setTimer should set the timer of a smart light with the given amount of seconds.
        Calendar calendar = new GregorianCalendar();
        Calendar changedCalendar = new GregorianCalendar();
        changedCalendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + seconds);
        if (super.isConnectionStatus()) {
            if (isHasLightTurned()) {
                System.out.println("Smart light - " + super.getAlias() + " will be turned off " + seconds + " seconds later!" + "(" + "Current time: " +
                        calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
            } else {
                System.out.println("Smart light - " + super.getAlias() + " will be turned on " + seconds + " seconds later!" + "(" + "Current time: " +
                        calendar.get(Calendar.HOUR_OF_DAY) + ":" + +calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ")");
            }
        }
        setProgramTime(changedCalendar);
    }

    @Override
    public void cancelTimer() { // it should cancel the timer of a smart light by assigning the value of null to the programTime property.
        if (super.isConnectionStatus()) {
            setProgramTime(null);
        } else
            setProgramTime(null);
    }

    @Override
    public void runProgram() { /**it should either turn on or turn off the light by checking the programAction property of the smart light
     if the programTime value equals to the current time. **/

        if (programTime != null) {
            Calendar calendar = new GregorianCalendar();
            if (programTime.get(Calendar.SECOND) == calendar.get(Calendar.SECOND)
                    && programTime.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)
                    && programTime.get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY)) {
                setProgramAction(true);
            } else
                setProgramAction(false);
        } else
            setProgramAction(false);

        if (super.isConnectionStatus()) {
            if (programAction) {
                if (hasLightTurned) {
                    System.out.println("RunProgram -> Smart Light - " + super.getAlias());
                    turnOffLight();
                    setProgramTime(null);
                } else {
                    System.out.println("RunProgram -> Smart Light - " + super.getAlias());
                    turnOnLight();
                    setProgramTime(null);
                }
            }
        }
    }

    public boolean isHasLightTurned() {
        return hasLightTurned;
    }

    public void setHasLightTurned(boolean hasLightTurned) {
        this.hasLightTurned = hasLightTurned;
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
}
