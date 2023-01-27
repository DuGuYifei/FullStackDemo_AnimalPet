package com.example.springbootlab.animal.dto;

import com.example.springbootlab.animal.entity.FileInfo;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2023/1/16 8:56
 * Description: get files
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFilesResponse {

    @Singular
    private List<FileInfo> files;

    public static Function<Collection<FileInfo>, GetFilesResponse> entityToDtoMapper(){
        return files -> {
            GetFilesResponse.GetFilesResponseBuilder response = GetFilesResponse.builder();
            files.stream().map(file -> FileInfo.builder()
                    .id(file.getId())
                    .name(file.getName())
                    .author(file.getAuthor())
                    .description(file.getDescription())
                    .build())
                    .forEach(response::file);
            return response.build();
        };
    }
}
