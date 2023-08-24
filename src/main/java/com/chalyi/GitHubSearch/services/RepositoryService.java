package com.chalyi.GitHubSearch.services;

import com.chalyi.GitHubSearch.dto.RepositoryDto;
import com.chalyi.GitHubSearch.models.Repository;

import java.util.List;

public interface RepositoryService {
    List<RepositoryDto> getAllByUsername(String username);
}
