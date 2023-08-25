package com.chalyi.GitHubSearch.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public UserNotFoundException(String username) {
        super("User with username:"+username+" not found in GitHub");
    }


}
