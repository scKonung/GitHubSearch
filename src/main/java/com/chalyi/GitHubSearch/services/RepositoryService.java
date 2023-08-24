package com.chalyi.GitHubSearch.services;

import com.chalyi.GitHubSearch.models.Repository;

import java.util.List;

public interface RepositoryService {
    List<Repository> getAllByUsername(String username);
}
