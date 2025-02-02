package com.ecs160.hw1;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class WeightedAnalyzer implements Analyzer{
    @Override
    public double getTotalPosts(List<Post> posts) {
        int longest = findLongestPost(posts);
        double totalWeight = 0;
        for (Post post:posts){
            double weight = 1 + ((double) countWords(post.getContent()) / longest);
            totalWeight += weight;
            if (!post.getReplies().isEmpty()){
                totalWeight += getTotalPosts(post.getReplies());
            }
        }
        return totalWeight;
    }

    @Override
    public double getAvgPosts(List<Post> posts) {
        int longest = findLongestPost(posts);
        double totalWeight = 0;
        for (Post post:posts){
            if (!post.getReplies().isEmpty()){
                totalWeight += getTotalPosts(post.getReplies());
            }
        }
        return totalWeight / posts.size();
    }

    @Override
    public String getAvgInterval(List<Post> posts) {
        long averageDiff = 0;
        for (Post post: posts){
            if (!post.getReplies().isEmpty()){
                long totalInterval = 0;
                long parent = Instant.parse(post.getTimestamp()).getEpochSecond();
                for (Post reply: post.getReplies()){
                    totalInterval += Math.abs(parent - Instant.parse(reply.getTimestamp()).getEpochSecond());
                }
                averageDiff += totalInterval / (getTotalPosts(post.getReplies()) + 1);
            }
        }
        Duration avgInterval = Duration.ofSeconds(averageDiff);
        return String.format("%02d:%02d:%02d", avgInterval.toHours(), avgInterval.toMinutesPart(), avgInterval.toSecondsPart());
    }

    private int findLongestPost(List<Post> posts){
        int longest = 0;
        for (Post post : posts){
            int postLength = countWords(post.getContent());
            if (postLength > longest){
                longest = postLength;
            }
            if (!post.getReplies().isEmpty()){
                int replyLength = findLongestPost(post.getReplies());
                if (replyLength > longest){
                    longest = replyLength;
                }
            }
        }
        return longest;
    }

    private int countWords(String content){
        if (content == null || content.trim().isEmpty()) {
            return 0;
        }
        return content.trim().split("\\s+").length;
    }
}
