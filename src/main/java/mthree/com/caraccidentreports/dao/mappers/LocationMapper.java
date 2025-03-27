package mthree.com.caraccidentreports.dao.mappers;

import mthree.com.caraccidentreports.model.Incident;
import mthree.com.caraccidentreports.model.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setLid(rs.getString("lid"));
        location.setState(rs.getString("state"));
        location.setCity(rs.getString("city"));
        return location;
    }
}
