package com.brisbiere.Brisbiere.web.controller;


import com.brisbiere.Brisbiere.domain.Product;
import com.brisbiere.Brisbiere.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @Operation(description = "Get all products")
    @ApiResponse(responseCode = "200", description = "Ok")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    @Operation(description = "Search a beer by brand.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<List<Product>> getByBrand(@Parameter @PathVariable("brand") String brand){
        List<Product> products = productService.getByBrand(brand).orElse(null);

        return products != null && !products.isEmpty() ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/style/{style}")
    @Operation(description = "Search a beer by style")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<List<Product>> getByStyle(@Parameter @PathVariable("style") String style){
        List<Product> products = productService.getByStyle(style).orElse(null);

        return products != null && !products.isEmpty() ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/available")
    public  ResponseEntity<Page<Product>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int elements,
                                                       @RequestParam(defaultValue = "precio") String sortby,
                                                       @RequestParam(defaultValue = "ASC") String sortDirection
    ){
        Page<Product> products = productService.getAvailable(page, elements, sortby, sortDirection).orElse(null);
        return products != null && !products.isEmpty() ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @Operation(description = "Search a beer with id 1 - 9.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Beer not found")
    })
    public ResponseEntity<Product> getProduct(@Parameter @PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(description = "Save a beer with whole object.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestBody Product product){
        if(product.getProductId() != null && this.productService.exists(product.getProductId())){
            return ResponseEntity.ok(this.productService.save(product));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a beer by id from catalog.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> delete(@Parameter @PathVariable("id") int productId){
        return productService.delete(productId) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

