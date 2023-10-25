/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrchiefs;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmgavmsHdrchiefsDao;
import java.math.BigDecimal;

/**
 *
 * @author kahfi
 */
@Repository("vms026AhmgavmsHdrchiefsDao")
public class Vms026AhmgavmsHdrchiefsDaoImpl extends DefaultHibernateDao<AhmgavmsHdrchiefs, String> implements Vms026AhmgavmsHdrchiefsDao{

    @Override
    public int validateQuota(DtoParamPaging input) {
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                    + "FROM AHMGAVMS_DTLVISITS A "
                    + "WHERE A.INVITNO = '" + input.getSearch().get("invitNo").toString().toUpperCase() + "'");
            SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
            List<BigDecimal> list = sqlQuery.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getCountData(DtoParamPaging input) {
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                    + "FROM AHMGAVMS_HDRCHIEFS "
                    + "WHERE VMASTERNO = '" + input.getSearch().get("masterNo").toString() + "'");
            SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
            List<BigDecimal> list = sqlQuery.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
    }
    
}
