package one.model.dao.impl;



import one.model.dao.DaoFactory;
import one.model.dao.PaymentDao;
import one.model.dao.UserDao;
import one.model.dao.UtilityDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UtilityDao createUtilityDao() {
        return new UtilityDaoImpl(getConnection());
    }

    @Override
    public PaymentDao createPaymentDao() {
        return new PaymentDaoImpl(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }



//TODO Connection to db
    private Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/utilities?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root" ,
                    "root" );
        } catch (SQLException | ClassNotFoundException  e ) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
