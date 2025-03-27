package mthree.com.caraccidentreports.model;

import java.util.Objects;

public class Customer {
    private int cid;
    private String username;
    private String lid;
    private String fName;
    private String lName;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCid() == customer.getCid()
                && Objects.equals(getUsername(), customer.getUsername())
                && Objects.equals(getfName(), customer.getfName())
                && Objects.equals(getlName(), customer.getlName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCid(), getUsername(), getfName(), getlName());
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }
}
