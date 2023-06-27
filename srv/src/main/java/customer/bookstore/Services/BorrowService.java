package customer.bookstore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.booksservice.Books_;
import cds.gen.booksservice.Books;
import cds.gen.subscriberservice.Subscriber;
import cds.gen.subscriberservice.Subscriber_;
import cds.gen.borrowservice.Borrow;


@Component
public class BorrowService {
    @Autowired
    private PersistenceService db;

    public Books findBook(Borrow borrow){
        int book_id = borrow.getBook().getId();
        CqnSelect findBook= Select.from(Books_.class).where(b -> b.ID().eq(book_id));
        Books foundBook = db.run(findBook).first(Books.class).orElse(null);
        return foundBook;
    }

    public Subscriber findSubscriber(Borrow borrow){
        int subs_id = borrow.getSubscriber().getId();
        CqnSelect findSubs= Select.from(Subscriber_.class).where(b -> b.ID().eq(subs_id));
        Subscriber foundSub = db.run(findSubs).first(Subscriber.class).orElse(null);
        return foundSub;
    }

    public void BooksUpdate(Books foundBook, Borrow borrow){
        int book_id = borrow.getBook().getId();
        foundBook.setStock(foundBook.getStock() - borrow.getQuantity());
        CqnUpdate update = Update.entity(Books_.class).data(foundBook).where(b -> b.ID().eq(book_id));
        db.run(update);
    }
}
