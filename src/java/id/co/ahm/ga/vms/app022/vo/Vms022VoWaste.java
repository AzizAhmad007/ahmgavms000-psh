package id.co.ahm.ga.vms.app022.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.ahm.jxf.json.CustomddMMMyyyyHHmmssDeserializer;
import id.co.ahm.jxf.json.CustomddMMMyyyyHHmmssSerializer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author developer
 */
public class Vms022VoWaste {
    
    private boolean sapMatNo;
    private String wasteId;
    private String wasteDesc;
    private String wasteType;
    private String wasteTypeDesc;
    private String b3NonB3;
    private String b3NonB3Desc;
    private String wasteValue;
    private String wasteValueDesc;
    private String transactionType;
    private String transactionTypeDesc;
    private String entryNote;
    private String entryNoteDesc;
    private String entryWeight;
    private String entryWeightDesc;
    private String storageLocation;
    private String storageLocationDesc;
    private String status;
    private String statusDesc;
    private String ocNonOc;
    private String ocNonOcDesc;
    private String fullDayDisposal;
    private String fullDayDisposalDesc;
    private String whStockCounting;
    private String whStockCountingDesc;
    private String packagingType;
    private String packagingTypeDesc;
    private BigDecimal weightPerPackage;
    private BigDecimal wasteOutTolerance;
    private BigDecimal storeDuration;
    
    private String regulationCode;
    private String regulationCodeDesc;
    private String plantId;
    private String tpsId;
    private String wahoSloc;
    private BigDecimal storeCapacity;
    
    private String wbFlag;
    private String wbFlagDesc;
    private String scFlag;
    private String scFlagDesc;
    private String uom;
    
    private String[] characteristicCode;
    private String[] characteristicCodeDesc;
    private String characteristics;
    private String characteristicCodes;

    private String modifiedUser;
    @JsonSerialize(using = CustomddMMMyyyyHHmmssSerializer.class)
    @JsonDeserialize(using = CustomddMMMyyyyHHmmssDeserializer.class)
    private Date modifiedDate;
    
    @JsonSerialize(using = CustomddMMMyyyyHHmmssSerializer.class)
    @JsonDeserialize(using = CustomddMMMyyyyHHmmssDeserializer.class)
    private Date inactive;
    
    private List<Vms022VoWasteDetail> detail;
    
    private String packgeFlg;
    private String packgeFlgDesc;

    public String getPackgeFlgDesc() {
        return packgeFlgDesc;
    }

    public void setPackgeFlgDesc(String packgeFlgDesc) {
        this.packgeFlgDesc = packgeFlgDesc;
    }

