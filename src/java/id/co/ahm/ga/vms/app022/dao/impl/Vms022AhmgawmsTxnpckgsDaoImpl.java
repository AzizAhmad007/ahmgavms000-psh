package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgawmsTxnpckgs;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgawmsTxnpckgsDao;
import id.co.ahm.jxf.dao.GenericHibernateDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author teguh
 */
@Repository("vms022AhmgawmsTxnpckgsDao")
public class Vms022AhmgawmsTxnpckgsDaoImpl extends GenericHibernateDao<AhmgawmsTxnpckgs, String> 
        implements Vms022AhmgawmsTxnpckgsDao{
    
    @Override
    public List<AhmgawmsTxnpckgs> findByWasteId(String wasteId) {
        Criteria crit = getCurrentSession().createCriteria(AhmgawmsTxnpckgs.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        return crit.list();
    }
}
