package mthree.com.caraccidentreports.dao;

import mthree.com.caraccidentreports.dao.mappers.IncidentMapper;
import mthree.com.caraccidentreports.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncidentDaoImpl implements IncidentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IncidentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Incident> getIncidents() {
        String sql = "select * from incident";
        return jdbcTemplate.query(sql, new IncidentMapper());
    }

    @Override
    public List<Incident> getIncidentsByType(String type) {
        String sql = "select * from incident where incident_type=?";
        return jdbcTemplate.query(sql, new IncidentMapper(), type);
    }

    @Override
    public Incident getIncidentById(String id) {
        String sql = "select * from incident where iid = ?";
        return jdbcTemplate.queryForObject(sql, new IncidentMapper(), id);
    }

    @Override
    public Incident createIncident(Incident incident) {
        String sql = "insert into incident (iid, from_street, to_street, incident_type) values(?,?,?,?)";
        jdbcTemplate.update(sql, incident.getIid(), incident.getFrom(), incident.getTo(), incident.getIncidentType());
        return incident;
    }

    @Override
    public void updateIncident(Incident incident) {
        String sql = "update incident set" +
                "from_street = ?," +
                "to_street = ?," +
                "incident_type = ? " +
                "where iid = ?";
        jdbcTemplate.update(sql,
                incident.getFrom(),
                incident.getTo(),
                incident.getIncidentType(),
                incident.getIid());
    }

    @Override
    public void deleteIncident(Incident incident) {
        String sql = "delete from incident where iid = ?";
        jdbcTemplate.update(sql, incident.getIid());
    }
}
