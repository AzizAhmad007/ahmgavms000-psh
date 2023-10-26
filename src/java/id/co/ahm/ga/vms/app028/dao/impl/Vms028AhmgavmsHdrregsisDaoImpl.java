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
        
        StringBuilder sql = new StringBuilder("SELECT A.VNOREQSI AS NOREQSI, A.VREFDOCNO AS REFDOCNO, A.VIDTYPE AS IDTYPE, "
                + "A.VIDNO AS IDNO, A.VNAME AS NAME, "
                + "A.VTYPE AS TYPE, A.VPLANTID AS PLANT, A.DSTARTSI AS STARTDATE, A.VSTATUS AS STATUS, B.VTYPE AS CATEGORY, B.VRESULT AS RESULT, "
                + "B.DENDSI AS ENDDATE "
                + "FROM AHMGAVMS_HDRREGSIS A "
                + "JOIN AHMGAVMS_DTLREGSIS B "
                + "ON B.VNOREQSI = A.VNOREQSI "
                + "WHERE 1 = 1 ");
        
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
                    vo.setType((String) obj[5]);
                    vo.setPlant((String) obj[6]);
                    vo.setStartDate((Date) obj[7]);
                    vo.setStartDateText((String) DateUtil.dateToString((Date) obj[7], "dd-MMM-yyyy"));
                    vo.setStatus((String) obj[8]);
                    vo.setKategori((String) obj[9]);
                    vo.setHasil((String) obj[10]);
                    vo.setEndDate((Date) obj[11]);
                    vo.setEndDateText((String) DateUtil.dateToString((Date) obj[11], "dd-MMM-yyyy"));
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
            StringBuilder sql = new StringBuilder("SELECT COUNT(0) "
                + "FROM AHMGAVMS_HDRREGSIS A "
                + "JOIN AHMGAVMS_DTLREGSIS B "
                + "ON B.VNOREQSI = A.VNOREQSI "
                + "WHERE 1 = 1 ");
            
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
