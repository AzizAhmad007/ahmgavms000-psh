/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service.impl;
 
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app000.model.AhmhrntmTxnidreps;
import id.co.ahm.ga.vms.app022.constant.Vms022Constant;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmMstpicotsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmTxnidrepsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmitwfsMstwfdochistDao;
import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.exception.Vms022Exception;
import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.util.Vms022ValidationDateUtil;
import static id.co.ahm.ga.vms.app022.util.Vms022ValidationDateUtil.checkIsAfter;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFileAttachment;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jx.b2e.app000.dao.Ahmitb2eMstusrrolesDao;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrroles;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrrolesPk;
import id.co.ahm.jx.wfs.app000.service.WorkflowService;
import id.co.ahm.jx.wfs.app000.vo.VoWfsParam;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoMessageWorkspace;
import id.co.ahm.jxf.vo.VoPstUserCred;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
/**
 *
 * @author reza.mr
 */
@Transactional(readOnly = true)
@Service(value = "vms022Service")
public class Vms022ServiceImpl implements Vms022Service {
 
    public final static String pathServer = "/data/AHMGA/VMS/Registrasi/";
 
    public final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    public final static LocalDateTime now = LocalDateTime.now();

    @Autowired
    @Qualifier(value = "wfService")
    private WorkflowService wfService;
 
    @Autowired
    @Qualifier("ahmitb2eMstusrrolesDao")
    private Ahmitb2eMstusrrolesDao ahmitb2eMstusrrolesDao;
 
    @Autowired
    @Qualifier("vms022ObjectDao")
    private Vms022ObjectDao vms022ObjectDao;
 
    @Autowired
    @Qualifier("vms022ahmhrntmDtlprmgblsDao")
    private Vms022AhmhrntmDtlprmgblsDao vms022ahmhrntmDtlprmgblsDao;
 
    @Autowired
    @Qualifier("vms022ahmhrntmHdrotsempsDao")
    private Vms022AhmhrntmHdrotsempsDao vms022ahmhrntmHdrotsempsDao;
 
    @Autowired
    @Qualifier("vms022ahmhrntmDtlotsregsDao")
    private Vms022AhmhrntmDtlotsregsDao vms022ahmhrntmDtlotsregsDao;
 
    @Autowired
    @Qualifier("vms022AhmhrntmMstpicotsDao")
    private Vms022AhmhrntmMstpicotsDao vms022AhmhrntmMstpicotsDao;
 
    @Autowired
    @Qualifier("vms022AhmhrntmTxnidrepsDao")
    private Vms022AhmhrntmTxnidrepsDao vms022ahmhrntmTxnidrepsDao;
 
    @Autowired
    @Qualifier("vms022AhmitwfsMstwfdochistDao")
    private Vms022AhmitwfsMstwfdochistDao vms022AhmitwfsMstwfdochistDao;
 
    @Override
    public DtoResponseWorkspace getFormAuthorization(VoUserCred userCred) {
        String userId = getUserId(userCred);
        List<Ahmitb2eMstusrroles> formFunctionList = ahmitb2eMstusrrolesDao.getListUserRole(userId);
        List<Map<String, String>> result = new ArrayList<>();
 
        for (Ahmitb2eMstusrroles data : formFunctionList) {
            Ahmitb2eMstusrrolesPk desc = data.getAhmitb2eMstusrrolesPk();
 
            if (desc.getVroleid().equals("RO_GAVMS_PICAHM")) {
                Map<String, String> role = new HashMap<>();
                role.put("roleName", desc.getVroleid());
                result.add(role);
            } else if (desc.getVroleid().equals("RO_GAVMS_OFCSECT")) {
                Map<String, String> role = new HashMap<>();
                role.put("roleName", desc.getVroleid());
                result.add(role);
            } else if (desc.getVroleid().equals("RO_GAVMS_SCHSEC")) {
                Map<String, String> role = new HashMap<>();
                role.put("roleName", desc.getVroleid());
                result.add(role);
            }
        }
 
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }
 
