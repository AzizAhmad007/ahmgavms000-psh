/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app022.constant;

/**
 *
 * @author reza.mr
 */
public class Vms022Constant {
    
    public static final String TEMPLATE_PATH_UPLOAD = "/data/AHMGA/VMS/Registrasi/Template_Upload_PIC.xlsx";
    public static final String TEMPLATE_PATH_UPLOAD_EXTERNAL = "/data/AHMGA/VMS/Registrasi/Template_Upload_Mitra.xlsx";
    //public static final String TEMPLATE_PATH_UPLOAD = "D:\\Download\\template\\Template_Upload_PIC.xlsx"; /*Path lokal*/
    //public static final String TEMPLATE_PATH_UPLOAD_EXTERNAL = "D:\\Download\\template\\Template_Upload_Mitra.xlsx"; /*Path lokal*/
    
    public static final String SQL_GET_PLANTS_BY_USERID =
            "";
    
    public static final String SQL_GET_USER_ROLES_PST 
            = "SELECT ROL.VROLENAME "
            + "FROM AHMIPUAM_MSTROLEACCS RAC, AHMIPUAM_MSTMENUS MEN, AHMIPUAM_MSTROLES ROL "
            + "WHERE RAC.VENABLEFLAG = 'Y' "
            + " AND RAC.IMENUINTID = MEN.IINTERNALID "
            + " AND MEN.VMENUCODE = 'AHMGAVMS022' "
            + " AND MEN.VENABLEFLAG = 'Y' "
            + " AND RAC.IROLEINTID = ROL.IINTERNALID "
            + " AND ROL.VENABLEFLAG = 'Y' "
            + " AND RAC.IROLEINTID IN (SELECT IROLEINTID FROM AHMIPUAM_MSTUSERRLS WHERE VUSERNAME = :USERNAME) ";
    
    public static final String SQL_MONITORING =
            "select distinct " +
"                     A.VOTSID as Outsource_ID, " +
"                     A.VNAME as Outsource_Name, " +
"                     A.VPERSID as NIK, " +
"                     A.VOTSTYPE as Outsource_Type, " +
"                     A.VCOMPANY as Outsource_Company, " +
"                     A.VOTSSTTS as Outsource_status, " +
"                     C.VPLANT as Plant, " +
"                     A.VVACSTTS as Covid19VaccineStatus, " +
"                     A.DBGNEFFDT as Periode_Begin, " +
"                     A.DENDEFFDT as Periode_End, " +
"                     A.NAHMCARDORI as PassNumberCard, " +
"                     A.DPASSEXP as PassCardExpDate, " +
"                     A.VMODI, " +
"                     A.DMODI, " +
"                     A.VEMPPHONE, " +
"                     A.VJOBDTL, " +
"                     A.VNOTE, " +
"                     A.VVACTYPE, " +
"                     A.DLASTVAC, " +
"                     A.VVACDTL, " +
"                     A.VNTVS, " +
"                     A.ROTSEMPSHS " +
"                  from AHMHRNTM_HDROTSEMPS A, " +
"                 AHMHRNTM_MSTPICOTS B, AHMHRNTM_DTLOTSREGS C ";
    
    public static final String SQL_MONITORING_PARAM = 
            "";
}
