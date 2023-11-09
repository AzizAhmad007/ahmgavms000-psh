/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsMstvisitors;
import id.co.ahm.ga.vms.app000.model.AhmgavmsTxninouts;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmgavmsMstvisitorsDao;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmgavmsTxninoutsDao;
import id.co.ahm.ga.vms.app034.dao.Vms034AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app034.service.Vms034Service;
import id.co.ahm.ga.vms.app034.vo.Vms034VoLovOutput;
import id.co.ahm.ga.vms.app034.vo.Vms034VoMonitoringOutput;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    
    @Autowired
    @Qualifier("vms034AhmgavmsMstvisitorsDao")
    private Vms034AhmgavmsMstvisitorsDao vms034AhmgavmsMstvisitorsDao;

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
    public DtoResponseWorkspace submitCheckIn(DtoParamPaging input, VoUserCred user) {
        try {
            String nama = AhmStringUtil.hasValue(input.getSearch().get("name")) ? (input.getSearch().get("name") + "").toUpperCase() : "";
            String nik = AhmStringUtil.hasValue(input.getSearch().get("nik")) ? (input.getSearch().get("nik") + "").toUpperCase() : "";
            String noHp = AhmStringUtil.hasValue(input.getSearch().get("noHp")) ? (input.getSearch().get("noHp") + "").toUpperCase() : "";
            String email = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "").toUpperCase() : "";
            String nopol = AhmStringUtil.hasValue(input.getSearch().get("nopol")) ? (input.getSearch().get("nopol") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
            String plantId = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String loc = AhmStringUtil.hasValue(input.getSearch().get("loc")) ? (input.getSearch().get("loc") + "").toUpperCase() : "";
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
            String flagUpdate = AhmStringUtil.hasValue(input.getSearch().get("flagUpdate")) ? (input.getSearch().get("flagUpdate") + "").toUpperCase() : "";
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            String isCheckIn = vms034AhmgavmsTxninoutsDao.isCheckIn(nik);
            if (isCheckIn.equalsIgnoreCase("1")) {
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("NIK " + nik + " masih berstatus Check - In!"), null, null);
            } else {
                AhmgavmsTxninouts txn = new AhmgavmsTxninouts();
                txn.setVinvitno(invitNo);
                txn.setVnik(nik);
                txn.setVnohp(noHp);
                txn.setVemail(email);
                txn.setVnopol(nopol);
                txn.setVplantid(plantId);
                txn.setDstartdate(new Date());
                txn.setDcheckin(new Date());
                txn.setVidleft(idLeftType);
                txn.setVidleftno(idLeftNo);
                txn.setVpasscd(rfidPassCard);
                txn.setVbuilding(loc);
                txn.setVlocplant(locSpec);
                txn.setVreason(reason);
                txn.setVnotes(note);
                txn.setVpregflag(pregFlag);
                txn.setVstatus("I");
                txn.setCreateBy(userId);
                txn.setCreateDate(new Date());
                txn.setLastModBy(userId);
                txn.setLastModDate(new Date());
                vms034AhmgavmsTxninoutsDao.save(txn);
                vms034AhmgavmsTxninoutsDao.flush();

                Map<String, Object> isVisitorExist = vms034AhmgavmsMstvisitorsDao.checkVisitorExist(nik);
                String existsFlag = (String) isVisitorExist.get("exists");
                if (existsFlag.equalsIgnoreCase("0")) {
                    AhmgavmsMstvisitors vst = new AhmgavmsMstvisitors();
                    vst.setVnik(nik);
                    vst.setVname(nama);
                    vst.setVnohp(noHp);
                    vst.setVemail(email);
                    vst.setVcompany(company);
                    vst.setVpregflag(pregFlag);
                    vst.setCreateBy(userId);
                    vst.setCreateDate(new Date());
                    vst.setLastModBy(userId);
                    vst.setLastModDate(new Date());
                    vms034AhmgavmsMstvisitorsDao.save(vst);
                    vms034AhmgavmsMstvisitorsDao.flush();
                } else {
                    if (flagUpdate.equalsIgnoreCase("1")) {
                        AhmgavmsMstvisitors vst = new AhmgavmsMstvisitors();
                        BigDecimal nid = (BigDecimal) isVisitorExist.get("nid");
                        vst = vms034AhmgavmsMstvisitorsDao.findOne(nid);
                        vst.setVname(nama);
                        vst.setVnohp(noHp);
                        vst.setVemail(email);
                        vst.setVcompany(company);
                        vst.setVpregflag(pregFlag);
                        vst.setLastModBy(userId);
                        vst.setLastModDate(new Date());
                        vms034AhmgavmsMstvisitorsDao.update(vst);
                        vms034AhmgavmsMstvisitorsDao.flush();
                    }
                }
            }
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

    @Override
    public DtoResponseWorkspace submitCheckOut(DtoParamPaging input, VoUserCred user) {
        String nidStr = AhmStringUtil.hasValue(input.getSearch().get("nid")) ? (input.getSearch().get("nid") + "").toUpperCase() : "";
        String flagPassCard = AhmStringUtil.hasValue(input.getSearch().get("flagPassCard")) ? (input.getSearch().get("flagPassCard") + "").toUpperCase() : "";
        String userId;
        if (user == null) {
            userId = "DEVELOPER";
        } else {
            userId = user.getUserid();
        }
        BigDecimal nid = new BigDecimal(nidStr);

        try {
            AhmgavmsTxninouts txn = new AhmgavmsTxninouts();
            txn = vms034AhmgavmsTxninoutsDao.findOne(nid);
            txn.setVstatus(flagPassCard);
            txn.setDcheckout(new Date());
            txn.setLastModBy(userId);
            txn.setLastModDate(new Date());
            vms034AhmgavmsTxninoutsDao.update(txn);
            vms034AhmgavmsTxninoutsDao.flush();
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input) {
        try {
            List<Vms034VoMonitoringOutput> data = vms034AhmgavmsTxninoutsDao.getMonitoring(input);
            int countData = vms034AhmgavmsTxninoutsDao.getMonitoringCount(input);
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, data, countData);
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, "GAGAL", null, null, 0);
        }

    }
}
