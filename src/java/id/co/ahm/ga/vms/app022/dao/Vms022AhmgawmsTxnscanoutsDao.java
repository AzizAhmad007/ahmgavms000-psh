package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmgawmsTxnscanouts;
import id.co.ahm.jxf.dao.GenericDao;
import java.util.List;

/**
 *
 * @author teguh
 */
public interface Vms022AhmgawmsTxnscanoutsDao extends GenericDao<AhmgawmsTxnscanouts, String> {
    public List<AhmgawmsTxnscanouts> findByWasteId(String wasteId);
}
 
