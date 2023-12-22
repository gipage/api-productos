package com.paygoal.apiproductos.service;

import com.paygoal.apiproductos.dto.CreateSuccessfullyDTO;
import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;
import com.paygoal.apiproductos.exceptions.ProductDoesNotExist;
import com.paygoal.apiproductos.model.Product;
import com.paygoal.apiproductos.repository.IProductDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //inicializa los mocks
class ProductServiceImplTest {
    @Mock //simulo el comportamiento del repository -> no dependo de su implementación (enfoque en el servicio)
    private IProductDAO repository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks //inyecto mocks para sus dependencias (modelmapper y dao)
    private ProductServiceImpl service;

    private ProductDTO productDTO;
    private Product existingProduct;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO("product1", "description-product1", BigDecimal.valueOf(10000), 5);
        existingProduct= new Product(1L,"product2","description-product2",BigDecimal.valueOf(20000), 1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateProduct() {
        // arrange/given
        Product product = mock(Product.class);
        when(modelMapper.map(any(ProductDTO.class), eq(Product.class))).thenReturn(product);
        when(repository.save(any(Product.class))).thenReturn(product);
        //when/act
        CreateSuccessfullyDTO result = service.createProduct(productDTO);
        //then/assert
        verify(modelMapper, times(1)).map(productDTO, Product.class);
        verify(repository, times(1)).save(any(Product.class));
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(product.getId());
    }
    @Test
    void testUpdateProductSuccessful() throws ApiException {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(existingProduct));
        ProductDTO resultDTO = service.updateProduct(id, productDTO);
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existingProduct);
    }
    @Test
    void testUpdateProductThrowsException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatExceptionOfType(ProductDoesNotExist.class)
                .isThrownBy(() -> service.updateProduct(id, productDTO));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any()); //verifico que nunca se llame
    }
}
