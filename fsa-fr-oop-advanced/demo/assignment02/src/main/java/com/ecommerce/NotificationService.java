package com.ecommerce;

public class NotificationService {
    
    public void sendOrderConfirmation(String orderId, String email) {
        // Problem: Different regions need different providers
        // Vietnam: uses Zalo, SMS via Viettel
        // US: uses Twilio, SendGrid
        // This class creates everything inline
        
        System.out.println("Creating SendGrid client...");
        System.out.println("Creating Twilio client...");
        System.out.println("Sending email via SendGrid to " + email);
        System.out.println("Sending SMS via Twilio");
    }
}
