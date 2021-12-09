package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/1
 */

/**
 * The front and rear ends are not separated
 * Page mapping (/admin/comment/all)
 */
@Controller
@RequestMapping("admin")
public class AdminCommentController {
    @GetMapping("/comment/all")
    public String comment() {
        return "/admin/admin_comment";
    }
}
