package com.spring.boot.controller;

import com.spring.boot.service.FileService;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller class for handling file upload and delete operations.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/file")
@CrossOrigin
@RestController
@Tag(name = "File-API", description = "Controller for handling file upload, delete and existence check operations")
public class FileController {

    private static final Log log = LogFactory.getLog(FileController.class);

    private FileService fileService;

    /**
     * Default constructor for FileController.
     */
    public FileController() {
    }

    /**
     * Constructor with dependency injection for FileService.
     *
     * @param fileService the service to handle file operations
     */
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Endpoint for uploading a file.
     *
     * @param file the file to be uploaded
     * @return a Result object containing the file path
     * @throws IOException if an I/O error occurs
     */
    @Operation(
        summary = "Upload a file",
        description = "Endpoint for uploading a file.",
        parameters = {
            @Parameter(name = "file", in = ParameterIn.QUERY, description = "The file to be uploaded.", required = true)
        }
    )
    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        log.info("[uploadFile]: The user is uploading a file: " + file.getOriginalFilename());
        String path = fileService.uploadFile(file);
        return ResultUtil.success("File uploaded successfully", path);
    }

    /**
     * Endpoint for deleting an uploaded file.
     *
     * @param gfsId the ID of the file to be deleted
     * @return a Result object containing the ID of the deleted file
     */
    @Operation(
        summary = "Delete an uploaded file",
        description = "Endpoint for deleting an uploaded file.",
        parameters = {
            @Parameter(name = "gfsId", in = ParameterIn.QUERY, description = "The ID of the file to be deleted.", required = true)
        }
    )
    @DeleteMapping("/")
    public Result<String> deleteFile(@RequestParam String gfsId) {
        log.info("[deleteFile]: The user has uploaded a new file and is preparing to delete the previously uploaded file: " + gfsId);
        fileService.deleteFile(gfsId);
        return ResultUtil.success("File deleted successfully", gfsId);
    }

    @Operation(
        summary = "Check if a file exists",
        description = "Endpoint for checking if a file exists.",
        parameters = {
            @Parameter(
                    name = "filepath",
                    in = ParameterIn.QUERY,
                    description = "The path of the file to check.",
                    required = true,
                    example = "magma/magma_output/hg19_anno/CAUSALdb_BCCCEAC_F900139_11038.genes.annot"
            )
        }
    )
    @GetMapping("/exist")
    public Result<Boolean> existFile(@RequestParam String filepath) {
        boolean isExist = fileService.existFile(filepath);
        return ResultUtil.success("[existFile]: File Check if the file exists", isExist);
    }

}
