package uk.ac.cf.nsa.team2.deskbookingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.DeskTypeRepository;

import java.util.List;
@Service
public class DeskServiceImpl implements DesktypeService{
    @Autowired
    private DeskTypeRepository deskTypeRepository;

    /**add desk type*/
    @Override
    public int add(DeskTypeDTO deskTypeDTO) {
        return deskTypeRepository.add(deskTypeDTO);
    }
    /**update desk type*/
    @Override
    public int update(DeskTypeDTO deskTypeDTO) {
        return deskTypeRepository.update(deskTypeDTO);
    }
    /**delete desk type*/
    @Override
    public int delete(int deskTypeId) {
        return deskTypeRepository.delete(deskTypeId);
    }
    /**find one desktype by id*/
    @Override
    public DeskTypeDTO findDeskTypeById(int deskTypeId) {
        return deskTypeRepository.finddesktypeById(deskTypeId);
    }
    /**find desk type list*/
    @Override
    public List<DeskTypeDTO> findDeskTypeList() {
        return deskTypeRepository.finddesktypeist();
    }
}
