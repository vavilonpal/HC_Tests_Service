package org.combs.micro.hc_tests_service.controller;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SchoolSubjectController {
    private final SchoolSubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SchoolSubject>> getAllSubjects() {
        List<SchoolSubject> subjects = subjectService.getAllSubjects();
        if (subjects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolSubject> getSubject(@PathVariable Long id) {
        return subjectService.getSubjectById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<SchoolSubject> saveSubject(@RequestBody SchoolSubject subject) {
        subjectService.saveSubject(subject);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolSubject> updateResult(@PathVariable Long id, @RequestBody SchoolSubject updatedSubject) {
        return subjectService.getSubjectById(id)
                .map(oldSubject -> {
                    oldSubject.setName(updatedSubject.getName());
                    SchoolSubject subject = subjectService.saveSubject(oldSubject);
                    return ResponseEntity.ok(subject);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tests")
}
