package com.ecs160.hw1;

import com.google.gson.JsonObject;

import java.util.List;

public interface Post {
    String getUri();
    String getCid();
    JsonObject getAuthor();
    String getContent();
    Integer getReplyCount();
    String getTimestamp();
    List<Post> getReplies();
}
