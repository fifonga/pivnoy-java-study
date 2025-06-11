package ttv.poltoraha.pivka.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttv.poltoraha.pivka.entity.Author;
import ttv.poltoraha.pivka.entity.Book;
import ttv.poltoraha.pivka.entity.Quote;
import ttv.poltoraha.pivka.service.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("author")
    public List<Author> recommendAuthor(@RequestParam String username) {
        return recommendationService.recommendAuthor(username);
    }

    @GetMapping("book")
    public List<Book> recommendBook(@RequestParam String username) {
        return recommendationService.recommendBook(username);
    }

    @GetMapping("quote-by-book")
    public List<Quote> recommendQuoteByBook(@RequestParam Integer book_id) {
        return recommendationService.recommendQuoteByBook(book_id);
    }
}
