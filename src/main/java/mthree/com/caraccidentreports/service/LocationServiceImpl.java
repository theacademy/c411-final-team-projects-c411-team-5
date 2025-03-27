package mthree.com.caraccidentreports.service;

import mthree.com.caraccidentreports.dao.LocationDao;
import mthree.com.caraccidentreports.dao.UserCredentialDao;
import mthree.com.caraccidentreports.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl {
    private final LocationDao locationDao;
    @Autowired
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    public Location getLocationByLid(String lid){
        return locationDao.getLocationByLid(lid);
    }
}
