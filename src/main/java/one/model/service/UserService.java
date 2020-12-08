package one.model.service;


import one.model.dao.DaoFactory;
import one.model.dao.UserDao;
import one.model.entity.User;

import javax.enterprise.inject.Default;

@Default
public class UserService {
    DaoFactory factory;
    UserDao dao;

    public UserService() {
        factory = DaoFactory.getInstance();
        dao = factory.createUserDao();
    }

    public User findByEmail(String email) throws Exception {
        return dao.findByEmail(email).orElseThrow(Exception::new);
    }


    public void save(User user){
        try {
            dao.create(user);
        }catch (Exception e){
            //TODO
        }
    }

    public void update(User user) {
        try {
            dao.update(user);
        }catch (Exception e){
            //TODO
        }
    }
}
