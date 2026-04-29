package ru.netology.qa;

public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    // --- НОВЫЙ МЕТОД: Поиск товара по ID ---
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // Если не нашли — возвращаем null
    }

    // --- ОБНОВЛЕННЫЙ МЕТОД: Удаление с проверкой ---
    public void removeById(int id) {
        // 1. Проверяем, есть ли такой товар через findById
        if (findById(id) == null) {
            // Если товара нет — кидаем наше исключение
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        // 2. Если мы здесь, значит товар найден. Можно безопасно удалять.
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}