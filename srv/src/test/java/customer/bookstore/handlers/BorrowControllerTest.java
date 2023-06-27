package customer.bookstore.handlers;

import cds.gen.booksservice.Books;
import cds.gen.borrowservice.Borrow;
import cds.gen.subscriberservice.Subscriber;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.persistence.PersistenceService;
import customer.bookstore.Services.BorrowService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class BorrowControllerTest {

    @InjectMocks
    BorrowController borrowController;

    Borrow borrow1 = Borrow.create();
    Books book1 = Books.create();
    Books book2 = Books.create();

    Subscriber subs1 = Subscriber.create();
    Subscriber subs2 = Subscriber.create();

    Borrow borrow2 = Borrow.create();


    @Mock
    PersistenceService db;

    @Mock
    BorrowService borrowService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        book1.setId(1);
        book1.setName("HP");
        book1.setAuthor("JK");
        book1.setStock(4);

        subs1.setId(1);
        subs1.setName("Ashu");

        book2.setId(2);
        book2.setName("GOT");
        book2.setAuthor("RK");
        book2.setStock(4);

        subs2.setId(1);
        subs2.setName("Tosh");


        borrow1.setId(1);
        borrow1.setBook(book1);
        borrow1.setQuantity(2);
        borrow1.setSubscriber(subs1);

        borrow2.setId(1);
        borrow2.setBook(book2);
        borrow2.setQuantity(2);
        borrow2.setSubscriber(subs2);

    }

    @Test
    public void borrowBook_unsuccess(){
        List<Borrow> records = new ArrayList<>(Arrays.asList(borrow1, borrow2));
        Mockito.when(borrowService.findBook(borrow1)).thenReturn(null);
        assertThrows(ServiceException.class, ()-> borrowController.check(records));
    }
    @Test
    public void borrowSubscriber_unsuccess(){
        List<Borrow> records = new ArrayList<>(Arrays.asList(borrow1, borrow2));
        Mockito.when(borrowService.findSubscriber(borrow1)).thenReturn(null);
        assertThrows(ServiceException.class, ()-> borrowController.check(records));
    }
    @Test
    public void borrowBookQuantity_unsuccess(){
        borrow1.setQuantity(5);
        borrow2.setQuantity(6);
        List<Borrow> records = new ArrayList<>(Arrays.asList(borrow1, borrow2));
        Mockito.when(borrowService.findBook(borrow1)).thenReturn(book1);
        Mockito.when(borrowService.findSubscriber(borrow1)).thenReturn(subs1);
        Mockito.when(borrowService.findBook(borrow2)).thenReturn(book2);
        Mockito.when(borrowService.findSubscriber(borrow2)).thenReturn(subs2);
        assertThrows(ServiceException.class, ()-> borrowController.check(records));
    }
//    @Test
//    public void borrowBookQuantity_success(){
//        borrow1.setQuantity(2);
//        borrow2.setQuantity(2);
//        List<Borrow> records = new ArrayList<>(Arrays.asList(borrow1, borrow2));
//        Mockito.when(borrowService.findBook(borrow1)).thenReturn(book1);
//        Mockito.when(borrowService.findSubscriber(borrow1)).thenReturn(subs1);
//        Mockito.when(borrowService.findBook(borrow2)).thenReturn(book2);
//        Mockito.when(borrowService.findSubscriber(borrow2)).thenReturn(subs2);
//        Assertions.assertDoesNotThrow(()-> borrowController.check(records));
//    }
}
