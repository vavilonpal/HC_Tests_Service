package org.combs.micro.hc_tests_service.controller.statistic;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.combs.micro.hc_tests_service.response.StudentStatisticDTO;
import org.combs.micro.hc_tests_service.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
@Slf4j
public class StudentStatisticController {
    private final StatisticService statisticService;
    @GetMapping("/student/{id}")
    private ResponseEntity<StudentStatisticDTO> getStudentStatistic(@PathVariable Long id){
        StudentStatisticDTO statisticDTO = statisticService.getStudentStatistic(id);
        log.info(statisticDTO.toString());
        return ResponseEntity.ok(statisticDTO);
    }
}
