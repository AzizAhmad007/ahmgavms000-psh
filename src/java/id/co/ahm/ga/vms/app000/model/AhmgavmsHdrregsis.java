/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_HDRREGSIS")
public class AhmgavmsHdrregsis extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    protected AhmgavmsHdrregsisPk ahmgavmsHdrregsisPk;
    
    @Column(name = "VIDTYPE")
    private String vidtype;
    
    @Column(name = "VIDNO")
    private String vidno;
    
    @Column(name = "VNAME")
    private String vname;
    
    @Column(name = "VTYPE")
    private String vtype;
    
    @Column(name = "VPLANTID")
    private String vplantid;
    
    @Column(name = "DSTARTSI")
    @Temporal(TemporalType.DATE)
    private Date dstartsi;
    
    @Column(name = "VSTATUS")
    private String vstatus;

    public AhmgavmsHdrregsisPk getAhmgavmsHdrregsisPk() {
        return ahmgavmsHdrregsisPk;
    }

    public void setAhmgavmsHdrregsisPk(AhmgavmsHdrregsisPk ahmgavmsHdrregsisPk) {
        this.ahmgavmsHdrregsisPk = ahmgavmsHdrregsisPk;
    }

    public String getVidtype() {
        return vidtype;
    }

    public void setVidtype(String vidtype) {
        this.vidtype = vidtype;
    }

    public String getVidno() {
        return vidno;
    }

    public void setVidno(String vidno) {
        this.vidno = vidno;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public Date getDstartsi() {
        return dstartsi;
    }

    public void setDstartsi(Date dstartsi) {
        this.dstartsi = dstartsi;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
    
    
}
