package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.exception.CustomerNotFoundException;
import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EntityModel<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(customerDTO);
        CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);

        EntityModel<CustomerDTO> resource = EntityModel.of(dto);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel();
        resource.add(selfLink);

        return ResponseEntity.created(selfLink.toUri()).body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);

        EntityModel<CustomerDTO> resource = EntityModel.of(dto);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(id)).withSelfRel();
        resource.add(selfLink);

        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<CustomerDTO>>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<EntityModel<CustomerDTO>> resources = customers.stream()
                .map(customer -> {
                    CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);
                    EntityModel<CustomerDTO> resource = EntityModel.of(dto);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel();
                    resource.add(selfLink);
                    return resource;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(id, customerDTO);
        CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);

        EntityModel<CustomerDTO> resource = EntityModel.of(dto);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(id)).withSelfRel();
        resource.add(selfLink);

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}