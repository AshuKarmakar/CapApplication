package customer.bookstore.Services;
import cds.gen.booksservice.Books_;
import cds.gen.booksservice.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;

@Component
public class BooksService {

    @Autowired
    PersistenceService db;

    public Books getBooksByName(String bookName){
        CqnSelect findBook = Select.from(Books_.class).where(b -> b.name().eq(bookName));
        Books exists = db.run(findBook).first(Books.class).orElse(null);
        return exists;
    }

    public Books getBookById(int book_id){
        CqnSelect findBook = Select.from(Books_.class).where(b -> b.ID().eq(book_id));
        Books book = db.run(findBook).first(Books.class).orElse(null);
        return book;
    }

    public void bookUpdate(Books book, int qty, int book_id){
        book.setStock(book.getStock() + qty);
        CqnUpdate update = Update.entity(Books_.class).data(book).where(b -> b.ID().eq(book_id));
        db.run(update);
    }
}
