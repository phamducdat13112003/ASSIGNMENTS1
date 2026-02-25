# **SELF-LEARNING GUIDE: OOP ADVANCED (INSTRUCTION)**

**Target Audience:** Intern Developer/Data Engineer

**Format:** Self-learning (Independent Research) & Workshop & Evaluation

**Duration:** 9 days (self-learning + assignment + workshop) and 5 days evaluation (theory + practice)

## **I. LEARNING ROADMAP & KNOWLEDGE SCOPE**

### **DAY 1+2: ADVANCED OOP CONCEPTS**

**Goal:** Understand advanced object-oriented concepts and principles to design robust systems.

#### **1. Scope (Key Knowledge Areas)**

- **Association & Aggregation & Composition:** Understanding the "has-a" relationships and ownership levels.
- **Coupling & Cohesion:** Aiming for loose coupling and high cohesion.
- **Composition over Inheritance:** Why favors object composition over class inheritance.
- **Interface over Abstract Class:** Preferring interfaces to define types.
- **SOLID Principles:**
  - **S**ingle Responsibility Principle (SRP)
  - **O**pen/Closed Principle (OCP)
  - **L**iskov Substitution Principle (LSP)
  - **I**nterface Segregation Principle (ISP)
  - **D**ependency Inversion Principle (DIP)

#### **2. Instruction: How to leverage AI/Web**

Sample prompts:

- **SOLID:** "Explain SOLID principles using a 'Coffee Shop' analogy. Why is a 'RobotBarista' handling payments a violation of SRP?"
- **Composition vs Inheritance:** "Why is 'Composition over Inheritance' preferred? Give a Java example where Inheritance causes specific problems (fragile base class)."
- **Interface vs Abstract Class:** "In Java 17+, interfaces can have private and default methods. When should I ever use an Abstract Class now?"
- **Coupling:** "What is Tight Coupling? Rewrite this tightly coupled Java code [paste code] to use Dependency Injection."
- **LSP:** "Explain Liskov Substitution Principle. Why does 'Square extends Rectangle' break this principle?"

_Action:_ Complete **Assignment 01** in [ASSIGNMENTS.md](./ASSIGNMENTS.md).

#### **Refactor theo từng nhóm vấn đề**

**Vấn đề 1 – OrderService làm quá nhiều (SRP)**

- **Hiện tại:** Một class làm: validate, tính giá, giảm giá, thanh toán, cập nhật tồn kho, gửi email/SMS, log, analytics.
- **Cần làm:** Tách thành nhiều lớp/interface có một trách nhiệm rõ ràng, ví dụ:
  - Validator (kiểm tra sản phẩm, tồn kho).
  - Pricing / Discount (tính giá, áp dụng giảm giá).
  - Payment (xử lý thanh toán).
  - Stock (cập nhật tồn kho).
  - Notification (email, SMS).
  - Logging, Analytics (hoặc tách riêng).
- **OrderService** chỉ điều phối các thành phần trên (orchestrator), không chứa logic chi tiết của từng việc.

**Vấn đề 2 – Discount if-else (OCP)**

- **Hiện tại:** Trong `createOrder()` có if-else theo category (ELECTRONICS, CLOTHING, FOOD) để giảm giá.
- **Cần làm:** Áp dụng Strategy (hoặc Chain of Responsibility):
  - Interface kiểu `DiscountStrategy` hoặc `DiscountRule` (nhận product/price, trả về giá sau giảm).
  - Mỗi rule (Electronics, Clothing, Black Friday 20%, v.v.) là một class implement interface đó.
  - Thêm rule mới = thêm class mới, không sửa OrderService hay một “discount master” class (mở rộng, đóng sửa – OCP).

**Vấn đề 3 – Payment nhúng trong createOrder (OCP)**

- **Hiện tại:** Trong `createOrder()` có if-else theo paymentMethod (CREDIT_CARD, PAYPAL, BANK_TRANSFER) để tính phí và “process payment”.
- **Cần làm:** Tách payment thành Strategy (hoặc tương tự):
  - Interface kiểu `PaymentProcessor` (hoặc `PaymentHandler`) với method xử lý thanh toán (và có thể tính phí).
  - Mỗi phương thức (CreditCard, PayPal, BankTransfer, sau này Crypto) là một class implement.
  - Thêm phương thức mới = thêm class mới, không sửa logic bên trong `createOrder()` (OCP).

**Vấn đề 4 – Notification rải rác (SRP, DIP)**

