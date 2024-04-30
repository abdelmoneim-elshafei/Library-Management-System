package com.noob.lms.service;

import com.noob.lms.entity.Book;
import com.noob.lms.entity.BorrowingRecord;
import com.noob.lms.entity.Patron;
import com.noob.lms.exception.ConflictException;
import com.noob.lms.exception.ResourceNotFoundException;
import com.noob.lms.repository.BookRepo;
import com.noob.lms.repository.BorrowingRecordRepo;
import com.noob.lms.repository.PatronRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    private final BorrowingRecordRepo borrowingRecordRepository;

    private final BookRepo bookRepository;

    private final PatronRepo patronRepository;

    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron", "id", patronId));

        // Check if there are available copies of the book
        if (book.getCopiesAvailable() > 0) {
            // Create a new borrowing record
            if (!isBorrowIt(bookId, patronId)) {
                BorrowingRecord borrowingRecord = new BorrowingRecord();
                borrowingRecord.setBook(book);
                borrowingRecord.setPatron(patron);
                borrowingRecord.setBorrowingDate(LocalDate.now());

                // Save the borrowing record
                borrowingRecordRepository.save(borrowingRecord);

                // Update the number of available copies
                book.setCopiesAvailable(book.getCopiesAvailable() - 1);
                bookRepository.save(book);
            } else {
                throw new ConflictException("You can't borrow the same book twice ");
            }
        } else {
            throw new ConflictException("No available copies of the book.");
        }
    }


    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron", "id", patronId));
        // Find the borrowing record for the book and patron
        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookAndPatronAndReturnDateIsNull(book, patron)
                .orElseThrow(() -> new ResourceNotFoundException("No active borrowing record found for the book and patron."));
        // Update return date
        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);

        // Update the number of available copies
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);

    }


    private Boolean isBorrowIt(Long bookId, Long patronId) {
        return borrowingRecordRepository.existsByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
    }

}
