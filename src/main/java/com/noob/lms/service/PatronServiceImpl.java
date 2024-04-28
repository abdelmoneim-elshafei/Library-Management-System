package com.noob.lms.service;

import com.noob.lms.entity.Patron;
import com.noob.lms.repository.PatronRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {

    private final PatronRepo patronRepo;

    @Override
    public Patron addPatron(Patron patron) {
        return null;
    }

    @Override
    public Patron getPatronById(Long id) {
        return null;
    }

    @Override
    public List<Patron> getPatrons() {
        return List.of();
    }

    @Override
    public Patron updatePatron(Patron patron, Long id) {
        return null;
    }

    @Override
    public Patron deletePatron(Long id) {
        return null;
    }
}
