package com.chalyi.GitHubSearch.services.impl;

import com.chalyi.GitHubSearch.dto.BrancheDto;
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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    private final WebClient webClient;
    private final BrancheService brancheService;

    @Autowired
    public RepositoryServiceImpl(WebClient webClient, BrancheService brancheService) {
        this.webClient = webClient;
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
            return webClient
                    .get()
                    .uri("/users/{username}/repos", username)
                    .retrieve()
                    .bodyToMono(Repository[].class)
                    .block(); // Block to get the result
        } catch (WebClientResponseException.NotFound notFoundException) {
            throw new UserNotFoundException(username);
        }
    }
}
