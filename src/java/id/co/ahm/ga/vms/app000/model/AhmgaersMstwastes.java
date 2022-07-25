package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author A Roni Purwanto
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMGAERS_MSTWASTES")
public class AhmgaersMstwastes extends BaseAuditVersioning implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "VWASTEID")
    private String vwasteid;
    @Basic(optional = false)
    @Column(name = "VSTATUS")
    private String vstatus;

    @Column(name = "NSTORDUR")
    private BigDecimal nstordur;
    @Column(name = "DACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dactive;
    @Column(name = "VWBFLAG")
    private String vwbflag;
    @Column(name = "VSCFLAG")
    private String vscflag;
    @Column(name = "VWASTEUNIT")
    private String vwasteunit;
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
    @Column(name = "VPACKGEFLG")
    private String vpackgeflg;

    @OneToMany(mappedBy = "masterWaste", fetch = FetchType.LAZY)
    private List<AhmgaersMstdtlwastes> detailWastes;

    @OneToMany(mappedBy = "masterWaste", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AhmgaersMstcharwastes> characteristicsWastes;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VWASTETYPE", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_WUJUD'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings wasteTypeDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VB3NONB3", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_B3NONB3'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings b3NonB3Detail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VWASTEVL", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_WASTEVL'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings wasteValueDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VTRANSTYP", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_TRANSTYP'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings transactionTypeDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VENTRYNT", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_ENTRYNT'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings entryNoteDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VENTRYWGHT", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_ENTRYWGHT'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings entryWeightDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VSTORAGELOC", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_STORAGELOC'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings storageLocationDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VSTATUS", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_MASTER_STS'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings statusDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VOCNONOC", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_OCNONOC'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings ocNonOcDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VFDDISPLTRX", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_FDDISPLTRX'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings fullDayDisposalDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VWSCREQ", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_WSCREQ'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings whStockCountingDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VPACKAGE", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_PACKAGE'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings packagingTypeDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VWBFLAG", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_TRANS_UNIT'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings wbflagDetail;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VSCFLAG", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_VSCFLAG'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings scflagDetail;
    
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VPACKGEFLG", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_PACKAGEFLG'", referencedColumnName = "RSET_VID"))

    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings packgeFlgDetail;

    @Formula("(SELECT C.VNAMA FROM AHMMOERP_MSTKARYAWANS C  where to_char(C.IIDNRP) = vcrea)")
    private String createdName;
    @Formula("(SELECT C.VNAMA FROM AHMMOERP_MSTKARYAWANS C  where to_char(C.IIDNRP) = vmodi)")
    private String modifiedName;

    public AhmgaersMstwastes() {
    }

    public List<AhmgaersMstcharwastes> getCharacteristicsWastes() {        
        return characteristicsWastes;
    }

    public void setCharacteristicsWastes(List<AhmgaersMstcharwastes> characteristicsWastes) {
        this.characteristicsWastes = characteristicsWastes;
    }

    public void setWasteTypeDetail(AhmmoerpDtlsettings wasteTypeDetail) {
        this.wasteTypeDetail = wasteTypeDetail;
    }

    public void setB3NonB3Detail(AhmmoerpDtlsettings b3NonB3Detail) {
        this.b3NonB3Detail = b3NonB3Detail;
    }

    public void setWasteValueDetail(AhmmoerpDtlsettings wasteValueDetail) {
        this.wasteValueDetail = wasteValueDetail;
    }

    public void setTransactionTypeDetail(AhmmoerpDtlsettings transactionTypeDetail) {
        this.transactionTypeDetail = transactionTypeDetail;
    }

    public void setEntryNoteDetail(AhmmoerpDtlsettings entryNoteDetail) {
        this.entryNoteDetail = entryNoteDetail;
    }

    public void setEntryWeightDetail(AhmmoerpDtlsettings entryWeightDetail) {
        this.entryWeightDetail = entryWeightDetail;
    }

    public void setStorageLocationDetail(AhmmoerpDtlsettings storageLocationDetail) {
        this.storageLocationDetail = storageLocationDetail;
    }

    public void setStatusDetail(AhmmoerpDtlsettings statusDetail) {
        this.statusDetail = statusDetail;
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

    public AhmmoerpDtlsettings getWasteTypeDetail() {
        return wasteTypeDetail;
    }

    public AhmmoerpDtlsettings getB3NonB3Detail() {
        return b3NonB3Detail;
    }

    public AhmmoerpDtlsettings getWasteValueDetail() {
        return wasteValueDetail;
    }

    public AhmmoerpDtlsettings getTransactionTypeDetail() {
        return transactionTypeDetail;
    }

    public AhmmoerpDtlsettings getEntryNoteDetail() {
        return entryNoteDetail;
    }

    public AhmmoerpDtlsettings getEntryWeightDetail() {
        return entryWeightDetail;
    }

    public AhmmoerpDtlsettings getStorageLocationDetail() {
        return storageLocationDetail;
    }

    public AhmmoerpDtlsettings getStatusDetail() {
        return statusDetail;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    public List<AhmgaersMstdtlwastes> getDetailWastes() {
        return detailWastes;
    }

    public void setDetailWastes(List<AhmgaersMstdtlwastes> detailWastes) {
        this.detailWastes = detailWastes;
    }

    public AhmmoerpDtlsettings getOcNonOcDetail() {
        return ocNonOcDetail;
    }

    public void setOcNonOcDetail(AhmmoerpDtlsettings ocNonOcDetail) {
        this.ocNonOcDetail = ocNonOcDetail;
    }

    public AhmmoerpDtlsettings getFullDayDisposalDetail() {
        return fullDayDisposalDetail;
    }

    public void setFullDayDisposalDetail(AhmmoerpDtlsettings fullDayDisposalDetail) {
        this.fullDayDisposalDetail = fullDayDisposalDetail;
    }

    public AhmmoerpDtlsettings getWhStockCountingDetail() {
        return whStockCountingDetail;
    }

    public void setWhStockCountingDetail(AhmmoerpDtlsettings whStockCountingDetail) {
        this.whStockCountingDetail = whStockCountingDetail;
    }

    public AhmmoerpDtlsettings getPackagingTypeDetail() {
        return packagingTypeDetail;
    }

    public void setPackagingTypeDetail(AhmmoerpDtlsettings packagingTypeDetail) {
        this.packagingTypeDetail = packagingTypeDetail;
    }

    public AhmgaersMstwastes(String vwasteid) {
        this.vwasteid = vwasteid;
    }

    public String getVwasteid() {
        return vwasteid;
    }

    public void setVwasteid(String vwasteid) {
        this.vwasteid = vwasteid;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public BigDecimal getNstordur() {
        return nstordur;
    }

    public void setNstordur(BigDecimal nstordur) {
        this.nstordur = nstordur;
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

    public AhmmoerpDtlsettings getScflagDetail() {
        return scflagDetail;
    }

    public void setScflagDetail(AhmmoerpDtlsettings scflagDetail) {
        this.scflagDetail = scflagDetail;
    }

    public AhmmoerpDtlsettings getWbflagDetail() {
        return wbflagDetail;
    }

    public void setWbflagDetail(AhmmoerpDtlsettings wbflagDetail) {
        this.wbflagDetail = wbflagDetail;
    }

    public String getVpackgeflg() {
        return vpackgeflg;
    }

    public void setVpackgeflg(String vpackgeflg) {
        this.vpackgeflg = vpackgeflg;
    }

    public AhmmoerpDtlsettings getPackgeFlgDetail() {
        return packgeFlgDetail;
    }

    public void setPackgeFlgDetail(AhmmoerpDtlsettings packgeFlgDetail) {
        this.packgeFlgDetail = packgeFlgDetail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vwasteid != null ? vwasteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmgaersMstwastes)) {
            return false;
        }
        AhmgaersMstwastes other = (AhmgaersMstwastes) object;
        if ((this.vwasteid == null && other.vwasteid != null) || (this.vwasteid != null && !this.vwasteid.equals(other.vwasteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.ga.ers.app000.model.AhmgaersMstwastes[ vwasteid=" + vwasteid + " ]";
    }

}
