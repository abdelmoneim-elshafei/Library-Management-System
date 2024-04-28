package com.noob.lms.service;

import com.noob.lms.entity.Patron;

import java.util.List;

public interface PatronService {
    public Patron addPatron(Patron patron);
    public Patron getPatronById(Long id);
    public List<Patron> getPatrons();
    public Patron updatePatron(Patron patron, Long id);
    public Patron deletePatron(Long id);
}
