package com.ecs160.hw1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicAnalyzerTest {
    private BasicAnalyzer analyzer = new BasicAnalyzer();
    @Test
    void testBasic() {
        Post post1 = new SinglePost("1","1","a","abc","2023-07-17T21:13:20.284Z");
        Post post2 = new SinglePost("2","2","b","abadsafc","2023-07-17T22:13:20.284Z");
        Post post3 = new SinglePost("3","3","b","abasdgac","2023-07-17T21:16:20.284Z");
        List<Post> posts = new ArrayList<Post>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        assert(analyzer.getTotalPosts(posts) == 3);
        assert(analyzer.getAvgPosts(posts) == 0);
        assert (Objects.equals(analyzer.getAvgInterval(posts), "00:00:00"));
    }
    @Test
    void testReplies() {
        Post post1 = new SinglePost("1","1","a","abc","2023-07-17T21:16:20.284Z");
        Post post2 = new SinglePost("2","2","b","abadsafc","2023-07-17T21:16:20.284Z");
        Post post3 = new SinglePost("3","3","b","abasdgac","2023-07-17T21:16:20.284Z");
        List<Post> replies = new ArrayList<>();
        replies.add(post1);
        replies.add(post2);
        replies.add(post3);
        Post post4 = new Thread("4","4","d","arharharhreha",3,"2023-07-17T21:10:10.284Z", replies);
        Post post5 = new SinglePost("5","5","e","arhaerharh","2023-07-17T21:24:50.284Z");
        List<Post> posts = new ArrayList<>();
        posts.add(post4);
        posts.add(post5);
        assert(analyzer.getTotalPosts(posts) == 5);
        assert(analyzer.getAvgPosts(posts) == ((double) 3 /5));
        assert(Objects.equals(analyzer.getAvgInterval(posts), "00:06:10"));
    }
    @Test
    void testEmpty() {
        List<Post> posts = new ArrayList<>();
        assert(analyzer.getTotalPosts(posts) == 0);
        assert(analyzer.getAvgPosts(posts) == 0);
        assert(Objects.equals(analyzer.getAvgInterval(posts), "00:00:00"));
    }
}
