package mthree.com.caraccidentreports.dao.mappers;

import mthree.com.caraccidentreports.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCid(rs.getInt("cid"));
        customer.setUsername(rs.getString("username"));
        customer.setfName(rs.getString("fName"));
        customer.setlName(rs.getString("lName"));
        customer.setLid(rs.getString("lid"));
        return customer;
    }
}
