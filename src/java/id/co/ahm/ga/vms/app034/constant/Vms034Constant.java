/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app034.constant;

/**
 *
 * @author kahfi
 */
public class Vms034Constant {

    public static final String moduleId = "AHMGAVMS";
    public static final String appId = "AHMGAVMS034";
    
    public static final StringBuilder LOV_PLANTS = new StringBuilder("SELECT DISTINCT VPLANTVAR2, VDESC "
            + "FROM AHMMOSCD_MSTAGPLANTS "
            + "ORDER BY VPLANTVAR2");
    
    public static final StringBuilder LOV_IDCARD_ALL = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_IDTYPE' ");
    
    public static final StringBuilder LOV_STATUS = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_STSCHCK' "
            + "AND VITEMCODE NOT IN ('N') ");
}
