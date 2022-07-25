/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author developer
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNRGLFUFIS")
public class AhmgaersTxnrglfufis extends DefaultEntityImpl implements Serializable {

    @Id
    @Column(name = "VRGLFUFIID")
    private String vrglfufiid;

    @Column(name = "NFULLFILL")
    private Double nfullfill;

    @Column(name = "XGAMSTRQ_VDTLRGLID")
    private String xgamstrqVdtlrglid;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = AhmgaersTxndtlreqs.class)
    @JoinColumns({
        @JoinColumn(name = "XGAMSTRQ_VDTLRGLID", referencedColumnName = "VDTLRGLID", insertable = false, updatable = false)
    })
    private AhmgaersTxndtlreqs ahmgaersTxndtlreqs;

    @Column(name = "XGARGARE_VRGLID")
    private String xgargareVrglid;

    @Column(name = "XGARGARE_VAREA")
    private String xgargareVarea;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = AhmgaersTxnrglareas.class)
    @JoinColumns({
        @JoinColumn(name = "XGARGARE_VRGLID", referencedColumnName = "MGARGLS_VRGLID", insertable = false, updatable = false)
        ,
        @JoinColumn(name = "XGARGARE_VAREA", referencedColumnName = "VAREA", insertable = false, updatable = false)
    })
    private AhmgaersTxnrglareas ahmgaersTxnrglareas;

    @Lob
    @Column(name = "OATTACHMENT", nullable = true)
    private byte[] oattachment;

    @Column(name = "NFILESIZE")
    private Long nfilesize;

    @Column(name = "VFILENAME")
    private String vfilename;

    @Column(name = "VMIMETYPE")
    private String vmimetype;

    public String getVrglfufiid() {
        return vrglfufiid;
    }

    public void setVrglfufiid(String vrglfufiid) {
        this.vrglfufiid = vrglfufiid;
    }

    public Double getNfullfill() {
        return nfullfill;
    }

    public void setNfullfill(Double nfullfill) {
        this.nfullfill = nfullfill;
    }

    public String getXgamstrqVdtlrglid() {
        return xgamstrqVdtlrglid;
    }

    public void setXgamstrqVdtlrglid(String xgamstrqVdtlrglid) {
        this.xgamstrqVdtlrglid = xgamstrqVdtlrglid;
    }

    public AhmgaersTxndtlreqs getAhmgaersTxndtlreqs() {
        return ahmgaersTxndtlreqs;
    }

    public void setAhmgaersTxndtlreqs(AhmgaersTxndtlreqs ahmgaersTxndtlreqs) {
        this.ahmgaersTxndtlreqs = ahmgaersTxndtlreqs;
    }

    public String getXgargareVrglid() {
        return xgargareVrglid;
    }

    public void setXgargareVrglid(String xgargareVrglid) {
        this.xgargareVrglid = xgargareVrglid;
    }

    public String getXgargareVarea() {
        return xgargareVarea;
    }

    public void setXgargareVarea(String xgargareVarea) {
        this.xgargareVarea = xgargareVarea;
    }

    public AhmgaersTxnrglareas getAhmgaersTxnrglareas() {
        return ahmgaersTxnrglareas;
    }

    public void setAhmgaersTxnrglareas(AhmgaersTxnrglareas ahmgaersTxnrglareas) {
        this.ahmgaersTxnrglareas = ahmgaersTxnrglareas;
    }

    public byte[] getOattachment() {
        return oattachment;
    }

    public void setOattachment(byte[] oattachment) {
        this.oattachment = oattachment;
    }

    public long getNfilesize() {
        return nfilesize;
    }

    public void setNfilesize(long nfilesize) {
        this.nfilesize = nfilesize;
    }

    public String getVfilename() {
        return vfilename;
    }

    public void setVfilename(String vfilename) {
        this.vfilename = vfilename;
    }

    public String getVmimetype() {
        return vmimetype;
    }

    public void setVmimetype(String vmimetype) {
        this.vmimetype = vmimetype;
    }

}
