package com.chalyi.GitHubSearch.dto;

import com.chalyi.GitHubSearch.models.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDto {
    private String name;
    private String ownerLogin;
}
