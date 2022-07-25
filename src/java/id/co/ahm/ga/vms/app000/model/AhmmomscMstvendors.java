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
 * @author Yusuf Yadi Surya
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "AHMMOMSC_MSTVENDORS")
public class AhmmomscMstvendors extends DefaultEntityImpl implements Serializable  {

    @Id
    @Column(name = "VVENDORID")
    private String vvendorid;

    @Column(name = "VVENDORDESC")
    private String vvendordesc;

    public String getVvendorid() {
        return vvendorid;
    }

    public void setVvendorid(String vvendorid) {
        this.vvendorid = vvendorid;
    }

    public String getVvendordesc() {
        return vvendordesc;
    }

    public void setVvendordesc(String vvendordesc) {
        this.vvendordesc = vvendordesc;
    }
}
