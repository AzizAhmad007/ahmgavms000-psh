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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "AHMGAERS_MSTRGLS")
public class AhmgaersMstrgls extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VRGLID")
    private String vrglid;

    @Column(name = "VRGLDESC")
    private String vrgldesc;
    
    @Column(name = "VREGNUMBR")
    private String vregnumbr;
    
    @Column(name = "VREVNUMBR")
    private String vrevnumbr;
    
    @Column(name = "DEFFFROM")
    @Temporal(TemporalType.DATE)
    private Date defffrom;
    
    @Column(name = "DEFFTO")
    @Temporal(TemporalType.DATE)
    private Date deffto;
    
    @Column(name = "VSTATUS")
    private String vstatus;
    
    @Column(name = "VPUBLISHORN")
    private String vpublishorn;

    @Column(name = "MGARGLGR_VRGLGROUPID")
    private String mgarglgrVrglgroupid;
    
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AhmgaersMstrglgrps.class)
    @JoinColumn(name = "MGARGLGR_VRGLGROUPID", referencedColumnName = "VRGLGROUPID", insertable = false, updatable = false)
    private AhmgaersMstrglgrps ahmgaersMstrglgrps;

    @Lob
    @Column(name="OATTACHMENT",nullable=false)
    private byte[] oattachment;
    
    @Column(name = "NFILESIZE")
    private long nfilesize;
    
    @Column(name = "VFILENAME")
    private String vfilename;
    
    @Column(name = "VMIMETYPE")
    private String vmimetype;

    public String getVrglid() {
        return vrglid;
    }

    public void setVrglid(String vrglid) {
        this.vrglid = vrglid;
    }

    public String getVrgldesc() {
        return vrgldesc;
    }

    public void setVrgldesc(String vrgldesc) {
        this.vrgldesc = vrgldesc;
    }

    public String getVregnumbr() {
        return vregnumbr;
    }

    public void setVregnumbr(String vregnumbr) {
        this.vregnumbr = vregnumbr;
    }

    public String getVrevnumbr() {
        return vrevnumbr;
    }

    public void setVrevnumbr(String vrevnumbr) {
        this.vrevnumbr = vrevnumbr;
    }

    public Date getDefffrom() {
        return defffrom;
    }

    public void setDefffrom(Date defffrom) {
        this.defffrom = defffrom;
    }

    public Date getDeffto() {
        return deffto;
    }

    public void setDeffto(Date deffto) {
        this.deffto = deffto;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public String getVpublishorn() {
        return vpublishorn;
    }

    public void setVpublishorn(String vpublishorn) {
        this.vpublishorn = vpublishorn;
    }

    public String getMgarglgrVrglgroupid() {
        return mgarglgrVrglgroupid;
    }

    public void setMgarglgrVrglgroupid(String mgarglgrVrglgroupid) {
        this.mgarglgrVrglgroupid = mgarglgrVrglgroupid;
    }

    public AhmgaersMstrglgrps getAhmgaersMstrglgrps() {
        return ahmgaersMstrglgrps;
    }

    public void setAhmgaersMstrglgrps(AhmgaersMstrglgrps ahmgaersMstrglgrps) {
        this.ahmgaersMstrglgrps = ahmgaersMstrglgrps;
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
