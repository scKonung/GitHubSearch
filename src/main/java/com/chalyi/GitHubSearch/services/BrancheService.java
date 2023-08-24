package com.chalyi.GitHubSearch.services;

import com.chalyi.GitHubSearch.dto.BrancheDto;
import com.chalyi.GitHubSearch.models.Branche;

import java.util.List;

public interface BrancheService {
    List<BrancheDto> getAllBranches(String username, String repositoryName);
}
