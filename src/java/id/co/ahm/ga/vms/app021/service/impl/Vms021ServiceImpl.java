/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.service.impl;

import com.ibm.icu.util.Calendar;
import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregs;
import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregsPk;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmMstuprnums;
import id.co.ahm.ga.vms.app000.model.AhmhrntmMstuprnumsPk;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmMstpicotsDao;
import id.co.ahm.ga.vms.app021.dao.Vms021ObjectDao;
import id.co.ahm.ga.vms.app021.rest.view.Vms021DownloadTemplateView;
import id.co.ahm.ga.vms.app021.service.Vms021Service;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLov;
import id.co.ahm.ga.vms.app021.vo.Vms021VoMonitoring;
import id.co.ahm.ga.vms.app021.vo.Vms021VoSubmit;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoPstUserCred;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import id.co.ahm.ga.vms.app021.dao.AhmhrntmMstuprnumsDao;
import id.co.ahm.ga.vms.app021.exception.Vms021Exception;
import id.co.ahm.ga.vms.app021.exception.Vms021UploadException;
import id.co.ahm.ga.vms.app021.vo.Vms021VoFileAttachment;
import id.co.ahm.ga.vms.app021.vo.Vms021VoLovNrp;
import id.co.ahm.jxf.util.DateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ayik.op
 */
@Transactional(readOnly = true)
@Service(value = "vms021Service")
public class Vms021ServiceImpl implements Vms021Service {

    public final static String pathServer = "/data/AHMGA/VMS/Registrasi/";
    //public final static String pathServer = "D:\\Download\\";

    @Autowired
    @Qualifier("vms021ObjectDao")
    private Vms021ObjectDao vms021ObjectDao;

    @Autowired
    @Qualifier("ahmhrntmDtlprmgblsDao")
    private AhmhrntmDtlprmgblsDao ahmhrntmDtlprmgblsDao;

    @Autowired
    @Qualifier("ahmhrntmMstpicotsDao")
    private AhmhrntmMstpicotsDao ahmhrntmMstpicotsDao;

    @Autowired
    @Qualifier("ahmhrntmHdrotsempsDao")
    private AhmhrntmHdrotsempsDao ahmhrntmHdrotsempsDao;

    @Autowired
    @Qualifier("ahmhrntmMstuprnumsDao")
    private AhmhrntmMstuprnumsDao ahmhrntmMstuprnumsDao;

    @Autowired
    @Qualifier("ahmhrntmDtlotsregsDao")
    private AhmhrntmDtlotsregsDao ahmhrntmDtlotsregsDao;

