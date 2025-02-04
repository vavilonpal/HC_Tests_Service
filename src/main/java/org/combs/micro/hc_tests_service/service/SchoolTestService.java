package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
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
@Transactional
@RequiredArgsConstructor
public class SchoolTestService {
    private final TestRepository testRepository;
    private final SchoolTestMapper testMapper;


    public boolean existsById(Long id) {
        return testRepository.existsById(id);
    }

    public Page<SchoolTestResponse> getTests(Pageable pageable) {
        return testRepository.findAll(pageable)
                .map(testMapper::toResponse);
    }

    public List<SchoolTestResponse> getAllTests() {
        return testRepository.findAll().stream()
                .map(testMapper::toResponse)
                .toList();
    }

    public Page<SchoolTestResponse> getAllPageableTests(Pageable pageable, SchoolSubject subject, Integer complexity, String classLevel, Long teacherId) {
        return testRepository.findAllByFilters(pageable, subject, complexity, classLevel, teacherId)
                .map(testMapper::toResponse);
    }

    public SchoolTest getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Test not found"));
    }

    public void createTest(SchoolTestRequest schoolTestRequest) {
        SchoolTest test = testMapper.toEntity(schoolTestRequest);
        testRepository.save(test);
    }

    public SchoolTestResponse updateTest(Long testId, SchoolTestRequest schoolTestRequest) {
        SchoolTest test = getTestById(testId);
        testMapper.updateEntityFromRequest(schoolTestRequest, test);
        testRepository.save(test);
        return testMapper.toResponse(test);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
