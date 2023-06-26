package customer.bookstore.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import cds.gen.booksservice.Books_;
import cds.gen.booksservice.Books;
import cds.gen.borrowservice.Borrow;
import cds.gen.borrowservice.BorrowService_;
import cds.gen.borrowservice.Borrow_;
import cds.gen.subscriberservice.Subscriber;
import cds.gen.subscriberservice.Subscriber_;
import customer.bookstore.Services.BorrowService;



@Component
@ServiceName(BorrowService_.CDS_NAME)
public class BorrowController implements EventHandler {
    @Autowired
    PersistenceService db;

    @Autowired
    BorrowService borrowService;
    
    @Before(event = CqnService.EVENT_CREATE, entity = Borrow_.CDS_NAME)
    public void check(List<Borrow> borrows){
        for(Borrow borrow : borrows){
            int book_id = borrow.getBook().getId();
            int subs_id = borrow.getSubscriber().getId();
            Books foundBook = borrowService.findBook(book_id);
            Subscriber foundSub = borrowService.findSubscriber(subs_id);

            // db.run(findSubs).first(Subscriber.class).orElse(null);
            if((foundBook != null) && (foundSub != null)){
                if((foundBook.getStock() == borrow.getQuantity() || foundBook.getStock() > borrow.getQuantity())){
                    borrowService.BooksUpdate(foundBook, borrow, book_id);
                }
                else{
                    throw new ServiceException(ErrorStatuses.CONFLICT, "The Book is out of stock"); 
                }      
            }
            else{
                throw new ServiceException(ErrorStatuses.CONFLICT, "There is no book or subscriver entry with this ID");
            }
        }
    }
}

