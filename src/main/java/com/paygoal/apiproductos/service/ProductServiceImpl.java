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
    public CreateSuccessfullyDTO createProduct(ProductDTO productDTO) throws ApiException {
        //mapeo DTO->Product
        Product product = modelMapper.map(productDTO, Product.class);
        if (!(productDAO.existsById(product.getId()))) {
            productDAO.save(product);
            return new CreateSuccessfullyDTO(product.getId(), getDateNow());
        } else throw new ProductAlreadyExist(product.getId(), product.getName());

    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) throws ApiException {
        if (productDAO.existsById(id)) {
            Product product = productDAO.getReferenceById(id);
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setQuantity(productDTO.getQuantity());
            productDAO.save(product);
            return productDTO;
        } else throw new ProductDoesNotExist(id);
    }

    @Override
    public ProductDTO getProduct(long id) throws ApiException {
        if (productDAO.existsById(id)) {
            return modelMapper.map(productDAO.getReferenceById(id), ProductDTO.class);
        }
        throw new ProductDoesNotExist(id);

    }


    private Timestamp getDateNow() {
        Calendar calendar = Calendar.getInstance();
        // Convertir Calendar en un objeto Date
        Date fechaHoraActual = calendar.getTime();
        // Crear objeto Timestamp a partir de la fecha y hora actual
        return new Timestamp(fechaHoraActual.getTime());
    }
}
