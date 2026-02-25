package com.ecommerce;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Broken singleton
        AppConfig config1 = new AppConfig();
        AppConfig config2 = new AppConfig();
        System.out.println("Same config? " + (config1 == config2)); // false!

        // Hard-coded factory
        Object processor = PaymentProcessorFactory.createProcessor("STRIPE", "merchant_123");

        // Confusing constructor
        Order order = new Order(
            "ORD-001", "CUST-001", "john@email.com", "+84123456789",
            Arrays.asList("P001", "P002"),
            "123 Main St", "456 Billing St", "CREDIT_CARD",
            "SUMMER20", "Happy Birthday!", true, true, "Leave at door"
        );
        System.out.println(order);
    }
}
