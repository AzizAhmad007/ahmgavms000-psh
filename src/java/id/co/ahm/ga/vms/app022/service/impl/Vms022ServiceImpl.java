/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service.impl;

import com.ibm.icu.util.Calendar;
import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregs;
import id.co.ahm.ga.vms.app000.model.AhmhrntmDtlotsregsPk;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app000.model.AhmhrntmMstuprnums;
import id.co.ahm.ga.vms.app000.model.AhmhrntmMstuprnumsPk;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmMstpicotsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.rest.view.Vms022DownloadTemplateView;
import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.ga.vms.app022.vo.Vms022VoSubmit;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponsePaging;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoUserCred;
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
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmMstuprnumsDao;
import id.co.ahm.ga.vms.app022.exception.Vms022Exception;
import id.co.ahm.ga.vms.app022.exception.Vms022UploadException;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFileAttachment;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLovNrp;
import id.co.ahm.jxf.util.DateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author reza.mr
 */
@Transactional(readOnly = true)
@Service(value = "vms022Service")
public class Vms022ServiceImpl implements Vms022Service {

    public final static String pathServer = "/data/AHMGA/VMS/Registrasi/";
    //public final static String pathServer = "D:\\Download\\";

    @Autowired
    @Qualifier("vms022ObjectDao")
    private Vms022ObjectDao vms022ObjectDao;

    @Autowired
    @Qualifier("vms022ahmhrntmDtlprmgblsDao")
    private Vms022AhmhrntmDtlprmgblsDao vms022ahmhrntmDtlprmgblsDao;

    @Autowired
    @Qualifier("vms022ahmhrntmMstpicotsDao")
    private Vms022AhmhrntmMstpicotsDao vms022ahmhrntmMstpicotsDao;

    @Autowired
    @Qualifier("vms022ahmhrntmHdrotsempsDao")
    private Vms022AhmhrntmHdrotsempsDao vms022ahmhrntmHdrotsempsDao;

    @Autowired
    @Qualifier("vms022ahmhrntmMstuprnumsDao")
    private Vms022AhmhrntmMstuprnumsDao vms022ahmhrntmMstuprnumsDao;

    @Autowired
    @Qualifier("vms022ahmhrntmDtlotsregsDao")
    private Vms022AhmhrntmDtlotsregsDao vms022ahmhrntmDtlotsregsDao;

    @Override
    public DtoResponseWorkspace showPlant() {
        List<Vms022VoLov> list = vms022ahmhrntmDtlprmgblsDao.lovPlant(null, true);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, list);
    }

    @Override
    public DtoResponseWorkspace showVacType() {
        List<Vms022VoLov> list = vms022ahmhrntmDtlprmgblsDao.lovVaccine("VACTYPE");
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, list);
    }

    @Override
    public DtoResponsePaging lovPlant(DtoParamPaging input) {
        int total = vms022ahmhrntmDtlprmgblsDao.countLovPlant(input);
        List<Vms022VoLov> list = vms022ahmhrntmDtlprmgblsDao.lovPlant(input, false);

        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
    }

    @Override
    public DtoResponsePaging lovGate(DtoParamPaging input) {
        int total = vms022ahmhrntmDtlprmgblsDao.countLovGate(input);
        List<Vms022VoLov> list = vms022ahmhrntmDtlprmgblsDao.lovGate(input);

        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
    }

//    @Override
//    public DtoResponsePaging showNrp(DtoParamPaging input) {
//        int total = vms022ahmhrntmMstpicotsDao.countLovNrp(input);
//        List<Vms022VoLov> list = vms022ahmhrntmMstpicotsDao.lovNrp(input);
//
//        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
//    }

//    @Override
//    public DtoResponsePaging showOutsource(DtoParamPaging input, VoUserCred user) {
//        int total = 0;
//        List<Vms022VoLov> list = new ArrayList<>();
//        LinkedHashMap<String, Object> reqObj = (LinkedHashMap<String, Object>) input.getSearch();
//
//        String type = reqObj.get("type") != null ? reqObj.get("type").toString() : "";
//
//        if (StringUtils.isBlank(type)) {
//            if ("AHM".equalsIgnoreCase(user.getType())) {
//                total = vms022ahmhrntmDtlprmgblsDao.countLovOutsource(input, "INT", user.getUserid());
//                list = vms022ahmhrntmDtlprmgblsDao.lovOutsource(input, "INT", user.getUserid());
//            } else {
//                total = vms022ahmhrntmDtlprmgblsDao.countLovOutsource(input, "EXT", null);
//                list = vms022ahmhrntmDtlprmgblsDao.lovOutsource(input, "EXT", null);
//            }
//        } else {
//            total = vms022ahmhrntmDtlprmgblsDao.countLovOutsource(input, "FILTER", null);
//            list = vms022ahmhrntmDtlprmgblsDao.lovOutsource(input, "FILTER", null);
//        }
//
//        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
//    }

