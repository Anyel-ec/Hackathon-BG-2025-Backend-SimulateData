package com.guayaquil.hackathon.dto.facebook;

import lombok.Data;

import java.util.List;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@Data
public class FacebookUserDto {
    private String id;
    private String name;
    private PostsDto posts;
    private AgeRangeDto ageRange;
    private String birthday;
    private String email;
    private String gender;
    private HometownDto hometown;
    private LocationDto location;
    private PhotosDto photos;
    private String link;
    private List<WorkDto> work;
    private List<EducationDto> education;
    private LikesDto likes;
    private List<String> interests;
    private String relationshipStatus;
    private String politicalViews;
    private String religion;
    private List<BrandDto> favoriteBrands;
}