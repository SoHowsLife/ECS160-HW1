package com.ecs160.hw1;

import java.util.ArrayList;
import java.util.List;

public class SinglePost implements Post{

    private final String uri;
    private final String cid;
    private final String author;
    private final String content;
    private final String timestamp;

    public SinglePost(String uri, String cid, String author, String content, String timestamp) {
        this.uri = uri;
        this.cid = cid;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    @Override
    public List<Post> getReplies() {
        return List.of();
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getCid() {
        return cid;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Integer getReplyCount() {
        return 0;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }
}
