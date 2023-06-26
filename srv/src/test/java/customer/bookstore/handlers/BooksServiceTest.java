// package customer.bookstore.handlers;

// import static org.junit.Assert.*;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.w3c.dom.events.Event;

// import com.sap.cds.Result;
// import com.sap.cds.ql.Select;
// import com.sap.cds.ql.cqn.CqnSelect;
// import com.sap.cds.services.EventContext;
// import com.sap.cds.services.ServiceException;
// import com.sap.cds.services.cds.CqnService;
// import com.sap.cds.services.handler.annotations.Before;
// import com.sap.cds.services.persistence.PersistenceService;

// import cds.gen.booksservice.Books;
// import cds.gen.booksservice.BooksService_;
// import cds.gen.booksservice.Books_;
// import cds.gen.borrowservice.Subscriber_;


// @RunWith(MockitoJUnitRunner.class)
// public class BooksServiceTest {


//     @Autowired  
//     @Qualifier(BooksService_.CDS_NAME)
//     private CqnService service; 

//     @Autowired
//     PersistenceService db;
    
//     Books book_1 = Books.create();
//     Books book_2 = Books.create();

//     BooksService booksService = new BooksService();

//     @Before
//     public void prepareBooks(){
//         MockitoAnnotations.initMocks(this);


//         book_1.setId(2);
//         book_1.setName("HP");
//         book_1.setAuthor("JK");
//         book_1.setStock(2);
//         book_2.setId(3);
//         book_2.setName("HP");
//         book_2.setAuthor("JK");
//         book_2.setStock(3);
//     }

//     @Test
//     public void testing(){
//         // CqnSelect findBook = Select.from(Books_.class).where(b -> b.name().eq(book_1.getName()));

//         // Mockito.when(db.run(findBook)).thenReturn((Result) book_1);
  
//         List<Books> records = new ArrayList<>(Arrays.asList(book_1, book_2));
//         records.forEach(record->{
//             EventContext context = EventContext.create(CqnService.EVENT_CREATE, Books_.CDS_NAME);
//             context.put("ID",1);
//             context.put("name", "HP");
//             context.put("author", "JK");
//             context.put("stock", 3);
//             service.emit(context);
//         });

//         assertThrows(ServiceException.class, ()-> booksService.beforeCreate(records));
//     }
// }
