/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app028.dao.impl;

import static id.co.ahm.ga.vms.app028.constant.Vms028Constant.*;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import org.springframework.stereotype.Repository;
import id.co.ahm.ga.vms.app028.dao.Vms028AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app028.vo.Vms028VoLovOutput;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;

/**
 *
 * @author kahfi
 */
@Repository("vms028AhmmoerpDtlsettingsDao")
public class Vms028AhmmoerpDtlsettingsDaoImpl extends DefaultHibernateDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk> implements Vms028AhmmoerpDtlsettingsDao{

    @Override
    public List<Vms028VoLovOutput> lovPlant(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_PLANTS);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms028VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms028VoLovOutput vo = new Vms028VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms028VoLovOutput> lovParticipant(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_PARTICIPANT);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms028VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms028VoLovOutput vo = new Vms028VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms028VoLovOutput> lovResult(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_RESULT);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms028VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms028VoLovOutput vo = new Vms028VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms028VoLovOutput> lovTypeSI(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(LOV_TYPE_SI);
        
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms028VoLovOutput> vos = new ArrayList<>();
        if (list.size() > 0) {
            Object[] obj;
            for (Object object : list) {
                obj = (Object[]) object;
                Vms028VoLovOutput vo = new Vms028VoLovOutput();
                vo.setCode((String) obj[0]);
                vo.setName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }
}
