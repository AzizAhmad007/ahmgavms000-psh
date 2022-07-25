package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgawmsTxnscanouts;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgawmsTxnscanoutsDao;
import id.co.ahm.jxf.dao.GenericHibernateDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author teguh
 */
@Repository("vms022AhmgawmsTxnscanoutsDao")
public class Vms022AhmgawmsTxnscanoutsImpl extends GenericHibernateDao<AhmgawmsTxnscanouts, String> 
        implements Vms022AhmgawmsTxnscanoutsDao{
    
    @Override
    public List<AhmgawmsTxnscanouts> findByWasteId(String wasteId) {
        Criteria crit = getCurrentSession().createCriteria(AhmgawmsTxnscanouts.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        return crit.list();
    }
    
}
