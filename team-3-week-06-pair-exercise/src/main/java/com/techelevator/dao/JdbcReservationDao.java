package com.techelevator.dao;

import com.techelevator.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcReservationDao implements ReservationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createReservation(int siteId, String name, LocalDate fromDate, LocalDate toDate) {
     String sql = "INSERT INTO reservation (site_Id, name, from_date, to_date, create_date) " + "VALUES (?, ?, ?, ?, ?) RETURNING reservation_id;";
    return jdbcTemplate.queryForObject(sql, Integer.class, siteId, name, fromDate, toDate, LocalDate.now());
    }

    public List<Reservation> getReservationDate (int parkId) {
        List<Reservation> returnRevs = new ArrayList<>();

       String sql = "SELECT * FROM reservation JOIN site ON reservation.site_id = site.site_id " +
                "WHERE park_id = ? AND from_date BETWEEN create_date AND create_date + INTERVAL '30 day'";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
       while(results.next()) {
           Reservation theReservation = mapRowToReservation(results);
           returnRevs.add(theReservation);
       }
       return returnRevs;
    }



    private Reservation mapRowToReservation(SqlRowSet results) {
        Reservation r = new Reservation();
        r.setReservationId(results.getInt("reservation_id"));
        r.setSiteId(results.getInt("site_id"));
        r.setName(results.getString("name"));
        r.setFromDate(results.getDate("from_date").toLocalDate());
        r.setToDate(results.getDate("to_date").toLocalDate());
        r.setCreateDate(results.getDate("create_date").toLocalDate());
        return r;
    }


}
