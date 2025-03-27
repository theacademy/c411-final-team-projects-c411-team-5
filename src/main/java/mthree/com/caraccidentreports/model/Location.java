package mthree.com.caraccidentreports.model;

import java.util.Objects;

public class Location {
    private String lid;
    private String state;
    private String city;

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(getLid(), location.getLid()) && Objects.equals(getState(), location.getState()) && Objects.equals(getCity(), location.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLid(), getState(), getCity());
    }
}
