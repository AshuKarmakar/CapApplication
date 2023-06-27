package customer.bookstore.handlers;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import cds.gen.booksservice.Books;
import cds.gen.booksservice.BooksService_;
import cds.gen.booksservice.Books_;
import customer.bookstore.EventContext.BooksReStockEventContext;
import customer.bookstore.Services.BooksService;


@Component
@ServiceName(BooksService_.CDS_NAME)
public class BooksController implements EventHandler {

    @Autowired
    BooksService booksservice;

    @Autowired
    PersistenceService db;

    @Before(event = CqnService.EVENT_CREATE, entity = Books_.CDS_NAME)
    public void beforeCreate(List<Books> books){    
        System.out.println("Hello");
        for(Books book : books ){
            String bookName = book.getName();
            Books exists = booksservice.getBooksByName(bookName);
            if(exists != null){
                throw new ServiceException(ErrorStatuses.CONFLICT, "Book already present just restock");
            }
        }
    }
    
    @On(event = "BooksReStock")
    public void reStock(BooksReStockEventContext context){
        int book_id = (int) context.get("bookId");
        int qty = (int) context.get("quantity");
        System.out.println("Restocking");
        Books book = booksservice.getBookById(book_id);
        if(book != null){
            booksservice.bookUpdate(book, qty, book_id);
            context.setResult("Restocked Successfully");
        }
        else {
            context.setResult("Restock Unsuccessfull, Book not found");
        }
    }

}

