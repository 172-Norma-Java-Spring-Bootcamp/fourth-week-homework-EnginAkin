package com.tr.shopping.core.converter.concretes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.abstracts.CustomerConverterService;
import com.tr.shopping.core.exception.CustomerIdCannotFountException;
import com.tr.shopping.entity.BasketItem;
import com.tr.shopping.entity.Customer;
import com.tr.shopping.entity.CustomerAddress;
import com.tr.shopping.entity.Product;
import com.tr.shopping.core.model.dto.BasketItemDto;
import com.tr.shopping.core.model.dto.CustomerAddressDto;
import com.tr.shopping.core.model.dto.CustomerDto;
import com.tr.shopping.core.model.response.CustomerResponse;
import com.tr.shopping.repository.CustomerRepository;
import com.tr.shopping.repository.ProductRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CustomerConverterServiceImpl implements CustomerConverterService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        Customer customer=new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setGender(customerDto.getGender());
        customer.setPassword(customerDto.getPassword());
        customer.setUsername(customerDto.getUsername());
        customer.setIsDeleted(false);
        customer.setCreatedBy("ENGIN AKIN");
        customer.setCreatedAt(new Date());
        CustomerAddress address=new CustomerAddress();
        address.setCity(customerDto.getCustomerAddress().getCity());
        address.setCountry(customerDto.getCustomerAddress().getCountry());
        address.setDescription(customerDto.getCustomerAddress().getDescription());
        address.setPhoneNumber(customerDto.getCustomerAddress().getPhoneNumber());
        address.setPostalCode(customerDto.getCustomerAddress().getPostalCode());
        customer.setCustomerAddress(address);

        return customer;
    }

    @Override
    public BasketItem basketItemDtoToBasket(BasketItemDto basketItemDto, Long customerId) {
        BasketItem basketItem=new BasketItem();
        basketItem.setQuantity(basketItemDto.getQuantity());
        basketItem.setDiscountPrice(basketItemDto.getDiscountPrice());
        basketItem.setShippingPrice(basketItemDto.getShippingPrice());
        basketItem.setTaxPrice(basketItemDto.getTaxPrice());

        if(!checkExistsCustomerId(customerId)) throw new CustomerIdCannotFountException();
        Product product=productRepository.findById(basketItemDto.getProductId()).get();
        basketItem.setProduct(product);
        return basketItem;
    }

    @Override
    public CustomerResponse customerToCustomerResponse(Customer customer) {
        CustomerResponse customerResponse=new CustomerResponse();
        CustomerAddressDto customerAddressDto=new CustomerAddressDto();
        customerAddressDto.setDescription(customer.getCustomerAddress().getDescription());
        customerAddressDto.setPostalCode(customer.getCustomerAddress().getPostalCode());
        customerAddressDto.setCountry(customer.getCustomerAddress().getCountry());
        customerAddressDto.setCity(customer.getCustomerAddress().getCity());
        customerAddressDto.setPhoneNumber(customer.getCustomerAddress().getPhoneNumber());
        customerResponse.setCustomerAddress(customerAddressDto);
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setGender(customer.getGender());
        customerResponse.setUsername(customer.getUsername());
        return customerResponse;
    }

    public Boolean checkExistsCustomerId(long customerId){
        return customerRepository.existsById(customerId);
    }

}
