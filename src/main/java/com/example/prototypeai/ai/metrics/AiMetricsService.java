package com.example.prototypeai.ai.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter
@Service
public class AiMetricsService {

    private final MeterRegistry meterRegistry;
    private final Counter totalRequests;
    private final Counter cacheHits;
    private final Counter cacheMisses;
    private final Counter errors;

    public AiMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        this.totalRequests = Counter.builder("ai_requests_total")
                                    .description("Total number of requests")
                                    .register(meterRegistry);

        this.cacheHits = Counter.builder("ai_cache_hits_total")
                                .description("Total number of cache hits")
                                .register(meterRegistry);

        this.cacheMisses = Counter.builder("ai_cache_misses_total")
                                  .description("Total number of cache misses")
                                  .register(meterRegistry);

        this.errors = Counter.builder("ai_cache_errors_total")
                             .description("Total number of errors")
                             .register(meterRegistry);
    }

    public void incrementTotal() {
        totalRequests.increment();
    }

    public void incrementCacheHits() {
        cacheHits.increment();
    }

    public void incrementCacheMisses() {
        cacheMisses.increment();
    }

    public void incrementErrors() {
        errors.increment();
    }

}
