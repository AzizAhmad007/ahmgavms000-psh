package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author teguh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "AHMGAERS_MSTDTLWASTES")
public class AhmgaersMstdtlwastes extends BaseAuditVersioning implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "VID", updatable = false, nullable = false)
    private String id;
    @Basic(optional = false)
    @Column(name = "VWASTEID")
    private String vwasteid;
    @Basic(optional = false)
    @Column(name = "DINACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dinactive;
    @Basic(optional = false)
    @Column(name = "NSEQDTLWST")
    private int nseqdtlwst;
    @Basic(optional = false)
    @Column(name = "VSTATUS")
    private String vstatus;

    private static final long serialVersionUID = 1L;
    
    @Column(name = "VRGLCODE")
    private String vrglcode;
    @Column(name = "VRGLCODEDSC")
    private String vrglcodedsc;
    @Basic(optional = false)
    @Column(name = "VPLANTID")
    private String vplantid;
    @Column(name = "VTPSID")
    private String vtpsid;
    @Column(name = "VWAHOSLOCID")
    private String vwahoslocid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NSTORCAP")
    private BigDecimal nstorcap;

    @JoinColumns({
        @JoinColumn(name = "VWASTEID", referencedColumnName = "VWASTEID", insertable = false, updatable = false)}
    )
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AhmgaersMstwastes masterWaste;

    public AhmgaersMstdtlwastes() {
    }

    

    public String getVrglcode() {
        return vrglcode;
    }

    public void setVrglcode(String vrglcode) {
        this.vrglcode = vrglcode;
    }

    public String getVrglcodedsc() {
        return vrglcodedsc;
    }

    public void setVrglcodedsc(String vrglcodedsc) {
        this.vrglcodedsc = vrglcodedsc;
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

    public String getVwahoslocid() {
        return vwahoslocid;
    }

    public void setVwahoslocid(String vwahoslocid) {
        this.vwahoslocid = vwahoslocid;
    }

    public BigDecimal getNstorcap() {
        return nstorcap;
    }

    public void setNstorcap(BigDecimal nstorcap) {
        this.nstorcap = nstorcap;
    }

    public AhmgaersMstwastes getMasterWaste() {
        return masterWaste;
    }

    public void setMasterWaste(AhmgaersMstwastes masterWaste) {
        this.masterWaste = masterWaste;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVwasteid() {
        return vwasteid;
    }

    public void setVwasteid(String vwasteid) {
        this.vwasteid = vwasteid;
    }

    public Date getDinactive() {
        return dinactive;
    }

    public void setDinactive(Date dinactive) {
        this.dinactive = dinactive;
    }

    public int getNseqdtlwst() {
        return nseqdtlwst;
    }

    public void setNseqdtlwst(int nseqdtlwst) {
        this.nseqdtlwst = nseqdtlwst;
    }

    
    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final AhmgaersMstdtlwastes other = (AhmgaersMstdtlwastes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
