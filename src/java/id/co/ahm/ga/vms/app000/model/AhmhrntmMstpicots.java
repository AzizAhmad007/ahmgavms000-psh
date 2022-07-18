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
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author ayik.op
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMHRNTM_MSTPICOTS")
public class AhmhrntmMstpicots extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "RPICOTSID")
    private String rpicotsid;
        
    @Column(name = "VPGBLCD", length = 10, nullable = false)
    private String vpgblcd;
    
    @Column(name = "VOTSTYPE", length = 25, nullable = false)
    private String votstype;
    
    @Column(name = "DBGNEFFDT")
    private Date dbgneffdt;
    
    @Column(name = "DENDEFFDT")
    private Date dendeffdt;
    
    @Column(name = "VRGSROLE", length = 30)
    private String vrgsrole;

    @Column(name = "VAREA", length = 30)
    private String varea;       

    public String getRpicotsid() {
        return rpicotsid;
    }

    public void setRpicotsid(String rpicotsid) {
        this.rpicotsid = rpicotsid;
    }

    public String getVpgblcd() {
        return vpgblcd;
    }

    public void setVpgblcd(String vpgblcd) {
        this.vpgblcd = vpgblcd;
    }

    public String getVotstype() {
        return votstype;
    }

    public void setVotstype(String votstype) {
        this.votstype = votstype;
    }

    public Date getDbgneffdt() {
        return dbgneffdt;
    }

    public void setDbgneffdt(Date dbgneffdt) {
        this.dbgneffdt = dbgneffdt;
    }

    public Date getDendeffdt() {
        return dendeffdt;
    }

    public void setDendeffdt(Date dendeffdt) {
        this.dendeffdt = dendeffdt;
    }

    public String getVrgsrole() {
        return vrgsrole;
    }

    public void setVrgsrole(String vrgsrole) {
        this.vrgsrole = vrgsrole;
    }

    public String getVarea() {
        return varea;
    }

    public void setVarea(String varea) {
        this.varea = varea;
    }
        
    
}
