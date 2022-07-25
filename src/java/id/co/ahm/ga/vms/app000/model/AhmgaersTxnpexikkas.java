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
@Table(name = "AHMGAERS_TXNPEXIKKAS")
public class AhmgaersTxnpexikkas extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VPMTEXASID")
    private String vpmtexasid;
    
    @Column(name="VREVNUMBER")
    private String vrevnumber;
    
    @Column(name="VSRLNUMBR")
    private String vsrlnumbr;

    @Column(name="VASSETDESC")
    private String vassetdesc;
    
    @Column(name="VPERSRESNM")
    private String vpersresnm; 
           
    @Column(name="VPHONENMBR")
    private String vphonenmbr;

    @Column(name="VSTATUSASS")
    private String vstatusass;
    
    @Column(name="VSTATUSPMT")
    private String vstatuspmt;
    
    @Column(name="VSTATUSFLP")
    private String vstatusflp;

    public String getVpmtexasid() {
        return vpmtexasid;
    }

    public void setVpmtexasid(String vpmtexasid) {
        this.vpmtexasid = vpmtexasid;
    }

    public String getVrevnumber() {
        return vrevnumber;
    }

    public void setVrevnumber(String vrevnumber) {
        this.vrevnumber = vrevnumber;
    }

    public String getVsrlnumbr() {
        return vsrlnumbr;
    }

    public void setVsrlnumbr(String vsrlnumbr) {
        this.vsrlnumbr = vsrlnumbr;
    }

    public String getVassetdesc() {
        return vassetdesc;
    }

    public void setVassetdesc(String vassetdesc) {
        this.vassetdesc = vassetdesc;
    }

    public String getVpersresnm() {
        return vpersresnm;
    }

    public void setVpersresnm(String vpersresnm) {
        this.vpersresnm = vpersresnm;
    }

    public String getVphonenmbr() {
        return vphonenmbr;
    }

    public void setVphonenmbr(String vphonenmbr) {
        this.vphonenmbr = vphonenmbr;
    }

    public String getVstatusass() {
        return vstatusass;
    }

    public void setVstatusass(String vstatusass) {
        this.vstatusass = vstatusass;
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
