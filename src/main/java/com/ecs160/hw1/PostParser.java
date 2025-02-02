package com.ecs160.hw1;

import com.google.gson.*;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PostParser {
    public List<Post> parseJson(String fileName){
        List<Post> posts = new ArrayList<>();
        JsonElement element = JsonParser.parseReader(new InputStreamReader(JsonDeserializer.class.getClassLoader().getResourceAsStream("input.json")));

        if (element.isJsonObject()) {
            JsonArray feedArray = element.getAsJsonObject().get("feed").getAsJsonArray();
            for (JsonElement feedObject : feedArray) {
                if (feedObject.getAsJsonObject().has("thread")) {
                    posts.add(parsePost(feedObject.getAsJsonObject().get("thread").getAsJsonObject()));
                }
            }
        }
        return posts;
    }
    private Post parsePost(JsonObject postObject) {
        JsonObject postData = postObject.get("post").getAsJsonObject();
        String uri = postData.get("uri").getAsString();
        String cid = postData.get("cid").getAsString();
        JsonObject author = postData.get("author").getAsJsonObject();
        String content = postData.get("content").getAsString();
        String timestamp = postData.get("indexedAt").getAsString();
        JsonArray replies = postObject.get("replies").getAsJsonArray();

        Post post;

        if (replies.isEmpty()) {
            post = new SinglePost(uri, cid, author, content, timestamp);
        }
        else{
            Integer replyCount = postData.get("replyCount").getAsInt();
            List<Post> replyPosts = new ArrayList<>();
            for(int i = 0; i < replies.size(); i++) {
                Post reply = parsePost(replies.get(i).getAsJsonObject());
                replyPosts.add(reply);
            }
            post = new Thread(uri, cid, author, content, replyCount, timestamp, replyPosts);
        }
        return post;
    }
}
