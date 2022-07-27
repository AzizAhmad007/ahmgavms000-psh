/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022ObjectDao")
public class Vms022ObjectDaoImpl extends HrHibernateDao<Object, Serializable> implements Vms022ObjectDao{


    
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
}
