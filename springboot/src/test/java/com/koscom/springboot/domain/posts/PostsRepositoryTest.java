package com.koscom.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class PostsRepositoryTest {
    @Autowired
    PostsRepository postRepository;


    @AfterEach 
    void tearDown() {
        postRepository.deleteAll();
    }//테스트가 끝날때마다 수행하는 것

    @Test
    void 게시글저장_불러오기(){
        String title= "테스트 타이틀";
        String content = "테스트 본문";
        postRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .build());
        List<Posts> result = postRepository.findAll();

        System.out.println(result.get(0).getId()); //db가 넣어준 값
        System.out.println(result.get(0).getTitle()); //내가 넣어준 값

        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(content);
    }

    @Test
    void 게시글저장_불러오기2(){
        String title= "테스트 타이틀";
        String content = "테스트 본문";
        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());
        List<Posts> result = postRepository.findAll();

        System.out.println(result.size());
        assertThat(result).hasSize(1);
    }
}
