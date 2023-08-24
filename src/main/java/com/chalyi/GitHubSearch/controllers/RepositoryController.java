package com.chalyi.GitHubSearch.controllers;

import com.chalyi.GitHubSearch.models.Repository;
import com.chalyi.GitHubSearch.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RepositoryController {
    private final RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    // GET request to fetch repositories for a user
    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<Repository>> getUserRepositories(@PathVariable String username) {
        List<Repository> repositories = repositoryService.getAllByUsername(username);
        return ResponseEntity.ok(repositories);
    }
}
