/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.rest.view;

import id.co.ahm.ga.vms.app031.vo.Vms031VoError;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031ExportExcelError  extends AbstractXlsView{
    
     @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return super.createWorkbook(model, request);
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        List<Vms031VoError> voList = (List<Vms031VoError>) data.get("data");        
        Sheet sheet = wrkbk.createSheet("Sheet1");
        int rowNum = 1;

        CellStyle cellStyle = wrkbk.createCellStyle();
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);

        Row rowTitle = sheet.createRow(0);
        CellStyle cellStyleTitle = wrkbk.createCellStyle();
        Font fontTitle = sheet.getWorkbook().createFont();
        fontTitle.setBold(true);
        fontTitle.setFontHeightInPoints((short) 20);
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleTitle.setFont(fontTitle);
        cellStyleTitle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        cellStyleTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        createCell(rowTitle, "Error Log", 0, cellStyleTitle);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 1);
        sheet.addMergedRegion(cellRangeAddress);
        RegionUtil.setBorderBottom(CellStyle.BORDER_THICK, cellRangeAddress, sheet, wrkbk);
        RegionUtil.setBorderLeft(CellStyle.BORDER_THICK, cellRangeAddress, sheet, wrkbk);
        RegionUtil.setBorderRight(CellStyle.BORDER_THICK, cellRangeAddress, sheet, wrkbk);
        RegionUtil.setBorderTop(CellStyle.BORDER_THICK, cellRangeAddress, sheet, wrkbk);

        CellStyle cellStyleHeader = wrkbk.createCellStyle();
        Font fontHeader = sheet.getWorkbook().createFont();
        fontHeader.setBold(true);
        cellStyleHeader.setFont(fontHeader);
        cellStyleHeader.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        cellStyleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyleHeader.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleHeader.setBorderBottom(CellStyle.BORDER_THICK);
        cellStyleHeader.setBorderTop(CellStyle.BORDER_MEDIUM);
        cellStyleHeader.setBorderLeft(CellStyle.BORDER_MEDIUM);
        cellStyleHeader.setBorderRight(CellStyle.BORDER_MEDIUM);
        int indexHeader = 0;
        Row rowHeader = sheet.createRow(rowNum++);
        createCell(rowHeader, "Cell", indexHeader++, cellStyleHeader); 
        createCell(rowHeader, "Error", indexHeader++, cellStyleHeader); 
        
        for (Vms031VoError vo : voList) {
            int col = 0;
            Row row = sheet.createRow(rowNum++);
            createCell(row, vo.getCell(), col++, cellStyle); 
            createCell(row, vo.getError(), col++, cellStyle);
        }
        for (int x = 0; x < sheet.getRow(1).getPhysicalNumberOfCells(); x++) {
            sheet.autoSizeColumn(x, true);
        }
        String filename = "Error_Log_upload_qty_stock_saranahandling_" + DateUtil.dateToString(new Date(), "ddMMyyyyhhmmss");
        hsr1.setHeader("Content-Disposition", "attachment;filename=\"" + filename + ".xls\"");
    }

    private void createCell(Row row, Object obj, int col, CellStyle cs) {
        Cell cell = row.createCell(col);
        if (!AhmStringUtil.hasValue(obj)) {
            cell.setCellValue("");
        } else if (obj instanceof BigDecimal) {
            BigDecimal bd = new BigDecimal(String.valueOf(obj));
            cell.setCellValue(bd.doubleValue());
        } else if (obj instanceof BigInteger) {
            cell.setCellValue(((BigInteger) obj).intValue());
        } else if (obj instanceof Character) {
            cell.setCellValue(((Character) obj));
        } else if (obj instanceof Date) {
            String val = DateUtil.dateToString((Date) obj, "dd-MMM-yyyy");
            cell.setCellValue(val);
        } else if (obj != null) {
            cell.setCellValue(String.valueOf(obj));
        }
        cell.setCellStyle(cs);
    }
}
