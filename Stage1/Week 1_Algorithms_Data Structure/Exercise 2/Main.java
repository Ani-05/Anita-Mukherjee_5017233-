import java.util.Arrays;
import java.util.Comparator;
public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Laptop", "Electronics"),
                new Product(2, "Smartphone", "Electronics"),
                new Product(3, "Tablet", "Electronics"),
                new Product(4, "Headphones", "Accessories"),
                new Product(5, "Smartwatch", "Wearables")
        };

        System.out.println("Linear Search by ID: " + Search.linearSearchById(products, 3));
        System.out.println("Linear Search by Name: " + Search.linearSearchByName(products, "Tablet"));
        System.out.println("Linear Search by Category: " + Search.linearSearchByCategory(products, "Accessories"));

        Arrays.sort(products);
        System.out.println("Binary Search by ID: " + Search.binarySearchById(products, 3));

        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        System.out.println("Binary Search by Name: " + Search.binarySearchByName(products, "Tablet"));

        Arrays.sort(products, Comparator.comparing(Product::getCategory));
        System.out.println("Binary Search by Category: " + Search.binarySearchByCategory(products, "Accessories"));
    }
}

class Product implements Comparable<Product> {
    private final int productId;
    private final String productName;
    private final String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId +", productName='" + productName + '\'' +", category='" + category + '\'' + '}';
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.productId, other.productId);
    }
}
//Linear search by product id
class Search {
    public static Product linearSearchById(Product[] products, int targetId) {
        for (Product product : products) {
            if (product.getProductId() == targetId) {
                return product;
            }
        }
        return null;
    }

    // Linear search by product name
    public static Product linearSearchByName(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    // Linear search by category
    public static Product linearSearchByCategory(Product[] products, String targetCategory) {
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(targetCategory)) {
                return product;
            }
        }
        return null;
    }

    // Binary search by product Id
    public static Product binarySearchById(Product[] products, int targetId) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Product midProduct = products[mid];

            if (midProduct.getProductId() == targetId) {
                return midProduct;
            } else if (midProduct.getProductId() < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Binary search by product name
    public static Product binarySearchByName(Product[] products, String targetName) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Product midProduct = products[mid];

            int cmp = midProduct.getProductName().compareToIgnoreCase(targetName);
            if (cmp == 0) {
                return midProduct;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Binary search by category
    public static Product binarySearchByCategory(Product[] products, String targetCategory) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Product midProduct = products[mid];

            int cmp = midProduct.getCategory().compareToIgnoreCase(targetCategory);
            if (cmp == 0) {
                return midProduct;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}


