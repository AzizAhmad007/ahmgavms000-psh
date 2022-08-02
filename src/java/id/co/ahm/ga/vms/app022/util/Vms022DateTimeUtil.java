/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author reza.mr
 */
public class Vms022DateTimeUtil {
    public static Date setDate(Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);
        
        return calendar.getTime();
    }
    
    public static String dateToString(Date d) {
        if(d == null)
            return "";
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
        
        return formatter.format(d);
    }
    
    public static String dateToString(String format, Date d) {
        if(d == null)
            return "";
        
        SimpleDateFormat formatter = new SimpleDateFormat(format);  
        
        return formatter.format(d);
    }
    
    public static java.sql.Date dateUtilToDateSql(Date d) {
        if(d == null)
            return null;
        
        java.sql.Date dateSql = new java.sql.Date(d.getTime());
        
        return dateSql;

    }
    
    public static String timeToString(String format, Date d) {
        if(d == null)
            return "";
        
        SimpleDateFormat formatter = new SimpleDateFormat(format);  
        
        return formatter.format(d).toUpperCase(); 
    }
    
    public static Date stringToDate(String s){
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = dateFormat.parse(s);
            
            return date;
        }
        catch ( Exception e){
            return null;
        }
    }
    
    public static Date stringToDate(String format, String s){
        try{
            DateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse(s);
            
            return date;
        }
        catch ( Exception e){
            return null;
        }
    }
    
    public static Integer getYear(Date d){
        LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return localDate.getYear();
    }
    
    public static Integer getMonth(Date d){
        LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return localDate.getMonthValue();
    }
    
    public static Integer getDay(Date d){
        LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int day = localDate.getDayOfMonth();

        return day;
    }
    
    public static LocalDateTime getLocalDateTime(Date d){
        LocalDateTime localDateTime = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return localDateTime;
    }
}
