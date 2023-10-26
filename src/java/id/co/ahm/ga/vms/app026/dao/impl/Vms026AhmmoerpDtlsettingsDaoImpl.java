/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao.impl;

import static id.co.ahm.ga.vms.app026.constant.Vms026Constant.*;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app026.vo.Vms026VoLovOutput;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.security.CryptoSecurity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kahfi
 */
@Repository("vms026AhmmoerpDtlsettingsDao")
public class Vms026AhmmoerpDtlsettingsDaoImpl extends DefaultHibernateDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk>
        implements Vms026AhmmoerpDtlsettingsDao{
    
    Date m = new Date();
    Calendar cal = Calendar.getInstance();
    
    @Override
    public List<Vms026VoLovOutput> lovVisitorType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_VISITORTYPE);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms026VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms026VoLovOutput vo = new Vms026VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public Map<String, Object> getMasterNo() {
        Map<String, Object> ret = new HashMap<>();
        cal.setTime(m);
        int month = cal.get(Calendar.MONTH);
        String monthStr = String.valueOf(month + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }
        StringBuilder sql = new StringBuilder(GET_MASTER_NO);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        Object[] obj = (Object[]) list.get(0);
        String itemName = (String) obj[0];
        String runningNo = (String) obj[1];
        ret.put("nextNo", (Integer.valueOf(runningNo)) + 1);
        ret.put("nextMo", monthStr);
        if (monthStr.equalsIgnoreCase(itemName)) {
            ret.put("masterNo", (String) runningNo);
        } else {
            ret.put("masterNo", "1");
        }
        return ret;
    }

    @Override
    public String getInvLink(String invNo) {
        StringBuilder sql = new StringBuilder(GET_LINK);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<String> list = sqlQuery.list();
        String link = list.get(0);
        String token = CryptoSecurity.encrypt(invNo);
        return link + "id=" + token;
    }

    @Override
    public List<Vms026VoLovOutput> lovPlant(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_PLANTS);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms026VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms026VoLovOutput vo = new Vms026VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms026VoLovOutput> lovBuilding(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT VBUILDING A, VBUILDING B "
                + "FROM AHMMOSCD_MSTLOCS "
                + "WHERE VPLANT = '" + input.getSearch().get("plant").toString() + "'");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms026VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms026VoLovOutput vo = new Vms026VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms026VoLovOutput> lovFloor(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT VFLOOR A, VFLOOR B "
                + "FROM AHMMOSCD_MSTLOCS "
                + "WHERE VPLANT = '" + input.getSearch().get("plant").toString() + "' "
                + "AND VBUILDING = '" + input.getSearch().get("building").toString() + "' ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms026VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms026VoLovOutput vo = new Vms026VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms026VoLovOutput> lovStatus(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_STATUS);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms026VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms026VoLovOutput vo = new Vms026VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms026VoLovOutput> lovLocation(DtoParamPaging input) {
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
        List<Vms026VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms026VoLovOutput vo = new Vms026VoLovOutput();
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
}
