/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.service.impl;

import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsemps;
import id.co.ahm.ga.vms.app000.model.AhmhrntmHdrotsempsPk;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlotsregsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmDtlprmgblsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022AhmhrntmHdrotsempsDao;
import id.co.ahm.ga.vms.app022.dao.Vms022ObjectDao;
import id.co.ahm.ga.vms.app022.service.Vms022Service;
import id.co.ahm.ga.vms.app022.vo.Vms022VoLov;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
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
import id.co.ahm.ga.vms.app022.exception.Vms022Exception;
import id.co.ahm.ga.vms.app022.vo.Vms022VoFileAttachment;
import id.co.ahm.jx.b2e.app000.dao.Ahmitb2eMstusrrolesDao;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrroles;
import id.co.ahm.jx.b2e.app000.model.Ahmitb2eMstusrrolesPk;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @Qualifier("ahmitb2eMstusrrolesDao")
    private Ahmitb2eMstusrrolesDao ahmitb2eMstusrrolesDao;

    @Autowired
    @Qualifier("vms022ahmhrntmDtlprmgblsDao")
    private Vms022AhmhrntmDtlprmgblsDao vms022ahmhrntmDtlprmgblsDao;


    @Autowired
    @Qualifier("vms022ahmhrntmHdrotsempsDao")
    private Vms022AhmhrntmHdrotsempsDao vms022ahmhrntmHdrotsempsDao;


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

//    private Workbook getWorkbook(MultipartFile excel) throws IOException {
//        if (excel == null) {
//            throw new Vms022Exception("File yang diupload bukan format excel");
//        }
//        if (excel.getOriginalFilename().endsWith("xlsx")) {
//            return new XSSFWorkbook(excel.getInputStream());
//        } else {
//            throw new Vms022Exception("Ekstensi file yang diperbolehkan : xlsx");
//        }
//    }
//
//    private List<String> mapToList(Map<?, List<String>> map) {
//        List<String> list = new ArrayList<>();
//        map.values().forEach(err -> list.addAll(err));
//        return list;
//    }
    
    private String userId(VoUserCred user) {
        String domain = ""; 
        if (user==null) return null;
        if (!StringUtils.isEmpty(user.getDomain())) 
            domain = user.getDomain() + "\\";
        return domain + user.getUsername();         
    }

    @Override
    public DtoResponse getRoleByUserLogin(String plants, VoUserCred user) {
    List<Ahmitb2eMstusrroles> roles = ahmitb2eMstusrrolesDao.getListUserRole(userId(user));
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
