package com.chalyi.GitHubSearch.controllers;

import com.chalyi.GitHubSearch.dto.RepositoryDto;
import com.chalyi.GitHubSearch.dto.UsernameReuqest;
import com.chalyi.GitHubSearch.errors.UnsupportedAcceptXmlException;
import com.chalyi.GitHubSearch.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RepositoryController {
    private final RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    // GET request to fetch repositories for a user
    @GetMapping("/get")
    public ResponseEntity<List<RepositoryDto>> getUserRepositories(@RequestBody UsernameReuqest username,
                                                                   @RequestHeader(value = "Accept") String acceptHeader) {
        System.out.println(acceptHeader);
        if(acceptHeader.equals("application/xml"))
            throw new UnsupportedAcceptXmlException();
        List<RepositoryDto> repositories = repositoryService.getAllByUsername(username.getUsername());
        return ResponseEntity.ok(repositories);
    }
}
