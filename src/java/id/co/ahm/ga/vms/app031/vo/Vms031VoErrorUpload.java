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
public class Vms031VoErrorUpload {
     private String error;
    private int no;
    private int row;
    private String cellName;
    private String description;
    private String updHeader;
    
    public Vms031VoErrorUpload() {
    }

    public Vms031VoErrorUpload(int no, int row, String cellName, String description) {
        this.no = no;
        this.row = row;
        this.cellName = cellName;
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdHeader() {
        return updHeader;
    }

    public void setUpdHeader(String updHeader) {
        this.updHeader = updHeader;
    }
    
}
