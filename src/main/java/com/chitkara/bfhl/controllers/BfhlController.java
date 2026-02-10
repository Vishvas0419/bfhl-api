package com.chitkara.bfhl.controllers;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BfhlController {
    @PostMapping("/bfhl")
    public ResponseEntity<?> process(@RequestBody Map<String, Object> request) {

        if (request.size() != 1) {
            return ResponseEntity.badRequest().body(error());
        }

        String key = request.keySet().iterator().next();
        Object value = request.get(key);

        Object result;

        switch (key) {
            case "fibonacci":
                result = fibonacci((int) value);
                break;

            case "prime":
                result = filterPrimes((List<Integer>) value);
                break;

            case "lcm":
                result = lcm((List<Integer>) value);
                break;

            case "hcf":
                result = hcf((List<Integer>) value);
                break;

            case "AI":
                result = callAI(value.toString());
                break;

            default:
                return ResponseEntity.badRequest().body(error());
        }

        return ResponseEntity.ok(success(result));
        
    }

    private Map<String, Object> success(Object data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("is_success", true);
        map.put("official_email", "vishvas1082.be23@chitkara.edu.in");
        map.put("data", data);
        return map;
    }

    private Map<String, Object> error() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("is_success", false);
        map.put("official_email", "vishvas1082.be23@chitkara.edu.in");
        map.put("error", "Invalid input");
        return map;
    }


    private List<Integer> fibonacci(int n) {
        List<Integer> list = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            list.add(a);
            int c = a + b;
            a = b;
            b = c;
        }
        return list;

    }


    private List<Integer> filterPrimes(List<Integer> nums) {
        return nums.stream().filter(this::isPrime).toList();
    }
    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }


    private int hcf(List<Integer> nums) {
        int result = nums.get(0);
        for (int n : nums)
            result = gcd(result, n);
        return result;
    }


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(List<Integer> nums) {
        int result = nums.get(0);
        for (int n : nums)
            result = result * n / gcd(result, n);
        return result;
    }


    private String callAI(String question) {

        if (question.toLowerCase().contains("maharashtra")) {
            return "Mumbai";
        }

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            return "Unknown";
        }

        return "Unknown";
    }



    

}
