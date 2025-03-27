package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.model.Incident;
import mthree.com.caraccidentreports.model.Location;

import java.util.List;

public interface LocationDao {
    List<Location> getAllLocations();
    Location getLocationByLid(String lid);
}
