package com.project.controller;

import com.project.dao.ZadanieDaoImpl;
import com.project.model.DBFile;
import com.project.model.Zadanie;
import com.project.service.DBFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping({"/lecturer/viewFiles/{idTask}","/student/viewFiles/{idTask}"})
public class UploadController {

    @Autowired
    private DBFileService dbFileService;

    @Autowired
    private ZadanieDaoImpl zadanieDao;

    @GetMapping
    public String upload(Model model, @PathVariable("idTask") Integer idTask) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);

        List<DBFile> byZadanieId = dbFileService.findByZadanieId(idTask);
        model.addAttribute("listFiles", byZadanieId);

        Zadanie byId = zadanieDao.findById(idTask);
        List<DBFile> dbFiles = byId.getdBFiles();
        model.addAttribute("files", dbFiles);
        model.addAttribute("idTask", idTask);
        return "upload";
    }


    @PostMapping
    public String singleFileUpload(Model model, @RequestParam("file") MultipartFile file, @PathVariable("idTask") Integer idTask) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);




        if (file.isEmpty()) {
            model.addAttribute("message", "Proszę o podanie ścieżki pliku");
            return "redirect:/"+ role.toLowerCase() +"/viewFiles/"+ idTask +"/?error=true";
        }

        try {
            byte[] bytes = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            String[] split = originalFilename.split("\\.");

            if (split.length>2) {
                model.addAttribute("message", "Proszę o podanie poprawnego pliku");
                return "redirect:/"+ role.toLowerCase() +"/viewFiles/"+ idTask +"/?error=true";
            }

            String name = split[0];
            String roz = split[1];
            Zadanie byId = zadanieDao.findById(idTask);

            DBFile dbFile = new DBFile();
            dbFile.setFileName(name);
            dbFile.setFileType(file.getContentType());
            dbFile.setData(bytes);
            dbFile.setZadanie(byId);

            dbFileService.save(dbFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/"+ role.toLowerCase() +"/viewFiles/"+ idTask +"/?success=true";
    }

}