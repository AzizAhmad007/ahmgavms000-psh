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
import javax.persistence.OneToOne;
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
@Table(name = "AHMGAERS_TXNMEASURES")
public class AhmgaersTxnmeasures extends DefaultEntityImpl implements Serializable {

    @Id
    @Column(name = "VMEACODE")
    private String vmeacode;

    @Column(name = "VMEAID")
    private String vmeaid;

    @Column(name = "MGARGLS_VRGLID")
    private String mgarglsVrglid;

    @ManyToOne(fetch = FetchType.LAZY , targetEntity = AhmgaersMstrgls.class)
    @JoinColumn(name = "MGARGLS_VRGLID", referencedColumnName = "VRGLID", insertable = false, updatable = false)
    private AhmgaersMstrgls ahmgaersMstrgls;

    @Column(name = "VMEADESC")
    private String vmeadesc;

    @Column(name = "MGAMEATY_VMEATYPESID")
    private String mgameatyVmeatypesid;

    @NotFound(action=NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY , targetEntity = AhmgaersMstmeatypes.class)
    @JoinColumn(name = "MGAMEATY_VMEATYPESID", referencedColumnName = "VMEATYPESID", insertable = false, updatable = false)
    private AhmgaersMstmeatypes ahmgaersMstmeatypes;
    
    @Column(name = "DMEAPLANDT")
    private Date dmeaplandt;

    @Column(name = "VPICASSIGN")
    private String vpicassign;

    @Column(name = "VSUPREGNO")
    private String vsupregno;

    @Column(name = "VSUPPASSIGN")
    private String vsuppassign;

    @Column(name = "VSTATUS")
    private String vstatus;

    @Column(name = "DLASTCONF")
    private Date dlastconf;

    @Column(name = "VSUPPEMAIL")
    private String vsuppemail;

    @Column(name = "VWFGUID")
    private String vwfguid;

    @Column(name = "VTASKGUID")
    private String vtaskguid;

    @Column(name = "VSUPPDESC")
    private String vsuppdesc;

    @Column(name = "VREVNMBR")
    private String vrevnmbr;

    public String getVmeacode() {
        return vmeacode;
    }

    public void setVmeacode(String vmeacode) {
        this.vmeacode = vmeacode;
    }

    public String getVmeaid() {
        return vmeaid;
    }

    public void setVmeaid(String vmeaid) {
        this.vmeaid = vmeaid;
    }

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

    public String getVmeadesc() {
        return vmeadesc;
    }

    public void setVmeadesc(String vmeadesc) {
        this.vmeadesc = vmeadesc;
    }

    public String getMgameatyVmeatypesid() {
        return mgameatyVmeatypesid;
    }

    public void setMgameatyVmeatypesid(String mgameatyVmeatypesid) {
        this.mgameatyVmeatypesid = mgameatyVmeatypesid;
    }

    public AhmgaersMstmeatypes getAhmgaersMstmeatypes() {
        return ahmgaersMstmeatypes;
    }

    public void setAhmgaersMstmeatypes(AhmgaersMstmeatypes ahmgaersMstmeatypes) {
        this.ahmgaersMstmeatypes = ahmgaersMstmeatypes;
    }

    public Date getDmeaplandt() {
        return dmeaplandt;
    }

    public void setDmeaplandt(Date dmeaplandt) {
        this.dmeaplandt = dmeaplandt;
    }

    public String getVpicassign() {
        return vpicassign;
    }

    public void setVpicassign(String vpicassign) {
        this.vpicassign = vpicassign;
    }

    public String getVsupregno() {
        return vsupregno;
    }

    public void setVsupregno(String vsupregno) {
        this.vsupregno = vsupregno;
    }

    public String getVsuppassign() {
        return vsuppassign;
    }

    public void setVsuppassign(String vsuppassign) {
        this.vsuppassign = vsuppassign;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public Date getDlastconf() {
        return dlastconf;
    }

    public void setDlastconf(Date dlastconf) {
        this.dlastconf = dlastconf;
    }

    public String getVsuppemail() {
        return vsuppemail;
    }

    public void setVsuppemail(String vsuppemail) {
        this.vsuppemail = vsuppemail;
    }

    public String getVwfguid() {
        return vwfguid;
    }

    public void setVwfguid(String vwfguid) {
        this.vwfguid = vwfguid;
    }

    public String getVtaskguid() {
        return vtaskguid;
    }

    public void setVtaskguid(String vtaskguid) {
        this.vtaskguid = vtaskguid;
    }

    public String getVsuppdesc() {
        return vsuppdesc;
    }

    public void setVsuppdesc(String vsuppdesc) {
        this.vsuppdesc = vsuppdesc;
    }

    public String getVrevnmbr() {
        return vrevnmbr;
    }

    public void setVrevnmbr(String vrevnmbr) {
        this.vrevnmbr = vrevnmbr;
    }

}
