package id.co.ahm.ga.vms.app022.util;

public class Vms022IntegerUtil {
    public static Integer TryParse(String s) {
        try {
            if(s != null){
                s = s.trim();
            }

            if("".equals(s) || s == null)
                s = "0";
            
            s = s.replace(",", "");
            
            return Integer.parseInt(s);
        } 
        catch (NumberFormatException ex) {
            return null;
        }
    }
}
