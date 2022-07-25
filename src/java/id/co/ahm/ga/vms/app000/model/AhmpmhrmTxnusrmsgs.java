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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AHMPMHRM_TXNUSRMSGS")
public class AhmpmhrmTxnusrmsgs extends DefaultEntityImpl implements Serializable {
    @Id
    @Column(name = "IID")
    private int iid;
    
    @Column(name = "VUSERNAME")
    private String vusername;
    
    @Column(name = "XPMMSG_IID")
    private int xpmmsgId;
    
    @Column(name = "BISDELETE")
    private Boolean bisdelete;
    
    @Column(name = "BISMARKED")
    private Boolean bismarked;
    
    @Column(name = "BISREAD")
    private Boolean bisread;
    
    @Column(name = "BISSENT")
    private Boolean bissent;

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }

    public Boolean getBisdelete() {
        return bisdelete;
    }

    public void setBisdelete(Boolean bisdelete) {
        this.bisdelete = bisdelete;
    }

    public Boolean getBismarked() {
        return bismarked;
    }

    public void setBismarked(Boolean bismarked) {
        this.bismarked = bismarked;
    }

    public Boolean getBisread() {
        return bisread;
    }

    public void setBisread(Boolean bisread) {
        this.bisread = bisread;
    }

    public Boolean getBissent() {
        return bissent;
    }

    public void setBissent(Boolean bissent) {
        this.bissent = bissent;
    }

    public int getXpmmsgId() {
        return xpmmsgId;
    }

    public void setXpmmsgId(int xpmmsgId) {
        this.xpmmsgId = xpmmsgId;
    }
    
    

}
