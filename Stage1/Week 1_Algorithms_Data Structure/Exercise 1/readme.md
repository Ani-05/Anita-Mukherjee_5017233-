# Inventory Management System

## Overview

The Inventory Management System is a simple Java-based application designed to manage a warehouse inventory. The system supports basic operations such as adding, updating, deleting, and displaying products. The goal is to ensure efficient data storage and retrieval, which is crucial for handling large inventories.

## Data Structures and Algorithms

### Q1.Importance of Data Structures and Algorithms

Efficient data storage and retrieval are essential for managing large inventories. A well-chosen data structure allows for quick and efficient operations such as adding, removing, or updating product information. This efficiency enhances overall productivity and ensures that the system can handle large datasets effectively.

### Q2.Suitable Data Structures

1. **ArrayList**:
   - **Description**: Provides dynamic array functionalities that are easy to implement. It stores similar data types together and allows for straightforward traversal.
   - **Use Case**: Suitable for cases where the number of products is dynamic, and access to products by index is needed.

2. **HashMap**:
   - **Description**: Ideal for quick lookups, inserts, and deletions based on keys. It provides efficient access to elements, which is beneficial for managing inventories.
   - **Use Case**: Best for scenarios where fast retrieval of product information by unique identifiers (like product IDs) is required.

3. **TreeMap**:
   - **Description**: Maintains a sorted order of keys and allows for efficient range queries. Useful if the products need to be accessed or sorted based on specific criteria.
   - **Use Case**: Suitable when products need to be accessed in a sorted order or based on their categories.



### 3.Time Complexity Analysis

The `ArrayList` data structure has been chosen for this implementation. Below is the analysis of the time complexity for each operation:

### a.. Addition

**Operation**: Adding a product to the `ArrayList`.

- **Time Complexity**:
  - **Average Case**: \( O(1) \)
    - Adding an element to the end of an `ArrayList` is generally constant time, \( O(1) \), because it appends the element to the end of the list.
  - **Worst Case**: \( O(n) \)
    - If the internal array needs to be resized and copied to accommodate the new element, the time complexity becomes \( O(n) \), where \( n \) is the number of elements in the `ArrayList`.

### b. Update

**Operation**: Updating a product in the `ArrayList`.

- **Time Complexity**: \( O(n) \)
  - To find the product with the matching `productId`, a full traversal of the list may be required in the worst case. This involves checking each element until the product is found or until the end of the list is reached. Therefore, the time complexity is \( O(n) \), where \( n \) is the number of products in the `ArrayList`.

### c. Deletion

**Operation**: Deleting a product from the `ArrayList`.

- **Time Complexity**: \( O(n) \)
  - Similar to the update operation, finding the product involves traversing the list, which is \( O(n) \). After locating the product, removing it requires shifting all subsequent elements to fill the gap, which is also \( O(n) \) in the worst case. Hence, the overall time complexity for deletion is \( O(n) \).

## 4.Discuss how to optimize the code

The use of `ArrayList` provides simplicity in implementation but comes with time complexity considerations:

- **Addition**: \( O(1) \) on average, but \( O(n) \) in the worst case due to resizing.
- **Update**: \( O(n) \), due to the need to traverse the list.
- **Deletion**: \( O(n) \), due to traversal and shifting elements.

For scenarios where faster operations are required, especially for update and delete, alternative data structures such as `HashMap` or `TreeMap` may be considered, which offer better time complexity for these operations.

