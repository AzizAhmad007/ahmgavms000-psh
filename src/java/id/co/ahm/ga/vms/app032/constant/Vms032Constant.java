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
//    public static final StringBuilder LOV_PLANTS = new StringBuilder("SELECT DISTINCT VPLANTVAR2 "
//            + "FROM AHMMOSCD_MSTAGPLANTS "
//            + "ORDER BY VPLANTVAR2");
//     public static final StringBuilder LOV_PLANTS = new StringBuilder("SELECT DISTINCT VPLANTVAR2, VDESC "
//            + "FROM AHMMOSCD_MSTAGPLANTS "
//            + "ORDER BY VPLANTVAR2");
    public static final String GET_DATA = "SELECT VDECTYPE FROM AHMGAVMS_MSTDECLARS WHERE VDECTYPE = :VDECTYPE";
    
//    public static final String GET_NEWNID = "SELECT TO_CHAR(NVL(MAX(NID)+1,1)) HASIL FROM AHMGAVMS_MSTDECLARS";
}
