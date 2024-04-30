package com.noob.lms.service;

public interface BorrowingRecordService {
    void borrowBook(Long bookId, Long patronId);

    void returnBook(Long bookId, Long patronId);
}
