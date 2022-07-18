/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app021.exception;

import java.util.List;

/**
 *
 * @author oky
 */
public class Vms021UploadException extends RuntimeException{
    
    private String errMsg;
    private List<String> list;

    public Vms021UploadException(String message) {
        super(message);
        this.errMsg = message;
    }

    public Vms021UploadException(String message, List<String> list) {
        super(message);
        this.errMsg = message;
        this.list = list;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
