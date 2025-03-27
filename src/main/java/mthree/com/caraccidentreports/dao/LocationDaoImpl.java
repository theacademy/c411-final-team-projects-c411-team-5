package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.dao.mappers.LocationMapper;
import mthree.com.caraccidentreports.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDaoImpl implements LocationDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> getAllLocations() {
        final String sql = "SELECT * FROM location;";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    public Location getLocationByLid(String lid) {
        final String sql = "SELECT * FROM location WHERE lid = ?;";
        return jdbcTemplate.queryForObject(sql, new LocationMapper(), lid);    }
}
