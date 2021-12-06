package uk.ac.cf.nsa.team2.deskbookingapp.service;

import org.springframework.stereotype.Service;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.util.List;

public interface DesktypeService {
    int add(DeskTypeDTO deskTypeDTO);

    int update(DeskTypeDTO deskTypeDTO);

    int delete(int id);

    DeskTypeDTO findUserById(int id);

    List<DeskTypeDTO> findUserList();
}
