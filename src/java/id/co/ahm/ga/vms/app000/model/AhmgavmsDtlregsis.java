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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMGAVMS_DTLREGSIS")
public class AhmgavmsDtlregsis extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    protected AhmgavmsDtlregsisPk ahmgavmsDtlregsisPk;
    
    @Column(name = "VRESULT")
    private String vresult;
    
    @Column(name = "DENDSI")
    @Temporal(TemporalType.DATE)
    private Date dendsi;

    public AhmgavmsDtlregsisPk getAhmgavmsDtlregsisPk() {
        return ahmgavmsDtlregsisPk;
    }

    public void setAhmgavmsDtlregsisPk(AhmgavmsDtlregsisPk ahmgavmsDtlregsisPk) {
        this.ahmgavmsDtlregsisPk = ahmgavmsDtlregsisPk;
    }

    public String getVresult() {
        return vresult;
    }

    public void setVresult(String vresult) {
        this.vresult = vresult;
    }

    public Date getDendsi() {
        return dendsi;
    }

    public void setDendsi(Date dendsi) {
        this.dendsi = dendsi;
    }
    
    
}
