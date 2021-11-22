package uk.ac.cf.nsa.team2.deskbookingapp.exception;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/20
 */
public class FileException extends RuntimeException {
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
