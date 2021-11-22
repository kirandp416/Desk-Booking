package uk.ac.cf.nsa.team2.deskbookingapp.entity;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/20
 */
//@Component
//@ConfigurationProperties(prefix = "file")
public class File {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
