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
 * @author administator
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_MSTWSTVNDRS")
public class AhmgaersMstwstvndrs extends DefaultEntityImpl implements Serializable {
    @Id
    @Column(name="VWSTVENDRCD")
    private String vwstvendrcd;
    
    @Column(name="VWSTVENDRID")
    private String vwstvendrid;   
    
    @Column(name="VWSTVENDRDS")
    private String vwstvendrds;
    
    @Column(name="VWSTVENDRTY")
    private String vwstvendrty;
    
    @Column(name="VSTATUS")
    private String vstatus;

    public String getVwstvendrid() {
        return vwstvendrid;
    }

    public void setVwstvendrid(String vwstvendrid) {
        this.vwstvendrid = vwstvendrid;
    }

    public String getVwstvendrcd() {
        return vwstvendrcd;
    }

    public void setVwstvendrcd(String vwstvendrcd) {
        this.vwstvendrcd = vwstvendrcd;
    }

    public String getVwstvendrds() {
        return vwstvendrds;
    }

    public void setVwstvendrds(String vwstvendrds) {
        this.vwstvendrds = vwstvendrds;
    }

    public String getVwstvendrty() {
        return vwstvendrty;
    }

    public void setVwstvendrty(String vwstvendrty) {
        this.vwstvendrty = vwstvendrty;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String status) {
        this.vstatus = status;
    }
}
