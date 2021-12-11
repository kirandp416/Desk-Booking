package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.DeskTypeDTO;

import java.util.List;

@Repository
public class DeskTypeRepositoryJDBC implements DeskTypeRepository {

    /**
     * inject jdbc
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int add(DeskTypeDTO deskTypeDTO) {
        return jdbcTemplate.update("insert into desk_type(desk_type_name,desk_type_introduce)  values(?,?)", deskTypeDTO.getDeskTypeName(), deskTypeDTO.getDeskTypeIntroduce());
    }

    @Override
    public int update(DeskTypeDTO deskTypeDTO) {
        return jdbcTemplate.update("UPDATE  desk_type SET desk_type_name =?,desk_type_introduce=? WHERE desk_type_id= ?", deskTypeDTO.getDeskTypeName(),  deskTypeDTO.getDeskTypeIntroduce(), deskTypeDTO.getDeskTypeId());
    }

    @Override
    public int delete(int deskTypeId) {
        return jdbcTemplate.update("DELETE FROM desk_type  where desk_type_id=?", deskTypeId);
    }

    @Override
    public DeskTypeDTO finddesktypeById(int deskTypeId) {
        List<DeskTypeDTO> list = jdbcTemplate.query("select * from desk_type where desk_type_id = ?", new Object[]{deskTypeId}, new BeanPropertyRowMapper(DeskTypeDTO.class));
        if (list != null && list.size() > 0) {
            DeskTypeDTO deskTypeDTO = list.get(0);
            return deskTypeDTO;
        } else {
            return null;


        }
    }

    @Override
    public List<DeskTypeDTO> finddesktypeist() {
        List<DeskTypeDTO> list = jdbcTemplate.query("select * from desk_type", new Object[]{}, new BeanPropertyRowMapper(DeskTypeDTO.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }


}
