package com.example.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> { // entitiy명 id컬럼 타입
    // interface라도 내부적으로 class ItemRepository도 생성해줌 -> DB 입출력하는 함수 잔뜩 들어있음
    // db입출력을 하고싶은 클래스에 repository 등록

}
