package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.jxf.dao.GenericDao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bhakti
 */
public interface Vms022DefaultDao extends GenericDao<Object, Serializable>{
    
    public List<String> getPlantsByUserId(String plants);
    
    public String getUomByWasteId(String wasteId);
    
    public List<String> getPlantsById(String plants, String userId);
    
    public List<String> getTpsById(String tpsId, String plantId, String userId);
    
    public String getPlantTps(String plantId, String tpsId, String userid);
    
}
