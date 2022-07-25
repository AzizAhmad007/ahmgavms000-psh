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
@Table(name = "AHMGAERS_MSTASSETS")
public class AhmgaersMstassets extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VASSETNMBR")
    private String vassetnmbr;
    
    @Column(name = "VSUBNUMBER")
    private String vsubnmbr;
    
    @Column(name = "VASSETDESC")
    private String vassetdesc;
    
    @Column(name = "VPLANTID")
    private String vplantid;
    
    @Column(name = "VLOCID")
    private String vlocid;
    
    @Column(name = "VLOCDESC")
    private String vlocdesc;
    
    @Column(name = "VSTATUSID")
    private String vstatusid;
    
    @Column(name = "VSTATUSDESC")
    private String vstatusdesc;
    
    @Column(name = "VCOSTCENTER")
    private String vcostcenter;
    
    @Column(name = "VCOSTCENDSC")
    private String vcostcendsc;
    
    @Column(name = "VCOSTCENRES")
    private String vcostcenres;
    
    @Column(name = "VCCRESDESC")
    private String vccresdesc;

    public String getVassetnmbr() {
        return vassetnmbr;
    }

    public void setVassetnmbr(String vassetnmbr) {
        this.vassetnmbr = vassetnmbr;
    }

    public String getVsubnmbr() {
        return vsubnmbr;
    }

    public void setVsubnmbr(String vsubnmbr) {
        this.vsubnmbr = vsubnmbr;
    }

    public String getVassetdesc() {
        return vassetdesc;
    }

    public void setVassetdesc(String vassetdesc) {
        this.vassetdesc = vassetdesc;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVlocid() {
        return vlocid;
    }

    public void setVlocid(String vlocid) {
        this.vlocid = vlocid;
    }

    public String getVlocdesc() {
        return vlocdesc;
    }

    public void setVlocdesc(String vlocdesc) {
        this.vlocdesc = vlocdesc;
    }

    public String getVstatusid() {
        return vstatusid;
    }

    public void setVstatusid(String vstatusid) {
        this.vstatusid = vstatusid;
    }

    public String getVstatusdesc() {
        return vstatusdesc;
    }

    public void setVstatusdesc(String vstatusdesc) {
        this.vstatusdesc = vstatusdesc;
    }

    public String getVcostcenter() {
        return vcostcenter;
    }

    public void setVcostcenter(String vcostcenter) {
        this.vcostcenter = vcostcenter;
    }

    public String getVcostcendsc() {
        return vcostcendsc;
    }

    public void setVcostcendsc(String vcostcendsc) {
        this.vcostcendsc = vcostcendsc;
    }

    public String getVcostcenres() {
        return vcostcenres;
    }

    public void setVcostcenres(String vcostcenres) {
        this.vcostcenres = vcostcenres;
    }

    public String getVccresdesc() {
        return vccresdesc;
    }

    public void setVccresdesc(String vccresdesc) {
        this.vccresdesc = vccresdesc;
    }
    
    
}
