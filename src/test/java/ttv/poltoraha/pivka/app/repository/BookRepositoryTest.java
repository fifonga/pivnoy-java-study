package ttv.poltoraha.pivka.app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ttv.poltoraha.pivka.entity.Book;
import ttv.poltoraha.pivka.repository.BookRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBookIsNotEmpty() {
        List<Book> books = bookRepository.findBooksByTopAuthorLastName("Достоевский");

        assertThat(books).isNotEmpty();
    }

    @Test
    void testBookCanSelectBooks() {
        List<Book> books = bookRepository.findBooksByTopAuthorLastName("Достоевский");

        assertThat(books).isNotEmpty();
        assertThat(books).hasSize(2);

        // Проверка книги "Идиот"
        Book idiot = books.stream()
                .filter(book -> book.getArticle().equals("Идиот"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Книга 'Идиот' не найдена"));

        assertThat(idiot.getTags()).containsExactlyInAnyOrder("Идиот", "Лоли");

        Book besy = books.stream()
                .filter(book -> book.getArticle().equals("Бесы"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Книга 'Бесы' не найдена"));

        assertThat(besy.getTags()).containsExactlyInAnyOrder("Пьеса", "Лоли");

        assertThat(besy.getTags()).doesNotContain("лол", "Неправильно", "тудудум");

    }

    // Так как книг у клана Жумайсимба нет, то тест только на сортировку по среднему рейтингу
    @Test
    void testBookSortTest() {
        List<Book> books = bookRepository.findBooksByTopAuthorLastName("Жумайсимба");

        assertThat(books).allMatch(book ->
                book.getAuthor() != null &&
                        book.getAuthor().getFullName().equals("Тралл Жумайсимба") &&
                        book.getAuthor().getAvgRating().equals(5.0)
        );
    }

    @Test
    void testBookEmptyList() {
        String searchLastName = "empty";
        List<Book> books = bookRepository.findBooksByTopAuthorLastName(searchLastName);

        assertThat(books).isEmpty();
    }


}
