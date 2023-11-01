/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.exception;

/**
 *
 * @author Deri
 */
public class Vms031Exception extends RuntimeException{    private String errMsg;

    public Vms031Exception(String message) {
        super(message);
        this.errMsg = message;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
}
