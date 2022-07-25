package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgaersMstcharwastes;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgaersMstcharwastesDao;
import id.co.ahm.jxf.dao.GenericHibernateDao;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat; 
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author teguh
 */
@Repository("vms022AhmgaersMstcharwastesDao")
public class Vms022AhmgaersMstcharwastesDaoImpl extends GenericHibernateDao<AhmgaersMstcharwastes, String> implements Vms022AhmgaersMstcharwastesDao {

    @Override
    public void delete(String wasteId) {
        SimpleDateFormat sdf = new SimpleDateFormat(Vms022Constant.ANSI_DATE);
        Criteria crit = getCurrentSession().createCriteria(AhmgaersMstcharwastes.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        crit.add(Restrictions.eq("dinactive", Vms022Constant.DEFAULT_MAX_DATE));
        
        List<AhmgaersMstcharwastes> items = crit.list();
        for (AhmgaersMstcharwastes item: items) {
            deleteById(item.getId());
        }
    }
    
    @Override
    public List<AhmgaersMstcharwastes> getCharWastes(String wasteId, String charCode){
        String hql = "select ad from AhmgaersMstcharwastes ad"
                    + " where ad.vwasteid = :wasteId"
                    + " and ad.vcharwaste = :charCode"
                + " and ad.dinactive = :dinactive";

        Query query = getCurrentSession().createQuery(hql);

        query.setString("wasteId", wasteId);
        query.setString("charCode", charCode);
        query.setDate("dinactive", Vms022Constant.DEFAULT_MAX_DATE);
        List<AhmgaersMstcharwastes> charList = query.list();
        return charList;
    }
    
    @Override
    public void deleteByCharCode(String wasteId, String charCode) {
        AhmgaersMstcharwastes item = findOne(wasteId, charCode, Vms022Constant.DEFAULT_MAX_DATE);
        deleteById(item.getId());
    }
    
    @Override
    public List<AhmgaersMstcharwastes> getCharWastesForPrint(String wasteId, Date dinactive) {
        Query query = getCurrentSession().createQuery("select from AhmgaersMstcharwastes det where "
                + "det.vwasteid = :wasteId "
                + "and det.dinactive = :dinactive");
        
        query.setString("wasteId", wasteId);
        query.setDate("dinactive", dinactive);
        
        List<AhmgaersMstcharwastes> charList = query.list();
        return charList;
    }
    
    @Override
    public AhmgaersMstcharwastes findOne(String wasteId, String charWaste, Date inactiveDate) {
        Criteria crit = getCurrentSession().createCriteria(AhmgaersMstcharwastes.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        crit.add(Restrictions.eq("vcharwaste", charWaste));
        crit.add(Restrictions.eq("dinactive", inactiveDate));
        return (AhmgaersMstcharwastes)crit.uniqueResult();
    }
    
    
    @Override
    public List<AhmgaersMstcharwastes> find(String wasteId) {
        Criteria crit = getCurrentSession().createCriteria(AhmgaersMstcharwastes.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        crit.add(Restrictions.eq("vstatus", Vms022Constant.STATUS_ACTIVE));
        return crit.list();
    }
    
}
