package DAL;

import models.*;
import structures.*;

public class ProductOperations {
    BinaryTree<Integer, Product> productById;
    BinaryTree<Double, DoubleLinkedList<Product>> productByPrice;
    BinaryTree<Double, DoubleLinkedList<Product>> productByAvgRating;
    private static int countTop;

    public ProductOperations() {
        productById = new AVLTree<>();
        productByPrice = new AVLTree<>();
        productByAvgRating = new AVLTree<>();

    }

    public boolean addProduct(Product p) {
        if (productById.insert(p.getProductId(), p)) {
            productByPrice.insert(p.getPrice(), new DoubleLinkedList<Product>());
            productByPrice.retrieve().insert(p);

            productByAvgRating.insert(p.getAverageRating(), new DoubleLinkedList<Product>());
            productByAvgRating.retrieve().insert(p);
            return true;
        } else
            return false;
    }

    public boolean updateProduct(int id, String name, Double price, int stock) {
        if (productById.findKey(id)) {
            Product p = productById.retrieve();
            p.setName(name);
            p.setStock(stock);

            productByPrice.findKey(p.getPrice());

            DoubleLinkedList<Product> products = productByPrice.retrieve();

            removeFromDuplicates(p, products);

            if (products.empty()) {
                productByPrice.removeKey(p.getPrice());
            }

            p.setPrice(price);

            productByPrice.insert(price, new DoubleLinkedList<>());
            productByPrice.retrieve().insert(p);

            return true;
        }

        return false;
    }

    public boolean addReview(int id, Review r) {
        if (productById.findKey(id)) {
            Product p = productById.retrieve();

            Double avg = p.getAverageRating();

            productByAvgRating.findKey(avg);

            DoubleLinkedList<Product> products = productByAvgRating.retrieve();

            removeFromDuplicates(p, products);

            if (products.empty()) {
                productByAvgRating.removeKey(avg);
            }

            p.addReview(r);

            productByAvgRating.insert(p.getAverageRating(), new DoubleLinkedList<>());
            productByAvgRating.retrieve().insert(p);

            return true;
        }
        return false;
    }

    private void removeFromDuplicates(Product p, DoubleLinkedList<Product> products) {
        products.findFirst();
        while (true) {
            if (products.retrieve().getProductId() == p.getProductId()) {
                products.remove();
                break;
            }
            if (products.last()) {
                break;
            }
            products.findNext();
        }
    }

    public boolean removeProduct(Product p) {
        if (productById.removeKey(p.getProductId())) {
            productByPrice.findKey(p.getPrice());
            removeFromDuplicates(p, productByPrice.retrieve());

            productByAvgRating.findKey(p.getAverageRating());
            removeFromDuplicates(p, productByAvgRating.retrieve());
            return true;
        } else
            return false;
    }

    public Product searchProductId(int productId) {
        Product p = null;
        if (productById.findKey(productId))
            p = productById.retrieve();
        return p;
    }

    public void printSortedId(TraverseOrder t) {
        productById.traverse(t);
    }

    public List<Product> getTopProducts() {
        List<Product> top = new LinkedList<>();

        productByAvgRating.findRoot();

        countTop = 0;
        findMaxAvg(top);

        return top;
    }

    private void findMaxAvg(List<Product> result) {
        if (countTop >= 3)
            return;

        if (productByAvgRating.findRight()) {
            findMaxAvg(result);
            productByAvgRating.findParent();
        }

        if (countTop >= 3)
            return;

        DoubleLinkedList<Product> products = productByAvgRating.retrieve();

        products.findFirst();
        while (true) {
            result.insert(products.retrieve());
            if (++countTop >= 3)
                return;
            if (products.last())
                break;
            products.findNext();
        }

        if (countTop < 3) {
            if (productByAvgRating.findLeft()) {
                findMaxAvg(result);
                productByAvgRating.findParent();
            }
        }
    }

    public List<Product> priceRange(Double minPrice, Double maxPrice) {
        List<Product> productsInRange = new LinkedList<>();

        productByPrice.findRoot();

        findPriceRange(minPrice, maxPrice, productsInRange);

        return productsInRange;

    }

    private void findPriceRange(Double minPrice, Double maxPrice, List<Product> result) {
        Double key = productByPrice.getKey();

        if (key >= minPrice && key <= maxPrice) {

            DoubleLinkedList<Product> products = productByPrice.retrieve();

            products.findFirst();
            while (true) {
                result.insert(products.retrieve());
                if (products.last())
                    break;
                products.findNext();
            }
            
            if (productByPrice.findLeft()) {
                findPriceRange(minPrice, maxPrice, result);
                productByPrice.findParent();
            }

            if (productByPrice.findRight()) {
                findPriceRange(minPrice, maxPrice, result);
                productByPrice.findParent();
            }

        }

        if (key < minPrice) {
            if (productByPrice.findRight()) {
                findPriceRange(minPrice, maxPrice, result);
                productByPrice.findParent();
            }

        }

        if (key > maxPrice) {
            if (productByPrice.findLeft()) {
                findPriceRange(minPrice, maxPrice, result);
                productByPrice.findParent();
            }
        }

    }
}
