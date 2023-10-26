/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.util;

import id.co.ahm.ga.vms.app030.view.Vms030BaseXlsxStreamingView;
import id.co.ahm.ga.vms.app030.vo.Vms030VoTableResult;
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
 * @author Nurvan Afandi
 */
public class Vms030ExportExcel extends Vms030BaseXlsxStreamingView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest hsr, HttpServletResponse response) throws Exception {
        DtoParamPaging dtoParam = (DtoParamPaging) model.get("dtoParam");
        List<Vms030VoTableResult> data = (List<Vms030VoTableResult>) model.get("data");
        
        try {
            Map<String, Object> filters = dtoParam.getSearch();
            String visitorType = "All";
            String status = "All";
            String noDoc = "All";
            String workDesc = "All";
            String plant = "All";
            String company = "All";
            String nrp = "All";
            String email = "All";
            String dateStartText = "All";
            String dateEndText = "All";
            
            for (Map.Entry<String, Object> filter : filters.entrySet()) {
                
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
                
                if (filter.getKey().equalsIgnoreCase("noDoc")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        noDoc = valueStr;
                    }
                }
                
                if (filter.getKey().equalsIgnoreCase("workDesc")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        workDesc = valueStr;
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
                
                if (filter.getKey().equalsIgnoreCase("nrp")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        nrp = valueStr;
                    }
                }
                
                if (filter.getKey().equalsIgnoreCase("email")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        email = valueStr;
                    }
                }
                
                if (filter.getKey().equalsIgnoreCase("dateStart")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        dateStartText = valueStr;
                    }
                }
                
                if (filter.getKey().equalsIgnoreCase("dateEnd")) {
                    Object valueObject = filter.getValue();
                    String valueStr;
 
                    if (valueObject == null) {
                        valueStr = "";
                    } else {
                        valueStr = valueObject.toString();
                    }
 
                    if (StringUtils.isNotEmpty(valueStr)) {
                        dateEndText = valueStr;
                    }
                }
            }
                
            //for sheet naming start
            Sheet sheet = workbook.createSheet("Sheet1");
            //for sheet naming end
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
            sheet.setColumnWidth(0, 10000);      //Jenis Peserta
            sheet.setColumnWidth(1, 7500);     //Status
            sheet.setColumnWidth(2, 10000);      //No Document Reference
            sheet.setColumnWidth(3, 10000);      //Deskripsi Pekerjaan
            sheet.setColumnWidth(4, 7500);      //Plant
            sheet.setColumnWidth(5, 10000);      //Perusahaan
            sheet.setColumnWidth(6, 7500);      //NRP PIC Project
            sheet.setColumnWidth(7, 7500);      //Email PIC Project
            sheet.setColumnWidth(8, 7500);      //Tanggal Mulai
            sheet.setColumnWidth(9, 7500);      //Tanggal Selesai

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
            createCell(rowTitle, "Maintain Reference Document", col, styleHeader);

            rownum = rownum + 2;

            Row rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Jenis Perserta", col++, styleFilter1);
            createCell(rowFilter, visitorType, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Status", col++, styleFilter1);
            createCell(rowFilter, status, col++, styleFilter2WithWrap);

            col = 0;

            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "No Reference Document", col++, styleFilter1);
            createCell(rowFilter, noDoc, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Deskripsi Pekerjaan", col++, styleFilter1);
            createCell(rowFilter, workDesc, col++, styleFilter2WithWrap);

            col = 0;

            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Plant", col++, styleFilter1);
            createCell(rowFilter, plant, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Perusahaan", col++, styleFilter1);
            createCell(rowFilter, company, col++, styleFilter2WithWrap);

            col = 0;

            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "NRP PIC Project", col++, styleFilter3);
            createCell(rowFilter, nrp, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Email PIC Project", col++, styleFilter3);
            createCell(rowFilter, email, col++, styleFilter2WithWrap);

            col = 0;

            rowFilter = sheet.createRow(rownum++);
            createCell(rowFilter, "Tanggal Mulai", col++, styleFilter3);
            createCell(rowFilter, dateStartText, col++, styleFilter2WithWrap);
            createCell(rowFilter, "Tanggal Selesai", col++, styleFilter3);
            createCell(rowFilter, dateEndText, col++, styleFilter2WithWrap);

            col = 0;
            rownum = rownum + 1;

            Row rowHeaderTable = sheet.createRow(rownum++);
            createCell(rowHeaderTable, "Jenis Perserta", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Status", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "No Reference Document", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Deskripsi Pekerjaan", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Plant", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Perusahaan", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "NRP PIC Project", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Email PIC Project", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Tanggal Mulai", col++, styleHeaderTable1);
            createCell(rowHeaderTable, "Tanggal Selesai", col++, styleHeaderTable1);

            for (Vms030VoTableResult item : data) {
            col = 0;

            //<editor-fold defaultstate="collapsed" desc="Supplier Validation">
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Access Validation">

//                </editor-fold>

            Row rowContentData = sheet.createRow(rownum++);

            createCell(rowContentData, item.getVisitorType(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getStatus(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getNoDoc(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getWorkDesc(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getPlant(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getCompany(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getNrp(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getEmail(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getDateStartText(), col++, styleContentTable1WithWrap);
            createCell(rowContentData, item.getDateEndText(), col++, styleContentTable1WithWrap);
            }     
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());    
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt = sdf.format(new Date());
 
        String fileName = "Maintain Reference Document - " + dt;
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xlsx\"");
    }
    
}