    private String getUserId(VoUserCred userCred) {
        String userId = userCred.getUsername();
 
        if (!(!AhmStringUtil.hasValue(userCred.getDomain()) || "SYSTEM".equalsIgnoreCase(userCred.getDomain()))) {
            userId = userCred.getDomain() + "\\" + userCred.getUsername();
        }
 
        return userId;
    }
 
    @Override
    public DtoResponseWorkspace showPlant() {
        List<Vms022VoLov> list = vms022ahmhrntmDtlprmgblsDao.lovPlant(null, true);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, list);
    }
 
    @Override
    public DtoResponseWorkspace showMonitoring(DtoParamPaging dto, VoUserCred userCred) {
 
        String userId = getUserId(userCred);
        String roleFromFront = AhmStringUtil.hasValue(dto.getSearch().get("role")) ? (dto.getSearch().get("role") + "").toUpperCase() : "";
        String nrp = AhmStringUtil.hasValue(dto.getSearch().get("userid")) ? (dto.getSearch().get("userid") + "").toUpperCase() : "";
 
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getSearchData(dto, userId, roleFromFront, nrp);
        int count = vms022ahmhrntmHdrotsempsDao.countSearchData(dto, userId, roleFromFront, nrp);
        try {
 
            for (Vms022VoMonitoring vo : list) {
                String getGateList = vms022ahmhrntmDtlprmgblsDao.getGateForExcel(vo.getOutId(), vo.getPersId());
                vo.setGateName(getGateList);
 
                if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
                    List<Vms022VoLov> compNameList = vms022ObjectDao.lovCompExternal(dto, userId, "FILTER");
 
                    if (!compNameList.isEmpty()) {
                        vo.setCompanyName(compNameList.get(0).getName());
                    } else {
                        vo.setCompanyName("Company Code not found");
                    }
                }
 
                byte[] bFileKtp = readBytesFromFile(pathServer + vo.getFileNameKtp());
                vo.setFileKtp(Base64.getEncoder().encodeToString(bFileKtp));
 
                List<Vms022VoFileAttachment> listVacs = new ArrayList<>();
                List<String> flVacs = vms022ahmhrntmDtlotsregsDao.getFileName(vo.getOutId(), vo.getPersId(), "VC");
                if (!flVacs.isEmpty()) {
                    for (String v : flVacs) {
                        Vms022VoFileAttachment dtVac = new Vms022VoFileAttachment();
 
                        byte[] bFileVac = readBytesFromFile(pathServer + v);
                        dtVac.setName(Base64.getEncoder().encodeToString(bFileVac));
 
                        listVacs.add(dtVac);
                    }
                    vo.setFileVaccines(listVacs);
                }
 
                List<Vms022VoFileAttachment> listAttcs = new ArrayList<>();
                List<String> flAttc = vms022ahmhrntmDtlotsregsDao.getFileName(vo.getOutId(), vo.getPersId(), "SK");
                if (!flAttc.isEmpty()) {
                    for (String v : flAttc) {
                        Vms022VoFileAttachment dtAttc = new Vms022VoFileAttachment();
 
                        byte[] bFileVac = readBytesFromFile(pathServer + v);
                        dtAttc.setName(Base64.getEncoder().encodeToString(bFileVac));
 
                        listAttcs.add(dtAttc);
                    }
                    vo.setFileSk(listAttcs);
                }
            }
        } catch (Exception e) {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, "GAGAL", null, list, count);
        }
 
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, list, count);
    }
 
    @Override
    public DtoResponsePagingWorkspace getExcel(DtoParamPaging dto) {
 
        String nrp = AhmStringUtil.hasValue(dto.getSearch().get("userid")) ? (dto.getSearch().get("userid") + "").toUpperCase() : "";
        String roleFromFront = AhmStringUtil.hasValue(dto.getSearch().get("role")) ? (dto.getSearch().get("role") + "").toUpperCase() : "";
 
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getSearchData(dto, nrp, roleFromFront, nrp);
        int count = vms022ahmhrntmHdrotsempsDao.countSearchData(dto, nrp, roleFromFront, nrp);
 
        for (Vms022VoMonitoring vo : list) {
            String getGateList = vms022ahmhrntmDtlprmgblsDao.getGateForExcel(vo.getOutId(), vo.getPersId());
            vo.setGateName(getGateList);
 
            String getPlantList = vms022ahmhrntmDtlprmgblsDao.getPlantForExcel(vo.getOutId(), vo.getPersId());
            vo.setAreaName(getPlantList);
 
            String getPlantIDList = vms022ahmhrntmDtlprmgblsDao.getPlantIDForExcel(vo.getOutId(), vo.getPersId());
            vo.setArea(getPlantIDList);
 
            String getPicList = vms022AhmhrntmMstpicotsDao.getPicAhmForExcel(vo.getOutType(), vo.getArea());
            vo.setPic(getPicList);
        }
 
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, list, count);
    }
 
    private byte[] readBytesFromFile(String pathFile) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            File file = new File(pathFile);
            bytesArray = new byte[(int) file.length()];
 
            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
 
        } catch (IOException e) {
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
            }
 
        }
        return bytesArray;
    }
 
    @Override
    public DtoResponseWorkspace approve(Vms022VoMonitoring getdata, VoUserCred userCred) {
 
        UUID uuidWF = UUID.randomUUID();
        UUID uuidHist = UUID.randomUUID();
        String idWF = uuidWF.toString();
        String idHist = uuidHist.toString();
 
        if (getdata.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM") || getdata.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")) {
            try {
 
                String validateId = vms022ahmhrntmHdrotsempsDao.confirmId(getdata.getOutId(), Vms022Constant.STATUS_WAITING_FOR_PIC);
 
                AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                pk.setRotsempshs((getdata.getId()));
 
                AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                if (mp != null) {
                    if (getdata.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM")) {
 
                        if (!validateId.equalsIgnoreCase(getdata.getOutId())) {
                            returnFailed("This role only can process data with status 'Waiting for Approval PIC'");
                        } else {
                            mp.setVotsstts(getdata.getOutStatus());
 
                            mp.setLastModBy(userCred.getUserid());
                            vms022ahmhrntmHdrotsempsDao.update(mp);
                            vms022ahmhrntmHdrotsempsDao.flush();
                        }
 
                    } else if (getdata.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")) {
 
                        mp.setVotsstts(getdata.getOutStatus());
                        mp.setDpassexp(DateUtil.stringToDate(getdata.getPassExpiryDateText(), "dd-MMM-yyyy"));
                        mp.setDstatus(DateUtil.stringToDate(getdata.getDateStatus(), "dd-MM-yyyy"));
                        mp.setLastModBy(userCred.getUserid());
 
                        String validateWO = vms022ahmhrntmTxnidrepsDao.validateWorkOrder(getdata.getOutId());
 
                        String getNseq = vms022ahmhrntmTxnidrepsDao.getNseq();
 
                        int nseq;
                        Date datenow = new Date();
                        int yearnow = datenow.getYear() + 1900;
                        int monthnow = datenow.getMonth() + 1;
 
                        String vNseq;
 
                        if (monthnow < 10) {
                            vNseq = "OTR/" + yearnow + "/0" + monthnow + "/";
                        } else {
                            vNseq = "OTR/" + yearnow + "/" + monthnow + "/";
                        }
 
                        if (getNseq == null) {
                            nseq = 1;
                        } else {
                            nseq = Integer.valueOf(getNseq) + 1;
                        }
 
                        if (nseq < 10) {
                            vNseq += "00000" + nseq;
                        } else if (nseq < 100) {
                            vNseq += "0000" + nseq;
                        } else if (nseq < 1000) {
                            vNseq += "000" + nseq;
                        } else if (nseq < 10000) {
                            vNseq += "00" + nseq;
                        } else if (nseq < 100000) {
                            vNseq += "0" + nseq;
                        } else if (nseq < 1000000) {
                            vNseq += String.valueOf(nseq);
                        }
                        
                        String trimOutName = getdata.getOutName();
                        
                        if(trimOutName.length() > 18) {
                            trimOutName = trimOutName.substring(0, 17);
                        }
 
                        AhmhrntmTxnidreps vo = new AhmhrntmTxnidreps();
                        vo.setVwrkorderno(vNseq);
                        vo.setVnrp(getdata.getOutId());
                        vo.setNreasonrep(BigDecimal.valueOf(3));
                        vo.setNclaimemp(BigDecimal.ZERO);
                        vo.setVremark(getdata.getNote());
                        vo.setVstatus("WAITING");
                        vo.setVpckupsts("NOTDONE");
                        vo.setVcardname(trimOutName);
                        vo.setCreateBy(userCred.getUserid());
 
//comment because still of disscussion
                        //start
                        vo.setVwflowid(idWF);
                        Boolean get = vms022AhmitwfsMstwfdochistDao.generateHistory(vNseq, userCred.getUsername(), getdata.getOutId(), idWF, idHist);
                        if (get == false) {
                            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data"), null, null);
                        }
                        //end
                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();
                        if (validateWO.isEmpty()) {
                            vms022ahmhrntmTxnidrepsDao.save(vo);
                            vms022ahmhrntmTxnidrepsDao.flush();
                        }
 
                    }
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, ("Approve success"), null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Map<String, Object> msg = new HashMap<>();
                msg.put("e", e.getMessage());
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data"), null, msg);
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data"), null, null);
        } else {
            throw new Vms022Exception("Role Not Exist!");
        }
    }
    
    public void startWorkflow(String wfID, String WorkOrder, String nrp, VoUserCred user, String idHist) {
        Map<String, Object> mapWorkFlow = new HashMap<>();
        mapWorkFlow.put("@AHMHRNTM046Originator", user.getUsername());
        
        VoWfsParam voWorkflow = new VoWfsParam();
        voWorkflow.setWfid(Vms022Constant.workflowId);
        voWorkflow.setDocid(idHist);
        voWorkflow.setDesc(Vms022Constant.descWorkflow);
        voWorkflow.setVariables(mapWorkFlow);

        wfService.startWorkflow(Vms022Constant.moduleId, Vms022Constant.appId, voWorkflow, user);
    }
 
    @Override
    public DtoResponseWorkspace approving(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {
 
        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                if (vo.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM") || vo.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")) {
 
                    String validateId = vms022ahmhrntmHdrotsempsDao.confirmId(vo.getOutId(), Vms022Constant.STATUS_WAITING_FOR_PIC);
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        if (vo.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM")) {
 
                            if (!validateId.equalsIgnoreCase(vo.getOutId())) {
                                returnFailed("This role only can process data with status 'Waiting for Approval PIC'");
                            } else {
                                mp.setVotsstts(vo.getOutStatus());
                                mp.setLastModBy(userCred.getUserid());
                                vms022ahmhrntmHdrotsempsDao.update(mp);
                                vms022ahmhrntmHdrotsempsDao.flush();
                            }
 
                        } else {
                            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data! This Role cannot do this action!"), null, null);
                        }
                    }
                } else {
                    throw new Vms022Exception("Role Not Exist!");
                }
 
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Approve success", null, null);
        } else {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "Failed Approve data", null, null);
        }
    }
 
    @Override
    public DtoResponseWorkspace reject(Vms022VoMonitoring getdata, VoUserCred userCred) {
        if (getdata.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM") || getdata.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")) {
            try {
 
//                String validateId = vms022ahmhrntmHdrotsempsDao.confirmId(getdata.getOutId(), Vms022Constant.STATUS_WAITING_FOR_PIC);
                AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                pk.setRotsempshs((getdata.getId()));
                AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                if (mp != null) {
                    mp.setVotsstts(getdata.getOutStatus());
                    mp.setVnoterejc(getdata.getReasonReject());
                    mp.setDstatus(DateUtil.stringToDate(getdata.getDateStatus(), "dd-MM-yyyy"));
                    mp.setLastModBy(userCred.getUserid());
 
//                    if (!validateId.equalsIgnoreCase(getdata.getOutId())) {
//                        returnFailed("This role only can process data with status 'Waiting for Approval PIC'");
//                    } else {
                    vms022ahmhrntmHdrotsempsDao.update(mp);
                    vms022ahmhrntmHdrotsempsDao.flush();
//                    }
                }
            } catch (Exception e) {
                throw new Vms022Exception("Failed Reject Data Cause error when Updating");
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Reject success", null, null);
        } else {
            throw new Vms022Exception("Role Not Exist!");
        }
 
    }
 
    @Override
    public DtoResponseWorkspace rejecting(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {
        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                if (vo.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM")) {
 
//                    String validateId = vms022ahmhrntmHdrotsempsDao.confirmId(vo.getOutId(), Vms022Constant.STATUS_WAITING_FOR_PIC);
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts(vo.getOutStatus());
                        mp.setVnoterejc(vo.getReasonReject());
                        mp.setLastModBy(userCred.getUserid());
                        mp.setDstatus(DateUtil.stringToDate(vo.getDateStatus(), "dd-MM-yyyy"));
 
//                        if (!validateId.equalsIgnoreCase(vo.getOutId())) {
//                            returnFailed("This role only can process data with status 'Waiting for Approval PIC'");
//                        } else {
                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();
//                        }
                    }
                } else {
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Reject data! This Role cannot do this action!"), null, null);
                }
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Reject success", null, null);
        } else {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "Failed Reject data", null, null);
        }
    }
 
    @Override
    public DtoResponseWorkspace showPlant(Vms022VoLov input) {
        List<Vms022VoLov> Plant = vms022ahmhrntmDtlprmgblsDao.getPlant(input.getId(), input.getCode());
 
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, Plant, 1);
    }
 
    @Override
    public DtoResponseWorkspace showGate(Vms022VoLov input) {
        List<Vms022VoLov> Gate = vms022ahmhrntmDtlprmgblsDao.getGate(input.getId(), input.getCode());
 
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, Gate, 1);
    }
 
    @Override
    public DtoResponseWorkspace showPicAhm(Vms022VoLov input) {
        List<Vms022VoLov> Pic = vms022AhmhrntmMstpicotsDao.getPicAhm(input.getCode(), input.getArea());
 
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, Pic, 1);
    }
 
    @Override
    public DtoResponseWorkspace checkingDate(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {
        DtoResponseWorkspace dto = new DtoResponseWorkspace();
        validateDate(getdata, dto);
        if (dto.getError_fields() != null) {
            if (dto.getError_fields().size() > 0) {
                dto.setStatus("1");
                return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.GAGAL, "FAILURE", dto.getError_fields(), null, 0);
            }
        } else {
            return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, null, 1);
        }
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, null, 1);
    }
 
    private void validateDate(List<Vms022VoMonitoring> vos, DtoResponseWorkspace dto) {
        Vms022ValidationDateUtil validation = new Vms022ValidationDateUtil();
        for (Vms022VoMonitoring vo : vos) {
 
            if (AhmStringUtil.hasValue(vo.getPassExpiryDateText()) && AhmStringUtil.hasValue(vo.getEndDateText())) {
                boolean cekTglBegin = validation.validateDateTgl(vo.getPassExpiryDateText());
                if (!cekTglBegin) {
                    VoMessageWorkspace err = new VoMessageWorkspace();
                    err.setF("");
                    err.setM("Format Begin Effective (dd-MMM-yyyy) ");
                    dto.addMessage(err);
                }
                boolean cekTglEnd = validation.validateDateTgl(vo.getEndDateText());
                if (!cekTglEnd) {
                    VoMessageWorkspace err = new VoMessageWorkspace();
                    err.setF("");
                    err.setM("Format End Effective (dd-MMM-yyyy) ");
                    dto.addMessage(err);
                }
 
                if (cekTglBegin && cekTglEnd) {
 
                    if (!checkIsAfter(DateUtil.stringToDate(vo.getEndDateText(), "dd-MMM-yyyy"), DateUtil.stringToDate(vo.getPassExpiryDateText(), "dd-MMM-yyyy"))) {
                        VoMessageWorkspace err = new VoMessageWorkspace();
                        err.setF("");
                        err.setM("End Work Effective Date must be greater than or equal to Expiry Date");
                        dto.addMessage(err);
                    }
                }
            }
        }
    }
 
    @Override
    public DtoResponse testing(DtoParamPaging dto) {
        Map<String, Object> msg = new HashMap<>();
 
        String test = AhmStringUtil.hasValue(dto.getSearch().get("test")) ? (dto.getSearch().get("test") + "").toUpperCase() : "";
 
        String coba = vms022ahmhrntmHdrotsempsDao.confirmId(test, Vms022Constant.STATUS_WAITING_FOR_PIC);
 
        msg.put("m", coba);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
    }
 
    private DtoResponseWorkspace returnFailed(String msg) {
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, msg, null, null);
    }
 
    @Override
    public Workbook exportData(DtoParamPaging dto) {
 
        String userId = AhmStringUtil.hasValue(dto.getSearch().get("userid")) ? (dto.getSearch().get("userid") + "").toUpperCase() : "";;
        String roleFromFront = AhmStringUtil.hasValue(dto.getSearch().get("role")) ? (dto.getSearch().get("role") + "").toUpperCase() : "";;
        String nrp = AhmStringUtil.hasValue(dto.getSearch().get("nrp")) ? (dto.getSearch().get("nrp") + "").toUpperCase() : "";;
 
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getSearchData(dto, userId, roleFromFront, nrp);
 
        XSSFRow rowDetail;
 
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet worksheet = workbook.createSheet();
        SXSSFCell[] listCellD = new SXSSFCell[24];
 
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 
        //Format Cell Bold
        CellStyle styleBold = workbook.createCellStyle();
        Font fontBold = workbook.createFont();
        styleBold.setFont(fontBold);
        fontBold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
 
        //Format Cell Date
        CellStyle styleDate = workbook.createCellStyle();
        XSSFCreationHelper creationHelper = (XSSFCreationHelper) workbook.getCreationHelper();
        styleDate.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
        styleDate.setAlignment(CellStyle.ALIGN_LEFT);
 
        //Format Cell Number
        XSSFCellStyle styleNum = (XSSFCellStyle) workbook.createCellStyle();
        styleNum.setDataFormat(creationHelper.createDataFormat().getFormat("#,##0"));
        styleNum.setAlignment(CellStyle.ALIGN_RIGHT);
 
        return workbook;
 
    }
 
    private void createCellHeader(XSSFWorkbook workbook, XSSFRow row, String obj, int col) {
        XSSFCellStyle styleTblHdr = workbook.createCellStyle();
        XSSFFont fontTblHdr = workbook.createFont();
        fontTblHdr.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        styleTblHdr.setFont(fontTblHdr);
        styleTblHdr.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//        styleTblHdr.setFillForegroundColor(XSSFColor.toXSSFColor(color));
//        styleTblHdr.setFillForegroundColor(XSSFColor.LIGHT_CORNFLOWER_BLUE.index);
 
        XSSFCell cellTblHdr = row.createCell(col);
        cellTblHdr.setCellStyle(styleTblHdr);
        cellTblHdr.setCellValue(obj);
 
    }
 
}