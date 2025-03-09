package com.guayaquil.hackathon.dto.facebook;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;
import java.util.List;

@Data
public class PostsDto {
    private List<PostDto> data;
    private PagingDto paging;
}