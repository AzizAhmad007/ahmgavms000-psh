/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.dao.impl;

import static id.co.ahm.ga.vms.app030.constant.Vms030Constant.*;
import id.co.ahm.ga.vms.app030.dao.Vms030AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovDocType;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPic;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovVisitorType;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPlant;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPlantForm;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovStatus;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovStatusForm;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovVisitorTypeForm;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nurvan Afandi
 */
@Repository("vms030AhmmoerpDtlsettingsDao")
public class Vms030AhmmoerpDtlsettingsDaoImpl extends DefaultHibernateDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk>
        implements Vms030AhmmoerpDtlsettingsDao {

    @Override
    public List<Vms030VoLovPlant> lovPlant(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_PLANTS);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovPlant> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovPlant vo = new Vms030VoLovPlant();
                vo.setPlantId((String) obj[0]);
                vo.setPlantDesc((String) obj[1]);
                
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<Vms030VoLovVisitorType> lovVisitorType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_VISITORTYPE);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovVisitorType> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovVisitorType vo = new Vms030VoLovVisitorType();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<Vms030VoLovStatus> lovStatus(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_STATUS);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovStatus> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovStatus vo = new Vms030VoLovStatus();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<Vms030VoLovDocType> lovDocType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_DOCTYPE);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovDocType> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovDocType vo = new Vms030VoLovDocType();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<Vms030VoLovPlantForm> lovPlantForm(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_PLANTS);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovPlantForm> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovPlantForm vo = new Vms030VoLovPlantForm();
                vo.setPlantId((String) obj[0]);
                vo.setPlantDesc((String) obj[1]);
                
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<Vms030VoLovVisitorTypeForm> lovVisitorTypeForm(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_VISITORTYPE);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovVisitorTypeForm> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovVisitorTypeForm vo = new Vms030VoLovVisitorTypeForm();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<Vms030VoLovStatusForm> lovStatusForm(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_STATUS);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovStatusForm> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovStatusForm vo = new Vms030VoLovStatusForm();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<Vms030VoLovPic> getPic(String nrp) {
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT "
            + "TO_CHAR(A.NRP), "
            + "A.NAME, "
            + "A.NAMA_DIVISI, "
            + "A.NAMA_DEPARTEMEN, "
            + "A.NAMA_SUBDEPARTEMEN, "
            + "A.NAMA_SEKSI, "
            + "B.VEMAIL "
            + "FROM FMHRD_GENERAL_DATAS@DBHRDTXN A "
            + "LEFT JOIN AHMMOERP_MSTKARYAWANS B "
            + "ON A.NRP = B.IIDNRP ");
         
        sql.append("WHERE A.NRP = '");
        sql.append(nrp);
        sql.append("'");
        
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms030VoLovPic> result = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;  
            for(Object object : list) {
                obj = (Object[]) object;
                Vms030VoLovPic vo = new Vms030VoLovPic();
                vo.setNrp((String) obj [0]);
                vo.setNama((String) obj[1]);
                vo.setDivisi((String) obj[2]);
                vo.setDepartment((String) obj[3]);
                vo.setSubdepartment((String) obj[4]);
                vo.setSeksi((String) obj[5]);
                vo.setEmail((String) obj[6]);
                
                result.add(vo);
            }
        }
        return result;
    }
    
    
    
}
