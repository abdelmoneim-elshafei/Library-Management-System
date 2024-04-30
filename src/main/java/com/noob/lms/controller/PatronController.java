package com.noob.lms.controller;

import com.noob.lms.entity.Patron;
import com.noob.lms.service.PatronService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping
    @Operation(summary = "Get all patrons", description = "Retrieve a list of all patrons.")
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.getPatrons();
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patron by ID", description = "Retrieve details of a specific patron by ID.")
    public ResponseEntity<Patron> getPatronById(@Parameter(description = "Patron ID", example = "1", in = ParameterIn.PATH) @PathVariable Long id) {
        Patron patron = patronService.getPatronById(id);
        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new patron", description = "Add a new patron to the system.")
    public ResponseEntity<?> addPatron(@Parameter(description = "Patron object to add", schema = @Schema(implementation = Patron.class)) @Valid @RequestBody Patron patron) {

        Patron newPatron = patronService.addPatron(patron);
        return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update patron", description = "Update an existing patron's information.")
    public ResponseEntity<Patron> updatePatron( @Parameter(description = "Patron ID", example = "1", in = ParameterIn.PATH) @PathVariable Long id,
                                                @Parameter(description = "Updated patron information",schema = @Schema(implementation = Patron.class)) @Valid @RequestBody Patron patron) {
        Patron updatedPatron = patronService.updatePatron(patron,id);
        return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patron", description = "Remove a patron from the system.")
    public ResponseEntity<Void> deletePatron(@Parameter(description = "Patron ID", example = "1", in = ParameterIn.PATH) @PathVariable Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
