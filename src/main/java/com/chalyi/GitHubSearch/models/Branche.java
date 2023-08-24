package com.chalyi.GitHubSearch.models;

import lombok.Data;

@Data
public class Branche {
    private String name;
    private Commit commit;
}
