//package uk.ac.cf.nsa.team2.deskbookingapp;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import uk.ac.cf.nsa.team2.deskbookingapp.utils.MysqlCommonHandler;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.HashMap;
//@SpringBootTest
//public class test {
//    public static void main(String[] args) throws Exception {
//        loginUser("asd","12345");
//    }
//
//    @Test
//    public static boolean loginUser(String username,String password) throws Exception {
//        boolean res = false;
//        Connection conn = MysqlCommonHandler.getConn("jdbc:mysql://localhost:3306/deskbooking?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","123456","com.mysql.cj.jdbc.Driver");
//        PreparedStatement ps = conn.prepareStatement("select * from users where username= ? AND  pwd=?");
//        ResultSet rs = ps.executeQuery();
//        HashMap<String,String> map = new HashMap<>();
//        while(rs.next()){
//            map.put(rs.getString(1),rs.getString(2));
//        }
//        if(map.isEmpty()) return false;
//        return res;
//    }
//}
