package org.combs.micro.hc_tests_service.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.combs.micro.hc_tests_service.repository.ResultRepository;
import org.combs.micro.hc_tests_service.repository.TestRepository;
import org.combs.micro.hc_tests_service.repository.UserRepository;
import org.combs.micro.hc_tests_service.response.StudentStatisticDTO;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticService {
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
   public StudentStatisticDTO getStudentStatistic(Long studentId){


       if(!userRepository.existsById(studentId)){
            throw new  EntityNotFoundException("User not found by id: "+studentId);
       }

       Integer score = getSumOfScore(studentId);
       Integer rankScore = getSumOfRankScore(studentId);
       Integer solvedTestAmount = getStudentTestSolvedTestsAmount(studentId);

       return new StudentStatisticDTO(
               score,
               rankScore,
               solvedTestAmount);
   }




    public Integer getStudentTestSolvedTestsAmount(Long studentId){
        return resultRepository.getStudentResultsAmount(studentId);
    }
    public Integer getSumOfRankScore(Long studentId){
        return resultRepository.getSumOfRankScore(studentId);
    }
    public Integer getSumOfScore(Long studentId){
        return resultRepository.getSumOfScore(studentId);
    }
}
