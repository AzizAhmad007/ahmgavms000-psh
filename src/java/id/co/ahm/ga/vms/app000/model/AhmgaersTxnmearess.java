/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author Yusuf Yadi Surya
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_TXNMEARESS")
public class AhmgaersTxnmearess extends DefaultEntityImpl implements Serializable {
    
    @Id
    @Column(name = "VMEARESID")
    private String vmearesid;
    
    @Column(name = "XGAMEAPR_VMEAPARAMID")
    private String xgameaprVmeaparamid;
    
    @Column(name = "XGAMELOC_VMEALOCID")
    private String xgamelocVmealocid;
    
    @ManyToOne(fetch = FetchType.LAZY , targetEntity = AhmgaersTxnmealocs.class)
    @JoinColumn(name = "XGAMELOC_VMEALOCID", referencedColumnName = "VMEALOCID", insertable = false, updatable = false)
    private AhmgaersTxnmealocs ahmgaersTxnmealocs;
    
    @ManyToOne(fetch = FetchType.LAZY , targetEntity = AhmgaersTxnmeaparas.class)
    @JoinColumns({
        @JoinColumn(name = "XGAMEAPR_VMEAPARAMID", referencedColumnName = "VMEAPARAMID", insertable = false, updatable = false),
        @JoinColumn(name = "XGAMEAPR_VPARAVMEACD", referencedColumnName = "VPARAVMEACD", insertable = false, updatable = false),
    })
    private AhmgaersTxnmeaparas ahmgaersTxnmeaparas;
    
    @Column(name = "XGAMEAPR_VPARAVMEACD")
    private String xgameaprVparavmeacd;
    
    @Column(name = "FACTVALUE", precision = 126, scale = 40)
    private BigDecimal factvalue;
    
    @Column(name = "VRESULT")
    private String vresult;
    
    @Column(name = "VCOMMENT")
    private String vcomment;
    
    @Column(name = "VSTATUS")
    private String vstatus;

    public String getVmearesid() {
        return vmearesid;
    }

    public void setVmearesid(String vmearesid) {
        this.vmearesid = vmearesid;
    }    

    public String getXgameaprVmeaparamid() {
        return xgameaprVmeaparamid;
    }

    public void setXgameaprVmeaparamid(String xgameaprVmeaparamid) {
        this.xgameaprVmeaparamid = xgameaprVmeaparamid;
    }

    public String getXgamelocVmealocid() {
        return xgamelocVmealocid;
    }

    public void setXgamelocVmealocid(String xgamelocVmealocid) {
        this.xgamelocVmealocid = xgamelocVmealocid;
    }

    public String getXgameaprVparavmeacd() {
        return xgameaprVparavmeacd;
    }

    public void setXgameaprVparavmeacd(String xgameaprVparavmeacd) {
        this.xgameaprVparavmeacd = xgameaprVparavmeacd;
    }

    public BigDecimal getFactvalue() {
        return factvalue;
    }

    public void setFactvalue(BigDecimal factvalue) {
        this.factvalue = factvalue;
    }

    public String getVresult() {
        return vresult;
    }

    public void setVresult(String vresult) {
        this.vresult = vresult;
    }

    public String getVcomment() {
        return vcomment;
    }

    public void setVcomment(String vcomment) {
        this.vcomment = vcomment;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }    

    public AhmgaersTxnmealocs getAhmgaersTxnmealocs() {
        return ahmgaersTxnmealocs;
    }

    public void setAhmgaersTxnmealocs(AhmgaersTxnmealocs ahmgaersTxnmealocs) {
        this.ahmgaersTxnmealocs = ahmgaersTxnmealocs;
    }

    public AhmgaersTxnmeaparas getAhmgaersTxnmeaparas() {
        return ahmgaersTxnmeaparas;
    }

    public void setAhmgaersTxnmeaparas(AhmgaersTxnmeaparas ahmgaersTxnmeaparas) {
        this.ahmgaersTxnmeaparas = ahmgaersTxnmeaparas;
    }
}
