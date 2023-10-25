/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app028.constant;

/**
 *
 * @author kahfi
 */
public class Vms028Constant {
    
    public static final String TEMPLATE_PATH_UPLOAD = "/data/AHMGA/VMS/Registrasi/Template_Upload_PIC.xlsx";
    public static final String TEMPLATE_PATH_UPLOAD_EXTERNAL = "/data/AHMGA/VMS/Registrasi/Template_Upload_Mitra.xlsx";

    public static final String moduleId = "AHMGAVMS";
    public static final String appId = "AHMGAVMS028";
    
    public static final StringBuilder LOV_PLANTS = new StringBuilder("SELECT DISTINCT VPLANTVAR2, VDESC "
            + "FROM AHMMOSCD_MSTAGPLANTS "
            + "ORDER BY VPLANTVAR2");
    
    public static final StringBuilder LOV_PARTICIPANT = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_TYPE_PRTCP'");
    
    public static final StringBuilder LOV_RESULT = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_RSL_SI'");
    
    public static final StringBuilder LOV_TYPE_SI = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_SI_TYP'");
}
