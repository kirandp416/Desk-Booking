package uk.ac.cf.nsa.team2.deskbookingapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.BookingCommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.CommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.ICommentRepository;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.R;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/29
 */

/**
 * A REST API controller for comment.
 */

@RestController
@RequestMapping("comment")
public class CommentRestController {
    @Autowired
    private ICommentRepository commentRepository;

    @GetMapping(value = "/fetchBooking")
    public R<List<BookingCommentDTO>> getOwnBooking(String username, Integer current, Integer size) {
        if (current == null) {
            current = 0;
        }
        if (size == null) {
            size = 10;
        }
        return R.success(commentRepository.getOwnBooking(username, current, size));
    }

    @GetMapping(value = "/fetchAll")
    public R<List<CommentDTO>> getAllComment() {
        return R.success(commentRepository.findAll());
    }

    @PostMapping(value = "/add")
    public R<Integer> addComment(@RequestBody CommentDTO commentDTO) {
        return R.success(commentRepository.addComment(commentDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public R<Integer> deleteComment(@PathVariable Long id) {
        return R.success(commentRepository.removeById(id));
    }
}
