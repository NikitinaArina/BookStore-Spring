package com.spring.store.controller;

import com.spring.store.entity.Book;
import com.spring.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private final BookService bookService;

    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> main() {
        return bookService.findAll();
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam("filter") String filter) {
        return bookService.findAllWithFilter(filter);
    }

    /*@GetMapping("/find/books")
    public List<Book> main(
            @RequestParam(required = false, defaultValue = "") String filter) {
        List<Book> books;

        if (!filter.isEmpty()) {
            books = bookRepo.findByAuthorBContainsOrNameContains(filter, filter);
        } else books = bookRepo.findAll();

        return books;
    }

    @PostMapping("/update")
    public Book edit(@RequestParam("bookId") Book book, @RequestParam Integer quantity, @RequestParam Integer price) {
        Optional<Book> byId = bookRepo.findById(book.getId());

        Book book1 = byId.orElseThrow();
        book1.setQuantity(quantity);
        book1.setPrice(price);

        bookRepo.save(book1);
        return book;
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bookId") Book book) {
        List<OrderLine> allOrderLinesByBookId = orderLineRepo.findAllByBook_Id(book.getId());

        for (OrderLine orderLine : allOrderLinesByBookId) {
            orderLine.setAuthor(book.getAuthorB());
            orderLine.setName(book.getName());
            orderLine.setPrice(book.getPrice());
            orderLine.setBook(null);
        }

        bookRepo.deleteById(book.getId());
        return "redirect:/main";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        List<Book> books;

        if (!filter.isEmpty()) {
            books = bookRepo.findByAuthorBContainsOrNameContains(filter, filter);
        } else books = bookRepo.findAll();

        model.addAttribute("books", books);
        model.addAttribute("url", "/search");

        return "index";
    }

    @PostMapping("/main")
    public String add(
            @Valid Book book,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("book", book);
        } else {
            if (file != null) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                book.setFilename(resultFilename);
            }

            model.addAttribute("book", null);

            bookRepo.save(book);
        }
        Iterable<Book> books = bookRepo.findAll();

        model.addAttribute("books", books);

        return "redirect:/main";
    }*/
}