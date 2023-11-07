/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app030.constant;

/**
 *
 * @author Nurvan Afandi
 */
public class Vms030Constant {
    
    public static final String TEMPLATE_PATH_UPLOAD = "/data/AHMGA/VMS/Registrasi/...";
   
    public static final String TEMPLATE_PATH_UPLOAD_EXTERNAL = "/data/AHMGA/VMS/Registrasi/...";
    
    public static final String moduleId = "AHMGAVMS";
    
    public static final String appId = "AHMGAVMS030";
    
    public static final StringBuilder LOV_PLANTS = new StringBuilder("SELECT VPLANTVAR2, VDESC "
            + "FROM AHMMOSCD_MSTAGPLANTS "
            + "ORDER BY VPLANTVAR2");
    
    public static final StringBuilder LOV_VISITORTYPE = new StringBuilder(""
            + "SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_TYPE_PRTCP' "
            + "AND VITEMCODE IN ('KTR','MTO','KRW','TPS')");
    
    public static final StringBuilder LOV_STATUS = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_STAT' "
            + "AND VITEMCODE IN ('Y','N','D')");
    
    public static final StringBuilder LOV_DOCTYPE = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_TYPDOC_SI' "
            + "AND VITEMCODE IN ('IKP','MEMO')");
    
    public static final StringBuilder GET_LINK = new StringBuilder("SELECT VITEMDESC "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_INV_LINK' "
            + "AND VITEMCODE = 'L'");
    
    public static final String FROM = "no-reply@astra-honda.com";
    
}
