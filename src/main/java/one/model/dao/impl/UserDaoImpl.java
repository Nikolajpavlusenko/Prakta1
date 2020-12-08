package one.model.dao.impl;


import one.model.dao.UserDao;
import one.model.entity.RoleType;
import one.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO  user (name, surname, email, password, role) \n" +
                        "  VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getSurname());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setString(5, String.valueOf(entity.getRole()));

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE user SET  name = ?, surname = ?, email = ?, password = ?,  role= ?\n" +
                        "WHERE (id = ?);")) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getSurname());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setString(5, String.valueOf(entity.getRole()));
            ps.setInt(6, entity.getId());
            ps.executeUpdate();

        }catch (Exception e){
            //TODO
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            //TODO
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement("SELECT*FROM user WHERE email=?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = extractFromResultSet(rs);
            }
        }catch (Exception e){
            //TODO
        }
        return Optional.ofNullable(user);
    }

    private User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(RoleType.valueOf(rs.getString("role")))
                .build();

    }


}
