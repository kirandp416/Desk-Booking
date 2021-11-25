package uk.ac.cf.nsa.team2.deskbookingapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.ac.cf.nsa.team2.deskbookingapp.response.UploadFileResponse;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/11/20
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("file.getName() = " + file.getName());
        String fileName = FileUtil.upload(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName,fileDownloadUri,file.getContentType(),file.getSize()).toString();
    }

    @GetMapping(value = "test")
    public String test() {
        return "hello";
    }

    @GetMapping("/download/{fileName:.*}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = FileUtil.getResource(fileName);
        String contentType = "application/octet-stream";
        try {
            request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            logger.info("Could not determine file type.");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
