/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model.hr;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author reza.mr
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMHRNTM_DTLOTSREGS")
public class AhmhrntmDtlotsregs extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    private AhmhrntmDtlotsregsPk ahmhrntmDtlotsregsPk;
      
    @Column(name = "VFILE", length = 100)
    private String vfile;

    @Column(name = "VPLANT", length = 10)
    private String vplant;

    @Column(name = "VGATE", length = 10)
    private String vgate;
    
    public AhmhrntmDtlotsregsPk getAhmhrntmDtlotsregsPk() {
        return ahmhrntmDtlotsregsPk;
    }

    public void setAhmhrntmDtlotsregsPk(AhmhrntmDtlotsregsPk ahmhrntmDtlotsregsPk) {
        this.ahmhrntmDtlotsregsPk = ahmhrntmDtlotsregsPk;
    }

    public String getVfile() {
        return vfile;
    }

    public void setVfile(String vfile) {
        this.vfile = vfile;
    }

    public String getVplant() {
        return vplant;
    }

    public void setVplant(String vplant) {
        this.vplant = vplant;
    }

    public String getVgate() {
        return vgate;
    }

    public void setVgate(String vgate) {
        this.vgate = vgate;
    }    
}
