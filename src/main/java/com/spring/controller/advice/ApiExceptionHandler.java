package com.spring.controller.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.entity.ErrorDto;
import com.spring.entity.ErrorsDto;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public void handleNotFound(EntityNotFoundException enfEx) {
		log.debug("Entity not found", enfEx);

	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorsDto handleConstraintViolations(ConstraintViolationException e) {

		final List<ErrorDto> errors = e.getConstraintViolations()
                .stream()
                .map(v -> ErrorDto.builder()
                        .message(v.getMessage())
                        .path(v.getPropertyPath().toString())
                        .value(v.getInvalidValue().toString())
                        .build())
                .collect(Collectors.toList());

		log.debug("ConstraintViolations", errors);

		return new ErrorsDto(errors);

	}

}
