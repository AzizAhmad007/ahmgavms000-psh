package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmgawmsTxnpckgs;
import id.co.ahm.jxf.dao.GenericDao;
import java.util.List;

/**
 *
 * @author teguh
 */
public interface Vms022AhmgawmsTxnpckgsDao  extends GenericDao<AhmgawmsTxnpckgs, String> {
    public List<AhmgawmsTxnpckgs> findByWasteId(String wasteId);
}
