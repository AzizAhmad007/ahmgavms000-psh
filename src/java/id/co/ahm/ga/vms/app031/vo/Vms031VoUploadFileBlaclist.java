/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.vo;

import id.co.ahm.ga.vms.app031.constant.Vms031Constant;
import id.co.ahm.ga.vms.app031.util.validator.Vms031FieldProperty;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031VoUploadFileBlaclist {
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_NRP, length = 4, nullable = false)
    private String NRP;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_NAMA, length = 8, nullable = false)
    private String NAMA;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_NIK, length = 4, nullable = false)
    private String NIK;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_JENIS_KELAMIN, length = 8, nullable = false)
    private String JENIS_KELAMIN;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_ALAMAT_KTP, length = 4, nullable = false)
    private String ALAMAT_KTP;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_ALAMAT_DOMISILI, length = 8, nullable = false)
    private String ALAMAT_DOMISILI;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_TIPE_KARYAWAN_BLACKLIST, length = 4, nullable = false)
    private String TIPE_KARYAWAN_BLACKLIST;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_NAMA_PERUSAHAAN, length = 8, nullable = false)
    private String NAMA_PERUSAHAAN;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_ALASAN_BLACKLIST, length = 4, nullable = false)
    private String ALASAN_BLACKLIST;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_TYPE_FOTO, length = 8, nullable = false)
    private String TYPE_FOTO;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_NAMA_FOTO, length = 4, nullable = false)
    private String NAMA_FOTO;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_EXTENSION_FOTO, length = 8, nullable = false)
    private String EXTENSION_FOTO;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_PATH_FOTO, length = 4, nullable = false)
    private String PATH_FOTO;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_TGL_START_EFFECTIVE, length = 8, nullable = false)
    private String TGL_START_EFFECTIVE;
    
    @Vms031FieldProperty(nameAlias = Vms031Constant.COLUMN_TGL_END_EFFECTIVE, length = 4, nullable = false)
    private String TGL_END_EFFECTIVE;
   
    
    private MultipartFile file;
    private List<Vms031VoErrorUpload> errorList;
    private boolean isUpdateable;
    private String uploadType;

    public List<Vms031VoErrorUpload> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Vms031VoErrorUpload> errorList) {
        this.errorList = errorList;
    }

    public String getNRP() {
        return NRP;
    }

    public void setNRP(String NRP) {
        this.NRP = NRP;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getJENIS_KELAMIN() {
        return JENIS_KELAMIN;
    }

    public void setJENIS_KELAMIN(String JENIS_KELAMIN) {
        this.JENIS_KELAMIN = JENIS_KELAMIN;
    }

    public String getALAMAT_KTP() {
        return ALAMAT_KTP;
    }

    public void setALAMAT_KTP(String ALAMAT_KTP) {
        this.ALAMAT_KTP = ALAMAT_KTP;
    }

    public String getALAMAT_DOMISILI() {
        return ALAMAT_DOMISILI;
    }

    public void setALAMAT_DOMISILI(String ALAMAT_DOMISILI) {
        this.ALAMAT_DOMISILI = ALAMAT_DOMISILI;
    }

    public String getTIPE_KARYAWAN_BLACKLIST() {
        return TIPE_KARYAWAN_BLACKLIST;
    }

    public void setTIPE_KARYAWAN_BLACKLIST(String TIPE_KARYAWAN_BLACKLIST) {
        this.TIPE_KARYAWAN_BLACKLIST = TIPE_KARYAWAN_BLACKLIST;
    }

    public String getNAMA_PERUSAHAAN() {
        return NAMA_PERUSAHAAN;
    }

    public void setNAMA_PERUSAHAAN(String NAMA_PERUSAHAAN) {
        this.NAMA_PERUSAHAAN = NAMA_PERUSAHAAN;
    }

    public String getALASAN_BLACKLIST() {
        return ALASAN_BLACKLIST;
    }

    public void setALASAN_BLACKLIST(String ALASAN_BLACKLIST) {
        this.ALASAN_BLACKLIST = ALASAN_BLACKLIST;
    }

    public String getTYPE_FOTO() {
        return TYPE_FOTO;
    }

    public void setTYPE_FOTO(String TYPE_FOTO) {
        this.TYPE_FOTO = TYPE_FOTO;
    }

    public String getNAMA_FOTO() {
        return NAMA_FOTO;
    }

    public void setNAMA_FOTO(String NAMA_FOTO) {
        this.NAMA_FOTO = NAMA_FOTO;
    }

    public String getEXTENSION_FOTO() {
        return EXTENSION_FOTO;
    }

    public void setEXTENSION_FOTO(String EXTENSION_FOTO) {
        this.EXTENSION_FOTO = EXTENSION_FOTO;
    }

    public String getPATH_FOTO() {
        return PATH_FOTO;
    }

    public void setPATH_FOTO(String PATH_FOTO) {
        this.PATH_FOTO = PATH_FOTO;
    }

    public String getTGL_START_EFFECTIVE() {
        return TGL_START_EFFECTIVE;
    }

    public void setTGL_START_EFFECTIVE(String TGL_START_EFFECTIVE) {
        this.TGL_START_EFFECTIVE = TGL_START_EFFECTIVE;
    }

    public String getTGL_END_EFFECTIVE() {
        return TGL_END_EFFECTIVE;
    }

    public void setTGL_END_EFFECTIVE(String TGL_END_EFFECTIVE) {
        this.TGL_END_EFFECTIVE = TGL_END_EFFECTIVE;
    }
    


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public boolean isIsUpdateable() {
        return isUpdateable;
    }

    public void setIsUpdateable(boolean isUpdateable) {
        this.isUpdateable = isUpdateable;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }
    
    
}
