package com.stocks.product.controller;

import com.stocks.product.model.dto.ProductRequestDTO;
import com.stocks.product.model.entity.Product;
import com.stocks.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add product", description = "Add new product")
    public void addProduct(@RequestBody ProductRequestDTO dto) {
        var product = new Product();
        BeanUtils.copyProperties(dto, product);

        productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update product", description = "Update old product")
    @ApiResponse(responseCode = "202", description = "Successful update")
    @ApiResponse(responseCode = "500", description = "Product id not found")
    public void editProduct(@PathVariable String productId, @RequestBody ProductRequestDTO dto) {
        var product = new Product();
        BeanUtils.copyProperties(dto, product);

        productService.editProduct(productId, product);
    }

    @ApiResponse(responseCode = "202", description = "Successful remove")
    @ApiResponse(responseCode = "500", description = "Product id not found")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{productId}")
    @Operation(summary = "Remove product", description = "Remove old product")
    public void removeProduct(@PathVariable String productId) {
        productService.removeProduct(productId);
    }


    @GetMapping("/{productId}")
    @ApiResponse(responseCode = "200", description = "Successful retrieve")
    @ApiResponse(responseCode = "500", description = "Product id not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @Operation(summary = "Get a product", description = "Returns available product")
    public Product getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping
    @Operation(summary = "Get a list of products", description = "Returns a list of available products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/active")
    @Operation(summary = "Get a list of active products", description = "Returns a list of active available products")
    public List<Product> getActiveProducts() {
        return productService.getActiveProducts();
    }

    @GetMapping("/inactive")
    @Operation(summary = "Get list of inactive products", description = "Returns list of inactive available products")
    public List<Product> getInActiveProducts() {
        return productService.getInActiveProducts();
    }
}