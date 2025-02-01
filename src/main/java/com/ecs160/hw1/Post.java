package com.ecs160.hw1;

import java.util.List;

public interface Post {
    String getUri();
    String getCid();
    String getAuthor();
    String getContent();
    Integer getReplyCount();
    String getTimestamp();
    List<Post> getReplies();
}
