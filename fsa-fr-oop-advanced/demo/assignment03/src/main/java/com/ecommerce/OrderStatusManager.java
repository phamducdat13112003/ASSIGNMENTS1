package com.ecommerce;

public class OrderStatusManager {
    private EmailService emailService;
    private SMSService smsService;
    private InventoryService inventoryService;
    private AnalyticsService analyticsService;

    public OrderStatusManager() {
        this.emailService = new EmailService();
        this.smsService = new SMSService();
        this.inventoryService = new InventoryService();
        this.analyticsService = new AnalyticsService();
    }

    public void updateStatus(String orderId, String newStatus) {
        System.out.println("Order " + orderId + " status changed to: " + newStatus);
        
        // Problem: Adding a new listener (e.g., PushNotificationService) requires modifying this method
        if (newStatus.equals("SHIPPED")) {
            emailService.send("Your order has been shipped!");
            smsService.send("Order shipped: " + orderId);
            analyticsService.track("order_shipped", orderId);
        } else if (newStatus.equals("DELIVERED")) {
            emailService.send("Your order has been delivered!");
            inventoryService.markDelivered(orderId);
            analyticsService.track("order_delivered", orderId);
        } else if (newStatus.equals("CANCELLED")) {
            emailService.send("Your order has been cancelled.");
            inventoryService.restoreStock(orderId);
            analyticsService.track("order_cancelled", orderId);
        }
    }
}

class EmailService { 
    void send(String msg) { System.out.println("EMAIL: " + msg); } 
}

class SMSService { 
    void send(String msg) { System.out.println("SMS: " + msg); } 
}

class InventoryService { 
    void markDelivered(String id) { System.out.println("INVENTORY: Delivered " + id); }
    void restoreStock(String id) { System.out.println("INVENTORY: Stock restored for " + id); }
}

class AnalyticsService { 
    void track(String event, String id) { System.out.println("ANALYTICS: " + event + " - " + id); } 
}
