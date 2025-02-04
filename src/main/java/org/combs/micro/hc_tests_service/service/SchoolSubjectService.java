package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.repository.SchoolSubjectRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolSubjectService {
    private final SchoolSubjectRepository repository;
    public Optional<SchoolSubject> getSubjectById(Long id){
        return repository.findById(id);
    }
    public List<SchoolSubject> getAllSubjects(){
        return repository.findAll();
    }
    public Optional<SchoolSubject> findByName(String name){
        return  repository.findSchoolSubjectByName(name);
    }

    public SchoolSubject saveSubject(SchoolSubject subject) {
        repository.save(subject);
        return subject;
    }

    public SchoolSubject getSubjectByName(String subjectName) {
        return repository.findSchoolSubjectByName(subjectName)
                .orElseThrow(()->new EntityNotFoundException(String.format("School subject with name: %s not found", subjectName)));
    }
}
