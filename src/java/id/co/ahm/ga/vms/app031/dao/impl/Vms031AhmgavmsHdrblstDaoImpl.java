/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrblst;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrblstPk;
import id.co.ahm.ga.vms.app031.constant.Vms031Constant;
import id.co.ahm.ga.vms.app031.dao.Vms031AhmgavmsHdrblstDao;
import id.co.ahm.ga.vms.app031.util.Vms031QueryUtil;
import id.co.ahm.ga.vms.app031.vo.Vms031VoMonitoring;
import id.co.ahm.jxf.constant.CommonConstant;
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
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Repository("vms031AhmgavmsHdrblstDao")
public class Vms031AhmgavmsHdrblstDaoImpl extends DefaultHibernateDao<AhmgavmsHdrblst, AhmgavmsHdrblstPk> implements Vms031AhmgavmsHdrblstDao {
    
    private String getParam;
    
    public static final StringBuilder NID_HEADER = new StringBuilder("SELECT NIDHDR FROM AHMGAVMS_HDRBLCKLSTS " +
    "WHERE NIDHDR = :NIDHDR");
    
    public static final StringBuilder COUNT_BLACKLIST = new StringBuilder("SELECT COUNT(0) FROM AHMGAVMS_HDRBLCKLSTS " +
                        "WHERE VNIK = :VNIK " +
                        "AND TRUNC(DENDEFF) > TRUNC(SYSDATE)");  
    
    public static final StringBuilder NIK_BLACKLIST = new StringBuilder("SELECT DISTINCT NIDHDR FROM AHMGAVMS_HDRBLCKLSTS " +
                        "WHERE TRUNC(DENDEFF) > TRUNC(SYSDATE) "); 
    
    public static final StringBuilder SQL_DIVISI = new StringBuilder("SELECT DISTINCT NAMA_DIVISI, NAMA_DEPARTEMEN, NAMA_SUBDEPARTEMEN, NAMA_SEKSI "
            + "FROM FMHRD_GENERAL_DATAS@DBHRDTXN "
            + "WHERE NRP = :NRP");
    
