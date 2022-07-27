/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.util;

import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ayik.op
 */
public class Vms022QueryUtil {
    
    public static StringBuilder setSearchParamMonitoringTable(StringBuilder sql, Map<String, Object> filters, String columnName) {
                
        String[] col = columnName.split(",");
        if (filters != null && filters.size() > 0) {
                for (Map.Entry<String, Object> filter : filters.entrySet()) {
                    String key = filter.getKey();
                    String value = (String) filter.getValue();

                    if (key.equalsIgnoreCase("outId")) {
                        sql.append(" AND LOWER(A.VOTSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("outName")) {
                        sql.append(" AND LOWER(A.VNAME) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("nik")) {
                        sql.append(" AND LOWER(A.VPERSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("outType")) {
                        sql.append(" AND LOWER(D.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("pic", key)) {
                        sql.append(" AND LOWER(C.VNRP) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } 
//                    else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("company", key)) { //Jika ext cari ke ahmps nrp based filter ambil vvendorid
                          //cari dulu ke ahmps like name, ambil vvendorid
                          //
//                        sql.append(" AND LOWER(F.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
//                    } 
                    else if (key.equalsIgnoreCase("outStatus")) {
                        if(!"ALL".equalsIgnoreCase(value)){
                            sql.append(" AND LOWER(A.VOTSSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                        }
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("plant", key)) { 
                        if(!"PG10-00".equalsIgnoreCase(value)){
                            sql.append(" AND LOWER(E.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                        }
                    } else if (key.equalsIgnoreCase("passNumber")) {
                        sql.append(" AND LOWER(A.NAHMCARDORI) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("vacStatus")) {
                        if(!"ALL".equalsIgnoreCase(value)){
                            sql.append(" AND LOWER(A.VVACSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                        }                        
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("outIdFilter", key)) {
                        sql.append(" AND LOWER(A.VOTSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("outNameFilter", key)) {
                        sql.append(" AND LOWER(A.VNAME) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("nikFilter", key)) {
                        sql.append(" AND LOWER(A.VPERSID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("outTypeFilter", key)) {
                        sql.append(" AND LOWER(D.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } 
//                    else if (key.equalsIgnoreCase("companyFilter")) {  //Jika ext cari ke ahmps nrp based filter ambil vvendorid
//                        sql.append(" AND LOWER(F.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
//                    }  
                    else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("statusFilter", key)) {
                        sql.append(" AND LOWER(A.VOTSSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("plantFilter", key)) { //
                        sql.append(" AND LOWER(E.VPGBLNM) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("vacStatusFilter", key)) {
                        sql.append(" AND LOWER(A.VVACSTTS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("beginDateFilter", key)) {
                        sql.append(" AND TRUNC(A.DBGNEFFDT) = '").append(value.toLowerCase()).append("'");
                        //sql.append(" AND ((TRUNC(A.DBGNEFFDT) LIKE '%").append(value.toLowerCase()).append("%')");
                        //sql.append(" OR ('").append(value.toLowerCase()).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT) ");
                        //sql.append(" )");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("endDateFilter", key)) {
                        sql.append(" AND TRUNC(A.DENDEFFDT) = '").append(value.toLowerCase()).append("'");
                        //sql.append(" AND ((TRUNC(A.DENDEFFDT) LIKE '%").append(value.toLowerCase()).append("%')");
                        //sql.append(" OR ('").append(value.toLowerCase()).append("' BETWEEN A.DBGNEFFDT AND A.DENDEFFDT) ");
                        //sql.append(" )");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("passNumberFilter", key)) {
                        sql.append(" AND LOWER(A.NAHMCARDORI) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    }  else if (StringUtils.isNotEmpty(value) &&  StringUtils.equalsIgnoreCase("passExpiryFilter", key)) {
                        sql.append(" AND TRUNC(A.DPASSEXP) = '").append(value.toLowerCase()).append("'");
                    }  else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("modifyByFilter", key)) {
                        sql.append(" AND LOWER(A.VMODI) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
                    } else if (StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("modifyDateFilter", key)) {
                        sql.append(" AND TRUNC(A.DMODI) = '").append(value.toLowerCase()).append("'");
                    }   

//plant
//                    else if (key.equalsIgnoreCase("orgName")) {
//                        sql.append(" AND LOWER(( ")
//                                .append("CASE ")
//                                .append("WHEN VTYPE = 'V' AND VPARTNERID != 'OTHERS' THEN ( ")
//                                .append("SELECT ")
//                                .append("VVENDORDESC ")
//                                .append("FROM ")
//                                .append("AHMMOMSC_MSTVENDORS ")
//                                .append("WHERE ")
//                                .append("VVENDORID = VPARTNERID ) ")
//                                .append("WHEN VTYPE = 'A' THEN 'AHM' ")
//                                .append("WHEN VTYPE = 'M' THEN ( ")
//                                .append("SELECT ")
//                                .append("VND_NAME ")
//                                .append("FROM ")
//                                .append("FMPPC_PP00_VENDORS ")
//                                .append("WHERE ")
//                                .append("VNDTYP_VND_TYPE = 'D' ")
//                                .append("AND NVL(VND_KDEKS, 'D') <> 'E' ")
//                                .append("AND VND_CODE = VPARTNERID ")
//                                .append("AND ROWNUM = 1 ) ")
//                                .append("WHEN VTYPE = 'D' THEN ( ")
//                                .append("SELECT ")
//                                .append("VND_NAME ")
//                                .append("FROM ")
//                                .append("FMPPC_PP00_VENDORS ")
//                                .append("WHERE ")
//                                .append("VNDTYP_VND_TYPE = 'A' ")
//                                .append("AND NVL(VND_KDEKS, 'D') <> 'E' ")
//                                .append("AND VND_CODE = VPARTNERID ) ")
//                                .append("WHEN VTYPE = 'V' AND VPARTNERID = 'OTHERS' THEN 'OTHERS' ")
//                                .append("END )) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
//                    } else if (key.equalsIgnoreCase("status")) {
//                        sql.append(" AND LOWER(VSTATUS) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
//                    } else {
//
//                    }
                }
//            } else {
//                for (Map.Entry<String, Object> filter : filters.entrySet()) {
//                    String key = filter.getKey();
//                    String value = (String) filter.getValue();
//
//                    if (key.equalsIgnoreCase("statusFilter")) {
//                        if ("ACTIVE".equalsIgnoreCase(value)) {
//                            sql.append("AND TRUNC(SYSDATE) BETWEEN HDR.DBEGINEFF AND HDR.DENDEFF ");
//
//                        } else if ("INACTIVE".equalsIgnoreCase(value)) {
//                            sql.append("AND TRUNC(SYSDATE) NOT BETWEEN HDR.DBEGINEFF AND HDR.DENDEFF ");
//
//                        } else {
//
//                        }
//                    } else if (key.equalsIgnoreCase("batchId")) {
//                        sql.append(" AND LOWER(HDR.VBATCHID) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
//                    } else if (key.equalsIgnoreCase("nama")) {
//                        sql.append(" AND LOWER(TXN.VTRNNAME) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
//                    } else if (key.equalsIgnoreCase("group")) {
//                        sql.append(" AND LOWER(HDR.VTRNCAT) LIKE LOWER('%").append(value.toLowerCase()).append("%')");
//                    } else if (key.equalsIgnoreCase("tglMulai")) {
//                        if (StringUtils.isNotBlank(value)) {
//                            sql.append(" AND TRUNC(HDR.DBEGINEFF) = '").append(value.toLowerCase()).append("'");
//                        }
//                    } else if (key.equalsIgnoreCase("tglSelesai")) {
//                        if (StringUtils.isNotBlank(value)) {
//                            sql.append(" AND TRUNC(HDR.DENDEFF) = '").append(value.toLowerCase()).append("'");
//                        }
//
//                    } else {
//
//                    }
//                }
//            }
        }

        return sql;

    }
    
    public static StringBuilder setSearchParamLov(StringBuilder sql, Map<String, Object> filters, String columnName, String type) {

        String[] col = columnName.split(",");
        if (filters != null && filters.size() > 0) {
            for (Map.Entry<String, Object> filter : filters.entrySet()) {

                String key = filter.getKey();
                String value = (String) filter.getValue();
                
                if("PLANT".equalsIgnoreCase(type)){
                    if (key.equalsIgnoreCase("any")) {
                        sql.append(" AND (LOWER(").append(col[0]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[1]).append(") like LOWER('%").append(value.toLowerCase()).append("%')) ");
                    } else if (key.equalsIgnoreCase("id")) {
                        sql.append(" AND lower(A.VNRP) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("name")) {
                        sql.append(" AND lower(B.NAME) name lower('%").append(value.toLowerCase()).append("%')");
                    }
                } else if("OUTSOURCE".equalsIgnoreCase(type)){
                    if (key.equalsIgnoreCase("any")) {
                        sql.append(" AND (LOWER(").append(col[0]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[1]).append(") like LOWER('%").append(value.toLowerCase()).append("%')) ");
                    } else if (key.equalsIgnoreCase("id")) {
                        sql.append(" AND lower(VPGBLCD) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("name")) {
                        sql.append(" AND lower(VPGBLNM) like lower('%").append(value.toLowerCase()).append("%')");
                    }
                } else if ("GATE".equalsIgnoreCase(type)) {
                    if (key.equalsIgnoreCase("any")) {
                        sql.append(" AND (LOWER(").append(col[0]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[1]).append(") like LOWER('%").append(value.toLowerCase()).append("%') ");
                        sql.append(" OR LOWER(").append(col[2]).append(") like LOWER('%").append(value.toLowerCase()).append("%')) ");
                    } else if (key.equalsIgnoreCase("id")) {
                        sql.append(" AND lower(VPGBLCD) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("code")) {
                        sql.append(" AND lower(VPGBLNM) like lower('%").append(value.toLowerCase()).append("%')");
                    } else if (key.equalsIgnoreCase("name")) {
                        sql.append(" AND lower(VVAL) like lower('%").append(value.toLowerCase()).append("%')");
                    }
                }
                
            }
        }

        return sql;

    }
    
    public static StringBuilder setSortParamLov(StringBuilder sql, DtoParamPaging input, String columnName, String type) {

        if (input.getSort() != null) {
            List<String> order = new ArrayList<>();
            sql.append(" ORDER BY ");
            String[] col = columnName.split(",");
            
            if("PLANT".equalsIgnoreCase(type)){
                if (input.getOrder().equals(CommonConstant.ASC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" A.VNRP ASC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" B.NAME ASC ");
                    }
                } else if (input.getOrder().equals(CommonConstant.DESC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" A.VNRP DESC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" B.NAME DESC ");
                    }
                } else {
                    order.add(" ORDER BY A.VNRP ");
                }
            } else if("OUTSOURCE".equalsIgnoreCase(type)){
                if (input.getOrder().equals(CommonConstant.ASC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD ASC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM ASC ");
                    }
                } else if (input.getOrder().equals(CommonConstant.DESC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD DESC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM DESC ");
                    }
                } else {
                    order.add(" ORDER BY VPGBLCD ");
                }
            } else if("COMPANY".equalsIgnoreCase(type)){
                
            } else if ("GATE".equalsIgnoreCase(type)) {
                if (input.getOrder().equals(CommonConstant.ASC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD ASC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM ASC ");
                    } else if (input.getSort().equals(col[2])) {
                        addCommas(order);
                        order.add(" VVAL ASC ");
                    }
                } else if (input.getOrder().equals(CommonConstant.DESC)) {
                    if (input.getSort().equals(col[0])) {
                        addCommas(order);
                        order.add(" VPGBLCD DESC ");
                    } else if (input.getSort().equals(col[1])) {
                        addCommas(order);
                        order.add(" VPGBLNM DESC ");
                    } else if (input.getSort().equals(col[2])) {
                        addCommas(order);
                        order.add(" VVAL DESC ");
                    }
                } else {
                    order.add(" ORDER BY VPGBLCD ");
                }
            }

            if (CollectionUtils.isNotEmpty(order)) {
                for (String string : order) {
                    sql.append(string);
                }
            }
        }

        return sql;

    }
    
    private static void addCommas(List<String> list) {
        if (list.size() > 0) {
            list.add(" , ");
        }

    }
   
}
