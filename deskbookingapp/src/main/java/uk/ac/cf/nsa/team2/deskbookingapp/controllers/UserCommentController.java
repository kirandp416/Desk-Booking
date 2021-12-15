package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/3
 */

/**
 * The front and rear ends are not separated
 * Page mapping (/user//comment)
 */
@Controller
@RequestMapping("/user")
public class UserCommentController {
    @GetMapping("/comment")
    public String comment() {
        return "/comment";
    }
}
