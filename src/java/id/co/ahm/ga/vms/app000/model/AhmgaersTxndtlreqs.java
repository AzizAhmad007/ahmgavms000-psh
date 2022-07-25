/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.sql.Clob;
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
 * @author developer
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNDTLREQS")
public class AhmgaersTxndtlreqs extends DefaultEntityImpl implements Serializable{
    
    @Column(name = "MGARGLS_VRGLID")
    private String mgarglsVrglid;
    
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmgaersMstrgls.class)
    @JoinColumn(name = "MGARGLS_VRGLID", referencedColumnName = "VRGLID", insertable = false, updatable = false)
    private AhmgaersMstrgls ahmgaersMstrgls;

    @Column(name = "VREQTYPE")
    private String vreqtype;
    
    @Id
    @Column(name = "VDTLRGLID")
    private String vdtlrglid;

    @Column(name = "VCLAUSE")
    private String vclause;
    
    @Column(name = "VVERSE")
    private String vverse;
    
    @Column(name = "CDESC")
    private Clob cdesc;
    
    @Column(name = "VSTATUS")
    private String vstatus;
    
    @Column(name = "CTEXT")
    private Clob ctext;

    public String getMgarglsVrglid() {
        return mgarglsVrglid;
    }

    public void setMgarglsVrglid(String mgarglsVrglid) {
        this.mgarglsVrglid = mgarglsVrglid;
    }

    public AhmgaersMstrgls getAhmgaersMstrgls() {
        return ahmgaersMstrgls;
    }

    public void setAhmgaersMstrgls(AhmgaersMstrgls ahmgaersMstrgls) {
        this.ahmgaersMstrgls = ahmgaersMstrgls;
    }

    public String getVreqtype() {
        return vreqtype;
    }

    public void setVreqtype(String vreqtype) {
        this.vreqtype = vreqtype;
    }

    public String getVdtlrglid() {
        return vdtlrglid;
    }

    public void setVdtlrglid(String vdtlrglid) {
        this.vdtlrglid = vdtlrglid;
    }

    public String getVclause() {
        return vclause;
    }

    public void setVclause(String vclause) {
        this.vclause = vclause;
    }

    public String getVverse() {
        return vverse;
    }

    public void setVverse(String vverse) {
        this.vverse = vverse;
    }

    public Clob getCdesc() {
        return cdesc;
    }

    public void setCdesc(Clob cdesc) {
        this.cdesc = cdesc;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public Clob getCtext() {
        return ctext;
    }

    public void setCtext(Clob ctext) {
        this.ctext = ctext;
    }
    
}
