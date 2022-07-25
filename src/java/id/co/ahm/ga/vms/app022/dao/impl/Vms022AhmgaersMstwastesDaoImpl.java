package id.co.ahm.ga.vms.app022.dao.impl;

import edu.emory.mathcs.backport.java.util.Collections;
import id.co.ahm.ga.vms.app000.model.AhmgaersMstcharwastes;
import id.co.ahm.ga.vms.app000.model.AhmgaersMstdtlwastes;
import id.co.ahm.ga.vms.app000.model.AhmgaersMstwastes;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgaersMstwastesDao;
import id.co.ahm.ga.vms.app022.util.Vms022IntegerUtil;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWaste;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dao.GenericHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author developer
 */
@Repository("vms022AhmgaersMstwastesDao")
public class Vms022AhmgaersMstwastesDaoImpl extends GenericHibernateDao<AhmgaersMstwastes, String> implements Vms022AhmgaersMstwastesDao {

    @Override
    public Integer getDataRunningNumber(String id, String reset) {
        Integer result = 0;

        String sql = "SELECT "
                + "VRESET, "
                + "CAST(IVALUE AS VARCHAR2(10)) IVALUE "
                + "FROM "
                + "AHMMOERP_TXNRUNNUMS "
                + "WHERE "
                + "VIDNAME = :VIDNAME "
                + "AND VRESET = :VRESET ";

        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("VIDNAME", id);
        query.setParameter("VRESET", reset);

        Object[] obj = (Object[]) query.uniqueResult();

        if (obj != null) {
            int col = 0;

            reset = ((String) obj[col++]);
            result = (Vms022IntegerUtil.TryParse(obj[col++] + ""));
        }

        return result;
    }

    @Override
    public Vms022VoWaste getDataById(String wasteId) {

        AhmgaersMstwastes item = findOne(wasteId);

        if (item != null) {
            Vms022VoWaste vo = new Vms022VoWaste();
            populateMasterWaste(vo, item);

            return vo;
        } else {
            return null;
        }
    }

    private Map<String, String> getSortMap() {
        String[] a = ("wasteId vwasteid wasteDesc vwastedesc wasteTypeDesc vwastetype b3NonB3 vb3nonb3 wasteValueDesc vwastevl transactionTypeDesc vtranstyp "
                + "entryNoteDesc ventrynt entryWeightDesc ventrywght storageLocationDesc vstorageloc statusDesc vstatus modifiedDate dmod modifiedUser vmod ").split(" ");
        Map<String, String> r = new HashMap<>();
        for (int i = 0; i < a.length / 2; i++) {
            r.put(a[i * 2], a[i * 2 + 1]);
        }
        return r;
    }

    @Override
    public List<Vms022VoWaste> search(DtoParamPaging dtoParamPaging) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> bindparams = new HashMap<>();
        populateQuerySearch(query, bindparams, dtoParamPaging);

        Map<String, String> sortMap = getSortMap();
        decorateSort(dtoParamPaging, query, sortMap);

        SQLQuery result = getCurrentSession().createSQLQuery(query.toString());

        bindparams.entrySet().forEach((bindparam) -> {
            result.setParameter(bindparam.getKey(), bindparam.getValue());
        });

        result.setFirstResult(dtoParamPaging.getOffset());
        result.setMaxResults(dtoParamPaging.getLimit());
        result.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        List<Map<String, Object>> resultList = result.list();

