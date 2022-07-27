/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author reza.mr
 */
@Embeddable
public class AhmhrntmDtlotsregsPk implements Serializable{
    
    @Column(name = "VOTSID", length = 10, nullable = false)
    private String votsid;

    @Column(name = "VPERSID", nullable = false, length = 20)
    private String vpersid;

    @Column(name = "VREGID", nullable = false, length = 10)
    private String vregid;

    @Column(name = "NSEQ", nullable = false)
    private BigDecimal nseq;

    public String getVotsid() {
        return votsid;
    }

    public void setVotsid(String votsid) {
        this.votsid = votsid;
    }

    public String getVpersid() {
        return vpersid;
    }

    public void setVpersid(String vpersid) {
        this.vpersid = vpersid;
    }

    public String getVregid() {
        return vregid;
    }

    public void setVregid(String vregid) {
        this.vregid = vregid;
    }

    public BigDecimal getNseq() {
        return nseq;
    }

    public void setNseq(BigDecimal nseq) {
        this.nseq = nseq;
    }
    
    
}
