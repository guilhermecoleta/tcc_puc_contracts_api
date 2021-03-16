package puc.tcc.contracts.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ContractsApiException extends Exception {

    private final HttpStatus statusCode;
    private final String field;
    private final String message;

}
