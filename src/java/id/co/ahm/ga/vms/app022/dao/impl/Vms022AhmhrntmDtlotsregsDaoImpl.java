/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.hr.AhmhrntmDtlotsregs;
import id.co.ahm.ga.vms.app000.model.hr.AhmhrntmDtlotsregsPk;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository("vms022ahmhrntmDtlotsregsDao")
public class Vms022AhmhrntmDtlotsregsDaoImpl extends HrHibernateDao<AhmhrntmDtlotsregs, AhmhrntmDtlotsregsPk> implements Vms022AhmhrntmDtlotsregsDao{
    
    @Override
    public List<String> getFileName(String otsId, String nik, String regId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT "
                + "    VFILE "
                + "FROM "
                + "    AHMHRNTM_DTLOTSREGS "
                + "WHERE "
                + "    VOTSID = :OTSID "
                + "    AND VPERSID = :VPERSID "
                + "    AND VREGID = :REGID "
                + " ORDER BY NSEQ ASC ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("OTSID", otsId);
        query.setParameter("VPERSID", nik);
        query.setParameter("REGID", regId);

        List<String> results = new ArrayList<>();
        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object obj = (Object) lists.get(i);
                
                results.add((String) obj);
            }
            
            return results;
        } catch (SQLGrammarException e) {
            return results;
        }
    }
}
