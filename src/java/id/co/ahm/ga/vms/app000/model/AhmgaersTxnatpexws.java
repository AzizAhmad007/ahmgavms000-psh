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
 * @author Yusuf Yadi Surya
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "AHMGAERS_TXNATPEXWS")
public class AhmgaersTxnatpexws  extends DefaultEntityImpl implements Serializable {
    @Id
    @Column(name = "VATTACHID")
    private String vattachid;
    
    @Column(name = "XGAPMTEX_VPERMIID")
    private String xgapmtexVpermiid;
    
    @Column(name = "XGAPMTEX_VDOCTYPE")
    private String xgapmtexVdoctype;
    
    @Column(name = "XGAPMTEX_VREGTYPE")
    private String xgapmtexVregtype;
    
    @Column(name = "OATTACHMENT")
    private byte[] oattachment;
    
    @Column(name = "VATTACHTYPE")
    private String vattachtype;
    
    @Column(name = "VATTACHDESC")
    private String vattachdesc;
    
    @Column(name = "NFILESIZE")
    private Integer nfilesize;
    
    @Column(name = "VFILENAME")
    private String vfilename;
    
    @Column(name = "VMIMETYPE")
    private String vmimetype;

    public String getVattachid() {
        return vattachid;
    }

    public void setVattachid(String vattachid) {
        this.vattachid = vattachid;
    }

    public String getXgapmtexVpermiid() {
        return xgapmtexVpermiid;
    }

    public void setXgapmtexVpermiid(String xgapmtexVpermiid) {
        this.xgapmtexVpermiid = xgapmtexVpermiid;
    }

    public String getXgapmtexVdoctype() {
        return xgapmtexVdoctype;
    }

    public void setXgapmtexVdoctype(String xgapmtexVdoctype) {
        this.xgapmtexVdoctype = xgapmtexVdoctype;
    }

    public String getXgapmtexVregtype() {
        return xgapmtexVregtype;
    }

    public void setXgapmtexVregtype(String xgapmtexVregtype) {
        this.xgapmtexVregtype = xgapmtexVregtype;
    }

    public byte[] getOattachment() {
        return oattachment;
    }

    public void setOattachment(byte[] oattachment) {
        this.oattachment = oattachment;
    }

    public String getVattachtype() {
        return vattachtype;
    }

    public void setVattachtype(String vattachtype) {
        this.vattachtype = vattachtype;
    }

    public String getVattachdesc() {
        return vattachdesc;
    }

    public void setVattachdesc(String vattachdesc) {
        this.vattachdesc = vattachdesc;
    }

    public Integer getNfilesize() {
        return nfilesize;
    }

    public void setNfilesize(Integer nfilesize) {
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
