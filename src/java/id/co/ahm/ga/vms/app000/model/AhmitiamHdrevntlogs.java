/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
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
@Table(name = "AHMITIAM_HDREVNTLOGS")
public class AhmitiamHdrevntlogs extends DefaultEntityImpl implements Serializable {

    @EmbeddedId
    private AhmitiamHdrevntlogsPk ahmitiamHdrevntlogs;

    public AhmitiamHdrevntlogsPk getAhmitiamHdrevntlogs() {
        return ahmitiamHdrevntlogs;
    }

    public void setAhmitiamHdrevntlogs(AhmitiamHdrevntlogsPk ahmitiamHdrevntlogs) {
        this.ahmitiamHdrevntlogs = ahmitiamHdrevntlogs;
    }


}
