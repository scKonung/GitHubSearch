package com.chalyi.GitHubSearch.dto;

import com.chalyi.GitHubSearch.models.Branche;
import com.chalyi.GitHubSearch.models.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDto {
    private String name;
    private String ownerLogin;
    private List<BrancheDto> branches;
}
