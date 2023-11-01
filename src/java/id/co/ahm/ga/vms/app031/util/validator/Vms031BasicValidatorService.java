/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.validator;

import id.co.ahm.ga.vms.app031.exception.Vms031Exception;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
@Service(value = "vms031BasicValidatorService")
@Transactional
public class Vms031BasicValidatorService {
    @Autowired
    private MessageSource messageSource;

    public MessageSource getMessageSource() {
        return messageSource;
    }
    
    public Map<String, List<String>> validate(Object object, String[] validateProperties, String messageId) {
        Map<String, List<String>> messages = new HashMap<>();
        Class cl = object.getClass();

        StringBuilder message = new StringBuilder();
        for (String property : validateProperties) {
            message.setLength(0);
            try {
                Field field = cl.getDeclaredField(property);
                field.setAccessible(true);
                Annotation annotation = field.getDeclaredAnnotation(Vms031FieldProperty.class);
                if (annotation != null && annotation instanceof Vms031FieldProperty) {
                    Vms031FieldProperty column = (Vms031FieldProperty) annotation;
                    Object propertyValue = field.get(object);
                    String fieldName = "";
                    try {
                        fieldName = messageSource.getMessage(messageId + "." + property, null, Locale.getDefault());
                    } catch (NoSuchMessageException e) {
                        fieldName = property;
                    }

                    if (field.getType().isAssignableFrom(String.class)) {
                        String val = (String) propertyValue;
                        if (StringUtils.isEmpty(val) && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }

                        if (val != null && val.length() > column.length()) {
                            message.append(messageSource.getMessage("field.length", new Object[]{fieldName, column.length()}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }
                    }

                    if (field.getType().isAssignableFrom(Number.class)) {
                        Number val = (Number) propertyValue;

                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }

                        if (column.precision() > 0) {
                            int length = column.precision() - column.scale();

                            long l = Math.abs(val.longValue());
                            String s = Long.toString(l);

                            if (s.length() > length) {
                                message.append(messageSource.getMessage("field.numberformat", new Object[]{fieldName, column.precision() + "," + column.scale()}, Locale.getDefault()));
                                putMsg(property, messages, message);
                                continue;
                            }
                        }
                    }

                    if (field.getType().isAssignableFrom(BigDecimal.class)) {
                        BigDecimal val = (BigDecimal) propertyValue;
                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }

                        if (column.precision() > 0) {
                            int length = column.precision() - column.length();

                            long l = val.longValue();
                            String s = Long.toString(l);

                            if (s.length() > length) {
                                message.append(messageSource.getMessage("field.numberformat", new Object[]{fieldName, column.precision() + "," + column.scale()}, Locale.getDefault()));
                                putMsg(property, messages, message);
                                continue;
                            }
                        }
                    }

                    if (field.getType().isAssignableFrom(Date.class)) {
                        Date val = (Date) propertyValue;
                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }
                    }

                    if (field.getType().isAssignableFrom(Integer.class)) {
                        Integer val = (Integer) propertyValue;
                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }
                    }
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                putMsg(property, messages, "Property : " + property + " is not found in " + cl.getName());
                Logger.getLogger(Vms031FieldProperty.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return messages;
    }
    
    public Map<String, List<String>> validate(Object object, String[] validateProperties) {
        Map<String, List<String>> messages = new HashMap<>();
        Class cl = object.getClass();

        StringBuilder message = new StringBuilder();
        for (String property : validateProperties) {
            message.setLength(0);
            try {
                Field field = cl.getDeclaredField(property);
                field.setAccessible(true);
                Annotation annotation = field.getDeclaredAnnotation(Vms031FieldProperty.class);
                if (annotation != null && annotation instanceof Vms031FieldProperty) {
                    Vms031FieldProperty column = (Vms031FieldProperty) annotation;
                    Object propertyValue = field.get(object);
                    String fieldName = "";
                    try {
                        fieldName = messageSource.getMessage(column.name(), null, Locale.getDefault());
                    } catch (NoSuchMessageException e) {
                        fieldName = column.name();
                    }

                    if (field.getType().isAssignableFrom(String.class)) {
                        String val = (String) propertyValue;
                        if (StringUtils.isEmpty(val) && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }

                        if (StringUtils.trimToEmpty(val).length() > column.length()) {
                            message.append(messageSource.getMessage("field.length", new Object[]{fieldName, column.length()}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }
                    }

                    if (Number.class.isAssignableFrom(field.getType())) {
                        Number val = (Number) propertyValue;
                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }

                        if (column.precision() > 0) {
                            int length = column.precision() - column.scale();

                            long l = val.longValue();
                            String s = Long.toString(l);

                            if (s.length() > length) {
                                message.append(messageSource.getMessage("field.numberformat", new Object[]{fieldName, column.precision() + "," + column.scale()}, Locale.getDefault()));
                                putMsg(property, messages, message);
                                continue;
                            }
                        }
                    }

                    if (field.getType().isAssignableFrom(BigDecimal.class)) {
                        BigDecimal val = (BigDecimal) propertyValue;
                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }

                        if (column.precision() > 0) {
                            int length = column.precision() - column.length();

                            long l = val.longValue();
                            String s = Long.toString(l);

                            if (s.length() > length) {
                                message.append(messageSource.getMessage("field.numberformat", new Object[]{fieldName, column.precision() + "," + column.scale()}, Locale.getDefault()));
                                putMsg(property, messages, message);
                                continue;
                            }
                        }
                    }

                    if (field.getType().isAssignableFrom(Date.class)) {
                        Date val = (Date) propertyValue;
                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }
                    }

                    if (field.getType().isAssignableFrom(Integer.class)) {
                        Integer val = (Integer) propertyValue;
                        if (val == null && !column.nullable()) {
                            message.append(messageSource.getMessage("field.mandatory", new Object[]{fieldName}, Locale.getDefault()));
                            putMsg(property, messages, message);
                            continue;
                        }
                    }
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                putMsg(property, messages, "Property : " + property + " is not found in " + cl.getName());
                Logger.getLogger(Vms031FieldProperty.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return messages;
    }
    
    private void putMsg(String property, Map<String, List<String>> messages, StringBuilder message) {
        putMsg(property, messages, message.toString());
    }

    private void putMsg(String property, Map<String, List<String>> messages, String message) {
        if (messages.get(property) == null) {
            messages.put(property, new ArrayList<>());
        }
        messages.get(property).add(message);
    }

    public void validateUploadFile(MultipartFile file) {
        String message = "";
        if (file.isEmpty()) {
            message = "Tidak ada data yang diupload";
        } else {            
            if (!file.getOriginalFilename().endsWith("xlsx") && !file.getOriginalFilename().endsWith("xls")) {
                message = "Hanya file dengan ekstensi xlsx dan xls yang diperbolehkan";
            }
            if (file.getSize() > 4096000) {
                message = "File melebihi batas maksimal ukuran yang ditetapkan yaitu 4 MB";
            }
        }

        if (StringUtils.isNotEmpty(message)) {
            throw new Vms031Exception(message);
        }
    }
}
