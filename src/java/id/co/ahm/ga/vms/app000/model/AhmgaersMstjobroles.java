/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.ers.app000.model;

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
 * @author A Roni Purwanto
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_MSTJOBROLES")
public class AhmgaersMstjobroles extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VJOBROLEID")
    private String vjobroleid;
    
    @Column(name = "VJOBROLEDSC")
    private String vjobroledsc;

    @Column(name = "VSTATUS")
    private String vstatus;

    public String getVjobroleid() {
        return vjobroleid;
    }

    public void setVjobroleid(String vjobroleid) {
        this.vjobroleid = vjobroleid;
    }

    public String getVjobroledsc() {
        return vjobroledsc;
    }

    public void setVjobroledsc(String vjobroledsc) {
        this.vjobroledsc = vjobroledsc;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
}
