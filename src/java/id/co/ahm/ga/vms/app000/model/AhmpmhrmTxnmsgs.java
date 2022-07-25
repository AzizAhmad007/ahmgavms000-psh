/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AHMPMHRM_TXNMSGS")
public class AhmpmhrmTxnmsgs extends DefaultEntityImpl implements Serializable {
    @GeneratedValue
    @Id
    @Column(name = "IID")
    private int iid;
    
    @Column(name = "VTITLE")
    private String vtitle;
    
    @Column(name = "VCONTENTS")
    private String vcontents;
    
    @Column(name = "BISDELETE")
    private Boolean bisdelete;
    
    @Column(name = "IMSGTYPE")
    private int imsgtype;
    
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getVcontents() {
        return vcontents;
    }

    public void setVcontents(String vcontents) {
        this.vcontents = vcontents;
    }

    public Boolean getBisdelete() {
        return bisdelete;
    }

    public void setBisdelete(Boolean bisdelete) {
        this.bisdelete = bisdelete;
    }

    public int getImsgtype() {
        return imsgtype;
    }

    public void setImsgtype(int imsgtype) {
        this.imsgtype = imsgtype;
    }

}
