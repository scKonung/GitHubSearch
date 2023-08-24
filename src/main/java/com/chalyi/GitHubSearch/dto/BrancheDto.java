package com.chalyi.GitHubSearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrancheDto {
    private String name;
    private String commitSha;
}
