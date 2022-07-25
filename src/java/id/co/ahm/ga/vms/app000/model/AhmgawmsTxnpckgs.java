package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author teguh
 */
@Table(name = "AHMGAWMS_TXNPCKGS")
public class AhmgawmsTxnpckgs extends BaseAuditVersioning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VPCKGID")
    private String vpckgid;
    @Basic(optional = false)
    @Column(name = "VWSTRSRC")
    private String vwstrsrc;
    @Column(name = "VWSTDLVID")
    private String vwstdlvid;
    @Basic(optional = false)
    @Column(name = "VWASTEID")
    private String vwasteid;
    @Basic(optional = false)
    @Column(name = "VRGLCODE")
    private String vrglcode;
    @Basic(optional = false)
    @Column(name = "VPLANTID")
    private String vplantid;
    @Basic(optional = false)
    @Column(name = "VTPSID")
    private String vtpsid;
    @Basic(optional = false)
    @Column(name = "DINWASTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dinwaste;
    @Basic(optional = false)
    @Column(name = "VPACKAGE")
    private String vpackage;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NTTLWASTE")
    private BigDecimal nttlwaste;
    @Basic(optional = false)
    @Column(name = "NWEIGHTIN")
    private BigDecimal nweightin;
    @Basic(optional = false)
    @Column(name = "VSTATUS")
    private String vstatus;
    @Basic(optional = false)
    @Column(name = "VLASTSTS")
    private String vlaststs;   
    @Column(name = "DWSTINCTV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dwstinctv;
    @Column(name = "VWASTEUNIT")
    private String vwasteunit;
    @Column(name = "NPACKAGE")
    private BigDecimal npackage;

    public String getVpckgid() {
        return vpckgid;
    }

    public void setVpckgid(String vpckgid) {
        this.vpckgid = vpckgid;
    }

    public String getVwstrsrc() {
        return vwstrsrc;
    }

    public void setVwstrsrc(String vwstrsrc) {
        this.vwstrsrc = vwstrsrc;
    }

    public String getVwstdlvid() {
        return vwstdlvid;
    }

    public void setVwstdlvid(String vwstdlvid) {
        this.vwstdlvid = vwstdlvid;
    }

    public String getVwasteid() {
        return vwasteid;
    }

    public void setVwasteid(String vwasteid) {
        this.vwasteid = vwasteid;
    }

    public String getVrglcode() {
        return vrglcode;
    }

    public void setVrglcode(String vrglcode) {
        this.vrglcode = vrglcode;
    }

    public String getVplantid() {
        return vplantid;
    }

    public void setVplantid(String vplantid) {
        this.vplantid = vplantid;
    }

    public String getVtpsid() {
        return vtpsid;
    }

    public void setVtpsid(String vtpsid) {
        this.vtpsid = vtpsid;
    }

    public Date getDinwaste() {
        return dinwaste;
    }

    public void setDinwaste(Date dinwaste) {
        this.dinwaste = dinwaste;
    }

    public String getVpackage() {
        return vpackage;
    }

    public void setVpackage(String vpackage) {
        this.vpackage = vpackage;
    }

    public BigDecimal getNttlwaste() {
        return nttlwaste;
    }

    public void setNttlwaste(BigDecimal nttlwaste) {
        this.nttlwaste = nttlwaste;
    }

    public BigDecimal getNweightin() {
        return nweightin;
    }

    public void setNweightin(BigDecimal nweightin) {
        this.nweightin = nweightin;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public String getVlaststs() {
        return vlaststs;
    }

    public void setVlaststs(String vlaststs) {
        this.vlaststs = vlaststs;
    }

    public Date getDwstinctv() {
        return dwstinctv;
    }

    public void setDwstinctv(Date dwstinctv) {
        this.dwstinctv = dwstinctv;
    }

    public String getVwasteunit() {
        return vwasteunit;
    }

    public void setVwasteunit(String vwasteunit) {
        this.vwasteunit = vwasteunit;
    }

    public BigDecimal getNpackage() {
        return npackage;
    }

    public void setNpackage(BigDecimal npackage) {
        this.npackage = npackage;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.vpckgid);
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
        final AhmgawmsTxnpckgs other = (AhmgawmsTxnpckgs) obj;
        if (!Objects.equals(this.vpckgid, other.vpckgid)) {
            return false;
        }
        return true;
    }
    
    
    
}
