package com.example.springbootlab.animal.controller;

import com.example.springbootlab.animal.dto.CreateFileRequest;
import com.example.springbootlab.animal.dto.GetFilesResponse;
import com.example.springbootlab.animal.entity.FileInfo;
import com.example.springbootlab.animal.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2023/1/9 5:31
 * Description: upload and download file
 */


@Controller
@RequestMapping("api/files")
public class FileController {
    @Value("${filePath}")
    private String filePath;

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<GetFilesResponse> getAnimals() {
        return ResponseEntity.ok(GetFilesResponse.entityToDtoMapper().apply(fileService.findAll()));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public void getFile(@PathVariable("id") Long id, HttpServletResponse response) {
        Optional<FileInfo> fileInfo = fileService.find(id);
        System.out.println("get file");
        if(fileInfo.isPresent()) {
            String realPath = filePath + fileInfo.get().getId().toString() + "/" + fileInfo.get().getName();
            response.setCharacterEncoding("UTF-8");
            ServletUtil.write(response,new File(realPath));
        }else{
            response.addHeader("file", "not found");
        }
    }

    @PostMapping
    public ResponseEntity<Void> createFile(@RequestParam("name") String name,
                                           @RequestParam("author") String author,
                                           @RequestParam("description") String description,
                                           @RequestParam("file") MultipartFile file,
                                           UriComponentsBuilder builder) {

        System.out.println(file.getOriginalFilename() + "name: " + name);

        FileInfo fileInfo = CreateFileRequest
                .dtoToEntityMapper()
                .apply(new CreateFileRequest(name, author, description));

        fileInfo = fileService.create(fileInfo);

        File directoryFile = new File(filePath + fileInfo.getId().toString() + "/");

        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }

        saveFile(file, fileInfo.getId());

        return ResponseEntity.created(builder.pathSegment("api", "files", "{id}").buildAndExpand(fileInfo.getId()).toUri()).build();
    }

//    @PostMapping
//    public ResponseEntity<Void> createFile(@RequestBody CreateFileRequest request, UriComponentsBuilder builder) {
//
//        System.out.println(request);
//        request.setName(request.getFile().getOriginalFilename());
//
//        FileInfo fileInfo = CreateFileRequest
//                .dtoToEntityMapper()
//                .apply(request);
//
//        fileInfo = fileService.create(fileInfo);
//
//        File directoryFile = new File(filePath + fileInfo.getId().toString() + "/");
//
//        if (!directoryFile.exists()) {
//            directoryFile.mkdirs();
//        }
//
//        if (!request.getFile().isEmpty()) {
//            saveFile(request.getFile(), fileInfo.getId());
//        }
//        System.out.println("--new file--");
//        return ResponseEntity.created(builder.pathSegment("api", "files", "{id}").buildAndExpand(fileInfo.getId()).toUri()).build();
//    }

    public void saveFile(MultipartFile file, Long id){
        String name = file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath + id.toString() + "/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
