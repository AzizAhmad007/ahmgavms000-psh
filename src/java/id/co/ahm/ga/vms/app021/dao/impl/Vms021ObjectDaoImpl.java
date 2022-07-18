/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.dao.impl;

import id.co.ahm.ga.vms.app021.dao.Vms021ObjectDao;
import id.co.ahm.ga.vms.app021.util.Vms021QueryUtil;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLov;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayik.op
 */
@Repository("vms021ObjectDao")
public class Vms021ObjectDaoImpl extends DefaultHibernateDao<Object, Serializable> implements Vms021ObjectDao{
    
    @Override
    public int countLovCompExternal(DtoParamPaging input, String userId, String type) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT (*) FROM ( ")
                .append("SELECT VVENDORID, VVENDORDESC "
                        + "FROM AHMMOMSC_MSTVENDORS "
                        + "WHERE VCOUNTRYID = 'ID' ");
        if (!StringUtils.isBlank(type)) {
            sql.append(" AND VVENDORID = :USERID ");
            sql.append("ORDER BY VVENDORID ");
        }
        if (StringUtils.isBlank(type)) {
            sql = Vms021QueryUtil.setSearchParamLov(sql, input.getSearch(), "VVENDORID,VVENDORDESC", "COMPANY");
        }
        
        sql.append(" )");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (!StringUtils.isBlank(type)) {
            query.setParameter("USERID", userId);
        }
        
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
    public List<Vms021VoLov> lovCompExternal(DtoParamPaging input, String userId, String type) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT VVENDORID, VVENDORDESC "
                + "FROM AHMMOMSC_MSTVENDORS "
                + "WHERE VCOUNTRYID = 'ID' ");
            if(!StringUtils.isBlank(type)){
                sql.append(" AND VVENDORID = :USERID ");
                sql.append("ORDER BY VVENDORID ");
            }
        
        if (StringUtils.isBlank(type)) {
            sql = Vms021QueryUtil.setSearchParamLov(sql, input.getSearch(), "VVENDORID,VVENDORDESC", "COMPANY");
            sql = Vms021QueryUtil.setSortParamLov(sql, input, "id,name", "COMPANY"); 
        }
        
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (!StringUtils.isBlank(type)) {
            query.setParameter("USERID", userId);
        }
        
        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());
        List<Vms021VoLov> ress = new ArrayList<>();
        try {
            query.setResultTransformer(new ResultTransformer() {
                public Object transformTuple(Object[] os, String[] strings) {

                    Vms021VoLov v = new Vms021VoLov();
                    v.setId((String) os[0]);
                    if (os[1] != null) {
                        v.setName((String) os[1]);
                    } else {
                        v.setName("");
                    }

                    return v;
                }

                public List transformList(List list) {
                    return list;
                }
            });

            return query.list();
        } catch (GenericJDBCException | SQLGrammarException z) {
            return ress;
        }
    }

}
