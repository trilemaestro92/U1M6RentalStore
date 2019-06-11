package com.company.U1M6Summative.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class RentalStoreExecptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<VndErrors> doMotoValidationError(MethodArgumentNotValidException e, WebRequest request){
        //BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<VndErrors.VndError> vndErrorList = new ArrayList<>();
        for(FieldError error:fieldErrors){
            VndErrors.VndError vndError = new VndErrors.VndError(request.toString(),error.getDefaultMessage());
            vndErrorList.add(vndError);
        }
        VndErrors vndErrors = new VndErrors(vndErrorList);
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(vndErrors,HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }
}
