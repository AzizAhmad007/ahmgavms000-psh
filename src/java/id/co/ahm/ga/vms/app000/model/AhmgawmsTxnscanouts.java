package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author teguh
 */
@Entity
@Table(name = "AHMGAWMS_TXNSCANOUTS")
public class AhmgawmsTxnscanouts extends BaseAuditVersioning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "VWASTEOUTID")
    private String vwasteoutid;

    @Basic(optional = false)
    @Column(name = "VWASTEID")
    private String vwasteid;
    @Column(name = "NWASTEOUT")
    private BigDecimal nwasteout;
    @Column(name = "VTRANSUNIT")
    private String vtransunit;

    @Column(name = "NTAREWGHT")
    private BigDecimal ntarewght;
    @Column(name = "NGROSSWGHT")
    private BigDecimal ngrosswght;
    @Column(name = "NNETTOWGHT")
    private BigDecimal nnettowght;
    @Column(name = "DINACTIVE")
    private Date dinactive;

    public String getVwasteoutid() {
        return vwasteoutid;
    }

    public void setVwasteoutid(String vwasteoutid) {
        this.vwasteoutid = vwasteoutid;
    }

    public String getVwasteid() {
        return vwasteid;
    }

    public void setVwasteid(String vwasteid) {
        this.vwasteid = vwasteid;
    }

    public BigDecimal getNwasteout() {
        return nwasteout;
    }

    public void setNwasteout(BigDecimal nwasteout) {
        this.nwasteout = nwasteout;
    }

    public String getVtransunit() {
        return vtransunit;
    }

    public void setVtransunit(String vtransunit) {
        this.vtransunit = vtransunit;
    }

    public BigDecimal getNtarewght() {
        return ntarewght;
    }

    public void setNtarewght(BigDecimal ntarewght) {
        this.ntarewght = ntarewght;
    }

    public BigDecimal getNgrosswght() {
        return ngrosswght;
    }

    public void setNgrosswght(BigDecimal ngrosswght) {
        this.ngrosswght = ngrosswght;
    }

    public BigDecimal getNnettowght() {
        return nnettowght;
    }

    public void setNnettowght(BigDecimal nnettowght) {
        this.nnettowght = nnettowght;
    }

    public Date getDinactive() {
        return dinactive;
    }

    public void setDinactive(Date dinactive) {
        this.dinactive = dinactive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.vwasteoutid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AhmgawmsTxnscanouts other = (AhmgawmsTxnscanouts) obj;
        if (!Objects.equals(this.vwasteoutid, other.vwasteoutid)) {
            return false;
        }
        return true;
    }
    
    
}
