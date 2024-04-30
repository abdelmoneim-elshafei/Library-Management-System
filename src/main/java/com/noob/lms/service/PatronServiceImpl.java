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
        return patronRepo.save(patron);
    }

    @Override
    public Patron getPatronById(Long id) {
        return patronRepo.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
    }

    @Override
    public List<Patron> getPatrons() {
        return patronRepo.findAll();
    }

    @Override
    public Patron updatePatron(Patron patron, Long id) {
        Patron p = patronRepo.findById(id).orElseThrow(() ->new RuntimeException("Not Found"));
        p.setEmail(patron.getEmail());
        p.setName(patron.getName());
        p.setPhoneNumber(patron.getPhoneNumber());
        return patronRepo.save(p);
    }

    @Override
    public Patron deletePatron(Long id) {
        Patron p = patronRepo.findById(id).orElseThrow(() ->new RuntimeException("Not Found"));
        patronRepo.deleteById(id);
        return p;
    }
}