//    @Override
//    public DtoResponsePaging showCompany(DtoParamPaging input, VoUserCred user) {
//        int total = 0;
//        List<Vms022VoLov> list = new ArrayList<>();
//
//        if ("AHM".equalsIgnoreCase(user.getName())) {
//            total = vms022ahmhrntmDtlprmgblsDao.countLovCompInternal(input);
//            list = vms022ahmhrntmDtlprmgblsDao.lovCompInternal(input);
//        } else if ("V".equalsIgnoreCase(user.getName())) {
//            total = vms022ObjectDao.countLovCompExternal(input, user.getPartnerid(), "FILTER");
//            list = vms022ObjectDao.lovCompExternal(input, user.getPartnerid(), "FILTER");
//
//        }
//
//        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, list, total);
//    }

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
                    if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
                        List<Vms022VoLov> compNameList = vms022ObjectDao.lovCompExternal(input, userCred.getUserid(), "FILTER");

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
//    
//    @Override
//    public ModelAndView downloadTemplate(VoUserCred userCred) {
//        Map<String, Object> model = new HashMap<>();
//        model.put("user", userCred);
//        return new ModelAndView(new Vms022DownloadTemplateView(),"param", model);
//    }
//    
//    @Override
//    public DtoResponse getExportData(DtoParamPaging input, VoUserCred userCred) {
//        List<Vms022VoMonitoring> data = vms022ahmhrntmHdrotsempsDao.getMonitoring(input, userCred.getUserid(), "EXPORT");
//
//        if (!data.isEmpty()) {
//            for (Vms022VoMonitoring vo : data) {
//                String tempPlant = "";
//                String tempGate = "";
//                String tempPic = "";
//                String tempAccess = "";
//
//                if (StringUtils.isBlank(vo.getCompanyName()) && StringUtils.isBlank(vo.getCompany())) {
//                    List<Vms022VoLov> compNameList = vms022ObjectDao.lovCompExternal(input, userCred.getPartnerid(), "FILTER");
//
//                    if (!compNameList.isEmpty()) {
//                        vo.setCompanyName(compNameList.get(0).getName());
//                    } else {
//                        vo.setCompanyName("Company Code tidak ditemukan");
//                    }
//                }
//
//                List<String> pl = new ArrayList<>();
//                List<Vms022VoLov> plants = vms022ahmhrntmDtlprmgblsDao.getPlantsGates(vo.getOutId(), vo.getPersId(), "PLNT");
//                if (!plants.isEmpty() && plants.size() > 1) {
//                    for (Vms022VoLov p : plants) {
//                        tempPlant += p.getName() + "; ";
//
//                        pl.add(p.getId());
//                    }
//
//                    vo.setAreaName(tempPlant);
//                } else {
//                    if (!plants.isEmpty()) {
//                        vo.setAreaName(plants.get(0).getName());
//                        pl.add(plants.get(0).getId());
//                    }
//
//                }
//
//                List<Vms022VoLov> gates = vms022ahmhrntmDtlprmgblsDao.getPlantsGates(vo.getOutId(), vo.getPersId(), "");
//                if (!gates.isEmpty() && gates.size() > 1) {
//                    for (Vms022VoLov g : gates) {
//                        tempGate += g.getName() + "; ";
//                    }
//
//                    vo.setGateName(tempGate);
//                } else {
//                    if (!gates.isEmpty()) {
//                        vo.setGateName(gates.get(0).getName());
//                    } else {
//                        vo.setGateName("");
//                    }
//
//                }
//
//                List<Vms022VoLovNrp> pics = vms022ahmhrntmMstpicotsDao.getPicAhm(vo.getOutType(), pl);
//                if (!pics.isEmpty() && pics.size() > 1) {
//                    for (Vms022VoLovNrp g : pics) {
//                        tempPic += g.getName() + "; ";
//                    }
//
//                    vo.setPic(tempPic);
//                } else {
//                    if (!pics.isEmpty()) {
//                        vo.setPic(pics.get(0).getName());
//                    } else {
//                        vo.setPic("");
//                    }
//
//                }
//
//                if ("Y".equalsIgnoreCase(vo.getAccessReader())) {
//                    tempAccess += "Absence Reader;";
//                }
//                if ("Y".equalsIgnoreCase(vo.getCanteen())) {
//                    tempAccess += "Canteen;";
//                }
//                if ("Y".equalsIgnoreCase(vo.getSecurityGate())) {
//                    tempAccess += "Security Gate;";
//                }
//                vo.setAccess(tempAccess);
//            }
//        }
//        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, data, data.size());
//    }
//    
//    @Override
//    @Transactional(readOnly = false)
//    public DtoResponseWorkspace addItem(Vms022VoSubmit input, MultipartFile ktp, MultipartFile photo, List<MultipartFile> vacFiles, List<MultipartFile> attcFiles, VoUserCred user) {
//        Date sysDate = new Date();
//        String tempExp = null;
//        Boolean isVaccine = false;
//        //Validasi Ext / Int
//        //UploadFile
//
//        //checking length phone no, ktp
//        //checking list plant gate
//        //checking access
//        if (StringUtils.isBlank(input.getBeginDate())) {
//            throw new Vms022Exception("Begin Work Effective Date is required");
//        } else {
//            if (StringUtils.isBlank(input.getEndDate())) {
//                throw new Vms022Exception("End Work Effective Date is required");
//            } else {
//                Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
//                Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");
//
//                if (begin.compareTo(end) > 0) {
//                    throw new Vms022Exception("Begin Work Effective Date harus lebih kecil dari End Work Effective Date");
//                }
//            }
//        }
//        if (StringUtils.isBlank(input.getOutName())) {
//            throw new Vms022Exception("Outsource Name is required");
//        }
//        if (StringUtils.isBlank(input.getPhoneNo())) {
//            throw new Vms022Exception("Phone No is required");
//        } else {
//            //VALIDATE
//        }
//        if (StringUtils.isBlank(input.getNik())) {
//            throw new Vms022Exception("NIK is required");
//        } else {
//            //VALIDATE
//        }
//
//        if (StringUtils.isBlank(input.getOutTypeId())) {
//            throw new Vms022Exception("Outsource Type is required");
//        } else {
//            //VALIDATE 
//        }
//        if (StringUtils.isBlank(input.getCompanyId())) {
//            throw new Vms022Exception("Company is required");
//        } else {
//            //VALIDATE
//        }
//        if (StringUtils.isBlank(input.getJob())) {
//            throw new Vms022Exception("Job Detail is required");
//        } else {
//            //VALIDATE
//        }
//        if (input.getListPlants().isEmpty()) {
//            throw new Vms022Exception("Plants is required");
//        }
//        if (input.getListGate().isEmpty()) {
//            throw new Vms022Exception("Gates is required");
//        }
//        if (!StringUtils.isBlank(input.getVacStatus())) {
//            if ("Already Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
//                if (StringUtils.isBlank(input.getVacType())) {
//                    throw new Vms022Exception("Vaccine Type is required");
//                }
//                if (StringUtils.isBlank(input.getSumVacStatus())) {
//                    throw new Vms022Exception("Vaccine Summary Status is required");
//                }
//                if (StringUtils.isBlank(input.getVacDate())) {
//                    throw new Vms022Exception("Last Vaccine Date is required");
//                }
//                if (StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
//                    throw new Vms022Exception("Certificate Vaccine is required");
//                } else {
//                    isVaccine = true;
//                }
//            } else if ("Not Yet Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
//                if (StringUtils.isBlank(input.getVacNotes())) {
//                    throw new Vms022Exception("Note Vaccine is required");
//                }
//                if (StringUtils.isBlank(attcFiles.get(0).getOriginalFilename())) {
//                    throw new Vms022Exception("Attachment is required");
//                } else {
//                    isVaccine = false;
//                }
//            } else {
//                throw new Vms022Exception("Vaccine Status is invalid");
//            }
//        } else {
//            throw new Vms022Exception("Last Vaccine Status is required");
//        }
//
//        if (input.getIsAccessAbsReader() == null && input.getIsAccessCanteen() == null && input.getIsAccessSecGate() == null) {
//            throw new Vms022Exception("Access is required");
//        } else {
//            if (input.getIsAccessAbsReader() == false && input.getIsAccessCanteen() == false && input.getIsAccessSecGate() == false) {
//                throw new Vms022Exception("Access belum dipilih");
//            }
//        }
//
//        if (!StringUtils.isBlank(input.getSupplier())) {
//            if (!"N".equalsIgnoreCase(input.getSupplier()) && !"S".equalsIgnoreCase(input.getSupplier())) {
//                throw new Vms022Exception("Invalid Supplier");
//            }
//        } else {
//            throw new Vms022Exception("Supplier is required");
//        }
//
//        if (StringUtils.isBlank(photo.getOriginalFilename())) {
//            throw new Vms022Exception("File Photo is required");
//        }
//
//        if (StringUtils.isBlank(ktp.getOriginalFilename())) {
//            throw new Vms022Exception("File Ktp is required");
//        }
//
//        if (StringUtils.isBlank(input.getOutId())) {
//            List<AhmhrntmHdrotsemps> findNiks = vms022ahmhrntmHdrotsempsDao.findByNIKs(input.getNik());
//            if (findNiks.isEmpty()) {
//                try {
//                    String year = DateUtil.dateToString(sysDate, "yyyy");
//
//                    BigDecimal runnumb = getNewValue("OTSID", new BigDecimal("0000"), user.getUserid());
//                    String otsid = "3" + StringUtils.leftPad(String.valueOf(runnumb), 7, "0");
//
//                    List<AhmhrntmHdrotsemps> findOtsId = vms022ahmhrntmHdrotsempsDao.findByOtsId(otsid);
//                    if (findOtsId.isEmpty()) {
//                        UUID uuid = UUID.randomUUID();
//                        String randomUUIDString = uuid.toString();
//
//                        Random r = new Random();
//                        int xx = r.nextInt();
//                        String hexNumber = Integer.toHexString(xx);
//
//                        AhmhrntmHdrotsemps vo = new AhmhrntmHdrotsemps();
//                        vo.setVotsid(otsid);
//                        vo.setVname(input.getOutName());
//                        vo.setVempphone(input.getPhoneNo());
//                        vo.setVpersid(input.getNik());
//                        vo.setVphotoid(ktp.getOriginalFilename());
//                        vo.setVotstype(input.getOutTypeId());
//                        if ("AHM".equalsIgnoreCase(user.getType())) {
//                            vo.setVrbsuplay(input.getSupplier());
//                        } else {
//                            vo.setVrbsuplay("S");
//                        }
//                        if ("AHM".equalsIgnoreCase(user.getType())) {
//                            vo.setVcompany(input.getCompanyId());
//                        } else {
//                            vo.setVcompany(input.getCompanyName());
//                        }
//
//                        vo.setVjobdtl(input.getJob());
//                        vo.setVabsrdr(input.getIsAccessAbsReader() == true ? "Y" : "N");
//                        vo.setVcanteen(input.getIsAccessCanteen() == true ? "Y" : "N");
//                        vo.setVsecgate(input.getIsAccessSecGate() == true ? "Y" : "N");
//                        vo.setDbgneffdt(DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy"));
//                        vo.setDendeffdt(DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy"));
//
//                        Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
//                        int beginYear = Integer.valueOf(DateUtil.dateToString(begin, "yyyy"));
//                        Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");
//                        int endYear = Integer.valueOf(DateUtil.dateToString(end, "yyyy"));
//                        if (endYear > beginYear) {
//                            tempExp = "31-Dec-" + beginYear;
//                        } else {
//                            tempExp = input.getEndDate();
//                        }
//
//                        vo.setDpassexp(DateUtil.stringToDate(tempExp, "dd-MMM-yyyy"));
//                        vo.setVnote(input.getNote());
//                        vo.setVphotoname(photo.getOriginalFilename());
//                        vo.setBphoto(input.getbFileNamePhoto());
//                        vo.setVvacstts(input.getVacStatus());
//                        if ("Not Yet Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
//                            vo.setVntvs(input.getVacNotes());
//                        } else {
//                            vo.setVvactype(input.getVacType());
//                            vo.setDlastvac(DateUtil.stringToDate(input.getVacDate(), "dd-MMM-yyyy"));
//                            vo.setVvacdtl(input.getSumVacStatus());
//                        }
//
//                        vo.setVappdisclm(input.getDisclaimer() == true ? "Y" : "N");
//                        if ("AHM".equalsIgnoreCase(user.getType())) {
//                            vo.setVotsstts("Waiting for Approval Security");
//                        } else {
//                            vo.setVotsstts("Waiting for Approval PIC");
//                        }
//                        vo.setCreateBy(user.getUserid());
//
//                        vms022ahmhrntmHdrotsempsDao.save(vo);
//                        //ahmhrntmHdrotsempsDao.flush();
//
//                        //copyFile foto dan ktp ke server
//                        Vms022VoFileAttachment flKtp = uploadFileToServer(pathServer, ktp, null);
//                        Vms022VoFileAttachment flPhoto = uploadFileToServer(pathServer, photo, null);
//
//                        //Save Plants
//                        int seqPlant = vms022ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "PLNT");
//                        for (Vms022VoLov plnt : input.getListPlants()) {
//                            AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                            AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                            pk.setVotsid(otsid);
//                            pk.setVregid("PLNT");
//                            pk.setVpersid(input.getNik());
//                            seqPlant = seqPlant + 1;
//                            pk.setNseq(new BigDecimal(seqPlant));
//                            vox.setAhmhrntmDtlotsregsPk(pk);
//                            vox.setVplant(plnt.getId());
//                            vox.setCreateBy(user.getUserid());
//
//                            vms022ahmhrntmDtlotsregsDao.save(vox);
//                        }
//
//                        //Save Gate
//                        int seqGate = vms022ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "GATE");
//                        for (Vms022VoLov gate : input.getListGate()) {
//                            AhmhrntmDtlotsregs gt = new AhmhrntmDtlotsregs();
//                            AhmhrntmDtlotsregsPk gtPk = new AhmhrntmDtlotsregsPk();
//                            gtPk.setVotsid(otsid);
//                            gtPk.setVregid("GATE");
//                            gtPk.setVpersid(input.getNik());
//                            seqGate = seqGate + 1;
//                            gtPk.setNseq(new BigDecimal(seqGate));
//                            gt.setAhmhrntmDtlotsregsPk(gtPk);
//                            gt.setVgate(gate.getId());
//                            gt.setCreateBy(user.getUserid());
//
//                            vms022ahmhrntmDtlotsregsDao.save(gt);
//                        }
//
//                        //copy file vac and attch
//                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
//                            int seqVac = vms022ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "VC");
//                            for (MultipartFile vac : vacFiles) {
//                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                seqVac = seqVac + 1;
//
//                                String fileName = otsid + "_VC_" + input.getNik() + "_" + seqVac;
//                                Vms022VoFileAttachment flVac = uploadFileToServer(pathServer, vac, fileName);
//
//                                pk.setVotsid(otsid);
//                                pk.setVregid("VC");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(seqVac));
//                                vox.setAhmhrntmDtlotsregsPk(pk);
//                                vox.setVfile(flVac.getName());
//                                vox.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(vox);
//                            }
//                        }
//                        if (!StringUtils.isBlank(attcFiles.get(0).getOriginalFilename())) {
//                            int seqAtt = vms022ahmhrntmDtlotsregsDao.getLastSeq(otsid, input.getNik(), "SK");
//                            for (MultipartFile attc : attcFiles) {
//                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                seqAtt = seqAtt + 1;
//
//                                String fileName = otsid + "_SK_" + input.getNik() + "_" + seqAtt;
//                                Vms022VoFileAttachment flSk = uploadFileToServer(pathServer, attc, fileName);
//
//                                pk.setVotsid(otsid);
//                                pk.setVregid("SK");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(seqAtt));
//                                vox.setAhmhrntmDtlotsregsPk(pk);
//                                vox.setVfile(flSk.getName());
//                                vox.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(vox);
//                            }
//                        }
//                    } else {
//                    }
//                } catch (Exception e) {
//                    throw new Vms022Exception("Failed to saved");
//                }
//            } else {
//                throw new Vms022Exception("NIK [" + input.getNik() + "] dengan Nama [" + findNiks.get(0).getVname() + "] masih berstatus active di AHM.");
//            }
//        }
//
//        DtoResponseWorkspace response = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
//        response.setMessage("Data sukses disimpan");
//        return response;
//    }
//    
//    @Override
//    @Transactional(readOnly = false)
//    public DtoResponseWorkspace updateItem(Vms022VoSubmit input, MultipartFile ktp, MultipartFile photo, List<MultipartFile> vacFiles, List<MultipartFile> attcFiles, VoUserCred user) {
//        //Validasi Ext / Int
//        Boolean isVaccine = false;
//        String tempExp = null;
//        if (StringUtils.isBlank(input.getId())) {
//            throw new Vms022Exception("Data Invalid");
//        }
//        if (StringUtils.isBlank(input.getOutId())) {
//            throw new Vms022Exception("Outsource ID invalid");
//        }
//        if (StringUtils.isBlank(input.getBeginDate())) {
//            throw new Vms022Exception("Begin Work Effective Date is required");
//        } else {
//            if (StringUtils.isBlank(input.getEndDate())) {
//                throw new Vms022Exception("End Work Effective Date is required");
//            } else {
//                Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
//                Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");
//
//                if (begin.compareTo(end) > 0) {
//                    throw new Vms022Exception("Begin Work Effective Date harus lebih kecil dari End Work Effective Date");
//                }
//            }
//        }
//        if (StringUtils.isBlank(input.getOutName())) {
//            throw new Vms022Exception("Outsource Name is required");
//        }
//        if (StringUtils.isBlank(input.getPhoneNo())) {
//            throw new Vms022Exception("Phone No is required");
//        } else {
//            //VALIDATE
//        }
//        if (StringUtils.isBlank(input.getNik())) {
//            throw new Vms022Exception("NIK is required");
//        } else {
//            //VALIDATE
//        }
//
//        if (StringUtils.isBlank(input.getOutTypeId())) {
//            throw new Vms022Exception("Outsource Type is required");
//        } else {
//            //VALIDATE 
//        }
//        if (StringUtils.isBlank(input.getCompanyId())) {
//            throw new Vms022Exception("Company is required");
//        } else {
//            //VALIDATE
//        }
//        if (StringUtils.isBlank(input.getJob())) {
//            throw new Vms022Exception("Job Detail is required");
//        } else {
//            //VALIDATE
//        }
//        if (input.getListPlants().isEmpty()) {
//            throw new Vms022Exception("Plants is required");
//        }
//        if (input.getListGate().isEmpty()) {
//            throw new Vms022Exception("Gates is required");
//        }
//        if (!StringUtils.isBlank(input.getVacStatus())) {
//            if ("Already Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
//                if (StringUtils.isBlank(input.getVacType())) {
//                    throw new Vms022Exception("Vaccinee Type is required");
//                }
//                if (StringUtils.isBlank(input.getSumVacStatus())) {
//                    throw new Vms022Exception("Vaccinee Summary Status is required");
//                }
//                if (StringUtils.isBlank(input.getVacDate())) {
//                    throw new Vms022Exception("Last Vaccinee Date is required");
//                }
//                if (StringUtils.isBlank(vacFiles.get(0).getOriginalFilename()) && input.getFileVacExist().isEmpty()) {
//                    throw new Vms022Exception("Certificate Vaccine is required");
//                } else {
//                    isVaccine = true;
//                }
//            } else if ("Not Yet Vaccinated".equalsIgnoreCase(input.getVacStatus())) {
//                if (StringUtils.isBlank(input.getVacNotes())) {
//                    throw new Vms022Exception("Note Vaccinee is required");
//                }
//                if (StringUtils.isBlank(attcFiles.get(0).getOriginalFilename()) && input.getFileDocExist().isEmpty()) {
//                    throw new Vms022Exception("Attachment is required");
//                } else {
//                    isVaccine = false;
//                }
//            } else {
//                throw new Vms022Exception("Vaccinee Status is invalid");
//            }
//        } else {
//            throw new Vms022Exception("Last Vaccinee Status is required");
//        }
//
//        if (input.getIsAccessAbsReader() == null && input.getIsAccessCanteen() == null && input.getIsAccessSecGate() == null) {
//            throw new Vms022Exception("Access is required");
//        } else {
//            if (input.getIsAccessAbsReader() == false && input.getIsAccessCanteen() == false && input.getIsAccessSecGate() == false) {
//                throw new Vms022Exception("Access belum dipilih");
//            }
//        }
//
//        if (!StringUtils.isBlank(input.getSupplier())) {
//            if (!"N".equalsIgnoreCase(input.getSupplier()) && !"S".equalsIgnoreCase(input.getSupplier())) {
//                throw new Vms022Exception("Invalid Supplier");
//            }
//        } else {
//            throw new Vms022Exception("Supplier is required");
//        }
//
//        if (input.getIsNewPhoto() && StringUtils.isBlank(photo.getOriginalFilename())) {
//            throw new Vms022Exception("File Photo is required");
//        }
//
//        if (input.getIsNewKtp() && StringUtils.isBlank(ktp.getOriginalFilename())) {
//            throw new Vms022Exception("File Ktp is required");
//        }
//
//        List<AhmhrntmHdrotsemps> dt = vms022ahmhrntmHdrotsempsDao.validateUpdate(input.getOutId(), input.getNik());
//        if (dt.isEmpty()) {
//            AhmhrntmHdrotsemps checks = vms022ahmhrntmHdrotsempsDao.findOne(input.getId().toUpperCase());
//            if (checks != null) {
//                try {
//                    checks.setVname(input.getOutName());
//                    checks.setVempphone(input.getPhoneNo());
//                    checks.setVpersid(input.getNik());
//                    checks.setVotstype(input.getOutTypeId());
//                    checks.setVrbsuplay(input.getSupplier());
//                    checks.setVcompany(input.getCompanyId());
//                    checks.setVjobdtl(input.getJob());
//                    checks.setVabsrdr(input.getIsAccessAbsReader() == true ? "Y" : "N");
//                    checks.setVcanteen(input.getIsAccessCanteen() == true ? "Y" : "N");
//                    checks.setVsecgate(input.getIsAccessSecGate() == true ? "Y" : "N");
//                    checks.setDbgneffdt(DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy"));
//                    checks.setDendeffdt(DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy"));
//
//                    Date begin = DateUtil.stringToDate(input.getBeginDate(), "dd-MMM-yyyy");
//                    int beginYear = Integer.valueOf(DateUtil.dateToString(begin, "yyyy"));
//                    Date end = DateUtil.stringToDate(input.getEndDate(), "dd-MMM-yyyy");
//                    int endYear = Integer.valueOf(DateUtil.dateToString(end, "yyyy"));
//                    if (endYear > beginYear) {
//                        tempExp = "31-Dec-" + beginYear;
//
//                    } else {
//                        tempExp = input.getEndDate();
//                    }
//
//                    checks.setDpassexp(DateUtil.stringToDate(tempExp, "dd-MMM-yyyy"));
//                    checks.setVnote(input.getNote());
//                    checks.setVvacstts(input.getVacStatus());
//                    checks.setVvactype(input.getVacType());
//                    checks.setDlastvac(DateUtil.stringToDate(input.getVacDate(), "dd-MMM-yyyy"));
//                    checks.setVvacdtl(input.getSumVacStatus());
//                    checks.setVntvs(input.getVacNotes());
//
//                    //copyFile foto dan ktp ke server
//                    if (input.getIsNewPhoto()) {
//                        checks.setVphotoname(photo.getOriginalFilename());
//                        checks.setBphoto(input.getbFileNamePhoto());
//
//                        Vms022VoFileAttachment flPhoto = uploadFileToServer(pathServer, photo, null);
//                    }
//                    if (input.getIsNewKtp()) {
//                        checks.setVphotoid(ktp.getOriginalFilename());
//
//                        Vms022VoFileAttachment flKtp = uploadFileToServer(pathServer, ktp, null);
//                    }
//                    checks.setLastModBy(user.getUserid());
//                    vms022ahmhrntmHdrotsempsDao.update(checks);
//
//                    
//                    if(input.getIsNewPersid()){
//                        //get seq old with nik old
//                        List<String> listSeq = vms022ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "PLNT");
//                        if(!listSeq.isEmpty()){
//                            for(String s : listSeq){
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("PLNT");
//                                pk.setVpersid(input.getOldNik());
//                                pk.setNseq(new BigDecimal(s));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pk);
//                            }
//                        }
//                        
//                        //save dgn nik baru
//                        int seqPlant = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "PLNT");
//                        for (Vms022VoLov plnt : input.getListPlants()) {
//                            AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                            AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                            pk.setVotsid(input.getOutId());
//                            pk.setVregid("PLNT");
//                            pk.setVpersid(input.getNik());
//                            seqPlant = seqPlant + 1;
//                            pk.setNseq(new BigDecimal(seqPlant));
//                            vox.setAhmhrntmDtlotsregsPk(pk);
//                            vox.setVplant(plnt.getId());
//                            vox.setCreateBy(user.getUserid());
//
//                            vms022ahmhrntmDtlotsregsDao.save(vox);
//                        }
//                    } else {
//                        //Save Only New Plants
//                        List<Vms022VoLov> temps = new ArrayList<>();
//                        int seqPlant = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "PLNT");
//                        for (Vms022VoLov plnt : input.getListPlants()) {
//                            if ("N".equalsIgnoreCase(plnt.getStat())) {
//                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("PLNT");
//                                pk.setVpersid(input.getNik());
//                                seqPlant = seqPlant + 1;
//                                pk.setNseq(new BigDecimal(seqPlant));
//                                vox.setAhmhrntmDtlotsregsPk(pk);
//                                vox.setVplant(plnt.getId());
//                                vox.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(vox);
//                            }
//                        }
//                        //Delete Plants
//                        if (!input.getListPlantDelete().isEmpty()) {
//                            for (String s : input.getListPlantDelete()) {
//                                String[] idx = s.split("_");
//                                String tempSeq = idx[1];
//
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("PLNT");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(tempSeq));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pk);
//                            }
//                        }
//                    }
//                    
//
//                    if (input.getIsNewPersid()) {
//                        //get seq old with nik old
//                        List<String> listSeq = vms022ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "GATE");
//                        if (!listSeq.isEmpty()) {
//                            for (String s : listSeq) {
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("GATE");
//                                pk.setVpersid(input.getOldNik());
//                                pk.setNseq(new BigDecimal(s));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pk);
//                            }
//                        }
//
//                        //save dgn nik baru
//                        int seqGate = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "GATE");
//                        for (Vms022VoLov gate : input.getListGate()) {
//                            AhmhrntmDtlotsregs gt = new AhmhrntmDtlotsregs();
//                            AhmhrntmDtlotsregsPk gtPk = new AhmhrntmDtlotsregsPk();
//                            gtPk.setVotsid(input.getOutId());
//                            gtPk.setVregid("GATE");
//                            gtPk.setVpersid(input.getNik());
//                            seqGate = seqGate + 1;
//                            gtPk.setNseq(new BigDecimal(seqGate));
//                            gt.setAhmhrntmDtlotsregsPk(gtPk);
//                            gt.setVgate(gate.getId());
//                            gt.setCreateBy(user.getUserid());
//
//                            vms022ahmhrntmDtlotsregsDao.save(gt);
//                        }
//                    } else {
//                        //Save Only New Gate
//                        int seqGate = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "GATE");
//                        for (Vms022VoLov gate : input.getListGate()) {
//                            if ("N".equalsIgnoreCase(gate.getStat())) {
//                                AhmhrntmDtlotsregs gt = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk gtPk = new AhmhrntmDtlotsregsPk();
//                                gtPk.setVotsid(input.getOutId());
//                                gtPk.setVregid("GATE");
//                                gtPk.setVpersid(input.getNik());
//                                seqGate = seqGate + 1;
//                                gtPk.setNseq(new BigDecimal(seqGate));
//                                gt.setAhmhrntmDtlotsregsPk(gtPk);
//                                gt.setVgate(gate.getId());
//                                gt.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(gt);
//                            }
//                        }
//                        //Delete Gate
//                        if (!input.getListGateDelete().isEmpty()) {
//                            for (String s : input.getListGateDelete()) {
//                                String[] idx = s.split("_");
//                                String tempSeq = idx[1];
//
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("GATE");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(tempSeq));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pk);
//                            }
//                        }
//                    }
//                    
//                        //ganti nik
//                        //upload ulang
//                        //delete
//                        
//
//                    if (input.getIsNewPersid()) {
//                        //delete dulu data lama
//                        if (!input.getFileVacDeletes().isEmpty()) {
//                            for (String s : input.getFileVacDeletes()) {
//                                String[] tempFilename = s.split("_");
//                                String tempSeq = tempFilename[3];
//                                String[] seqs = tempSeq.split("\\.");
//                                String seq = seqs[0];
//
//                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
//                                pkDelete.setVotsid(input.getOutId());
//                                pkDelete.setVregid("VC");
//                                pkDelete.setVpersid(input.getOldNik());
//                                pkDelete.setNseq(new BigDecimal(seq));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pkDelete);
//                                vms022ahmhrntmDtlotsregsDao.flush();
//                            }
//                        }
//                        
//                        //update data lama dgn new nik
//                        List<String> listSeq = vms022ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "VC");
//                        if (!listSeq.isEmpty()) {
//                            for (String s : listSeq) {
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("VC");
//                                pk.setVpersid(input.getOldNik());
//                                pk.setNseq(new BigDecimal(s));
//                                
//                                AhmhrntmDtlotsregs find = vms022ahmhrntmDtlotsregsDao.findOne(pk);
//                                
//                                AhmhrntmDtlotsregsPk newPk = new AhmhrntmDtlotsregsPk();
//                                AhmhrntmDtlotsregs newVo = new AhmhrntmDtlotsregs();
//                                newPk.setVotsid(input.getOutId());
//                                newPk.setVregid("VC");
//                                newPk.setVpersid(input.getNik());
//                                newPk.setNseq(new BigDecimal(s));
//                                newVo.setAhmhrntmDtlotsregsPk(newPk);
//                                newVo.setVfile(find.getVfile());
//                                vms022ahmhrntmDtlotsregsDao.save(newVo);
//                                
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pk);
//                            }
//                        }
//                        
//                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
//                            int seqVac = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "VC");
//                            for (MultipartFile vac : vacFiles) {
//                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                seqVac = seqVac + 1;
//
//                                String fileName = input.getOutId() + "_VC_" + input.getNik() + "_" + seqVac;
//                                Vms022VoFileAttachment flVac = uploadFileToServer(pathServer, vac, fileName);
//
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("VC");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(seqVac));
//                                vox.setAhmhrntmDtlotsregsPk(pk);
//                                vox.setVfile(flVac.getName());
//                                vox.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(vox);
//                            }                            
//                        } 
//                    } else {
//                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
//                            int seqVac = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "VC");
//                            for (MultipartFile vac : vacFiles) {
//                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                seqVac = seqVac + 1;
//
//                                String fileName = input.getOutId() + "_VC_" + input.getNik() + "_" + seqVac;
//                                Vms022VoFileAttachment flVac = uploadFileToServer(pathServer, vac, fileName);
//
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("VC");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(seqVac));
//                                vox.setAhmhrntmDtlotsregsPk(pk);
//                                vox.setVfile(flVac.getName());
//                                vox.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(vox);
//                            }
//                        }
//                        if (!input.getFileVacDeletes().isEmpty()) {
//                            for (String s : input.getFileVacDeletes()) {
//                                String[] tempFilename = s.split("_");
//                                String tempSeq = tempFilename[3];
//                                String[] seqs = tempSeq.split("\\.");
//                                String seq = seqs[0];
//
//                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
//                                pkDelete.setVotsid(input.getOutId());
//                                pkDelete.setVregid("VC");
//                                pkDelete.setVpersid(input.getNik());
//                                pkDelete.setNseq(new BigDecimal(seq));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pkDelete);
//                            }
//                        }
//                    }
//                    
//
//                    if (input.getIsNewPersid()) {
//                        //delete dulu data lama
//                        if (!input.getFileDocDeletes().isEmpty()) {
//                            for (String s : input.getFileDocDeletes()) {
//                                String[] tempFilename = s.split("_");
//                                String tempSeq = tempFilename[3];
//                                String[] seqs = tempSeq.split("\\.");
//                                String seq = seqs[0];
//
//                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
//                                pkDelete.setVotsid(input.getOutId());
//                                pkDelete.setVregid("SK");
//                                pkDelete.setVpersid(input.getOldNik());
//                                pkDelete.setNseq(new BigDecimal(seq));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pkDelete);
//                                vms022ahmhrntmDtlotsregsDao.flush();
//                            }
//                        }
//
//                        //update data lama dgn new nik
//                        List<String> listSeq = vms022ahmhrntmDtlotsregsDao.getSeq(input.getOutId(), input.getOldNik(), "SK");
//                        if (!listSeq.isEmpty()) {
//                            for (String s : listSeq) {
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("SK");
//                                pk.setVpersid(input.getOldNik());
//                                pk.setNseq(new BigDecimal(s));
//                                
//                                AhmhrntmDtlotsregs find = vms022ahmhrntmDtlotsregsDao.findOne(pk);
//                                
//                                AhmhrntmDtlotsregsPk newPk = new AhmhrntmDtlotsregsPk();
//                                AhmhrntmDtlotsregs newVo = new AhmhrntmDtlotsregs();
//                                newPk.setVotsid(input.getOutId());
//                                newPk.setVregid("VC");
//                                newPk.setVpersid(input.getNik());
//                                newPk.setNseq(new BigDecimal(s));
//                                newVo.setAhmhrntmDtlotsregsPk(newPk);
//                                newVo.setVfile(find.getVfile());
//                                vms022ahmhrntmDtlotsregsDao.save(newVo);
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pk);
//                            }
//                        }
//                        
//                        if (!StringUtils.isBlank(vacFiles.get(0).getOriginalFilename())) {
//                            int seqAtt = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "SK");
//                            for (MultipartFile attc : attcFiles) {
//                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                seqAtt = seqAtt + 1;
//
//                                String fileName = input.getOutId() + "_SK_" + input.getNik() + "_" + seqAtt;
//                                Vms022VoFileAttachment flSk = uploadFileToServer(pathServer, attc, fileName);
//
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("SK");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(seqAtt));
//                                vox.setAhmhrntmDtlotsregsPk(pk);
//                                vox.setVfile(flSk.getName());
//                                vox.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(vox);
//                            }
//                        }
//                    } else {
//                        if (!StringUtils.isBlank(attcFiles.get(0).getOriginalFilename())) {
//                            int seqAtt = vms022ahmhrntmDtlotsregsDao.getLastSeq(input.getOutId(), input.getNik(), "SK");
//                            for (MultipartFile attc : attcFiles) {
//                                AhmhrntmDtlotsregs vox = new AhmhrntmDtlotsregs();
//                                AhmhrntmDtlotsregsPk pk = new AhmhrntmDtlotsregsPk();
//                                seqAtt = seqAtt + 1;
//
//                                String fileName = input.getOutId() + "_SK_" + input.getNik() + "_" + seqAtt;
//                                Vms022VoFileAttachment flSk = uploadFileToServer(pathServer, attc, fileName);
//
//                                pk.setVotsid(input.getOutId());
//                                pk.setVregid("SK");
//                                pk.setVpersid(input.getNik());
//                                pk.setNseq(new BigDecimal(seqAtt));
//                                vox.setAhmhrntmDtlotsregsPk(pk);
//                                vox.setVfile(flSk.getName());
//                                vox.setCreateBy(user.getUserid());
//
//                                vms022ahmhrntmDtlotsregsDao.save(vox);
//                            }
//                        }
//                        if (!input.getFileDocDeletes().isEmpty()) {
//                            for (String s : input.getFileDocDeletes()) {
//                                String[] tempFilename = s.split("_");
//                                String tempSeq = tempFilename[3];
//                                String[] seqs = tempSeq.split("\\.");
//                                String seq = seqs[0];
//
//                                AhmhrntmDtlotsregsPk pkDelete = new AhmhrntmDtlotsregsPk();
//                                pkDelete.setVotsid(input.getOutId());
//                                pkDelete.setVregid("SK");
//                                pkDelete.setVpersid(input.getNik());
//                                pkDelete.setNseq(new BigDecimal(seq));
//
//                                vms022ahmhrntmDtlotsregsDao.deleteById(pkDelete);
//                            }
//                        }
//                    }
//                    
//                } catch (Exception e) {
//                    throw new Vms022Exception("Failed to update");
//                }
//            } else {
//                throw new Vms022Exception("Data not found");
//            }
//        } else {
//            throw new Vms022Exception("NIK [" + input.getNik() + "] dengan Nama [" + dt.get(0).getVname() + "] masih berstatus active di AHM.");
//        }
//
//        DtoResponseWorkspace response = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, input);
//        response.setMessage("Data sukses diubah");
//        return response;
//    }
//    
//    
//    @Override
//    @Transactional(readOnly = false)
//    public DtoResponseWorkspace inActiveData(Vms022VoSubmit input, VoUserCred user) {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -1);
//        Date ys = cal.getTime();
//        
//        if(!input.getListInactiveData().isEmpty()){
//            for(Vms022VoMonitoring vo : input.getListInactiveData()){
//                if("Active".equalsIgnoreCase(vo.getOutStatus())){
//                    AhmhrntmHdrotsemps dt = vms022ahmhrntmHdrotsempsDao.findOne(vo.getId().toUpperCase());
//                    dt.setVotsstts("Inactive");
//                    dt.setDendeffdt(ys);
//                    
//                    vms022ahmhrntmHdrotsempsDao.update(dt);
//                }
//            }
//        }
//
//        DtoResponseWorkspace response = DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, null);
//        response.setMessage("Inactive Sukses");
//        return response;
//    }
//    
//    @Override
//    @Transactional
//    public BigDecimal getNewValue(String vidname, BigDecimal vreset, String user) {
//        BigDecimal newValue;
//        System.out.println("runnummmm: " + vidname + vreset);
//
//        AhmhrntmMstuprnumsPk pk = new AhmhrntmMstuprnumsPk();
//        pk.setVabstcode(vidname);
//        pk.setNyear(vreset);
//        AhmhrntmMstuprnums ahmhrntmMstuprnums = vms022ahmhrntmMstuprnumsDao.findOne(pk);
//        if (ahmhrntmMstuprnums == null) { // Insert new running number
//            newValue = new BigDecimal(BigInteger.ONE);
//            ahmhrntmMstuprnums = new AhmhrntmMstuprnums();
//            ahmhrntmMstuprnums.setAhmhrntmMstuprnumsPk(pk);
//            ahmhrntmMstuprnums.setNrunnumb(newValue);
//            vms022ahmhrntmMstuprnumsDao.save(ahmhrntmMstuprnums);
//        } else { // Update existing running number
//            newValue = ahmhrntmMstuprnums.getNrunnumb().add(new BigDecimal(BigInteger.ONE));
//            ahmhrntmMstuprnums.setNrunnumb(newValue);
//            vms022ahmhrntmMstuprnumsDao.update(ahmhrntmMstuprnums);
//        }
//        return newValue;
//    }
//    
//    @Override
//    public DtoResponseWorkspace getPlantsGates(Vms022VoSubmit input) {
//        Vms022VoSubmit result = new Vms022VoSubmit();
//        List<Vms022VoLov> tempPlants = new ArrayList<>();
//        List<Vms022VoLov> tempGates = new ArrayList<>();
//
//        tempPlants = vms022ahmhrntmDtlprmgblsDao.getPlantsGates(input.getOutId(), input.getNik(), "PLNT");
//        tempGates = vms022ahmhrntmDtlprmgblsDao.getPlantsGates(input.getOutId(), input.getNik(), "");
//
//        if (!tempPlants.isEmpty()) {
//            tempPlants.sort(Comparator.comparing(Vms022VoLov::getSeq));
//        }
//        if (!tempGates.isEmpty()) {
//            tempGates.sort(Comparator.comparing(Vms022VoLov::getSeq));
//        }
//
//        result.setOutId(input.getOutId());
//        result.setNik(input.getNik());
//        result.setListPlants(tempPlants);
//        result.setListGate(tempGates);
//
//        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
//    }
//    
//    @Override
//    public DtoResponseWorkspace getPicAhm(Vms022VoMonitoring input) {
//        List<Vms022VoLovNrp> result = new ArrayList<>();
//        List<String> plants = new ArrayList<>();
//
//        if (!input.getPlants().isEmpty() && !StringUtils.isBlank(input.getOutType())) {
//            for (Vms022VoLov v : input.getPlants()) {
//
//                plants.add(v.getId());
//            }
//
//            result = vms022ahmhrntmMstpicotsDao.getPicAhm(input.getOutType(), plants);
//        }
//
//        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
//    }
//    
//    @Override
//    public DtoResponseWorkspace getFiles(Vms022VoSubmit input) {
//        Vms022VoMonitoring result = new Vms022VoMonitoring();
//
//        List<Vms022VoFileAttachment> listVacs = new ArrayList<>();
//        List<String> flVacs = vms022ahmhrntmDtlotsregsDao.getFileName(input.getOutId(), input.getNik(), "VC");
//        if (!flVacs.isEmpty()) {
//            for (String v : flVacs) {
//                Vms022VoFileAttachment dtVac = new Vms022VoFileAttachment();
//
//                byte[] bFileVac = readBytesFromFile(pathServer + v);
//                dtVac.setName(v);
//                dtVac.setPath(Base64.getEncoder().encodeToString(bFileVac));
//
//                listVacs.add(dtVac);
//            }
//        }
//        result.setFileVaccines(listVacs);
//
//        List<Vms022VoFileAttachment> listAttcs = new ArrayList<>();
//        List<String> flAttc = vms022ahmhrntmDtlotsregsDao.getFileName(input.getOutId(), input.getNik(), "SK");
//        if (!flAttc.isEmpty()) {
//            for (String v : flAttc) {
//                Vms022VoFileAttachment dtAttc = new Vms022VoFileAttachment();
//
//                byte[] bFileAttc = readBytesFromFile(pathServer + v);
//                dtAttc.setName(v);
//                dtAttc.setPath(Base64.getEncoder().encodeToString(bFileAttc));
//
//                listAttcs.add(dtAttc);
//            }
//        }
//        result.setFileSk(listAttcs);
//
//        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
//    }
//    
//    @Override
//    @Transactional(readOnly = false)
//    public Vms022VoFileAttachment uploadFileToServer(String pathServer, MultipartFile fileToUpload, String newFileName) {
//        Vms022VoFileAttachment fileDtl = new Vms022VoFileAttachment();
//        try {
//            String fileName = "";
//            if (StringUtils.isBlank(newFileName)) {
//                fileName = fileToUpload.getOriginalFilename();
//            } else {
//                fileName = newFileName + "." + fileToUpload.getOriginalFilename().substring(fileToUpload.getOriginalFilename().lastIndexOf(".") + 1);
//            }
//
//            String fileType = fileToUpload.getContentType();
//            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
//            String uploadFilePath = pathServer + fileName;
//
//            File newFile = new File(uploadFilePath);
//            fileToUpload.transferTo(newFile);
//
//            fileDtl.setName(fileName);
//            fileDtl.setPath(uploadFilePath);
//            fileDtl.setType(fileType);
//            fileDtl.setExt(fileExt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return fileDtl;
//    }
//    

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
//    
//    @Override
//    public DtoResponseWorkspace upload(MultipartFile excel, MultipartFile ktp, MultipartFile photo, MultipartFile vacFiles, MultipartFile attcFiles, VoUserCred user) {
//        Map<Integer, List<String>> errors = new HashMap<>();
//        try {
//            Workbook workbook = getWorkbook(excel);
//            Sheet sheet = workbook.getSheetAt(0);
//            
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new Vms022UploadException("Data gagal diupload", mapToList(errors));
//        } 
//        
//        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, "Data excel berhasil di upload.", null, null);
//    }
//    

    private Workbook getWorkbook(MultipartFile excel) throws IOException {
        if (excel == null) {
            throw new Vms022Exception("File yang diupload bukan format excel");
        }
        if (excel.getOriginalFilename().endsWith("xlsx")) {
            return new XSSFWorkbook(excel.getInputStream());
        } else {
            throw new Vms022Exception("Ekstensi file yang diperbolehkan : xlsx");
        }
    }

    private List<String> mapToList(Map<?, List<String>> map) {
        List<String> list = new ArrayList<>();
        map.values().forEach(err -> list.addAll(err));
        return list;
    }

    @Override
    public DtoResponse getRoles(String username) {
        Map<String, Object> msg = new HashMap<>();
        boolean isValidRole = vms022ObjectDao.getRoles(username)
                .contains("RO_GAVMS_OFCSECT");
        msg.put("isValidRole", isValidRole);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
    }

    @Override
    public DtoResponseWorkspace approve(List<Vms022VoMonitoring> getdata, VoUserCred voUserCred) {
        Map<String, Object> msg = new HashMap<>();

        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                try {
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));

                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts("Active");
