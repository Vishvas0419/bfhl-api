package com.chitkara.bfhl.controllers;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("is_success", true);
        response.put("official_email", "vishvas1082.be23@chitkara.edu.in");
        return response;
    }
}