        List<Vms022VoWaste> voList = resultList.stream().map((row) -> {
            Vms022VoWaste ers005VoWaste = new Vms022VoWaste();
            ers005VoWaste.setWasteId((String) row.get("VWASTEID"));
            ers005VoWaste.setWasteDesc((String) row.get("VWASTEDESC"));
            ers005VoWaste.setWasteType((String) row.get("VWASTETYPE"));
            ers005VoWaste.setWasteTypeDesc((String) row.get("VWASTETYPEDESC"));
            ers005VoWaste.setB3NonB3((String) row.get("VB3NONB3"));
            ers005VoWaste.setB3NonB3Desc((String) row.get("VB3NONB3DESC"));
            ers005VoWaste.setWasteValue((String) row.get("VWASTEVL"));
            ers005VoWaste.setWasteValueDesc((String) row.get("VWASTEVLDESC"));
            ers005VoWaste.setTransactionType((String) row.get("VTRANSTYP"));
            ers005VoWaste.setTransactionTypeDesc((String) row.get("VTRANSTYPDESC"));
            ers005VoWaste.setEntryNote((String) row.get("VENTRYNT"));
            ers005VoWaste.setEntryNoteDesc((String) row.get("VENTRYNTDESC"));
            ers005VoWaste.setEntryWeight((String) row.get("VENTRYWGHT"));
            ers005VoWaste.setEntryWeightDesc((String) row.get("VENTRYWGHTDESC"));
            ers005VoWaste.setStorageLocation((String) row.get("VSTORAGELOC"));
            ers005VoWaste.setStorageLocationDesc((String) row.get("VSTORAGELOC"));
            ers005VoWaste.setStatus((String) row.get("VSTATUS"));
            ers005VoWaste.setStatusDesc((String) row.get("VSTATUSDESC"));
            ers005VoWaste.setModifiedUser((String) row.get("VMOD"));
            ers005VoWaste.setModifiedDate((Date) row.get("DMOD"));
            ers005VoWaste.setOcNonOc((String) row.get("VOCNONOC"));
            ers005VoWaste.setFullDayDisposal((String) row.get("VFDDISPLTRX"));
            ers005VoWaste.setPackgeFlg((String) row.get("VPACKGEFLG"));
            return ers005VoWaste;
        }).collect(Collectors.toList());

