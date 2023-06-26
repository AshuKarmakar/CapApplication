package customer.bookstore.EventContext;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
@EventName("BooksReStock")
public interface BooksReStockEventContext extends EventContext{
    void setResult(String resulString);
}
