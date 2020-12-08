package one.model.service;


import one.model.dao.DaoFactory;
import one.model.dao.PaymentDao;
import one.model.entity.Payment;


import javax.enterprise.inject.Default;

@Default
public class PaymentService {
    DaoFactory factory;
    PaymentDao dao;

    public PaymentService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createPaymentDao();
    }

    public void saveNewPayment(Payment payment){
        dao.create(payment);
    }

    public void update(Payment payment){
        dao.update(payment);}


    public Payment getPaymentById(int id) throws Exception {
        return dao.findById(id).orElseThrow(Exception::new);
    }


}
