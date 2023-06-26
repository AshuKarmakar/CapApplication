// package customer.bookstore.handlers;

// import static org.junit.Assert.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.mock;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import cds.gen.booksservice.Books;


// import org.junit.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.MockitoJUnitRunner;

// import com.sap.cds.Result;
// import com.sap.cds.ql.Insert;
// import com.sap.cds.ql.cqn.CqnInsert;
// import com.sap.cds.services.ServiceException;
// import com.sap.cds.services.environment.CdsProperties.Persistence;
// import com.sap.cds.services.persistence.PersistenceService;
// import cds.gen.booksservice.Books_;
// import customer.bookstore.Services.BooksServices;


// @RunWith(MockitoJUnitRunner.class)
// public class BooksTest {

//     @InjectMocks
//     BooksService booksService;

//     Books book1 = Books.create();
//     Books book2 = Books.create();

//     @Mock
//     PersistenceService db;

//     @Mock
//     BooksServices booksSer;

//     @BeforeEach
//     void setUp() throws Exception{
//         MockitoAnnotations.initMocks(this);
  
//     }

//     @Test
//     public void testBooks(){ 
//         book1.setId(1);
//         book1.setName("HP");
//         book1.setAuthor("JK");
//         book1.setStock(3);

//         book2.setId(2);
//         book2.setName("A");
//         book2.setAuthor("B");
//         book2.setStock(3);
 
//         List<Books> records = new ArrayList<>(Arrays.asList(book1, book2)); 
//         Mockito.when(booksSer.getBooks("HP")).thenReturn(null);
//         assertThrows(ServiceException.class, ()-> booksService.beforeCreate(records));
//     }
    
// }
