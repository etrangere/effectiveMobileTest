package com.em.test_em.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Demo", description = "Demo management APIs")
public class DemoController {

    @Operation(
        summary = "Get demo data",
        description = "Retrieve demo data from the API",
        tags = {"demo", "get"}
    )
    @GetMapping("/demo")
    public ResponseEntity<String> getDemoData() {
        return new ResponseEntity<>("Demo data", HttpStatus.OK);
    }
}
