package com.chalyi.GitHubSearch.services.impl;

import com.chalyi.GitHubSearch.dto.RepositoryDto;
import com.chalyi.GitHubSearch.models.Repository;
import com.chalyi.GitHubSearch.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    private final RestTemplate restTemplate;
    private final String apiBaseUrl = "https://api.github.com";

    @Autowired
    public RepositoryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RepositoryDto> getAllByUsername(String username) {
        String url = apiBaseUrl + "/users/" + username + "/repos";
        ResponseEntity<Repository[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Repository[].class);

        return Arrays.stream(response.getBody())
                .filter(repository -> !repository.isFork())
                .map(repository -> new RepositoryDto(repository.getName() , repository.getOwner().getLogin()))
                .collect(Collectors.toList());
    }
}
