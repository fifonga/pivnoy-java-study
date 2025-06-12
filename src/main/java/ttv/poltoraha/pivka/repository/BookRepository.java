package ttv.poltoraha.pivka.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttv.poltoraha.pivka.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {



    @Query(
            value = """
        SELECT b.* FROM book b
        JOIN author a ON b.author_id = a.id
        WHERE a.full_name LIKE '%' || :lastName || '%'
        AND a.avg_rating = (
            SELECT MAX(a2.avg_rating)
            FROM author a2
            WHERE a2.full_name LIKE '%' || :lastName || '%'
        )
    """,
            nativeQuery = true
    )
    public List<Book> findBooksByTopAuthorLastName(@Param("lastName") String lastName);



}
