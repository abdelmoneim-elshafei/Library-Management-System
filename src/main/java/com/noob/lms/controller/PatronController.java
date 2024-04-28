package com.noob.lms.controller;

import com.noob.lms.entity.Patron;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patrons")
public class PatronController {
    @GetMapping
    public ResponseEntity<?> getPatrons() {

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id) {

        return null;
    }

    @PostMapping
    public ResponseEntity<?> addPatron(@RequestBody Patron patron) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@RequestBody Patron patron, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatron(@PathVariable Long id){
        return null;
    }
}
