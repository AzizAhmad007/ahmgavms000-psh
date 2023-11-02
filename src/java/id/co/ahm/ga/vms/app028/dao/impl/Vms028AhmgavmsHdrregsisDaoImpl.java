/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app028.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrregsis;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrregsisPk;
import id.co.ahm.ga.vms.app028.dao.Vms028AhmgavmsHdrregsisDao;
import id.co.ahm.ga.vms.app028.vo.Vms028VoMonitoringOutput;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kahfi
 */
@Repository("vms028AhmgavmsHdrregsisDao")
public class Vms028AhmgavmsHdrregsisDaoImpl extends DefaultHibernateDao<AhmgavmsHdrregsis, AhmgavmsHdrregsisPk> implements Vms028AhmgavmsHdrregsisDao{

    private String getParam;

    @Override
    public List<Vms028VoMonitoringOutput> getMonitoring(DtoParamPaging input) {
        List<Vms028VoMonitoringOutput> vos = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("ahmgavms028p01NoReqSort", "");
        sortMap.put("ahmgavms028p01RefDocNoSort", "");
        sortMap.put("ahmgavms028p01IdTypeSort", "");
        sortMap.put("ahmgavms028p01IdNoSort", "");
        sortMap.put("ahmgavms028p01NameSort", "");
        sortMap.put("ahmgavms028p01PlantSort", "");
        sortMap.put("ahmgavms028p01StartDateSort", "");
        sortMap.put("ahmgavms028p01StatusSort", "");
        
        String noReg = AhmStringUtil.hasValue(input.getSearch().get("noReq")) ? (input.getSearch().get("noReq") + "").toUpperCase() : "";
        String noDoc = AhmStringUtil.hasValue(input.getSearch().get("noDoc")) ? (input.getSearch().get("noDoc") + "").toUpperCase() : "";
        String name = AhmStringUtil.hasValue(input.getSearch().get("name")) ? (input.getSearch().get("name") + "").toUpperCase() : "";
        String siType = AhmStringUtil.hasValue(input.getSearch().get("siType")) ? (input.getSearch().get("siType") + "").toUpperCase() : "";
        String siDate = AhmStringUtil.hasValue(input.getSearch().get("siDate")) ? (input.getSearch().get("siDate") + "").toUpperCase() : "";
        String picProj = AhmStringUtil.hasValue(input.getSearch().get("picProj")) ? (input.getSearch().get("picProj") + "").toUpperCase() : "";
        String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
        String prtType = AhmStringUtil.hasValue(input.getSearch().get("prtType")) ? (input.getSearch().get("prtType") + "").toUpperCase() : "";
        String picAhm = AhmStringUtil.hasValue(input.getSearch().get("picAhm")) ? (input.getSearch().get("picAhm") + "").toLowerCase() : "";
        String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";     
        
        StringBuilder sql = new StringBuilder("SELECT A.VNOREQSI AS NOREQSI, A.VREFDOCNO AS REFDOCNO, A.VIDTYPE AS IDTYPE, "
                + "A.VIDNO AS IDNO, A.VNAME AS NAME, "
                + "A.VTYPE AS TYPE, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_TYPE_PRTCP' "
                + "     AND C.VITEMCODE = A.VTYPE) VTYPEDESC, "
                + "A.VPLANTID AS PLANT, "
                + "(SELECT D.VDESC FROM AHMMOSCD_MSTAGPLANTS D "
                + "     WHERE D.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "A.DSTARTSI AS STARTDATE, A.VSTATUS AS STATUS, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_RSL_SI' "
                + "     AND C.VITEMCODE = A.VSTATUS) VSTATUSDESC, "
                + "B.VTYPE AS CATEGORY, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_SI_TYP' "
                + "     AND C.VITEMCODE = B.VTYPE) VSITYPE, "
                + "B.VRESULT AS RESULT, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_RSL_SI' "
                + "     AND C.VITEMCODE = B.VRESULT) VRESULTDESC, "
                + "B.DENDSI AS ENDDATE "
                + "FROM AHMGAVMS_HDRREGSIS A "
                + "JOIN AHMGAVMS_DTLREGSIS B "
                + "ON B.VNOREQSI = A.VNOREQSI "
                + "WHERE 1 = 1 ");
        
        
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
                for (Object object : list) {
                    obj = (Object[]) object;
                    Vms028VoMonitoringOutput vo = new Vms028VoMonitoringOutput();
                    vo.setReqNo((String) obj[0]);
                    vo.setRefDocNo((String) obj[1]);
                    vo.setIdType((String) obj[2]);
                    vo.setIdNo((String) obj[3]);
                    vo.setName((String) obj[4]);
                    vo.setTypeCode((String) obj[5]);
                    vo.setType((String) obj[6]);
                    vo.setPlantCode((String) obj[7]);
                    vo.setPlant((String) obj[8]);
                    vo.setStartDate((Date) obj[9]);
                    vo.setStartDateText((String) DateUtil.dateToString((Date) obj[9], "dd-MMM-yyyy"));
                    vo.setStatusCode((String) obj[10]);
                    vo.setStatus((String) obj[11]);
                    vo.setKategoriCode((String) obj[12]);
                    vo.setKategori((String) obj[13]);
                    vo.setHasilCode((String) obj[14]);
                    vo.setHasil((String) obj[15]);
                    vo.setEndDate((Date) obj[16]);
                    vo.setEndDateText((String) DateUtil.dateToString((Date) obj[16], "dd-MMM-yyyy"));
                    vo.setRowNum(i);
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
            String noReg = AhmStringUtil.hasValue(input.getSearch().get("noReq")) ? (input.getSearch().get("noReq") + "").toUpperCase() : "";
            String noDoc = AhmStringUtil.hasValue(input.getSearch().get("noDoc")) ? (input.getSearch().get("noDoc") + "").toUpperCase() : "";
            String name = AhmStringUtil.hasValue(input.getSearch().get("name")) ? (input.getSearch().get("name") + "").toUpperCase() : "";
            String siType = AhmStringUtil.hasValue(input.getSearch().get("siType")) ? (input.getSearch().get("siType") + "").toUpperCase() : "";
            String siDate = AhmStringUtil.hasValue(input.getSearch().get("siDate")) ? (input.getSearch().get("siDate") + "").toUpperCase() : "";
            String picProj = AhmStringUtil.hasValue(input.getSearch().get("picProj")) ? (input.getSearch().get("picProj") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String prtType = AhmStringUtil.hasValue(input.getSearch().get("prtType")) ? (input.getSearch().get("prtType") + "").toUpperCase() : "";
            String picAhm = AhmStringUtil.hasValue(input.getSearch().get("picAhm")) ? (input.getSearch().get("picAhm") + "").toLowerCase() : "";
            String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT COUNT (*) FROM ( ");
               
             sql.append("SELECT A.VNOREQSI AS NOREQSI, A.VREFDOCNO AS REFDOCNO, A.VIDTYPE AS IDTYPE, "
                + "A.VIDNO AS IDNO, A.VNAME AS NAME, "
                + "A.VTYPE AS TYPE, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_TYPE_PRTCP' "
                + "     AND C.VITEMCODE = A.VTYPE) VTYPEDESC, "
                + "A.VPLANTID AS PLANT, "
                + "(SELECT D.VDESC FROM AHMMOSCD_MSTAGPLANTS D "
                + "     WHERE D.VPLANTVAR2 = A.VPLANTID) VPLANTDESC, "
                + "A.DSTARTSI AS STARTDATE, A.VSTATUS AS STATUS, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_RSL_SI' "
                + "     AND C.VITEMCODE = A.VSTATUS) VSTATUSDESC, "
                + "B.VTYPE AS CATEGORY, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_SI_TYP' "
                + "     AND C.VITEMCODE = B.VTYPE) VSITYPE, "
                + "B.VRESULT AS RESULT, "
                + "(SELECT C.VITEMNAME FROM AHMMOERP_DTLSETTINGS C "
                + "     WHERE RSET_VID = 'VMS_RSL_SI' "
                + "     AND C.VITEMCODE = B.VRESULT) VRESULTDESC, "
                + "B.DENDSI AS ENDDATE "
                + "FROM AHMGAVMS_HDRREGSIS A "
                + "JOIN AHMGAVMS_DTLREGSIS B "
                + "ON B.VNOREQSI = A.VNOREQSI ");
              
            sql.append(" )");
            
            sql.append("WHERE 1 = 1 ");
            
            Query query = getCurrentSession().createSQLQuery(sql.toString())
                .setFirstResult(input.getOffset())
                .setMaxResults(input.getLimit());
            List<BigDecimal> list = query.list();
            return (Integer) list.get(0).intValueExact();
        } catch (Exception e) {
            return 0;
        }
    }
    
    private void voSetter(DtoParamPaging input) {
        try {
            if (input.getSort() == null) {

            } else {
                String param = input.getSort();

                switch (param) {
                    case "noReq":
                        getParam = "A.VNOREQSI";
                        break;
                    case "noDoc":
                        getParam = "A.VREFDOCNO";
                        break;
                    case "name":
                        getParam = "A.VNAME";
                        break;
                    case "siType":
                        getParam = "A.VTYPE";
                        break;
                    case "siDate":
                        getParam = "A.DSTARTSI";
                        break;
                    case "plant":
                        getParam = "A.VPLANTID";
                        break;
                    case "prtType":
                        getParam = "B.VTYPE";
                        break;
                    case "status":
                        getParam = "A.VSTATUS";
                        break;
                    default:
                        getParam = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
