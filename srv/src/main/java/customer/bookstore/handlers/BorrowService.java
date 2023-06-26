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
import cds.gen.borrowservice.Books;
import cds.gen.borrowservice.Borrow;
import cds.gen.borrowservice.BorrowService_;
import cds.gen.borrowservice.Borrow_;
import cds.gen.borrowservice.Subscriber;
import cds.gen.subscriberservice.Subscriber_;



@Component
@ServiceName(BorrowService_.CDS_NAME)
public class BorrowService implements EventHandler {
    @Autowired
    PersistenceService db;
    
    @Before(event = CqnService.EVENT_CREATE, entity = Borrow_.CDS_NAME)
    public void check(List<Borrow> borrows){
        for(Borrow borrow : borrows){
            int book_id = borrow.getBook().getId();
            int subs_id = borrow.getSubscriber().getId();
            
            CqnSelect findBook= Select.from(Books_.class).where(b -> b.ID().eq(book_id));
            CqnSelect findSubs= Select.from(Subscriber_.class).where(b -> b.ID().eq(subs_id));
            Books foundBook = db.run(findBook).first(Books.class).orElse(null);
            Subscriber foundSub = db.run(findSubs).first(Subscriber.class).orElse(null);
            if((foundBook != null) && (foundSub != null)){
                if((foundBook.getStock() == borrow.getQuantity() || foundBook.getStock() > borrow.getQuantity())){
                    foundBook.setStock(foundBook.getStock() - borrow.getQuantity());
                    CqnUpdate update = Update.entity(Books_.class).data(foundBook).where(b -> b.ID().eq(book_id));
                    db.run(update);
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

