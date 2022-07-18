/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.rest.view;

import id.co.ahm.ga.vms.app021.exception.Vms021Exception;
import id.co.ahm.ga.vms.app021.vo.Vms021VoMonitoring;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

/**
 *
 * @author ayik.op
 */
public class Vms021ExportDataView extends AbstractXlsxView{
    
    private final String GAVMS021_TEMPLATE_PATH = "/data/AHMGA/VMS/Registrasi/";
    //private final String GAVMS021_TEMPLATE_PATH = "D:\\Download\\template\\";
    
    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        InputStream is = null;
        Workbook wb = null;
        String filedoc = null;
        String fileName = "";
        try {
            Map<String, Object> map = (Map<String, Object>) model.get("param");
            Map<String, Object> filters = (Map<String, Object>) map.get("input");
            filedoc = GAVMS021_TEMPLATE_PATH + "RegistrationPartnerOutsourceTemplate.xlsx";

            is = new FileInputStream(filedoc);
            wb = WorkbookFactory.create(is);

        } catch (EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
            throw new Vms021Exception("Failed to get file for template ");
        } catch (IOException ex) {
            Logger.getLogger(Vms021ExportDataView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;

        //return new SXSSFWorkbook(null, 1000, true);
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest hsr, HttpServletResponse response) throws Exception {

        Map<String, Object> map = (Map<String, Object>) model.get("param");
        String fileName = "";
        try {

            //Sheet sheet = workbook.createSheet("Sheet1");
            Sheet sheet = workbook.getSheetAt(0);
            DtoResponse responsePosition = (DtoResponse) map.get("tx");
            Map<String, Object> filters = (Map<String, Object>) map.get("input");
            List<Vms021VoMonitoring> responseVos = new ArrayList<>();

            if (responsePosition.getData().size() > 0 && responsePosition.getData().get(0).getClass().equals(Vms021VoMonitoring.class)) {
                responseVos = responsePosition.getData();
            }

            String outId = filters.get("outId") != null ? filters.get("outId").toString() : "";
            String outName = filters.get("outName") != null ? filters.get("outName").toString() : "";
            String nik = filters.get("nik") != null ? filters.get("nik").toString() : "";
            String beginDate = filters.get("beginDate") != null ? filters.get("beginDate").toString() : "";
            String endDate = filters.get("endDate") != null ? filters.get("endDate").toString() : "";
            String passNumber = filters.get("passNumber") != null ? filters.get("passNumber").toString() : "";
            String pic = filters.get("picName") != null ? filters.get("picName").toString() : "";
            String outType = filters.get("outType") != null ? filters.get("outType").toString() : "";
            String company = filters.get("company") != null ? filters.get("company").toString() : "";
            String outStatus = filters.get("outStatus") != null ? filters.get("outStatus").toString() : "";
            String plant = filters.get("plantName") != null ? filters.get("plantName").toString() : "";
            String vacStatus = filters.get("vacStatus") != null ? filters.get("vacStatus").toString() : "";

            CellStyle styleHeader = workbook.createCellStyle();

            CellStyle styleBody = workbook.createCellStyle();

            CellStyle styleBodyRed = workbook.createCellStyle();
            Font newFont = sheet.getWorkbook().createFont();
            newFont.setFontHeightInPoints(Short.valueOf("8"));
            newFont.setColor(IndexedColors.WHITE.getIndex());
            //styleBodyRed.setFillBackgroundColor(IndexedColors.RED.getIndex());
            //styleBodyRed.setFont(newFont);
            styleBodyRed.setFillForegroundColor(IndexedColors.RED.getIndex());
            styleBodyRed.setFillPattern(CellStyle.SOLID_FOREGROUND);

            CellStyle center = workbook.createCellStyle();
            center.setAlignment(CellStyle.ALIGN_CENTER);
            center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            center.setBorderTop(HSSFCellStyle.BORDER_THIN);
            center.setBorderRight(HSSFCellStyle.BORDER_THIN);
            center.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            CellStyle styleNo = workbook.createCellStyle();
            styleNo.setAlignment(CellStyle.ALIGN_CENTER);

            CellStyle combineAlign = workbook.createCellStyle();
            combineAlign.cloneStyleFrom(center);
            combineAlign.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            //TITLE
            //Row row = sheet.createRow(0);
            //createCellTitle(workbook, row, "External Work Partner & Outsource Registration", 0);

            //HEADER
            Row row3 = sheet.createRow(2);
            createCell(styleHeader, row3, "Outsource ID ", 0, false);
            createCell(styleHeader, row3, ": " + outId, 1, false);
            createCell(styleHeader, row3, "Outsource Type ", 3, false);
            createCell(styleHeader, row3, ": " + outType, 4, false);

            Row row4 = sheet.createRow(3);
            createCell(styleHeader, row4, "Outsource Name ", 0, false);
            createCell(styleHeader, row4, ": " + outName, 1, false);
            createCell(styleHeader, row4, "Outsource Company ", 3, false);
            createCell(styleHeader, row4, ": " + company, 4, false);

            Row row5 = sheet.createRow(4);
            createCell(styleHeader, row5, "NIK ", 0, false);
            createCell(styleHeader, row5, ": " + nik, 1, false);
            createCell(styleHeader, row5, "Outsource Status ", 3, false);
            createCell(styleHeader, row5, ": " + outStatus, 4, false);

            Row row6 = sheet.createRow(5);
            createCell(styleHeader, row6, "Periode ", 0, false);
            createCell(styleHeader, row6, ": " + beginDate + " To " + endDate, 1, false);
            createCell(styleHeader, row6, "Plant ", 3, false);
            createCell(styleHeader, row6, ": " + plant, 4, false);

            Row row7 = sheet.createRow(6);
            createCell(styleHeader, row7, "Pass Card Number ", 0, false);
            createCell(styleHeader, row7, ": " + passNumber, 1, false);
            createCell(styleHeader, row7, "Covid19 Vaccine Status ", 3, false);
            createCell(styleHeader, row7, ": " + vacStatus, 4, false);

            Row row8 = sheet.createRow(7);
            createCell(styleHeader, row8, "PIC AHM ", 0, false);
            createCell(styleHeader, row8, ": " + pic, 1, false);

            //BODY
            int rowNum = 10;
            int nomor = 1;
            for (Vms021VoMonitoring data : responseVos) {
                Row rowBody = sheet.createRow(rowNum++);
                int col = 0;
                createCell(styleBody, rowBody, nomor++, col++, false);
                createCell(styleBody, rowBody, data.getOutId(), col++, false);
                createCell(styleBody, rowBody, data.getOutName(), col++, false);
                createCell(styleBody, rowBody, data.getPhoneNo(), col++, false);
                createCell(styleBody, rowBody, data.getPersId(), col++, false);
                createCell(styleBody, rowBody, data.getOutTypeName(), col++, false);
                createCell(styleBody, rowBody, data.getSupplier().equalsIgnoreCase("S") ? "Supplier" : "Non Supplier", col++, false);
                createCell(styleBody, rowBody, data.getCompanyName(), col++, false);
                createCell(styleBody, rowBody, data.getOutStatus(), col++, false);
                createCell(styleBody, rowBody, data.getJob(), col++, false);
                createCell(styleBody, rowBody, data.getAreaName(), col++, false);
                createCell(styleBody, rowBody, data.getAccess(), col++, false);
                createCell(styleBody, rowBody, data.getGateName(), col++, false);
                createCell(styleBody, rowBody, data.getBeginDateText(), col++, false);
                createCell(styleBody, rowBody, data.getEndDateText(), col++, false);
                createCell(styleBody, rowBody, data.getPassExpiryDateText(), col++, false);
                createCell(styleBody, rowBody, data.getPassNumber(), col++, false);
                createCell(styleBody, rowBody, data.getVacStatus(), col++, false);
                createCell(styleBody, rowBody, data.getVacTypeName(), col++, false);
                createCell(styleBody, rowBody, data.getVacDateText(), col++, false);
                createCell(styleBody, rowBody, data.getVacSummary(), col++, false);
                createCell(styleBody, rowBody, data.getVacNote(), col++, false);
                createCell(styleBody, rowBody, data.getPic(), col++, false);
                createCell(styleBody, rowBody, data.getModifyBy(), col++, false);
                createCell(styleBody, rowBody, data.getModifyDateText(), col++, false);                    

            }

            fileName = "Registration_Partner_Outsource";
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Generate Excel Failed");
        }

        response.setHeader("Content-Disposition", "attachment;filename=\"" + AhmStringUtil.concatWithDate(fileName, ".xlsx\""));

    }

    private void createCellTitle(Workbook workbook, Row row, String obj, int col) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        Cell cell = row.createCell(col);
        cell.setCellStyle(style);
        cell.setCellValue(obj);
    }

