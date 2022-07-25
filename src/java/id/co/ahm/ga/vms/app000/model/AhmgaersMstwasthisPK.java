/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author teguh
 */
@Embeddable
public class AhmgaersMstwasthisPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "VWASTEID")
    private String vwasteid;
    @Basic(optional = false)
    @Column(name = "DINACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dinactive;

    public AhmgaersMstwasthisPK() {
    }

    public AhmgaersMstwasthisPK(String vwasteid, Date dinactive) {
        this.vwasteid = vwasteid;
        this.dinactive = dinactive;
    }

    public String getVwasteid() {
        return vwasteid;
    }

    public void setVwasteid(String vwasteid) {
        this.vwasteid = vwasteid;
    }

    public Date getDinactive() {
        return dinactive;
    }

    public void setDinactive(Date dinactive) {
        this.dinactive = dinactive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vwasteid != null ? vwasteid.hashCode() : 0);
        hash += (dinactive != null ? dinactive.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmgaersMstwasthisPK)) {
            return false;
        }
        AhmgaersMstwasthisPK other = (AhmgaersMstwasthisPK) object;
        if ((this.vwasteid == null && other.vwasteid != null) || (this.vwasteid != null && !this.vwasteid.equals(other.vwasteid))) {
            return false;
        }
        if ((this.dinactive == null && other.dinactive != null) || (this.dinactive != null && !this.dinactive.equals(other.dinactive))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.ga.ers.app000.model.AhmgaersMstwasthisPK[ vwasteid=" + vwasteid + ", dinactive=" + dinactive + " ]";
    }
    
}
