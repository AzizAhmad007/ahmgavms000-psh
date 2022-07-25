package id.co.ahm.ga.vms.app022.util;

import java.util.Map;

/**
 *
 * @author Yusuf Yadi Surya
 */
public class Vms022DownloadConfigDTO {
   
    private boolean isAddNumbering = false;
    private boolean uppercase;
    private Integer sheetNo = 0;
    private Integer startDetailRow = 0;
    private Integer startHeaderRow = 0;
    private Integer startColumn = 0;// For copyying stye
    private Integer endColumn = 0; //
    
    private Integer numberingColumn = 0;
    private String staticStyleTemplate = "";    
    private Map<String, String> headerMapping; // <"A1","partId">
    private Map<Integer, String> detailMapping; //<"1", "partId">
    private Map<String, Object> staticData; //<"1", "partId">

    public boolean isIsAddNumbering() {
        return isAddNumbering;
    }

    public void setIsAddNumbering(boolean isAddNumbering) {
        this.isAddNumbering = isAddNumbering;
    }

    public boolean isUppercase() {
        return uppercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }

    public Integer getSheetNo() {
        return sheetNo;
    }

    public void setSheetNo(Integer sheetNo) {
        this.sheetNo = sheetNo;
    }

    public Integer getStartDetailRow() {
        return startDetailRow;
    }

    public void setStartDetailRow(Integer startDetailRow) {
        this.startDetailRow = startDetailRow;
    }

    public Integer getStartHeaderRow() {
        return startHeaderRow;
    }

    public void setStartHeaderRow(Integer startHeaderRow) {
        this.startHeaderRow = startHeaderRow;
    }

    public Integer getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(Integer startColumn) {
        this.startColumn = startColumn;
    }

    public Integer getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(Integer endColumn) {
        this.endColumn = endColumn;
    }

    public Integer getNumberingColumn() {
        return numberingColumn;
    }

    public void setNumberingColumn(Integer numberingColumn) {
        this.numberingColumn = numberingColumn;
    }

    public String getStaticStyleTemplate() {
        return staticStyleTemplate;
    }

    public void setStaticStyleTemplate(String staticStyleTemplate) {
        this.staticStyleTemplate = staticStyleTemplate;
    }

    public Map<String, String> getHeaderMapping() {
        return headerMapping;
    }

    public void setHeaderMapping(Map<String, String> headerMapping) {
        this.headerMapping = headerMapping;
    }

    public Map<Integer, String> getDetailMapping() {
        return detailMapping;
    }

    public void setDetailMapping(Map<Integer, String> detailMapping) {
        this.detailMapping = detailMapping;
    }

    public Map<String, Object> getStaticData() {
        return staticData;
    }

    public void setStaticData(Map<String, Object> staticData) {
        this.staticData = staticData;
    }
    
    
}
