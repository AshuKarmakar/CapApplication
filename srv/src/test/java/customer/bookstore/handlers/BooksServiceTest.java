package customer.bookstore.handlers;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.persistence.PersistenceService;
import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import cds.gen.borrowservice.Books;
import io.vavr.collection.List;


@RunWith(MockitoJUnitRunner.class)
public class BooksServiceTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private PersistenceService db;

    @InjectMocks
    private BooksService bookService;

    Books book_1 =Books.create();
    book_1.setID(1);
    book_1.setName("HP");
    book_1.setAuthor("JK");
    book_1.setStock(2);


    // Books book_2 = new Books(2, "HPA", "JKA", 4);
    // Books book_3 = new Books(3, "HPB", "JKB", 3);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookService).build();
    }

    @Test
    public void getAllData_success() throws Exception{
        List<Books> records = new ArrayList<>(Arrays.asList(book_1));

        // CqnSelect findBook = Select.from(Books_.class);

        // Mockito.when(db.run(findBook, namedValues))
        Mockito.when(Registry.getId()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/odata/v4/BooksService/Books")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }




}
