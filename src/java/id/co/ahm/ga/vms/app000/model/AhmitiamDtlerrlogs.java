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
 * @author kholish
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMITIAM_DTLERRLOGS")
public class AhmitiamDtlerrlogs extends DefaultEntityImpl implements Serializable {

    @EmbeddedId
    private AhmitiamDtlerrlogsPk ahmitiamDtlerrlogsPk;

    @Column(name = "VERRORCODE")
    private String verrorcode;

    @Column(name = "VERRORDESC")
    private String verrordesc;

    @Column(name = "VDATA")
    private String vdata;

    public AhmitiamDtlerrlogsPk getAhmitiamDtlerrlogsPk() {
        return ahmitiamDtlerrlogsPk;
    }

    public void setAhmitiamDtlerrlogsPk(AhmitiamDtlerrlogsPk ahmitiamDtlerrlogsPk) {
        this.ahmitiamDtlerrlogsPk = ahmitiamDtlerrlogsPk;
    }

    public String getVerrorcode() {
        return verrorcode;
    }

    public void setVerrorcode(String verrorcode) {
        this.verrorcode = verrorcode;
    }

    public String getVerrordesc() {
        return verrordesc;
    }

    public void setVerrordesc(String verrordesc) {
        this.verrordesc = verrordesc;
    }

    public String getVdata() {
        return vdata;
    }

    public void setVdata(String vdata) {
        this.vdata = vdata;
    }

}
