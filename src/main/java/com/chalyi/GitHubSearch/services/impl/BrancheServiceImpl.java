package com.chalyi.GitHubSearch.services.impl;

import com.chalyi.GitHubSearch.dto.BrancheDto;
import com.chalyi.GitHubSearch.dto.RepositoryDto;
import com.chalyi.GitHubSearch.models.Branche;
import com.chalyi.GitHubSearch.models.Repository;
import com.chalyi.GitHubSearch.services.BrancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrancheServiceImpl implements BrancheService {
    private final WebClient webClient;

    @Autowired
    public BrancheServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<BrancheDto> getAllBranches(String username, String repositoryName) {
        Flux<BrancheDto> brancheDtoFlux = webClient
                .get()
                .uri("/repos/{username}/{repositoryName}/branches", username, repositoryName)
                .retrieve()
                .bodyToFlux(Branche.class)
                .map(branche -> new BrancheDto(branche.getName(), branche.getCommit().getSha()));

        return brancheDtoFlux.collectList().block();
    }
}
