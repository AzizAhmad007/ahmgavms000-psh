/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.util;

import id.co.ahm.jx.util.ExcelUtil;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Yusuf Yadi Surya
 */
public class Vms022DownloadProcessor<T,U> {
    public String TEMPLATE_PACKAGE = "/data/deploy/download/ahmgaers005/";
    private T headerDto;
    private List<U> detailDto;
    private Vms022DownloadConfigDTO downloadConfigDTO;
    private String template;
    private Map<String, Integer> dynamicHeaders;
    private int dynamicHeaderAmount;

    public Vms022DownloadProcessor(T headerDto, List<U> detailDto, List<String> dynamicHeaders, Vms022DownloadConfigDTO downloadConfigDTO, String template) {
        this.headerDto = headerDto;
        this.detailDto = detailDto;
        this.downloadConfigDTO = downloadConfigDTO;
        this.template = template;
        if(dynamicHeaders!=null){
            this.dynamicHeaderAmount = dynamicHeaders.size();
            this.dynamicHeaders = new LinkedHashMap<>();
            for (String dynamicHeader : dynamicHeaders) {
                this.dynamicHeaders.put(dynamicHeader, dynamicHeaders.indexOf(dynamicHeader));
            }
        }       
    }
    
    public Vms022DownloadProcessor(T headerDto, List<U> detailDto, Vms022DownloadConfigDTO downloadConfigDTO, String template) {
        this.headerDto = headerDto;
        this.detailDto = detailDto;
        this.downloadConfigDTO = downloadConfigDTO;
        this.template = template;
    }
    
     
    Workbook wb = null;
    public Workbook generateExcels(Workbook wb) throws IOException, Exception {
        this.wb = wb;
        return generateExcels();
    }
    public Workbook generateExcels(InputStream inputStream) throws IOException, Exception {
        this.wb = new XSSFWorkbook(inputStream);
        return generateExcels();
    }
    public Workbook generateExcels() throws IOException, Exception {
        InputStream inputStream = null;
        try {
            
            if(wb==null){
                if(!template.endsWith("xlsx")&&!template.endsWith("xlsm")){
                    throw new IOException("Template ["+template+"] should be xlsx type.");
                }
                inputStream = new FileInputStream(new File(TEMPLATE_PACKAGE + template));
                if(inputStream==null){
                    throw new IOException("Template ["+template+"] could not read or unavailable.");
                }
                wb = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = wb.getSheetAt( this.downloadConfigDTO.getSheetNo());
            writeDynamicHeader(sheet);
            writeStatic(sheet);           
            
            if(downloadConfigDTO.getDetailMapping()!=null){
                writeDetail(sheet);
            }
            XSSFFormulaEvaluator.evaluateAllFormulaCells((XSSFWorkbook)wb);
        } catch(IOException io){
            throw io;
        } catch(Exception e){            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            sw.toString(); 
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Error generateExcels : " + e.getMessage() + " > " + sw.toString());
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return wb;
    }

    public void insertImage(Workbook wb, Sheet sheet, String imageSource, int startRow, int endRow, int startCol, int endCol) throws IOException {
        File f = new File(imageSource);
        insertImage(wb, sheet, f, startRow, endRow, startCol, endCol);
    }
    
    public Workbook lockSheet(int sheetNo,XSSFWorkbook wb, String password) {
        Sheet sheet = wb.getSheetAt(sheetNo);
        ExcelUtil.lock(sheet, wb, password);
        return wb;
    }
    
    private void writeStatic(Sheet sheet) {

        for (Map.Entry<String, Object> entry : downloadConfigDTO.getStaticData().entrySet()) {
              
            ExcelUtil.write(sheet, entry.getKey(), entry.getValue());          
        }
    }

    private void writeDetail(Sheet sheet) {
        String masterRegion = null;
        int iRow = downloadConfigDTO.getStartDetailRow();
        String region = ExcelUtil.numberToAlphabet(downloadConfigDTO.getStartColumn()) +"" +iRow+":" + ExcelUtil.numberToAlphabet(downloadConfigDTO.getEndColumn()) +"" +iRow;
        ExcelUtil.loadStyle("detail01", sheet, ExcelUtil.asRegion(region));
        ExcelUtil.loadMerge("detail01", sheet, ExcelUtil.asRegion(region));
        
        for (U detail: detailDto) {            
            if (downloadConfigDTO.isIsAddNumbering()) {
                ExcelUtil.write(sheet, iRow - 1, downloadConfigDTO.getNumberingColumn() - 1, iRow - downloadConfigDTO.getStartDetailRow() + 1);
            }
            if(downloadConfigDTO.getDetailMapping()!=null){                
                for (Map.Entry<Integer, String> entry : downloadConfigDTO.getDetailMapping().entrySet()) {
                    Object value = readValue(detail, entry.getValue());
                    if(value!=null && value instanceof HashMap){
                        Map<String,String> mappedDates = (Map<String,String>) value;
                        for (Map.Entry<String, Integer> header : dynamicHeaders.entrySet()) {
                            String actualValue = mappedDates.get(header.getKey());

                            
                            if(masterRegion == null){
                                masterRegion = ExcelUtil.numberToAlphabet(entry.getKey()) +"" +iRow+":"+ExcelUtil.numberToAlphabet(entry.getKey()) +"" +iRow;
                                ExcelUtil.loadStyle("detail02", sheet, ExcelUtil.asRegion(masterRegion));
                            }else{
                                region = ExcelUtil.numberToAlphabet(entry.getKey()+header.getValue()) +"" +iRow+":"+ExcelUtil.numberToAlphabet(entry.getKey()+header.getValue()) +"" +iRow;
                                ExcelUtil.applyStyle("detail02", sheet, ExcelUtil.asRegion(region));
                            }
                            
                            ExcelUtil.write(
                                    sheet, 
                                    iRow - 1, 
                                    (entry.getKey() - 1)+header.getValue(), 
                                    actualValue);
                        }

                    }else{
                        if(downloadConfigDTO.isUppercase() && value!=null){
                            value = value.toString().toUpperCase();
                        }
                        ExcelUtil.write(sheet, iRow - 1, entry.getKey() - 1, value);
                    }                    
                }
            }
            region = ExcelUtil.numberToAlphabet(downloadConfigDTO.getStartColumn()) +"" +iRow+":" + ExcelUtil.numberToAlphabet(downloadConfigDTO.getEndColumn()) +"" +iRow;
            if(iRow>downloadConfigDTO.getStartDetailRow() && ExcelUtil.mergeRegionMap.size()>0){
                ExcelUtil.applyMerge("detail01", sheet, ExcelUtil.asRegion(region));
            }
            
            ExcelUtil.applyStyle("detail01", sheet, ExcelUtil.asRegion(region));
            
            iRow++;
        }
    }
        
    private Object readValue(Object obj, String property) {
        try {
           PropertyDescriptor pd = new PropertyDescriptor(property, obj.getClass());
            Method getter = pd.getReadMethod();
            Object value = getter.invoke(obj);
            
            return value;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IntrospectionException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private void insertImage(Workbook wb, Sheet sheet, File f, int startRow, int endRow, int startCol, int endCol) throws FileNotFoundException, IOException {
        InputStream inputStream = new FileInputStream(f);
        
        byte[] bytes = IOUtils.toByteArray(inputStream);

        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        inputStream.close();

        
        if(f.exists()){
            f.delete();
        }

        CreationHelper helper = wb.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(startCol);
        anchor.setRow1(startRow);
        anchor.setCol2(endCol);
        anchor.setRow2(endRow);	   
        drawing.createPicture(anchor, pictureIdx);
    }

    private void writeDynamicHeader(Sheet sheet) {
        if(dynamicHeaders!=null){
            int row = downloadConfigDTO.getStartHeaderRow() - 1;
            int col = downloadConfigDTO.getEndColumn() - 1;
            int firstCol = col;
            
            String region = ExcelUtil.numberToAlphabet(col+1) +"" +(row)+":" + ExcelUtil.numberToAlphabet(col+1) +"" +(row+3);
            ExcelUtil.loadStyle("header01", sheet, ExcelUtil.asRegion(region));
            
            for (Map.Entry<String, Integer> entry : dynamicHeaders.entrySet()) {
                if(col!=firstCol){
                    region = ExcelUtil.numberToAlphabet(col+1) +"" +(row)+":" + ExcelUtil.numberToAlphabet(col+1) +"" +(row+3);
                    ExcelUtil.applyStyle("header01", sheet, ExcelUtil.asRegion(region));
                }
                String key = entry.getKey() + " (Actual Value)";
                ExcelUtil.write(sheet, row, col, key);
                col++;
            }
            
            if(dynamicHeaders.size() == 1){
                sheet.removeColumnBreak(col);
            }
        }              
    }
    
    
}
