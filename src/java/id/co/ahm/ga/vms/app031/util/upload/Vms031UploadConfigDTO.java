/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.upload;

import id.co.ahm.ga.vms.app031.util.validator.Vms031ExtendedLogicValidator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031UploadConfigDTO {
     private int startRow = 3;
    private int sheetNo = 0;
    private Map<Integer, String> columnPropertyMap = new HashMap<>();
    private Vms031UploadSecurityConfiguration security;

    public static final Map<String, Vms031ExtendedLogicValidator> extendedValidation = new HashMap<>();
    private boolean uppercaseValue;
    
    public Vms031UploadConfigDTO() {
        uppercaseValue = true;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getSheetNo() {
        return sheetNo;
    }

    public void setSheetNo(int sheetNo) {
        this.sheetNo = sheetNo;
    }

    public Map<Integer, String> getColumnPropertyMap() {
        return columnPropertyMap;
    }

    public void setColumnPropertyMap(Map<Integer, String> columnPropertyMap) {
        this.columnPropertyMap = columnPropertyMap;
    }

    public Vms031UploadSecurityConfiguration getSecurity() {
        return security;
    }

    public void setSecurity(Vms031UploadSecurityConfiguration security) {
        this.security = security;
    }

    public boolean isUppercaseValue() {
        return uppercaseValue;
    }

    public void setUppercaseValue(boolean uppercaseValue) {
        this.uppercaseValue = uppercaseValue;
    }

}
