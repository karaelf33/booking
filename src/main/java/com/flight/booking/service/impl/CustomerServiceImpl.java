package com.flight.booking.service.impl;

import com.flight.booking.dto.GenericDTO;
import com.flight.booking.entity.Customer;
import com.flight.booking.repository.CustomerRepository;
import com.flight.booking.service.CustomerService;
import com.flight.booking.utils.CreditCardValidationUtils;
import com.flight.booking.utils.MapperUtils;
import com.flight.booking.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public GenericDTO createCustomer(String nameSurname, String cardNumber) {

        Customer customer = null;

        try {

            customer = Customer.buildCustomer(nameSurname,
                    CreditCardValidationUtils.getMaskedCreditCardNumber(cardNumber));

            customerRepository.save(customer);
            return OperationUtils.returnMessageHandling(
                    MapperUtils.entityToHashMapMapper(customer),
                    OperationUtils.SUCCESS_CODE,
                    true,
                    OperationUtils.SUCCESS_MESSAGE);


        } catch (Exception e) {
            return OperationUtils.returnMessageHandling(
                    customer,
                    OperationUtils.FAIL_CODE,
                    false,
                    e.getMessage());

        }

    }
}
