package com.noob.lms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowingRecordController {

//    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
//        borrowingRecordService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
//        borrowingRecordService.returnBook(bookId, patronId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
