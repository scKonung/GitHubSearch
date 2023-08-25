package com.chalyi.GitHubSearch.services.impl;

import com.chalyi.GitHubSearch.dto.RepositoryDto;
import com.chalyi.GitHubSearch.errors.UserNotFoundException;
import com.chalyi.GitHubSearch.models.Repository;
import com.chalyi.GitHubSearch.services.BrancheService;
import com.chalyi.GitHubSearch.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    private final RestTemplate restTemplate;
    private final BrancheService brancheService;
    private final String apiBaseUrl = "https://api.github.com";

    @Autowired
    public RepositoryServiceImpl(RestTemplate restTemplate, BrancheService brancheService) {
        this.restTemplate = restTemplate;
        this.brancheService = brancheService;
    }

    public List<RepositoryDto> getAllByUsername(String username) {

        return Arrays.stream(findUserRepositories(username))
                .filter(repository -> !repository.isFork())
                .map(repository -> new RepositoryDto(
                        repository.getName() ,
                        repository.getOwner().getLogin(), brancheService.getAllBranches(username, repository.getName())))
                .collect(Collectors.toList());
    }

    private Repository[] findUserRepositories(String username){
        try {
            ResponseEntity<Repository[]> response = restTemplate.exchange(
                    apiBaseUrl + "/users/" + username + "/repos",
                    HttpMethod.GET,
                    null,
                    Repository[].class);

            return response.getBody();
        } catch (HttpClientErrorException.NotFound notFoundException) {
            throw new UserNotFoundException(username);
        }
    }
}
