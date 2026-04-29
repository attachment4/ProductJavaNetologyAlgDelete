import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.qa.NotFoundException;
import ru.netology.qa.Product;
import ru.netology.qa.ShopRepository;

public class ShopRepositoryTest {

    Product item1 = new Product(1, "Книга", 500);
    Product item2 = new Product(2, "Смартфон", 30_000);
    Product item3 = new Product(3, "Чайник", 2_500);

    @Test
    public void shouldRemoveExistingElement() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        repo.removeById(2);

        Product[] expected = {item1, item3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenNotFound() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);

        // Проверяем, что выкидывается именно NotFoundException
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(100);
        });
    }
}