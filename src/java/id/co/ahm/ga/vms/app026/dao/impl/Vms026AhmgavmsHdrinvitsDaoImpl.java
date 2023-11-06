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
import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringDetail;
import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringEmail;
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
        sortMap.put("ahmgavms026p01MasterNoSort", "");
        sortMap.put("ahmgavms026p01StatusSort", "");
        sortMap.put("ahmgavms026p01VisitorTypeSort", "");
        sortMap.put("ahmgavms026p01PlantSort", "");
        sortMap.put("ahmgavms026p01LocSort", "");
        sortMap.put("ahmgavms026p01LocSpecSort", "");
        sortMap.put("ahmgavms026p01StartDateSort", "");
        sortMap.put("ahmgavms026p01EndDateSort", "");
        sortMap.put("ahmgavms026p01NameSort", "");
        sortMap.put("ahmgavms026p01CompanySort", "");
        sortMap.put("ahmgavms026p01TotalQuotaSort", "");
        sortMap.put("ahmgavms026p01PicAhmSort", "");

        StringBuilder sql = new StringBuilder("SELECT A.VMASTERNO, A.DPLSTART, A.DPLEND, A.VNRPPIC, "
                + "(SELECT C.VDESC "
                + "FROM AHMMOSCD_MSTAGPLANTS C "
                + "WHERE C.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, A.VLOC, A.VLOCSPEC, "
                + "(SELECT D.VITEMDESC "
                + "FROM AHMMOERP_DTLSETTINGS D "
                + "WHERE D.RSET_VID = 'VMS_VST2' "
                + "AND D.VITEMCODE = A.VTYPE) VTYPEDESC, A.VPURPOSE, "
                + "(CASE " 
                + "WHEN TRUNC(A.DPLEND) < TRUNC(SYSDATE) THEN " 
                + "    'TIDAK AKTIF' " 
                + "WHEN A.VSTATUS = 'Y' THEN " 
                + "    'AKTIF' " 
                + "WHEN A.VSTATUS = 'D' THEN " 
                + "    'DRAFT' " 
                + "ELSE " 
                + "    '-' " 
                + "END) VSTATUS, B.VNAME, B.VCOMPANY, B.NQUOTA, B.VINVITNO, A.VTYPE, A.VPLANTID, B.VEMAIL, B.VNOHP, "
                + "B.VINVLINK "
                + "FROM AHMGAVMS_HDRINVITS A "
                + "JOIN AHMGAVMS_HDRCHIEFS B "
                + "ON A.VMASTERNO = B.VMASTERNO "
                + "WHERE 1 = 1 ");
        if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
            if (input.getSearch().get("status").toString().equalsIgnoreCase("N")) {
                sql.append("AND TRUNC(A.DPLEND) < TRUNC(SYSDATE) ");
            } else {
                sql.append("AND A.VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");
                sql.append("AND TRUNC(A.DPLEND) > TRUNC(SYSDATE) - 1 ");
            }
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
        if (!input.getSearch().get("masterNo").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VMASTERNO LIKE '%").append(input.getSearch().get("masterNo").toString().toUpperCase()).append("%' ");
        }
        if (!input.getSearch().get("visitorName").toString().equalsIgnoreCase("")) {
            sql.append("AND B.VNAME LIKE '%").append(input.getSearch().get("visitorName").toString().toUpperCase()).append("%' ");
        }
        if (!input.getSearch().get("locSpec").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VLOC = '").append(input.getSearch().get("locSpec").toString().toUpperCase()).append("' ");
        }
        voSetter(input);
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
                    vo.setName((String) obj[10]);
                    vo.setCompany((String) obj[11]);
                    BigDecimal quota = (BigDecimal) obj[12];
                    vo.setTotalQuota(Integer.valueOf(quota.intValueExact()));
                    vo.setRowNum(i);
                    vo.setInvitNo((String) obj[13]);
                    vo.setVisitorTypeCode((String) obj[14]);
                    vo.setPlantCode((String) obj[15]);
                    vo.setEmail((String) obj[16]);
                    vo.setNoHp((String) obj[17]);
                    vo.setLink((String) obj[18]);
                    
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
                if (input.getSearch().get("status").toString().equalsIgnoreCase("N")) {
                    sql.append("AND TRUNC(A.DPLEND) < TRUNC(SYSDATE) ");
                } else {
                    sql.append("AND A.VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");
                    sql.append("AND TRUNC(A.DPLEND) > TRUNC(SYSDATE) - 1 ");
                }
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
            if (!input.getSearch().get("masterNo").toString().equalsIgnoreCase("")) {
                sql.append("AND A.VMASTERNO LIKE '%").append(input.getSearch().get("masterNo").toString().toUpperCase()).append("%' ");
            }
            if (!input.getSearch().get("visitorName").toString().equalsIgnoreCase("")) {
                sql.append("AND B.VNAME LIKE '%").append(input.getSearch().get("visitorName").toString().toUpperCase()).append("%' ");
            }
            if (!input.getSearch().get("locSpec").toString().equalsIgnoreCase("")) {
                sql.append("AND A.VLOC = '").append(input.getSearch().get("locSpec").toString().toUpperCase()).append("' ");
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
    
    private void voSetter(DtoParamPaging input) {
        try {
            if (input.getSort() == null) {

            } else {
                String param = input.getSort();

                switch (param) {
                    case "masterNo":
                        getParam = "A.VMASTERNO";
                        break;
                    case "status":
                        getParam = "VSTATUS";
                        break;
                    case "visitorType":
                        getParam = "VTYPEDESC";
                        break;
                    case "plant":
                        getParam = "VPLANTDESC";
                        break;
                    case "loc":
                        getParam = "A.VLOC";
                        break;
                    case "locSpec":
                        getParam = "A.VLOCSPEC";
                        break;
                    case "startDateText":
                        getParam = "A.DPLSTART";
                        break;
                    case "endDateText":
                        getParam = "A.DPLEND";
                        break;
                    case "name":
                        getParam = "B.VNAME";
                        break;
                    case "company":
                        getParam = "B.VCOMPANY";
                        break;
                    case "totalQuota":
                        getParam = "B.NQUOTA";
                        break;
                    case "picAhm":
                        getParam = "A.VNRPPIC";
                        break;
                    default:
                        getParam = null;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vms026VoMonitoringDetail> getMonitoringDetail(DtoParamPaging input) {
        List<Vms026VoMonitoringDetail> vos = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();

        StringBuilder sql = new StringBuilder("SELECT A.NCDVISIT, "
                + "A.VNAME, "
                + "(SELECT B.VITEMNAME "
                + "FROM AHMMOERP_DTLSETTINGS B "
                + "WHERE B.RSET_VID = 'VMS_IDTYPE' "
                + "AND B.VITEMCODE = A.VIDTYPE) VIDTYPE, A.VNIK "
                + "FROM AHMGAVMS_DTLVISITS A "
                + "WHERE 1 = 1 ");
        if (!input.getSearch().get("invitNo").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VINVITNO = '").append(input.getSearch().get("invitNo").toString().toUpperCase()).append("' ");
        }
        sql.append("ORDER BY A.NCDVISIT ");
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
                    Vms026VoMonitoringDetail vo = new Vms026VoMonitoringDetail();
                    vo.setIdVisit((BigDecimal) obj[0]);
                    vo.setName((String) obj[1]);
                    vo.setIdType((String) obj[2]);
                    vo.setNoId((String) obj[3]);
                    vo.setRowNum(i);
                    i++;
                    vo.setNoUrut(i);
                    vos.add(vo);
                }
            }
        } catch (Exception e) {
            return vos;
        }
        return vos;
    }
    
    @Override
    public List<Vms026VoMonitoringEmail> getMonitoringEmail(DtoParamPaging input) {
        List<Vms026VoMonitoringEmail> vos = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT DCREA, VTO, "
                + "(CASE "
                + "WHEN VFLAG == '1' THEN "
                + "     'Success' "
                + "WHEN VFLAG == '0' THEN "
                + "     'Failed' "
                + "ELSE "
                + "     '-' "
                + "END) VSTATUS "
                + "FROM AHMGAVMS_LOGEMAILS "
                + "WHERE 1 = 1 ");
        if (!input.getSearch().get("invitNo").toString().equalsIgnoreCase("")) {
            sql.append("AND VCODE = '").append(input.getSearch().get("invitNo").toString().toUpperCase()).append("' ");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
        try {
            List<Object[]> list = query.list();
            if (list.size() > 0) {
                Object[] obj;
                int i = 0;
                for (Object object : list) {
                    obj = (Object[]) object;
                    Vms026VoMonitoringEmail vo = new Vms026VoMonitoringEmail();
                    vo.setDateSend((String) DateUtil.dateToString((Date) obj[0], "dd-MMM-yyyy"));
                    vo.setEmailTo((String) obj[1]);
                    vo.setStatus((String) obj[2]);
                    
                    vos.add(vo);
                }
            }
            return vos;
        } catch (Exception e) {
            return vos;
        }
    }

    @Override
    public int getMonitoringDetailCount(DtoParamPaging input) {
        try {
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                    + "FROM AHMGAVMS_DTLVISITS A "
                    + "WHERE 1 = 1 ");
            if (!input.getSearch().get("invitNo").toString().equalsIgnoreCase("")) {
                sql.append("AND A.VINVITNO = '").append(input.getSearch().get("invitNo").toString().toUpperCase()).append("' ");
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
}