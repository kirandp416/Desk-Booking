package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/20
 */
public class UploadFileDTO implements Serializable {
    @JsonProperty("fileName")
    private final String fileName;
    @JsonProperty("fileDownloadUri")
    private final String fileDownloadUri;
    @JsonProperty("dbSave")
    private final String dbSave;
    @JsonProperty("fileType")
    private final String fileType;
    @JsonProperty("size")
    private final long size;


    public UploadFileDTO(String fileName, String fileDownloadUri, String dbSave, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.dbSave = dbSave;
        this.fileType = fileType;
        this.size = size;
    }
}
