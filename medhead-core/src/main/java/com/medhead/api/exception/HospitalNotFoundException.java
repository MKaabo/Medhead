package com.medhead.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Hospital not found")
public class HospitalNotFoundException extends RuntimeException
{
}
