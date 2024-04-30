package com.noob.lms.controller;

import com.noob.lms.service.BorrowingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @Operation(summary = "Borrow a book", description = "Allow a patron to borrow a book.")
    public ResponseEntity<?> borrowBook(@Parameter(description = "Book ID", example = "1", in = ParameterIn.PATH) @PathVariable Long bookId,
                                        @Parameter(description = "Patron ID", example = "1", in = ParameterIn.PATH) @PathVariable Long patronId) {
        borrowingRecordService.borrowBook(bookId, patronId);
        String body = "Book with id: " + bookId + " borrowed successfully by Patron with id: " + patronId;
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @Operation(summary = "Return a book", description = "Record the return of a borrowed book by a patron.")
    public ResponseEntity<?> returnBook(@Parameter(description = "Book ID", example = "1", in = ParameterIn.PATH) @PathVariable Long bookId,
                                        @Parameter(description = "Patron ID", example = "1", in = ParameterIn.PATH) @PathVariable Long patronId) {
        borrowingRecordService.returnBook(bookId, patronId);

        String body = "Book with id: " + bookId + " returned successfully by Patron with id: " + patronId;
        return new ResponseEntity<>(body, HttpStatus.OK);

    }
}
