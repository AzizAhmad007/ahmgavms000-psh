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
public class AhmgaersTxnpexwstsPk 
        implements Serializable{
    
    @Column(name = "VPERMITID")
    private String vpermitid;
    
    @Column(name = "VREGTYPE")
    private String vregtype;
    
    @Column(name = "VDOCTYPE")
    private String vdoctype;

    public String getVpermitid() {
        return vpermitid;
    }

    public void setVpermitid(String vpermitid) {
        this.vpermitid = vpermitid;
    }

    public String getVregtype() {
        return vregtype;
    }

    public void setVregtype(String vregtype) {
        this.vregtype = vregtype;
    }

    public String getVdoctype() {
        return vdoctype;
    }

    public void setVdoctype(String vdoctype) {
        this.vdoctype = vdoctype;
    }
}
