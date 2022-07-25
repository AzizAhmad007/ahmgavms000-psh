package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author teguh
 */
@Entity
@Table(name = "AHMGAERS_MSTWASTHIS")
public class AhmgaersMstwasthis extends BaseAuditVersioning implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AhmgaersMstwasthisPK ahmgaersMstwasthisPK;
    @Basic(optional = false)
    @Column(name = "VWASTETYPE")
    private String vwastetype;
    @Basic(optional = false)
    @Column(name = "VWASTEDESC")
    private String vwastedesc;
    @Basic(optional = false)
    @Column(name = "VB3NONB3")
    private String vb3nonb3;
    @Basic(optional = false)
    @Column(name = "VWASTEVL")
    private String vwastevl;
    @Basic(optional = false)
    @Column(name = "VTRANSTYP")
    private String vtranstyp;
    @Basic(optional = false)
    @Column(name = "VOCNONOC")
    private String vocnonoc;
    @Basic(optional = false)
    @Column(name = "VSTORAGELOC")
    private String vstorageloc;
    @Column(name = "VRGLCODE")
    private String vrglcode;
    @Column(name = "VRGLCODEDSC")
    private String vrglcodedsc;
    @Basic(optional = false)
    @Column(name = "VFDDISPLTRX")
    private String vfddispltrx;
    @Basic(optional = false)
    @Column(name = "VWSCREQ")
    private String vwscreq;
    @Basic(optional = false)
    @Column(name = "VPACKAGE")
    private String vpackage;
    @Basic(optional = false)
    @Column(name = "VENTRYNT")
    private String ventrynt;
    @Column(name = "VENTRYWGHT")
    private String ventrywght;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NWGHTPPKG")
    private BigDecimal nwghtppkg;
    @Column(name = "NWASTETL")
    private BigDecimal nwastetl;
    @Basic(optional = false)
    @Column(name = "NSTORDUR")
    private BigDecimal nstordur;
    @Basic(optional = false)
    @Column(name = "VSTATUS")
    private String vstatus;
    @Basic(optional = false)
    @Column(name = "DACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dactive;
    @Column(name = "VWBFLAG")
    private String vwbflag;
    @Column(name = "VSCFLAG")
    private String vscflag;
    @Column(name = "VWASTEUNIT")
    private String vwasteunit;
    

    public AhmgaersMstwasthis() {
    }

    public AhmgaersMstwasthis(AhmgaersMstwasthisPK ahmgaersMstwasthisPK) {
        this.ahmgaersMstwasthisPK = ahmgaersMstwasthisPK;
    }

    

    public AhmgaersMstwasthis(String vwasteid, Date dinactive) {
        this.ahmgaersMstwasthisPK = new AhmgaersMstwasthisPK(vwasteid, dinactive);
    }

    public AhmgaersMstwasthisPK getAhmgaersMstwasthisPK() {
        return ahmgaersMstwasthisPK;
    }

    public void setAhmgaersMstwasthisPK(AhmgaersMstwasthisPK ahmgaersMstwasthisPK) {
        this.ahmgaersMstwasthisPK = ahmgaersMstwasthisPK;
    }

    public String getVwastetype() {
        return vwastetype;
    }

    public void setVwastetype(String vwastetype) {
        this.vwastetype = vwastetype;
    }

    public String getVwastedesc() {
        return vwastedesc;
    }

    public void setVwastedesc(String vwastedesc) {
        this.vwastedesc = vwastedesc;
    }

    public String getVb3nonb3() {
        return vb3nonb3;
    }

    public void setVb3nonb3(String vb3nonb3) {
        this.vb3nonb3 = vb3nonb3;
    }

    public String getVwastevl() {
        return vwastevl;
    }

    public void setVwastevl(String vwastevl) {
        this.vwastevl = vwastevl;
    }

    public String getVtranstyp() {
        return vtranstyp;
    }

    public void setVtranstyp(String vtranstyp) {
        this.vtranstyp = vtranstyp;
    }

    public String getVocnonoc() {
        return vocnonoc;
    }

    public void setVocnonoc(String vocnonoc) {
        this.vocnonoc = vocnonoc;
    }

    public String getVstorageloc() {
        return vstorageloc;
    }

    public void setVstorageloc(String vstorageloc) {
        this.vstorageloc = vstorageloc;
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

    public String getVfddispltrx() {
        return vfddispltrx;
    }

    public void setVfddispltrx(String vfddispltrx) {
        this.vfddispltrx = vfddispltrx;
    }

    public String getVwscreq() {
        return vwscreq;
    }

    public void setVwscreq(String vwscreq) {
        this.vwscreq = vwscreq;
    }

    public String getVpackage() {
        return vpackage;
    }

    public void setVpackage(String vpackage) {
        this.vpackage = vpackage;
    }

    public String getVentrynt() {
        return ventrynt;
    }

    public void setVentrynt(String ventrynt) {
        this.ventrynt = ventrynt;
    }

    public String getVentrywght() {
        return ventrywght;
    }

    public void setVentrywght(String ventrywght) {
        this.ventrywght = ventrywght;
    }

    public BigDecimal getNwghtppkg() {
        return nwghtppkg;
    }

    public void setNwghtppkg(BigDecimal nwghtppkg) {
        this.nwghtppkg = nwghtppkg;
    }

    public BigDecimal getNwastetl() {
        return nwastetl;
    }

    public void setNwastetl(BigDecimal nwastetl) {
        this.nwastetl = nwastetl;
    }

    public BigDecimal getNstordur() {
        return nstordur;
    }

    public void setNstordur(BigDecimal nstordur) {
        this.nstordur = nstordur;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public Date getDactive() {
        return dactive;
    }

    public void setDactive(Date dactive) {
        this.dactive = dactive;
    }

    public String getVwbflag() {
        return vwbflag;
    }

    public void setVwbflag(String vwbflag) {
        this.vwbflag = vwbflag;
    }

    public String getVscflag() {
        return vscflag;
    }

    public void setVscflag(String vscflag) {
        this.vscflag = vscflag;
    }

    public String getVwasteunit() {
        return vwasteunit;
    }

    public void setVwasteunit(String vwasteunit) {
        this.vwasteunit = vwasteunit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ahmgaersMstwasthisPK != null ? ahmgaersMstwasthisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmgaersMstwasthis)) {
            return false;
        }
        AhmgaersMstwasthis other = (AhmgaersMstwasthis) object;
        if ((this.ahmgaersMstwasthisPK == null && other.ahmgaersMstwasthisPK != null) || (this.ahmgaersMstwasthisPK != null && !this.ahmgaersMstwasthisPK.equals(other.ahmgaersMstwasthisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.ga.ers.app000.model.AhmgaersMstwasthis[ ahmgaersMstwasthisPK=" + ahmgaersMstwasthisPK + " ]";
    }
    
}
