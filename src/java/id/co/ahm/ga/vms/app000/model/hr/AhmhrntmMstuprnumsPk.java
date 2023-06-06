/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model.hr;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author reza.mr
 */
@Embeddable
public class AhmhrntmMstuprnumsPk implements Serializable{
    
    @Column(name = "VABSTCODE", nullable = false, length = 10)
    private String vabstcode;

    @Column(name = "NYEAR", nullable = false)
    private BigDecimal nyear;

    public String getVabstcode() {
        return vabstcode;
    }

    public void setVabstcode(String vabstcode) {
        this.vabstcode = vabstcode;
    }

    public BigDecimal getNyear() {
        return nyear;
    }

    public void setNyear(BigDecimal nyear) {
        this.nyear = nyear;
    }
    
    
}
