package id.co.ahm.ga.vms.app022.dao.impl;

import id.co.ahm.ga.vms.app000.model.AhmgaersMstdtlwastes;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmgaersMstdtlwastesDao;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWaste;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWasteDetail;
import id.co.ahm.ga.vms.app022.vo.Vms022VoRegulation;
import id.co.ahm.jxf.dao.GenericHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.hibernate.ScrollableResults;

/**
 *
 * @author teguh
 */
@Repository("vms022AhmgaersMstdtlwastesDao")
public class Vms022AhmgaersMstdtlwastesDaoImpl extends GenericHibernateDao<AhmgaersMstdtlwastes, String> implements Vms022AhmgaersMstdtlwastesDao {

    @Override
    public Vms022VoWasteDetail getDataById(String wasteId, Integer seq) {
        Criteria crit = getCurrentSession().createCriteria(AhmgaersMstdtlwastes.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        crit.add(Restrictions.eq("nseqdtlwst", seq));
        crit.add(Restrictions.eq("dinactive", Vms022Constant.DEFAULT_MAX_DATE));
        AhmgaersMstdtlwastes item = (AhmgaersMstdtlwastes) crit.uniqueResult();

        if (item != null) {
            Vms022VoWasteDetail vo = new Vms022VoWasteDetail();
            populateDetailWaste(vo, item);
            return vo;
        } else {
            return null;
        }
    }

    @Override
    public AhmgaersMstdtlwastes findOne(String wasteId, Date inactiveDate, Integer seq) {
        Criteria crit = getCurrentSession().createCriteria(AhmgaersMstdtlwastes.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        crit.add(Restrictions.eq("nseqdtlwst", seq));
        crit.add(Restrictions.eq("dinactive", inactiveDate));
        return (AhmgaersMstdtlwastes) crit.uniqueResult();
    }

    @Override
    public List<AhmgaersMstdtlwastes> find(String wasteId) {
        Criteria crit = getCurrentSession().createCriteria(AhmgaersMstdtlwastes.class);
        crit.add(Restrictions.eq("vwasteid", wasteId));
        crit.add(Restrictions.eq("vstatus", Vms022Constant.ACTIVE));
        return crit.list();
    }

    @Override
    public Integer getDetailSequence(String wasteId) {
        String hql = "select max(det.nseqdtlwst) from AhmgaersMstdtlwastes det "
                + "where det.vwasteid = :wasteId  and  det.dinactive = :dinactive";
        Query q = getCurrentSession().createQuery(hql);
        q.setParameter("wasteId", wasteId);
        q.setParameter("dinactive", Vms022Constant.DEFAULT_MAX_DATE);

        Object resultCount = q.uniqueResult();
        return (Integer) resultCount;
    }

    private void populateDetailWaste(Vms022VoWasteDetail vo, AhmgaersMstdtlwastes item) {
        vo.setRegulationCode(item.getVrglcode());
        vo.setRegulationCodeDesc(item.getVrglcodedsc());
        vo.setPlantId(item.getVplantid());
        vo.setTpsId(item.getVtpsid());
        vo.setWahoSloc(item.getVwahoslocid());
        vo.setStoreCapacity(item.getNstorcap());
        vo.setWasteId(item.getVwasteid());
        vo.setWasteDesc(item.getMasterWaste().getVwastedesc());
        vo.setSeq(item.getNseqdtlwst());
        vo.setIsAdded(false);
    }

    @Override
    public List<Vms022VoWasteDetail> search(DtoParamPaging dtoParamPaging) {

        Criteria c = getCriteria(dtoParamPaging);
        c.addOrder(Order.asc("nseqdtlwst"));
        List<AhmgaersMstdtlwastes> data = c.list();
        List<Vms022VoWasteDetail> dataVo = new ArrayList<>();
        Vms022VoWasteDetail vo;
        for (AhmgaersMstdtlwastes item : data) {
            vo = new Vms022VoWasteDetail();
            populateDetailWaste(vo, item);
            dataVo.add(vo);
        }

        return dataVo;
    }

    @Override
    public Criteria getCriteria(DtoParamPaging param) {
        Criteria c = getCurrentSession().createCriteria(AhmgaersMstdtlwastes.class);

        if (param.getSearch() != null) {
            String wasteId = (String) param.getSearch().get("wasteId");
            if (StringUtils.isNotBlank(wasteId)) {
                c.add(Restrictions.eq("vwasteid", wasteId).ignoreCase());
            }

            c.add(Restrictions.eq("vstatus", Vms022Constant.ACTIVE));
            c.add(Restrictions.eq("dinactive", Vms022Constant.DEFAULT_MAX_DATE));
        }

        return c;
    }

    @Override
    public List<Vms022VoWasteDetail> viewDetail(DtoParamPaging dtoParamPaging) {
        StringBuilder hql = new StringBuilder();
        String statusCode = "";
        if (dtoParamPaging.getSearch() != null) {
            statusCode = (String) dtoParamPaging.getSearch().get("status");
            if (statusCode.equals("Active")) {
                hql = new StringBuilder("select det from AhmgaersMstdtlwastes det where 1=1 ");
            } else {
                hql = new StringBuilder("select det from AhmgaersMstdtlwastes det, "
                        + " AhmgaersMstwasthis ret,AhmgaersMstwastes bet where det.vwasteid = ret.ahmgaersMstwasthisPK.vwasteid "
                        + " and det.dinactive = ret.ahmgaersMstwasthisPK.dinactive "
                        + " and bet.dactive = ret.dactive ");
            }
        }
        String wasteId = "";
        String status = "";
        if (dtoParamPaging.getSearch() != null) {
            wasteId = (String) dtoParamPaging.getSearch().get("wasteId");
            status = (String) dtoParamPaging.getSearch().get("status");
            if (StringUtils.isNotBlank(wasteId)) {
                hql.append(" and det.vwasteid = :wasteid ");
            }
            if (StringUtils.isNotBlank(status) && statusCode.equals("Active")) {
                hql.append(" and det.vstatus = :status ");
            }
        }
        hql.append(" order by det.vrglcode, det.vplantid, det.vtpsid, det.vwahoslocid");
        Query q = getCurrentSession().createQuery(hql.toString());
        q.setParameter("wasteid", wasteId);
        if (StringUtils.isNotBlank(status) && statusCode.equals("Active")) {
            q.setParameter("status", status);
        }
        

        List<AhmgaersMstdtlwastes> data = q.list();
        List<Vms022VoWasteDetail> dataVo = new ArrayList<>();
        Vms022VoWasteDetail vo;
        for (AhmgaersMstdtlwastes item : data) {
            vo = new Vms022VoWasteDetail();
            populateDetailWaste(vo, item);
            dataVo.add(vo);
        }

        return dataVo;
    }

    @Override
    public Integer countViewDetail(DtoParamPaging dtoParamPaging) {
        StringBuilder hql = new StringBuilder();
        String statusCode = "";
        if (dtoParamPaging.getSearch() != null) {
            statusCode = (String) dtoParamPaging.getSearch().get("status");
            if (statusCode.equals("Active")) {
                hql = new StringBuilder("select count(*) from AhmgaersMstdtlwastes det where 1=1 ");
            } else {
                hql = new StringBuilder("select count(*) from AhmgaersMstdtlwastes det, "
                        + " AhmgaersMstwasthis ret,AhmgaersMstwastes bet where det.vwasteid = ret.ahmgaersMstwasthisPK.vwasteid "
                        + " and det.dinactive = ret.ahmgaersMstwasthisPK.dinactive "
                        + " and bet.dactive = ret.dactive ");
            }
        }
        String wasteId = "";
        String status = "";
        if (dtoParamPaging.getSearch() != null) {
            wasteId = (String) dtoParamPaging.getSearch().get("wasteId");
            status = (String) dtoParamPaging.getSearch().get("status");
            if (StringUtils.isNotBlank(wasteId)) {
                hql.append(" and det.vwasteid = :wasteid ");
            }
            if (StringUtils.isNotBlank(status) && statusCode.equals("Active")) {
                hql.append(" and det.vstatus = :status ");
            }
        }

        Query q = getCurrentSession().createQuery(hql.toString());
        q.setParameter("wasteid", wasteId);
        if (StringUtils.isNotBlank(status) && statusCode.equals("Active")) {
            q.setParameter("status", status);
        }

        q.setFirstResult(0);
        q.setMaxResults(0);
        q.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Long resultCount = (Long) q.uniqueResult();
        Integer total = Integer.parseInt(Long.toString(resultCount));
        return total;
    }

    @Override
    public Vms022VoRegulation getRegulationsWaste(String wasteId) {
        String sql = "SELECT VWASTEID, LISTAGG(coalesce(VRGLCODE,''),',') WITHIN GROUP (ORDER BY VWASTEID, nseqdtlwst) VRGLCODE"
                + ", LISTAGG(COALESCE(VRGLCODEDSC, ''), ',') WITHIN GROUP (ORDER BY VWASTEID, nseqdtlwst) VRGCODEDSC "
                + " FROM AHMGAERS_MSTDTLWASTES "
                + " WHERE vwasteid = :wasteId  "
                + "   and VSTATUS = 'Active' and dinactive >= CURRENT_DATE "
                + " GROUP BY VWASTEID";
        Query q = getCurrentSession().createSQLQuery(sql);
        q.setParameter("wasteId", wasteId);
        Vms022VoRegulation w = null;
        ScrollableResults r = q.scroll(ScrollMode.FORWARD_ONLY);
        while (r.next() && w == null) {
            Object[] o = r.get();
            if (o == null || o.length < 3) {
                break;
            }
            w = new Vms022VoRegulation();
            w.setWasteId((String) o[0]);
            w.setRegulationCode((String) o[1]);
            w.setRegulationCodeDesc((String) o[2]);
        }
        r.close();
        return w;
    }

}
