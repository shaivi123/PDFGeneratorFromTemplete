package com.springrest.ThymeleafDemo.service;

import com.springrest.ThymeleafDemo.model.Products;
import com.springrest.ThymeleafDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {

    @Autowired(required = false)
    private ProductRepository productRepository;

    public Optional<Products> editProduct(long id){
          Optional<Products> product=productRepository.findById(id);
          return product;
    }
    public Products saveProduct(String productName, String description, String price, String quantity){
        Products product=new Products();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
//        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public void home(Model m) {
        List<Products> data= productRepository.findAll();
        m.addAttribute("all_products",data);
    }
}
