package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmgaersMstcharwastes;
import id.co.ahm.jxf.dao.GenericDao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author teguh
 */
public interface Vms022AhmgaersMstcharwastesDao extends GenericDao<AhmgaersMstcharwastes, String> {
    
    public void delete(String wasteId);
    
    public List<AhmgaersMstcharwastes> getCharWastes(String wasteId, String charCode);
    
    public void deleteByCharCode(String wasteId, String charCode);
    
    public List<AhmgaersMstcharwastes> getCharWastesForPrint(String wasteId, Date dinactive);
    
    public AhmgaersMstcharwastes findOne(String wasteId, String charWaste, Date inactiveDate);
     
    public List<AhmgaersMstcharwastes> find(String wasteId);
    
}
