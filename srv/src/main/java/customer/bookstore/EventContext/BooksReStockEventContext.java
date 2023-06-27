package customer.bookstore.EventContext;

import cds.gen.booksservice.Books_;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
@EventName("BooksReStock")
public interface BooksReStockEventContext extends EventContext{
    void setResult(String resulString);
    static BooksReStockEventContext create() {
        return EventContext.create(
                BooksReStockEventContext.class,
                Books_.CDS_NAME);
    }
    void setBookId(int id);
    void setQuantity(int qty);
    String getResult();

}
