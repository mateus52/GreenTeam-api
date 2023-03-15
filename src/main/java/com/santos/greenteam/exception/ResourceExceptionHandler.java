package com.santos.greenteam.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequestException(BadRequestException e, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(HttpStatus.BAD_REQUEST,
				e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<StandardError> unauthorizedException(UnauthorizedException e, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new StandardError(HttpStatus.UNAUTHORIZED,
				e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<StandardError> forbiddenException(ForbiddenException e, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new StandardError(HttpStatus.FORBIDDEN,
				e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFoundException(NotFoundException e, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(HttpStatus.NOT_FOUND,
				e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> fileException(FileException e, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(HttpStatus.BAD_REQUEST,
				e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException e, HttpServletRequest request){
		
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StandardError err = new StandardError(System.currentTimeMillis(), code.value(), "Erro Amazon Service", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(code).body(err);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClientException(AmazonClientException e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro S3", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<StandardError> internalServerErrorException(InternalServerErrorException e, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardError(HttpStatus.INTERNAL_SERVER_ERROR,
				e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandardError> runTimeException(RuntimeException e, HttpServletRequest request){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardError(HttpStatus.INTERNAL_SERVER_ERROR,
				e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handlerException(Exception e, Object body, HttpStatus httpStatus,
			HttpHeaders headers, WebRequest request){
		
		return ResponseEntity.status(httpStatus).body(new StandardError(httpStatus,
				e.getMessage(), request.getContextPath()));
	}

}
