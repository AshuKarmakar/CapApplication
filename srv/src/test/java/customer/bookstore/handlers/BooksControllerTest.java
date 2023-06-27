 package customer.bookstore.handlers;

 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertThrows;
 import static org.mockito.ArgumentMatchers.any;
 import static org.mockito.Mockito.mock;

 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Set;

 import cds.gen.booksservice.Books;


 import cds.gen.booksservice.BooksService_;
 import com.sap.cds.reflect.CdsEntity;
 import com.sap.cds.reflect.CdsModel;
 import com.sap.cds.services.EventContext;
 import com.sap.cds.services.Service;
 import com.sap.cds.services.ServiceCatalog;
 import com.sap.cds.services.authentication.AuthenticationInfo;
 import com.sap.cds.services.cds.CqnService;
 import com.sap.cds.services.changeset.ChangeSetContext;
 import com.sap.cds.services.messages.Messages;
 import com.sap.cds.services.request.FeatureTogglesInfo;
 import com.sap.cds.services.request.ParameterInfo;
 import com.sap.cds.services.request.UserInfo;
 import com.sap.cds.services.runtime.CdsRuntime;
 import customer.bookstore.EventContext.BooksReStockEventContext;
 import customer.bookstore.Services.BooksService;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.runner.RunWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;
 import org.mockito.junit.MockitoJUnitRunner;

 import com.sap.cds.services.ServiceException;
 import com.sap.cds.services.persistence.PersistenceService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;


 @RunWith(MockitoJUnitRunner.class)
 public class BooksControllerTest {

     @InjectMocks
     BooksController booksController;


     Books book1 = Books.create();
     Books book2 = Books.create();

     @Mock
     PersistenceService db;

     @Mock
     BooksService booksService;

     @Mock
     @Qualifier(BooksService_.CDS_NAME)
     private CqnService service;

     @Before
     public void setUp() throws Exception{
         MockitoAnnotations.initMocks(this);
     }

     @Test
     public void bookExist_test(){
         book1.setId(1);
         book1.setName("HP");
         book1.setAuthor("JK");
         book1.setStock(3);

         book2.setId(2);
         book2.setName("A");
         book2.setAuthor("B");
         book2.setStock(3);
         List<Books> records = new ArrayList<Books>(Arrays.asList(book1, book2));
         Mockito.when(booksService.getBooksByName("HP")).thenReturn(book1);
         assertThrows(ServiceException.class, ()-> booksController.beforeCreate(records));
     }

     @Test
     public void bookNotExixt_test(){
         book1.setId(1);
         book1.setName("HP");
         book1.setAuthor("JK");
         book1.setStock(3);

         book2.setId(2);
         book2.setName("A");
         book2.setAuthor("B");
         book2.setStock(3);

         List<Books> records = new ArrayList<Books>(Arrays.asList(book1, book2));
         Mockito.when(booksService.getBooksByName("HP")).thenReturn(null);
         Assertions.assertDoesNotThrow(()-> booksController.beforeCreate(records));
     }

     @Test
     public void bookRestock_test(){
         BooksReStockEventContext context = BooksReStockEventContext.create();
         context.setBookId(1);
         context.setQuantity(2);

         book1.setId(1);
         book1.setName("HP");
         book1.setAuthor("JK");
         book1.setStock(3);

         Mockito.when(booksService.getBookById(1)).thenReturn(book1);
//         service.emit(context);
         booksController.reStock(context);

         String result = context.getResult();
         assertEquals("Restocked Successfully", result);

     }

 }
