package one.model.service;


import one.model.dao.DaoFactory;
import one.model.dao.UtilityDao;
import one.model.entity.Utility;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Default
public class UtilityService {
    DaoFactory factory;
    UtilityDao dao;

    public UtilityService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createUtilityDao();
    }
    public void addUtility(Utility utility){
        dao.create(utility);
    }

    public void deleteUtility(int id){
        dao.delete(id);
    }

    public List<Utility> getUtilities(){
        return dao.findAll();
    }

    public Utility getUtility(int utilityId) throws Exception {
        return dao.findById(utilityId).orElseThrow(Exception::new);
    }

    public void updateUtility(Utility utility){
        dao.update(utility);
    }

    public List<Utility> searchUtilitiesFromList(List<Utility> utilities, String utilityName, String price){
        List<Utility> utilityResult = utilities;
        if (utilityName != null && !utilityName.isEmpty()){
            utilityResult = utilityResult.stream().filter(a -> a.getName().equals(utilityName)).collect(Collectors.toList());
        }
        if (price != null && !price.isEmpty()){
            utilityResult = utilityResult.stream().filter(a -> a.getPrice() == Integer.parseInt(price)).collect(Collectors.toList());
        }

        return utilityResult;
    }
}
