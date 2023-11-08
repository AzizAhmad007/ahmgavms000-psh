/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.dao.impl;

import static id.co.ahm.ga.vms.app034.constant.Vms034Constant.*;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app034.vo.Vms034VoLovOutput;
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
 * @author kahfi
 */
@Repository("vms034AhmmoerpDtlsettingsDao")
public class Vms034AhmmoerpDtlsettingsDaoImpl extends DefaultHibernateDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk> implements Vms034AhmmoerpDtlsettingsDao{

    @Override
    public List<Vms034VoLovOutput> lovVisitorType(DtoParamPaging input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vms034VoLovOutput> lovPlant(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_PLANTS);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms034VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms034VoLovOutput vo = new Vms034VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms034VoLovOutput> lovLocation(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT A.VWCTID, A.VASSETID, A.VEQUIPM, A.VDESC "
                + "FROM AHMMOSCD_MSTASSETS A "
                + "JOIN AHMMOMSC_MSTWCTS B "
                + "ON B.VWCTID = A.VWCTID "
                + "JOIN AHMMOSCD_MSTAGPLANTS C "
                + "ON C.VPLANTVAR1 = B.MPLANT_VPLANTID "
                + "WHERE A.VCTG IN ('LOB', 'RME', 'MAS') "
                + "AND C.VPLANTVAR2 = '" + input.getSearch().get("plant").toString() + "'");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms034VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms034VoLovOutput vo = new Vms034VoLovOutput();
                String code;
                if (((String) obj[2]).equalsIgnoreCase("-")) {
                    code = (String) obj[0] + "-" + (String) obj[1];
                } else {
                    code = (String) obj[0] + "-" + (String) obj[1] + "-" + (String) obj[2];
                }
                vo.setCode(code);
                vo.setName((String) obj[3]);
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms034VoLovOutput> lovIdCardTypeAll(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_IDCARD_ALL);
        sql.append("AND VITEMCODE NOT IN ('SIM')");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms034VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms034VoLovOutput vo = new Vms034VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms034VoLovOutput> lovIdCardTypeFilter(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_IDCARD_ALL);
        sql.append("AND VITEMCODE NOT IN ('SIMA', 'SIMB', 'SIMC', 'PASSPORT')");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms034VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms034VoLovOutput vo = new Vms034VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms034VoLovOutput> lovStatusCheck(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_STATUS);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms034VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms034VoLovOutput vo = new Vms034VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }
}
