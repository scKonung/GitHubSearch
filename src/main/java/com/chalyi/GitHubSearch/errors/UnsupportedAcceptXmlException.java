package com.chalyi.GitHubSearch.errors;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public final class UnsupportedAcceptXmlException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;

    public UnsupportedAcceptXmlException(){
        super("Unsupported xml type. Only JSON is supported.");
    }

}
