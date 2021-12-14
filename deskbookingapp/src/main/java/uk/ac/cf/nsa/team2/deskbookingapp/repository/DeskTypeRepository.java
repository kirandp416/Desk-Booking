package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.util.List;
/**An interface will hold abstract versions of all our methods
 * You want to execute on the Manage Desk Type object. This allows us to abstract
 * Implement database-side actions into classes like this
 * We can switch to a different database by switching that class.*/
public interface DeskTypeRepository {
    int add(DeskTypeDTO deskTypeDTO);

    int update(DeskTypeDTO deskTypeDTO);

    int delete(int id);

    DeskTypeDTO finddesktypeById(int id);

    List<DeskTypeDTO > finddesktypeist();

}
