package com.noob.lms.repository;

import com.noob.lms.entity.Book;
import com.noob.lms.entity.BorrowingRecord;
import com.noob.lms.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord, Long> {

    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
    boolean existsByBookIdAndPatronIdAndReturnDateIsNull(Long bookId, Long patronId);
}
