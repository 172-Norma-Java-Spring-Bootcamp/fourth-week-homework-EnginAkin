package com.tr.shopping.service.concretes;

import com.tr.shopping.core.model.dto.CustomerPaymentDto;
import com.tr.shopping.entity.CustomerPayment;
import com.tr.shopping.repository.CustomerPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.concretes.ConverterService;
import com.tr.shopping.core.constant.CustomerResponseMessage;
import com.tr.shopping.core.exception.*;
import com.tr.shopping.core.response.GeneralDataResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.response.GeneralSuccessfullResponse;
import com.tr.shopping.entity.Basket;
import com.tr.shopping.entity.BasketItem;
import com.tr.shopping.entity.Customer;
import com.tr.shopping.core.model.dto.BasketItemDto;
import com.tr.shopping.core.model.dto.CustomerDto;
import com.tr.shopping.core.model.response.BasketResponse;
import com.tr.shopping.core.model.response.CustomerResponse;
import com.tr.shopping.core.model.response.ProductResponse;
import com.tr.shopping.repository.CustomerRepository;
import com.tr.shopping.repository.ProductRepository;
import com.tr.shopping.service.abstracts.CustomerService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ConverterService converterService;
    private final CustomerPaymentRepository customerPaymentRepository;



    @Override
    public GeneralResponse createCustomer(CustomerDto customerDto) {
        Customer customer=converterService.getCustomerConverterService().customerDtoToCustomer(customerDto);
        customerRepository.saveAndFlush(customer);
       return new GeneralSuccessfullResponse(CustomerResponseMessage.CUSTOMER_CREATED_SUCCESSFULL);
    }
    @Override
    public GeneralResponse addCustomerPayment(CustomerPaymentDto customerPaymentDto,Long customerId) {
        if(!customerRepository.existsById(customerId)) throw new CustomerIdCannotFountException();
        Customer customer=customerRepository.findById(customerId).get();
        CustomerPayment customerPayment=converterService.getPaymentConverterService().customerPaymentDtoToCustomerPayment(customerPaymentDto,customer);
        customerPaymentRepository.save(customerPayment);
        return new GeneralSuccessfullResponse("created payment successfull");
    }

    @Override
    public GeneralResponse getCustomerVerifyCode(long customerId) {
        if(!checkCustomerIdFound(customerId)) throw new CustomerIdCannotFountException();
        if(checkCustomerIsDeleted(customerId)) throw new CustomerDeletedException();
        return new GeneralDataResponse<>(customerPaymentRepository.getCustomerPaymentByCustomerId(customerId).getPaymentVerifyCode());
    }



    @Override
    public GeneralResponse getCustomerAddress(Long id) {
        if(customerRepository.existsById(id)){
            if(checkCustomerIsDeleted(id)) throw new CustomerDeletedException(CustomerResponseMessage.CUSTOMER_CANNOT_ACCESS_DELETED_EXCEPTION);
            Customer customer=customerRepository.getById(id);
            return new GeneralDataResponse<>(CustomerResponseMessage.CUSTOMER_REQUEST_SUCCESSFULL,true,customer.getCustomerAddress());
        }
        throw  new CustomerIdCannotFountException(CustomerResponseMessage.CUSTOMER_ID_CANNOT_FOUND_EXCEPTION);
    }

    @Override
    public GeneralResponse getCustomerById(long id) {
        if(checkCustomerIsDeleted(id)) throw new CustomerDeletedException(CustomerResponseMessage.CUSTOMER_CANNOT_ACCESS_DELETED_EXCEPTION);
        return new GeneralDataResponse<>(converterService.getCustomerConverterService().customerToCustomerResponse(customerRepository.findById(id).get()));
    }

    @Override
    public GeneralResponse deleteCustomerById(long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        if (!Objects.isNull(customer)) {
            if(checkCustomerIsDeleted(customerId)) throw new CustomerDeletedException();
            customer.setDeletedAt(new Date());
            customer.setDeletedBy("EnginAkin");
            customer.setIsDeleted(true);
            customerRepository.save(customer);
            return new GeneralSuccessfullResponse(CustomerResponseMessage.CUSTOMER_DELETED_SUCCESSFULL);
        }
        throw new CustomerIdCannotFountException();
    }
    @Override
    public GeneralResponse addBasketItemToCustomerBasket(BasketItemDto basketItemDto, long customerId) {
        if(checkCustomerIsDeleted(customerId)) throw new CustomerDeletedException(CustomerResponseMessage.CUSTOMER_DELETED_CANNOT_ADDED_BASKET_ITEM_EXCEPTION);
        if(!checkExistsProductId(basketItemDto.getProductId())) throw new ProductIdCannotFoundException();
        if(compareAddedQuantityToProductQuantity(basketItemDto.getProductId(),basketItemDto.getQuantity())) throw new CustomerBasketQuantityException();
        Customer customer=customerRepository.findById(customerId).get();
        BasketItem basketItem=converterService.getCustomerConverterService().basketItemDtoToBasket(basketItemDto,customerId);
        if(Objects.isNull(customer.getBasket())){// sepet yoksa sepet oluşturulmalı varsa olan sepete eklenmeli
            Basket basket=new Basket();
            basketItem.setBasket(basket);
            basketItem.calculateBasketItemPrice();
            basket.addBasketItemToBasket(basketItem);
            customer.setBasket(basket);
            customerRepository.save(customer);
            return new GeneralSuccessfullResponse("basket items added successfull");
        }
        basketItem.setBasket(customer.getBasket());
        basketItem.calculateBasketItemPrice();
        customer.getBasket().addBasketItemToBasket(basketItem);
        customerRepository.save(customer);
        return new GeneralSuccessfullResponse("basket items added successfull");
    }

    @Override
    public GeneralResponse getCustomerBasket(long customerId) {
        if(checkCustomerIsDeleted(customerId)) throw new CustomerDeletedException(CustomerResponseMessage.CUSTOMER_DELETED_BASKET_CANNOT_ACCESS_EXCEPTION);
        if(!customerRepository.existsById(customerId)) throw new CustomerIdCannotFountException();
        Basket customerBasketById=customerRepository.findById(customerId).get().getBasket();
        BasketResponse basketResponse =converterService.getBasketConverterService().basketToBasketResponse(customerBasketById);
        return new GeneralDataResponse("Getting basket is successfull",true, basketResponse);
    }

    @Override
    public GeneralResponse getCustomerProduct(long customerId) {
        if(!customerRepository.existsById(customerId)) throw new CustomerIdCannotFountException();
        Basket basket = customerRepository.findById(customerId).get().getBasket();
        if(Objects.isNull(basket)) throw new CustomerBasketNullException();

        List<ProductResponse> products=basket.getItems().stream().map(basketItem-> converterService.getProductConverterService().productToProductResponse(basketItem.getProduct())).collect(Collectors.toList());
        return new GeneralDataResponse("Getting products is successfully",true,products);
    }

    @Override
    public GeneralResponse getAllCustomer() {
        List<Customer> customers = customerRepository.getCustomerUnDeleted();
        List<CustomerResponse> customerResponses=customers.stream().map(customer -> converterService.getCustomerConverterService().customerToCustomerResponse(customer)).collect(Collectors.toList());
        return new GeneralDataResponse<>(CustomerResponseMessage.CUSTOMER_REQUEST_SUCCESSFULL,true,customerResponses);
    }



    private boolean compareAddedQuantityToProductQuantity(Long productId, BigDecimal quantity) {
        return productRepository.findById(productId).get().getStock().getQuantity().compareTo(quantity)<0;
    }
    public Boolean checkExistsProductId(long productId){
        return productRepository.existsById(productId);
    }
    private boolean checkCustomerIsDeleted(long customerId) {
        if(customerRepository.findById(customerId).get().getIsDeleted()) return true;
        return false;
    }
    private boolean checkCustomerIdFound(long customerId) {
        return customerRepository.existsById(customerId);
    }

}
