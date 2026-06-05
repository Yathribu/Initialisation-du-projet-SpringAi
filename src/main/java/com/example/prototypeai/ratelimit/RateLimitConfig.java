package com.example.prototypeai.ratelimit;

import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.user.repository.IAiUserRepository;
import com.ratelimiterspringcore.ratelimit.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimitConfig implements RateLimiter {

    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    private static final long WINDOW_MS = 60_000;

    private final IAiUserRepository aiUserRepository;

    @Override
    public boolean isAuthorizedToPrompt(Long userId) {

        AiUser aiuser = aiUserRepository.findById(userId).orElse(null);
        int limit = aiuser.getUserSubscription().getSubscriptionType().getNumberOfRequestsPer60seconds();
        long now = System.currentTimeMillis();

        Window window = windows.computeIfAbsent(userId.toString(), k -> new Window(0, now));

        synchronized (window) {

            if (now - window.startTime > WINDOW_MS) {
                window.count = 0;
                window.startTime = now;
            }

            if (window.count >= limit) {
                return false;
            }

            window.count++;
            return true;
        }
    }

    private static class Window {
        int count;
        long startTime;

        Window(int count, long startTime) {
            this.count = count;
            this.startTime = startTime;
        }
    }

}
