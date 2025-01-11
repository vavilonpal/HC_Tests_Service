package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Long> {
    Optional<SchoolSubject> findSchoolSubjectByName(String name);
}
