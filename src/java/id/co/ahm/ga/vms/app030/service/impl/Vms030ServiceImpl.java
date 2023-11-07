/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsLogemails;
import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocs;
import id.co.ahm.ga.vms.app000.model.AhmgavmsMstrefdocsPk;
import id.co.ahm.ga.vms.app000.model.hr.FmhrdGeneralDatas;
import static id.co.ahm.ga.vms.app030.constant.Vms030Constant.FROM;
import id.co.ahm.ga.vms.app030.dao.Vms030AhmgavmsLogemailsDao;
import id.co.ahm.ga.vms.app030.dao.Vms030AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app030.service.Vms030Service;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovDocType;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovVisitorType;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPlant;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPlantForm;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovStatus;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovStatusForm;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovVisitorTypeForm;
import id.co.ahm.ga.vms.app030.vo.Vms030VoTableResult;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.co.ahm.ga.vms.app030.dao.Vms030AhmgavmsMstrefdocsDao;
import id.co.ahm.ga.vms.app030.dao.Vms030FmhrdGeneralDatasDao;
import id.co.ahm.ga.vms.app030.dao.Vms030ObjectDao;
import id.co.ahm.ga.vms.app030.vo.Vms030VoEmail;
import id.co.ahm.ga.vms.app030.vo.Vms030VoLovPic;
import id.co.ahm.jx.email.service.EmailService;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import java.math.BigDecimal;

/**
 *
 * @author Nurvan Afandi
 */

@Transactional
@Service(value = "vms030Service")
public class Vms030ServiceImpl implements Vms030Service {
    
    @Autowired
    @Qualifier("vms030AhmgavmsMstrefdocsDao")
    private Vms030AhmgavmsMstrefdocsDao vms030AhmgavmsMstrefdocsDao;
    
    @Autowired
    @Qualifier("vms030AhmmoerpDtlsettingsDao")
    private Vms030AhmmoerpDtlsettingsDao vms030AhmmoerpDtlsettingsDao;
      
    @Autowired
    @Qualifier("vms030ObjectDao")
    private Vms030ObjectDao vms030ObjectDao;
    
    @Autowired
    @Qualifier("vms030FmhrdGeneralDatasDao")
    private Vms030FmhrdGeneralDatasDao vms030FmhrdGeneralDatasDao;
    
    @Autowired
    @Qualifier("mailService")
    private EmailService emailService;
    
    @Autowired
    @Qualifier("vms030AhmgavmsLogemailsDao")
    private Vms030AhmgavmsLogemailsDao vms030AhmgavmsLogemailsDao;
    
