/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app026.constant;

/**
 *
 * @author kahfi
 */

public class Vms026Constant {
    public static final String TEMPLATE_PATH_UPLOAD = "/data/AHMGA/VMS/Registrasi/Template_Upload_PIC.xlsx";
    public static final String TEMPLATE_PATH_UPLOAD_EXTERNAL = "/data/AHMGA/VMS/Registrasi/Template_Upload_Mitra.xlsx";

    public static final String moduleId = "AHMGAVMS";
    public static final String appId = "AHMGAVMS026";
    
    public static final StringBuilder LOV_PLANTS = new StringBuilder("SELECT DISTINCT VPLANTVAR2, VDESC "
            + "FROM AHMMOSCD_MSTAGPLANTS "
            + "ORDER BY VPLANTVAR2");
    
    public static final StringBuilder LOV_VISITORTYPE = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_VST2'");
    
    public static final StringBuilder GET_MASTER_NO = new StringBuilder("SELECT VITEMNAME, VITEMDESC "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_INV_NO' "
            + "AND VITEMCODE = 'INV'");
    
    public static final StringBuilder GET_LINK = new StringBuilder("SELECT VITEMDESC "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_INV_LINK' "
            + "AND VITEMCODE = 'L'");
    
    public static final StringBuilder LOV_STATUS = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_STAT' "
            + "AND VITEMCODE NOT IN('N')");
}
