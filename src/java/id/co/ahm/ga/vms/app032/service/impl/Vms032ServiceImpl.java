/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmgavmsDecs;
import id.co.ahm.ga.vms.app000.model.AhmgavmsHisDeclrs;
import id.co.ahm.ga.vms.app032.dao.Vms032AhmgavmsDecsDao;
import id.co.ahm.ga.vms.app032.dao.Vms032AhmgavmsHisDeclrsDao;
import id.co.ahm.ga.vms.app032.dao.Vms032AhmmoerpDtlsettingsDao;
import id.co.ahm.ga.vms.app032.service.Vms032Service;
import id.co.ahm.ga.vms.app032.vo.Vms032VoFileDtl;
import id.co.ahm.ga.vms.app032.vo.Vms032VoLov;
import id.co.ahm.ga.vms.app032.vo.Vms032VoShowData;
import id.co.ahm.ga.vms.app032.vo.Vms032VoShowPlant;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;



/**
 *
 * @author Hitoshi Mario Naga M
 */
@Transactional(readOnly = true)
@Service(value = "vms032Service")
public class Vms032ServiceImpl implements Vms032Service {
    @Autowired
    @Qualifier("vms032AhmmoerpDtlsettingsDao")
    private Vms032AhmmoerpDtlsettingsDao vms032AhmmoerpDtlsettingsDao;
    
    @Autowired
    @Qualifier("vms032AhmgavmsHisDeclrsDao")
    private Vms032AhmgavmsHisDeclrsDao vms032AhmgavmsHisDeclrsDao;
    
    @Autowired
    @Qualifier("vms032AhmgavmsDecsDao")
    private Vms032AhmgavmsDecsDao vms032AhmgavmsDecsDao;

    private String getUserId(VoUserCred userCred) {
        String userId = userCred.getUsername();

        if (!(!AhmStringUtil.hasValue(userCred.getDomain()) || "SYSTEM".equalsIgnoreCase(userCred.getDomain()))) {
            userId = userCred.getDomain() + "\\" + userCred.getUsername();
        }

        return userId;
    }
    
