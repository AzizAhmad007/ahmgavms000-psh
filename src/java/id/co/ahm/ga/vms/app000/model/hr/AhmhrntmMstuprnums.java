/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model.hr;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "AHMHRNTM_MSTUPRNUMS")
public class AhmhrntmMstuprnums extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    private AhmhrntmMstuprnumsPk ahmhrntmMstuprnumsPk;
        
    @Column(name = "NRUNNUMB")
    private BigDecimal nrunnumb;

    public AhmhrntmMstuprnumsPk getAhmhrntmMstuprnumsPk() {
        return ahmhrntmMstuprnumsPk;
    }

    public void setAhmhrntmMstuprnumsPk(AhmhrntmMstuprnumsPk ahmhrntmMstuprnumsPk) {
        this.ahmhrntmMstuprnumsPk = ahmhrntmMstuprnumsPk;
    }

    public BigDecimal getNrunnumb() {
        return nrunnumb;
    }

    public void setNrunnumb(BigDecimal nrunnumb) {
        this.nrunnumb = nrunnumb;
    }
    
    
}
