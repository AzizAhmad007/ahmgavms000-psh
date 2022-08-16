/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app022.constant.Vms022Status;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022Ahmitb2eMstusrrolesDao;
import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.exception.Vms022Exception;
import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFileAttachment;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFormAuthorization;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitor;
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
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
    //public final static String pathServer = "D:\\Download\\";

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
    @Qualifier("vms022Ahmitb2eMstusrrolesDao")
    private Vms022Ahmitb2eMstusrrolesDao vms022Ahmitb2eMstusrrolesDao;
//    

    private String userId(VoUserCred user) {
        String domain = "";
        if (user == null) {
            return null;
        }
        if (!StringUtils.isEmpty(user.getDomain())) {
            domain = user.getDomain() + "\\";
        }
        return domain + user.getUsername();
    }
    
    @Override
    public DtoResponseWorkspace getFormAuthorization(VoUserCred userCred) {
        String userId = getUserId(userCred);
        List<Ahmitb2eMstusrroles> formFunctionList = ahmitb2eMstusrrolesDao.getListUserRole(userId);
        List<Map<String, String>> result = new ArrayList<>();
        
        for (Ahmitb2eMstusrroles data : formFunctionList) {
            Ahmitb2eMstusrrolesPk desc =  data.getAhmitb2eMstusrrolesPk();
            
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
        
        
        
//        System.out.println("============================= isi value daru formfunctionlist == " + formFunctionList);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, result);
    }
    
    private String getUserId(VoUserCred userCred){
        String userId = userCred.getUsername();
        
        if (!(!AhmStringUtil.hasValue(userCred.getDomain()) || "SYSTEM".equalsIgnoreCase(userCred.getDomain()))) {
            userId = userCred.getDomain() + "\\" + userCred.getUsername();
        }
        
        return userId;
    }
    
        private Vms022VoFormAuthorization getFormAuthorization(List<String> formFunctionList){
        Vms022VoFormAuthorization vms022VoFormAuthorization = new Vms022VoFormAuthorization();
        vms022VoFormAuthorization.setIsAdd(Boolean.TRUE);
        vms022VoFormAuthorization.setIsEdit(Boolean.TRUE);
        vms022VoFormAuthorization.setIsDelete(Boolean.TRUE);
        
        for(String s : formFunctionList){
            if(s.equals(Vms022Status.ADD.getMessage())){
                vms022VoFormAuthorization.setIsAdd(Boolean.TRUE);
            }
            else if(s.equals(Vms022Status.EDIT.getMessage())){
                vms022VoFormAuthorization.setIsEdit(Boolean.TRUE);
            }
            else if(s.equals(Vms022Status.DELETE.getMessage())){
                vms022VoFormAuthorization.setIsDelete(Boolean.TRUE);
            }
        }
        
        return vms022VoFormAuthorization;
    }

    @Override
    public DtoResponse getRoleByUserLogin(String plants, VoUserCred user) {
        System.out.println("================" + "SERVICE_IMPL | mau tau ini isinya apa " + "================");
        System.out.println("================" + userId(user) + "================");
        List<Ahmitb2eMstusrroles> roles = ahmitb2eMstusrrolesDao.getListUserRole(userId(user));
        System.out.println("================" + roles + "================");
        List<String> useroles = new ArrayList<>();
        List<String> userPlant = new ArrayList<>();
        HashMap<String, Object> h = new HashMap<>();
        if (roles != null && roles.size() > 0) {
            for (Ahmitb2eMstusrroles r : roles) {
                Ahmitb2eMstusrrolesPk k = r.getAhmitb2eMstusrrolesPk();
                String role = k.getVroleid();
                Pattern p = Pattern.compile("[A-Z_]+([0-9]+)");
                Matcher m = p.matcher(role);
                if (m.matches() && m.groupCount() > 0) {
                    String plant = m.group(1);
                    userPlant.add(plant);
                }
                useroles.add(role);
            }
        }
        h.put("roles", String.join(" ", useroles));
        h.put("plants", String.join(",", userPlant));
        h.put("plants_db", plants);
        if (!userPlant.isEmpty()) {
            plants = String.join(",", userPlant);
        } else {
            plants = "00000";
        }

        List<String> l = new ArrayList<>();
        if (!StringUtils.isEmpty(plants)) {
            l = vms022ObjectDao.getPlantsByUserId(plants);
        }

        boolean ok = (l != null && !l.isEmpty());
        StatusMsgEnum e = (ok) ? StatusMsgEnum.SUKSES : StatusMsgEnum.GAGAL;

        if (!ok) {
            h.put("error", "Plant user tidak ditemukan");
            h.put("plantIds", "");
        } else {
            h.put("success", "Plant user ada");
            h.put("plantIds", l);
        }

        return DtoHelper.constructResponse(e, h, useroles);
    }

    @Override
    public DtoResponseWorkspace showPlant() {
        List<Vms022VoLov> list = vms022ahmhrntmDtlprmgblsDao.lovPlant(null, true);
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, null, list);
    }

    @Override
    public DtoResponseWorkspace showMonitoring(DtoParamPaging dto) {
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getSearchData(dto, "");
        int count = vms022ahmhrntmHdrotsempsDao.countSearchData(dto, "");
        return DtoHelper.constructResponsePagingWorkspace(StatusMsgEnum.SUKSES, "SUCCESS", null, list, count);
    }

    @Override
    public DtoResponseWorkspace getExcel(DtoParamPaging dto) {
        List<Vms022VoMonitoring> list = vms022ahmhrntmHdrotsempsDao.getDataExcel(dto);
        int count = vms022ahmhrntmHdrotsempsDao.countDataExcel(dto);
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

        if ((!getdata.getPic().equalsIgnoreCase("RO_GAVMS_PICAHM")) || (!getdata.getPic().equalsIgnoreCase("RO_GAVMS_OFCSECT"))) {
            throw new Vms022Exception("Role tidak sesuai!");
        } else {
            try {
                AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                pk.setRotsempshs((getdata.getId()));

                AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                if (mp != null) {
                    mp.setVotsstts(getdata.getOutStatus());
                    vms022ahmhrntmHdrotsempsDao.update(mp);
                    return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, ("Approve success"), null, null);
                }
            } catch (Exception e) {
                return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data"), null, null);
            }
        }
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, ("Failed Approve data"), null, null);
    }

    @Override
    public DtoResponseWorkspace approving(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {

        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                System.out.println("============================= value dari vo.outstat = " + vo.getOutStatus());
                if (vo.getOutStatus().equalsIgnoreCase("")) {
                    throw new Vms022Exception("Role tidak sesuai!");
                } else {
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts(vo.getOutStatus());
                        mp.setLastModBy(userCred.getUserid());
                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();
                    }
                }
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Approve success", null, null);
        } else {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "Failed Approve data", null, null);
        }
    }

    @Override
    public DtoResponseWorkspace reject(Vms022VoMonitoring getdata, VoUserCred userCred) {
        if (getdata.getOutStatus().equalsIgnoreCase("")) {
            throw new Vms022Exception("Role tidak sesuai!");
        } else {
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
        }

    }

    @Override
    public DtoResponseWorkspace rejecting(List<Vms022VoMonitoring> getdata, VoUserCred userCred) {
        if (!getdata.isEmpty()) {
            for (Vms022VoMonitoring vo : getdata) {
                if (vo.getOutStatus().equalsIgnoreCase("")) {
                    throw new Vms022Exception("Role tidak sesuai!");
                } else {
                    AhmhrntmHdrotsempsPk pk = new AhmhrntmHdrotsempsPk();
                    pk.setRotsempshs((vo.getId()));
                    AhmhrntmHdrotsemps mp = vms022ahmhrntmHdrotsempsDao.findOne(pk);
                    if (mp != null) {
                        mp.setVotsstts(vo.getOutStatus());
                        mp.setVnoterejc(vo.getReasonReject());
//                        mp.setVotsstts("Waiting for Approval Security");
                        mp.setLastModBy(userCred.getUserid());
                        vms022ahmhrntmHdrotsempsDao.update(mp);
                        vms022ahmhrntmHdrotsempsDao.flush();
                    }
                }
            }
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "Reject success", null, null);
        } else {
            return DtoHelper.constructResponseWorkspace(StatusMsgEnum.GAGAL, "Failed Reject data", null, null);
        }
    }

    @Override
    public Workbook exportToExcelMainData(DtoParamPaging dto
    ) {
        Vms022VoMonitoring vo = new Vms022VoMonitoring();
        String valueStr = "";
        for (Map.Entry<String, Object> filter : dto.getSearch().entrySet()) {
            valueStr = filter.getValue().toString();
            if (filter.getKey().equalsIgnoreCase("outId") && valueStr != null && !valueStr.isEmpty()) {
                vo.setOutId(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("outName") && valueStr != null && !valueStr.isEmpty()) {
                vo.setOutName(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("persId") && valueStr != null && !valueStr.isEmpty()) {
                vo.setPersId(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("outType") && valueStr != null && !valueStr.isEmpty()) {
                vo.setOutType(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("company") && valueStr != null && !valueStr.isEmpty()) {
                vo.setCompany(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("outStatus") && valueStr != null && !valueStr.isEmpty()) {
                vo.setOutStatus(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("areaName") && valueStr != null && !valueStr.isEmpty()) {
                vo.setAreaName(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("vacStatus") && valueStr != null && !valueStr.isEmpty()) {
                vo.setVacStatus(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("beginDateText") && valueStr != null && !valueStr.isEmpty()) {
                vo.setBeginDateText(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("endDateText") && valueStr != null && !valueStr.isEmpty()) {
                vo.setEndDateText(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("passNumber") && valueStr != null && !valueStr.isEmpty()) {
                vo.setPassNumber(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("passExpiryDateText") && valueStr != null && !valueStr.isEmpty()) {
                vo.setPassExpiryDateText(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("modifyBy") && valueStr != null && !valueStr.isEmpty()) {
                vo.setModifyBy(valueStr);
            }
            if (filter.getKey().equalsIgnoreCase("modifyDateText") && valueStr != null && !valueStr.isEmpty()) {
                vo.setModifyDateText(valueStr);
            }
        }

        List<Vms022VoMonitoring> listData = vms022ahmhrntmHdrotsempsDao.getDataExcel(dto);

        HSSFRow rowDetail;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("Verification Personal Data Partner & Outsource ");
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

        if (vo.getOutId() != null) {
            ihdrrow++;
        }
        if (vo.getOutName() != null) {
            ihdrrow++;
        }
        if (vo.getPersId() != null) {
            ihdrrow++;
        }
        if (vo.getOutType() != null) {
            ihdrrow++;
        }
        if (vo.getCompany() != null) {
            ihdrrow++;
        }
        if (vo.getOutStatus() != null) {
            ihdrrow++;
        }
        if (vo.getAreaName() != null) {
            ihdrrow++;
        }
        if (vo.getVacStatus() != null) {
            ihdrrow++;
        }
        if (vo.getBeginDateText() != null) {
            ihdrrow++;
        }
        if (vo.getEndDateText() != null) {
            ihdrrow++;
        }
        if (vo.getPassNumber() != null) {
            ihdrrow++;
        }
        if (vo.getPassExpiryDateText() != null) {
            ihdrrow++;
        }
        if (vo.getModifyBy() != null) {
            ihdrrow++;
        }
        if (vo.getModifyDateText() != null) {
            ihdrrow++;
        }

        ihdrrow = ihdrrow + 1;

        HSSFRow row = null;
        for (int i = 0; i < 24; i++) {
            worksheet.addMergedRegion(new CellRangeAddress(ihdrrow, (ihdrrow + 1), i, i));
        }

        HSSFRow headerMerge = worksheet.createRow(ihdrrow);
        String[] arrHdr = {"Outsource ID", "Outsource Name", "NIK", "Outsource Type", "Outsource Company", "Outsource Status", "Plant", "Vaccine Status", "Begin Date Effective", "End Date Effective", "ID Card Number", "Expiry Date", "Modify By", "Modify Date"};

        for (int i = 0; i < arrHdr.length; i++) {
            createCellHeader(workbook, headerMerge, arrHdr[i], i);
        }

        Vms022VoMonitoring voData;
        // Set value
        if (!listData.isEmpty()) {
            int pointerRow = ihdrrow + 2;
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
                listCellD[4].setCellValue(voData.getOutType());
                listCellD[5] = rowDetail.createCell(5);
                listCellD[5].setCellValue(voData.getCompany());
                listCellD[6] = rowDetail.createCell(6);
                listCellD[6].setCellValue(voData.getOutStatus());
                listCellD[7] = rowDetail.createCell(7);
                listCellD[7].setCellValue(voData.getAreaName());
                listCellD[8] = rowDetail.createCell(8);
                listCellD[8].setCellValue(voData.getVacStatus());
                listCellD[9] = rowDetail.createCell(9);
                listCellD[9].setCellValue(DateUtil.stringToDate(voData.getBeginDateText(), "dd-MM-yyyy") != null ? sdf.format(DateUtil.stringToDate(voData.getBeginDateText(), "dd-MM-yyyy")) : "");
                listCellD[10] = rowDetail.createCell(10);
                listCellD[10].setCellValue(DateUtil.stringToDate(voData.getEndDateText(), "dd-MM-yyyy") != null ? sdf.format(DateUtil.stringToDate(voData.getEndDateText(), "dd-MM-yyyy")) : "");
                listCellD[11] = rowDetail.createCell(11);
                listCellD[11].setCellValue(voData.getPassNumber());
                listCellD[12] = rowDetail.createCell(12);
                listCellD[12].setCellValue(DateUtil.stringToDate(voData.getPassExpiryDateText(), "dd-MM-yyyy") != null ? sdf.format(DateUtil.stringToDate(voData.getPassExpiryDateText(), "dd-MM-yyyy")) : "");
                listCellD[13] = rowDetail.createCell(13);
                listCellD[13].setCellValue(voData.getModifyBy());
                listCellD[14] = rowDetail.createCell(14);
                listCellD[14].setCellValue(DateUtil.stringToDate(voData.getModifyDateText(), "dd-MM-yyyy") != null ? sdf.format(DateUtil.stringToDate(voData.getModifyDateText(), "dd-MM-yyyy")) : "");

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
        iprmrow = createCellParam(vo.getOutType(), "Outsource Type", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getCompany(), "Outsource Company", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getOutStatus(), "Outsource Status", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getAreaName(), "Plant", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getVacStatus(), "Vaccine Status", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParamDate(vo.getBeginDateText(), "Begin Date Effective", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParamDate(vo.getEndDateText(), "End Date Effective", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getPassNumber(), "ID Card Number", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParamDate(vo.getPassExpiryDateText(), "Expiry Date", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParam(vo.getModifyBy(), "Modify By", iprmrow, row, worksheet, cellHeader, styleHdr);
        iprmrow = createCellParamDate(vo.getModifyDateText(), "Modify Date", iprmrow, row, worksheet, cellHeader, styleHdr);

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
        cellTitle.setCellValue("Verification Personal Data Partner & Outsource ");
        cellTitle.setCellStyle(styleTitle);

        for (int x = 1; x <= worksheet.getRow(ihdrrow).getPhysicalNumberOfCells(); x++) {
            worksheet.autoSizeColumn(x, true);
        }

        return workbook;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            cellHeader.setCellValue(":");
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

    private int createCellParamDate(String valbgn, String vlabel, int iprmrow, HSSFRow row, HSSFSheet worksheet,
            HSSFCell cellHeader, HSSFCellStyle styleHdr) {
        if (valbgn != null) {
            row = worksheet.createRow(iprmrow);
            cellHeader = row.createCell(0);
            cellHeader.setCellValue(vlabel);
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(1);
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(2);
            cellHeader.setCellValue(":");
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(3);
            cellHeader.setCellValue(valbgn == null ? "" : valbgn);
            cellHeader.setCellStyle(styleHdr);
            cellHeader = row.createCell(4);
            cellHeader.setCellStyle(styleHdr);
//            cellHeader.setCellValue("to");
            cellHeader = row.createCell(5);
//            cellHeader.setCellValue(valend == null ? "" : valend);
            cellHeader.setCellStyle(styleHdr);
            iprmrow++;
        }
        return iprmrow;
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

}
