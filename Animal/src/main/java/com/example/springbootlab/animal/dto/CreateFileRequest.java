package com.example.springbootlab.animal.dto;

import com.example.springbootlab.animal.entity.FileInfo;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.Function;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2023/1/9 5:35
 * Description: upload new file
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateFileRequest {

    private String name;

    private String author;

    private String description;

    public static Function<CreateFileRequest, FileInfo> dtoToEntityMapper(){
        return request -> FileInfo.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .description(request.getDescription())
                .build();
    }
}
