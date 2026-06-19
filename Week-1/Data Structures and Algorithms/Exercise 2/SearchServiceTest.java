public class SearchServiceTest {

    public static void main(String[] args) {

        Product[] catalog = {
                new Product(105, "Wireless Mouse", "Electronics"),
                new Product(101, "Bluetooth Speaker", "Electronics"),
                new Product(110, "Running Shoes", "Footwear"),
                new Product(102, "Yoga Mat", "Fitness"),
                new Product(108, "Office Chair", "Furniture"),
                new Product(103, "Desk Lamp", "Furniture")
        };

        SearchService searchService = new SearchService();
        int targetId = 108;

        System.out.println("--- Linear Search ---");
        Product linearResult = searchService.linearSearch(catalog, targetId);
        System.out.println("Searching for productId " + targetId + " -> " + linearResult);

        System.out.println("\n--- Binary Search ---");
        Product[] sortedCatalog = catalog.clone();
        SearchService.sortByProductId(sortedCatalog);

        System.out.println("Catalog sorted by productId:");
        for (Product p : sortedCatalog) {
            System.out.println("  " + p);
        }

        Product binaryResult = searchService.binarySearch(sortedCatalog, targetId);
        System.out.println("Searching for productId " + targetId + " -> " + binaryResult);

        System.out.println("\n--- Searching for a non-existent product (id=999) ---");
        System.out.println("Linear result: " + searchService.linearSearch(catalog, 999));
        System.out.println("Binary result: " + searchService.binarySearch(sortedCatalog, 999));

        System.out.println("\n--- Analysis ---");
        System.out.println("Linear search time complexity: O(n) average/worst, O(1) best case.");
        System.out.println("Binary search time complexity: O(log n) average/worst, O(1) best case,");
        System.out.println("but requires the catalog to be pre-sorted, which itself costs O(n log n).");
        System.out.println("For an e-commerce platform where the catalog is large and read far more");
        System.out.println("often than it changes, binary search is the better choice: we pay the");
        System.out.println("sorting cost once (or keep the catalog sorted as items are added), and");
        System.out.println("every search afterward is much faster than scanning the whole catalog.");
    }
}