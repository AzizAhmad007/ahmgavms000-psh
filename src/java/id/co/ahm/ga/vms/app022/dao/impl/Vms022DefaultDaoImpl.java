/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import com.mysql.jdbc.StringUtils;
import id.co.ahm.ga.vms.app022.dao.Vms022DefaultDao;
import id.co.ahm.jxf.dao.GenericHibernateDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bhakti
 */
@Repository("vms022DefaultDao")
public class Vms022DefaultDaoImpl extends GenericHibernateDao<Object, Serializable> implements Vms022DefaultDao {

    @Override
    public List<String> getPlantsByUserId(String plants) {
        String sql = "SELECT D.VITEMNAME plantId "
                + " FROM AHMMOERP_HDRSETTINGS H "
                + " JOIN AHMMOERP_DTLSETTINGS d ON D.RSET_VID = H.VID AND D.BVALID='T' AND H.VID = 'AHMGAWMS_ROLE_PLANT'  "
                + " WHERE "
                + "   D.VITEMCODE IN (" + plants + ") OR '00000' = '" + plants+ "' ";
        
        Query query = getCurrentSession().createSQLQuery(sql);
        List<String> l = (List<String>) query.list();
        return l;
    }

    @Override
    public String getUomByWasteId(String wasteId) {
        String sql = "  SELECT mp.MUOM_VUOMID AS UOM FROM AHMMOMSC_MSTPARTS mp\n" +
                    "   WHERE mp.VMATGROUP IN (SELECT ds.VITEMCODE FROM AHMMOERP_DTLSETTINGS ds WHERE ds.RSET_VID = 'AHMGAWMS_MATGROUP' AND ds.BVALID = 'T')\n" +
                    "       AND SYSDATE >= mp.DBEGINEFF AND SYSDATE <= mp.DENDEFF\n" +
                    "       AND (UPPER(mp.VPARTNUM) LIKE '%'||UPPER('"+wasteId+"')||'%')\n" +
                    "   ORDER BY mp.VPARTNUM ASC";      
        Query query = getCurrentSession().createSQLQuery(sql);
        String result = (String) query.uniqueResult();
        return result;
    }
    
    public List<String> getPlantsById(String plantId, String userId) {
        String sql = "SELECT VPLANTID FROM AHMMOMSC_MSTPLANTS WHERE CURRENT_DATE BETWEEN DBEGINEFF AND DENDEFF";
        if (!StringUtils.isNullOrEmpty(plantId)) sql = sql + " AND VPLANTID='" + plantId +"' ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<String> l = (List<String>) query.list(); 
        return l;
    }
    
    public List<String> getTpsById(String tpsId, String plantId, String userId) {
        String sql = "SELECT VTPSID FROM AHMGAWMS_MSTTPSDATAS WHERE DINACTIVE > CURRENT_DATE AND VSTATUS = 0"         
                + " AND VPLANTID='" + plantId +"' ";
        if (!StringUtils.isNullOrEmpty(tpsId)) sql = sql + " AND VTPSID = '" + tpsId + "' ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<String> l = (List<String>) query.list(); 
        return l;
    }
    
    public String getPlantTps(String plantId, String tpsId, String userid) {
        StringBuilder b = new StringBuilder("");
        StringBuilder sql = new StringBuilder("SELECT VPLANTID, VTPSID FROM AHMGAWMS_MSTTPSDATAS WHERE DINACTIVE > CURRENT_DATE AND VSTATUS = 0"); 
        if (!StringUtils.isNullOrEmpty(plantId)) sql.append(" AND VPLANTID = '").append(plantId).append("' ");
        if (!StringUtils.isNullOrEmpty(tpsId)) sql.append(" AND VTPSID = '").append(tpsId).append("' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        ScrollableResults r= query.scroll(ScrollMode.FORWARD_ONLY); 
        while (r.next()) {
            Object[] otps = r.get();
            if (otps == null || otps.length < 2) break;
            b.append(";").append(otps[0]).append(" ").append(otps[1]);
        }
        return b.toString();
    }   
    
}
