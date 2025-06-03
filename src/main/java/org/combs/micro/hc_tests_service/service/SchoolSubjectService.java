package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.exeptions.SchoolSubjectNotFoundException;
import org.combs.micro.hc_tests_service.repository.SchoolSubjectRepository;
import org.combs.micro.hc_tests_service.request.SchoolSubjectRequest;
import org.combs.micro.hc_tests_service.response.SchoolSubjectResponse;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolSubjectService {
    private final SchoolSubjectRepository repository;

    public SchoolSubject getSubjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new SchoolSubjectNotFoundException("School subject by" + id + " not found"));
    }
    public SchoolSubject getSubjectByName(String subjectName) {
        return repository.findSchoolSubjectByName(subjectName)
                .orElseThrow(() -> new SchoolSubjectNotFoundException("School subject by" + subjectName + " not found"));
    }

    public List<SchoolSubject> getAllSubjects() {
        return repository.findAll();
    }

    public SchoolSubject createSubject(SchoolSubjectRequest request) {

        if (repository.existsByName(request.getName())) {
            throw new SchoolSubjectNotFoundException("School with name " + request.getName() + " already exists");
        }

        SchoolSubject subject = SchoolSubject.builder().name(request.getName()).build();

        repository.save(subject);

        return subject;
    }

    public SchoolSubject updateSubject(Long id, SchoolSubjectRequest request) {
        SchoolSubject subject = getSubjectById(id);
        subject.setName(request.getName());

        if (repository.existsByName(subject.getName())) {
            throw new SchoolSubjectNotFoundException("School with name " + request.getName() + " already exists");
        }

        repository.save(subject);
        return subject;
    }
}
