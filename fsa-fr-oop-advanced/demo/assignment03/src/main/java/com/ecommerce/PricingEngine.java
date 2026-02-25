package com.ecommerce;

public class PricingEngine {
    private String customerType;

    public void setCustomerType(String type) {
        this.customerType = type;
    }

    // Problem: Adding new pricing tier (e.g., ENTERPRISE) requires modifying this method
    public double calculatePrice(double basePrice, int quantity) {
        double price = basePrice * quantity;
        
        if (customerType.equals("REGULAR")) {
            return price;
        } else if (customerType.equals("MEMBER")) {
            return price * 0.95; // 5% discount
        } else if (customerType.equals("VIP")) {
            return price * 0.85; // 15% discount
        } else if (customerType.equals("WHOLESALE")) {
            if (quantity >= 100) {
                return price * 0.70; // 30% off for bulk
            } else if (quantity >= 50) {
                return price * 0.80; // 20% off
            } else {
                return price * 0.90; // 10% off
            }
        } else {
            return price;
        }
    }
}
