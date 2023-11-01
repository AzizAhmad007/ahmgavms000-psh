/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.upload;

import static com.itextpdf.text.pdf.PdfName.T;
import id.co.ahm.ga.vms.app031.vo.Vms031VoErrorUpload;
import java.util.List;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031UploadResultDTO<T> {
    private List<T> result;
    private Integer numberOfErrors;
    private List<String> messages;
    private List<Vms031VoErrorUpload> errorResult;
    private String sheetName;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Integer getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(Integer numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<Vms031VoErrorUpload> getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(List<Vms031VoErrorUpload> errorResult) {
        this.errorResult = errorResult;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    
}
