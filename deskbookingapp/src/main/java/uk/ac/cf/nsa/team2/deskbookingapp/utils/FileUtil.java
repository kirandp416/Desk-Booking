package uk.ac.cf.nsa.team2.deskbookingapp.utils;

import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.cf.nsa.team2.deskbookingapp.Application;
import uk.ac.cf.nsa.team2.deskbookingapp.exception.FileException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/23
 */
public class FileUtil {

    private static String BASE_PATH;

    static {
        String[] builds = Application.class.getResource("").getPath().split("build");
        BASE_PATH = builds[0] + "build/resources/main/templates/upload";
        BASE_PATH = BASE_PATH.substring(1);
    }

    public static String upload(MultipartFile file) {
        ClassLoader classLoader = Application.class.getClassLoader();
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
            }

            Files.createFile(p);
            file.transferTo(p);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
        return filename;
    }

    public static Resource getResource(String filename) {
        System.out.println("BASE_PATH = " + BASE_PATH);
        filename = "/" + filename;
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
