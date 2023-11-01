/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.exception;

/**
 *
 * @author pradana
 */
public class Vms031DataNotFoundException extends RuntimeException {

    public Vms031DataNotFoundException() {
        super("data not found");
    }

    public Vms031DataNotFoundException(String message) {
        super(message);
    }
}
