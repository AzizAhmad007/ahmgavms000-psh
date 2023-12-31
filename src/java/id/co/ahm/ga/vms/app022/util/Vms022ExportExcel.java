/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.util;
 
import id.co.ahm.ga.vms.app022.view.Vms022BaseXlsxStreamingView;
import id.co.ahm.ga.vms.app022.vo.Vms022VoMonitoring;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.commons.lang3.StringUtils;
 
/**
 *
 * @author reza.mr
 */
public class Vms022ExportExcel extends Vms022BaseXlsxStreamingView {
 
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest hsr, HttpServletResponse response) throws Exception {
        DtoParamPaging dtoParam = (DtoParamPaging) model.get("dtoParam");
        List<Vms022VoMonitoring> data = (List<Vms022VoMonitoring>) model.get("data");
 
        try {
            Map<String, Object> filters = dtoParam.getSearch();
            String outId = "All";
            String outName = "All";
            String persId = "All";
            String beginDateText = "-";
            String endDateText = "-";
            String passNumber = "All";
            String pic = "All";
            String outType = "All";
            String company = "All";
            String outStatus = "All";
            String areaName = "All";
            String vacStatus = "All";
 
            for (Map.Entry<String, Object> filter : filters.entrySet()) {
                if (filter.getKey().equalsIgnoreCase("outId")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        outId = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("outName")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        outName = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("nik")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        persId = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("beginDate")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        beginDateText = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("endDate")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        endDateText = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("passNumber")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        passNumber = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("pic")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        pic = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("outType")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        outType = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("company")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        company = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("outStatus")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (valueStr.equalsIgnoreCase(" ")) {
                        outStatus = "All";
                    } else if (StringUtils.isNotEmpty(valueStr)) {
                        outStatus = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("plant")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (valueStr.equalsIgnoreCase(" ")) {
                        outStatus = "All";
                    } else if (StringUtils.isNotEmpty(valueStr)) {
                        areaName = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("vacStatus")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (valueStr.equalsIgnoreCase(" ")) {
                        outStatus = "All";
                    } else if (StringUtils.isNotEmpty(valueStr)) {
                        vacStatus = valueStr;
                    }
                }
 
            }
 
            //for sheet naming start
            Sheet sheet = workbook.createSheet("Sheet1");
            //for sheet naming end
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
            sheet.setColumnWidth(0, 7500);      //Outsource ID
            sheet.setColumnWidth(1, 10000);     //Outsource Name
            sheet.setColumnWidth(2, 7500);      //Phone Number Outsource
            sheet.setColumnWidth(3, 7500);      //NIK
            sheet.setColumnWidth(4, 7500);      //Outsource Type
            sheet.setColumnWidth(5, 7500);      //Supplier
            sheet.setColumnWidth(6, 7500);      //Outsource Company
            sheet.setColumnWidth(7, 7500);      //Outsource Status
            sheet.setColumnWidth(8, 7500);      //Job Detail
            sheet.setColumnWidth(9, 7500);      //Plant
            sheet.setColumnWidth(10, 7500);     //Access
            sheet.setColumnWidth(11, 7500);     //Gate Level Access
            sheet.setColumnWidth(12, 7500);     //Begin Work Eff Date
            sheet.setColumnWidth(13, 7500);     //End Work Eff Date
            sheet.setColumnWidth(14, 7500);     //Pass Card Expiry Date
            sheet.setColumnWidth(15, 7500);     //Pass Card Number
            sheet.setColumnWidth(16, 10000);    //Note                          //new
            sheet.setColumnWidth(17, 7500);     //Covid19 Vaccine Status
            sheet.setColumnWidth(18, 7500);     //Covid19 Last Vaccinate Type
            sheet.setColumnWidth(19, 7500);     //Covid19 Last Vaccinate Date
            sheet.setColumnWidth(20, 10000);    //Covid19 Summary Vaccinate Status
            sheet.setColumnWidth(21, 10000);    //Notes Covid19 Vaccinate Status
            sheet.setColumnWidth(22, 7500);     //PIC AHM
            sheet.setColumnWidth(23, 7500);     //Modified By
            sheet.setColumnWidth(24, 7500);     //Modified Date
 
            Font headerFont = sheet.getWorkbook().createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 18);
 
            Font fontFilter1 = sheet.getWorkbook().createFont();
            fontFilter1.setBold(true);
            fontFilter1.setFontHeightInPoints((short) 12);
 
            Font fontFilter2 = sheet.getWorkbook().createFont();
            fontFilter2.setFontHeightInPoints((short) 12);
 
            Font fontHeaderTable1 = sheet.getWorkbook().createFont();
            fontHeaderTable1.setBold(true);
            fontHeaderTable1.setFontHeightInPoints((short) 12);
 
            Font fontContentTable1 = sheet.getWorkbook().createFont();
            fontContentTable1.setFontHeightInPoints((short) 12);
 
            CellStyle styleHeader = workbook.createCellStyle();
            styleHeader.setFont(headerFont);
            styleHeader.setAlignment(CellStyle.ALIGN_LEFT);
 
            CellStyle styleFilter1 = workbook.createCellStyle();
            styleFilter1.setFont(fontFilter1);
            styleFilter1.setBorderTop((short) 1);
            styleFilter1.setBorderRight((short) 1);
            styleFilter1.setBorderLeft((short) 1);
            styleFilter1.setAlignment(CellStyle.ALIGN_LEFT);
            styleFilter1.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            CellStyle styleFilter2 = workbook.createCellStyle();
            styleFilter2.setFont(fontFilter2);
            styleFilter2.setAlignment(CellStyle.ALIGN_LEFT);
 
            CellStyle styleFilter2WithWrap = workbook.createCellStyle();
            styleFilter2WithWrap.setFont(fontFilter2);
            styleFilter2WithWrap.setAlignment(CellStyle.ALIGN_LEFT);
            styleFilter2WithWrap.setWrapText(true);
            styleFilter2WithWrap.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            CellStyle styleFilter3 = workbook.createCellStyle();
            styleFilter3.setFont(fontFilter1);
            styleFilter3.setBorderTop((short) 1);
            styleFilter3.setBorderRight((short) 1);
            styleFilter3.setBorderBottom((short) 1);
            styleFilter3.setBorderLeft((short) 1);
            styleFilter3.setAlignment(CellStyle.ALIGN_LEFT);
 
            CellStyle styleFilter3WithWrap = workbook.createCellStyle();
            styleFilter3WithWrap.setFont(fontFilter1);
            styleFilter3WithWrap.setBorderTop((short) 1);
            styleFilter3WithWrap.setBorderRight((short) 1);
            styleFilter3WithWrap.setBorderBottom((short) 1);
            styleFilter3WithWrap.setBorderLeft((short) 1);
            styleFilter3WithWrap.setAlignment(CellStyle.ALIGN_LEFT);
            styleFilter3WithWrap.setWrapText(true);
            styleFilter3WithWrap.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            CellStyle styleHeaderTable1 = workbook.createCellStyle();
            styleHeaderTable1.setFont(fontHeaderTable1);
            styleHeaderTable1.setBorderTop((short) 1);
            styleHeaderTable1.setBorderBottom((short) 1);
            styleHeaderTable1.setBorderLeft((short) 1);
            styleHeaderTable1.setBorderRight((short) 1);
            styleHeaderTable1.setAlignment(CellStyle.ALIGN_CENTER);
 
            CellStyle styleHeaderTable2 = workbook.createCellStyle();
            styleHeaderTable2.setFont(fontHeaderTable1);
            styleHeaderTable2.setBorderTop((short) 1);
            styleHeaderTable2.setBorderRight((short) 1);
            styleHeaderTable2.setBorderBottom((short) 1);
            styleHeaderTable2.setBorderLeft((short) 1);
            styleHeaderTable2.setAlignment(CellStyle.ALIGN_CENTER);
 
            CellStyle styleContentTable1 = workbook.createCellStyle();
            styleContentTable1.setFont(fontContentTable1);
            styleContentTable1.setBorderBottom((short) 1);
            styleContentTable1.setBorderLeft((short) 1);
            styleContentTable1.setAlignment(CellStyle.ALIGN_LEFT);
            styleContentTable1.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            CellStyle styleContentTable1WithWrap = workbook.createCellStyle();
            styleContentTable1WithWrap.setFont(fontContentTable1);
            styleContentTable1WithWrap.setBorderBottom((short) 1);
            styleContentTable1WithWrap.setBorderLeft((short) 1);
            styleContentTable1WithWrap.setAlignment(CellStyle.ALIGN_LEFT);
            styleContentTable1WithWrap.setWrapText(true);
            styleContentTable1WithWrap.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            CellStyle styleContentTable2 = workbook.createCellStyle();
            styleContentTable2.setFont(fontContentTable1);
            styleContentTable2.setBorderBottom((short) 1);
            styleContentTable2.setBorderLeft((short) 1);
            styleContentTable2.setAlignment(CellStyle.ALIGN_CENTER);
            styleContentTable2.setVerticalAlignment(CellStyle.VERTICAL_TOP);
  
            CellStyle styleContentTable2WithWrap = workbook.createCellStyle();
            styleContentTable2WithWrap.setFont(fontContentTable1);
            styleContentTable2WithWrap.setBorderBottom((short) 1);
            styleContentTable2WithWrap.setBorderLeft((short) 1);
            styleContentTable2WithWrap.setBorderRight((short) 1);
            styleContentTable2WithWrap.setAlignment(CellStyle.ALIGN_CENTER);
            styleContentTable2WithWrap.setWrapText(true);
            styleContentTable2WithWrap.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            CellStyle styleContentTable3 = workbook.createCellStyle();
            styleContentTable3.setFont(fontContentTable1);
            styleContentTable3.setBorderBottom((short) 1);
            styleContentTable3.setBorderRight((short) 1);
            styleContentTable3.setBorderLeft((short) 1);
            styleContentTable3.setAlignment(CellStyle.ALIGN_LEFT);
            styleContentTable3.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            CellStyle styleContentTable3WithWrap = workbook.createCellStyle();
            styleContentTable3WithWrap.setFont(fontContentTable1);
            styleContentTable3WithWrap.setBorderBottom((short) 1);
            styleContentTable3WithWrap.setBorderRight((short) 1);
            styleContentTable3WithWrap.setBorderLeft((short) 1);
            styleContentTable3WithWrap.setAlignment(CellStyle.ALIGN_LEFT);
            styleContentTable3WithWrap.setWrapText(true);
            styleContentTable3WithWrap.setVerticalAlignment(CellStyle.VERTICAL_TOP);
 
            int col = 0;
            int rownum = 0;
 
            Row rowTitle = sheet.createRow(rownum);
            createCell(rowTitle, "Verification Personal Data Partner & Outsource", col, styleHeader);
 
            rownum = rownum + 2;
 
            Row rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "outsource ID", col++, styleFilter1);
            createCell(rowFilter, outId, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Outsource Type", col++, styleFilter1);
            createCell(rowFilter, outType, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Outsource Name", col++, styleFilter1);
            createCell(rowFilter, outName, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Outsource Company", col++, styleFilter1);
            createCell(rowFilter, company, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "NIK", col++, styleFilter1);
            createCell(rowFilter, persId, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Outsource Status", col++, styleFilter1);
            createCell(rowFilter, outStatus, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Periode", col++, styleFilter3);
            createCell(rowFilter, beginDateText, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Plant", col++, styleFilter3);
            createCell(rowFilter, areaName, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "To", col++, styleFilter3);
            createCell(rowFilter, endDateText, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Covid19 Vaccine Status", col++, styleFilter3);
            createCell(rowFilter, vacStatus, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Pass Card Number", col++, styleFilter3);
            createCell(rowFilter, passNumber, col++, styleFilter2WithWrap);
            createCell(rowFilter, "PIC AHM", col++, styleFilter3);
            createCell(rowFilter, pic, col++, styleFilter2WithWrap);
 
            col = 0;
            rownum = rownum + 1;
 
            Row rowHeaderTable = sheet.createRow(rownum++);
            createCell(rowHeaderTable, "Outsource ID", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Outsource Name", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Phone Number Outsource", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "NIK", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Outsource Type", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Supplier", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Outsource Company", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Outsource Status", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Job Detail", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Plant", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Access", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Gate Access Level", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Begin Work Effective Date", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "End Work Effective Date", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Pass Card Expiry Date", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Pass Card Number", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Note", col++, styleHeaderTable1);                      //new
            createCell(rowHeaderTable, "Covid19 Vaccine Status", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Covid19 Last Vaccinate Type", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Covid19 Last Vaccinate Date", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Covid19 Summary Vaccinate Status", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Notes Covid19 Vaccinate Status", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "PIC AHM", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Modified By", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Modified Date", col++, styleHeaderTable1);
 
            for (Vms022VoMonitoring item : data) {
                col = 0;
                
                //<editor-fold defaultstate="collapsed" desc="Supplier Validation">
                String supplier = "";
                if (item.getSupplier().equalsIgnoreCase("S")) {
                    supplier += "Supplier";
                } else if (item.getSupplier().equalsIgnoreCase("N")) {
                    supplier += "Non-Supplier";
                }
                //</editor-fold>
 
                //<editor-fold defaultstate="collapsed" desc="Access Validation">
                String access = "";
                if (item.getAccessReader().equalsIgnoreCase("Y")) {
                    access += "Absence Reader";
                    if (item.getCanteen().equalsIgnoreCase("Y")) {
                        access += "; ";
                    }
 
                    if (item.getCanteen().equalsIgnoreCase("Y")) {
                        access += "Canteen";
                    }
 
                    if (item.getSecurityGate().equalsIgnoreCase("Y")) {
                        access += "; ";
                    }
 
                    if (item.getSecurityGate().equalsIgnoreCase("Y")) {
                        access += "Security Gate";
                    }
 
                } else if (item.getCanteen().equalsIgnoreCase("Y")) {
                    access += "Canteen";
                    if (item.getSecurityGate().equalsIgnoreCase("Y")) {
                        access += "; ";
                    }
 
                    if (item.getSecurityGate().equalsIgnoreCase("Y")) {
                        access += "Security Gate";
                    }
 
                } else if (item.getSecurityGate().equalsIgnoreCase("Y")) {
                    access += "Security Gate";
                } else {
                    access += "-";
                }
//                </editor-fold>
                
                Row rowContentData = sheet.createRow(rownum++);
 
                createCell(rowContentData, item.getOutId(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getOutName(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getPhoneNo(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getPersId(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getOutTypeName(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, supplier, col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getCompanyName(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getOutStatus(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getJob(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getAreaName(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, access, col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getGateName(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getBeginDateText(), col++, styleContentTable2WithWrap);
                createCell(rowContentData, item.getEndDateText(), col++, styleContentTable2WithWrap);
                createCell(rowContentData, item.getPassExpiryDateText(), col++, styleContentTable2WithWrap);
                createCell(rowContentData, item.getPassNumber(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getNote(), col++, styleContentTable1WithWrap);          //new
                createCell(rowContentData, item.getVacStatus(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getVacTypeName(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getVacDateText(), col++, styleContentTable2WithWrap);
                createCell(rowContentData, item.getVacSummary(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getVacNote(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getPic(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getModifyBy(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getModifyDateText(), col++, styleContentTable2WithWrap);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt = sdf.format(new Date());
 
        String fileName = "Verification Personal Data Partner & Outsource - " + dt;
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xlsx\"");
    }
 
}