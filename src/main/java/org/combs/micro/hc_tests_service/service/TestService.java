package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;


    public boolean existsById(Long id){
        return testRepository.existsById(id);
    }
    public List<SchoolTest> getAllTests() {
        return testRepository.findAll();
    }

    public Optional<SchoolTest> getTestById(Long id) {
        return testRepository.findById(id);
    }

    public SchoolTest createTest(SchoolTest schoolTest) {
        return testRepository.save(schoolTest);
    }

    public SchoolTest updateTest(Long id, SchoolTest updatedSchoolTest) {
        return testRepository.findById(id).map(test -> {
            test.setTitle(updatedSchoolTest.getTitle());
            test.setTeacherId(updatedSchoolTest.getTeacherId());
            test.setType(updatedSchoolTest.getType());
            test.setSchoolSubject(updatedSchoolTest.getSchoolSubject());
            test.setComplexity(updatedSchoolTest.getComplexity());
            test.setClassLevel(updatedSchoolTest.getClassLevel());
            test.setDescription(updatedSchoolTest.getDescription());
            test.setDuration(updatedSchoolTest.getDuration());
            return testRepository.save(test);
        }).orElseThrow(() -> new RuntimeException("SchoolTest not found"));
    }

    public List<SchoolTest> getTestsBySchoolSubject(SchoolSubject schoolSubject) {
        return testRepository.findAllBySchoolSubject(schoolSubject);
    }
    public List<SchoolTest> getTestsByComplexity(Complexity complexity) {
        return testRepository.findAllByComplexity(complexity);
    }
    public List<SchoolTest> getTestsByClassLevel(Integer classLevel) {
        return testRepository.findAllByClassLevel(classLevel);
    }
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
