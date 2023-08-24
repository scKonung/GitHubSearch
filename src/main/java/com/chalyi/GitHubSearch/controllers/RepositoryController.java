package com.chalyi.GitHubSearch.controllers;

import com.chalyi.GitHubSearch.dto.RepositoryDto;
import com.chalyi.GitHubSearch.dto.UsernameReuqest;
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
    @GetMapping("/get")
    public ResponseEntity<List<RepositoryDto>> getUserRepositories(@RequestBody UsernameReuqest username) {
        List<RepositoryDto> repositories = repositoryService.getAllByUsername(username.getUsername());
        return ResponseEntity.ok(repositories);
    }
}