    @Override
    public DtoResponseWorkspace showPlant() {
        List<Vms021VoLov> list = ahmhrntmDtlprmgblsDao.lovPlant(null, true);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, list);
    }

    @Override
    public DtoResponseWorkspace showVacType() {
        List<Vms021VoLov> list = ahmhrntmDtlprmgblsDao.lovVaccine("VACTYPE");
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, list);
    }

    @Override
    public DtoResponsePaging lovPlant(DtoParamPaging input) {
        int total = ahmhrntmDtlprmgblsDao.countLovPlant(input);
        List<Vms021VoLov> list = ahmhrntmDtlprmgblsDao.lovPlant(input, false);

        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
    }

    @Override
    public DtoResponsePaging lovGate(DtoParamPaging input) {
        int total = ahmhrntmDtlprmgblsDao.countLovGate(input);
        List<Vms021VoLov> list = ahmhrntmDtlprmgblsDao.lovGate(input);

        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
    }

    @Override
    public DtoResponsePaging showNrp(DtoParamPaging input) {
        int total = ahmhrntmMstpicotsDao.countLovNrp(input);
        List<Vms021VoLov> list = ahmhrntmMstpicotsDao.lovNrp(input);

        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
    }

    @Override
    public DtoResponsePaging showOutsource(DtoParamPaging input, VoPstUserCred user) {
        int total = 0;
        List<Vms021VoLov> list = new ArrayList<>();
        LinkedHashMap<String, Object> reqObj = (LinkedHashMap<String, Object>) input.getSearch();

        String type = reqObj.get("type") != null ? reqObj.get("type").toString() : "";

        if (StringUtils.isBlank(type)) {
            if ("AHM".equalsIgnoreCase(user.getType())) {
                total = ahmhrntmDtlprmgblsDao.countLovOutsource(input, "INT", user.getUserid());
                list = ahmhrntmDtlprmgblsDao.lovOutsource(input, "INT", user.getUserid());
            } else {
                total = ahmhrntmDtlprmgblsDao.countLovOutsource(input, "EXT", null);
                list = ahmhrntmDtlprmgblsDao.lovOutsource(input, "EXT", null);
            }
        } else {
            total = ahmhrntmDtlprmgblsDao.countLovOutsource(input, "FILTER", null);
            list = ahmhrntmDtlprmgblsDao.lovOutsource(input, "FILTER", null);
        }

        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
    }

    @Override
    public DtoResponsePaging showCompany(DtoParamPaging input, VoPstUserCred user) {
        int total = 0;
        List<Vms021VoLov> list = new ArrayList<>();

        if ("AHM".equalsIgnoreCase(user.getType())) {
            total = ahmhrntmDtlprmgblsDao.countLovCompInternal(input);
            list = ahmhrntmDtlprmgblsDao.lovCompInternal(input);
        } else if ("V".equalsIgnoreCase(user.getType())) {
            total = vms021ObjectDao.countLovCompExternal(input, user.getPartnerid(), "FILTER");
            list = vms021ObjectDao.lovCompExternal(input, user.getPartnerid(), "FILTER");

        }

        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
    }

    @Override
    public DtoResponsePaging monitoring(DtoParamPaging input, VoPstUserCred userCred) {
        List<Vms021VoMonitoring> datas = new ArrayList<>();
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
            datas = ahmhrntmHdrotsempsDao.getMonitoring(input, userCred.getUserid(), null);

            if (!datas.isEmpty()) {
                for (Vms021VoMonitoring vo : datas) {
                    if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
                        List<Vms021VoLov> compNameList = vms021ObjectDao.lovCompExternal(input, userCred.getPartnerid(), "FILTER");

                        if (!compNameList.isEmpty()) {
                            vo.setCompanyName(compNameList.get(0).getName());
                        } else {
                            vo.setCompanyName("Company Code tidak ditemukan");
                        }
                    }
                    if (!StringUtils.isBlank(vo.getFileNameKtp())) {
                        byte[] bFileKtp = readBytesFromFile(pathServer + vo.getFileNameKtp());
                        vo.setFileKtp(Base64.getEncoder().encodeToString(bFileKtp));
                    }

                    List<Vms021VoFileAttachment> listVacs = new ArrayList<>();
                    List<String> flVacs = ahmhrntmDtlotsregsDao.getFileName(vo.getOutId(), vo.getPersId(), "VC");
                    if (!flVacs.isEmpty()) {
                        for (String v : flVacs) {
                            Vms021VoFileAttachment dtVac = new Vms021VoFileAttachment();

                            byte[] bFileVac = readBytesFromFile(pathServer + v);
                            dtVac.setName(Base64.getEncoder().encodeToString(bFileVac));

                            listVacs.add(dtVac);
                        }

                        vo.setFileVaccines(listVacs);
                    }

                    List<Vms021VoFileAttachment> listAttcs = new ArrayList<>();
                    List<String> flAttc = ahmhrntmDtlotsregsDao.getFileName(vo.getOutId(), vo.getPersId(), "VC");
                    if (!flAttc.isEmpty()) {
                        for (String v : flVacs) {
                            Vms021VoFileAttachment dtVac = new Vms021VoFileAttachment();

                            byte[] bFileVac = readBytesFromFile(pathServer + v);
                            dtVac.setName(Base64.getEncoder().encodeToString(bFileVac));

                            listAttcs.add(dtVac);
                        }

                        vo.setFileVaccines(listAttcs);
                    }
                }
            }

            count = ahmhrntmHdrotsempsDao.countMonitoring(input, userCred.getUserid());

            return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, datas, count);
        }

        //jika o-m
        //Cari list attch vaccine;
        //CAri list attch sk
    }

    @Override
    public ModelAndView downloadTemplate(VoPstUserCred userCred) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", userCred);
        return new ModelAndView(new Vms021DownloadTemplateView(),"param", model);
    }

    @Override
    public DtoResponse getExportData(DtoParamPaging input, VoPstUserCred userCred) {
        List<Vms021VoMonitoring> data = ahmhrntmHdrotsempsDao.getMonitoring(input, userCred.getUserid(), "EXPORT");

        if (!data.isEmpty()) {
            for (Vms021VoMonitoring vo : data) {
                String tempPlant = "";
                String tempGate = "";
                String tempPic = "";
                String tempAccess = "";

                if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
                    List<Vms021VoLov> compNameList = vms021ObjectDao.lovCompExternal(input, userCred.getPartnerid(), "FILTER");

                    if (!compNameList.isEmpty()) {
                        vo.setCompanyName(compNameList.get(0).getName());
                    } else {
                        vo.setCompanyName("Company Code tidak ditemukan");
                    }
                }

                List<String> pl = new ArrayList<>();
                List<Vms021VoLov> plants = ahmhrntmDtlprmgblsDao.getPlantsGates(vo.getOutId(), vo.getPersId(), "PLNT");
                if (!plants.isEmpty() && plants.size() > 1) {
                    for (Vms021VoLov p : plants) {
                        tempPlant += p.getName() + "; ";

                        pl.add(p.getId());
                    }

                    vo.setAreaName(tempPlant);
                } else {
                    if (!plants.isEmpty()) {
                        vo.setAreaName(plants.get(0).getName());
                        pl.add(plants.get(0).getId());
                    }

                }

                List<Vms021VoLov> gates = ahmhrntmDtlprmgblsDao.getPlantsGates(vo.getOutId(), vo.getPersId(), "");
                if (!gates.isEmpty() && gates.size() > 1) {
                    for (Vms021VoLov g : gates) {
                        tempGate += g.getName() + "; ";
                    }

                    vo.setGateName(tempGate);
                } else {
                    if (!gates.isEmpty()) {
                        vo.setGateName(gates.get(0).getName());
                    } else {
                        vo.setGateName("");
                    }

                }

                List<Vms021VoLovNrp> pics = ahmhrntmMstpicotsDao.getPicAhm(vo.getOutType(), pl);
                if (!pics.isEmpty() && pics.size() > 1) {
                    for (Vms021VoLovNrp g : pics) {
                        tempPic += g.getName() + "; ";
                    }

                    vo.setPic(tempPic);
                } else {
                    if (!pics.isEmpty()) {
                        vo.setPic(pics.get(0).getName());
                    } else {
                        vo.setPic("");
                    }

                }

                if ("Y".equalsIgnoreCase(vo.getAccessReader())) {
                    tempAccess += "Absence Reader;";
                }
                if ("Y".equalsIgnoreCase(vo.getCanteen())) {
                    tempAccess += "Canteen;";
                }
                if ("Y".equalsIgnoreCase(vo.getSecurityGate())) {
                    tempAccess += "Security Gate;";
                }
                vo.setAccess(tempAccess);
            }
        }
        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, data, data.size());
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace addItem(Vms021VoSubmit input, MultipartFile ktp, MultipartFile photo, List<MultipartFile> vacFiles, List<MultipartFile> attcFiles, VoPstUserCred user) {
        Date sysDate = new Date();
        String tempExp = null;
        Boolean isVaccine = false;
        //Validasi Ext / Int
        //UploadFile

        //checking length phone no, ktp
        //checking list plant gate
        //checking access
        if (StringUtils.isBlank(input.getBeginDate())) {
            throw new Vms021Exception("Begin Work Effective Date is required");
        } else {
            if (StringUtils.isBlank(input.getEndDate())) {
                throw new Vms021Exception("End Work Effective Date is required");
            } else {
                Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
                Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");

                if (begin.compareTo(end) > 0) {
                    throw new Vms021Exception("Begin Work Effective Date harus lebih kecil dari End Work Effective Date");
                }
            }
        }
        if (StringUtils.isBlank(input.getOutName())) {
            throw new Vms021Exception("Outsource Name is required");
        }
        if (StringUtils.isBlank(input.getPhoneNo())) {
            throw new Vms021Exception("Phone No is required");
        } else {
            //VALIDATE
        }
        if (StringUtils.isBlank(input.getNik())) {
            throw new Vms021Exception("NIK is required");
        } else {
            //VALIDATE
        }

        if (StringUtils.isBlank(input.getOutTypeId())) {
            throw new Vms021Exception("Outsource Type is required");
        } else {
            //VALIDATE 
        }
        if (StringUtils.isBlank(input.getCompanyId())) {
            throw new Vms021Exception("Company is required");
        } else {
            //VALIDATE
        }
        if (StringUtils.isBlank(input.getJob())) {
            throw new Vms021Exception("Job Detail is required");
        } else {
            //VALIDATE
        }
        if (input.getListPlants().isEmpty()) {
            throw new Vms021Exception("Plants is required");
        }
        if (input.getListGate().isEmpty()) {
            throw new Vms021Exception("Gates is required");
        }
        if (!StringUtils.isBlank(input.getVacStatus())) {
            if ("Already Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
                if (StringUtils.isBlank(input.getVacType())) {
                    throw new Vms021Exception("Vaccine Type is required");
                }
                if (StringUtils.isBlank(input.getSumVacStatus())) {
                    throw new Vms021Exception("Vaccine Summary Status is required");
                }
                if (StringUtils.isBlank(input.getVacDate())) {
                    throw new Vms021Exception("Last Vaccine Date is required");
                }
                if (StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
                    throw new Vms021Exception("Certificate Vaccine is required");
                } else {
                    isVaccine = true;
                }
            } else if ("Not Yet Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
                if (StringUtils.isBlank(input.getVacNotes())) {
                    throw new Vms021Exception("Note Vaccine is required");
                }
                if (StringUtils.isBlank(attcFiles.get(0).getOriginalFilename())) {
                    throw new Vms021Exception("Attachment is required");
                } else {
                    isVaccine = false;
                }
            } else {
                throw new Vms021Exception("Vaccine Status is invalid");
            }
        } else {
            throw new Vms021Exception("Last Vaccine Status is required");
        }

        if (input.getIsAccessAbsReader() == null && input.getIsAccessCanteen() == null && input.getIsAccessSecGate() == null) {
            throw new Vms021Exception("Access is required");
        } else {
            if (input.getIsAccessAbsReader() == false && input.getIsAccessCanteen() == false && input.getIsAccessSecGate() == false) {
                throw new Vms021Exception("Access belum dipilih");
            }
        }

        if (!StringUtils.isBlank(input.getSupplier())) {
            if (!"N".equalsIgnoreCase(input.getSupplier()) && !"S".equalsIgnoreCase(input.getSupplier())) {
                throw new Vms021Exception("Invalid Supplier");
            }
        } else {
            throw new Vms021Exception("Supplier is required");
        }

        if (StringUtils.isBlank(photo.getOriginalFilename())) {
            throw new Vms021Exception("File Photo is required");
        }

        if (StringUtils.isBlank(ktp.getOriginalFilename())) {
            throw new Vms021Exception("File Ktp is required");
        }

        if (StringUtils.isBlank(input.getOutId())) {
            List<AhmhrntmHdrotsemps> findNiks = ahmhrntmHdrotsempsDao.findByNIKs(input.getNik());
            if (findNiks.isEmpty()) {
                try {
                    String year = DateUtil.dateToString(sysDate, "yyyy");

                    BigDecimal runnumb = getNewValue("OTSID", new BigDecimal("0000"), user.getUserid());
                    String otsid = "3" + StringUtils.leftPad(String.valueOf(runnumb), 7, "0");

                    List<AhmhrntmHdrotsemps> findOtsId = ahmhrntmHdrotsempsDao.findByOtsId(otsid);
                    if (findOtsId.isEmpty()) {
                        UUID uuid = UUID.randomUUID();
                        String randomUUIDString = uuid.toString();

                        Random r = new Random();
                        int xx = r.nextInt();
                        String hexNumber = Integer.toHexString(xx);

                        AhmhrntmHdrotsemps vo = new AhmhrntmHdrotsemps();
                        vo.setVotsid(otsid);
                        vo.setVname(input.getOutName());
                        vo.setVempphone(input.getPhoneNo());
                        vo.setVpersid(input.getNik());
                        vo.setVphotoid(ktp.getOriginalFilename());
                        vo.setVotstype(input.getOutTypeId());
                        if ("AHM".equalsIgnoreCase(user.getType())) {
                            vo.setVrbsuplay(input.getSupplier());
                        } else {
                            vo.setVrbsuplay("S");
                        }
                        if ("AHM".equalsIgnoreCase(user.getType())) {
                            vo.setVcompany(input.getCompanyId());
                        } else {
                            vo.setVcompany(input.getCompanyName());
                        }

                        vo.setVjobdtl(input.getJob());
                        vo.setVabsrdr(input.getIsAccessAbsReader() == true ? "Y" : "N");
                        vo.setVcanteen(input.getIsAccessCanteen() == true ? "Y" : "N");
                        vo.setVsecgate(input.getIsAccessSecGate() == true ? "Y" : "N");
                        vo.setDbgneffdt(DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy"));
                        vo.setDendeffdt(DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy"));

                        Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
                        int beginYear = Integer.valueOf(DateUtil.dateToString(begin, "yyyy"));
                        Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");
                        int endYear = Integer.valueOf(DateUtil.dateToString(end, "yyyy"));
                        if (endYear > beginYear) {
                            tempExp = "31-Dec-" + beginYear;
                        } else {
                            tempExp = input.getEndDate();
                        }

                        vo.setDpassexp(DateUtil.stringToDate(tempExp, "dd-MMM-yyyy"));
                        vo.setVnote(input.getNote());
                        vo.setVphotoname(photo.getOriginalFilename());
                        vo.setBphoto(input.getbFileNamePhoto());
                        vo.setVvacstts(input.getVacStatus());
                        if ("Not Yet Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
                            vo.setVntvs(input.getVacNotes());
                        } else {
                            vo.setVvactype(input.getVacType());
                            vo.setDlastvac(DateUtil.stringToDate(input.getVacDate(), "dd-MMM-yyyy"));
                            vo.setVvacdtl(input.getSumVacStatus());
                        }

                        vo.setVappdisclm(input.getDisclaimer() == true ? "Y" : "N");
                        if ("AHM".equalsIgnoreCase(user.getType())) {
                            vo.setVotsstts("Waiting for Approval Security");
                        } else {
                            vo.setVotsstts("Waiting for Approval PIC");
                        }
                        vo.setCreateBy(user.getUserid());

                        ahmhrntmHdrotsempsDao.save(vo);
                        //ahmhrntmHdrotsempsDao.flush();

                        //copyFile foto dan ktp ke server
                        Vms021VoFileAttachment flKtp = uploadFileToServer(pathServer, ktp, null);
                        Vms021VoFileAttachment flPhoto = uploadFileToServer(pathServer, photo, null);

                        //Save Plants
                        int seqPlant = ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "PLNT");
                        for (Vms021VoLov plnt : input.getListPlants()) {
                            AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                            AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                            pk.setVotsid(otsid);
                            pk.setVregid("PLNT");
                            pk.setVpersid(input.getNik());
                            seqPlant = seqPlant + 1;
                            pk.setNseq(new BigDecimal(seqPlant));
                            vox.setAhmhrntmDtlotsregsPk(pk);
                            vox.setVplant(plnt.getId());
                            vox.setCreateBy(user.getUserid());

                            ahmhrntmDtlotsregsDao.save(vox);
                        }

                        //Save Gate
                        int seqGate = ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "GATE");
                        for (Vms021VoLov gate : input.getListGate()) {
                            AhmhrntmDtlotsregs gt = new AhmhrntmDtlotsregs();
                            AhmhrntmDtlotsregsPk gtPk = new AhmhrntmDtlotsregsPk();
                            gtPk.setVotsid(otsid);
                            gtPk.setVregid("GATE");
                            gtPk.setVpersid(input.getNik());
                            seqGate = seqGate + 1;
                            gtPk.setNseq(new BigDecimal(seqGate));
                            gt.setAhmhrntmDtlotsregsPk(gtPk);
                            gt.setVgate(gate.getId());
                            gt.setCreateBy(user.getUserid());

                            ahmhrntmDtlotsregsDao.save(gt);
                        }

                        //copy file vac and attch
                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
                            int seqVac = ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "VC");
                            for (MultipartFile vac : vacFiles) {
                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                seqVac = seqVac + 1;

                                String fileName = otsid + "_VC_" + input.getNik() + "_" + seqVac;
                                Vms021VoFileAttachment flVac = uploadFileToServer(pathServer, vac, fileName);

                                pk.setVotsid(otsid);
                                pk.setVregid("VC");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(seqVac));
                                vox.setAhmhrntmDtlotsregsPk(pk);
                                vox.setVfile(flVac.getName());
                                vox.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(vox);
                            }
                        }
                        if (!StringUtils.isBlank(attcFiles.get(0).getOriginalFilename())) {
                            int seqAtt = ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "SK");
                            for (MultipartFile attc : attcFiles) {
                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                seqAtt = seqAtt + 1;

                                String fileName = otsid + "_SK_" + input.getNik() + "_" + seqAtt;
                                Vms021VoFileAttachment flSk = uploadFileToServer(pathServer, attc, fileName);

                                pk.setVotsid(otsid);
                                pk.setVregid("SK");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(seqAtt));
                                vox.setAhmhrntmDtlotsregsPk(pk);
                                vox.setVfile(flSk.getName());
                                vox.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(vox);
                            }
                        }
                    } else {
                    }
                } catch (Exception e) {
                    throw new Vms021Exception("Failed to saved");
                }
            } else {
                throw new Vms021Exception("NIK [" + input.getNik() + "] dengan Nama [" + findNiks.get(0).getVname() + "] masih berstatus active di AHM.");
            }
        }

        DtoResponseWorkspace response = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        response.setMessage("Data sukses disimpan");
        return response;
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace updateItem(Vms021VoSubmit input, MultipartFile ktp, MultipartFile photo, List<MultipartFile> vacFiles, List<MultipartFile> attcFiles, VoPstUserCred user) {
        //Validasi Ext / Int
        Boolean isVaccine = false;
        String tempExp = null;
        if (StringUtils.isBlank(input.getId())) {
            throw new Vms021Exception("Data Invalid");
        }
        if (StringUtils.isBlank(input.getOutId())) {
            throw new Vms021Exception("Outsource ID invalid");
        }
        if (StringUtils.isBlank(input.getBeginDate())) {
            throw new Vms021Exception("Begin Work Effective Date is required");
        } else {
            if (StringUtils.isBlank(input.getEndDate())) {
                throw new Vms021Exception("End Work Effective Date is required");
            } else {
                Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
                Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");

                if (begin.compareTo(end) > 0) {
                    throw new Vms021Exception("Begin Work Effective Date harus lebih kecil dari End Work Effective Date");
                }
            }
        }
        if (StringUtils.isBlank(input.getOutName())) {
            throw new Vms021Exception("Outsource Name is required");
        }
        if (StringUtils.isBlank(input.getPhoneNo())) {
            throw new Vms021Exception("Phone No is required");
        } else {
            //VALIDATE
        }
        if (StringUtils.isBlank(input.getNik())) {
            throw new Vms021Exception("NIK is required");
        } else {
            //VALIDATE
        }

        if (StringUtils.isBlank(input.getOutTypeId())) {
            throw new Vms021Exception("Outsource Type is required");
        } else {
            //VALIDATE 
        }
        if (StringUtils.isBlank(input.getCompanyId())) {
            throw new Vms021Exception("Company is required");
        } else {
            //VALIDATE
        }
        if (StringUtils.isBlank(input.getJob())) {
            throw new Vms021Exception("Job Detail is required");
        } else {
            //VALIDATE
        }
        if (input.getListPlants().isEmpty()) {
            throw new Vms021Exception("Plants is required");
        }
        if (input.getListGate().isEmpty()) {
            throw new Vms021Exception("Gates is required");
        }
        if (!StringUtils.isBlank(input.getVacStatus())) {
            if ("Already Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
                if (StringUtils.isBlank(input.getVacType())) {
                    throw new Vms021Exception("Vaccinee Type is required");
                }
                if (StringUtils.isBlank(input.getSumVacStatus())) {
                    throw new Vms021Exception("Vaccinee Summary Status is required");
                }
                if (StringUtils.isBlank(input.getVacDate())) {
                    throw new Vms021Exception("Last Vaccinee Date is required");
                }
                if (StringUtils.isBlank(vacFiles.get(0).getOriginalFilename()) && input.getFileVacExist().isEmpty()) {
                    throw new Vms021Exception("Certificate Vaccine is required");
                } else {
                    isVaccine = true;
                }
            } else if ("Not Yet Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
                if (StringUtils.isBlank(input.getVacNotes())) {
                    throw new Vms021Exception("Note Vaccinee is required");
                }
                if (StringUtils.isBlank(attcFiles.get(0).getOriginalFilename()) && input.getFileDocExist().isEmpty()) {
                    throw new Vms021Exception("Attachment is required");
                } else {
                    isVaccine = false;
                }
            } else {
                throw new Vms021Exception("Vaccinee Status is invalid");
            }
        } else {
            throw new Vms021Exception("Last Vaccinee Status is required");
        }

        if (input.getIsAccessAbsReader() == null && input.getIsAccessCanteen() == null && input.getIsAccessSecGate() == null) {
            throw new Vms021Exception("Access is required");
        } else {
            if (input.getIsAccessAbsReader() == false && input.getIsAccessCanteen() == false && input.getIsAccessSecGate() == false) {
                throw new Vms021Exception("Access belum dipilih");
            }
        }

        if (!StringUtils.isBlank(input.getSupplier())) {
            if (!"N".equalsIgnoreCase(input.getSupplier()) && !"S".equalsIgnoreCase(input.getSupplier())) {
                throw new Vms021Exception("Invalid Supplier");
            }
        } else {
            throw new Vms021Exception("Supplier is required");
        }

        if (input.getIsNewPhoto() && StringUtils.isBlank(photo.getOriginalFilename())) {
            throw new Vms021Exception("File Photo is required");
        }

        if (input.getIsNewKtp() && StringUtils.isBlank(ktp.getOriginalFilename())) {
            throw new Vms021Exception("File Ktp is required");
        }

        List<AhmhrntmHdrotsemps> dt = ahmhrntmHdrotsempsDao.validateUpdate(input.getOutId(), input.getNik());
        if (dt.isEmpty()) {
            AhmhrntmHdrotsemps checks = ahmhrntmHdrotsempsDao.findOne(input.getId().toUpperCase());
            if (checks != null) {
                try {
                    checks.setVname(input.getOutName());
                    checks.setVempphone(input.getPhoneNo());
                    checks.setVpersid(input.getNik());
                    checks.setVotstype(input.getOutTypeId());
                    checks.setVrbsuplay(input.getSupplier());
                    checks.setVcompany(input.getCompanyId());
                    checks.setVjobdtl(input.getJob());
                    checks.setVabsrdr(input.getIsAccessAbsReader() == true ? "Y" : "N");
                    checks.setVcanteen(input.getIsAccessCanteen() == true ? "Y" : "N");
                    checks.setVsecgate(input.getIsAccessSecGate() == true ? "Y" : "N");
                    checks.setDbgneffdt(DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy"));
                    checks.setDendeffdt(DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy"));

                    Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
                    int beginYear = Integer.valueOf(DateUtil.dateToString(begin, "yyyy"));
                    Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");
                    int endYear = Integer.valueOf(DateUtil.dateToString(end, "yyyy"));
                    if (endYear > beginYear) {
                        tempExp = "31-Dec-" + beginYear;

                    } else {
                        tempExp = input.getEndDate();
                    }

                    checks.setDpassexp(DateUtil.stringToDate(tempExp, "dd-MMM-yyyy"));
                    checks.setVnote(input.getNote());
                    checks.setVvacstts(input.getVacStatus());
                    checks.setVvactype(input.getVacType());
                    checks.setDlastvac(DateUtil.stringToDate(input.getVacDate(), "dd-MMM-yyyy"));
                    checks.setVvacdtl(input.getSumVacStatus());
                    checks.setVntvs(input.getVacNotes());

                    //copyFile foto dan ktp ke server
                    if (input.getIsNewPhoto()) {
                        checks.setVphotoname(photo.getOriginalFilename());
                        checks.setBphoto(input.getbFileNamePhoto());

                        Vms021VoFileAttachment flPhoto = uploadFileToServer(pathServer, photo, null);
                    }
                    if (input.getIsNewKtp()) {
                        checks.setVphotoid(ktp.getOriginalFilename());

                        Vms021VoFileAttachment flKtp = uploadFileToServer(pathServer, ktp, null);
                    }
                    checks.setLastModBy(user.getUserid());
                    ahmhrntmHdrotsempsDao.update(checks);

                    
                    if(input.getIsNewPersid()){
                        //get seq old with nik old
                        List<String> listSeq = ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "PLNT");
                        if(!listSeq.isEmpty()){
                            for(String s : listSeq){
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                pk.setVotsid(input.getOutId());
                                pk.setVregid("PLNT");
                                pk.setVpersid(input.getOldNik());
                                pk.setNseq(new BigDecimal(s));

                                ahmhrntmDtlotsregsDao.deleteById(pk);
                            }
                        }
                        
                        //save dgn nik baru
                        int seqPlant = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "PLNT");
                        for (Vms021VoLov plnt : input.getListPlants()) {
                            AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                            AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                            pk.setVotsid(input.getOutId());
                            pk.setVregid("PLNT");
                            pk.setVpersid(input.getNik());
                            seqPlant = seqPlant + 1;
                            pk.setNseq(new BigDecimal(seqPlant));
                            vox.setAhmhrntmDtlotsregsPk(pk);
                            vox.setVplant(plnt.getId());
                            vox.setCreateBy(user.getUserid());

                            ahmhrntmDtlotsregsDao.save(vox);
                        }
                    } else {
                        //Save Only New Plants
                        List<Vms021VoLov> temps = new ArrayList<>();
                        int seqPlant = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "PLNT");
                        for (Vms021VoLov plnt : input.getListPlants()) {
                            if ("N".equalsIgnoreCase(plnt.getStat())) {
                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                pk.setVotsid(input.getOutId());
                                pk.setVregid("PLNT");
                                pk.setVpersid(input.getNik());
                                seqPlant = seqPlant + 1;
                                pk.setNseq(new BigDecimal(seqPlant));
                                vox.setAhmhrntmDtlotsregsPk(pk);
                                vox.setVplant(plnt.getId());
                                vox.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(vox);
                            }
                        }
                        //Delete Plants
                        if (!input.getListPlantDelete().isEmpty()) {
                            for (String s : input.getListPlantDelete()) {
                                String[] idx = s.split("_");
                                String tempSeq = idx[1];

                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                pk.setVotsid(input.getOutId());
                                pk.setVregid("PLNT");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(tempSeq));

                                ahmhrntmDtlotsregsDao.deleteById(pk);
                            }
                        }
                    }
                    

                    if (input.getIsNewPersid()) {
                        //get seq old with nik old
                        List<String> listSeq = ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "GATE");
                        if (!listSeq.isEmpty()) {
                            for (String s : listSeq) {
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                pk.setVotsid(input.getOutId());
                                pk.setVregid("GATE");
                                pk.setVpersid(input.getOldNik());
                                pk.setNseq(new BigDecimal(s));

                                ahmhrntmDtlotsregsDao.deleteById(pk);
                            }
                        }

                        //save dgn nik baru
                        int seqGate = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "GATE");
                        for (Vms021VoLov gate : input.getListGate()) {
                            AhmhrntmDtlotsregs gt = new AhmhrntmDtlotsregs();
                            AhmhrntmDtlotsregsPk gtPk = new AhmhrntmDtlotsregsPk();
                            gtPk.setVotsid(input.getOutId());
                            gtPk.setVregid("GATE");
                            gtPk.setVpersid(input.getNik());
                            seqGate = seqGate + 1;
                            gtPk.setNseq(new BigDecimal(seqGate));
                            gt.setAhmhrntmDtlotsregsPk(gtPk);
                            gt.setVgate(gate.getId());
                            gt.setCreateBy(user.getUserid());

                            ahmhrntmDtlotsregsDao.save(gt);
                        }
                    } else {
                        //Save Only New Gate
                        int seqGate = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "GATE");
                        for (Vms021VoLov gate : input.getListGate()) {
                            if ("N".equalsIgnoreCase(gate.getStat())) {
                                AhmhrntmDtlotsregs gt = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk gtPk = new AhmhrntmDtlotsregsPk();
                                gtPk.setVotsid(input.getOutId());
                                gtPk.setVregid("GATE");
                                gtPk.setVpersid(input.getNik());
                                seqGate = seqGate + 1;
                                gtPk.setNseq(new BigDecimal(seqGate));
                                gt.setAhmhrntmDtlotsregsPk(gtPk);
                                gt.setVgate(gate.getId());
                                gt.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(gt);
                            }
                        }
                        //Delete Gate
                        if (!input.getListGateDelete().isEmpty()) {
                            for (String s : input.getListGateDelete()) {
                                String[] idx = s.split("_");
                                String tempSeq = idx[1];

                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                pk.setVotsid(input.getOutId());
                                pk.setVregid("GATE");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(tempSeq));

                                ahmhrntmDtlotsregsDao.deleteById(pk);
                            }
                        }
                    }
                    
                        //ganti nik
                        //upload ulang
                        //delete
                        

                    if (input.getIsNewPersid()) {
                        //delete dulu data lama
                        if (!input.getFileVacDeletes().isEmpty()) {
                            for (String s : input.getFileVacDeletes()) {
                                String[] tempFilename = s.split("_");
                                String tempSeq = tempFilename[3];
                                String[] seqs = tempSeq.split("\\.");
                                String seq = seqs[0];

                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
                                pkDelete.setVotsid(input.getOutId());
                                pkDelete.setVregid("VC");
                                pkDelete.setVpersid(input.getOldNik());
                                pkDelete.setNseq(new BigDecimal(seq));

                                ahmhrntmDtlotsregsDao.deleteById(pkDelete);
                                ahmhrntmDtlotsregsDao.flush();
                            }
                        }
                        
                        //update data lama dgn new nik
                        List<String> listSeq = ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "VC");
                        if (!listSeq.isEmpty()) {
                            for (String s : listSeq) {
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                pk.setVotsid(input.getOutId());
                                pk.setVregid("VC");
                                pk.setVpersid(input.getOldNik());
                                pk.setNseq(new BigDecimal(s));
                                
                                AhmhrntmDtlotsregs find = ahmhrntmDtlotsregsDao.findOne(pk);
                                
                                AhmhrntmDtlotsregsPk newPk = new AhmhrntmDtlotsregsPk();
                                AhmhrntmDtlotsregs newVo = new AhmhrntmDtlotsregs();
                                newPk.setVotsid(input.getOutId());
                                newPk.setVregid("VC");
                                newPk.setVpersid(input.getNik());
                                newPk.setNseq(new BigDecimal(s));
                                newVo.setAhmhrntmDtlotsregsPk(newPk);
                                newVo.setVfile(find.getVfile());
                                ahmhrntmDtlotsregsDao.save(newVo);
                                
                                ahmhrntmDtlotsregsDao.deleteById(pk);
                            }
                        }
                        
                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
                            int seqVac = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "VC");
                            for (MultipartFile vac : vacFiles) {
                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                seqVac = seqVac + 1;

                                String fileName = input.getOutId() + "_VC_" + input.getNik() + "_" + seqVac;
                                Vms021VoFileAttachment flVac = uploadFileToServer(pathServer, vac, fileName);

                                pk.setVotsid(input.getOutId());
                                pk.setVregid("VC");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(seqVac));
                                vox.setAhmhrntmDtlotsregsPk(pk);
                                vox.setVfile(flVac.getName());
                                vox.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(vox);
                            }                            
                        } 
                    } else {
                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
                            int seqVac = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "VC");
                            for (MultipartFile vac : vacFiles) {
                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                seqVac = seqVac + 1;

                                String fileName = input.getOutId() + "_VC_" + input.getNik() + "_" + seqVac;
                                Vms021VoFileAttachment flVac = uploadFileToServer(pathServer, vac, fileName);

                                pk.setVotsid(input.getOutId());
                                pk.setVregid("VC");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(seqVac));
                                vox.setAhmhrntmDtlotsregsPk(pk);
                                vox.setVfile(flVac.getName());
                                vox.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(vox);
                            }
                        }
                        if (!input.getFileVacDeletes().isEmpty()) {
                            for (String s : input.getFileVacDeletes()) {
                                String[] tempFilename = s.split("_");
                                String tempSeq = tempFilename[3];
                                String[] seqs = tempSeq.split("\\.");
                                String seq = seqs[0];

                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
                                pkDelete.setVotsid(input.getOutId());
                                pkDelete.setVregid("VC");
                                pkDelete.setVpersid(input.getNik());
                                pkDelete.setNseq(new BigDecimal(seq));

                                ahmhrntmDtlotsregsDao.deleteById(pkDelete);
                            }
                        }
                    }
                    

                    if (input.getIsNewPersid()) {
                        //delete dulu data lama
                        if (!input.getFileDocDeletes().isEmpty()) {
                            for (String s : input.getFileDocDeletes()) {
                                String[] tempFilename = s.split("_");
                                String tempSeq = tempFilename[3];
                                String[] seqs = tempSeq.split("\\.");
                                String seq = seqs[0];

                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
                                pkDelete.setVotsid(input.getOutId());
                                pkDelete.setVregid("SK");
                                pkDelete.setVpersid(input.getOldNik());
                                pkDelete.setNseq(new BigDecimal(seq));

                                ahmhrntmDtlotsregsDao.deleteById(pkDelete);
                                ahmhrntmDtlotsregsDao.flush();
                            }
                        }

                        //update data lama dgn new nik
                        List<String> listSeq = ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "SK");
                        if (!listSeq.isEmpty()) {
                            for (String s : listSeq) {
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                pk.setVotsid(input.getOutId());
                                pk.setVregid("SK");
                                pk.setVpersid(input.getOldNik());
                                pk.setNseq(new BigDecimal(s));
                                
                                AhmhrntmDtlotsregs find = ahmhrntmDtlotsregsDao.findOne(pk);
                                
                                AhmhrntmDtlotsregsPk newPk = new AhmhrntmDtlotsregsPk();
                                AhmhrntmDtlotsregs newVo = new AhmhrntmDtlotsregs();
                                newPk.setVotsid(input.getOutId());
                                newPk.setVregid("VC");
                                newPk.setVpersid(input.getNik());
                                newPk.setNseq(new BigDecimal(s));
                                newVo.setAhmhrntmDtlotsregsPk(newPk);
                                newVo.setVfile(find.getVfile());
                                ahmhrntmDtlotsregsDao.save(newVo);

                                ahmhrntmDtlotsregsDao.deleteById(pk);
                            }
                        }
                        
                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
                            int seqAtt = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "SK");
                            for (MultipartFile attc : attcFiles) {
                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                seqAtt = seqAtt + 1;

                                String fileName = input.getOutId() + "_SK_" + input.getNik() + "_" + seqAtt;
                                Vms021VoFileAttachment flSk = uploadFileToServer(pathServer, attc, fileName);

                                pk.setVotsid(input.getOutId());
                                pk.setVregid("SK");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(seqAtt));
                                vox.setAhmhrntmDtlotsregsPk(pk);
                                vox.setVfile(flSk.getName());
                                vox.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(vox);
                            }
                        }
                    } else {
                        if (!StringUtils.isBlank(attcFiles.get(0).getOriginalFilename())) {
                            int seqAtt = ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "SK");
                            for (MultipartFile attc : attcFiles) {
                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
                                seqAtt = seqAtt + 1;

                                String fileName = input.getOutId() + "_SK_" + input.getNik() + "_" + seqAtt;
                                Vms021VoFileAttachment flSk = uploadFileToServer(pathServer, attc, fileName);

                                pk.setVotsid(input.getOutId());
                                pk.setVregid("SK");
                                pk.setVpersid(input.getNik());
                                pk.setNseq(new BigDecimal(seqAtt));
                                vox.setAhmhrntmDtlotsregsPk(pk);
                                vox.setVfile(flSk.getName());
                                vox.setCreateBy(user.getUserid());

                                ahmhrntmDtlotsregsDao.save(vox);
                            }
                        }
                        if (!input.getFileDocDeletes().isEmpty()) {
                            for (String s : input.getFileDocDeletes()) {
                                String[] tempFilename = s.split("_");
                                String tempSeq = tempFilename[3];
                                String[] seqs = tempSeq.split("\\.");
                                String seq = seqs[0];

                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
                                pkDelete.setVotsid(input.getOutId());
                                pkDelete.setVregid("SK");
                                pkDelete.setVpersid(input.getNik());
                                pkDelete.setNseq(new BigDecimal(seq));

                                ahmhrntmDtlotsregsDao.deleteById(pkDelete);
                            }
                        }
                    }
                    
                } catch (Exception e) {
                    throw new Vms021Exception("Failed to update");
                }
            } else {
                throw new Vms021Exception("Data not found");
            }
        } else {
            throw new Vms021Exception("NIK [" + input.getNik() + "] dengan Nama [" + dt.get(0).getVname() + "] masih berstatus active di AHM.");
        }

        DtoResponseWorkspace response = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, input);
        response.setMessage("Data sukses diubah");
        return response;
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponseWorkspace inActiveData(Vms021VoSubmit input, VoPstUserCred user) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date ys = cal.getTime();
        
        if(!input.getListInactiveData().isEmpty()){
            for(Vms021VoMonitoring vo : input.getListInactiveData()){
                if("Active".equalsIgnoreCase(vo.getOutStatus())){
                    AhmhrntmHdrotsemps dt = ahmhrntmHdrotsempsDao.findOne(vo.getId().toUpperCase());
                    dt.setVotsstts("Inactive");
                    dt.setDendeffdt(ys);
                    
                    ahmhrntmHdrotsempsDao.update(dt);
                }
            }
        }

        DtoResponseWorkspace response = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
        response.setMessage("Inactive Sukses");
        return response;
    }

    @Override
    @Transactional
    public BigDecimal getNewValue(String vidname, BigDecimal vreset, String user) {
        BigDecimal newValue;
        System.out.println("runnummmm: " + vidname + vreset);

        AhmhrntmMstuprnumsPk pk = new AhmhrntmMstuprnumsPk();
        pk.setVabstcode(vidname);
        pk.setNyear(vreset);
        AhmhrntmMstuprnums ahmhrntmMstuprnums = ahmhrntmMstuprnumsDao.findOne(pk);
        if (ahmhrntmMstuprnums == null) { // Insert new running number
            newValue = new BigDecimal(BigInteger.ONE);
            ahmhrntmMstuprnums = new AhmhrntmMstuprnums();
            ahmhrntmMstuprnums.setAhmhrntmMstuprnumsPk(pk);
            ahmhrntmMstuprnums.setNrunnumb(newValue);
            ahmhrntmMstuprnumsDao.save(ahmhrntmMstuprnums);
        } else { // Update existing running number
            newValue = ahmhrntmMstuprnums.getNrunnumb().add(new BigDecimal(BigInteger.ONE));
            ahmhrntmMstuprnums.setNrunnumb(newValue);
            ahmhrntmMstuprnumsDao.update(ahmhrntmMstuprnums);
        }

        return newValue;
    }

    @Override
    public DtoResponseWorkspace getPlantsGates(Vms021VoSubmit input) {
        Vms021VoSubmit result = new Vms021VoSubmit();
        List<Vms021VoLov> tempPlants = new ArrayList<>();
        List<Vms021VoLov> tempGates = new ArrayList<>();

        tempPlants = ahmhrntmDtlprmgblsDao.getPlantsGates(input.getOutId(), input.getNik(), "PLNT");
        tempGates = ahmhrntmDtlprmgblsDao.getPlantsGates(input.getOutId(), input.getNik(), "");

        if (!tempPlants.isEmpty()) {
            tempPlants.sort(Comparator.comparing(Vms021VoLov::getSeq));
        }
        if (!tempGates.isEmpty()) {
            tempGates.sort(Comparator.comparing(Vms021VoLov::getSeq));
        }

        result.setOutId(input.getOutId());
        result.setNik(input.getNik());
        result.setListPlants(tempPlants);
        result.setListGate(tempGates);

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }

    @Override
    public DtoResponseWorkspace getPicAhm(Vms021VoMonitoring input) {
        List<Vms021VoLovNrp> result = new ArrayList<>();
        List<String> plants = new ArrayList<>();

        if (!input.getPlants().isEmpty() && !StringUtils.isBlank(input.getOutType())) {
            for (Vms021VoLov v : input.getPlants()) {

                plants.add(v.getId());
            }

            result = ahmhrntmMstpicotsDao.getPicAhm(input.getOutType(), plants);
        }

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }

    @Override
    public DtoResponseWorkspace getFiles(Vms021VoSubmit input) {
        Vms021VoMonitoring result = new Vms021VoMonitoring();

        List<Vms021VoFileAttachment> listVacs = new ArrayList<>();
        List<String> flVacs = ahmhrntmDtlotsregsDao.getFileName(input.getOutId(), input.getNik(), "VC");
        if (!flVacs.isEmpty()) {
            for (String v : flVacs) {
                Vms021VoFileAttachment dtVac = new Vms021VoFileAttachment();

                byte[] bFileVac = readBytesFromFile(pathServer + v);
                dtVac.setName(v);
                dtVac.setPath(Base64.getEncoder().encodeToString(bFileVac));

                listVacs.add(dtVac);
            }
        }
        result.setFileVaccines(listVacs);

        List<Vms021VoFileAttachment> listAttcs = new ArrayList<>();
        List<String> flAttc = ahmhrntmDtlotsregsDao.getFileName(input.getOutId(), input.getNik(), "SK");
        if (!flAttc.isEmpty()) {
            for (String v : flAttc) {
                Vms021VoFileAttachment dtAttc = new Vms021VoFileAttachment();

                byte[] bFileAttc = readBytesFromFile(pathServer + v);
                dtAttc.setName(v);
                dtAttc.setPath(Base64.getEncoder().encodeToString(bFileAttc));

                listAttcs.add(dtAttc);
            }
        }
        result.setFileSk(listAttcs);

        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }

    @Override
    @Transactional(readOnly = false)
    public Vms021VoFileAttachment uploadFileToServer(String pathServer, MultipartFile fileToUpload, String newFileName) {
        Vms021VoFileAttachment fileDtl = new Vms021VoFileAttachment();
        try {
            String fileName = "";
            if (StringUtils.isBlank(newFileName)) {
                fileName = fileToUpload.getOriginalFilename();
            } else {
                fileName = newFileName + "." + fileToUpload.getOriginalFilename().substring(fileToUpload.getOriginalFilename().lastIndexOf(".") + 1);
            }

            String fileType = fileToUpload.getContentType();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            String uploadFilePath = pathServer + fileName;

            File newFile = new File(uploadFilePath);
            fileToUpload.transferTo(newFile);

            fileDtl.setName(fileName);
            fileDtl.setPath(uploadFilePath);
            fileDtl.setType(fileType);
            fileDtl.setExt(fileExt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileDtl;
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
    public DtoResponseWorkspace upload(MultipartFile excel, MultipartFile ktp, MultipartFile photo, MultipartFile vacFiles, MultipartFile attcFiles, VoPstUserCred user) {
        Map<Integer, List<String>> errors = new HashMap<>();
        try {
            Workbook workbook = getWorkbook(excel);
            Sheet sheet = workbook.getSheetAt(0);

            //Get data every row
            //Validate Duplicate
            //Validate value blank
            //Validate Mandatory
            //Validate Valid Value
            
            //Save Data
            
            //Processing File Zip
            //Decompress
            //Checking File name - get nik 
            //Compare with data excel
            
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new Vms021UploadException("Data gagal diupload", mapToList(errors));
        } 
//        catch (ParseException ex) {
//            Logger.getLogger(Vms021ServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, "Data excel berhasil di upload.", null, null);
    }
    
    private Workbook getWorkbook(MultipartFile excel) throws IOException {
        if (excel == null) {
            throw new Vms021Exception("File yang diupload bukan format excel");
        }
        if (excel.getOriginalFilename().endsWith("xlsx")) {
            return new XSSFWorkbook(excel.getInputStream());            
        } else {
            throw new Vms021Exception("Ekstensi file yang diperbolehkan : xlsx");
        }
    }
    
    private List<String> mapToList(Map<?, List<String>> map) {
        List<String> list = new ArrayList<>();
        map.values().forEach(err -> list.addAll(err));
        return list;
    }
    
}
