package com.vishal.demo.resources;

import com.vishal.demo.dto.ApiResponse;
import com.vishal.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@SuppressWarnings("unused")
public class ProductResource {

    @Autowired
    private ProductService productService;

    /**
     * End-point to fetch list of products, if includeDeleted true, it will return all produced including soft deleted ones,
     * otherwise this will only return "non" soft deleted ones
     *
     * @param includeDeleted the flag to define whether to return all entities including soft deleted or exclude soft deleted
     * @return the list of products
     */
    @GetMapping
    public ApiResponse getProducts(@RequestParam(name = "includeDeleted", required = false, defaultValue = "false") boolean includeDeleted) {
        return ApiResponse.builder()
                .data(productService.findAll(includeDeleted))
                .message("Products fetched successfully")
                .build();
    }

    /***
     * This will always soft delete the product record, never deletes it permanently
     *
     * @param id the product id
     * @return it will return 404 if there are no record with given id exist <BR>
     *     it will always return 200 success when we try to "soft" delete the already "soft" deleted record
     */
    @DeleteMapping(path = "/{id}")
    public ApiResponse remove(@PathVariable("id") int id) {
        productService.remove(id);
        return ApiResponse.builder().message(String.format("The product with id : %s soft deleted successfully", id)).build();
    }
}
