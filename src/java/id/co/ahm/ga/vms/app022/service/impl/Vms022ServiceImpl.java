/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app000.model.AhmhrntmTxnidreps;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmMstpicotsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmTxnidrepsDao;
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
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponsePagingWorkspace;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoMessageWorkspace;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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

    String ServiceUser;

    public final static String pathServer = "/data/deploy/upload/ahmgavms/Registration/";
//    public final static String pathServer = "D:\\Download\\";

    public final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    public final static LocalDateTime now = LocalDateTime.now();

    @Autowired
    @Qualifier("ahmitb2eMstusrrolesDao")
    private Ahmitb2eMstusrrolesDao ahmitb2eMstusrrolesDao;

    @Autowired
    @Qualifier("vms022ObjectDao")
    private Vms022ObjectDao vms022ObjectDao;
//
    @Autowired
    @Qualifier("vms022ahmhrntmDtlprmgblsDao")
    private Vms022AhmhrntmDtlprmgblsDao vms022ahmhrntmDtlprmgblsDao;
//
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
        ServiceUser = userCred.getUserid();
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getSearchData(dto, "");
//        int cont = list.size();
        int count = vms022ahmhrntmHdrotsempsDao.countSearchData(dto, "");
        try {

            for (Vms022VoMonitoring vo : list) {
                String getGateList = vms022ahmhrntmDtlprmgblsDao.getGateForExcel(vo.getOutId(), vo.getPersId());
                vo.setGateName(getGateList);

                boolean filterData = vms022AhmhrntmMstpicotsDao.isPicAvailable(userCred.getUserid(), vo.getArea(), vo.getOutType());

                if (filterData == false) {
                    list.remove(vo);
                    continue;
                }

                if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
                    List<Vms022VoLov> compNameList = vms022ObjectDao.lovCompExternal(dto, userCred.getUserid(), "FILTER");

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
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getSearchData(dto, "");
        int count = vms022ahmhrntmHdrotsempsDao.countSearchData(dto, "");

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

    @Override
    public DtoResponsePaging monitoring(DtoParamPaging input, VoUserCred userCred) {
        List<Vms022VoMonitoring> datas = new ArrayList<>();
        int count = 0;
        LinkedHashMap<String, Object> reqObj = (LinkedHashMap<String, Object>) input.getSearch();

        String outId = reqObj.get("outId") != null ? reqObj.get("outId").toString() : "";
        String outName = reqObj.get("outName") != null ? reqObj.get("outName").toString() : "";
        String outType = reqObj.get("outType") != null ? reqObj.get("outType").toString() : "";
        String beginDate = reqObj.get("beginDate") != null ? reqObj.get("beginDate").toString() : "";
        String endDate = reqObj.get("endDate") != null ? reqObj.get("endDate").toString() : "";
        String pic = reqObj.get("pic") != null ? reqObj.get("pic").toString() : "";
        String nik = reqObj.get("nik") != null ? reqObj.get("nik").toString() : "";
        String company = reqObj.get("company") != null ? reqObj.get("company").toString() : "";
        String outStatus = reqObj.get("outStatus") != null ? reqObj.get("outStatus").toString() : "";
        String passNumber = reqObj.get("passNumber") != null ? reqObj.get("passNumber").toString() : "";

        if (StringUtils.isBlank(outId) && StringUtils.isBlank(outName) && StringUtils.isBlank(outType) && StringUtils.isBlank(beginDate)
                && StringUtils.isBlank(endDate) && StringUtils.isBlank(pic) && StringUtils.isBlank(nik) && StringUtils.isBlank(company)
                && StringUtils.isBlank(outStatus) && StringUtils.isBlank(passNumber)) {
            return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, datas, 0);
        } else {
            datas = vms022ahmhrntmHdrotsempsDao.getSearchData(input, userCred.getUserid());

            if (!datas.isEmpty()) {

                for (Vms022VoMonitoring vo : datas) {
                    String getGateList = vms022ahmhrntmDtlprmgblsDao.getGateForExcel(vo.getOutId(), vo.getPersId());
                    vo.setGateName(getGateList);

                    if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
                        List<Vms022VoLov> compNameList = vms022ObjectDao.lovCompExternal(input, userCred.getUserid(), "FILTER");

                        if (!compNameList.isEmpty()) {
                            vo.setCompanyName(compNameList.get(0).getName());
                        } else {
                            vo.setCompanyName("Company Code not found");
                        }
                    }
                    if (!StringUtils.isBlank(vo.getFileNameKtp())) {
                        byte[] bFileKtp = readBytesFromFile(pathServer + vo.getFileNameKtp());
                        vo.setFileKtp(Base64.getEncoder().encodeToString(bFileKtp));
                    }

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
                    List<String> flAttc = vms022ahmhrntmDtlotsregsDao.getFileName(vo.getOutId(), vo.getPersId(), "VC");
                    if (!flAttc.isEmpty()) {
                        for (String v : flVacs) {
                            Vms022VoFileAttachment dtVac = new Vms022VoFileAttachment();

                            byte[] bFileVac = readBytesFromFile(pathServer + v);
                            dtVac.setName(Base64.getEncoder().encodeToString(bFileVac));

                            listAttcs.add(dtVac);
                        }

                        vo.setFileVaccines(listAttcs);
                    }
                }
            }

            count = vms022ahmhrntmHdrotsempsDao.countSearchData(input, userCred.getUserid());

            return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, datas, count);
        }
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
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return bytesArray;
    }

    @Override
    public DtoResponseWorkspace approve(Vms022VoMonitoring getdata, VoUserCred userCred) {

        if (getdata.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM") || getdata.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")) {
            try {

                String validateId = vms022ahmhrntmHdrotsempsDao.confirmId(getdata.getOutId());

                String getName = vms022ahmhrntmHdrotsempsDao.getName(getdata.getOutId());
                String getNote = vms022ahmhrntmHdrotsempsDao.getNote(getdata.getOutId());

                AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                pk.setRotsempshs((getdata.getId()));

                AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                if (mp != null) {
                    if (getdata.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM")) {

                        if (!validateId.equalsIgnoreCase(getdata.getOutId())) {
                            throw new Vms022Exception("Cannot Process Outsource Status with data 'WAITING FOR APPROVAL SECURITY'");
                        } else {
                            mp.setVotsstts(getdata.getOutStatus());
                            vms022ahmhrntmHdrotsempsDao.update(mp);
                            vms022ahmhrntmHdrotsempsDao.flush();
                        }
                    } else if (getdata.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")) {
                        mp.setVotsstts(getdata.getOutStatus());
                        mp.setDpassexp(DateUtil.stringToDate(getdata.getPassExpiryDateText(), "dd-MMM-yyyy"));
                        mp.setDstatus(DateUtil.stringToDate(getdata.getDateStatus(), "dd-MM-yyyy"));

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

                        AhmhrntmTxnidreps vo = new AhmhrntmTxnidreps();
                        vo.setVwrkorderno(vNseq);
                        vo.setVnrp(getdata.getOutId());
                        vo.setNreasonrep(BigDecimal.valueOf(3));
                        vo.setNclaimemp(BigDecimal.ZERO);
                        vo.setVremark(getdata.getNote());
                        vo.setVstatus("WAITING");
                        vo.setVpckupsts("NOTDONE");
                        vo.setVcardname(getdata.getOutName());

                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();

                        vms022ahmhrntmTxnidrepsDao.save(vo);
                        vms022ahmhrntmTxnidrepsDao.flush();
                    }
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, ("Approve success"), null, null);
                }
            } catch (Exception e) {
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data"), null, null);
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data"), null, null);
        } else {
            throw new Vms022Exception("Role Not Exist!");
        }
    }

    @Override
    public DtoResponseWorkspace approving(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {

        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                if (vo.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM") || vo.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")) {

                    String validateId = vms022ahmhrntmHdrotsempsDao.confirmId(vo.getOutId());

//                    String getName = vms022ahmhrntmHdrotsempsDao.getName(vo.getOutId());
//                    String getNote = vms022ahmhrntmHdrotsempsDao.getNote(vo.getOutId());
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        if (vo.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM")) {

                            if (!validateId.equalsIgnoreCase(vo.getOutId())) {
                                throw new Vms022Exception("Cannot Process Outsource Status with data 'WAITING FOR APPROVAL SECURITY'");
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
                AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                pk.setRotsempshs((getdata.getId()));
                AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                if (mp != null) {
                    mp.setVotsstts(getdata.getOutStatus());
                    mp.setVnoterejc(getdata.getReasonReject());
                    vms022ahmhrntmHdrotsempsDao.update(mp);
                    vms022ahmhrntmHdrotsempsDao.flush();
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
                if (vo.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM") //                        || vo.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT")
                        ) {
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts(vo.getOutStatus());
                        mp.setVnoterejc(vo.getReasonReject());
                        mp.setLastModBy(userCred.getUserid());
                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();
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
                        err.setM("End Date Effective must be greater than Expiry Date! ");
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

        String coba = vms022ahmhrntmHdrotsempsDao.confirmId(test);

        msg.put("m", coba);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
    }
}
