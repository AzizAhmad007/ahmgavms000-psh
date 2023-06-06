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
@Table(name = "AHMITIAM_DTLEVNTLOG")
public class AhmitiamDtlevntlogs extends DefaultEntityImpl implements Serializable {

    @EmbeddedId
    private AhmitiamDtlevntlogsPk ahmitiamDtlevntlogPk;

    @Column(name = "VEVENT")
    private String vevent;

    @Column(name = "VEVENTDESC")
    private String veventdesc;

    public AhmitiamDtlevntlogsPk getAhmitiamDtlevntlogPk() {
        return ahmitiamDtlevntlogPk;
    }

    public void setAhmitiamDtlevntlogPk(AhmitiamDtlevntlogsPk ahmitiamDtlevntlogPk) {
        this.ahmitiamDtlevntlogPk = ahmitiamDtlevntlogPk;
    }

    public String getVevent() {
        return vevent;
    }

    public void setVevent(String vevent) {
        this.vevent = vevent;
    }

    public String getVeventdesc() {
        return veventdesc;
    }

    public void setVeventdesc(String veventdesc) {
        this.veventdesc = veventdesc;
    }

}
