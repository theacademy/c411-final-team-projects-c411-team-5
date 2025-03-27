package mthree.com.caraccidentreports.dao.mappers;

import mthree.com.caraccidentreports.model.Incident;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncidentMapper implements RowMapper<Incident> {

    @Override
    public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {
        Incident incident = new Incident();
        incident.setIid(rs.getString("iid"));
        incident.setLid(rs.getString("lid"));
        incident.setIncidentType(rs.getString("incident_type"));
        incident.setFrom(rs.getString("from_street"));
        incident.setTo(rs.getString("to_street"));
        return incident;
    }
}
