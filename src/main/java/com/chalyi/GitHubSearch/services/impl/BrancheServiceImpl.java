package com.chalyi.GitHubSearch.services.impl;

import com.chalyi.GitHubSearch.dto.BrancheDto;
import com.chalyi.GitHubSearch.dto.RepositoryDto;
import com.chalyi.GitHubSearch.models.Branche;
import com.chalyi.GitHubSearch.models.Repository;
import com.chalyi.GitHubSearch.services.BrancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrancheServiceImpl implements BrancheService {
    private final RestTemplate restTemplate;
    private final String apiBaseUrl = "https://api.github.com";

    @Autowired
    public BrancheServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<BrancheDto> getAllBranches(String username, String repositoryName) {
        ResponseEntity<Branche[]> response = restTemplate.exchange(
                apiBaseUrl + "/repos/" + username + "/" + repositoryName + "/branches",
                HttpMethod.GET,
                null,
                Branche[].class);

        return Arrays.stream(response.getBody())
                .map(branche -> new BrancheDto(branche.getName(), branche.getCommit().getSha()))
                .collect(Collectors.toList());
    }
}
