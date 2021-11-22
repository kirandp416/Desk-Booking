package uk.ac.cf.nsa.team2.deskbookingapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.cf.nsa.team2.deskbookingapp.exception.FileException;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/20
 */
@Service("FileService")
public class FileService {
    private Path fileStorageLocation;

    public FileService(@Value("D:/loadTest") String path) {
        this.fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new FileException("Could not create the directory", e);
        }
    }

    /**
     * @param file
     * @return fileName
     */
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String new_Name = fileName;
        try {
            //
            File file1 = new File(this.fileStorageLocation + "/" + new_Name);
            if (!file1.exists()) {
                Path targetLocation = this.fileStorageLocation.resolve(new_Name);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return new_Name;
            } else {
                // solve the same filename
                int count = 1;
                boolean flag = false;
                do {
                    int lastIndexOf = fileName.lastIndexOf(".");
                    String suffix = fileName.substring(lastIndexOf);
                    new_Name = lastIndexOf + "(" + count + ")" + suffix;
                    count++;
                    file1 = new File(this.fileStorageLocation + "/" + new_Name);
                    if(!file1.exists()) {
                        flag = true;
                        Path targetLocation = this.fileStorageLocation.resolve(new_Name);
                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                        return new_Name;
                    }
                } while (flag = true);
            }
            return new_Name;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
}
