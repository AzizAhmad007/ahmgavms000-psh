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
public class AhmmoscdMstlocsPk implements Serializable{
    
    @Column(name = "VPLANT", nullable = false, length = 30)
    private String vplant;
    
    @Column(name = "VBUILDING", nullable = false, length = 30)
    private String vbuilding;
    
    @Column(name = "VFLOOR", nullable = false, length = 30)
    private String vfloor;
    
    @Column(name = "VSECTION", nullable = false, length = 30)
    private String vsection;

    public String getVplant() {
        return vplant;
    }

    public void setVplant(String vplant) {
        this.vplant = vplant;
    }

    public String getVbuilding() {
        return vbuilding;
    }

    public void setVbuilding(String vbuilding) {
        this.vbuilding = vbuilding;
    }

    public String getVfloor() {
        return vfloor;
    }

    public void setVfloor(String vfloor) {
        this.vfloor = vfloor;
    }

    public String getVsection() {
        return vsection;
    }

    public void setVsection(String vsection) {
        this.vsection = vsection;
    }
}
