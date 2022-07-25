package id.co.ahm.ga.vms.app022.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.ahm.jxf.json.CustomddMMMyyyyHHmmssDeserializer;
import id.co.ahm.jxf.json.CustomddMMMyyyyHHmmssSerializer;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author teguh
 */
public class Vms022VoWasteDetail {
    
    private String wasteId;
    private String wasteDesc;
    private Integer seq;
    private String regulationCode;
    private String regulationCodeDesc;
    private String plantId;
    private String tpsId;
    private String wahoSloc;
    private BigDecimal storeCapacity;
    private boolean isAdded;
    private String listRglCode;
    private String listRglDesc;
    
    public boolean isIsAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    private String modifiedUser;
    @JsonSerialize(using = CustomddMMMyyyyHHmmssSerializer.class)
    @JsonDeserialize(using = CustomddMMMyyyyHHmmssDeserializer.class)
    private Date modifiedDate;

    public String getWasteId() {
        return wasteId;
    }

    
    public void setWasteId(String wasteId) {
        this.wasteId = wasteId;
    }

    public String getWasteDesc() {
        return wasteDesc;
    }

    public void setWasteDesc(String wasteDesc) {
        this.wasteDesc = wasteDesc;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    
    public String getRegulationCode() {
        return regulationCode;
    }

    public void setRegulationCode(String regulationCode) {
        this.regulationCode = regulationCode;
    }

    public String getRegulationCodeDesc() {
        return regulationCodeDesc;
    }

    public void setRegulationCodeDesc(String regulationCodeDesc) {
        this.regulationCodeDesc = regulationCodeDesc;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getTpsId() {
        return tpsId;
    }

    public void setTpsId(String tpsId) {
        this.tpsId = tpsId;
    }

    public String getWahoSloc() {
        return wahoSloc;
    }

    public void setWahoSloc(String wahoSloc) {
        this.wahoSloc = wahoSloc;
    }

    public BigDecimal getStoreCapacity() {
        return storeCapacity;
    }

    public void setStoreCapacity(BigDecimal storeCapacity) {
        this.storeCapacity = storeCapacity;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    public String getListRglCode() {
        return listRglCode;
    }

    public void setListRglCode(String listRglCode) {
        this.listRglCode = listRglCode;
    }
    
    public String getListRglDesc() {
        return listRglDesc;
    }

    public void setListRglDesc(String listRglDesc) {
        this.listRglDesc = listRglDesc;
    }
    
}
