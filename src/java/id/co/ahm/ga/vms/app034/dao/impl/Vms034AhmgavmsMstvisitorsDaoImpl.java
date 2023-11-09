/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsMstvisitors;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmgavmsMstvisitorsDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kahfi
 */
@Repository("vms034AhmgavmsMstvisitorsDao")
public class Vms034AhmgavmsMstvisitorsDaoImpl extends DefaultHibernateDao<AhmgavmsMstvisitors, BigDecimal> implements Vms034AhmgavmsMstvisitorsDao{

    @Override
    public Map<String, Object> checkVisitorExist(String nik) {
        Map<String, Object> data = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT '1' AS EXIST, NID "
                + "FROM AHMGAVMS_MSTVISITORS "
                + "WHERE VNIK = '").append(nik).append("' ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        try {
            List<Object[]> list = sqlQuery.list();
            if (list.size() > 0) {
                Object[] obj;
                for (Object object : list) {
                    obj = (Object[]) object;
                    data.put("exists", obj[0]);
                    data.put("nid", obj[1]);
                }
            }
            return data;
        } catch (Exception e) {
            data.put("exists", "0");
            data.put("nid", null);
            return data;
        }
    }
    
}
