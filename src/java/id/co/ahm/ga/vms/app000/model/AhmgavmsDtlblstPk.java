/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app000.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Embeddable
public class AhmgavmsDtlblstPk implements Serializable{

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "NIDDTL")
    private Integer detailId;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

   

    
    
}
