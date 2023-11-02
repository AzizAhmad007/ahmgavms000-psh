/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app028.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlregsis;
import id.co.ahm.ga.vms.app000.model.AhmgavmsDtlregsisPk;
import id.co.ahm.ga.vms.app028.dao.Vms028AhmgavmsDtlregsisDao;
import id.co.ahm.ga.vms.app028.dao.Vms028AhmgavmsHdrregsisDao;
import id.co.ahm.ga.vms.app028.service.Vms028Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.co.ahm.ga.vms.app028.dao.Vms028AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app028.vo.Vms028VoLovOutput;
import id.co.ahm.ga.vms.app028.vo.Vms028VoMonitoringOutput;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kahfi
 */
@Transactional(readOnly = true)
@Service(value = "vms028Service")
public class Vms028ServiceImpl implements Vms028Service{
    
    @Autowired
    @Qualifier("vms028AhmgavmsHdrregsisDao")
    private Vms028AhmgavmsHdrregsisDao vms028AhmgavmsHdrregsisDao;
    
    @Autowired
    @Qualifier("vms028AhmgavmsDtlregsisDao")
    private Vms028AhmgavmsDtlregsisDao vms028AhmgavmsDtlregsisDao;
    
    @Autowired
    @Qualifier("vms028AhmmoerpDtlsettingsDao")
    private Vms028AhmmoerpDtlsettingsDao vms028AhmmoerpDtlsettingsDao;

    @Override
    public DtoResponseWorkspace showPlant(DtoParamPaging input) {
        try {
            List<Vms028VoLovOutput> data = vms028AhmmoerpDtlsettingsDao.lovPlant(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showParticipant(DtoParamPaging input) {
        try {
            List<Vms028VoLovOutput> data = vms028AhmmoerpDtlsettingsDao.lovParticipant(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showResult(DtoParamPaging input) {
        try {
            List<Vms028VoLovOutput> data = vms028AhmmoerpDtlsettingsDao.lovResult(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showTypeSI(DtoParamPaging input) {
        try {
            List<Vms028VoLovOutput> data = vms028AhmmoerpDtlsettingsDao.lovTypeSI(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
    @Override
    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input) {
        try {
            List<Vms028VoMonitoringOutput> data = vms028AhmgavmsHdrregsisDao.getMonitoring(input);
            int countData = vms028AhmgavmsHdrregsisDao.getMonitoringCount(input);
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, data, countData);
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, "GAGAL", null, null, 0);
        }
    }

    @Override
    public DtoResponseWorkspace updateResult(DtoParamPaging input, VoUserCred user) {
        try {
            String reqNo = AhmStringUtil.hasValue(input.getSearch().get("reqNo")) ? (input.getSearch().get("reqNo") + "").toUpperCase() : "";
            String kategori = AhmStringUtil.hasValue(input.getSearch().get("kategori")) ? (input.getSearch().get("kategori") + "").toUpperCase() : "";
            String hasil = AhmStringUtil.hasValue(input.getSearch().get("hasil")) ? (input.getSearch().get("hasil") + "").toUpperCase() : "";
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            AhmgavmsDtlregsisPk dtlPk = new AhmgavmsDtlregsisPk();
            dtlPk.setVnoreqsi(reqNo);
            dtlPk.setVtype(kategori);
            AhmgavmsDtlregsis dtl = new AhmgavmsDtlregsis();
            dtl = vms028AhmgavmsDtlregsisDao.findOne(dtlPk);
            if (dtl == null) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Data tidak ditemukan");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, msg, null);
            } else {
                dtl.setVresult(hasil);
                dtl.setLastModBy(userId);
                dtl.setLastModDate(new Date());
                vms028AhmgavmsDtlregsisDao.update(dtl);
                vms028AhmgavmsDtlregsisDao.flush();
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
            }
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
}
