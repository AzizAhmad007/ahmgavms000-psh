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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "AHMGAERS_TXNATPINMPS")
public class AhmgaersTxnatpinmps extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VATTPINMPID")
    private String vattpinmpid;
    
    @Column(name="OATTACHMENT")
    private byte[] oattachment;
    
    @Column(name="XGAPMTMP_VPERMITID")
    private String xgapmtmpVpermitId;
    
    @Column(name = "NFILESIZE")
    private long nfilesize;
    
    @Column(name="VFILENAME")
    private String vfilename;
    
    @Column(name="VMIMETYPE")
    private String vmimetype;
    
    @ManyToOne(targetEntity = AhmgaersTxnpmtinmps.class)
    @JoinColumn(name = "XGAPMTMP_VPERMITID", referencedColumnName = "VPERMITID", insertable = false, updatable = false)
    private AhmgaersTxnpmtinmps atpMpHeader;

    public String getVattpinmpid() {
        return vattpinmpid;
    }

    public void setVattpinmpid(String vattpinmpid) {
        this.vattpinmpid = vattpinmpid;
    }

    public byte[] getOattachment() {
        return oattachment;
    }

    public void setOattachment(byte[] oattachment) {
        this.oattachment = oattachment;
    }

    public String getXgapmtmpVpermitId() {
        return xgapmtmpVpermitId;
    }

    public void setXgapmtmpVpermitId(String xgapmtmpVpermitId) {
        this.xgapmtmpVpermitId = xgapmtmpVpermitId;
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

    public AhmgaersTxnpmtinmps getAtpMpHeader() {
        return atpMpHeader;
    }

    public void setAtpMpHeader(AhmgaersTxnpmtinmps atpMpHeader) {
        this.atpMpHeader = atpMpHeader;
    }

}
