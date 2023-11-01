/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.vo;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031VoValidatorParameter {
    private String fieldName;
    private String rowPosition;
    private String columnPosition;
    private String locationPosition;
    private String message;
    private String type;
    private Object validatedValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(String rowPosition) {
        this.rowPosition = rowPosition;
    }

    public String getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(String columnPosition) {
        this.columnPosition = columnPosition;
    }

    public String getLocationPosition() {
        return locationPosition;
    }

    public void setLocationPosition(String locationPosition) {
        this.locationPosition = locationPosition;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValidatedValue() {
        return validatedValue;
    }

    public void setValidatedValue(Object validatedValue) {
        this.validatedValue = validatedValue;
    }
}
