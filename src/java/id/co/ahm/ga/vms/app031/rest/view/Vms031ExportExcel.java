/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.rest.view;

import id.co.ahm.ga.vms.app031.constant.Vms031Constant;
import id.co.ahm.ga.vms.app031.vo.Vms031VoMonitoring;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031ExportExcel extends AbstractXlsView {

     @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            List<Vms031VoMonitoring> data = (List<Vms031VoMonitoring>) model.get("tx");
            Sheet sheet = workbook.createSheet("AHMGAVMS031");
            
            CellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
            
            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setBorderTop(CellStyle.BORDER_THIN);
            cellStyleNumber.setBorderBottom(CellStyle.BORDER_THIN);
            cellStyleNumber.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyleNumber.setBorderRight(CellStyle.BORDER_THIN);
            cellStyleNumber.setAlignment(CellStyle.ALIGN_RIGHT);

            Row header = sheet.createRow(0);
            
            createCellHeader(workbook, header, "Nrp", 0);
            createCellHeader(workbook, header, "Jenis Kartu Identitas", 1);
            createCellHeader(workbook, header, "No Identitas", 2);
            createCellHeader(workbook, header, "Nama", 3);
            createCellHeader(workbook, header, "Nik", 4);
            createCellHeader(workbook, header, "Jenis Kelamin", 5);
            createCellHeader(workbook, header, "Alamat Ktp", 6);
            createCellHeader(workbook, header, "Alamat Domisili", 7);
            createCellHeader(workbook, header, "Tipe Karyawan Blacklist", 8);
            createCellHeader(workbook, header, "Nama Perusahaan", 9);
            createCellHeader(workbook, header, "Alasan Blacklist", 10);
            createCellHeader(workbook, header, "Type Foto", 11);
            createCellHeader(workbook, header, "Nama Foto", 12);
            createCellHeader(workbook, header, "Extension Foto", 13);
            createCellHeader(workbook, header, "Path Foto", 14);
            createCellHeader(workbook, header, "Tanggal Start Effective", 15);
            createCellHeader(workbook, header, "Tanggal End Effective", 16);

            
        if(data!=null){
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_LEFT);
            int rowNum = 1;

            for (Vms031VoMonitoring cc : data) {
                Row row = sheet.createRow(rowNum++);
                int col = 0;
                createCell(style, row, (String) cc.getNrp(), col++);
                createCell(style, row, (String) cc.getJenisKartuIdentitas(), col++);
                createCell(style, row, (String) cc.getNoIdentitas(), col++);
                createCell(style, row, (String) cc.getNama(), col++);
                createCell(style, row, (String) cc.getNik(), col++);
                createCell(style, row, (String) cc.getJenisKelamin(), col++);
                createCell(style, row, (String) cc.getAlamatKtp(), col++);
                createCell(style, row, (String) cc.getAlamatDomisili(), col++);
                createCell(style, row, (String) cc.getTipeKaryawanBlacklist(), col++);
                createCell(style, row, (String) cc.getNamaPerusahaan(), col++);
                createCell(style, row, (String) cc.getAlasanBlacklist(), col++);
                createCell(style, row, (String) cc.getTypeFoto(), col++);
                createCell(style, row, (String) cc.getNamaFoto(), col++);
                createCell(style, row, (String) cc.getExtensionFoto(), col++);
                createCell(style, row, (String) cc.getPathFoto(), col++);
                createCell(style, row, (Date) cc.getTglStartEffective(), col++);
                createCell(style, row, (Date) cc.getTglEndEffective(), col++);
            }
            
            for (int x = 0; x < sheet.getRow(0).getPhysicalNumberOfCells(); x++) {
                sheet.autoSizeColumn(x, true);
            }
            
        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail generate excel file");
        }
        String fileName = Vms031Constant.FILENAME_EXCEL;
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xls\"");

    }
    
    private void createCellHeader(Workbook workbook, Row row, String obj, int col) {

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);

        font.setBold(true);
        style.setFont(font);
        Cell cell = row.createCell(col);
        cell.setCellStyle(style);
        cell.setCellValue(obj);
    }
    
    private void createCell(CellStyle style, Row row, Object obj, int cellNum){
        Cell cell = row.createCell(cellNum);
        
        if (obj instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            cell.setCellStyle(style);
            cell.setCellValue(sdf.format((Date) obj));
        }else if (obj instanceof Number) {
            if (obj instanceof BigDecimal) {
                cell.setCellStyle(style);
                cell.setCellValue(((BigDecimal) obj).doubleValue());
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
        }else if (obj instanceof String){
            cell.setCellStyle(style);
            cell.setCellValue((String) obj);
        }else{
            cell.setCellStyle(style);
            cell.setCellValue("");
        }
    }
}
