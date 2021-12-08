package entity;

import java.rmi.server.RemoteServer;

public class Constant {
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPRIVATE_KEY() {
        return PRIVATE_KEY;
    }

    public void setPRIVATE_KEY(String PRIVATE_KEY) {
        this.PRIVATE_KEY = PRIVATE_KEY;
    }

    public String getREMOTE_PATH() {
        return REMOTE_PATH;
    }

    public void setREMOTE_PATH(String REMOTE_PATH) {
        this.REMOTE_PATH = REMOTE_PATH;
    }

    public String getLOCAL_PATH() {
        return LOCAL_PATH;
    }

    public void setLOCAL_PATH(String LOCAL_PATH) {
        this.LOCAL_PATH = LOCAL_PATH;
    }

    public Constant(String IP, int PORT, String USER, String PASSWORD, String PRIVATE_KEY, String REMOTE_PATH, String LOCAL_PATH) {
        this.IP = IP;
        this.PORT = PORT;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.PRIVATE_KEY = PRIVATE_KEY;
        this.REMOTE_PATH = REMOTE_PATH;
        this.LOCAL_PATH = LOCAL_PATH;
    }

    private String IP;
    private int PORT;
    private String USER;
    private String PASSWORD;
    private String PRIVATE_KEY;
    private String REMOTE_PATH;
    private String LOCAL_PATH;


}