- **Hiện tại:** Gửi email/SMS/log nằm trực tiếp trong `createOrder()`, `cancelOrder()`, `shipOrder()` (System.out.println giả lập).
- **Cần làm:**
  - Tách Notification (và có thể Logging) thành service/interface riêng.
  - Order flow chỉ gọi kiểu: `notificationService.notifyOrderConfirmed(...)`, `notificationService.notifyShipped(...)`, v.v.
  - Đổi provider (email/SMS) = đổi implementation của interface, không sửa OrderService (DIP: phụ thuộc abstraction).

**Vấn đề 5 – Object[] (type safety, domain modeling)**

- **Hiện tại:** `orders`, `products`, `orderItems` dùng `Object[]` (index 0 = id, 1 = name, …), dễ lỗi và khó đọc.
- **Cần làm:** Đưa vào domain model rõ ràng:
  - Class **Product** (id, name, price, stock, category).
  - Class **OrderItem** (productId, productName, price hoặc tham chiếu Product).
  - Class **Order** (orderId, customerId, items, total, status, shippingAddress).
  - Dùng `List<Product>`, `List<Order>`, `List<OrderItem>` thay cho `List<Object[]>`.

### **DAY 3: WORKSHOP**

**Activity:** Present and review Assignment 01 solutions. Discuss refactoring decisions and SOLID violations.

### **DAY 4+5: CREATIONAL DESIGN PATTERNS**

**Goal:** Learn patterns for object creation mechanisms.

#### **1. Scope (Key Knowledge Areas)**

- **Factory Method:** Define an interface for creating an object, but let subclasses decide which class to instantiate.
- **Abstract Factory:** Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
- **Singleton:** Ensure a class has only one instance and provide a global point of access to it.
- **Builder:** Separate the construction of a complex object from its representation.
- **Prototype:** Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype.

#### **2. Instruction: How to leverage AI/Web**

Sample prompts:

- **Factory:** "What is the key difference between Factory Method and Abstract Factory? When to use which?"
- **Singleton:** "Show me 3 ways to implement Singleton in Java. Which one is thread-safe and why is `enum` the best?"
- **Builder:** "Refactor this class with a constructor having 10 parameters to use the Builder Pattern."
- **Prototype:** "When is the Prototype pattern useful? How does `Cloneable` interface in Java relate to this?"
- **Real-world:** "Give examples of Builder pattern usage in standard Java libraries (e.g., `StringBuilder`, `Stream.Builder`)."

_Action:_ Complete **Assignment 02** in [ASSIGNMENTS.md](./ASSIGNMENTS.md).

### **DAY 6: WORKSHOP**

**Activity:** Present and review Assignment 02 solutions. Compare different pattern implementations.

### **DAY 7+8: STRUCTURAL & BEHAVIORAL DESIGN PATTERNS**

**Goal:** Learn patterns for object composition and communication between objects.

#### **1. Scope (Key Knowledge Areas)**

- **Adapter:** Convert the interface of a class into another interface clients expect.
- **Decorator:** Attach additional responsibilities to an object dynamically.
- **Proxy:** Provide a surrogate or placeholder for another object to control access to it.
- **Strategy:** Define a family of algorithms, encapsulate each one, and make them interchangeable.
- **Observer:** Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
- **Command:** Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

#### **2. Instruction: How to leverage AI/Web**

Sample prompts:

- **Adapter vs Decorator:** "Both Adapter and Decorator wrap an object. What is the difference in INTENT? Give a code example comparing them."
- **Strategy:** "How does the Strategy Pattern replace 'if-else' or 'switch-case' statements? Show a refactoring example for a Payment System."
- **Observer:** "Explain the Observer pattern. What is the 'Memory Leak' risk (Lapsed Listener Problem) and how to avoid it?"
- **Command:** "How does the Command Pattern support 'Undo/Redo' operations in a text editor?"
- **Proxy:** "What is the difference between specific Proxy types: Remote Proxy vs Virtual Proxy vs Protection Proxy?"

_Action:_ Complete **Assignment 03** in [ASSIGNMENTS.md](./ASSIGNMENTS.md).

### **DAY 9: WORKSHOP**

**Activity:** Present and review Assignment 03 solutions. Discuss pattern trade-offs and real-world applications.

### **DAY 10-14: EVALUATION**

**Format:**

- **Theory:** Quiz covering all topics.
- **Practice:** Coding statement to assess the application of learned concepts and patterns.
