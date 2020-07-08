package com.project.controller;

import com.project.model.DBFile;
import com.project.service.DBFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping({"/lecturer/download/{idFile}", "/student/download/{idFile}"})
public class DownloadController {

    @Autowired
    private DBFileService dbFileService;


    @GetMapping
    public ResponseEntity<ByteArrayResource> download( @PathVariable("idFile") String idFile) {

        ResponseEntity respEntity = null;
        DBFile dbFile = dbFileService.findById(idFile);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=" + dbFile.getFileName());
        responseHeaders.add("Content-Type", dbFile.getFileType());

        respEntity = new ResponseEntity(dbFile.getData(), responseHeaders, HttpStatus.OK);

        return respEntity;

//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
//                .body(new ByteArrayResource(dbFile.getData()));


    }

}
