package com.paygoal.apiproductos.service;

import com.paygoal.apiproductos.dto.CreateSuccessfullyDTO;
import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;
import com.paygoal.apiproductos.exceptions.ProductDoesNotExist;
import com.paygoal.apiproductos.model.Product;
import com.paygoal.apiproductos.repository.IProductDAO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
    private Product product;
    private Long id;

    @BeforeEach
    void setUp() {
        product = new Product();
        id = 1L;
        product.setId(id);
        productDTO = new ProductDTO();
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("JUnit test for createProduct method")
    @Test
    void testCreateProduct() {
        //given - precondition
        when(modelMapper.map(any(ProductDTO.class), eq(Product.class))).thenReturn(product);
        when(repository.save(any(Product.class))).thenReturn(product);
        //when/act
        CreateSuccessfullyDTO result = service.createProduct(productDTO);
        //then/assert
        verify(modelMapper, times(1)).map(productDTO, Product.class);
        verify(repository, times(1)).save(any(Product.class));//¿save(product)-> +especifico?
        assertThat(result).isNotNull();
    }
    @DisplayName("JUnit test for getProduct method")
    @Test
    void testGetProductSuccessful() throws ApiException {
        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(productDTO);
        ProductDTO result = service.getProduct(id);
        assertThat(result).isNotNull();
        verify(repository, times(1)).findById(id);
    }
    @DisplayName("JUnit test for getProduct method (negative case)")
    @Test
    void testGetProductThrowsException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatExceptionOfType(ProductDoesNotExist.class).isThrownBy(() -> service.updateProduct(id, productDTO));
        verify(repository, times(1)).findById(id);
    }
    @DisplayName("JUnit test for updateProduct")
    @Test
    void testUpdateProductSuccessful() throws ApiException {
       //given
        Long id = 3L;
        Product existenceProduct= new Product(3L,"product","description",BigDecimal.valueOf(2000),2);
        ProductDTO dtoUpdate = new ProductDTO("product-update","description-update",BigDecimal.valueOf(4000),4);

        when(repository.findById(id)).thenReturn(Optional.of(existenceProduct));
        //any(Product.class) porque productDTO no tiene id
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(dtoUpdate);
        //when - action to test
        ProductDTO result = service.updateProduct(id, dtoUpdate);
        //then
        assertThat(result.getName()).isEqualTo(dtoUpdate.getName());
        assertThat(result.getDescription()).isEqualTo(dtoUpdate.getDescription());
        assertThat(result.getPrice()).isEqualTo(dtoUpdate.getPrice());
        assertThat(result.getQuantity()).isEqualTo(dtoUpdate.getQuantity());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existenceProduct);


    }

    @DisplayName("JUnit test for updateProduct (negative case)")
    @Test
    void testUpdateProductThrowsException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatExceptionOfType(ProductDoesNotExist.class)
                .isThrownBy(() -> service.updateProduct(id, productDTO));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any()); //verifico que nunca se llame
    }
    @DisplayName("JUnit test for deleteProduct method")
    @Test
    void testDeleteProduct(){
        //given
        doNothing().when(repository).deleteById(id);
        //when
        repository.deleteById(id);
        //then
        verify(repository,times(1)).deleteById(id);
    }

    @DisplayName("JUnit test for deleteProduct method (negative case)")
    @Test
    void testDeleteProductThrowsException(){
        //given
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatExceptionOfType(ProductDoesNotExist.class).isThrownBy(() -> service.deleteProduct(id));
        doNothing().when(repository).deleteById(id);
        //when
        repository.deleteById(id);
        //then
        verify(repository,times(1)).deleteById(id);
    }

}