    private String getUserId(VoUserCred userCred) {
        String userId = userCred.getUsername();

        if (!(!AhmStringUtil.hasValue(userCred.getDomain()) || "SYSTEM".equalsIgnoreCase(userCred.getDomain()))) {
            userId = userCred.getDomain() + "\\" + userCred.getUsername();
        }

        return userId;
    }
    
    
    @Override
    public DtoResponseWorkspace showPlant(DtoParamPaging input) {
        try {
            List<Vms030VoLovPlant> data = vms030AhmmoerpDtlsettingsDao.lovPlant(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showVisitorType(DtoParamPaging input) {
        try {
            List<Vms030VoLovVisitorType> data = vms030AhmmoerpDtlsettingsDao.lovVisitorType(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
    @Override
    public DtoResponseWorkspace showStatus(DtoParamPaging input) {
        try {
            List<Vms030VoLovStatus> data = vms030AhmmoerpDtlsettingsDao.lovStatus(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showDocType(DtoParamPaging input) {
        try {
            List<Vms030VoLovDocType> data = vms030AhmmoerpDtlsettingsDao.lovDocType(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
        @Override
    public DtoResponseWorkspace showVisitorTypeForm(DtoParamPaging input) {
        try {
            List<Vms030VoLovVisitorTypeForm> data = vms030AhmmoerpDtlsettingsDao.lovVisitorTypeForm(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
        @Override
    public DtoResponseWorkspace showPlantForm(DtoParamPaging input) {
        try {
            List<Vms030VoLovPlantForm> data = vms030AhmmoerpDtlsettingsDao.lovPlantForm(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showStatusForm(DtoParamPaging input) {
        try {
            List<Vms030VoLovStatusForm> data = vms030AhmmoerpDtlsettingsDao.lovStatusForm(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
    @Override
    public DtoResponseWorkspace showPic(DtoParamPaging input) {
        try {
            List<Vms030VoLovPic> data = vms030ObjectDao.getPic(input);
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, null, null, data, 1);
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, null, null, null, 0);
        }
    }
    
    @Override
    public DtoResponsePagingWorkspace showTable(DtoParamPaging input) {
        try {
             List<Vms030VoTableResult> data = vms030AhmgavmsMstrefdocsDao.getTable(input);
             int countData = vms030AhmgavmsMstrefdocsDao.getTableCount(input);
             return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, null, null, data, countData);
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, null, null, null, 0);
        }
    }
    
    @Override
    public DtoResponseWorkspace saveReference(DtoParamPaging input, VoUserCred user) {
        try {
            String noDoc = AhmStringUtil.hasValue(input.getSearch().get("noDoc")) ? (input.getSearch().get("noDoc") + "").toUpperCase() : "";
            String workDesc = AhmStringUtil.hasValue(input.getSearch().get("workDesc")) ? (input.getSearch().get("workDesc") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
            String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
            String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
            String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
            Date dateStart = DateUtil.stringToDate((String) input.getSearch().get("dateStart"), "dd-MMM-yyyy");
            Date dateEnd = DateUtil.stringToDate((String) input.getSearch().get("dateEnd"), "dd-MMM-yyyy");
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            
            if (noDoc.equalsIgnoreCase("") || 
                    visitorType.equalsIgnoreCase("") || 
                    plant.equalsIgnoreCase("") ||
                    company.equalsIgnoreCase("") ||
                    workDesc.equalsIgnoreCase("") ||
                    status.equalsIgnoreCase("") ||
                    dateStart.toString().equalsIgnoreCase("") || 
                    dateEnd.toString().equalsIgnoreCase("") ||
                    nrp.equalsIgnoreCase("")) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Field mandatory tidak boleh kosong");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, msg, null);
            } else {
                
                AhmgavmsMstrefdocsPk pk = new AhmgavmsMstrefdocsPk();
                pk.setVrefdocno(noDoc);
                
                AhmgavmsMstrefdocs vo = new AhmgavmsMstrefdocs();
                vo = vms030AhmgavmsMstrefdocsDao.findOne(pk);

                if (vo == null) {
                    
                    AhmgavmsMstrefdocsPk refPk = new AhmgavmsMstrefdocsPk();
                    refPk.setVrefdocno(noDoc);
                    
                    AhmgavmsMstrefdocs ref = new AhmgavmsMstrefdocs();
                    ref.setAhmgavmsMstrefdocsPk(refPk);
                    
                    ref.setVworkdesc(workDesc);
                    ref.setVplantid(plant);
                    ref.setVtype(visitorType);    
                    ref.setVcompany(company);
                    ref.setVstatus("D");    
                    ref.setDworkstart(dateStart);
                    ref.setDworkend(dateEnd);   
                    ref.setVpicnrp(nrp);
                    ref.setCreateDate(new Date());
                    ref.setCreateBy(userId);
                    ref.setLastModDate(new Date());
                    ref.setLastModBy(userId);
                    vms030AhmgavmsMstrefdocsDao.save(ref);
                    vms030AhmgavmsMstrefdocsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
                else {
                    vo.setVworkdesc(workDesc);
                    vo.setVplantid(plant);
                    vo.setVtype(visitorType);    
                    vo.setVcompany(company);
                    vo.setVstatus("D");    
                    vo.setDworkstart(dateStart);
                    vo.setDworkend(dateEnd);   
                    vo.setVpicnrp(nrp); 
                    vo.setCreateDate(new Date());
                    vo.setCreateBy(userId);
                    vo.setLastModDate(new Date());
                    vo.setLastModBy(userId);
                    vms030AhmgavmsMstrefdocsDao.update(vo);
                    vms030AhmgavmsMstrefdocsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
            }
        } catch (Exception e){
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
    @Override
    public DtoResponseWorkspace submitReference(DtoParamPaging input, VoUserCred user) {
        try {
            String noDoc = AhmStringUtil.hasValue(input.getSearch().get("noDoc")) ? (input.getSearch().get("noDoc") + "").toUpperCase() : "";
            String workDesc = AhmStringUtil.hasValue(input.getSearch().get("workDesc")) ? (input.getSearch().get("workDesc") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String visitorType = AhmStringUtil.hasValue(input.getSearch().get("visitorType")) ? (input.getSearch().get("visitorType") + "").toUpperCase() : "";
            String company = AhmStringUtil.hasValue(input.getSearch().get("company")) ? (input.getSearch().get("company") + "").toUpperCase() : "";
            String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
            String nrp = AhmStringUtil.hasValue(input.getSearch().get("nrp")) ? (input.getSearch().get("nrp") + "").toUpperCase() : "";
            Date dateStart = DateUtil.stringToDate((String) input.getSearch().get("dateStart"), "dd-MMM-yyyy");
            Date dateEnd = DateUtil.stringToDate((String) input.getSearch().get("dateEnd"), "dd-MMM-yyyy");
            
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            
            if (noDoc.equalsIgnoreCase("") || 
                    visitorType.equalsIgnoreCase("") ||
                    plant.equalsIgnoreCase("") ||
                    company.equalsIgnoreCase("") ||
                    workDesc.equalsIgnoreCase("") ||
                    status.equalsIgnoreCase("") ||
                    dateStart.toString().equalsIgnoreCase("") || 
                    dateEnd.toString().equalsIgnoreCase("") ||
                    nrp.equalsIgnoreCase("")) {
                Map<String, Object> msg = new HashMap<>();
                msg.put("validate", "Field mandatory tidak boleh kosong");
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, msg, null);
            } else {
            
                AhmgavmsMstrefdocsPk pk = new AhmgavmsMstrefdocsPk();
                pk.setVrefdocno(noDoc);
                        
                AhmgavmsMstrefdocs vo = new AhmgavmsMstrefdocs();
                vo = vms030AhmgavmsMstrefdocsDao.findOne(pk);
                
                if (vo == null) {
                    
                    AhmgavmsMstrefdocsPk refPk = new AhmgavmsMstrefdocsPk();
                    refPk.setVrefdocno(noDoc);
                    
                    AhmgavmsMstrefdocs ref = new AhmgavmsMstrefdocs();
                    ref.setAhmgavmsMstrefdocsPk(refPk);
                    
//                    List<Vms030VoTableResult> data = vms030AhmgavmsMstrefdocsDao.getTable(input);
//                    
//                    for (Vms030VoTableResult ls : data) {
//                        List<Vms030VoFileAttachment> listAtc = new ArrayList<>();
//                        List<String> fAtc = vms030AhmmoerpDtlsettingsDao.getFileName(ls.getFileAtc(), ls.getFileNameAtc(), "MEMO");
//                        if (!fAtc.isEmpty()) 
//                        for (String v : fAtc) {
//                            Vms030VoFileAttachment dtAtc = new Vms030VoFileAttachment();
//
//                            byte[] bFileAtc = readBytesFromFile(pathServer + v);
//                            dtAtc.setName(Base64.getEncoder().encodeToString(bFileAtc));
//
//                            listAtc.add(dtAtc);
//                        }
//                        ls.setFileAtc(listAtc);
//                    }
                    
                    
                    ref.setVworkdesc(workDesc);
                    ref.setVplantid(plant);
                    ref.setVtype(visitorType);
                    ref.setVcompany(company);
                    ref.setVstatus(status);
                    ref.setDworkstart(dateStart);
                    ref.setDworkend(dateEnd);
                    ref.setVpicnrp(nrp);
                    ref.setCreateBy(userId);
                    ref.setLastModDate(new Date());
                    ref.setLastModBy(userId);
                    vms030AhmgavmsMstrefdocsDao.save(ref);
                    vms030AhmgavmsMstrefdocsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
                else {
                    vo.setVworkdesc(workDesc);
                    vo.setVplantid(plant);
                    vo.setVtype(visitorType);
                    vo.setVcompany(company);
                    vo.setVstatus(status);
                    vo.setDworkstart(dateStart);
                    vo.setDworkend(dateEnd);
                    vo.setVpicnrp(nrp);
                    vo.setCreateBy(userId);
                    vo.setLastModDate(new Date());
                    vo.setLastModBy(userId);
                    vms030AhmgavmsMstrefdocsDao.update(vo);
                    vms030AhmgavmsMstrefdocsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
            }
        } catch (Exception e){
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace getExcel(DtoParamPaging dto) {
         String nrp = AhmStringUtil.hasValue(dto.getSearch().get("nrp")) ? (dto.getSearch().get("nrp") + "").toUpperCase() : "";
        
        
        List<Vms030VoTableResult> list = vms030AhmgavmsMstrefdocsDao.getTable(dto);
        int count = vms030AhmgavmsMstrefdocsDao.getTableCount(dto);
        
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, list, count);
    }

    @Override
    public DtoResponseWorkspace sendEmail(List<Vms030VoEmail> input, VoUserCred user) {
        try {
            for (Vms030VoEmail vo : input) {
                sendEmailInvitationLink(vo, user);
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
     
    private void sendEmailInvitationLink(Vms030VoEmail data, VoUserCred user) {
        String to = data.getTo();
        String noDoc = data.getNoDoc();
        String picName = data.getPicName();
        String companyName = data.getCompany();
        String plant = data.getPlant();
        String picAhm = data.getPicAhm();
        Date dateStart = DateUtil.stringToDate(data.getDateStart(), "dd-MMM-yyyy");
        Date dateEnd = DateUtil.stringToDate(data.getDateEnd(), "dd-MMM-yyyy");
        String emailPicAhm = data.getEmailPicAhm();
        String noHpPicAhm = data.getNoHpPicAhm();
        String userId;
        if (user == null) {
            userId = "DEVELOPER";
        } else {
            userId = user.getUserid();
        }
        
        try {

            String link = getInvitationLink(noDoc);

            String subject = subjectEmailInvitationLink(noDoc);

            String header = headerEmailInvitationLink(companyName);

            String body = bodyEmailInvitationLink(companyName, plant, (String) DateUtil.dateToString(dateStart, "dd-MMM-yyyy"), (String) DateUtil.dateToString(dateEnd, "dd-MMM-yyyy"), link,
                    picAhm, emailPicAhm, noHpPicAhm);

            String footer = "<br><i>\n NB : Email ini dihasilkan secara otomatis oleh sistem kami dan tidak memerlukan balasan. "
                    + "Pesan ini hanya bertujuan untuk memberikan informasi atau pemberitahuan tertentu dan tidak dimaksudkan "
                    + "untuk memulai percakapan atau interaksi email.<i/></p>";

            body = header + body + footer;
            emailService.callProcSendMail(subject, FROM, to.toString(), null, body);

            logEmails(noDoc, FROM, to, "1", userId);
        } catch (NullPointerException npE) {
            logEmails(noDoc, FROM, to, "0", userId);
        }
    }

    private String headerEmailInvitationLink(String companyName) {
	return "<p>Kepada Yth.</p>\n"
                + "<p>Tim Project</p>\n"
                + "<p>" + companyName + "</p>\n";
    }

    private String subjectEmailInvitationLink(String masterNo) {
	return "Surat Undangan No. " + masterNo + " Kunjungan ke PT. Astra Honda Motor.";
    }

    private String bodyEmailInvitationLink(String companyName, String plant, String dateStart, String dateEnd, 
            String link, String picAhmName, String emailPicAhm, String noHpPicAhm) {
	return "<table border='0'>\n"
		+ "    <tbody>\n"
		+ "        <tr>\n"
		+ "            <td colspan=3>Sehubungan dengan adanya keperluan " + companyName + " berkunjung ke PT. Astra Honda Motor " + plant + ","
                + " kami mengundang bapak / ibu pada : </td>\n"
		+ "        </tr>\n"
                + "        <tr>\n"
		+ "             <td><p> </p></td>\n"
                + "        </tr>\n"
		+ "        <tr>\n"
		+ "            <td>     Tanggal : " + dateStart + " - " + dateEnd + " </td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
		+ "            <td>     Lokasi  : " + plant + " </td>\n"
		+ "        </tr>\n"
                + "        <tr>\n"
		+ "             <td><p> </p></td>\n"
                + "        </tr>\n"
		+ "        <tr>\n"
		+ "            <td colspan=3>Kami telah menyediakan link pendaftaran online yang dapat anda akses melalui email ini. </td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
		+ "            <td> Link Registrasi: <a href=" + link + ">" + link + "</a></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
		+ "             <td><p> </p></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Diharapkan bapak / ibu sebagai PIC Visitor dari perusahaan " + companyName + " dapat membagikan Link "
                + "Register ini kepada anggota dari tim bapak / ibu yang akan berkunjung ke AHM.</td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
		+ "             <td><p> </p></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td>Jika anda memiliki pertanyaan lebih lanjut atau mengalami kesulitan dalam proses registrasi, jangan ragu "
                + "untuk menghubungi " + picAhmName + " di " + emailPicAhm + " atau " + noHpPicAhm + "</td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
		+ "             <td><p> </p></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "             <td>Hormat kami,</td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "             <td>PT. Astra Honda Motor</td>\n"
                + "        </tr>\n"
		+ "    </tbody>\n"
		+ "</table>";

    }
    
    private void logEmails(String code, String from, String to, String flag, String userId) {
        try {
            AhmgavmsLogemails log = new AhmgavmsLogemails();

            log.setVcode(code);
            log.setVfrom(from);
            log.setVto(to);
            log.setVflag(flag);
            log.setCreateBy(userId);
            log.setCreateDate(new Date());
            log.setLastModBy(userId);
            log.setLastModDate(new Date());

            vms030AhmgavmsLogemailsDao.save(log);
            vms030AhmgavmsLogemailsDao.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getNoHpUser(String userId) {
        FmhrdGeneralDatas data = new FmhrdGeneralDatas();
        BigDecimal nrp = new BigDecimal(userId);
        data = vms030FmhrdGeneralDatasDao.findOne(nrp);
        return data.getTelephone();
    }
    
    private String getInvitationLink(String noDoc) {
        return vms030AhmmoerpDtlsettingsDao.getInvLink(noDoc);
    }
}
