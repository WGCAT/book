package com.book.rest_sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.rest_sample.model.Book;
import com.book.rest_sample.repository.BookRepository;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List <Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping
    public List <Book> getBooksByName(@PathVariable("name") String name) {
        return bookRepository.findByName(name);
    }
    
    @GetMapping
    public List <Book> getBooksBySameName(@PathVariable("word") String word) {
        return bookRepository.findBySameName(word);
    }

    @PostMapping
    // RequestBody : 바디 파라미터를 매개변수로 받아서 사용
    public Book createBook(@RequestBody Book book) {
        // save : 저장한 것을 리턴까지 해주는 메서드
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    // PathVariable : 패스파라미터로 id를 받음
    public Book updateUser(@PathVariable("id") Long id, @RequestBody Book bookInfo) {
        // id로 book을 get하고
        Book book = bookRepository.findById(id).get();
        // book의 요소들을 bookInfo에 입력한 내용으로 대입
        book.setName(bookInfo.getName());
        book.setAuthor(bookInfo.getAuthor());
        book.setPublisher(bookInfo.getPublisher());

        // 대입한 내용 저장
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
    }
}
