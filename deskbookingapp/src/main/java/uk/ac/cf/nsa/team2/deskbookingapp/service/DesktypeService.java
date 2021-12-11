package uk.ac.cf.nsa.team2.deskbookingapp.service;


import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.util.List;

public interface DesktypeService {
    int add(DeskTypeDTO deskTypeDTO);

    int update(DeskTypeDTO deskTypeDTO);

    int delete(int id);

    DeskTypeDTO findDeskTypeById(int id);

    List<DeskTypeDTO> findDeskTypeList();
}
