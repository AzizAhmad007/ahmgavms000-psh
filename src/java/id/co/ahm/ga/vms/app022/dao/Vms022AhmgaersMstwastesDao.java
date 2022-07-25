package id.co.ahm.ga.vms.app022.dao;

import id.co.ahm.ga.vms.app000.model.AhmgaersMstwastes;
import id.co.ahm.ga.vms.app022.vo.Vms022VoWaste;
import id.co.ahm.jxf.dao.GenericDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import java.util.List;

/**
 *
 * @author developer
 */
public interface Vms022AhmgaersMstwastesDao extends GenericDao<AhmgaersMstwastes, String> {

    public Integer getDataRunningNumber(String id, String reset);

    List<Vms022VoWaste> search(DtoParamPaging dtoParamPaging);

    Vms022VoWaste getDataById(String id);

    int count(DtoParamPaging dtoParamPaging);

    List<Vms022VoWaste> searchDetail(DtoParamPaging dtoParamPaging);

    int countDetail(DtoParamPaging dtoParamPaging);

    public String getWasteIdNo();

    public List<Vms022VoWaste> searchForPrint(DtoParamPaging dtoParamPaging);

    public int countPrint(DtoParamPaging dtoParamPaging);

}
