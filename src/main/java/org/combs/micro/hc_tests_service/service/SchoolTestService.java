package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.client.TeacherServiceClient;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.exeptions.SchoolSubjectNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.TeacherNotFoundException;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.repository.TestRepository;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolTestService {
    private final TestRepository testRepository;
    private final SchoolTestMapper schoolTestMapper;
    private TeacherServiceClient teacherServiceClient;

    public SchoolTest getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new SchoolSubjectNotFoundException("Test not found"));
    }
    public List<SchoolTest> getAllTests() {
        return testRepository.findAll();
    }

    private void checkTeacherExist(Long teacherId){
        boolean teacherExists = teacherServiceClient.existById(teacherId);
        if (!teacherExists) {
            throw new TeacherNotFoundException("Учитель с ID " + teacherId + " не найден");
        }
    }
    public SchoolTest createTest(SchoolTestRequest schoolTestRequest) {
        checkTeacherExist(schoolTestRequest.getTeacherId());

        SchoolTest test = schoolTestMapper.toCreateEntity(schoolTestRequest);
        return testRepository.save(test);
    }
    public SchoolTest updateTest(Long testId, SchoolTestRequest schoolTestRequest) {
        checkTeacherExist(schoolTestRequest.getTeacherId());

        SchoolTest test = getTestById(testId);
        schoolTestMapper.toUpdateEntity(schoolTestRequest, test);

        return testRepository.save(test);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