        return voList;

    }

    @Override
    public int count(DtoParamPaging dtoParamPaging) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> bindparams = new HashMap<>();
        populateQuerySearch(query, bindparams, dtoParamPaging);

        String countQuery = "SELECT COUNT(1) FROM ( " + query.toString() + ")";

        SQLQuery result = getCurrentSession().createSQLQuery(countQuery.toString());

        bindparams.entrySet().forEach((bindparam) -> {
            result.setParameter(bindparam.getKey(), bindparam.getValue());
        });

        Number results = (Number) result.uniqueResult();
        if (results == null) {
            results = 0;
        }

        return results.intValue();

    }

    public void populateQuerySearch(StringBuilder query, Map<String, Object> bindparams, DtoParamPaging dtoParamPaging) {
        query.append(Vms022Constant.QSEARCH);
        Map<String, Object> filters = dtoParamPaging.getSearch();
        if (filters == null || filters.isEmpty()) return;
        
        String[] params = Vms022Constant.Qparam.split(" "); 
        for (String p : params) {
            String f = (String) filters.get(p);
            bindparams.put(p, (StringUtils.isNotBlank(f)) ? f : "");
        }
    }

    public void populateQuerySearchPrint(StringBuilder query, Map<String, Object> bindparams, DtoParamPaging dtoParamPaging) {
        query.append(Vms022Constant.QPRINT);
        
        Map<String, Object> filters = dtoParamPaging.getSearch();
        if (filters == null || filters.isEmpty()) return;

        String[] params = Vms022Constant.Qparam.split(" "); 
        for (String p : params) {
            String f = (String) filters.get(p);
            bindparams.put(p, (StringUtils.isNotBlank(f)) ? f : "");
        }
    }

    @Override
    public int countPrint(DtoParamPaging dtoParamPaging) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> bindparams = new HashMap<>();
        populateQuerySearchPrint(query, bindparams, dtoParamPaging);

        String countQuery = "SELECT COUNT(1) FROM ( " + query.toString() + ")";

        SQLQuery result = getCurrentSession().createSQLQuery(countQuery);

        bindparams.entrySet().forEach((bindparam) -> {
            result.setParameter(bindparam.getKey(), bindparam.getValue());
        });

        Number results = (Number) result.uniqueResult();
        if (results == null) {
            results = 0;
        }

        return results.intValue();
    }

    @Override
    public List<Vms022VoWaste> searchDetail(DtoParamPaging dtoParamPaging) {
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("wasteId", "vwasteid");
        sortMap.put("wasteDesc", "masterWaste.vwastedesc");
        sortMap.put("wasteType", "masterWaste.vwastetype");
        sortMap.put("b3NonB3", "masterWaste.vb3nonb3");

        sortMap.put("wasteValue", "masterWaste.vwastevl");
        sortMap.put("transactionType", "masterWaste.vtranstyp");
        sortMap.put("entryNote", "masterWaste.ventrynt");
        sortMap.put("entryWeight", "masterWaste.ventrywght");
        sortMap.put("storageLocation", "masterWaste.vstorageloc");
        sortMap.put("status", "masterWaste.vstatus");

        StringBuilder hql = new StringBuilder("select det FROM AhmgaersMstdtlwastes det");
        decorateHqlQueryWithSearchDetail(hql, dtoParamPaging.getSearch());
        decorateSort(dtoParamPaging, hql, sortMap);
        Query query = getCurrentSession().createQuery(hql.toString());
        decorateSqlParameter(query, dtoParamPaging.getSearch());
        query.setFirstResult(dtoParamPaging.getOffset());
        query.setMaxResults(dtoParamPaging.getLimit());
        List<AhmgaersMstdtlwastes> resultsList = query.list();
        List<Vms022VoWaste> results = new ArrayList<>();
        for (AhmgaersMstdtlwastes item : resultsList) {
            AhmgaersMstwastes ahmgaersMstwastes = item.getMasterWaste();
            Vms022VoWaste ers005VoWaste = new Vms022VoWaste();
            populateMasterWaste(ers005VoWaste, ahmgaersMstwastes);
            ers005VoWaste.setRegulationCode(item.getVrglcode());
            ers005VoWaste.setRegulationCodeDesc(item.getVrglcodedsc());
            ers005VoWaste.setPlantId(item.getVplantid());
            ers005VoWaste.setTpsId(item.getVtpsid());
            ers005VoWaste.setWahoSloc(item.getVwahoslocid());
            ers005VoWaste.setStoreCapacity(item.getNstorcap());
            results.add(ers005VoWaste);
        }
        return results;
    }

    @Override
    public List<Vms022VoWaste> searchForPrint(DtoParamPaging dtoParamPaging) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> bindparams = new HashMap<>();
        populateQuerySearchPrint(query, bindparams, dtoParamPaging);

        Map<String, String> sortMap = getSortMap();
        decorateSort(dtoParamPaging, query, sortMap);

        SQLQuery result = getCurrentSession().createSQLQuery(query.toString());

        bindparams.entrySet().forEach((bindparam) -> {
            result.setParameter(bindparam.getKey(), bindparam.getValue());
        });

        result.setFirstResult(dtoParamPaging.getOffset());
        result.setMaxResults(dtoParamPaging.getLimit());
        result.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        List<Map<String, Object>> resultList = result.list();

        List<Vms022VoWaste> voList = resultList.stream().map((row) -> {
            Vms022VoWaste ers005VoWaste = new Vms022VoWaste();
            ers005VoWaste.setWasteId((String) row.get("VWASTEID"));
            ers005VoWaste.setWasteDesc((String) row.get("VWASTEDESC"));
            ers005VoWaste.setWasteType((String) row.get("VWASTETYPE"));
            ers005VoWaste.setWasteTypeDesc((String) row.get("VWASTETYPEDESC"));
            ers005VoWaste.setB3NonB3((String) row.get("VB3NONB3"));
            ers005VoWaste.setB3NonB3Desc((String) row.get("VB3NONB3DESC"));
            ers005VoWaste.setWasteValue((String) row.get("VWASTEVL"));
            ers005VoWaste.setWasteValueDesc((String) row.get("VWASTEVLDESC"));
            ers005VoWaste.setTransactionType((String) row.get("VTRANSTYP"));
            ers005VoWaste.setTransactionTypeDesc((String) row.get("VTRANSTYPDESC"));
            ers005VoWaste.setOcNonOcDesc((String) row.get("VOCNONOC"));
            ers005VoWaste.setStorageLocation((String) row.get("VSTORAGELOC"));
            ers005VoWaste.setStorageLocationDesc((String) row.get("VSTORAGELOC"));
            ers005VoWaste.setFullDayDisposalDesc((String) row.get("VFDDISPLTRXDESC"));
            ers005VoWaste.setWhStockCountingDesc((String) row.get("VWSCREQDESC"));
            ers005VoWaste.setPackagingTypeDesc((String) row.get("VPACKAGEDESC"));
            ers005VoWaste.setEntryNoteDesc((String) row.get("VENTRYNTDESC"));
            ers005VoWaste.setEntryWeightDesc((String) row.get("VENTRYWGHTDESC"));
            ers005VoWaste.setWeightPerPackage((BigDecimal) row.get("NWGHTPPKG"));
            ers005VoWaste.setWasteOutTolerance((BigDecimal) row.get("NWASTETL"));
            ers005VoWaste.setStoreDuration((BigDecimal) row.get("NSTORDUR"));
            ers005VoWaste.setWbFlagDesc((String) row.get("VWBFLAGDESC"));
            ers005VoWaste.setUom((String) row.get("VWASTEUNIT"));
            ers005VoWaste.setScFlagDesc((String) row.get("VSCFLAGDESC"));
            ers005VoWaste.setRegulationCode((String) row.get("VRGLCODE"));
            ers005VoWaste.setRegulationCodeDesc((String) row.get("VRGLCODEDSC"));
            ers005VoWaste.setPlantId((String) row.get("VPLANTID"));
            ers005VoWaste.setTpsId((String) row.get("VTPSID"));
            ers005VoWaste.setWahoSloc((String) row.get("VWAHOSLOCID"));
            ers005VoWaste.setStoreCapacity((BigDecimal) row.get("NSTORCAP"));
            ers005VoWaste.setStatus((String) row.get("VSTATUS"));
            ers005VoWaste.setStatusDesc((String) row.get("VSTATUSDESC"));
            ers005VoWaste.setModifiedUser((String) row.get("VMOD"));
            ers005VoWaste.setModifiedDate((Date) row.get("DMOD"));
            ers005VoWaste.setInactive((Date) row.get("DINACTIVE"));
            ers005VoWaste.setCharacteristicCodes((String) row.get("LISTCHARWASTE"));
            ers005VoWaste.setCharacteristics((String) row.get("LISTCHARWASTEDESC"));
            ers005VoWaste.setPackgeFlg((String) row.get("packgeFlg"));
            ers005VoWaste.setPackgeFlgDesc((String) row.get("packgeFlgDesc"));

            return ers005VoWaste;

        }).collect(Collectors.toList());

        return voList;
    }

    @Override
    public int countDetail(DtoParamPaging dtoParamPaging) {
        StringBuilder hql = new StringBuilder("SELECT COUNT(det.vwasteid) FROM AhmgaersMstdtlwastes det ");
        decorateHqlQueryWithSearchDetail(hql, dtoParamPaging.getSearch());
        Query query = getCurrentSession().createQuery(hql.toString());
        decorateSqlParameter(query, dtoParamPaging.getSearch());
        Long result = (Long) query.uniqueResult();
        return result.intValue();
    }

    private void populateMasterWaste(Vms022VoWaste ers005VoWaste, AhmgaersMstwastes ahmgaersMstwastes) {
        ers005VoWaste.setWasteId(ahmgaersMstwastes.getVwasteid());
        ers005VoWaste.setWasteDesc(ahmgaersMstwastes.getVwastedesc());
        ers005VoWaste.setWasteType(ahmgaersMstwastes.getVwastetype());
        ers005VoWaste.setWasteTypeDesc(ahmgaersMstwastes.getWasteTypeDetail() != null ? ahmgaersMstwastes.getWasteTypeDetail().getVitemname() : null);
        ers005VoWaste.setB3NonB3(ahmgaersMstwastes.getVb3nonb3());
        ers005VoWaste.setB3NonB3Desc(ahmgaersMstwastes.getB3NonB3Detail() != null ? ahmgaersMstwastes.getB3NonB3Detail().getVitemname() : null);
        ers005VoWaste.setWasteValue(ahmgaersMstwastes.getVwastevl());
        ers005VoWaste.setWasteValueDesc(ahmgaersMstwastes.getWasteValueDetail() != null ? ahmgaersMstwastes.getWasteValueDetail().getVitemname() : null);
        ers005VoWaste.setTransactionType(ahmgaersMstwastes.getVtranstyp());
        ers005VoWaste.setTransactionTypeDesc(ahmgaersMstwastes.getTransactionTypeDetail() != null ? ahmgaersMstwastes.getTransactionTypeDetail().getVitemdesc() : null);
        ers005VoWaste.setEntryNote(ahmgaersMstwastes.getVentrynt());
        ers005VoWaste.setEntryNoteDesc(ahmgaersMstwastes.getEntryNoteDetail() != null ? ahmgaersMstwastes.getEntryNoteDetail().getVitemdesc() : null);
        ers005VoWaste.setEntryWeight(ahmgaersMstwastes.getVentrywght());
        ers005VoWaste.setEntryWeightDesc(ahmgaersMstwastes.getEntryWeightDetail() != null ? ahmgaersMstwastes.getEntryWeightDetail().getVitemname() : null);
        ers005VoWaste.setStorageLocation(ahmgaersMstwastes.getVstorageloc());
        ers005VoWaste.setStorageLocationDesc(ahmgaersMstwastes.getStorageLocationDetail() != null ? ahmgaersMstwastes.getStorageLocationDetail().getVitemname() : null);
        ers005VoWaste.setStatus(ahmgaersMstwastes.getVstatus());
        ers005VoWaste.setStatusDesc(ahmgaersMstwastes.getStatusDetail() != null ? ahmgaersMstwastes.getStatusDetail().getVitemname() : null);
        ers005VoWaste.setModifiedUser(ahmgaersMstwastes.getLastModBy() == null ? ahmgaersMstwastes.getCreatedName() : ahmgaersMstwastes.getModifiedName());
        ers005VoWaste.setModifiedDate(ahmgaersMstwastes.getLastModBy() == null ? ahmgaersMstwastes.getCreateDate() : ahmgaersMstwastes.getLastModDate());
        ers005VoWaste.setOcNonOc(ahmgaersMstwastes.getVocnonoc());
        ers005VoWaste.setOcNonOcDesc(ahmgaersMstwastes.getOcNonOcDetail() != null ? ahmgaersMstwastes.getOcNonOcDetail().getVitemname() : null);
        ers005VoWaste.setFullDayDisposal(ahmgaersMstwastes.getVfddispltrx());
        ers005VoWaste.setFullDayDisposalDesc(ahmgaersMstwastes.getFullDayDisposalDetail() != null ? ahmgaersMstwastes.getFullDayDisposalDetail().getVitemname() : null);
        ers005VoWaste.setWhStockCounting(ahmgaersMstwastes.getVwscreq());
        ers005VoWaste.setWhStockCountingDesc(ahmgaersMstwastes.getWhStockCountingDetail() != null ? ahmgaersMstwastes.getWhStockCountingDetail().getVitemname() : null);
        ers005VoWaste.setPackagingType(ahmgaersMstwastes.getVpackage());
        ers005VoWaste.setPackagingTypeDesc(ahmgaersMstwastes.getPackagingTypeDetail() != null ? ahmgaersMstwastes.getPackagingTypeDetail().getVitemname() : null);
        ers005VoWaste.setWeightPerPackage(ahmgaersMstwastes.getNwghtppkg());
        ers005VoWaste.setWasteOutTolerance(ahmgaersMstwastes.getNwastetl());
        ers005VoWaste.setStoreDuration(ahmgaersMstwastes.getNstordur());
        ers005VoWaste.setWbFlag(ahmgaersMstwastes.getVwbflag());
        ers005VoWaste.setWbFlagDesc(ahmgaersMstwastes.getWbflagDetail() != null ? ahmgaersMstwastes.getWbflagDetail().getVitemdesc() : null);
        ers005VoWaste.setScFlag(ahmgaersMstwastes.getVscflag());
        ers005VoWaste.setScFlagDesc(ahmgaersMstwastes.getScflagDetail() != null ? ahmgaersMstwastes.getScflagDetail().getVitemname() : null);
        ers005VoWaste.setUom(ahmgaersMstwastes.getVwasteunit());
        ers005VoWaste.setPackgeFlg(ahmgaersMstwastes.getVpackgeflg());
        ers005VoWaste.setPackgeFlgDesc(ahmgaersMstwastes.getVpackgeflg() != null ? ahmgaersMstwastes.getPackgeFlgDetail().getVitemname() : null);
        ers005VoWaste.setRegulationCode(ahmgaersMstwastes.getVrglcode());
        ers005VoWaste.setRegulationCodeDesc(ahmgaersMstwastes.getVrglcodedsc());
        List<AhmgaersMstcharwastes> chars = ahmgaersMstwastes.getCharacteristicsWastes();
        Collections.sort(chars, (Comparator<AhmgaersMstcharwastes>) (AhmgaersMstcharwastes c1, AhmgaersMstcharwastes c2) -> c1.getId().compareTo(c2.getId()));
        List<String> Codes = new ArrayList<>();
        List<String> Descs = new ArrayList<>();

        String[] charCodes = new String[chars.size()];
        String[] charDescs = new String[chars.size()];
        StringBuilder charsAll = new StringBuilder();
        String pref = "";
        int i = 0;
        for (AhmgaersMstcharwastes cw : chars) {
            if (Vms022Constant.DEFAULT_MAX_DATE.compareTo(cw.getDinactive()) == 0 
                || (Vms022Constant.STATUS_INACTIVE.equals(cw.getVstatus()) 
                    && Vms022Constant.INACTIVE.equals(ers005VoWaste.getStatus()))
                    ) {
                if (Codes.indexOf(cw.getVcharwaste()) < 0)
                    Codes.add(cw.getVcharwaste());
                String chardetail = (cw.getCharacteristicsDetail() != null) ? cw.getCharacteristicsDetail().getVitemdesc(): null;
                if (chardetail != null && Descs.indexOf(chardetail) < 0) 
                    Descs.add(chardetail);
            }
        }
        charCodes = new String[Codes.size()];
        charCodes = Codes.toArray(charCodes);

        charDescs = new String[Descs.size()];
        charDescs = Descs.toArray(charDescs);

        ers005VoWaste.setCharacteristicCode(charCodes);
        ers005VoWaste.setCharacteristicCodeDesc(charDescs);
        ers005VoWaste.setCharacteristics(String.join(", ", charDescs));
        ers005VoWaste.setCharacteristicCodes(String.join(",", charCodes));
    }

    private void decorateSort(DtoParamPaging dtoParamPaging, StringBuilder hql, Map<String, String> sortMap) {
        String sorting = dtoParamPaging.getSort();
        String ordering = dtoParamPaging.getOrder();
        if (!StringUtils.isEmpty(sorting) || !StringUtils.isEmpty(ordering)) {
            String omap = sortMap.get(sorting);
            if (!StringUtils.isEmpty(omap)) {
                hql.append(" ORDER BY ");
                hql.append(omap);
                hql.append((CommonConstant.DESC.equalsIgnoreCase(ordering)) ? " DESC " : " ASC ");
                return;
            }
        }
        hql.append(" ORDER BY dactive DESC ");
    }

    private void decorateHqlQueryWithSearchDetail(StringBuilder sql, Map<String, Object> filters) {
        if (filters != null && !filters.isEmpty()) {

            String id = filters.get("wasteId") != null ? filters.get("wasteId").toString() : "";
            String type = filters.get("wasteType") != null ? filters.get("wasteType").toString() : "";
            String status = filters.get("status") != null ? filters.get("status").toString() : "";
            String description = filters.get("wasteDesc") != null ? filters.get("wasteDesc").toString() : "";
            String entryNote = filters.get("entryNote") != null ? filters.get("entryNote").toString() : "";
            String entryWeight = filters.get("entryWeight") != null ? filters.get("entryWeight").toString() : "";

            sql.append(" WHERE 1=1 ");

            if (StringUtils.isNotBlank(id)) {
                sql.append("AND masterWaste.vwasteid LIKE :wasteId ");
            }
            if (StringUtils.isNotBlank(type)) {
                sql.append(" AND masterWaste.vwastetype LIKE :wasteType ");
            }
            if (StringUtils.isNotBlank(status)) {
                sql.append(" AND masterWaste.vstatus = :status) ");
            }
            if (StringUtils.isNotBlank(description)) {
                sql.append(" AND masterWaste.vwastedesc LIKE :wasteDesc ");
            }

            if (StringUtils.isNotBlank(entryNote)) {
                sql.append(" AND masterWaste.ventrynt LIKE :entryNote ");
            }

            if (StringUtils.isNotBlank(entryWeight)) {
                sql.append(" AND masterWaste.ventrywght LIKE :entryWeight ");
            }
        }
    }

    private void decorateSqlParameter(Query q, Map<String, Object> filters) {
        if (q.getNamedParameters() != null && q.getNamedParameters().length > 0) {
            for (String p : q.getNamedParameters()) {
                String val = filters.get(p).toString();
                if (!"status".equals(p)) {
                    val = "%" + val + "%";
                }
                q.setParameter(p, val);
            }
        }
    }

    @Override
    public String getWasteIdNo() {
        return "WD-0001";
    }

}
