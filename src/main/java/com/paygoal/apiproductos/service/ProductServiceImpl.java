package com.paygoal.apiproductos.service;

import com.paygoal.apiproductos.dto.CreateSuccessfullyDTO;
import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;
import com.paygoal.apiproductos.exceptions.ProductAlreadyExist;
import com.paygoal.apiproductos.exceptions.ProductDoesNotExist;
import com.paygoal.apiproductos.model.Product;
import com.paygoal.apiproductos.repository.IProductDAO;
import com.paygoal.apiproductos.service.interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    private final ModelMapper modelMapper;
    private final IProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, IProductDAO productDAO) {
        this.modelMapper = modelMapper;
        this.productDAO = productDAO;
    }

    @Transactional
    @Override
    public CreateSuccessfullyDTO createProduct(ProductDTO productDTO) {
        //mapeo DTO->Product
        Product product = modelMapper.map(productDTO, Product.class);
        productDAO.save(product);
        return new CreateSuccessfullyDTO(product.getId(), getDateNow());

    }

    @Transactional
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws ApiException {
        //optional

        Product product = productDAO.findById(id).orElseThrow(()-> new ProductDoesNotExist(id));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        productDAO.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getProduct(Long id) throws ApiException {
       Product product = productDAO.findById(id).orElseThrow(()-> new ProductDoesNotExist(id));
       return modelMapper.map(product, ProductDTO.class);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) throws ApiException {
        if (productDAO.existsById(id))
            productDAO.delete(productDAO.getReferenceById(id));
        else throw new ProductDoesNotExist(id);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productDAO.getAllOrderByPrice();
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }


    private Timestamp getDateNow() {
        Calendar calendar = Calendar.getInstance();
        // Convertir Calendar en un objeto Date
        Date fechaHoraActual = calendar.getTime();
        // Crear objeto Timestamp a partir de la fecha y hora actual
        return new Timestamp(fechaHoraActual.getTime());
    }
}
