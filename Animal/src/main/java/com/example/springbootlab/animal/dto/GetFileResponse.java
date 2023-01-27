package com.example.springbootlab.animal.dto;

import com.example.springbootlab.animal.entity.FileInfo;
import lombok.*;

import java.util.function.Function;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2023/1/9 6:21
 * Description: get file
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFileResponse {

    private Long id;

    private String name;

    private String author;

    private String description;

    public static Function<FileInfo, GetFileResponse> entityToDtoMapper(){
        return fileInfo -> GetFileResponse.builder()
                .id(fileInfo.getId())
                .name(fileInfo.getName())
                .author(fileInfo.getAuthor())
                .description(fileInfo.getDescription())
                .build();
    }

}
