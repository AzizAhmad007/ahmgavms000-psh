/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.co.ahm.ga.vms.app000.model.wfs;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author reza.mr
 */

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMITWFS_MSTWFDOCHIST")
public class AhmitwfsMstwfdochist extends DefaultEntityImpl implements Serializable {
    
    
    @Id
    @Column(name = "VWFGUID")
    private String vwfguid;
    
    @Column(name = "VHISTID")
    private String vhistid;

    @Column(name = "VTASKID")
    private String vtaskid;
    
    @Column(name = "VEVENTTYPE")
    private String veventtype;
    
    @Column(name = "VTASKRESULT")
    private String vtaskresult;
    
    @Column(name = "VNOTE")
    private String vnote;
    
    @Column(name = "VDOCID")
    private String vdocid;

    public String getVdocid() {
        return vdocid;
    }

    public void setVdocid(String vdocid) {
        this.vdocid = vdocid;
    }   

    public String getVwfguid() {
        return vwfguid;
    }

    public void setVwfguid(String vwfguid) {
        this.vwfguid = vwfguid;
    }

    public String getVhistid() {
        return vhistid;
    }

    public void setVhistid(String vhistid) {
        this.vhistid = vhistid;
    }
    
    public String getVtaskid() {
        return vtaskid;
    }

    public void setVtaskid(String vtaskid) {
        this.vtaskid = vtaskid;
    }

    public String getVeventtype() {
        return veventtype;
    }

    public void setVeventtype(String veventtype) {
        this.veventtype = veventtype;
    }

    public String getVtaskresult() {
        return vtaskresult;
    }

    public void setVtaskresult(String vtaskresult) {
        this.vtaskresult = vtaskresult;
    }

    public String getVnote() {
        return vnote;
    }

    public void setVnote(String vnote) {
        this.vnote = vnote;
    }
    
}
