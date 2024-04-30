Entities:

● Book: Includes attributes like ID, title, author, publication year, ISBN, etc.

● Patron: Contains details like ID, name, contact information, etc.

● Borrowing Record: Tracks the association between books and patrons,
including borrowing and return dates.

API Endpoints:

● Implement RESTful endpoints to handle the following operations:

Book management endpoints:

● GET /api/books: Retrieve a list of all books.

● GET /api/books/{id}: Retrieve details of a specific book by ID.

● POST /api/books: Add a new book to the library.

● PUT /api/books/{id}: Update an existing book's information.

● DELETE /api/books/{id}: Remove a book from the library.

Patron management endpoints:

● GET /api/patrons: Retrieve a list of all patrons.

● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.

● POST /api/patrons: Add a new patron to the system.

● PUT /api/patrons/{id}: Update an existing patron's information.

● DELETE /api/patrons/{id}: Remove a patron from the system.

Borrowing endpoints:

● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to
borrow a book.

● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.![2024-04-30_10-42](https://github.com/abdelmoneim-elshafei/Library-Management-System/assets/103078233/ea100561-f02b-4dd8-93d6-3c2aec242902)


