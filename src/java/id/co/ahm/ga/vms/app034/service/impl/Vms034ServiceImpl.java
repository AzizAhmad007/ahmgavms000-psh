/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.service.impl;

import id.co.ahm.ga.vms.app034.dao.Vms034AhmgavmsTxninoutsDao;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app034.service.Vms034Service;
import id.co.ahm.ga.vms.app034.vo.Vms034VoLovOutput;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DtoHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kahfi
 */
@Transactional(readOnly = true)
@Service(value = "vms034Service")
public class Vms034ServiceImpl implements Vms034Service{
    
    @Autowired
    @Qualifier("vms034AhmgavmsTxninoutsDao")
    private Vms034AhmgavmsTxninoutsDao vms034AhmgavmsTxninoutsDao;
    
    @Autowired
    @Qualifier("vms034AhmmoerpDtlsettingsDao")
    private Vms034AhmmoerpDtlsettingsDao vms034AhmmoerpDtlsettingsDao;

    @Override
    public DtoResponseWorkspace showVisitorType(DtoParamPaging input) {
        try {
            List<Vms034VoLovOutput> data = vms034AhmmoerpDtlsettingsDao.lovVisitorType(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showPlant(DtoParamPaging input) {
        try {
            List<Vms034VoLovOutput> data = vms034AhmmoerpDtlsettingsDao.lovPlant(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showLocation(DtoParamPaging input) {
        try {
            List<Vms034VoLovOutput> data = vms034AhmmoerpDtlsettingsDao.lovLocation(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showIdCardType(DtoParamPaging input) {
        try {
            List<Vms034VoLovOutput> data = vms034AhmmoerpDtlsettingsDao.lovIdCardTypeAll(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showIdCardTypeFilter(DtoParamPaging input) {
        try {
            List<Vms034VoLovOutput> data = vms034AhmmoerpDtlsettingsDao.lovIdCardTypeFilter(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace submitCheckIn(DtoParamPaging input) {
        try {
            String nama = AhmStringUtil.hasValue(input.getSearch().get("name")) ? (input.getSearch().get("name") + "").toUpperCase() : "";
            String nik = AhmStringUtil.hasValue(input.getSearch().get("nik")) ? (input.getSearch().get("nik") + "").toUpperCase() : "";
            String noHp = AhmStringUtil.hasValue(input.getSearch().get("noHp")) ? (input.getSearch().get("noHp") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String locSpec = AhmStringUtil.hasValue(input.getSearch().get("locSpec")) ? (input.getSearch().get("locSpec") + "").toUpperCase() : "";
            String reason = AhmStringUtil.hasValue(input.getSearch().get("reason")) ? (input.getSearch().get("reason") + "").toUpperCase() : "";
            String picAhm = AhmStringUtil.hasValue(input.getSearch().get("picAhm")) ? (input.getSearch().get("picAhm") + "").toUpperCase() : "";
            String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
            String invitNo = AhmStringUtil.hasValue(input.getSearch().get("invitNo")) ? (input.getSearch().get("invitNo") + "").toUpperCase() : "";
            String note = AhmStringUtil.hasValue(input.getSearch().get("note")) ? (input.getSearch().get("note") + "").toUpperCase() : "";
            String pregFlag = AhmStringUtil.hasValue(input.getSearch().get("pregFlag")) ? (input.getSearch().get("pregFlag") + "").toUpperCase() : "";
            String idLeftType = AhmStringUtil.hasValue(input.getSearch().get("idLeftType")) ? (input.getSearch().get("idLeftType") + "").toUpperCase() : "";
            String idLeftNo = AhmStringUtil.hasValue(input.getSearch().get("idLeftNo")) ? (input.getSearch().get("idLeftNo") + "").toUpperCase() : "";
            String rfidVisitor = AhmStringUtil.hasValue(input.getSearch().get("rfidVisitor")) ? (input.getSearch().get("rfidVisitor") + "").toUpperCase() : "";
            String rfidPassCard = AhmStringUtil.hasValue(input.getSearch().get("rfidPassCard")) ? (input.getSearch().get("rfidPassCard") + "").toUpperCase() : "";
            
            
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showStatusCheck(DtoParamPaging input) {
        try {
            List<Vms034VoLovOutput> data = vms034AhmmoerpDtlsettingsDao.lovStatusCheck(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
}
