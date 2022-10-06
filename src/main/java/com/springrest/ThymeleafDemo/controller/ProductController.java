package com.springrest.ThymeleafDemo.controller;

import com.springrest.ThymeleafDemo.dto.ProductReq;
import com.springrest.ThymeleafDemo.model.Products;
import com.springrest.ThymeleafDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired(required = false)
    private ProductService productService;

    @GetMapping("/home")
    public String home(Model m){
        productService.home(m);
        return "index";
    }
    @GetMapping("/link")
    public String linkProduct(){
        return null;
        }

    @GetMapping("/loadAdd")
    public String loadAdd(){
        return "add";
    }
    //for edit button
    @GetMapping("/loadEdit/{id}")
    public String loadEdit(@PathVariable long id,Model m){
        Optional<Products> prop=productService.editProduct(id);
        if(!prop.isEmpty()) {
            Products pro = prop.get();
            m.addAttribute("product", pro);
        }
        return "edit";
    }
   //for save the product in database
   @PostMapping("/save_products")
    public String saveProducts( ProductReq products,HttpSession session){
        productService.saveProduct(products.getProductName(),products.getDescription(),products.getPrice(),products.getQuantity());
        session.setAttribute("msg","product added successfully..");
        return "redirect:/loadAdd";
   }
    //for update existing product
    @PostMapping("/update_products")
    public String updateProducts( ProductReq products,HttpSession session){
        productService.saveProduct(products.getProductName(),products.getDescription(),products.getPrice(),products.getQuantity());
        session.setAttribute("msg","product updated successfully..");
        return "redirect:/home";
    }
    //for deleting product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id, HttpSession session){
        productService.deleteProduct(id);
        session.setAttribute("msg","product deleted successfully");
        return "redirect:/home";
    }
}
