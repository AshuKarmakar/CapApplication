using { sap.capire.books_store as db } from '../db/schema';
service BooksService {
    entity Books as projection on db.Books;    
    action BooksReStock(bookId : Integer, quantity : Integer) returns String;
}
service SubscriberService{
    entity Subscriber as  projection on db.Subscriber;
}
service BorrowService{
    entity Borrow as projection on db.Borrow;
    // entity Borrow {
    //     ID : Integer;
    //     Book_Id : Integer;
    //     quantity : Integer;
    //     subscriber_id : Integer;
    // }
    entity Subscriber as  projection on db.Subscriber;
    entity Books as projection on db.Books;
}