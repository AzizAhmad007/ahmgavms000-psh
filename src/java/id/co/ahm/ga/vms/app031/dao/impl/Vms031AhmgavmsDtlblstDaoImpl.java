/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlblstPk;
import id.co.ahm.ga.vms.app031.dao.Vms031AhmgavmsDtlblstDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Repository("vms031AhmgavmsDtlblstDao")
public class Vms031AhmgavmsDtlblstDaoImpl extends DefaultHibernateDao<AhmgavmsDtlblst, AhmgavmsDtlblstPk> implements Vms031AhmgavmsDtlblstDao {
  
    public static final StringBuilder NID_DETAIL = new StringBuilder("SELECT NIDDTL FROM AHMGAVMS_DTLBLCKLSTS " +
    "WHERE NIDDTL = :NIDDTL");
     
    @Override
    public int validateId(DtoParamPaging input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getDetailId(DtoParamPaging input) {
        try {
        StringBuilder sql = new StringBuilder(NID_DETAIL);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("NIDDTL", input.getSearch().get("detailId").toString());
        
        
        return (Integer.valueOf((String) sqlQuery.uniqueResult()));
        }catch(Exception e){
            return 0;
        }
    }

    @Override
    public String getExportExcel(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT A.VNIK, A.VNAME, " +
                      "A.VGENDER, A.VADRESKTP, A.VADRESDOM, A.VISEMP, " +
                      "A.VCOMPANY, A.VREASON, A.VFTYPEFOTO, A.VFNAMEFOTO, " +
                      "A.VFEXTFOTO, A.VFPATHFOTO, A.DSTARTEFF, A.DENDEFF, " +
                      "A.DCREA, A.VCREA, A.DMODI, A.VMODI, B.NIDDTL, B.VIDTYPE, B.VIDNO, " +
                      "FROM AHMGAVMS_HDRBLCKLSTS A " +
                      "INNER JOIN AHMGAVMS_DTLBLCKLSTS B "+
                      "ON A.NIDHDR = B.NIDHDR");
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        
        sqlQuery.setParameter("VNIK", input)
                .setParameter("NIDDTL", input);
        
        List queryResult = sqlQuery.list();
        String vo = "";
        if (queryResult.size() > 0) {
            Object[] obj;
            boolean limitText = true;

            for (Object object : queryResult) {
                obj = (Object[]) object;

                if (limitText) {
                    vo += obj[3].toString();
                    limitText = false;
                } else {
                    vo += "; " + obj[3].toString();
                }

            }
        }
        return vo;
        
    }
    
}
