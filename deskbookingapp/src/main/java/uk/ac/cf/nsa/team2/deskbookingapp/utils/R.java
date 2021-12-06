package uk.ac.cf.nsa.team2.deskbookingapp.utils;


/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/1
 */
public class R<T> {
    private final static String SUCCESS_CODE = "000000";
    private final static String SUCCESS_MSG = "success";

    private String code;
    private String message;
    private T records;

    public R(String code, String message, T records) {
        this.code = code;
        this.message = message;
        this.records = records;
    }

    public static R success(Object data) {
        return new R(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public R fail(String code, String message, Object records) {
        return new R(code, message, records);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRecords() {
        return records;
    }

    public void setRecords(T records) {
        this.records = records;
    }
}
