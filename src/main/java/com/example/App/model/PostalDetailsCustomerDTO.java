package com.example.App.model;

import lombok.Getter;
import lombok.Setter;

//create a DTO to return only the address, phone and city of the customer, called PostalDetailsUserDTO
@Getter
@Setter
public class PostalDetailsCustomerDTO {
    private String address;
    private String phone;
    private String city;

    public PostalDetailsCustomerDTO(Customer c){
        this.address = c.getAddress();
        this.phone = c.getPhone();
        this.city = c.getCity();
    }
}
