/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kahfi
 */
@Embeddable
public class AhmgavmsDtlregsisPk implements Serializable{
    
    @Column(name = "VNOREQSI")
    private String vnoreqsi;
    
    @Column(name = "VTYPE")
    private String vtype;

    public String getVnoreqsi() {
        return vnoreqsi;
    }

    public void setVnoreqsi(String vnoreqsi) {
        this.vnoreqsi = vnoreqsi;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }
    
    
}
