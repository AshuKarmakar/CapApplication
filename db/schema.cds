namespace sap.capire.books_store;
// using { cuid } from '@sap/cds/common';
entity Books{
    KEY ID : Integer;
    name : String(111);
    author : String(111);
    stock : Integer;
}
entity Subscriber {
    KEY ID : Integer;
    name : String(111);
}
entity Borrow {
    KEY ID : Integer;
    book : Association to Books;
    quantity : Integer;
    subscriber : Association to Subscriber;
}
