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
 * @author Kahfi
 */
@Embeddable
public class AhmsdtmsDtldriversPk implements Serializable{
    
    @Column(name = "RSDDRIVER_VDRIVERID")
    private String rsddriver_vdriverid;
    
    @Column(name = "RSDDRIVER_VMDCODE")
    private String rsddriver_vmdcode;
    
    @Column(name = "VEXPID")
    private String vexpid;

    public String getRsddriver_vdriverid() {
        return rsddriver_vdriverid;
    }

    public void setRsddriver_vdriverid(String rsddriver_vdriverid) {
        this.rsddriver_vdriverid = rsddriver_vdriverid;
    }

    public String getRsddriver_vmdcode() {
        return rsddriver_vmdcode;
    }

    public void setRsddriver_vmdcode(String rsddriver_vmdcode) {
        this.rsddriver_vmdcode = rsddriver_vmdcode;
    }

    public String getVexpid() {
        return vexpid;
    }

    public void setVexpid(String vexpid) {
        this.vexpid = vexpid;
    }
}