package com.paygoal.apiproductos.repository;

import com.paygoal.apiproductos.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("Suite de Pruebas para la Clase IProductDAOTest")
@DataJpaTest
public class IProductDAOTest {
    //given - when - then
    @Autowired
    private IProductDAO iProductDAO;
    @BeforeEach
    void setUp() {
        //given
        iProductDAO.save(new Product(1L, "product1", "description-product1", BigDecimal.valueOf(10000), 5));
        iProductDAO.save(new Product(2L, "product2", "description-product2", BigDecimal.valueOf(8000), 3));
        iProductDAO.save(new Product(3L, "product3", "description-product3", BigDecimal.valueOf(12000), 7));
    }

    @AfterEach
    void tearDown() {
        iProductDAO.deleteAll();
    }

    //Test case Success
    @Test
    void testGetAllOrderByPrice() {
        //when
        List<Product> productList = iProductDAO.getAllOrderByPrice();
        // Then
        assertThat(productList).isSortedAccordingTo(Comparator.comparing(Product::getPrice).reversed());

    }
    //Test case Failure
    @Test
    void testGetAllOrderByPriceIsEmpty(){
        tearDown(); //llamo debido a que setUp me carga la base de datos al iniciar el test.
        //when
        List<Product> productList = iProductDAO.getAllOrderByPrice();
        //then
        assertThat(productList).isEmpty();
    }
}
