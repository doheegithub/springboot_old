package com.koscom.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //디폴트 생성자
@Entity

public class Posts {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 채번 방식
    private Long id; //pk (auto increment, bigint)

    @Column(length=500, nullable = false) //varchar(500), notnull
    private String title;

    @Column(length=2000, nullable = false) //varchar(2000), notnull
    private String content;

    private String author; // @Column이 필수값은 아니고, 없으면 varchar(255), nullable=true가 default

    @Builder // lombok의 builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        //id가 없고 나머지 필드만 있는 생성자
    }
}