    private void createCell(CellStyle style, Row row, Object obj, int cellNum, boolean isTime) {
        Cell cell = row.createCell(cellNum);
        if (obj == null) {
            cell.setCellStyle(style);
            cell.setCellValue("");
        }
        if (obj instanceof Date) {
            com.ibm.icu.text.SimpleDateFormat sdf = new com.ibm.icu.text.SimpleDateFormat("dd-MMM-yyyy");
            if (isTime) {
                sdf = new com.ibm.icu.text.SimpleDateFormat("HH:mm", Locale.ENGLISH);
            }
            cell.setCellStyle(style);
            cell.setCellValue(sdf.format((Date) obj));

        } else if (obj instanceof Number) {
            if (obj instanceof BigDecimal) {
                cell.setCellStyle(style);
                //cell.setCellValue(((BigDecimal) obj).toPlainString());
                cell.setCellValue(((BigDecimal) obj).intValue());
            } else if (obj instanceof Double) {
                cell.setCellStyle(style);
                cell.setCellValue(((Double) obj).doubleValue());
            } else if (obj instanceof Long) {
                cell.setCellStyle(style);
                cell.setCellValue(((Long) obj).doubleValue());
            } else {
                cell.setCellStyle(style);
                cell.setCellValue((Integer) obj);
            }
        } else {
            cell.setCellStyle(style);
            cell.setCellValue((String) obj);
        }
    }

}
