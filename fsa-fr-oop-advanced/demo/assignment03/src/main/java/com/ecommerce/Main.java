package com.ecommerce;

public class Main {
    public static void main(String[] args) {
        // Order status with hard-coded listeners
        OrderStatusManager statusManager = new OrderStatusManager();
        statusManager.updateStatus("ORD-001", "SHIPPED");
        System.out.println();
        
        // Checkout without undo
        Cart cart = new Cart();
        CheckoutController checkout = new CheckoutController(cart);
        checkout.processAction("ADD_ITEM", "P001", 2);
        checkout.processAction("APPLY_COUPON", "SUMMER20");
        // Can't undo!
        System.out.println();
        
        // Pricing with if-else chain
        PricingEngine pricing = new PricingEngine();
        pricing.setCustomerType("VIP");
        System.out.println("VIP Price: $" + pricing.calculatePrice(100.0, 5));
        
        pricing.setCustomerType("WHOLESALE");
        System.out.println("Wholesale Price (100 qty): $" + pricing.calculatePrice(100.0, 100));
    }
}
