package org.combs.micro.hc_tests_service.controller;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.response.SchoolSubjectResponse;
import org.combs.micro.hc_tests_service.request.SchoolSubjectRequest;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SchoolSubjectController {
    private final SchoolSubjectService subjectService;

}
