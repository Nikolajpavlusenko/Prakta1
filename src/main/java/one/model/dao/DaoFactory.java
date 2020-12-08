package one.model.dao;


import one.model.dao.impl.JDBCDaoFactory;
import one.model.entity.Payment;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract UtilityDao createUtilityDao();
    public abstract PaymentDao createPaymentDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){

                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }


}
