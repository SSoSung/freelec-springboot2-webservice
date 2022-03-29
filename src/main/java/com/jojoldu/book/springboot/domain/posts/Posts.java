package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
// 기본 생성자 자동 추가
// public Posts(){}와 같은 효과
@NoArgsConstructor
@Entity
public class Posts extends  BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    // 어느 필드에 어떤 값을 채워야 할지 명확하게 인지 할 수 있기에 빌더패턴을 사용. ex) Example.builder()
    //                                                                          .a(a)
    //                                                                          .b(b)
    //                                                                          .build();
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 자바빈 규약을 생각하면서 getter/setter를 무작정 생성하는 경우가 있다.
    // 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없어, 차후 기능 변경시 정말 복잡해진다.
    // Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
    // 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 한다.
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
