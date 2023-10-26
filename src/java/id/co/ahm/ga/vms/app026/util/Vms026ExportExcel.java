/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.util;

import id.co.ahm.ga.vms.app026.vo.Vms026VoMonitoringOutput;
import id.co.ahm.ga.vms.app026.view.Vms026BaseXlsxStreamingView;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author kahfi
 */
public class Vms026ExportExcel extends Vms026BaseXlsxStreamingView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest hsr, HttpServletResponse response) throws Exception {
        DtoParamPaging dtoParam = (DtoParamPaging) model.get("dtoParam");
        List<Vms026VoMonitoringOutput> data = (List<Vms026VoMonitoringOutput>) model.get("data");
 
        try {
            Map<String, Object> filters = dtoParam.getSearch();
            String invNo = "All";
            String status = "All";
            String visitorType = "All";
            String plant = "All";
            String loc = "All";
            String locSpec = "All";
            String purpose = "All";
            String startDateText = "All";
            String endDateText = "All";
            String company = "All";
            String totalQuota = "All";
            String picAhm = "All";
 
            for (Map.Entry<String, Object> filter : filters.entrySet()) {
                if (filter.getKey().equalsIgnoreCase("masterNo")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        invNo = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("status")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        status = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("visitorType")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        visitorType = valueStr;
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
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        plant = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("loc")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        loc = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("locSpec")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        locSpec = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("purpose")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        purpose = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("startDate")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        startDateText = valueStr;
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
 
                if (filter.getKey().equalsIgnoreCase("company")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (valueStr.equalsIgnoreCase(" ")) {
                        company = "-";
                    } else if (StringUtils.isNotEmpty(valueStr)) {
                        company = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("totalQuota")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (valueStr.equalsIgnoreCase(" ")) {
                        totalQuota = "0";
                    } else if (StringUtils.isNotEmpty(valueStr)) {
                        totalQuota = valueStr;
                    }
                }
 
                if (filter.getKey().equalsIgnoreCase("picAhm")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (valueStr.equalsIgnoreCase(" ")) {
                        picAhm = "-";
                    } else if (StringUtils.isNotEmpty(valueStr)) {
                        picAhm = valueStr;
                    }
                }
 
            }
 
            //for sheet naming start
            Sheet sheet = workbook.createSheet("Sheet1");
            //for sheet naming end
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
            sheet.setColumnWidth(0, 10000);      //Invitation No
            sheet.setColumnWidth(1, 7500);     //Status
            sheet.setColumnWidth(2, 10000);      //Visitor Type
            sheet.setColumnWidth(3, 7500);      //Plant
            sheet.setColumnWidth(4, 7500);      //Location
            sheet.setColumnWidth(5, 7500);      //Specific Location
            sheet.setColumnWidth(6, 7500);      //Purpose
            sheet.setColumnWidth(7, 7500);      //Start Date
            sheet.setColumnWidth(8, 7500);      //End Date
            sheet.setColumnWidth(9, 7500);      //Company
            sheet.setColumnWidth(10, 7500);     //Total Quota
            sheet.setColumnWidth(11, 7500);     //PIC AHM
 
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
            createCell(rowTitle, "Maintain Invitation for Visitor", col, styleHeader);
 
            rownum = rownum + 2;
 
            Row rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Invitation No", col++, styleFilter1);
            createCell(rowFilter, invNo, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Status", col++, styleFilter1);
            createCell(rowFilter, status, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Visitor Type", col++, styleFilter1);
            createCell(rowFilter, visitorType, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Plant", col++, styleFilter1);
            createCell(rowFilter, plant, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Location", col++, styleFilter1);
            createCell(rowFilter, loc, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Specific Location", col++, styleFilter1);
            createCell(rowFilter, locSpec, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Start Date", col++, styleFilter3);
            createCell(rowFilter, startDateText, col++, styleFilter2WithWrap);
            createCell(rowFilter, "EndDate", col++, styleFilter3);
            createCell(rowFilter, endDateText, col++, styleFilter2WithWrap);
 
            col = 0;
 
            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Company", col++, styleFilter3);
            createCell(rowFilter, company, col++, styleFilter2WithWrap);
            createCell(rowFilter, "PIC AHM", col++, styleFilter3);
            createCell(rowFilter, picAhm, col++, styleFilter2WithWrap);
 
            col = 0;
            rownum = rownum + 1;
 
            Row rowHeaderTable = sheet.createRow(rownum++);
            createCell(rowHeaderTable, "Invitation No", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Status", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Visitor Type", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Plant", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Location", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Specific Location", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Purpose", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Start Date", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "End Date", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Company", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Total Kuota", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "PIC AHM", col++, styleHeaderTable1);
 
            for (Vms026VoMonitoringOutput item : data) {
                col = 0;
                
                //<editor-fold defaultstate="collapsed" desc="Supplier Validation">
                //</editor-fold>
 
                //<editor-fold defaultstate="collapsed" desc="Access Validation">
                
//                </editor-fold>
                
                Row rowContentData = sheet.createRow(rownum++);
 
                createCell(rowContentData, item.getInvitNo(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getStatus(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getVisitorType(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getPlant(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getLoc(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getLocSpec(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getPurpose(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getStartDateText(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getEndDateText(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getCompany(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getTotalQuota(), col++, styleContentTable1WithWrap);
                createCell(rowContentData, item.getPicAhm(), col++, styleContentTable1WithWrap);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt = sdf.format(new Date());
 
        String fileName = "Maintain Invitation for Visitor - " + dt;
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xlsx\"");
    }
}
