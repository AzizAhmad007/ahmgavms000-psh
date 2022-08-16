/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.constant;

/**
 *
 * @author reza.mr
 */
public enum Vms022Status {
    ADD("ADD"),
    EDIT("EDIT"),
    DELETE("DELETE"),
    NOT_AUTHORIZED("You are not authorized to perform this operation."),
    NO_DATA_FOUND("No data found.");

    private String message;

    private Vms022Status() {
    }

    private Vms022Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
