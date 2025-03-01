# **Building Elevator System**

## **Overview**  
This project involves designing an **object-oriented model** for a building with an elevator system. The implementation follows **OOP principles**, ensuring **modularity, scalability, and maintainability**. A **UML diagram** has been provided to visualize the **class structure and interactions**. **Unit tests** are included to verify the functionality of individual components.

## **UML Design**  
A comprehensive **UML diagram** has been created to represent the **class structure and relationships**. The UML diagram includes:  

- **Building Class:** Manages **elevators** and **request distribution**.  
- **Elevator Class:** Handles **individual elevator operations**.  
- **Request Handling:** Manages **up and down requests efficiently**.  
- **Service Management:** Supports **taking elevators or the entire system out of service**.  

## **Object-Oriented Principles Applied**  

✅ **Encapsulation:** Data fields such as `elevators`, `upRequests`, and `downRequests` are encapsulated within the `Building` class.  
✅ **Abstraction:** Public methods like `startElevatorSystem()`, `distributeUpRequests()`, and `takeOutOfService()` provide **high-level functionality** without exposing internal details.  
✅ **Inheritance:** Future extensions can leverage **inheritance** to introduce **specialized elevator behaviors**.  
✅ **Polymorphism:** Methods like `takeOutOfService()` in both `Building` and `Elevator` ensure **flexibility** in handling service requests at different levels.  

## **Functionality**  

### **Elevator Storage**  
📌 Elevators are stored in an **array** within the `Building` class.  

### **Request Handling**  
📌 Incoming requests are stored separately as **`upRequests`** and **`downRequests`** in the `Building` class.  
📌 Requests are distributed using `distributeUpRequests()` and `distributeDownRequests()`.  

### **System Operations**  
- **Processing Requests:** Requests are processed by calling `startElevatorSystem()`, which allows new requests to be added and distributed to elevators.  
- **System Updates:** The `step()` method in `Building` calls `step()` on each elevator to update their states.  
- **Taking Elevators Out of Service:** The `takeOutOfService()` method in the `Elevator` class ensures requests assigned to an out-of-service elevator are handled properly.  
- **Shutting Down the System:** The `stopElevatorSystem()` method halts all operations and clears requests.  

## **Unit Testing**  
Comprehensive **unit tests** are implemented to verify:  

✔️ Proper request **storage and distribution**.  
✔️ Correct **elevator movement logic**.  
✔️ Handling of **out-of-service scenarios**.  
✔️ System **initialization and shutdown behavior**.  

## **How to Run the Project**  

1. Ensure all necessary **dependencies** are installed.  
2. Run the application to **simulate elevator operations**.  
3. Execute **unit tests** to validate functionality.  

## **Conclusion**  
This project demonstrates a **structured approach** to designing an **elevator system** using **UML diagrams**, **object-oriented principles**, and **rigorous unit testing**. The **modular design** ensures that the system is **scalable** and **maintainable** for future enhancements. 🚀  
