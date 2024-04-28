package com.noob.lms.repository;

import com.noob.lms.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepo extends JpaRepository<Patron, Long> {

}
