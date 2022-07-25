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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author administator
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNMEALOCS")
public class AhmgaersTxnmealocs extends DefaultEntityImpl implements Serializable {

    @Id
    @Column(name = "VMEALOCID")
    private String vmealocid;

    @Column(name = "XGAMEA_VMEACODE")
    private String xgameaVmeacode;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmgaersTxnmeasures.class)
    @JoinColumn(name = "XGAMEA_VMEACODE", referencedColumnName = "VMEACODE", insertable = false, updatable = false)
    private AhmgaersTxnmeasures ahmgaersTxnmeasures;

    @Column(name = "MGAMEPT_VMEAPOINTID")
    private String mgameptVmeapointid;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmgaersMstmeapnts.class)
    @JoinColumn(name = "MGAMEPT_VMEAPOINTID", referencedColumnName = "VMEAPOINTID", insertable = false, updatable = false)
    private AhmgaersMstmeapnts ahmgaersMstmeapnts;

    @Column(name = "OFLPHOSAMP")
    private byte[] oflphosamp;

    @Column(name = "OFLRESCERT")
    private byte[] oflrescert;

    @Column(name = "OFLOTHERS")
    private byte[] oflothers;

    @Column(name = "DACTMEADATE")
    private Date dactmeadate;

    @Column(name = "VOPRTIME")
    private String voprtime;

    @Column(name = "NFILESMPSZ")
    private Integer nfilesmpsz;

    @Column(name = "VFILESMPNM")
    private String vfilesmpnm;

    @Column(name = "VMIMETYSMP")
    private String vmimetysmp;

    @Column(name = "NFILERESSZ")
    private Integer nfileressz;

    @Column(name = "VFILERESNM")
    private String vfileresnm;

    @Column(name = "VMIMETYRES")
    private String vmimetyres;

    @Column(name = "NFILEOTHSZ")
    private Integer nfileothsz;

    @Column(name = "VFILEOTHNM")
    private String vfileothnm;

    @Column(name = "VMIMETYOTH")
    private String vmimetyoth;

    public String getVmealocid() {
        return vmealocid;
    }

    public void setVmealocid(String vmealocid) {
        this.vmealocid = vmealocid;
    }

    public String getXgameaVmeacode() {
        return xgameaVmeacode;
    }

    public void setXgameaVmeacode(String xgameaVmeacode) {
        this.xgameaVmeacode = xgameaVmeacode;
    }

    public AhmgaersTxnmeasures getAhmgaersTxnmeasures() {
        return ahmgaersTxnmeasures;
    }

    public void setAhmgaersTxnmeasures(AhmgaersTxnmeasures ahmgaersTxnmeasures) {
        this.ahmgaersTxnmeasures = ahmgaersTxnmeasures;
    }

    public String getMgameptVmeapointid() {
        return mgameptVmeapointid;
    }

    public void setMgameptVmeapointid(String mgameptVmeapointid) {
        this.mgameptVmeapointid = mgameptVmeapointid;
    }

    public AhmgaersMstmeapnts getAhmgaersMstmeapnts() {
        return ahmgaersMstmeapnts;
    }

    public void setAhmgaersMstmeapnts(AhmgaersMstmeapnts ahmgaersMstmeapnts) {
        this.ahmgaersMstmeapnts = ahmgaersMstmeapnts;
    }

    public byte[] getOflphosamp() {
        return oflphosamp;
    }

    public void setOflphosamp(byte[] oflphosamp) {
        this.oflphosamp = oflphosamp;
    }

    public byte[] getOflrescert() {
        return oflrescert;
    }

    public void setOflrescert(byte[] oflrescert) {
        this.oflrescert = oflrescert;
    }

    public byte[] getOflothers() {
        return oflothers;
    }

    public void setOflothers(byte[] oflothers) {
        this.oflothers = oflothers;
    }

    public Date getDactmeadate() {
        return dactmeadate;
    }

    public void setDactmeadate(Date dactmeadate) {
        this.dactmeadate = dactmeadate;
    }

    public String getVoprtime() {
        return voprtime;
    }

    public void setVoprtime(String voprtime) {
        this.voprtime = voprtime;
    }

    public Integer getNfilesmpsz() {
        return nfilesmpsz;
    }

    public void setNfilesmpsz(Integer nfilesmpsz) {
        this.nfilesmpsz = nfilesmpsz;
    }

    public String getVfilesmpnm() {
        return vfilesmpnm;
    }

    public void setVfilesmpnm(String vfilesmpnm) {
        this.vfilesmpnm = vfilesmpnm;
    }

    public String getVmimetysmp() {
        return vmimetysmp;
    }

    public void setVmimetysmp(String vmimetysmp) {
        this.vmimetysmp = vmimetysmp;
    }

    public Integer getNfileressz() {
        return nfileressz;
    }

    public void setNfileressz(Integer nfileressz) {
        this.nfileressz = nfileressz;
    }

    public String getVfileresnm() {
        return vfileresnm;
    }

    public void setVfileresnm(String vfileresnm) {
        this.vfileresnm = vfileresnm;
    }

    public String getVmimetyres() {
        return vmimetyres;
    }

    public void setVmimetyres(String vmimetyres) {
        this.vmimetyres = vmimetyres;
    }

    public Integer getNfileothsz() {
        return nfileothsz;
    }

    public void setNfileothsz(Integer nfileothsz) {
        this.nfileothsz = nfileothsz;
    }

    public String getVfileothnm() {
        return vfileothnm;
    }

    public void setVfileothnm(String vfileothnm) {
        this.vfileothnm = vfileothnm;
    }

    public String getVmimetyoth() {
        return vmimetyoth;
    }

    public void setVmimetyoth(String vmimetyoth) {
        this.vmimetyoth = vmimetyoth;
    }

}
