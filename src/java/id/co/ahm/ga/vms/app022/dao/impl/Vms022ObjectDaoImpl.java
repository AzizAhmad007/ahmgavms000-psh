/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.util.Vms022QueryUtil;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
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
@Repository("vms022ObjectDao")
public class Vms022ObjectDaoImpl extends DefaultHibernateDao<Object, Serializable> implements Vms022ObjectDao{
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
            sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VVENDORID,VVENDORDESC", "COMPANY");
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
    public List<Vms022VoLov> lovCompExternal(DtoParamPaging input, String userId, String type) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT VVENDORID, VVENDORDESC "
                + "FROM AHMMOMSC_MSTVENDORS "
                + "WHERE VCOUNTRYID = 'ID' ");
            if(!StringUtils.isBlank(type)){
                sql.append(" AND VVENDORID = :USERID ");
                sql.append("ORDER BY VVENDORID ");
            }
        
        if (StringUtils.isBlank(type)) {
            sql = Vms022QueryUtil.setSearchParamLov(sql, input.getSearch(), "VVENDORID,VVENDORDESC", "COMPANY");
            sql = Vms022QueryUtil.setSortParamLov(sql, input, "id,name", "COMPANY"); 
        }
        
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (!StringUtils.isBlank(type)) {
            query.setParameter("USERID", userId);
        }
        
        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());
        List<Vms022VoLov> ress = new ArrayList<>();
        try {
            query.setResultTransformer(new ResultTransformer() {
                public Object transformTuple(Object[] os, String[] strings) {

                    Vms022VoLov v = new Vms022VoLov();
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

    @Override
    public List<String> getRoles(String username) {
        Query query = getCurrentSession().createSQLQuery(Vms022Constant.SQL_GET_USER_ROLES);
        query.setParameter("USERNAME", username);
        return query.list();
    }
}
