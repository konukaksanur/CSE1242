/**SmartCamera is a subclass of SmartObject class and it implements MotionControl and Comparable interfaces.
 * It allows the camera to be recorded on or not in different conditions.
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera> {
    private boolean status;
    private int batteryLife;
    private boolean nightVision;


    public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
        super.setAlias(alias);
        super.setMacId(macId);
        this.batteryLife = batteryLife;
        this.nightVision = nightVision;
    }

    public void recordOn(boolean isDay) { //The recordOn method should check the dome conditions. Based on these controls it should start recording.
        if (super.isConnectionStatus()) {
            if (!isDay) {
                if (!nightVision) {
                    System.out.println("Sorry! Smart Camera - " + super.getAlias() + " does not have night vision feature.");
                    status = false;
                } else {
                    if (status) {
                        System.out.println("Smart Camera - " + super.getAlias() + " has been already turned on");
                    } else {
                        System.out.println("Smart Camera - " + super.getAlias() + " is turned on now");
                    }
                    status = true;
                }
            } else {
                if (status) {
                    System.out.println("Smart Camera - " + super.getAlias() + " has been already turned on");
                } else {
                    System.out.println("Smart Camera - " + super.getAlias() + " is turned on now");
                }
                status = true;
            }
        }
    }

    public void recordOff() { //The recordOff method should check the connection of a smart camera firstly and it should stoprecording.
        if (super.isConnectionStatus()) {
            if (status) {
                System.out.println("Smart Camera - " + super.getAlias() + " is turned off now");
            } else {
                System.out.println("Smart Camera - " + super.getAlias() + " has been already turned off");
            }
        }
        status = false;
    }

    @Override
    public boolean testObject() { // test the functionalities of the smart camera by invoking methods
        System.out.println("Test is starting for SmartCamera");
        if (super.isConnectionStatus()) {
            super.SmartObjectToString();
            System.out.println("Test is starting for SmartCamera day time");
            recordOn(true);
            recordOff();
            System.out.println("Test is starting for SmartCamera night time");
            recordOn(false);
            recordOff();
            System.out.println("Test completed for SmartCamera\n");
            return true;
        } else
            return false;

    }

    @Override
    public boolean shutDownObject() {
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
    public boolean controlMotion(boolean hasMotion, boolean isDay) {
        if (hasMotion) {
            System.out.println("Motion detected!");
            if (isDay) {
                recordOn(true);
            } else {
                if (nightVision) {
                    recordOn(false);
                }
            }
            return true;
        } else {
            System.out.println("Motion not detected!");
            return false;
        }

    }

    @Override
    public int compareTo(SmartCamera smartCamera) {
        if (smartCamera.batteryLife > this.batteryLife) {
            return 1;
        } else if (smartCamera.batteryLife == this.batteryLife) {
            return 0;
        } else
            return -1;
    }

    @Override
    public String toString() {
        return "SmartCamera -> " + super.getAlias() + "'s battery life is " + batteryLife + " status is recording"; //recording ??????????
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public boolean isNightVision() {
        return nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
