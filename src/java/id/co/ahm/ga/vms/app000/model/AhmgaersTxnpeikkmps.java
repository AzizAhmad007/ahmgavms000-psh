/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author A Roni Purwanto
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNPEIKKMPS")
public class AhmgaersTxnpeikkmps extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VPMTEXMPID")
    private String vpmtexmpid;
    
    @Column(name="VREVNUMBR")
    private String vrevnumbr;
    
    @Column(name="VIDNUMBER")
    private String vidnumber;

    @Column(name="VNAME")
    private String vname;
    
    @Column(name="VPERSRESNM")
    private String vpersresnm; 
           
    @Column(name="VEMAIL")
    private String vemail;

    @Column(name="VSTATUSMP")
    private String vstatusmp;
    
    @Column(name="VSTATUSPMT")
    private String vstatuspmt;
    
    @Column(name="VSTATUSFLP")
    private String vstatusflp;

    public String getVpmtexmpid() {
        return vpmtexmpid;
    }

    public void setVpmtexmpid(String vpmtexmpid) {
        this.vpmtexmpid = vpmtexmpid;
    }

    public String getVrevnumbr() {
        return vrevnumbr;
    }

    public void setVrevnumbr(String vrevnumbr) {
        this.vrevnumbr = vrevnumbr;
    }

    public String getVidnumber() {
        return vidnumber;
    }

    public void setVidnumber(String vidnumber) {
        this.vidnumber = vidnumber;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVpersresnm() {
        return vpersresnm;
    }

    public void setVpersresnm(String vpersresnm) {
        this.vpersresnm = vpersresnm;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }

    public String getVstatusmp() {
        return vstatusmp;
    }

    public void setVstatusmp(String vstatusmp) {
        this.vstatusmp = vstatusmp;
    }

    public String getVstatuspmt() {
        return vstatuspmt;
    }

    public void setVstatuspmt(String vstatuspmt) {
        this.vstatuspmt = vstatuspmt;
    }

    public String getVstatusflp() {
        return vstatusflp;
    }

    public void setVstatusflp(String vstatusflp) {
        this.vstatusflp = vstatusflp;
    }

}
