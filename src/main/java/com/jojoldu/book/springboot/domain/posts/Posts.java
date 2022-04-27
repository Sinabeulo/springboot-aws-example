package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    /*
    Entity 클래스에서는 절대 Setter 메소드를 만들지 말라고 함
    대신 목적과 의도에 맞는 메소드를 추가
    */

    @Id // pk를 의미함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // @Column 을 선언하지 않아도 기본적으로 필드값은 컬럼으로 인식한다.
    // 다만, 문자열 기본값이 varchar(255)인데, 다른 설정을 원할 경우 사용된다.
    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
