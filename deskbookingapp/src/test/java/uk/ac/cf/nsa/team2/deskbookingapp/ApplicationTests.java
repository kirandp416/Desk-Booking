package uk.ac.cf.nsa.team2.deskbookingapp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import uk.ac.cf.nsa.team2.deskbookingapp.dto.CommentDTO;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.impl.CommentRepositoryImpl;
import uk.ac.cf.nsa.team2.deskbookingapp.rest.CommentRestController;

import java.util.List;
import java.util.Map;


@SpringBootTest
class ApplicationTests {

    @Autowired
    private CommentRestController commentRestController;

    @Autowired
    private CommentRepositoryImpl commentRepository;

    @Test
    void contextLoads() {
        List<CommentDTO> all = commentRepository.findAll();
//        Assert.
//        Assert.isTrue(all.size() >= 0, "not exp");
        System.out.println("commentRestController = " + all);
    }

}
