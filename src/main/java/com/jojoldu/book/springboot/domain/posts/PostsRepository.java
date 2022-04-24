package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
ibatis, mybatis 등에서 DAO 라고 불리는 DB Layer 접근자이다.
JPA 에선 Repository 라고 부르며 인터페이스로 생성한다.
JpaRepository<Entity 클래스, PK 타입> 을 상속하면 기본적인 CRUD 메소드가 자동 생성됨
*/
public interface PostsRepository extends JpaRepository<Posts,Long> {

}
