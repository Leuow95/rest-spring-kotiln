package br.com.leomaia.exceptions

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.FORBIDDEN)
class InvalidJwtAuthException(exception: String?): AuthenticationException(exception){
    

}