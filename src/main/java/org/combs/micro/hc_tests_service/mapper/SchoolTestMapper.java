package org.combs.micro.hc_tests_service.mapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.SchoolTestResponse;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class SchoolTestMapper {
    public SchoolTest toEntity(SchoolTestRequest request){
        return  SchoolTest.builder()
                .build();
    }

    public SchoolTestResponse toResponse(SchoolTest test){
        return SchoolTestResponse.builder()
                .build();
    }

    public void updateEntityFromRequest(SchoolTestRequest request, SchoolTest test){


    }


}
