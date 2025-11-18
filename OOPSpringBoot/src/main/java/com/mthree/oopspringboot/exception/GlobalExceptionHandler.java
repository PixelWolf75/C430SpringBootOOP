package com.mthree.oopspringboot.exception;

import com.google.genai.errors.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //    @ExceptionHandler(CategoryNotFoundException.class)
    //    public ResponseEntity<?> handleCategoryNotFound(CategoryNotFoundException e) {
    //        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    //    }
   @ExceptionHandler(ServerException.class)
   public ResponseEntity<String> handleGeminiOverloadE(ServerException e){
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Gemini model is overloaded for now. Please try again later.");
   }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception e) {
        return
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. " + e.getMessage());
    }

}
