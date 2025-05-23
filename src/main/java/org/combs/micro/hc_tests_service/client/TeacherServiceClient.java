package org.combs.micro.hc_tests_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("school")
public interface TeacherServiceClient {

    @GetMapping("/api/v1/teachers/{teacherId}/exists")
    public Boolean existById(@PathVariable Long teacherId);
}
