package ttv.poltoraha.pivka.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttv.poltoraha.pivka.entity.Book;
import ttv.poltoraha.pivka.entity.Review;
import ttv.poltoraha.pivka.repository.BookRepository;
import ttv.poltoraha.pivka.service.BookService;
import util.MyUtility;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);


    public void updateAvgRating(Integer bookId) {
        Book book = MyUtility.findEntityById(bookRepository.findById(bookId), "book", bookId.toString());
        Double updatedAvgRaiting = book.getReviews()
                .stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        book.setRating(updatedAvgRaiting);

        bookRepository.save(book);
    }

    @Override
    public List<Book> getTopBooksByTag(String tag, int count) {
        Pageable pageable = PageRequest.of(0, count);
        logger.info("Пытается получить топ {} книг по тегу: {}", count, tag);
        val books = bookRepository.findTopBooksByTag(tag);
        logger.info("Успешно получен топ {} книг по тегу: {}", count, tag);
        return bookRepository.findTopBooksByTag(tag, pageable);
    }

}
