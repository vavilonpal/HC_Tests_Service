package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.exeptions.notFound.SchoolSubjectNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.notFound.TeacherNotFoundException;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.repository.TestRepository;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.springframework.stereotype.Service;

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

    private void checkTeacherExist(Long teacherId) {
        if (teacherId == null) {
            throw new TeacherNotFoundException("ID учителя равно null ");
        }
        //todo
        //boolean teacherExists = teacherServiceClient.existById(teacherId);


    }

    public SchoolTest createTest(SchoolTestRequest schoolTestRequest) {
        //checkTeacherExist(schoolTestRequest.getTeacherId());

        SchoolTest test = schoolTestMapper.toCreateEntity(schoolTestRequest);
        return testRepository.save(test);
    }

    public SchoolTest updateTest(Long testId, SchoolTestRequest schoolTestRequest) {
        //checkTeacherExist(schoolTestRequest.getTeacherId());

        SchoolTest test = getTestById(testId);
        schoolTestMapper.toUpdateEntity(schoolTestRequest, test);

        return testRepository.save(test);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
