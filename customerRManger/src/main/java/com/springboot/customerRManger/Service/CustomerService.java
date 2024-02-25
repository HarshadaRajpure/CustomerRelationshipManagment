package com.springboot.customerRManger.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.customerRManger.entity.CustomerEntity;
import com.springboot.customerRManger.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public CustomerEntity InsertCustomer(CustomerEntity customerEntity) {
		return customerRepository.save(customerEntity);
	}
	
	public List<CustomerEntity> FindAllCustomer() {
		List<CustomerEntity> flist=customerRepository.findAll();	
		return 	flist;
	}
	
	public List<CustomerEntity> getCustomer() {
	    List<CustomerEntity> list = customerRepository.findAll();
	    List<CustomerEntity> filteredList = new ArrayList<>();

	    for (CustomerEntity customer : list) {
	        if (customer.getAge() > 24) {
	            filteredList.add(customer);
	        }
	    }
	    return filteredList;
	}
	
	
	public Optional<CustomerEntity> getCustomerById(int id) {
		return customerRepository.findById(id);
	}
	
	public String updateCustomer(int id, CustomerEntity updatedCustomer) {
        // Retrieve the existing customer entity from the database
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(updatedCustomer.getId());

        if (optionalCustomer.isPresent()) {
            // Update the existing customer entity with the new values
            CustomerEntity existingCustomer = optionalCustomer.get();
            existingCustomer.setFirstname(updatedCustomer.getFirstname());
            existingCustomer.setLastname(updatedCustomer.getLastname());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setMobileno(updatedCustomer.getMobileno());
            existingCustomer.setAge(updatedCustomer.getAge());

            // Save the modified customer entity back to the database
            customerRepository.save(existingCustomer);
            
            return "Customer updated successfully";
        } else {
            return "Customer not found";
        }
    }
	
	public String deleteCustomer(int id) {
		customerRepository.deleteById(id);
		return "Customer deleted succesfully";
		
	}
	
	 @Transactional
	    public String InsertMultipleCustomers(List<CustomerEntity> customers) {
	        try {
	            customerRepository.saveAll(customers);
	            return "Customers inserted successfully";
	        } catch (Exception e) {
	            // Handle exceptions, log errors, or perform rollback if needed
	            e.printStackTrace();
	            return "Failed to insert customers: " + e.getMessage();
	        }
	    }
	 public List<CustomerEntity> getCustomersByFirstName(String firstName) {
	        return customerRepository.findByFirstname(firstName);
	    }

}
	

