package mthree.com.caraccidentreports.dao.mappers;

import mthree.com.caraccidentreports.model.Incident;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncidentMapper implements RowMapper<Incident> {

    @Override
    public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {
        Incident incident = new Incident();
        incident.setAid(rs.getInt("aid"));
        incident.setIncidentType(rs.getString("incidentType"));
        incident.setFrom(rs.getString("from"));
        incident.setTo(rs.getString("to"));
        return incident;
    }
}
