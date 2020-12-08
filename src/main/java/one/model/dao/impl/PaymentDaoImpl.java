package one.model.dao.impl;


import one.model.dao.PaymentDao;
import one.model.entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    private Connection connection;

    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Payment entity) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO  payment (date, amount, price, client_id, utility_id) \n" +
                            "  VALUES (?, ?, ?, ?, ?)")) {
                ps.setDate(1, Date.valueOf(entity.getDate()));
                ps.setInt(2, entity.getAmount());
                ps.setInt(3, entity.getPrice());
                ps.setInt(4, entity.getClientId());
                ps.setInt(5, entity.getUtilityId());
                ps.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    @Override
    public Optional<Payment> findById(int id) {
        Payment payment = null;
        try(PreparedStatement ps = connection.prepareStatement(
                "select * from payment  as p where p.id =?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                payment = extractFromResultSet(rs);
            }
        }catch (Exception e){

        }

        return Optional.ofNullable(payment);
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> result = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    "select * from payment as p");
            while (rs.next()) {
                Payment payment = extractFromResultSet(rs);
                result.add(payment);
            }
        }catch (Exception e){

        }
        return result;
    }

    @Override
    public void update(Payment entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE payment SET  date = ?, amount = ?, price = ?, client_id = ?, utility_id = ? \n" +
                        " WHERE (id = ?);")) {
            ps.setDate(1, Date.valueOf(entity.getDate()));
            ps.setInt(2, entity.getAmount());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getClientId());
            ps.setInt(5, entity.getUtilityId());
            ps.setInt(6, entity.getId());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }

    private Payment extractFromResultSet(ResultSet rs) throws SQLException {
        return new Payment.Builder()
                .id(rs.getInt("p.id"))
                .date(rs.getDate("p.date").toLocalDate())
                .amount(rs.getInt("p.amount"))
                .price(rs.getInt("p.price"))
                .utilityId(rs.getInt("p.utility_id"))
                .clientId(rs.getInt("p.client_id"))
                .build();


    }




}
