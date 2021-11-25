package uk.ac.cf.nsa.team2.deskbookingapp.response;

import java.io.Serializable;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/20
 */
public class UploadFileResponse implements Serializable {
    private final String fileName;
    private final String fileDownloadUri;
    private final String fileType;
    private final long size;


    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    @Override
    public String toString() {
        return "{" +
                "fileName='" + fileName + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", fileType='" + fileType + '\'' +
                ", size=" + size +
                '}';
    }
}