    @Override
    public DtoResponseWorkspace showStatus(DtoParamPaging input){
        try {
            List<Vms032VoLov> data = vms032AhmmoerpDtlsettingsDao.lovStatusType(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showType(DtoParamPaging input) {
        try {
            List<Vms032VoLov> data = vms032AhmmoerpDtlsettingsDao.lovDecType(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
    
        @Override
        public DtoResponsePagingWorkspace showMonitoring(DtoParamPaging input) {
        try {
            List<Vms032VoShowData> data = vms032AhmgavmsDecsDao.getMonitoring(input);
            int countData = vms032AhmgavmsDecsDao.getMonitoringCount(input);
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, data, countData);
	} catch (Exception e) {
		return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, "GAGAL", null, null, 0);
        }
    }

    @Override
    public DtoResponseWorkspace submitDeclarationMaster(DtoParamPaging input, VoUserCred user) {
            try{
            String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
            String declarationType = AhmStringUtil.hasValue(input.getSearch().get("declarationType")) ? (input.getSearch().get("declarationType") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String judul = AhmStringUtil.hasValue(input.getSearch().get("judul")) ? (input.getSearch().get("judul") + "").toUpperCase() : "";
            String htmlIndonesia = AhmStringUtil.hasValue(input.getSearch().get("htmlIndonesia")) ? (input.getSearch().get("htmlIndonesia") + "").toUpperCase() : "";
            String escapeHtmlIndonesia = HtmlUtils.htmlEscape(htmlIndonesia);
            String htmlInggris = AhmStringUtil.hasValue(input.getSearch().get("htmlInggris")) ? (input.getSearch().get("htmlInggris") + "").toUpperCase() : "";
            String escapeHtmlInggris = HtmlUtils.htmlEscape(htmlInggris);
            String sequence = AhmStringUtil.hasValue(input.getSearch().get("sequence")) ? (input.getSearch().get("sequence") + "").toUpperCase() : "";
            Date dateStart = DateUtil.stringToDate((String) input.getSearch().get("dateStart"), "dd-MM-yyyy");
            Date dateEnd = DateUtil.stringToDate((String) input.getSearch().get("dateEnd"), "dd-MM-yyyy");
            String form = AhmStringUtil.hasValue(input.getSearch().get("form")) ? (input.getSearch().get("form") + "").toUpperCase() : "";
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
            
            AhmgavmsDecs dec = new AhmgavmsDecs();
            dec = vms032AhmgavmsDecsDao.findOne(declarationType);
            
            int count = vms032AhmgavmsDecsDao.getVersionData(input);
            if(dec == null ){
                    AhmgavmsDecs decs = new AhmgavmsDecs();
                    decs.setVdectype(declarationType);
                    decs.setVplantid("ALL");
                    decs.setVtitle(declarationType);
                    decs.setVstatus(status);
                    decs.setDstarteff(dateStart);
                    decs.setDendeff(dateEnd);
                    decs.setVbodyid(escapeHtmlIndonesia);
                    decs.setVbodyen(escapeHtmlInggris);
                    decs.setVversion(count+1);
                    decs.setVseq(sequence);
                    decs.setCreateDate(new Date());
                    decs.setCreateBy(userId);
                    decs.setLastModDate(new Date());
                    decs.setLastModBy(userId);

                    vms032AhmgavmsDecsDao.save(decs);
                    vms032AhmgavmsDecsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
           }else{
                    
                    if(status.equals("N")){
                        AhmgavmsHisDeclrs his = new AhmgavmsHisDeclrs();
                        his.setVdecstype(declarationType);
                        his.setVplantid("ALL");
                        his.setVtitle(declarationType);
                        his.setVbodyid(escapeHtmlIndonesia);
                        his.setVbodyen(escapeHtmlInggris);
                        his.setDstarteff(dateStart);
                        his.setDendeff(dateEnd);
                        his.setVseq(sequence);
                        his.setVversion(count);
                        his.setCreateDate(new Date());
                        his.setCreateBy(userId);
                        his.setLastModDate(new Date());
                        his.setLastModBy(userId);
                        
                        vms032AhmgavmsHisDeclrsDao.save(his);
                        vms032AhmgavmsHisDeclrsDao.flush();
                        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                    }else{
                    dec.setVstatus(status);
                    dec.setDstarteff(dateStart);
                    dec.setDendeff(dateEnd);
                    dec.setVbodyid(escapeHtmlIndonesia);
                    dec.setVbodyen(escapeHtmlInggris);
                    dec.setVseq(sequence);
                    dec.setVversion(count+1);
                    dec.setCreateDate(new Date());
                    dec.setCreateBy(userId);
                    dec.setLastModDate(new Date());
                    dec.setLastModBy(userId);

                    vms032AhmgavmsDecsDao.update(dec);
                    vms032AhmgavmsDecsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
//
    @Override
    public DtoResponseWorkspace draftDeclaration(DtoParamPaging input, VoUserCred user) {
        try{
            String status = AhmStringUtil.hasValue(input.getSearch().get("status")) ? (input.getSearch().get("status") + "").toUpperCase() : "";
            String declarationType = AhmStringUtil.hasValue(input.getSearch().get("declarationType")) ? (input.getSearch().get("declarationType") + "").toUpperCase() : "";
            String plant = AhmStringUtil.hasValue(input.getSearch().get("plant")) ? (input.getSearch().get("plant") + "").toUpperCase() : "";
            String judul = AhmStringUtil.hasValue(input.getSearch().get("judul")) ? (input.getSearch().get("judul") + "").toUpperCase() : "";
            String htmlIndonesia = AhmStringUtil.hasValue(input.getSearch().get("htmlIndonesia")) ? (input.getSearch().get("htmlIndonesia") + "").toUpperCase() : "";
            String escapeHtmlIndonesia = HtmlUtils.htmlEscape(htmlIndonesia);
            String htmlInggris = AhmStringUtil.hasValue(input.getSearch().get("htmlInggris")) ? (input.getSearch().get("htmlInggris") + "").toUpperCase() : "";
            String escapeHtmlInggris = HtmlUtils.htmlEscape(htmlInggris);
            String sequence = AhmStringUtil.hasValue(input.getSearch().get("sequence")) ? (input.getSearch().get("sequence") + "").toUpperCase() : "";
            Date dateStart = DateUtil.stringToDate((String) input.getSearch().get("dateStart"), "dd-MM-yyyy");
            Date dateEnd = DateUtil.stringToDate((String) input.getSearch().get("dateEnd"), "dd-MM-yyyy");
            String form = AhmStringUtil.hasValue(input.getSearch().get("form")) ? (input.getSearch().get("form") + "").toUpperCase() : "";
            String userId;
            if (user == null) {
                userId = "DEVELOPER";
            } else {
                userId = user.getUserid();
            }
                
                AhmgavmsDecs dec = new AhmgavmsDecs();
                dec = vms032AhmgavmsDecsDao.findOne(declarationType);
                
                int count = vms032AhmgavmsDecsDao.getVersionData(input);
                if(dec == null){
                    AhmgavmsDecs decs = new AhmgavmsDecs();
                    decs.setVdectype(declarationType);
                    decs.setVplantid("ALL");
                    decs.setVtitle(declarationType);
                    decs.setVstatus("D");
                    decs.setDstarteff(dateStart);
                    decs.setDendeff(dateEnd);
                    decs.setVbodyid(escapeHtmlIndonesia);
                    decs.setVbodyen(escapeHtmlInggris);
                    decs.setVversion(count+1);
                    decs.setVseq(sequence);
                    decs.setCreateDate(new Date());
                    decs.setCreateBy(userId);
                    decs.setLastModDate(new Date());
                    decs.setLastModBy(userId);

                    vms032AhmgavmsDecsDao.save(decs);
                    vms032AhmgavmsDecsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
		} else {
                    dec.setVstatus("D");
                    dec.setDstarteff(dateStart);
                    dec.setDendeff(dateEnd);
                    dec.setVbodyid(escapeHtmlIndonesia);
                    dec.setVbodyen(escapeHtmlInggris);
                    dec.setVseq(sequence);
                    dec.setVversion(count+1);
                    dec.setCreateDate(new Date());
                    dec.setCreateBy(userId);
                    dec.setLastModDate(new Date());
                    dec.setLastModBy(userId);

                    vms032AhmgavmsDecsDao.update(dec);
                    vms032AhmgavmsDecsDao.flush();
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
                }
        } catch (Exception e){
                e.printStackTrace();
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }
//

    @Override
    public DtoResponseWorkspace deleteDeclaration(DtoParamPaging input) {
        try{
        AhmgavmsDecs dec = new AhmgavmsDecs();
        String declarationType = AhmStringUtil.hasValue(input.getSearch().get("declarationType")) ? (input.getSearch().get("declarationType") + "").toUpperCase() : "";

            int count = vms032AhmgavmsDecsDao.getCountData(input);
                if(count != 0){
                    vms032AhmgavmsDecsDao.deleteById(declarationType);
                    vms032AhmgavmsDecsDao.flush();
                }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        }catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @Override
    public DtoResponseWorkspace showDocType(DtoParamPaging input) {
        try {
            List<Vms032VoLov> data = vms032AhmmoerpDtlsettingsDao.lovDocType(input);
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, data);
        } catch (Exception e) {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, null, null);
        }
    }

//    @Override
//    public Vms032VoFileDtl uploadToServer(String pathServer, MultipartFile fileToUpload, String tipeFile, VoUserCred user) {
//        Vms032VoFileDtl fileDtl = new Vms032VoFileDtl();
//        try{
//            String fileName = fileToUpload.getOriginalFilename();
//            String fileType = fileToUpload.getContentType();
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
//            String newFileName = fileName + "_" + tipeFile + "." + fileExt;
//            String pathServer
//        }
//    }
    
        
        
}
    
