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
import id.co.ahm.jxf.security.CryptoSecurity;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.exception.SQLGrammarException;
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
    public List<String> getFileName(String type, String name, String ext) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT "
                + "    VFPATHDOC "
                + "FROM "
                + "    AHMGAVMS_MSTREFDOCS "
                + "WHERE "
                + "    VFNAMEDOC = :name "
                + "    AND VFEXTDOC = :ext "
                + "    AND VFTYPEDOC = :type ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("VFTYPEDOC", type);
        query.setParameter("VFNAMEDOC", name);
        query.setParameter("VFEXTDOC", ext);

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
