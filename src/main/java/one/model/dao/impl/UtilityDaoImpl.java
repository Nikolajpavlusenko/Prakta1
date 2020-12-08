package one.model.dao.impl;


import one.model.dao.UtilityDao;
import one.model.entity.Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilityDaoImpl implements UtilityDao {
    private Connection connection;

    public UtilityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Utility entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO  utility (name, price) \n" +
                        "  VALUES (?, ?)")) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public Optional<Utility> findById(int id) {
        Utility utility = null;
        try(PreparedStatement ps = connection.prepareStatement(
                "select * from utility  where utility.id =?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utility = extractFromResultSet(rs);
            }
        }catch (Exception e){

        }

        return Optional.ofNullable(utility);
    }

    @Override
    public List<Utility> findAll() {
        List<Utility> result = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    "select * from utility");
            while (rs.next()) {
                Utility utility = extractFromResultSet(rs);
                result.add(utility);
            }
        }catch (Exception e){

        }
        return result;
    }

    @Override
    public void update(Utility entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE utility SET  name = ?, price = ?\n" +
                        " WHERE (id = ?);")) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();

        }catch (Exception e){

        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM utility WHERE (utility.id=?);")) {
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

        }
    }


    static Utility extractFromResultSet(ResultSet rs) throws SQLException {
        return new Utility(rs.getInt("utility.id"), rs.getString("utility.name"),
                rs.getInt("utility.price"));

    }
        @Override
        public void close () {
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }

}
