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
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNATPEXASS")
public class AhmgaersTxnatpexass extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VATPEXASID")
    private String vatpexasid;
    
    @Column(name = "XGAIKKAS_VPERMITID")
    private String xgaikkasVpermitid;
    
    @Column(name = "OATTACHMENT")
    private byte[] oattachment;
    
    @Column(name = "NFILESIZE")
    private long nfilesize;
    
    @Column(name="VFILENAME")
    private String vfilename;
    
    @Column(name="VMIMETYPE")
    private String vmimetype;

    public String getVatpexasid() {
        return vatpexasid;
    }

    public void setVatpexasid(String vatpexasid) {
        this.vatpexasid = vatpexasid;
    }   

    public String getXgaikkasVpermitid() {
        return xgaikkasVpermitid;
    }

    public void setXgaikkasVpermitid(String xgaikkasVpermitid) {
        this.xgaikkasVpermitid = xgaikkasVpermitid;
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