//                        mp.setVotsstts("Waiting for Approval Security");
                        vms022ahmhrntmHdrotsempsDao.update(mp, voUserCred.getUserid());
                        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, setMessage("Approve success"), null);
                    }
                } catch (Exception e) {
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, setMessage("Failed Approve data"), null);
                }
            }
        }
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, setMessage("Approve success"), null);
    }

    @Override
    public DtoResponseWorkspace reject(List<Vms022VoMonitoring> getdata, VoUserCred voUserCred) {
        Map<String, Object> msg = new HashMap<>();

        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                try {
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));

                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts("Reject");
                        vms022ahmhrntmHdrotsempsDao.update(mp, voUserCred.getUserid());
                        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, setMessage("Reject success"), null);
                    }
                } catch (Exception e) {
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, setMessage("Failed Reject data"), null);
                }
            }
        }
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, setMessage("Reject success"), null);
    }

    @Override
    public Workbook exportToExcelMainData(DtoParamPaging dto) {
        Vms022VoMonitoring vo = new Vms022VoMonitoring();
        String valueStr = "";
//        for (Map.Entry<String, Object> filter : dto.getSearch().entrySet()) {
//            valueStr = filter.getValue().toString();
//            if (filter.getKey().equalsIgnoreCase("vendorCode") && valueStr != null && !valueStr.isEmpty()) {
//                vo.setVnpwp(valueStr);
//            }
//            if (filter.getKey().equalsIgnoreCase("transaction") && valueStr != null && !valueStr.isEmpty()) {
//                vo.setVaddr(valueStr);
//            }
//            if (filter.getKey().equalsIgnoreCase("vdateFrom") && valueStr != null && !valueStr.isEmpty()) {
//                vo.setVcontaint(valueStr);
//            }
//            if (filter.getKey().equalsIgnoreCase("vdateTo") && valueStr != null && !valueStr.isEmpty()) {
//                vo.setMplantVplantid(valueStr);
//            }
//            if (filter.getKey().equalsIgnoreCase("vdateFromTxn") && valueStr != null && !valueStr.isEmpty()) {
//                vo.setDbegin(valueStr);
//            }
//            if (filter.getKey().equalsIgnoreCase("vdateToTxn") && valueStr != null && !valueStr.isEmpty()) {
//                vo.setDend(valueStr);
//            }
//        }

        List<Vms022VoMonitoring> listData = vms022ahmhrntmHdrotsempsDao.getDataExcel(dto);

        HSSFRow rowDetail;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("Master NPWP Plant PPN In");
        HSSFCell[] listCellD = new HSSFCell[24];

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        HSSFCellStyle styleBold = workbook.createCellStyle();
        HSSFFont fontBold = workbook.createFont();
        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleBold.setFont(fontBold);

        //Format style tanggal
        HSSFCellStyle styleDate = workbook.createCellStyle();
        HSSFCreationHelper creationHelper = workbook.getCreationHelper();
        styleDate.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
        styleDate.setAlignment(CellStyle.ALIGN_LEFT);

        //Format style angka
        HSSFCellStyle styleNum = workbook.createCellStyle();
        styleNum.setDataFormat(creationHelper.createDataFormat().getFormat("#,##0"));
        styleNum.setAlignment(CellStyle.ALIGN_RIGHT);

        int ihdrrow = 3;

//        if (vo.getVnpwp() != null) {
//            ihdrrow++;
//        }
//        if (vo.getVaddr() != null) {
//            ihdrrow++;
//        }
//        if (vo.getVcontaint() != null) {
//            ihdrrow++;
//        }
//        if (vo.getMplantVplantid() != null) {
//            ihdrrow++;
//        }
//        if (vo.getDbegin() != null) {
//            ihdrrow++;
//        }
//        if (vo.getDend() != null) {
//            ihdrrow++;
//        }
        ihdrrow = ihdrrow + 1;

        HSSFRow row = null;
        for (int i = 0; i < 24; i++) {
            worksheet.addMergedRegion(new CellRangeAddress(ihdrrow, (ihdrrow + 1), i, i));
        }

        HSSFRow headerMerge = worksheet.createRow(ihdrrow);
        String[] arrHdr = {"No.", "NPWP", "Address", "Key Words", "Plant ID", "Begin Effective", "End Effective"};

        for (int i = 0; i < arrHdr.length; i++) {
            createCellHeader(workbook, headerMerge, arrHdr[i], i);
        }

        Vms022VoMonitoring voData;
        // Set value
        if (!listData.isEmpty()) {
//            int pointerRow = ihdrrow + 2;
            int pointerRow = 3;
            for (int i = 0; i < listData.size(); i++) {
                voData = listData.get(i);
                rowDetail = worksheet.createRow(pointerRow);
                listCellD[0] = rowDetail.createCell(0);
                listCellD[0].setCellValue(i + 1);
                listCellD[1] = rowDetail.createCell(1);
                listCellD[1].setCellValue(voData.getOutId());
                listCellD[2] = rowDetail.createCell(2);
                listCellD[2].setCellValue(voData.getOutName());
                listCellD[3] = rowDetail.createCell(3);
                listCellD[3].setCellValue(voData.getPersId());
                listCellD[4] = rowDetail.createCell(4);
                listCellD[4].setCellValue(voData.getOutTypeName());
                listCellD[5] = rowDetail.createCell(5);
                listCellD[5].setCellValue(voData.getCompanyName());
                listCellD[6] = rowDetail.createCell(6);
                listCellD[6].setCellValue(voData.getOutStatus());
                listCellD[7] = rowDetail.createCell(7);
                listCellD[7].setCellValue(voData.getAreaName());
                listCellD[8] = rowDetail.createCell(8);
                listCellD[8].setCellValue(voData.getVacStatus());
                listCellD[9] = rowDetail.createCell(9);
                listCellD[9].setCellValue(voData.getBeginDateText());
                listCellD[10] = rowDetail.createCell(10);
                listCellD[10].setCellValue(voData.getEndDateText());
                listCellD[11] = rowDetail.createCell(11);
                listCellD[11].setCellValue(voData.getPassNumber());
                listCellD[12] = rowDetail.createCell(12);
                listCellD[12].setCellValue(voData.getPassExpiryDateText());
                listCellD[13] = rowDetail.createCell(13);
                listCellD[13].setCellValue(voData.getModifyBy());
                listCellD[14] = rowDetail.createCell(14);
                listCellD[14].setCellValue(voData.getModifyDateText());
//                listCellD[5] = rowDetail.createCell(5);
//                listCellD[5].setCellValue(DateUtil.stringToDate(voData.getDbegin(), "dd-MM-yyyy") != null ? sdf.format(DateUtil.stringToDate(voData.getDbegin(), "dd-MM-yyyy")) : "");
//                listCellD[6] = rowDetail.createCell(6);
//                listCellD[6].setCellValue(DateUtil.stringToDate(voData.getDend(), "dd-MM-yyyy") != null ? sdf.format(DateUtil.stringToDate(voData.getDend(), "dd-MM-yyyy")) : "");

                pointerRow++;
            }
        }

        //Format style header
        HSSFCellStyle styleHdr = workbook.createCellStyle();
        styleHdr.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHdr.setFillForegroundColor(HSSFColor.TAN.index);
        styleHdr.setFont(fontBold);

        HSSFCell cellHeader = null;
        int iprmrow = 2;

        iprmrow = createCellParam(vo.getOutId(), "Outsource ID", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getOutName(), "Outsource Name", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getPersId(), "NIK", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getOutTypeName(), "Outsource Type", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getCompanyName(), "Outsource Company", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getOutStatus(), "Outsource Status", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getAreaName(), "Plant", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getVacStatus(), "Covid19 Vaccine Status", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getBeginDateText(), "Begin Work Effective Date", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getEndDateText(), "End Work Effective Date", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getPassNumber(), "Pass Card Number", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getPassExpiryDateText(), "Pass Card Expiry Date", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getModifyBy(), "Modified By", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getModifyDateText(), "Modified Date", iprmrow, row, worksheet, cellHeader, styleHdr);
//        iprmrow = createCellParamDate(vo.getDbegin(), "Transaction Date", iprmrow, row, worksheet, cellHeader, styleHdr);
//        iprmrow = createCellParamDate(vo.getDend(), "Ref Document Date", iprmrow, row, worksheet, cellHeader, styleHdr);

        iprmrow = iprmrow + 1;

        //Format style title
        HSSFCellStyle styleTitle = workbook.createCellStyle();
        HSSFFont fontTitle = workbook.createFont();
        fontTitle.setFontName(HSSFFont.FONT_ARIAL);
        fontTitle.setFontHeightInPoints((short) 20);
        fontTitle.setColor(HSSFColor.DARK_BLUE.index);
        styleTitle.setFont(fontTitle);

        row = worksheet.createRow(0);
        HSSFCell cellTitle = row.createCell(0);
        cellTitle.setCellValue("Master NPWP Plant PPN In");
        cellTitle.setCellStyle(styleTitle);

        for (int x = 1; x <= worksheet.getRow(ihdrrow).getPhysicalNumberOfCells(); x++) {
            worksheet.autoSizeColumn(x, true);
        }

        return workbook;
    }

    private void createCellHeader(HSSFWorkbook workbook, HSSFRow row, String obj, int col) {
        HSSFCellStyle styleTblHdr = workbook.createCellStyle();
        HSSFFont fontTblHdr = workbook.createFont();
        fontTblHdr.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleTblHdr.setFont(fontTblHdr);
        styleTblHdr.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleTblHdr.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);

        HSSFCell cellTblHdr = row.createCell(col);
        cellTblHdr.setCellStyle(styleTblHdr);
        cellTblHdr.setCellValue(obj);
    }

    private int createCellParam(String val, String vlabel, int iprmrow, HSSFRow row, HSSFSheet worksheet, HSSFCell cellHeader,
            HSSFCellStyle styleHdr) {
        if (val != null) {
            row = worksheet.createRow(iprmrow);
            cellHeader = row.createCell(0);
            cellHeader.setCellValue(vlabel);
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(1);
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(2);
//            cellHeader.setCellValue(":");
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(3);
            cellHeader.setCellValue(val);
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(4);
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(5);
            cellHeader.setCellStyle(styleHdr);
            iprmrow++;
        }
        return iprmrow;
    }

    private Map<String, Object> setMessage(String message) {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("info", message);
        return msg;
    }

}
