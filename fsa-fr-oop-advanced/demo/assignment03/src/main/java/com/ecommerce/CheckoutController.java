package com.ecommerce;

public class CheckoutController {
    private Cart cart;
    private PaymentService paymentService;
    private OrderRepository orderRepository;

    public CheckoutController(Cart cart) {
        this.cart = cart;
        this.paymentService = new PaymentService();
        this.orderRepository = new OrderRepository();
    }

    // Problem: No way to undo operations. No transaction history.
    public void processAction(String action, Object... params) {
        if (action.equals("ADD_ITEM")) {
            cart.addItem((String) params[0], (Integer) params[1]);
        } else if (action.equals("REMOVE_ITEM")) {
            cart.removeItem((String) params[0]);
        } else if (action.equals("APPLY_COUPON")) {
            cart.applyCoupon((String) params[0]);
        } else if (action.equals("CHECKOUT")) {
            paymentService.charge(cart.getTotal());
            orderRepository.save(cart);
            cart.clear();
        } else {
            System.out.println("Unknown action: " + action);
        }
    }
}

class Cart {
    void addItem(String id, int qty) { System.out.println("Added " + qty + "x " + id); }
    void removeItem(String id) { System.out.println("Removed " + id); }
    void applyCoupon(String code) { System.out.println("Applied coupon: " + code); }
    double getTotal() { return 99.99; }
    void clear() { System.out.println("Cart cleared"); }
}

class PaymentService { 
    void charge(double amount) { System.out.println("Charged: $" + amount); } 
}

class OrderRepository { 
    void save(Cart cart) { System.out.println("Order saved"); } 
}
