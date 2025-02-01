package com.ecs160.hw1;

import java.util.List;

public class BasicAnalyzer implements Analyzer{
    @Override
    public double getTotalPosts(List<Post> posts) {
        double total = 0;
        for(Post post: posts){
            total += post.getReplyCount() + 1;
        }
        return total;
    }

    @Override
    public double getAvgPosts(List<Post> posts) {
        return 0;
    }

    @Override
    public String getAvgInterval(List<Post> posts) {
        return "";
    }
}
