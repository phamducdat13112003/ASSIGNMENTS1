# **PROJECT ASSIGNMENTS: E-COMMERCE ORDER MANAGEMENT SYSTEM**

**Context:**
You are building the backend for an e-commerce platform. The system handles products, shopping carts, orders, payments, and notifications. The architecture must be maintainable, testable, and follow Clean Architecture principles.

**Source Code:** All initial source code is located in `demo/` directory.

## **ASSIGNMENT 01: SOLID PRINCIPLES REFACTORING**

**Objective:** Refactor a legacy "God Class" order processing system to adhere to SOLID principles.

**Source Code:** `demo/assignment01/`

### **Problems**

1. The `OrderService` class handles validation, pricing, discounts, payment processing, stock management, notifications (email, SMS), logging, and analytics all in one place.
2. The discount logic uses `if-else` chains based on product category. Adding a new discount rule (e.g., "Black Friday 20% off everything") requires modifying this class.
3. The payment processing logic is embedded inside `createOrder()`. Adding a new payment method (e.g., Crypto) requires editing the same method.
4. Notification logic (email, SMS, logging) is scattered throughout multiple methods. Changing the email provider means hunting through the entire class.
5. The class uses `Object[]` arrays for data, making it impossible to use type safety or proper domain modeling.

**Action:** Analyze the problems, write down your solutions reasoning to `README.md` and refactor the code.

## **ASSIGNMENT 02: CREATIONAL DESIGN PATTERNS**

**Objective:** Apply Creational Design Patterns to improve object creation in an e-commerce system.

**Source Code:** `demo/assignment02/`

### **Problems**

1. The `AppConfig` allows multiple instantiations. In a multi-threaded server, two requests could create two config instances with potentially different values.
2. The `PaymentProcessorFactory` uses `if-else` chains. Adding support for a new gateway (e.g., ZaloPay) requires editing this class.
3. Different regions (Vietnam vs US) need different notification providers. There's no way to create a "family" of region-specific services together.
4. Creating an `Order` is confusing with 13 constructor parameters. It's unclear what `true, true, null` means without checking the signature.

**Action:** Analyze the problems, write down your solutions reasoning to `README.md` and refactor the code.

## **ASSIGNMENT 03: STRUCTURAL & BEHAVIORAL PATTERNS**

**Objective:** Apply Structural and Behavioral patterns for flexible e-commerce business logic.

**Source Code:** `demo/assignment03/`

### **Problems**

1. The `OrderStatusManager` is tightly coupled to Email, SMS, Inventory, and Analytics services. Adding push notifications or a webhook service requires modifying this class.
2. In `CheckoutController`, actions are executed immediately with no history. If the user wants to undo "Apply Coupon", the system cannot reverse it.
3. The `PricingEngine` uses a long `if-else` chain for different customer types. Adding a new tier or changing the VIP discount percentage means editing and re-testing this entire class.
4. The `LegacyPaymentGateway` uses VND currency and returns integer status codes. Your modern system uses USD and expects `boolean` responses. You cannot modify the legacy code.
5. (Bonus) You want to add "Express Shipping Fee" and "Gift Wrapping Fee" to cart calculations. Using inheritance leads to class explosion.

**Action:** Analyze the problems, write down your solutions reasoning to `README.md` and refactor the code.
