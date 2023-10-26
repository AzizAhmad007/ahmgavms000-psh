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
 * @author Kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMSDTMS_DTLDRIVERS")
public class AhmsdtmsDtldrivers extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    protected AhmsdtmsDtldriversPk ahmsdtmsDtldriversPk;

    @Column(name = "DEND")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dend;
    
    @Column(name = "DSTART")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dstart;

    public AhmsdtmsDtldriversPk getAhmsdtmsDtldriversPk() {
        return ahmsdtmsDtldriversPk;
    }

    public void setAhmsdtmsDtldriversPk(AhmsdtmsDtldriversPk ahmsdtmsDtldriversPk) {
        this.ahmsdtmsDtldriversPk = ahmsdtmsDtldriversPk;
    }

    public Date getDend() {
        return dend;
    }

    public void setDend(Date dend) {
        this.dend = dend;
    }

    public Date getDstart() {
        return dstart;
    }

    public void setDstart(Date dstart) {
        this.dstart = dstart;
    }
}