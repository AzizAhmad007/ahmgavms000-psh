/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Yusuf Yadi Surya
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNSPKHOLDS")
public class AhmgaersTxnspkholds extends DefaultEntityImpl 
        implements Serializable{

    @EmbeddedId
    private AhmgaersTxnspkholdsPk ahmgaersTxnspkholdsPk;
    
    @Column(name = "VSPKHOLDCD", insertable = false, updatable = false)
    private String vspkholdcd;

    @Column(name = "VB3ORNON", insertable = false, updatable = false)
    private String vb3ornon;
    
    @Column(name = "VPERMITTYPE", insertable = false, updatable = false)
    private String vpermittype;

    @Column(name = "VSPKHOLDID", insertable = false, updatable = false)
    private String vspkholdid;
    
    @ManyToOne(fetch = FetchType.LAZY , targetEntity = AhmmomscMstvendors.class)
    @JoinColumn(name = "VSPKHOLDID", referencedColumnName = "VVENDORID", insertable = false, updatable = false)
    private AhmmomscMstvendors ahmmomscMstvendors;
    
    @Column(name = "VSPKEMAIL")
    private String vspkemail;
    
    @Column(name = "VMANIFESTID")
    private String vmanifestcd;

    public AhmgaersTxnspkholdsPk getAhmgaersTxnspkholdsPk() {
        return ahmgaersTxnspkholdsPk;
    }

    public void setAhmgaersTxnspkholdsPk(AhmgaersTxnspkholdsPk ahmgaersTxnspkholdsPk) {
        this.ahmgaersTxnspkholdsPk = ahmgaersTxnspkholdsPk;
    }

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

    public String getVspkemail() {
        return vspkemail;
    }

    public void setVspkemail(String vspkemail) {
        this.vspkemail = vspkemail;
    }

    public String getVmanifestcd() {
        return vmanifestcd;
    }

    public void setVmanifestcd(String vmanifestcd) {
        this.vmanifestcd = vmanifestcd;
    }

    public AhmmomscMstvendors getAhmmomscMstvendors() {
        return ahmmomscMstvendors;
    }

    public void setAhmmomscMstvendors(AhmmomscMstvendors ahmmomscMstvendors) {
        this.ahmmomscMstvendors = ahmmomscMstvendors;
    }
}
