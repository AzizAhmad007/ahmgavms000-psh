/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrchiefs;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHdrinvits;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app026.service.Vms026Service;
import id.co.ahm.ga.vms.app026.vo.Vms026VoLovOutput;
import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringOutput;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettingsPk;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmgavmsHdrinvitsDao;
import id.co.ahm.ga.vms.app026.dao.Vms026AhmgavmsHdrchiefsDao;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;

/**
 *
 * @author Kahfi
 */
@Transactional(readOnly = true)
@Service(value = "vms026Service")
public class Vms026ServiceImpl implements Vms026Service{

    @Autowired
    @Qualifier("vms026AhmmoerpDtlsettingsDao")
    private Vms026AhmmoerpDtlsettingsDao vms026AhmmoerpDtlsettingsDao;
    
    @Autowired
    @Qualifier("vms026AhmgavmsHdrinvitsDao")
    private Vms026AhmgavmsHdrinvitsDao vms026AhmgavmsHdrinvitsDao;
    
    @Autowired
    @Qualifier("vms026AhmgavmsHdrchiefsDao")
    private Vms026AhmgavmsHdrchiefsDao vms026AhmgavmsHdrchiefsDao;
    
    SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-YYYY");
    Date m = new Date();
    Calendar cal = Calendar.getInstance();
    
