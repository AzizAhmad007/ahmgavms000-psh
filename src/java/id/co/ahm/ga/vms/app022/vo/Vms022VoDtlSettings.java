/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.vo;

/**
 *
 * @author administator
 */
public class Vms022VoDtlSettings {
    
    private String id;

    private String itemCode;
    
    private String itemName;
    
    private String itemDesc;
    
    public Vms022VoDtlSettings(){
        
    }

    public Vms022VoDtlSettings(String id, String itemCode, String itemName, String itemDesc) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }    
}
