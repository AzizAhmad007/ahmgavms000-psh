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
 * @author Yusuf Yadi Surya
 */
@Embeddable
public class AhmgaersTxnspkholdsPk implements Serializable{
    @Column(name = "VSPKHOLDCD")
    private String vspkholdcd;

    @Column(name = "VB3ORNON")
    private String vb3ornon;
    
    @Column(name = "VPERMITTYPE")
    private String vpermittype;

    @Column(name = "VSPKHOLDID")
    private String vspkholdid;

    public String getVspkholdcd() {
        return vspkholdcd;
    }

    public void setVspkholdcd(String vspkholdcd) {
        this.vspkholdcd = vspkholdcd;
    }

    public String getVb3ornon() {
        return vb3ornon;
    }

    public void setVb3ornon(String vb3ornon) {
        this.vb3ornon = vb3ornon;
    }

    public String getVpermittype() {
        return vpermittype;
    }

    public void setVpermittype(String vpermittype) {
        this.vpermittype = vpermittype;
    }

    public String getVspkholdid() {
        return vspkholdid;
    }

    public void setVspkholdid(String vspkholdid) {
        this.vspkholdid = vspkholdid;
    }
}
