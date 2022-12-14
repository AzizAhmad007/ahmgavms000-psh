/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.util;

import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.jxf.util.DateUtil;
import id.co.ahm.jxf.vo.VoMessageWorkspace;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author reza.mr
 */
public class Vms022ValidationDateUtil {

    public void validateNotNull(DtoResponseWorkspace dto, String field, Object value, String message) {
        if (StringUtils.isBlank(value != null ? value.toString() : null)) {
            VoMessageWorkspace err = new VoMessageWorkspace();
            err.setF(field);
            err.setM(message);

            dto.addMessage(err);
        }
    }

    public boolean validateNotNull(Object value) {
        if (StringUtils.isBlank(value != null ? value.toString() : null)) {
            return false;
        }
        return true;
    }

    public void validateMaxLength(DtoResponseWorkspace dto, String field, Object value, String message, int maxChar) {
        if (StringUtils.isNotBlank(value != null ? value.toString() : null)) {
            if (value.toString().length() > maxChar) {
                VoMessageWorkspace err = new VoMessageWorkspace();
                err.setF(field);
                err.setM(message + " " + maxChar);

                dto.addMessage(err);
            }
        }
    }

    public void validateDate(DtoResponseWorkspace dto, Date voBeginDate, Date domainEndDate, String field, String message) {
        if (AhmStringUtil.hasValue(domainEndDate) && AhmStringUtil.hasValue(voBeginDate)) {
            if (voBeginDate.compareTo(domainEndDate) < 0) {
                VoMessageWorkspace err = new VoMessageWorkspace();
                err.setF(field);
                err.setM(message + " " + DateUtil.dateToString(domainEndDate, "dd-MMM-yyyy"));

                dto.addMessage(err);
            } else if (voBeginDate.compareTo(domainEndDate) == 0) {
                VoMessageWorkspace err = new VoMessageWorkspace();
                err.setF(field);
                err.setM(message + " " + DateUtil.dateToString(domainEndDate, "dd-MMM-yyyy"));

                dto.addMessage(err);
            }
        }
    }

    public void validateStartAndEndDate(DtoResponseWorkspace dto, Date startDate, Date endDate, String field, String message) {
        if (AhmStringUtil.hasValue(endDate) && AhmStringUtil.hasValue(startDate)) {
            if (endDate.compareTo(startDate) < 0) {
                VoMessageWorkspace err = new VoMessageWorkspace();
                err.setF(field);
                err.setM(message);

                dto.addMessage(err);
            }
        }
    }

    public void validateDate(DtoResponseWorkspace dto, String sdate, String field, String message) {
        Date date = DateUtil.stringToDate(sdate, "dd-MMM-yyyy");
        if (!AhmStringUtil.hasValue(date)) {
            VoMessageWorkspace err = new VoMessageWorkspace();
            err.setF(field);
            err.setM(message);
            dto.addMessage(err);
        }
    }

    public void validateExistingData(DtoResponseWorkspace dto, Integer count, String data1, String data2, String field, String message) {
        if (!count.equals(0)) {
            VoMessageWorkspace err = new VoMessageWorkspace();
            err.setF(field);
            err.setM(message + " " + data1 + (AhmStringUtil.hasValue(data2) ? "," + data2 : ""));

            dto.addMessage(err);
        }
    }

    public boolean validateDateTgl(String sdate) {
        try {
            Date date = DateUtil.stringToDate(sdate, "dd-MMM-yyyy");
            if (!AhmStringUtil.hasValue(date)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkIsAfter(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int month1 = cal1.get(Calendar.MONTH) + 1;
        int year1 = cal1.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int month2 = cal2.get(Calendar.MONTH) + 1;
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 > year2) { // check year
            return true;
        } else if (year1 == year2) { // if year is equal
            if (month1 > month2) { // check month
                return true;
            } else if (month1 == month2) { // if month is equal
                if (day1 > day2) { // check day
                    return true;
                }
                else if (day1 == day2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkNotActive(Date date1, Date date2) {
        //date1 end date 2 begin
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int month1 = cal1.get(Calendar.MONTH) + 1;
        int year1 = cal1.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int year2 = cal2.get(Calendar.YEAR);

        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(new Date());
        int daynow = cal3.get(Calendar.DAY_OF_MONTH);
        int monthnow = cal3.get(Calendar.MONTH) + 1;
        int yearnow = cal3.get(Calendar.YEAR);

        if (year2 < yearnow || year2 == yearnow) {
            if (year1 > yearnow) { // check year
                return true;
            } else if (year1 == yearnow) { // if year is equal
                if (month1 > monthnow) { // check month
                    return true;
                } else if (month1 == monthnow) { // if month is equal
                    if (day1 > daynow) { // check day
                        return true;
                    }
                    if (day1 == daynow) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
