package com.vishal.demo.resources;

import com.vishal.demo.dto.ApiResponse;
import com.vishal.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@SuppressWarnings("unused")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    /***
     * Fetch all clients
     *
     * @return all clients
     */
    @GetMapping
    public ApiResponse getClients() {
        return ApiResponse.builder()
                .data(clientService.findAll())
                .message("Clients fetched successfully")
                .build();
    }

    /***
     * This will always hard delete the product record from database permanently
     *
     * @param id the client id
     * @return it will return 404 if there are no record with given id exist
     */
    @DeleteMapping(path = "/{id}")
    public ApiResponse remove(@PathVariable("id") int id) {
        clientService.remove(id);
        return ApiResponse.builder().message(String.format("The client with id : %s deleted successfully", id)).build();
    }
}
