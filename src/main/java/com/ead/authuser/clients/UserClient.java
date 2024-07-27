package com.ead.authuser.clients;

import com.ead.authuser.dtos.CourseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        String REQUEST_URI = "http://localhost:8082";
        String url = REQUEST_URI + "/courses?userId=" + userId + "?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(":", ",");
        ParameterizedTypeReference<Page<CourseDto>> responseType = new ParameterizedTypeReference<Page<CourseDto>>() {};
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }
}
