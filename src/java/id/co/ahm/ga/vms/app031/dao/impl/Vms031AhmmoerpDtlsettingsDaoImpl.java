/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao.impl;

import id.co.ahm.ga.vms.app031.vo.Vms031VoLovInput;
import id.co.ahm.ga.vms.app031.vo.Vms031VoLovOutput;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import id.co.ahm.ga.vms.app031.dao.Vms031AhmmoerpDtlsettingsDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import org.hibernate.SQLQuery;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Repository("vms031AhmmoerpDtlsettingsDao")
public class Vms031AhmmoerpDtlsettingsDaoImpl extends DefaultHibernateDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk> implements Vms031AhmmoerpDtlsettingsDao {

      public static final StringBuilder LOV_GENDER = new StringBuilder("SELECT VITEMCODE, VITEMNAME FROM AHMMOERP_DTLSETTINGS " +
                                                                        "WHERE RSET_VID = 'VMS_GENDER'");
      
      public static final StringBuilder LOV_TYPEBLST = new StringBuilder("SELECT VITEMCODE, VITEMNAME FROM AHMMOERP_DTLSETTINGS " +
                                                                          "WHERE RSET_VID = 'VMS_BLST_TYP'");
    
      public static final StringBuilder LOV_STATUS = new StringBuilder("SELECT VITEMCODE, VITEMNAME FROM AHMMOERP_DTLSETTINGS " +
                                                                       "WHERE RSET_VID = 'VMS_BLST_STAT'");
      
      public static final StringBuilder LOV_CARDTYPE = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
                                                                       + "FROM AHMMOERP_DTLSETTINGS "
                                                                       + "WHERE RSET_VID = 'VMS_IDTYPE'");
      
      public static final StringBuilder LOV_HEADCARDTYPE = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
                                                                       + "FROM AHMMOERP_DTLSETTINGS "
                                                                       + "WHERE RSET_VID = 'VMS_IDTYPE'"
                                                                       + "AND VITEMCODE NOT IN ('SIMA', 'SIMB', 'SIMC')");
      
      public static final StringBuilder LOV_DETAILCARDTYPE = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
                                                                       + "FROM AHMMOERP_DTLSETTINGS "
                                                                       + "WHERE RSET_VID = 'VMS_IDTYPE'"
                                                                       + "AND VITEMCODE NOT IN ('KTP', 'PASSPORT')");
    
      
    @Override
    public List<Vms031VoLovOutput> LovBlstCardType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_CARDTYPE);
         
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        
        List<Object[]> list = sqlQuery.list();
        List<Vms031VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0){
            Object[] obj;
            for(Object object : list){
                obj = (Object[]) object;
                Vms031VoLovOutput vo = new Vms031VoLovOutput();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }  
      
    @Override
    public List<Vms031VoLovOutput> LovHeadBlstCardType(DtoParamPaging input) {
        
        StringBuilder sql = new StringBuilder(LOV_HEADCARDTYPE);
         
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        
        List<Object[]> list = sqlQuery.list();
        List<Vms031VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0){
            Object[] obj;
            for(Object object : list){
                obj = (Object[]) object;
                Vms031VoLovOutput vo = new Vms031VoLovOutput();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms031VoLovOutput> lovBlstStatus(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_STATUS);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        
        List<Object[]> list = sqlQuery.list();
        List<Vms031VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0){
            Object[] obj;
            for(Object object : list){
                obj = (Object[]) object;
                Vms031VoLovOutput vo = new Vms031VoLovOutput();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms031VoLovOutput> lovBlstType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_TYPEBLST);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        
        List<Object[]> list = sqlQuery.list();
        List<Vms031VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0){
            Object[] obj;
            for(Object object : list){
                obj = (Object[]) object;
                Vms031VoLovOutput vo = new Vms031VoLovOutput();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;   
    }

    @Override
    public List<Vms031VoLovOutput> lovBlstGender(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_GENDER);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        
        List<Object[]> list = sqlQuery.list();
        List<Vms031VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0){
            Object[] obj;
            for(Object object : list){
                obj = (Object[]) object;
                Vms031VoLovOutput vo = new Vms031VoLovOutput();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms031VoLovOutput> LovDtlBlstCardType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_DETAILCARDTYPE);
         
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        
        List<Object[]> list = sqlQuery.list();
        List<Vms031VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0){
            Object[] obj;
            for(Object object : list){
                obj = (Object[]) object;
                Vms031VoLovOutput vo = new Vms031VoLovOutput();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    
}
