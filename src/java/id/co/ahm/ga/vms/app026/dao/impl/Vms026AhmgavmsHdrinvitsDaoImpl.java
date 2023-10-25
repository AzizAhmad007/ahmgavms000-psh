/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrinvits;
import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringOutput;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmgavmsHdrinvitsDao;
import java.math.BigDecimal;
import org.hibernate.Query;

/**
 *
 * @author kahfi
 */
@Repository("vms026AhmgavmsHdrinvitsDao")
public class Vms026AhmgavmsHdrinvitsDaoImpl extends DefaultHibernateDao<AhmgavmsHdrinvits, String> implements Vms026AhmgavmsHdrinvitsDao{
    
    private String getParam;
    
    @Override
    public List<Vms026VoMonitoringOutput> getMonitoring(DtoParamPaging input) {
        List<Vms026VoMonitoringOutput> vos = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("ahmgavms026p01StatusSort", "");
        sortMap.put("ahmgavms026p01VisitorTypeSort", "");
        sortMap.put("ahmgavms026p01PlantSort", "");
        sortMap.put("ahmgavms026p01LocSort", "");
        sortMap.put("ahmgavms026p01LocSpecSort", "");
        sortMap.put("ahmgavms026p01PurposeSort", "");
        sortMap.put("ahmgavms026p01StartDateSort", "");
        sortMap.put("ahmgavms026p01EndDateSort", "");
        sortMap.put("ahmgavms026p01CompanySort", "");
        sortMap.put("ahmgavms026p01TotalQuotaSort", "");

        StringBuilder sql = new StringBuilder("SELECT A.VMASTERNO, A.DPLSTART, A.DPLEND, A.VNRPPIC, "
                + "(SELECT C.VDESC "
                + "FROM AHMMOSCD_MSTAGPLANTS C "
                + "WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTID, A.VLOC, A.VLOCSPEC, "
                + "(SELECT D.VITEMNAME "
                + "FROM AHMMOERP_DTLSETTINGS D "
                + "WHERE D.RSET_VID = 'VMS_VST2' "
                + "AND D.VITEMCODE = A.VTYPE) VTYPE, A.VPURPOSE, A.VSTATUS, B.VCOMPANY, B.NQUOTA, B.VINVITNO "
                + "FROM AHMGAVMS_HDRINVITS A "
                + "JOIN AHMGAVMS_HDRCHIEFS B "
                + "ON A.VMASTERNO = B.VMASTERNO "
                + "WHERE 1 = 1 ");
        if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("visitorType").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VTYPE = '").append(input.getSearch().get("visitorType").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("plant").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VPLANTID = '").append(input.getSearch().get("plant").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("startDate").toString().equalsIgnoreCase("")) {
            sql.append("AND A.DPLSTART BETWEEN TO_DATE('").append(input.getSearch().get("startDate").toString().toUpperCase())
                    .append("', 'DD-MM-YYYY') AND TO_DATE('").append(input.getSearch().get("endDate").toString().toUpperCase())
                    .append("', 'DD-MM-YYYY') ");
        }
        if (!input.getSearch().get("endDate").toString().equalsIgnoreCase("")) {
            sql.append("AND A.DPLEND BETWEEN TO_DATE('").append(input.getSearch().get("startDate").toString().toUpperCase())
                    .append("', 'DD-MM-YYYY') AND TO_DATE('").append(input.getSearch().get("endDate").toString().toUpperCase())
                    .append("', 'DD-MM-YYYY') ");
        }
        if (!input.getSearch().get("nrppic").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VNRPPIC LIKE '%").append(input.getSearch().get("nrppic").toString().toUpperCase()).append("%' ");
        }
        if (!input.getSearch().get("company").toString().equalsIgnoreCase("")) {
            sql.append("AND B.VCOMPANY LIKE '%").append(input.getSearch().get("company").toString().toUpperCase()).append("%' ");
        }
        orderClause(input, sql, sortMap, getParam);
        Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
        try {
            List<Object[]> list = query.list();
            if (list.size() > 0) {
                Object[] obj;
                int i = 0;
                for(Object object : list) {
                    obj = (Object[]) object;
                    Vms026VoMonitoringOutput vo = new Vms026VoMonitoringOutput();
                    vo.setMasterNo((String) obj[0]);
                    vo.setStartDate((Date) obj[1]);
                    vo.setStartDateText((String) DateUtil.dateToString((Date) obj[1], "dd-MMM-yyyy"));
                    vo.setEndDate((Date) obj[2]);
                    vo.setEndDateText((String) DateUtil.dateToString((Date) obj[2], "dd-MMM-yyyy"));
                    vo.setPicAhm((String) obj[3]);
                    vo.setPlant((String) obj[4]);
                    vo.setLoc((String) obj[5]);
                    vo.setLocSpec((String) obj[6]);
                    vo.setVisitorType((String) obj[7]);
                    vo.setPurpose((String) obj[8]);
                    vo.setStatus((String) obj[9]);
                    vo.setCompany((String) obj[10]);
                    BigDecimal quota = (BigDecimal) obj[11];
                    vo.setTotalQuota(Integer.valueOf(quota.intValueExact()));
                    vo.setRowNum(i);
                    vo.setInvitNo((String) obj[12]);
                    i++;
                    vos.add(vo);
                }
            }
        } catch (Exception e) {
            return vos;
        }
        return vos;
    }

    @Override
    public int getMonitoringCount(DtoParamPaging input) {
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                + "FROM AHMGAVMS_HDRINVITS A "
                + "JOIN AHMGAVMS_HDRCHIEFS B "
                + "ON A.VMASTERNO = B.VMASTERNO "
                + "WHERE 1 = 1 ");
            if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
                sql.append("AND VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");
            }
            if (!input.getSearch().get("visitorType").toString().equalsIgnoreCase("")) {
                sql.append("AND VTYPE = '").append(input.getSearch().get("visitorType").toString().toUpperCase()).append("' ");
            }
            if (!input.getSearch().get("plant").toString().equalsIgnoreCase("")) {
                sql.append("AND VPLANTID = '").append(input.getSearch().get("plant").toString().toUpperCase()).append("' ");
            }
            if (!input.getSearch().get("startDate").toString().equalsIgnoreCase("")) {
                sql.append("AND DPLSTART BETWEEN '").append(input.getSearch().get("startDate").toString().toUpperCase()).append("' ");
            }
            if (!input.getSearch().get("endDate").toString().equalsIgnoreCase("")) {
                sql.append("AND DPLEND BETWEEN '").append(input.getSearch().get("endDate").toString().toUpperCase()).append("' ");
            }
            Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
            List<BigDecimal> list = query.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
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
