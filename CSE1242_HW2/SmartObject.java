/**SmartObject is an abstract superclass of SmartLight, SmartPlug and SmartCamera classes.
 * It contains common features and methods for smart objects.*/
public abstract class SmartObject {
    private String alias;
    private String macId;
    private String IP;
    private boolean connectionStatus;

    public SmartObject() {
    }

    public boolean connect(String IP) {
        setIP(IP);
        setConnectionStatus(true);
        System.out.println(this.alias + " connection established");
        return true;
    }

    public boolean disconnect() {
        setIP(null);
        setConnectionStatus(false);
        return false;
    }

    public void SmartObjectToString() {
        System.out.println("This is " + getClass().getSimpleName() + " device " + alias
                + "\n\t" + "MacId:" + " " + macId
                + "\n\t" + "IP: " + IP);
        //thanks to getClass().getSimpleName(),we can call the name of the classes inherited from this class.
    }

    public boolean controlConnection() {
        if (!connectionStatus) {
            System.out.println("This device is not connected. " + getClass().getSimpleName() + " -> " + alias);
            return false;
        } else
            return true;
    }

    public abstract boolean testObject();

    public abstract boolean shutDownObject();

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getMacId() {
        return macId;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIP() {
        return this.IP;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

}
