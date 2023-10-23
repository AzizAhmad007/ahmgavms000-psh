/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

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
 * @author kahfi
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMMOSCD_MSTLOCS")
public class AhmmoscdMstlocs extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    protected AhmmoscdMstlocsPk ahmmoscdMstlocsPk;
    
    @Column(name = "VDESC")
    private String vdesc;

    public AhmmoscdMstlocsPk getAhmmoscdMstlocsPk() {
        return ahmmoscdMstlocsPk;
    }

    public void setAhmmoscdMstlocsPk(AhmmoscdMstlocsPk ahmmoscdMstlocsPk) {
        this.ahmmoscdMstlocsPk = ahmmoscdMstlocsPk;
    }

    public String getVdesc() {
        return vdesc;
    }

    public void setVdesc(String vdesc) {
        this.vdesc = vdesc;
    }
}
