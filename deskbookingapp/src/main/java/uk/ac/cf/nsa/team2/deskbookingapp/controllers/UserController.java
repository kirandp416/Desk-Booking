package uk.ac.cf.nsa.team2.deskbookingapp.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.cf.nsa.team2.deskbookingapp.repository.UserRepository;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.MysqlCommonHandler;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.StringCommonHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;


@Controller
@RequestMapping(path = "/login")
public class UserController {

private UserRepository userRepository;

@RequestMapping(path = "/tologin")
public boolean loginUser(String username) throws Exception {
    System.out.println("start..");
    boolean res = false;
    //if(StringCommonHandler.isEmpty(username) || StringCommonHandler.isEmpty(password)) return false;
    Connection conn = MysqlCommonHandler.getConn("jdbc:mysql://localhost:3306/deskbooking?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","123456","com.mysql.cj.jdbc.Driver");
    PreparedStatement ps = conn.prepareStatement("select * from users where username= ? ");
    ResultSet rs = ps.executeQuery();
    HashMap<String,String> map = new HashMap<>();
    while(rs.next()){
        map.put(rs.getString(1),rs.getString(2));
    }
    if(map.isEmpty()) return false;
    return res;
}


}
