/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsTxninouts;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmgavmsTxninoutsDao;
import id.co.ahm.ga.vms.app034.vo.Vms034VoMonitoringOutput;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kahfi
 */
@Repository("vms034AhmgavmsTxninoutsDao")
public class Vms034AhmgavmsTxninoutsDaoImpl extends DefaultHibernateDao<AhmgavmsTxninouts, BigDecimal> implements Vms034AhmgavmsTxninoutsDao{

    @Override
    public String isCheckIn(String nik) {
        try {
            StringBuilder sql = new StringBuilder("SELECT '1' "
                + "FROM AHMGAVMS_TXNINOUTS "
                + "WHERE VNIK = '").append(nik).append("' AND VSTATUS = 'I' ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        String data = (String) sqlQuery.list().get(0);
        return data;
        } catch (Exception e) {
            return "0";
        }
    }

    @Override
    public List<Vms034VoMonitoringOutput> getMonitoring(DtoParamPaging input) {
        List<Vms034VoMonitoringOutput> vos = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        
        sortMap.put("ahmgavms034p01StatusSort", "");
        sortMap.put("ahmgavms034p01NameSort", "");
        sortMap.put("ahmgavms034p01NikSort", "");
        sortMap.put("ahmgavms034p01NoHandphoneSort", "");
        sortMap.put("ahmgavms034p01VisitorTypeSort", "");
        sortMap.put("ahmgavms034p01InvitNoSort", "");
        sortMap.put("ahmgavms034p01PlantSort", "");
        sortMap.put("ahmgavms034p01LocSpecSort", "");
        sortMap.put("ahmgavms034p01PurposeSort", "");
        sortMap.put("ahmgavms034p01SimSort", "");
        sortMap.put("ahmgavms034p01PicAhmSort", "");
        sortMap.put("ahmgavms034p01PassCardSort", "");
        sortMap.put("ahmgavms034p01CheckInSort", "");
        sortMap.put("ahmgavms034p01CheckOutSort", "");
        
        StringBuilder sql = new StringBuilder("SELECT * "
                + "FROM AHMGAVMS_TXNINOUTS A "
                + "JOIN AHMGAVMS_MSTVISITORS B "
                + "ON B.VNIK = A.VNIK "
                + "JOIN AHMGAVMS_HDRINVITS C "
                + "ON C.VMASTERNO = A.VINVITNO "
                + "JOIN AHMGAVMS_HDRCHIEFS D "
                + "ON D.VMASTERNO = C.VMASTERNO "
                + "WHERE 1 = 1 ");
        if (!input.getSearch().get("typeRfid").toString().equalsIgnoreCase("") && 
                !input.getSearch().get("rfidNo").toString().equalsIgnoreCase("")) {
            if (input.getSearch().get("typeRfid").toString().equalsIgnoreCase("KTP")) {
                sql.append("AND A.VNIK = (SELECT KTP.VNIK "
                        + "FROM AHMGAVMS_MSTRFIDKTPS KTP "
                        + "WHERE KTP.VRFIDKTP = '").append(input.getSearch().get("rfidNo").toString().toUpperCase()).append("' ) ");
            } else if (input.getSearch().get("typeRfid").toString().equalsIgnoreCase("SIM")) {
                sql.append("AND A.VNIK = (SELECT SIM.VNIK "
                        + "FROM AHMGAVMS_MSTRFIDSIMS SIM "
                        + "WHERE SIM.VRFIDSIM = '").append(input.getSearch().get("rfidNo").toString().toUpperCase()).append("' ) ");
            }
        }
        if (!input.getSearch().get("nik").toString().equalsIgnoreCase("")) {
            sql.append("AND B.VNIK = '").append(input.getSearch().get("nik").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("name").toString().equalsIgnoreCase("")) {
            sql.append("AND B.VNAME LIKE '%").append(input.getSearch().get("name").toString().toUpperCase()).append("%' ");
        }
        if (!input.getSearch().get("rfidPassCard").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VPASSCD = (SELECT PC.VPASSNO "
                    + "FROM AHMGAVMS_MSTPASSCDS PC "
                    + "WHERE PC.VRFIDNO = '").append(input.getSearch().get("rfidPassCard").toString().toUpperCase()).append("' ) ");
        }
        if (!input.getSearch().get("invitNo").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VINVITNO LIKE '%").append(input.getSearch().get("invitNo").toString().toUpperCase()).append("%' ");
        }
        if (!input.getSearch().get("visitorType").toString().equalsIgnoreCase("")) {
            sql.append("AND C.VTYPE = '").append(input.getSearch().get("visitorType").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
            if (input.getSearch().get("status").toString().equalsIgnoreCase("I")) {
                sql.append("AND A.VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");
            } else {
                sql.append("AND A.VSTATUS NOT IN ('I') ");
            }
        }
        if (!input.getSearch().get("noPassCard").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VPASSCD LIKE '%").append(input.getSearch().get("noPassCard").toString().toUpperCase()).append("%' ");
        }
        if (!input.getSearch().get("plant").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VPLANTID = '").append(input.getSearch().get("plant").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("company").toString().equalsIgnoreCase("")) {
            sql.append("AND D.VCOMPANY LIKE '%").append(input.getSearch().get("company").toString().toUpperCase()).append("%' ");
        }
        if (!input.getSearch().get("locSpec").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VBUILDING = '").append(input.getSearch().get("locSpec").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("startDate").toString().equalsIgnoreCase("") 
                && input.getSearch().get("endDate").toString().equalsIgnoreCase("")) {
            sql.append("AND A.DSTARTDATE BETWEEN TO_DATE('").append(input.getSearch().get("startDate").toString().toUpperCase())
                    .append("', 'DD-MM-YYYY') AND TO_DATE('").append(input.getSearch().get("endDate").toString().toUpperCase())
                    .append("', 'DD-MM-YYYY') ");
        }
        
        try {
            return vos;
        } catch (Exception e) {
            return vos;
        }
    }

    @Override
    public int getMonitoringCount(DtoParamPaging input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void orderClause(DtoParamPaging input, StringBuilder query, Map<String, String> clause, String param) {
        if (input.getSort() != null && !StringUtils.isEmpty(input.getSort())) {
            query.append(" ORDER BY ");
            query.append(param + " ");
            if (input.getOrder() != null && !StringUtils.isEmpty(input.getOrder())) {
                query.append(input.getOrder() + " ");
            }
        }
    }
}
