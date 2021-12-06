package uk.ac.cf.nsa.team2.deskbookingapp.repository;

import uk.ac.cf.nsa.team2.deskbookingapp.dto.CommentDTO;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/1
 */
public interface ICommentRepository {

    <T> T getOwnBooking(String username, int page, int num);

    List<CommentDTO> findAll();

    String addComment(CommentDTO commentDTO);

    String removeById(Long id);

}
