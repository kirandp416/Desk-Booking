package uk.ac.cf.nsa.team2.deskbookingapp.utils;

public class StringCommonHandler {
    public static boolean isEmpty(String str){
        boolean res = false;
        if(str.isEmpty() || str.isBlank()){
            return true;
        }
        return res;
    }
}
