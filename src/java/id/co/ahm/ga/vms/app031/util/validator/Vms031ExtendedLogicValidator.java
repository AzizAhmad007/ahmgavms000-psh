/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.validator;

import id.co.ahm.ga.vms.app031.vo.Vms031VoValidatorParameter;
import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public abstract class Vms031ExtendedLogicValidator {
    protected MessageSource messageSource;
    public static final Locale localFormat = Locale.getDefault();

    public abstract boolean validate(Vms031VoValidatorParameter param);
}
