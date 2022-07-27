/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.util.Vms022QueryUtil;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.jxf.dao.HrHibernateDao;
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
}
