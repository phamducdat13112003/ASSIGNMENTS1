package com.ecommerce;

public class PaymentProcessorFactory {

    public static Object createProcessor(String type, String merchantId) {
        if (type.equals("STRIPE")) {
            return new StripeProcessor(merchantId);
        } else if (type.equals("PAYPAL")) {
            return new PayPalProcessor(merchantId);
        } else if (type.equals("SQUARE")) {
            return new SquareProcessor(merchantId);
        } else if (type.equals("MOMO")) {
            return new MomoProcessor(merchantId);
        } else if (type.equals("VNPAY")) {
            return new VNPayProcessor(merchantId);
        } else {
            throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}

class StripeProcessor { 
    String id; 
    StripeProcessor(String id) { this.id = id; } 
}

class PayPalProcessor { 
    String id; 
    PayPalProcessor(String id) { this.id = id; } 
}

class SquareProcessor { 
    String id; 
    SquareProcessor(String id) { this.id = id; } 
}

class MomoProcessor { 
    String id; 
    MomoProcessor(String id) { this.id = id; } 
}

class VNPayProcessor { 
    String id; 
    VNPayProcessor(String id) { this.id = id; } 
}
