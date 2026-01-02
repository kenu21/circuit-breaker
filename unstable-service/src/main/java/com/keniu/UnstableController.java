package com.keniu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/unstable")
public class UnstableController {

    private final Random random = new Random();

    private static final int ERROR_THRESHOLD = 30;
    private static final int SLOW_THRESHOLD = 60;
    private static final int SLOW_CALL_DURATION_MS = 1000; // 1 second

    @GetMapping
    public String call() {
        int randomValue = random.nextInt(100); // 100 %

        if (randomValue < ERROR_THRESHOLD) { // 30% chance to fail
            throw new RuntimeException("Service failed");
        }

        if (randomValue < SLOW_THRESHOLD) { // 30% chance to be slow
            try {
                Thread.sleep(SLOW_CALL_DURATION_MS);
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt();
            }
        }

        return "OK";
    }
}
