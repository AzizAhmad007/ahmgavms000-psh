/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregs;
import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregsPk;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmDtlotsregsDao;
import id.co.ahm.jxf.dao.HrHibernateDao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayik.op
 */
@Repository("ahmhrntmDtlotsregsDao")
public class AhmhrntmDtlotsregsDaoImpl extends HrHibernateDao<AhmhrntmDtlotsregs, AhmhrntmDtlotsregsPk> implements AhmhrntmDtlotsregsDao{
    
    @Override
    public int getLastSeq(String otsId, String nik, String regId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT "
                + "    NVL(MAX(NSEQ), 0) "
                + "FROM "
                + "    AHMHRNTM_DTLOTSREGS "
                + "WHERE "
                + "    VOTSID = :OTSID "
                + "    AND VPERSID = :VPERSID "
                + "    AND VREGID = :REGID ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("OTSID", otsId);
        query.setParameter("VPERSID", nik);
        query.setParameter("REGID", regId);

        Number results = 0;
        try {
            Object obj = (Object) query.uniqueResult();

            if (obj != null) {
                results = (Number) query.uniqueResult();
            }

            return results.intValue();
        } catch (SQLGrammarException e) {
            return results.intValue();
        }
    }
    
    @Override
    public int getCount(String otsId, String nik, String regId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT "
                + "    COUNT(*) "
                + "FROM "
                + "    AHMHRNTM_DTLOTSREGS "
                + "WHERE "
                + "    VOTSID = :OTSID "
                + "    AND VPERSID = :VPERSID "
                + "    AND VREGID = :REGID ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("OTSID", otsId);
        query.setParameter("VPERSID", nik);
        query.setParameter("REGID", regId);

        Number results = 0;
        try {
            Object obj = (Object) query.uniqueResult();

            if (obj != null) {
                results = (Number) query.uniqueResult();
            }

            return results.intValue();
        } catch (SQLGrammarException e) {
            return results.intValue();
        }
    }
    
    @Override
    public List<String> getSeq(String otsId, String nik, String regId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT "
                + "    NSEQ "
                + "FROM "
                + "    AHMHRNTM_DTLOTSREGS "
                + "WHERE "
                + "    VOTSID = :OTSID "
                + "    AND VPERSID = :VPERSID "
                + "    AND VREGID = :REGID ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("OTSID", otsId);
        query.setParameter("VPERSID", nik);
        query.setParameter("REGID", regId);

        List<String> results = new ArrayList<>();
        try {
            List lists = query.list();
            for (int i = 0; i < lists.size(); i++) {
                Object obj = (Object) lists.get(i);
                BigDecimal a = (BigDecimal) obj;

                results.add(a.toString());
            }

            return results;
        } catch (SQLGrammarException e) {
            return results;
        }
    }
    
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
