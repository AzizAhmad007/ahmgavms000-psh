/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.upload;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031UploadSecurityConfiguration {
    private int authRow;
    private int authColumn;
    private String authName;
    
    public Vms031UploadSecurityConfiguration() {
    }
    
    public Vms031UploadSecurityConfiguration(int authRow, int authColumn, String authName) {
        this.authRow = authRow;
        this.authColumn = authColumn;
        this.authName = authName;
    }

    public int getAuthRow() {
        return authRow;
    }

    public void setAuthRow(int authRow) {
        this.authRow = authRow;
    }

    public int getAuthColumn() {
        return authColumn;
    }

    public void setAuthColumn(int authColumn) {
        this.authColumn = authColumn;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }
}
