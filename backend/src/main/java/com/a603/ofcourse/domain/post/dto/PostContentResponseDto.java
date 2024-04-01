package com.a603.ofcourse.domain.post.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class PostContentResponseDto {
    private String postTitle;
    private String memberImageUrl;
    private String memberNickname;
    private List<PostContentInfo> postContentInfoList;

    @Data @Builder
    public static class PostContentInfo{
        private String placeName;
        private String placeImageUrl;
        private String title;
        private String content;
    }

}

