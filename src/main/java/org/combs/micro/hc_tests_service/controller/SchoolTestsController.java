package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.SchoolTestResponse;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class SchoolTestsController {
    private final SchoolTestService schoolTestService;
    private final SchoolSubjectService schoolSubjectService;
    private final SchoolTestMapper testMapper;

}
