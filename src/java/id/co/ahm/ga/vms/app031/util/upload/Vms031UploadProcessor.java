/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.upload;

import id.co.ahm.ga.vms.app031.constant.Vms031Constant;
import id.co.ahm.ga.vms.app031.exception.Vms031DateFormatException;
import id.co.ahm.ga.vms.app031.exception.Vms031Exception;
import id.co.ahm.ga.vms.app031.util.validator.Vms031ExtendedLogicValidator;
import id.co.ahm.ga.vms.app031.util.validator.Vms031FieldProperty;
import id.co.ahm.ga.vms.app031.vo.Vms031VoErrorUpload;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031UploadProcessor<T> {
    private static DecimalFormat df = new DecimalFormat();
    private Vms031UploadConfigDTO uploadConfigDTO;
    private InputStream inputStream;
    private String fileName;
    private Vms031UploadResultDTO<T> uploadResultDTO = new Vms031UploadResultDTO<T>();
    Workbook wb = null;
    private T clazz;
    private Class<T> mClass;
    private static SimpleDateFormat sdf;
    
    public Vms031UploadProcessor(Vms031UploadConfigDTO uploadConfigDTO, InputStream inputStream, String fileName, Class<T> mcl) {
        this.uploadConfigDTO = uploadConfigDTO;
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.mClass = mcl;
        try {
            clazz = this.mClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Vms031UploadProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Vms031UploadProcessor(Vms031UploadConfigDTO uploadConfigDTO, InputStream inputStream, String fileName) {
        this.uploadConfigDTO = uploadConfigDTO;
        this.inputStream = inputStream;
        this.fileName = fileName;
    }
    
     public Vms031UploadResultDTO<T> getTemplateValidation() {
        wb = null;
        Sheet sheet = null;
        try {

            if (fileName.endsWith("xlsx")) {
                wb = new XSSFWorkbook(inputStream);
            } else if (fileName.endsWith("xls")) {
                wb = new HSSFWorkbook(inputStream);
            }
            if (uploadConfigDTO.getSheetNo() != 0) {

                if (wb != null) {
                    sheet = wb.getSheetAt(uploadConfigDTO.getSheetNo());
                    uploadResultDTO.setSheetName(wb.getSheetAt(uploadConfigDTO.getSheetNo()).getSheetName());
                    if (validateTemplate(sheet, uploadResultDTO.getMessages())) {
                    }
                }
            }
        } catch (IOException ex) {
            uploadResultDTO.getMessages().add("Berkas salah. Tidak dapat membaca berkas: " + ex.getMessage());
        } catch (OfficeXmlFileException ex) {
            throw new Vms031DateFormatException("Format [file] salah. Format seharusnya [.xlsx]");
        } catch (Exception tes) {
            throw new Vms031Exception("Template file tidak sesuai, silahkan download template file yang sudah disediakan.");
        }
        return uploadResultDTO;
    } 
     
      private boolean validateTemplate(Sheet sheet, List<String> message) {
        boolean result = true;
        Vms031UploadSecurityConfiguration sec = uploadConfigDTO.getSecurity();
        if (sec != null) {
            try {
                Row row = sheet.getRow(sec.getAuthRow() - 1);
                Cell cell = row.getCell(sec.getAuthColumn() - 1);
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    String value = cell.getStringCellValue().trim();
                    if (!value.equals(sec.getAuthName())) {
                        throw new Exception();
                    } else {
                        result = true;
                    }
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                message.add("Template salah");
            }
        }
        return result;
    }
      
       public Vms031UploadResultDTO<T> getUploadResultHeader() {
        wb = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        List<T> result = new ArrayList<>();
        List<Vms031VoErrorUpload> errorMessageResult = new ArrayList<>();
        uploadResultDTO.setErrorResult(errorMessageResult);
        uploadResultDTO.setResult(result);
        try {

            List<PropertyDef> propertyDefs = getPropertyDefinition();
            if (uploadResultDTO.getErrorResult().size() > 0) {
                return uploadResultDTO;
            }

            getTemplateValidation();
            if (wb != null) {
                sheet = wb.getSheetAt(uploadConfigDTO.getSheetNo());
                uploadResultDTO.setSheetName(sheet.getSheetName());
                int lastRowNum = sheet.getLastRowNum() + 1;
                Logger.getLogger(Vms031UploadProcessor.class.getName()).log(Level.INFO, "Start Row : {0}", uploadConfigDTO.getStartRow());
                Logger.getLogger(Vms031UploadProcessor.class.getName()).log(Level.INFO, "Last Row : {0}", lastRowNum);
                for (int iRow = uploadConfigDTO.getStartRow() - 1; iRow < lastRowNum ; iRow++) {

                    row = sheet.getRow(iRow);
                    int excelRow = iRow + 1;
                    if (excelRow <= lastRowNum) {

                        //check if current row is black row then break
                        if (isBlankRow(row, uploadConfigDTO.getColumnPropertyMap())) {
                            errorMessageResult.add(
                                    new Vms031VoErrorUpload(
                                            errorMessageResult.size() + 1,
                                            excelRow,//getCellName(iRow, intCol), 
                                            "Baris Kosong",
                                            "Mohon Diperbaiki, Terdapat Baris yang Kosong")
                            );
                            continue;
                        }

                        //parameter setting
                        int intCol;
                        T instance = mClass.newInstance();
                        for (int i = 0; i < propertyDefs.size(); i++) {

                            Vms031UploadProcessor.PropertyDef propertyDef = propertyDefs.get(i);
                            intCol = propertyDef.position;
                            cell = row.getCell((short) (intCol - 1));

                            if (cell == null) {
                                if (!propertyDef.nullable) {
                                    errorMessageResult.add(
                                            new Vms031VoErrorUpload(
                                                    errorMessageResult.size() + 1,
                                                    excelRow,//getCellName(iRow, intCol), 
                                                    propertyDef.nameAlias,
                                                    propertyDef.nameAlias + " harus diisi")
                                    );
                                }
                                continue;
                            }

                            String stringCellValue = null;
                            if (Vms031Constant.STRING.equals(propertyDef.type)) {
                                Vms031ExtendedLogicValidator validator = uploadConfigDTO.extendedValidation.get(propertyDef.name);
                                try {
                                    stringCellValue = cell.getStringCellValue();
                                    if (stringCellValue != null && uploadConfigDTO.isUppercaseValue()) {
                                        stringCellValue = stringCellValue.toUpperCase();
                                    }

                                    if (propertyDef.valueCategory.equals("id_combo")) {
                                        if (stringCellValue.contains("-")) {
                                            stringCellValue = stringCellValue.split("-")[0].trim();
                                        }
                                    }

                                    if (validator != null) {
                                        if (propertyDef.nullable == false && !StringUtils.isNotEmpty(stringCellValue)) {
                                            errorMessageResult.add(
                                                    new Vms031VoErrorUpload(
                                                            errorMessageResult.size() + 1,
                                                            excelRow,//getCellName(iRow, intCol), 
                                                            propertyDef.nameAlias,
                                                            propertyDef.nameAlias + " harus diisi")
                                            );
                                            continue;
                                        }
                                    }
                                } catch (Exception nfe) {
                                    Double doubleCellValue = new Double(cell.getNumericCellValue());
                                    stringCellValue = format(doubleCellValue, "####.#####################");

                                    if (stringCellValue.endsWith(".0")) {
                                        stringCellValue = stringCellValue.substring(0, stringCellValue.indexOf(".0"));
                                    }
                                }

                                //check mandatory and value is empty
                                if (!propertyDef.nullable
                                        && stringCellValue.length() == 0) {
                                    errorMessageResult.add(
                                            new Vms031VoErrorUpload(
                                                    errorMessageResult.size() + 1,
                                                    excelRow,//getCellName(iRow, intCol), 
                                                    propertyDef.nameAlias,
                                                    propertyDef.nameAlias + " harus diisi")
                                    );
                                    continue;
                                }

                                //      Validasi Panjang Karakter
                                if (stringCellValue.length() > propertyDef.length) {
                                    errorMessageResult.add(
                                            new Vms031VoErrorUpload(
                                                    errorMessageResult.size() + 1,
                                                    excelRow,//getCellName(iRow, intCol), 
                                                    propertyDef.nameAlias,
                                                    propertyDef.nameAlias
                                                    + "("
                                                    + stringCellValue.length()
                                                    + ") tidak boleh lebih dari "
                                                    + propertyDef.length
                                                    + " karakter ")
                                    );
                                    continue;
                                }

                                setValuePropertyValue(instance, propertyDef.name, stringCellValue);

                            }
                        }

                        result.add(instance);
                    } else {

                        break;
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Vms031UploadProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OfficeXmlFileException ex) {
            throw new Vms031Exception("Format [file] salah. Format seharusnya [.xlsx]");
        }
        return uploadResultDTO;
    }
       
        public String getValue(int row, int column) {
        String result = "";
        if (wb != null) {
            Sheet sheet = wb.getSheetAt(uploadConfigDTO.getSheetNo());
            result = sheet.getRow(row).getCell(column).getStringCellValue();
        }
        return result;
    }
        
        public String numberToAlphabet(int i) {
        if (i < 27) {
            return new String(new char[]{(char) (i + 64)});
        } else {
            int intMod = i % 26;
            return numberToAlphabet((i - 1) / 26)
                    + numberToAlphabet(intMod == 0 ? 26 : intMod);
        }
    }
        
         public static Date convert(String stDate, String stFormat) throws ParseException {
        Date dtResutl;
        synchronized (sdf) {
            sdf.applyPattern(stFormat);
            dtResutl = stDate == null || stDate.length() == 0 ? null : sdf.parse(stDate);
        }
        return dtResutl;
    }
       
       public class PropertyDef {

        private int position;
        private String name;
        private String nameAlias;
        private String type;
        private String valueCategory;
        private String format;
        private int length;
        private int precision;
        private int scale;
        private boolean nullable;
        private boolean isValidateEffectiveDate;
    }
       
       private List<PropertyDef> getPropertyDefinition() {
        List<PropertyDef> propertyDefs = new ArrayList<>();

        Class cl = clazz.getClass();

        for (Map.Entry<Integer, String> entry : uploadConfigDTO.getColumnPropertyMap().entrySet()) {
            try {
                Field field = cl.getDeclaredField(entry.getValue());
                PropertyDef propertyDef = new PropertyDef();
                propertyDef.position = entry.getKey();
                propertyDef.name = entry.getValue();

                Vms031FieldProperty column = null;

                if (field.getAnnotations().length > 1) {
                    for (Annotation annotation : field.getAnnotations()) {
                        if (annotation instanceof Vms031FieldProperty) {
                        }
                    }
                } else {
                    column = (Vms031FieldProperty) field.getAnnotations()[0];
                }

                propertyDef.nameAlias = column.nameAlias();
                propertyDef.nullable = column.nullable();
                propertyDef.length = column.length();
                propertyDef.precision = column.precision();
                propertyDef.scale = column.scale();
                propertyDef.isValidateEffectiveDate = column.isValidateEffectiveDate();
                propertyDef.valueCategory = column.valueCategory();
                propertyDef.type = column.type();
                propertyDef.format = column.format();
                propertyDefs.add(propertyDef);

            } catch (NoSuchFieldException | SecurityException ex) {
                uploadResultDTO.getMessages().add("Property : " + entry.getValue() + " is not found in " + clazz.getClass().getName());
            }

        }
        return propertyDefs;
    }
       
       private boolean isBlankRow(Row row, Map<Integer, String> columnPropertyMap) {
        if (row == null) {
            return true;
        }

        boolean isBlankRow = true;
        Cell cell;

        for (Integer key : columnPropertyMap.keySet()) {

            cell = row.getCell((short) (key - 1));
            if (cell != null) {
                try {
                    if (0 != cell.getNumericCellValue()) {
                        cell = null;
                        return false;
                    }
                } catch (NumberFormatException | IllegalStateException nfe) {
                    if (!"".equals(cell.getStringCellValue().trim())) {
                        return false;
                    }
                }
            } else {
                isBlankRow = true;
            }
        }
        return isBlankRow;
    }
       
       public static String format(Double dbValue, String stFormat) {
        String stReturn;
        synchronized (df) {
            df.applyPattern(stFormat);
            stReturn = dbValue == null ? "" : df.format(dbValue);
        }

        return stReturn;
    }
       
       private void setValuePropertyValue(Object instance, String property, Object value) {
        Field f1;
        try {
            f1 = instance.getClass().getDeclaredField(property);
            f1.setAccessible(true);
            f1.set(instance, value);

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(Vms031UploadProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
