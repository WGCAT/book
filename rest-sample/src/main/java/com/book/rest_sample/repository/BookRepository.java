package com.book.rest_sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.rest_sample.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 메서드쿼리
List<Book> findByName(String name);

// JPQL
// nativeQuery = true라면 연동하는 db에 수동으로 쿼리를 때려박는다
// @Query(value = "SELECT b FROM Book b WHERE b.name Like %:word%") 까지 있다면 자동으로 형식에 맞게 쿼리를 넣어준다
@Query(value = "SELECT b FROM Book b WHERE b.name Like %:word%", nativeQuery = true)
List<Book> findBySameName(@Param("word") String name);
    
}