    @Override
    public BigDecimal validateNik(DtoParamPaging input) {
       StringBuilder sql = new StringBuilder(COUNT_BLACKLIST);
        try{
            SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
            sqlQuery.setParameter("VNIK", input.getSearch().get("nik").toString());

            List<Object[]> list = sqlQuery.list();
            Object[] obj = (Object[]) list.get(0);
            return (BigDecimal) sqlQuery.uniqueResult();
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
        
    }
    
    

    @Override
    public BigDecimal getNik(DtoParamPaging id) {
        try{
            StringBuilder sql = new StringBuilder(NIK_BLACKLIST);
            
            sql.append("AND VNIK = '").append(id.getSearch().get("nik")).append("' ");
        
            Query sqlQuery = getCurrentSession().createSQLQuery(sql.toString());

            List<BigDecimal> list = sqlQuery.list();
            Integer obj = list.get(0).intValueExact();
            return (BigDecimal) sqlQuery.uniqueResult();
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public String getDivisi(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(SQL_DIVISI);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("NRP", input.getSearch().get("nrp").toString());
        
        return (String) sqlQuery.uniqueResult();
    }

    @Override
    public List<Vms031VoMonitoring> getMonitoring(DtoParamPaging input) {
        List<Vms031VoMonitoring> vos = new ArrayList<>();
        Map<String, String> sortMap = new HashMap<>();
        
        sortMap.put("ahmgavms031p01StatusSort", "");
        sortMap.put("ahmgavms031p01NrpSort", "");
        sortMap.put("ahmgavms031p01NamaSort", "");
        sortMap.put("ahmgavms031p01NikSort", "");
        sortMap.put("ahmgavms031p01JenisKelaminSort", "");
        sortMap.put("ahmgavms031p01AlamatKtpSort", "");
        sortMap.put("ahmgavms031p01AlamatDomisiliSort", "");
        sortMap.put("ahmgavms031p01TipeKaryawanBlacklistSort", "");
        sortMap.put("ahmgavms031p01NamaPerusahaanSort", "");
        sortMap.put("ahmgavms031p01AlasanBlacklistSort", "");
        sortMap.put("ahmgavms031p01TypeFotoSort", "");
        sortMap.put("ahmgavms031p01NamaFotoSort", "");
        sortMap.put("ahmgavms031p01ExtensionFotoSort", "");
        sortMap.put("ahmgavms031p01PathFotoSort", "");
        sortMap.put("ahmgavms031p01TglStartEffectiveSort", "");
        sortMap.put("ahmgavms031p01TglEndEffectiveSort", "");
        sortMap.put("ahmgavms031p01DateCreatedSort", "");
        sortMap.put("ahmgavms031p01PicCreatedSort", "");
        sortMap.put("ahmgavms031p01DateUpdateSort", "");
        sortMap.put("ahmgavms031p01PicUpdateSort", "");
        sortMap.put("ahmgavms031p01JenisKartuIdentitasSort", "");
        sortMap.put("ahmgavms031p01NoIdentitasSort", "");
        sortMap.put("ahmgavms031p01TotalMonitoring", "");

        StringBuilder sql = new StringBuilder("SELECT DISTINCT A.VNIK, A.VNRPPIC, A.VNAME, "
                + "A.VGENDER, A.VADRESKTP, A.VADRESDOM, A.VISEMP, "
                + "A.VCOMPANY, A.VREASON, A.VFTYPEFOTO, A.VFNAMEFOTO, "
                + "A.VFEXTFOTO, A.VFPATHFOTO, A.DSTARTEFF, A.DENDEFF, A.DCREA, A.VCREA, "
                + "B.VIDTYPE, B.VIDNO, "
                + "(CASE " 
                + "WHEN A.DENDEFF > SYSDATE - 1 THEN " 
                + "    'AKTIF' " 
                + "ELSE " 
                + "    'TIDAK AKTIF' " 
                + "END) AS STATUS "
                + "FROM AHMGAVMS_HDRBLCKLSTS A "
                + "LEFT JOIN AHMGAVMS_DTLBLCKLSTS B "
                + "ON A.NIDHDR = B.NIDHDR "
                + "WHERE 1 = 1 ");
        if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
                if (input.getSearch().get("status").toString().equalsIgnoreCase("A")) {
                    sql.append("AND A.DENDEFF <  SYSDATE ");
                } else {
                sql.append("AND A.VSTATUS = '").append(input.getSearch().get("status").toString().toUpperCase()).append("' ");    
                sql.append("AND A.DENDEFF > SYSDATE - 1 THEN ");
            }
            }
        if (!input.getSearch().get("nik").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VNIK LIKE '%").append(input.getSearch().get("nik").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("nama").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VNAME LIKE '%").append(input.getSearch().get("nama").toString().toUpperCase()).append("' ");
        }

        if (!input.getSearch().get("jenisKartuIdentitas").toString().equalsIgnoreCase("")) {
            sql.append("AND B.VIDTYPE = '").append(input.getSearch().get("jenisKartuIdentitas").toString().toUpperCase()).append("' ");
        }
        if (!input.getSearch().get("noIdentitas").toString().equalsIgnoreCase("")) {
            sql.append("AND B.VIDNO LIKE '%").append(input.getSearch().get("noIdentitas").toString().toUpperCase()).append("' ");
        }

        if (!input.getSearch().get("tipeKaryawanBlacklist").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VISEMP = '").append(input.getSearch().get("tipeKaryawanBlacklist").toString().toUpperCase()).append("' ");
        }

        if (!input.getSearch().get("nrp").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VNRPPIC = '").append(input.getSearch().get("nrp").toString().toUpperCase()).append("' ");
        }
        
        if (!input.getSearch().get("tglStartEffective").toString().equalsIgnoreCase("")) {
//            sql.append("AND DSTARTEFF = '").append(input.getSearch().get("tglStartEffective").toString().toUpperCase()).append("' ");
              sql.append("AND A.DSTARTEFF BETWEEN TO_DATE('").append(input.getSearch().get("tglStartEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') AND TO_DATE('").append(input.getSearch().get("tglEndEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') ");
        }
        if (!input.getSearch().get("tglEndEffective").toString().equalsIgnoreCase("")) {
//            sql.append("AND DENDEFF = '").append(input.getSearch().get("tglEndEffective").toString().toUpperCase()).append("' ");
              sql.append("AND A.DENDEFF BETWEEN TO_DATE('").append(input.getSearch().get("tglStartEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') AND TO_DATE('").append(input.getSearch().get("tglEndEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') ");
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
                Vms031VoMonitoring vo = new Vms031VoMonitoring();
                vo.setNik((String) obj[0]);
                vo.setNrp((String) obj[1]);
                vo.setNama((String) obj[2]);
                vo.setJenisKelamin((String) obj[3]);
                vo.setAlamatKtp((String) obj[4]);
                vo.setAlamatDomisili((String) obj[5]);
                vo.setTipeKaryawanBlacklist((String) obj[6]);
                vo.setNamaPerusahaan((String) obj[7]);
                vo.setAlasanBlacklist((String) obj[8]);
                vo.setTypeFoto((String) obj[9]);
                vo.setNamaFoto((String) obj[10]);
                vo.setExtensionFoto((String) obj[11]);
                vo.setPathFoto((String) obj[12]);
                vo.setTglStartEffective((Date) obj[13]);
                vo.setTglStartEffectiveText((String) DateUtil.dateToString((Date) obj[13], "dd-MMM-yyyy"));
                vo.setTglEndEffective((Date) obj[14]);
                vo.setTglEndEffectiveText((String) DateUtil.dateToString((Date) obj[14], "dd-MMM-yyyy"));

                vo.setCreateDate((Date) obj[15]);
                vo.setCreateDateText((String) DateUtil.dateToString((Date) obj[15], "dd-MMM-yyyy"));
                vo.setCreateBy((String) obj[16]);
                
                vo.setJenisKartuIdentitas((String) obj[17]);
                vo.setNoIdentitas((String) obj[18]);
                vo.setStatus((String) obj[19]);
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
            StringBuilder sql = new StringBuilder(" SELECT COUNT (0) FROM ( "
                + "SELECT DISTINCT A.VNIK, A.VNRPPIC, A.VNAME, "
                + "A.VGENDER, A.VADRESKTP, A.VADRESDOM, A.VISEMP, "
                + "A.VCOMPANY, A.VREASON, A.VFTYPEFOTO, A.VFNAMEFOTO, "
                + "A.VFEXTFOTO, A.VFPATHFOTO, A.DSTARTEFF, A.DENDEFF, A.DCREA, A.VCREA, A.DMODI, A.VMODI, "
                + "B.VIDTYPE, B.VIDNO, "
                + "(CASE " 
                + "WHEN A.DENDEFF > SYSDATE - 1 THEN " 
                + "    'AKTIF' " 
                + "ELSE " 
                + "    'TIDAK AKTIF' " 
                + "END) AS STATUS "
                + "FROM AHMGAVMS_HDRBLCKLSTS A "
                + "LEFT JOIN AHMGAVMS_DTLBLCKLSTS B "
                + "ON A.NIDHDR = B.NIDHDR "
                + ")"
                + "WHERE 1 = 1");
             if (!input.getSearch().get("status").toString().equalsIgnoreCase("")) {
                if (input.getSearch().get("status").toString().equalsIgnoreCase("A")) {
                    sql.append("AND A.DENDEFF > SYSDATE - 1 ");
                } else {
                sql.append("AND A.DENDEFF <  SYSDATE ");
            }
            }

            if (!input.getSearch().get("nik").toString().equalsIgnoreCase("")) {
                sql.append("AND A.VNIK LIKE '%").append(input.getSearch().get("nik").toString().toUpperCase()).append("' ");
            }
            if (!input.getSearch().get("nama").toString().equalsIgnoreCase("")) {
                sql.append("AND A.VNAME LIKE '%").append(input.getSearch().get("nama").toString().toUpperCase()).append("' ");
            }

            if (!input.getSearch().get("jenisKartuIdentitas").toString().equalsIgnoreCase("")) {
                sql.append("AND B.VIDTYPE = '").append(input.getSearch().get("jenisKartuIdentitas").toString().toUpperCase()).append("' ");
            }
            if (!input.getSearch().get("noIdentitas").toString().equalsIgnoreCase("")) {
                sql.append("AND B.VIDNO LIKE '%").append(input.getSearch().get("noIdentitas").toString().toUpperCase()).append("' ");
            }

            if (!input.getSearch().get("tipeKaryawanBlacklist").toString().equalsIgnoreCase("")) {
                sql.append("AND A.VISEMP = '").append(input.getSearch().get("tipeKaryawanBlacklist").toString().toUpperCase()).append("' ");
            }
            
            if (!input.getSearch().get("nrp").toString().equalsIgnoreCase("")) {
            sql.append("AND A.VNRPPIC = '").append(input.getSearch().get("nrp").toString().toUpperCase()).append("' ");
            }
            
            if (!input.getSearch().get("tglStartEffective").toString().equalsIgnoreCase("")) {

              sql.append("AND A.DSTARTEFF BETWEEN TO_DATE('").append(input.getSearch().get("tglStartEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') AND TO_DATE('").append(input.getSearch().get("tglEndEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') ");
            }
            if (!input.getSearch().get("tglEndEffective").toString().equalsIgnoreCase("")) {

              sql.append("AND A.DENDEFF BETWEEN TO_DATE('").append(input.getSearch().get("tglStartEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') AND TO_DATE('").append(input.getSearch().get("tglEndEffective").toString().toUpperCase())
                        .append("', 'DD-MM-YYYY') ");
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

    @Override
    public List<Vms031VoMonitoring> getDataExportExcel(Map<String, Object> param) {
         Vms031VoMonitoring filter = Vms031QueryUtil.setFilter(param);
        String sqlQuery = Vms031QueryUtil.setParamQueryString(Vms031Constant.SQL_MONITORING_BLACKLIST, filter);
        DtoParamPaging dtoParam = new DtoParamPaging();
        dtoParam.setOrder(param.get("order").toString());
        dtoParam.setSort(param.get("sort").toString());
        sqlQuery = sqlQuery.concat(orderClause(dtoParam, sqlQuery, Vms031Constant.FIELD_SHOW_DATA, Vms031Constant.DEFAULT_SORT));
        Query q = getCurrentSession().createSQLQuery(sqlQuery);
        q = Vms031QueryUtil.setParamQuery(q, filter);
        q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> list = q.list();
        List<Vms031VoMonitoring> volist = Vms031QueryUtil.mappingResponseMonitoringStockSh(list);
        return volist;
    }
    
    private String orderClause(DtoParamPaging input, String query,
        Map<String, String> clause, String addorder) {
        StringBuilder sql = new StringBuilder();
        if (input.getSort() != null && !StringUtils.isEmpty(input.getSort())) {
            sql.append(" ORDER BY ");
            if (input.getOrder() != null && !StringUtils.isEmpty(input.getOrder())) {
                if (input.getOrder().equalsIgnoreCase(CommonConstant.DESC)) {
                    sql.append(clause.get((input.getSort().toLowerCase())));
                    if (addorder != null) {
                        sql.append(" DESC, " + addorder);
                    } else {
                        sql.append(" DESC");
                    }
                } else {
                    sql.append(clause.get(input.getSort().toLowerCase()));
                    if (addorder != null) {
                        sql.append(" ASC, " + addorder);
                    } else {
                        sql.append(" ASC");
                    }
                }
            } else {
                sql.append(clause.get(input.getSort()));
                if (addorder != null) {
                    sql.append(" ASC, " + addorder);
                } else {
                    sql.append(" ASC");
                }
            }
        } else if (addorder != null) {
            sql = sql.append(" ORDER BY ").append(addorder);
        }
        return sql.toString();
    }

    @Override
    public BigDecimal getHeaderId(DtoParamPaging input) {
         try {
        StringBuilder sql = new StringBuilder(NID_HEADER);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("NIDHDR", input.getSearch().get("headerId").toString());
        
        return (BigDecimal) sqlQuery.uniqueResult();
        }catch(Exception e){
            return BigDecimal.ZERO;
        }
       
    }

    private void voSetter(DtoParamPaging input){
        try {
            if (input.getSort() == null) {
        
            } else {
                String param = input.getSort();
                
                switch (param){
                    case "headerId":
                        getParam = "A.NIDHDR";
                        break;
                    case "status":
                        getParam = "VSTATUS";
                        break;
                    case "nama":
                        getParam = "A.VNAME";
                        break;
                    case "jenisKelamin":
                        getParam = "A.VGENDER";
                        break;
                    case "alamatKtp":
                        getParam = "A.VADRESKTP";
                        break;
                    case "alamatDomisili":
                        getParam = "A.VADRESDOM";
                        break;
                    case "tipeKaryawanBlacklist":
                        getParam = "A.VISEMP";
                        break;
                    case "namaPerusahaan":
                        getParam = "A.VCOMPANY";
                        break;
                    case "alasanBlacklist":
                        getParam = "A.VREASON";
                        break;
                    case "typeFoto":
                        getParam = "A.VFTYPEFOTO";
                        break;
                    case "namaFoto":
                        getParam = "A.VFNAMEFOTO";
                        break;
                    case "extensionFoto":
                        getParam = "A.VFEXTFOTO";
                        break;
                    case "pathFoto":
                        getParam = "A.VFPATHFOTO";
                        break;
                    case "tglStartEffective":
                        getParam = "A.DSTARTEFF";
                        break;
                    case "tglEndEffective":
                        getParam = "A.DENDEFF";
                        break;
                    case "detailId":
                        getParam = "B.NIDDTL";
                        break;
                    case "jenisKartuIdentitas":
                        getParam = "VIDTYPE";
                        break;
                    case "noIdentias":
                        getParam = "VIDNO";
                        break;
                        default:
                            getParam = null;
                     }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
