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

    public static final String STATUS_WAITING_FOR_PIC = "Waiting for Approval PIC";
    public static final String STATUS_WAITING_FOR_SEC = "Waiting for Approval Security";
    
    public static final String workflowId = "WF_AHMHRNTM_ID_Card_Replacement";

    public static final String moduleId = "AHMGAVMS";
    public static final String appId = "AHMGAVMS022";
    public static final String descWorkflow = "Workflow for ID Card Replacement";

    public static final String SQL_GET_PLANTS_BY_USERID
            = "";

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

    public static final String SQL_MONITORING
            = "select distinct "
            + "                     A.VOTSID as Outsource_ID, "
            + "                     A.VNAME as Outsource_Name, "
            + "                     A.VPERSID as NIK, "
            + "                     A.VOTSTYPE as Outsource_Type, "
            + "                     A.VCOMPANY as Outsource_Company, "
            + "                     A.VOTSSTTS as Outsource_status, "
            + "                     C.VPLANT as Plant, "
            + "                     A.VVACSTTS as Covid19VaccineStatus, "
            + "                     A.DBGNEFFDT as Periode_Begin, "
            + "                     A.DENDEFFDT as Periode_End, "
            + "                     A.NAHMCARDORI as PassNumberCard, "
            + "                     A.DPASSEXP as PassCardExpDate, "
            + "                     A.VMODI, "
            + "                     A.DMODI, "
            + "                     A.VEMPPHONE, "
            + "                     A.VJOBDTL, "
            + "                     A.VNOTE, "
            + "                     A.VVACTYPE, "
            + "                     A.DLASTVAC, "
            + "                     A.VVACDTL, "
            + "                     A.VNTVS, "
            + "                     A.ROTSEMPSHS "
            + "                  from AHMHRNTM_HDROTSEMPS A, "
            + "                 AHMHRNTM_MSTPICOTS B, AHMHRNTM_DTLOTSREGS C ";

    public static final String SQL_CONFIRM_ID
            = "select distinct  "
            + "    votsid  "
            + " from  "
            + "    AHMHRNTM_HDROTSEMPS  "
            + " where  "
            + "    votsid = :VOTSID "
            + " and"
            + "    UPPER(Votsstts) = UPPER(:OUTSTAT) ";

    public static final String SQL_GET_NAME = "select distinct  "
            + "    VNAME  "
            + " from  "
            + "    AHMHRNTM_HDROTSEMPS  "
            + " where  "
            + "    votsid = :VOTSID ";

    public static final String SQL_GET_AREA_TYPE
            = "SELECT DISTINCT MPO.VNRP, MPO.VAREA, MPO.VOTSTYPE, FGD.NAME, DP.VPGBLNM, FGD.VHANDPHONE  "
            + "FROM AHMHRNTM_MSTPICOTS MPO, AHMHRNTM_DTLPRMGBLS DP,  FMHRD_GENERAL_DATAS FGD, AHMMOERP_MSTKARYAWANS@AHMPS MKA  "
            + "WHERE  "
            + "    SYSDATE BETWEEN MPO.DBGNEFFDT AND MPO.DENDEFFDT  "
            + "    AND DP.VPGBLCD = MPO.VAREA  "
            + "    AND DP.VPGBLCD LIKE 'PG10%'  "
            + "    AND MPO.VNRP = FGD.NRP  "
            + "    AND MPO.VNRP = MKA.IIDNRP  "
            + "    AND FGD.VEND_VND_CODE = 'AHM'  "
            + "    AND MPO.VNRP = :NRP "
            + "    AND TRUNC(SYSDATE) BETWEEN TRUNC(DP.DBGNEFFDT) AND TRUNC(DP.DENDEFFDT) ";

    public static final String SQL_VALIDATE_WORK_ORDER
            = "select vwrkorderno from AHMHRNTM_TXNIDREPS  "
            + "where  "
            + "vnrp = :NRP  "
            + "and rownum = 1 ";
}
