/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.dao.impl;

import id.co.ahm.ga.vms.app032.constant.Vms032Constant;
import id.co.ahm.ga.vms.app032.dao.Vms032AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app032.vo.Vms032VoLov;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Hitoshi Mario Naga M
 */
@Repository("vms032AhmmoerpDtlsettingsDao")
public class Vms032AhmmoerpDtlSettingsDaoImpl extends DefaultHibernateDao<AhmmoerpDtlsettings, AhmmoerpDtlsettingsPk> 
        implements Vms032AhmmoerpDtlsettingsDao{

    @Override
    public List<Vms032VoLov> lovStatusType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(Vms032Constant.LOV_STATUS_DECLARATION);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms032VoLov> vos = new ArrayList<Vms032VoLov>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms032VoLov vo = new Vms032VoLov();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public List<Vms032VoLov> lovDecType(DtoParamPaging input) {
        StringBuilder sql = new StringBuilder(Vms032Constant.LOV_TYPE_DECLARATION);
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = sqlQuery.list();
        List<Vms032VoLov> vos = new ArrayList<Vms032VoLov>();
        if (list.size() > 0) {
            Object[] obj;
            for(Object object : list) {
                obj = (Object[]) object;
                Vms032VoLov vo = new Vms032VoLov();
                vo.setItemCode((String) obj[0]);
                vo.setItemName((String) obj[1]);
                
                vos.add(vo);
            }
        }
        return vos;
    }

}
