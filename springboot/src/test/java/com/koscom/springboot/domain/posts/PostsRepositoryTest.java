package com.koscom.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
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

    //LocalDate : 일자 (2021.10.31)
    //LocalDateTime : 일시까지 (2021.10.31 10:55:01.9999)

    @Test
    public void 등록시간_수정시간이_저장된다(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //지금 등록한 일시와 수정일시는 최소한 2019년보다는 뒤에 있을 것 이라는 확신이 있음.
        //매일 수행할때마다 테스트 일시가 변경되니 특정 일시를 확정할 수 없는 테스트

        //when
        List<Posts> postsList = postRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+",modifyDate="+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now); //2019년보다는 생성일이 뒤에 있는지
        assertThat(posts.getModifiedDate()).isAfter(now); //2019년보다는 수정일이 뒤에 있는지
    }
}
