package id.co.ahm.ga.vms.app000.model;

import id.co.ahm.jx.sys.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author teguh
 */
@Entity
@Table(name = "AHMGAERS_MSTCHARWASTES")
public class AhmgaersMstcharwastes extends BaseAuditVersioning implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "VID", updatable = false, nullable = false)
    private String id;
    @Basic(optional = false)
    @Column(name = "VWASTEID")
    private String vwasteid;
    @Basic(optional = false)
    @Column(name = "VCHARWASTE")
    private String vcharwaste;
    @Basic(optional = false)
    @Column(name = "DINACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dinactive;
    
    @Basic(optional = false)
    @Column(name = "VSTATUS")
    private String vstatus;

  
    @JoinColumn(name = "VWASTEID",  referencedColumnName = "VWASTEID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AhmgaersMstwastes masterWaste;

    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "VCHARWASTE", referencedColumnName = "VITEMCODE", nullable = false, insertable = false, updatable = false))
        ,
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'AHMGAWMS_WASTE_CHAR'", referencedColumnName = "RSET_VID"))
    })
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmmoerpDtlsettings characteristicsDetail;

    public AhmgaersMstcharwastes() {
    }

    public AhmmoerpDtlsettings getCharacteristicsDetail() {
        return characteristicsDetail;
    }

    public void setCharacteristicsDetail(AhmmoerpDtlsettings characteristicsDetail) {
        this.characteristicsDetail = characteristicsDetail;
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

    public String getVcharwaste() {
        return vcharwaste;
    }

    public void setVcharwaste(String vcharwaste) {
        this.vcharwaste = vcharwaste;
    }

    public Date getDinactive() {
        return dinactive;
    }

    public void setDinactive(Date dinactive) {
        this.dinactive = dinactive;
    }

    

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final AhmgaersMstcharwastes other = (AhmgaersMstcharwastes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
