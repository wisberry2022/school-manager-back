package com.schm.schoolmanager.domain_common.controller;

import com.schm.schoolmanager.common.exception.StudentMigrateException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> conflictExceptionHandle(Exception e) {
        Map<String, Object> errMap = new HashMap<>();
        errMap.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMap);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> nullExceptionHandle(Exception e) {
        Map<String, Object> errMap = new HashMap<>();
        errMap.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errMap);
    }

    @ExceptionHandler({StudentMigrateException.class})
    public ResponseEntity<Object> migrateExceptionHandle(Exception e) {
        Map<String, Object> errMap = new HashMap<>();
        errMap.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMap);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> disableConvertGradeTypeHandle(Exception e) {
        Map<String, Object> errMap = new HashMap<>();
        errMap.put("message", "허용되지 않은 학년정보입니다. FIRST_YEAR, SECOND_YEAR, THIRD_YEAR만 입력 할 수 있습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMap);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> noSuchElementException(Exception e) {
        Map<String, Object> errMap = new HashMap<>();
        errMap.put("message", "존재하지 않는 정보입니다.");
        errMap.put("detail", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMap);
    }

}
