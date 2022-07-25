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
@Table(name = "AHMGAERS_TXNATPINASS")
public class AhmgaersTxnatpinass extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VATTPINASID")
    private String vattpinasid;
    
    @Column(name="OATTACHMENT")
    private byte[] oattachment;
    
    @Column(name="XGAPMTAS_VPERMITID")
    private String xgapmtasVpermitId;
    
    @Column(name = "NFILESIZE")
    private long nfilesize;
    
    @Column(name="VFILENAME")
    private String vfilename;
    
    @Column(name="VMIMETYPE")
    private String vmimetype;
    
    @ManyToOne(targetEntity = AhmgaersTxnpmtinass.class)
    @JoinColumn(name = "XGAPMTAS_VPERMITID", referencedColumnName = "VPERMITID", insertable = false, updatable = false)
    private AhmgaersTxnpmtinass atpAssetHeader;

    public String getVattpinasid() {
        return vattpinasid;
    }

    public void setVattpinasid(String vattpinasid) {
        this.vattpinasid = vattpinasid;
    }

    public byte[] getOattachment() {
        return oattachment;
    }

    public void setOattachment(byte[] oattachment) {
        this.oattachment = oattachment;
    }

    public String getXgapmtasVpermitId() {
        return xgapmtasVpermitId;
    }

    public void setXgapmtasVpermitId(String xgapmtasVpermitId) {
        this.xgapmtasVpermitId = xgapmtasVpermitId;
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

    public AhmgaersTxnpmtinass getAtpAssetHeader() {
        return atpAssetHeader;
    }

    public void setAtpAssetHeader(AhmgaersTxnpmtinass atpAssetHeader) {
        this.atpAssetHeader = atpAssetHeader;
    }

}
