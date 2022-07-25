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
 * @author A Roni Purwanto
 */
@Embeddable
public class AhmgaersTxnrglmpsPk implements Serializable{    
    @Column(name="VRGLIDMP")
    private String vrglidmp;
    
    @Column(name="VPERMITIDMP")
    private String vpermitidmp;

    public AhmgaersTxnrglmpsPk() {
    }

    public AhmgaersTxnrglmpsPk(String vrglidmp, String vpermitidmp) {
        this.vrglidmp = vrglidmp;
        this.vpermitidmp = vpermitidmp;
    }

    public String getVrglidmp() {
        return vrglidmp;
    }

    public void setVrglidmp(String vrglidmp) {
        this.vrglidmp = vrglidmp;
    }

    public String getVpermitidmp() {
        return vpermitidmp;
    }

    public void setVpermitidmp(String vpermitidmp) {
        this.vpermitidmp = vpermitidmp;
    }
    
    
}
