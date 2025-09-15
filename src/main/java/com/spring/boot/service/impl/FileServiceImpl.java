package com.spring.boot.service.impl;

import com.spring.boot.config.bean.Path;
import com.spring.boot.service.FileService;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.util.FileUtil;
import com.spring.boot.util.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service implementation for file operations.
 * This class provides methods to upload and delete files.
 *
 * @author Zhengmin Yu
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Log log = LogFactory.getLog(FileServiceImpl.class);

    private Path path;

    /**
     * Default constructor for FileServiceImpl.
     */
    public FileServiceImpl() {
    }

    /**
     * Constructor with Path dependency injection.
     *
     * @param path the Path object containing file path configurations
     */
    @Autowired
    public FileServiceImpl(Path path) {
        this.path = path;
    }

    /**
     * Uploads a file to the server.
     *
     * @param file the MultipartFile to upload
     * @return the unique filename of the uploaded file
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // Retrieve the working path from the Path object
        String workPath = path.getWorkPath();
        // Construct the user-specific path for file storage
        String userPath = workPath + "/user/";
        // Generate a unique filename for the uploaded file
        String filename = StringUtil.getUniqueId10() + "_" + file.getOriginalFilename();
        // Log the file upload path and filename
        log.info("[uploadFile]: User upload file path: {}, filename: {}", userPath, filename);
        // Form the file on the server using the provided input stream
        FileUtil.formation(userPath + filename, file.getInputStream());
        // Return the unique filename
        return filename;
    }

    /**
     * Deletes a file from the server.
     *
     * @param fileId the filename to delete
     */
    @Override
    public void deleteFile(String fileId) {

        if (StringUtil.isEqual(fileId, "example_genes.txt") || StringUtil.isEqual(fileId, "example_tfs.txt")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

        // Retrieve the working path from the Path object
        String workPath = path.getWorkPath();
        // Construct the user-specific path for file storage
        String userPath = workPath + "/user/";
        // Log the file deletion path and filename
        log.info("[deleteFile]: Delete file ==> path: {}, filename: {}", userPath, fileId);
        // Delete the file from the server
        boolean isDelete = FileUtil.delete(userPath + fileId);

        if (!isDelete) {
            log.error("[deleteFile]: Delete file-error ==> path: {}, filename: {}", userPath, fileId);
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

    }

    @Override
    public boolean existFile(String filepath) {
        String workPath = path.getWorkPath();
        return FileUtil.isExist(workPath + "/" + filepath);
    }
}
