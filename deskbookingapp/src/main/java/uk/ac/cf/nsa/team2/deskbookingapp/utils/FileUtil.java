package uk.ac.cf.nsa.team2.deskbookingapp.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.cf.nsa.team2.deskbookingapp.exception.FileException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/23
 */
public class FileUtil {

    private final static String BASE_PATH = "D:/loadTest/";

    public static String upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        Path path = Paths.get(BASE_PATH, filename);
        try {
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            int count = 0;
            Path p = Paths.get(BASE_PATH, filename);
            int lastIndexOf = filename.lastIndexOf(".");
            String preName = filename.substring(0, lastIndexOf);
            String suffix = filename.substring(lastIndexOf);
            for (int i = 0; ; i++) {
                if (Files.notExists(p)) {
                    break;
                }
                filename = preName + "(" + i + ")" + suffix;
                p = Paths.get(BASE_PATH, filename);
//                String tempName = preName + lastIndexOf + "(" + count + ")" + suffix;
                System.out.println("filename = " + filename);
            }

            Files.createFile(p);
            file.transferTo(p);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
        return filename;
    }

    public static Resource getResource(String filename) {
        Path path = Paths.get(BASE_PATH.concat(filename));
        try {
            UrlResource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            }
            throw new FileException("file not found!");
        } catch (MalformedURLException e) {
            throw new FileException(e.getMessage());
        }
    }
}
