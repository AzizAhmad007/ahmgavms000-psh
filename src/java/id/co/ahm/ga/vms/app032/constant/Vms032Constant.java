/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app032.constant;

/**
 *
 * @author Hitoshi Mario Naga M
 */
public class Vms032Constant {
    public static final String moduleId = "AHMGAVMS";
    
    public static final String appId = "AHMGAVMS032";
    
    public static final StringBuilder LOV_STATUS_DECLARATION = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_STAT'");
    public static final StringBuilder LOV_TYPE_DECLARATION = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_DECLR'");
    public static final StringBuilder LOV_DOC_TYPE_DECLARATION = new StringBuilder("SELECT VITEMCODE, VITEMNAME "
            + "FROM AHMMOERP_DTLSETTINGS "
            + "WHERE RSET_VID = 'VMS_DOCTYP'");
    public static final String PATH_SERVER = "/data/vms2023/";
}
