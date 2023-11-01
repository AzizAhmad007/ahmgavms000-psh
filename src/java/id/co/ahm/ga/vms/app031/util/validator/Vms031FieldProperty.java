/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.validator;

import id.co.ahm.ga.vms.app031.constant.Vms031Constant;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Vms031FieldProperty {
    public String name() default "";

    public String nameAlias() default "";

    public String valueCategory() default "";

    public String type() default Vms031Constant.STRING;

    public String format() default "";

    public String mappingField() default "";

    public boolean nullable() default true;

    public int length() default 255;

    public int precision() default 0;

    public int scale() default 0;

    public long min() default Long.MIN_VALUE;

    public long max() default Long.MAX_VALUE;

    public boolean isValidateEffectiveDate() default false;
}
