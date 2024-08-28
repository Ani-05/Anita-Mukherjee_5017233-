package com.example.bookstoreapi.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    public CustomMetrics(MeterRegistry meterRegistry) {
        // Create a custom gauge
        meterRegistry.gauge("custom_metric_example", this, CustomMetrics::getCustomMetricValue);
    }

    public double getCustomMetricValue() {
        // Logic to return the custom metric value
        return Math.random() * 100; // Example value
    }
}