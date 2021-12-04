package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.util.List;

public interface DeskTypeRepository {
    int add(DeskTypeDTO deskTypeDTO);

    int update(DeskTypeDTO deskTypeDTO);

    int delete(int id);

    DeskTypeDTO finddesktypeById(int id);

    List<DeskTypeDTO > finddesktypeist();

}
