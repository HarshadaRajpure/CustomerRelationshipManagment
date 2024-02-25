package com.springboot.customerRManger.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.customerRManger.Service.CustomerService;
import com.springboot.customerRManger.entity.CustomerEntity;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

@PostMapping("/insert")
public String insertCustomer(@RequestBody CustomerEntity customerEntity) {
	CustomerEntity customerEntity2=customerService.InsertCustomer(customerEntity);
	System.out.println(customerEntity2);
	return "Customer inserted succesfully";
}

@GetMapping()
public List<CustomerEntity> findAllCustomer(){
	return customerService.FindAllCustomer();
}

@GetMapping("/getByAge")
public ResponseEntity<List<CustomerEntity>> getEmployeeByAge() {
    try {
        List<CustomerEntity> customers = customerService.getCustomer();
        return ResponseEntity.ok(customers);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

@GetMapping("/{id}")
public Optional<CustomerEntity> getById(@PathVariable int id){
	return customerService.getCustomerById(id);
}


@PutMapping("/update/{id}")
public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody CustomerEntity updatedCustomer) {
    String message = customerService.updateCustomer(id, updatedCustomer);
    HttpStatus status = message.equals("Customer updated successfully") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return ResponseEntity.status(status).body(message);
}

@DeleteMapping("/delete/{id}")
public String deleteCutomer(@PathVariable int id) {
	return customerService.deleteCustomer(id);
	
}

@PostMapping("/multiple")
public String inserMultipleCustomer(@RequestBody List<CustomerEntity> entities) {
	return customerService.InsertMultipleCustomers(entities);
	
}

@GetMapping("/check")
public List<CustomerEntity> checkCustomer(String firstName){
	
	List<CustomerEntity> list=customerService.getCustomersByFirstName(firstName);
	System.out.println(list);
	return list;
	
}

}