    public List<Vms022VoWasteDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<Vms022VoWasteDetail> detail) {
        this.detail = detail;
    }
    
    public String getWasteId() {
        return wasteId;
    }

    public void setWasteId(String wasteId) {
        this.wasteId = wasteId;
    }

    public String getWasteDesc() {
        return wasteDesc;
    }

    public boolean isSapMatNo() {
        return sapMatNo;
    }

    public void setSapMatNo(boolean sapMatNo) {
        this.sapMatNo = sapMatNo;
    }

    public void setWasteDesc(String wasteDesc) {
        this.wasteDesc = wasteDesc;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public String getB3NonB3() {
        return b3NonB3;
    }

    public void setB3NonB3(String b3NonB3) {
        this.b3NonB3 = b3NonB3;
    }

    public String getWasteValue() {
        return wasteValue;
    }

    public void setWasteValue(String wasteValue) {
        this.wasteValue = wasteValue;
    }

    public String getWasteValueDesc() {
        return wasteValueDesc;
    }

    public void setWasteValueDesc(String wasteValueDesc) {
        this.wasteValueDesc = wasteValueDesc;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionTypeDesc() {
        return transactionTypeDesc;
    }

    public void setTransactionTypeDesc(String transactionTypeDesc) {
        this.transactionTypeDesc = transactionTypeDesc;
    }

    public String getEntryNote() {
        return entryNote;
    }

    public void setEntryNote(String entryNote) {
        this.entryNote = entryNote;
    }

    public String getEntryNoteDesc() {
        return entryNoteDesc;
    }

    public void setEntryNoteDesc(String entryNoteDesc) {
        this.entryNoteDesc = entryNoteDesc;
    }

    public String getEntryWeight() {
        return entryWeight;
    }

    public void setEntryWeight(String entryWeight) {
        this.entryWeight = entryWeight;
    }

    public String getEntryWeightDesc() {
        return entryWeightDesc;
    }

    public void setEntryWeightDesc(String entryWeightDesc) {
        this.entryWeightDesc = entryWeightDesc;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getWasteTypeDesc() {
        return wasteTypeDesc;
    }

    public void setWasteTypeDesc(String wasteTypeDesc) {
        this.wasteTypeDesc = wasteTypeDesc;
    }

    public String getB3NonB3Desc() {
        return b3NonB3Desc;
    }

    public void setB3NonB3Desc(String b3NonB3Desc) {
        this.b3NonB3Desc = b3NonB3Desc;
    }

    public String getStorageLocationDesc() {
        return storageLocationDesc;
    }

    public void setStorageLocationDesc(String storageLocationDesc) {
        this.storageLocationDesc = storageLocationDesc;
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

    public String getFullDayDisposal() {
        return fullDayDisposal;
    }

    public void setFullDayDisposal(String fullDayDisposal) {
        this.fullDayDisposal = fullDayDisposal;
    }

    public String getWhStockCounting() {
        return whStockCounting;
    }

    public void setWhStockCounting(String whStockCounting) {
        this.whStockCounting = whStockCounting;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public BigDecimal getWeightPerPackage() {
        return weightPerPackage;
    }

    public void setWeightPerPackage(BigDecimal weightPerPackage) {
        this.weightPerPackage = weightPerPackage;
    }

    public BigDecimal getWasteOutTolerance() {
        return wasteOutTolerance;
    }

    public void setWasteOutTolerance(BigDecimal wasteOutTolerance) {
        this.wasteOutTolerance = wasteOutTolerance;
    }

    public BigDecimal getStoreDuration() {
        return storeDuration;
    }

    public void setStoreDuration(BigDecimal storeDuration) {
        this.storeDuration = storeDuration;
    }

    public String getOcNonOc() {
        return ocNonOc;
    }

    public void setOcNonOc(String ocNonOc) {
        this.ocNonOc = ocNonOc;
    }

    public String getOcNonOcDesc() {
        return ocNonOcDesc;
    }

    public void setOcNonOcDesc(String ocNonOcDesc) {
        this.ocNonOcDesc = ocNonOcDesc;
    }

    public String getFullDayDisposalDesc() {
        return fullDayDisposalDesc;
    }

    public void setFullDayDisposalDesc(String fullDayDisposalDesc) {
        this.fullDayDisposalDesc = fullDayDisposalDesc;
    }

    public String getWhStockCountingDesc() {
        return whStockCountingDesc;
    }

    public void setWhStockCountingDesc(String whStockCountingDesc) {
        this.whStockCountingDesc = whStockCountingDesc;
    }

    public String getPackagingTypeDesc() {
        return packagingTypeDesc;
    }

    public void setPackagingTypeDesc(String packagingTypeDesc) {
        this.packagingTypeDesc = packagingTypeDesc;
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

    public String[] getCharacteristicCode() {
        return characteristicCode;
    }

    public void setCharacteristicCode(String[] characteristicCode) {
        this.characteristicCode = characteristicCode;
    }

    public String[] getCharacteristicCodeDesc() {
        return characteristicCodeDesc;
    }

    public void setCharacteristicCodeDesc(String[] characteristicCodeDesc) {
        this.characteristicCodeDesc = characteristicCodeDesc;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    } 

    public String getWbFlag() {
        return wbFlag;
    }

    public void setWbFlag(String wbFlag) {
        this.wbFlag = wbFlag;
    }

    public String getWbFlagDesc() {
        return wbFlagDesc;
    }

    public void setWbFlagDesc(String wbFlagDesc) {
        this.wbFlagDesc = wbFlagDesc;
    }

    public String getScFlag() {
        return scFlag;
    }

    public void setScFlag(String scFlag) {
        this.scFlag = scFlag;
    }

    public String getScFlagDesc() {
        return scFlagDesc;
    }

    public void setScFlagDesc(String scFlagDesc) {
        this.scFlagDesc = scFlagDesc;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Date getInactive() {
        return inactive;
    }

    public void setInactive(Date inactive) {
        this.inactive = inactive;
    }

    public String getCharacteristicCodes() {
        return characteristicCodes;
    }

    public void setCharacteristicCodes(String characteristicCodes) {
        this.characteristicCodes = characteristicCodes;
    }

    public String getPackgeFlg() {
        return packgeFlg;
    }

    public void setPackgeFlg(String packgeFlg) {
        this.packgeFlg = packgeFlg;
    }
    
    
    
}
