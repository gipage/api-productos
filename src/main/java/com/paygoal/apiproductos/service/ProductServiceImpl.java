package com.paygoal.apiproductos.service;

import com.paygoal.apiproductos.dto.CreateSuccessfullyDTO;
import com.paygoal.apiproductos.dto.ProductDTO;
import com.paygoal.apiproductos.exceptions.ApiException;
import com.paygoal.apiproductos.exceptions.ProductAlreadyExist;
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
    //realizar en constructor
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductDAO productDAO;

    @Transactional
    @Override
    public CreateSuccessfullyDTO createProduct(ProductDTO productDTO) throws ApiException {
        //mapeo DTO->Product
        Product product = modelMapper.map(productDTO, Product.class);
        if (!(productDAO.existsById(product.getId()))) {
            productDAO.save(product);
            return new CreateSuccessfullyDTO(product.getId(), getDateNow());
        } else throw new ProductAlreadyExist(product.getId(),product.getName());

    }


    private Timestamp getDateNow() {
        Calendar calendar = Calendar.getInstance();
        // Convertir Calendar en un objeto Date
        Date fechaHoraActual = calendar.getTime();
        // Crear objeto Timestamp a partir de la fecha y hora actual
        Timestamp timestamp = new Timestamp(fechaHoraActual.getTime());
        return timestamp;
    }
}
