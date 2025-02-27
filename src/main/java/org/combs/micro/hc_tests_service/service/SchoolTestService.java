package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.exeptions.SchoolSubjectNotFoundException;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.repository.TestRepository;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.SchoolTestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolTestService {
    private final TestRepository testRepository;
    private final SchoolTestMapper schoolTestMapper;

    public SchoolTest getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new SchoolSubjectNotFoundException("Test not found"));
    }
    public List<SchoolTest> getAllTests() {
        return testRepository.findAll();
    }

    /*public Page<SchoolTest> getAllPageableTests(Pageable pageable, SchoolSubject subject, Integer complexity, String classLevel, Long teacherId) {
        return testRepository.findAllByFilters(pageable, subject, complexity, classLevel, teacherId);
    }*/

    public void createTest(SchoolTestRequest schoolTestRequest) {
        SchoolTest test = schoolTestMapper.toEntity(schoolTestRequest);
        testRepository.save(test);
    }

    public SchoolTest updateTest(Long testId, SchoolTestRequest schoolTestRequest) {
        SchoolTest test = getTestById(testId);
        schoolTestMapper.updateEntityFromRequest(schoolTestRequest, test);
        testRepository.save(test);
        return test;
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