    @Override
    public DtoResponseWorkspace showVisitorType(DtoParamPaging input) {
        try {
            List<Vms026VoLovOutput> data = vms026AhmmoerpDtlsettingsDao.lovVisitorType(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
        
    }

    @Override
    public DtoResponseWorkspace showPlant(DtoParamPaging input) {
        try {
            List<Vms026VoLovOutput> data = vms026AhmmoerpDtlsettingsDao.lovPlant(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showBuilding(DtoParamPaging input) {
        try {
            List<Vms026VoLovOutput> data = vms026AhmmoerpDtlsettingsDao.lovBuilding(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showFloor(DtoParamPaging input) {
        try {
            List<Vms026VoLovOutput> data = vms026AhmmoerpDtlsettingsDao.lovFloor(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input) {
        try {
            List<Vms026VoMonitoringOutput> data = vms026AhmgavmsHdrinvitsDao.getMonitoring(input);
            int countData = vms026AhmgavmsHdrinvitsDao.getMonitoringCount(input);
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, data, countData);
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, "GAGAL", null, null, 0);
        }
    }

    @Override
    public DtoResponseWorkspace submitInvitation(DtoParamPaging input, VoUserCred user) {
        try {
            String masterNo = AhmStringUtil.hasValue(input.getSearch().get("masterNo")) ? (input.getSearch().get("masterNo") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            Date dateStart = DateUtil.stringToDate((String) input.getSearch().get("dateStart"), "dd-MMM-yyyy");
            Date dateEnd = DateUtil.stringToDate((String) input.getSearch().get("dateEnd"), "dd-MMM-yyyy");
            String loc = AhmStringUtil.hasValue(input.getSearch().get("loc")) ? (input.getSearch().get("loc") + "").toUpperCase() : "";
            String locSpec = AhmStringUtil.hasValue(input.getSearch().get("locSpec")) ? (input.getSearch().get("locSpec") + "").toUpperCase() : "";
            String purpose = AhmStringUtil.hasValue(input.getSearch().get("purpose")) ? (input.getSearch().get("purpose") + "").toUpperCase() : "";
            String form = AhmStringUtil.hasValue(input.getSearch().get("form")) ? (input.getSearch().get("form") + "").toUpperCase() : "";
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            if (visitorType.equalsIgnoreCase("") || 
                    plant.equalsIgnoreCase("") || 
                    purpose.equalsIgnoreCase("") || 
                    dateStart.toString().equalsIgnoreCase("") || 
                    dateEnd.toString().equalsIgnoreCase("")) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Field mandatory tidak boleh kosong");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, msg, null);
            } else {
                AhmgavmsHdrinvits cekExist = new AhmgavmsHdrinvits();
                cekExist = vms026AhmgavmsHdrinvitsDao.findOne(masterNo);
                if (cekExist == null) {
                    AhmgavmsHdrinvits hdr = new AhmgavmsHdrinvits();
                    hdr.setVmasterno(masterNo);
                    hdr.setDplstart(dateStart);
                    hdr.setDplend(dateEnd);
                    hdr.setVnrppic(userId);
                    hdr.setVplantid(plant);
                    hdr.setVloc(loc);
                    hdr.setVlocspec(locSpec);
                    hdr.setVtype(visitorType);
                    hdr.setVpurpose(purpose);
                    hdr.setVstatus("AKTIF");
                    hdr.setCreateBy(userId);
                    hdr.setCreateDate(new Date());
                    hdr.setLastModDate(new Date());
                    hdr.setLastModBy(userId);
                    vms026AhmgavmsHdrinvitsDao.save(hdr);
                    vms026AhmgavmsHdrinvitsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                } else {
                    cekExist.setDplstart(dateStart);
                    cekExist.setDplend(dateEnd);
                    cekExist.setVnrppic(userId);
                    cekExist.setVplantid(plant);
                    cekExist.setVloc(loc);
                    cekExist.setVlocspec(locSpec);
                    cekExist.setVtype(visitorType);
                    cekExist.setVpurpose(purpose);
                    if (form.equalsIgnoreCase("SUBMIT")) {
                        cekExist.setVstatus("AKTIF");
                    }
                    cekExist.setLastModDate(new Date());
                    cekExist.setLastModBy(userId);
                    vms026AhmgavmsHdrinvitsDao.update(cekExist);
                    vms026AhmgavmsHdrinvitsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
            }
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace saveInvitation(DtoParamPaging input, VoUserCred user) {
        try {
            String masterNo = AhmStringUtil.hasValue(input.getSearch().get("masterNo")) ? (input.getSearch().get("masterNo") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            Date dateStart = DateUtil.stringToDate((String) input.getSearch().get("dateStart"), "dd-MMM-yyyy");
            Date dateEnd = DateUtil.stringToDate((String) input.getSearch().get("dateEnd"), "dd-MMM-yyyy");
            String loc = AhmStringUtil.hasValue(input.getSearch().get("loc")) ? (input.getSearch().get("loc") + "").toUpperCase() : "";
            String locSpec = AhmStringUtil.hasValue(input.getSearch().get("locSpec")) ? (input.getSearch().get("locSpec") + "").toUpperCase() : "";
            String purpose = AhmStringUtil.hasValue(input.getSearch().get("purpose")) ? (input.getSearch().get("purpose") + "").toUpperCase() : "";
            String form = AhmStringUtil.hasValue(input.getSearch().get("form")) ? (input.getSearch().get("form") + "").toUpperCase() : "";
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            if (visitorType.equalsIgnoreCase("") || 
                    plant.equalsIgnoreCase("") || 
                    purpose.equalsIgnoreCase("") || 
                    dateStart.toString().equalsIgnoreCase("") || 
                    dateEnd.toString().equalsIgnoreCase("")) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Field mandatory tidak boleh kosong");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, msg, null);
            } else {
                AhmgavmsHdrinvits cekExist = new AhmgavmsHdrinvits();
                cekExist = vms026AhmgavmsHdrinvitsDao.findOne(masterNo);
                if (cekExist == null) {
                    AhmgavmsHdrinvits hdr = new AhmgavmsHdrinvits();
                    hdr.setVmasterno(masterNo);
                    hdr.setDplstart(dateStart);
                    hdr.setDplend(dateEnd);
                    hdr.setVnrppic(userId);
                    hdr.setVplantid(plant);
                    hdr.setVloc(loc);
                    hdr.setVlocspec(locSpec);
                    hdr.setVtype(visitorType);
                    hdr.setVpurpose(purpose);
                    hdr.setVstatus("DRAFT");
                    hdr.setCreateBy(userId);
                    hdr.setCreateDate(new Date());
                    hdr.setLastModDate(new Date());
                    hdr.setLastModBy(userId);
                    vms026AhmgavmsHdrinvitsDao.save(hdr);
                    vms026AhmgavmsHdrinvitsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                } else {
                    cekExist.setDplstart(dateStart);
                    cekExist.setDplend(dateEnd);
                    cekExist.setVnrppic(userId);
                    cekExist.setVplantid(plant);
                    cekExist.setVloc(loc);
                    cekExist.setVlocspec(locSpec);
                    cekExist.setVtype(visitorType);
                    cekExist.setVpurpose(purpose);
                    if (form.equalsIgnoreCase("SUBMIT")) {
                        cekExist.setVstatus("DRAFT");
                    }
                    cekExist.setLastModDate(new Date());
                    cekExist.setLastModBy(userId);
                    vms026AhmgavmsHdrinvitsDao.update(cekExist);
                    vms026AhmgavmsHdrinvitsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace submitChief(DtoParamPaging input, VoUserCred user) {
        try {
            String invitNo = AhmStringUtil.hasValue(input.getSearch().get("invitNo")) ? (input.getSearch().get("invitNo") + "").toUpperCase() : "";
            String masterNo = AhmStringUtil.hasValue(input.getSearch().get("masterNo")) ? (input.getSearch().get("masterNo") + "").toUpperCase() : "";
            String name = AhmStringUtil.hasValue(input.getSearch().get("name")) ? (input.getSearch().get("name") + "").toUpperCase() : "";
            String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
            String email = AhmStringUtil.hasValue(input.getSearch().get("email")) ? (input.getSearch().get("email") + "") : "";
            String noHp = AhmStringUtil.hasValue(input.getSearch().get("noHp")) ? (input.getSearch().get("noHp") + "").toUpperCase() : "";
            String strQuota = AhmStringUtil.hasValue(input.getSearch().get("quota")) ? (input.getSearch().get("quota") + "").toUpperCase() : "0";
            Integer quota = Integer.parseInt(strQuota);
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            if (company.equalsIgnoreCase("") || 
                    name.equalsIgnoreCase("") || 
                    email.equalsIgnoreCase("") || 
                    noHp.equalsIgnoreCase("") || 
                    quota == 0) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Field mandatory tidak boleh kosong");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, msg, null);
            } else {
                AhmgavmsHdrchiefs cekExist = new AhmgavmsHdrchiefs();
                cekExist = vms026AhmgavmsHdrchiefsDao.findOne(invitNo);
                if (cekExist == null) {
                    AhmgavmsHdrchiefs chf = new AhmgavmsHdrchiefs();
                    chf.setVinvitno(invitNo);
                    chf.setVmasterno(masterNo);
                    chf.setVname(name);
                    chf.setVcompany(company);
                    chf.setVemail(email);
                    chf.setVnohp(noHp);
                    chf.setNquota(quota);
                    String invLink = getInvitationLink(invitNo);
                    chf.setVinvlink(invLink);
                    chf.setCreateBy(userId);
                    chf.setCreateDate(new Date());
                    chf.setLastModDate(new Date());
                    chf.setLastModBy(userId);
                    vms026AhmgavmsHdrchiefsDao.save(chf);
                    vms026AhmgavmsHdrchiefsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                } else {
                    Integer val = vms026AhmgavmsHdrchiefsDao.validateQuota(input);
                    if (val > quota) {
                        Map<String, Object> msg = new HashMap<>();
                        msg.put("validate", "Quota pada undangan " + invitNo + ""
                                + "sudah digunakan sebanyak " + String.valueOf(val) + ".");
                        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, msg, null);
                    }
                    cekExist.setVname(name);
                    cekExist.setVcompany(company);
                    cekExist.setVemail(email);
                    cekExist.setVnohp(noHp);
                    cekExist.setNquota(quota);
                    String invLink = getInvitationLink(invitNo);
                    cekExist.setVinvlink(invLink);
                    cekExist.setLastModDate(new Date());
                    cekExist.setLastModBy(userId);
                    vms026AhmgavmsHdrchiefsDao.update(cekExist);
                    vms026AhmgavmsHdrchiefsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
            }
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    private String getInvitationLink(String invNo) {
        return vms026AhmmoerpDtlsettingsDao.getInvLink(invNo);
    }

    @Override
    public DtoResponseWorkspace showStatus(DtoParamPaging input) {
        try {
            List<Vms026VoLovOutput> data = vms026AhmmoerpDtlsettingsDao.lovStatus(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace getMasterNo(DtoParamPaging input) {
        try {
            Map<String, Object> masterNo = vms026AhmmoerpDtlsettingsDao.getMasterNo();
            String data;
            String runNumber = masterNo.get("masterNo").toString();
            switch (runNumber.length()) {
                case 1:
                    runNumber = "0000" + runNumber;
                    break;
                case 2:
                    runNumber = "000" + runNumber;
                    break;
                case 3:
                    runNumber = "00" + runNumber;
                    break;
                case 4:
                    runNumber = "0" + runNumber;
                    break;
                default:
                    break;
            }
            AhmmoerpDtlsettingsPk dtlPk = new AhmmoerpDtlsettingsPk();
            dtlPk.setRsetVid("VMS_INV_NO");
            dtlPk.setVitemcode("INV");
            AhmmoerpDtlsettings dtl = new AhmmoerpDtlsettings();
            dtl = vms026AhmmoerpDtlsettingsDao.findOne(dtlPk);
            dtl.setAhmmoerpDtlsettingsPk(dtlPk);
            dtl.setVitemname(masterNo.get("nextMo").toString());
            dtl.setVitemdesc(String.valueOf(masterNo.get("nextNo")));
            dtl.setLastModBy("DEVELOPER");
            dtl.setLastModDate(new Date());
            vms026AhmmoerpDtlsettingsDao.update(dtl);
            vms026AhmmoerpDtlsettingsDao.flush();

            cal.setTime(m);
            int year = cal.get(Calendar.YEAR);
            data = "VMS/INV/" + String.valueOf(year) + "/" + masterNo.get("nextMo").toString() + "/" + runNumber;
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
    private String getUserId(VoUserCred userCred) {
        String userId = userCred.getUsername();

        if (!(!AhmStringUtil.hasValue(userCred.getDomain()) || "SYSTEM".equalsIgnoreCase(userCred.getDomain()))) {
            userId = userCred.getDomain() + "\\" + userCred.getUsername();
        }

        return userId;
    }

    @Override
    public DtoResponseWorkspace getExcel(DtoParamPaging dto) {
        String nrp = AhmStringUtil.hasValue(dto.getSearch().get("picAhm")) ? (dto.getSearch().get("picAhm") + "").toUpperCase() : "";
        
        List<Vms026VoMonitoringOutput> list = vms026AhmgavmsHdrinvitsDao.getMonitoring(dto);
        int count = vms026AhmgavmsHdrinvitsDao.getMonitoringCount(dto);
        
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, list, count);
    }

    @Override
    public DtoResponseWorkspace showLocation(DtoParamPaging input) {
        try {
            List<Vms026VoLovOutput> data = vms026AhmmoerpDtlsettingsDao.lovLocation(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace deleteInvitation(DtoParamPaging input) {
        try {
            AhmgavmsHdrchiefs chf = new AhmgavmsHdrchiefs();
            AhmgavmsHdrinvits hdr = new AhmgavmsHdrinvits();
            String invitNo = AhmStringUtil.hasValue(input.getSearch().get("invitNo")) ? (input.getSearch().get("invitNo") + "").toUpperCase() : "";
            String masterNo = AhmStringUtil.hasValue(input.getSearch().get("masterNo")) ? (input.getSearch().get("masterNo") + "").toUpperCase() : "";
            hdr = vms026AhmgavmsHdrinvitsDao.findOne(masterNo);
            if (hdr.getVstatus().toString().equalsIgnoreCase("DRAFT")) {
                vms026AhmgavmsHdrchiefsDao.deleteById(invitNo);
                vms026AhmgavmsHdrchiefsDao.flush();
                int count = vms026AhmgavmsHdrchiefsDao.getCountData(input);
                if (count == 0) {
                    vms026AhmgavmsHdrinvitsDao.deleteById(masterNo);
                    vms026AhmgavmsHdrinvitsDao.flush();
                }
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
}
