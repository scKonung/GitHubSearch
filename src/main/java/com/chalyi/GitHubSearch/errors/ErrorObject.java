package com.chalyi.GitHubSearch.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorObject {
    private Integer status;
    private String Message;
}
