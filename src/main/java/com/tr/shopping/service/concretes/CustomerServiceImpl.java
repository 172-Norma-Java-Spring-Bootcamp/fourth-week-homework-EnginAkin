package com.tr.shopping.service.concretes;

import com.tr.shopping.core.model.dto.CustomerCouponDto;
import com.tr.shopping.core.model.dto.CustomerPaymentDto;
import com.tr.shopping.entity.*;
import com.tr.shopping.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.concretes.ConverterService;
import com.tr.shopping.core.constant.CustomerResponseMessage;
import com.tr.shopping.core.exception.*;
import com.tr.shopping.core.response.GeneralDataResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.response.GeneralSuccessfullResponse;
import com.tr.shopping.core.model.dto.BasketItemDto;
import com.tr.shopping.core.model.dto.CustomerDto;
import com.tr.shopping.core.model.response.BasketResponse;
import com.tr.shopping.core.model.response.CustomerResponse;
import com.tr.shopping.core.model.response.ProductResponse;
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
    private final CustomerCouponRepository customerCouponRepository;
    private final ProductRepository productRepository;
    private final ConverterService converterService;
    private final CustomerPaymentRepository customerPaymentRepository;
    private final CategoryRepository categoryRepository;



    @Override
    public GeneralResponse createCustomer(CustomerDto customerDto) {
        Customer customer=converterService.getCustomerConverterService().customerDtoToCustomer(customerDto);
        log.info("customer {} saved successfull in dataabase ",customer.getEmail());
        customerRepository.saveAndFlush(customer);
       return new GeneralSuccessfullResponse(CustomerResponseMessage.CUSTOMER_CREATED_SUCCESSFULL);
    }
    @Override
    public GeneralResponse addCustomerPayment(CustomerPaymentDto customerPaymentDto,long customerId) {
        if(!customerRepository.existsById(customerId)) throw new CustomerIdCannotFountException();
        Customer customer=customerRepository.findById(customerId).get();
        CustomerPayment customerPayment=converterService.getPaymentConverterService().customerPaymentDtoToCustomerPayment(customerPaymentDto,customer);
        log.info("customer {} ->  payment saved successfull in customer payment database ",customer.getEmail());
        customerPaymentRepository.save(customerPayment);
        return new GeneralSuccessfullResponse("created payment successfull");
    }

    @Override
    public GeneralResponse getCustomerVerifyCode(long customerId) {
        if(!checkCustomerIdFound(customerId)) throw new CustomerIdCannotFountException();
        if(checkCustomerIsDeleted(customerId)) throw new CustomerDeletedException();
        log.info("Customer verified code returned successfull");
        return new GeneralDataResponse<>(customerPaymentRepository.getCustomerPaymentByCustomerId(customerId).getPaymentVerifyCode(),true,"Customer verified code returned successful");
    }

    @Override
    public GeneralResponse createCouponForCustomer(long customerId, CustomerCouponDto customerCouponDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerIdCannotFountException::new);
        CustomerCoupon coupon=converterService.getCustomerCouponConverterService().customerCouponDtoToCustomerCoupon(customerCouponDto);
        Category category=categoryRepository.getCategoryByName(customerCouponDto.getCategory().getName());
        if(Objects.isNull(category)) throw new CategoryCannotFoundException();
        coupon.setCategory(category);
        coupon.setCustomer(customer);
        customer.addCouponCustomer(coupon);
        customerCouponRepository.save(coupon);
        log.info("Customer coupon created successfull for ->",customer.getEmail());
        return new GeneralSuccessfullResponse("Cupon created successfull");
    }

    @Override
    public GeneralResponse getCustomerAddress(long id) {
        if(customerRepository.existsById(id)){
            if(checkCustomerIsDeleted(id)) throw new CustomerDeletedException(CustomerResponseMessage.CUSTOMER_CANNOT_ACCESS_DELETED_EXCEPTION);
            Customer customer=customerRepository.getById(id);
            log.info("Customer addresses returned successful  ->",customer.getCustomerAddress());
            return new GeneralDataResponse<>(CustomerResponseMessage.CUSTOMER_REQUEST_SUCCESSFULL,true,customer.getCustomerAddress());
        }
        log.info("Customer addresses returned failed ,  Customer id cannot found ->");
        throw  new CustomerIdCannotFountException(CustomerResponseMessage.CUSTOMER_ID_CANNOT_FOUND_EXCEPTION);
    }

    @Override
    public GeneralResponse getCustomerById(long id) {
        if(checkCustomerIsDeleted(id)) throw new CustomerDeletedException(CustomerResponseMessage.CUSTOMER_CANNOT_ACCESS_DELETED_EXCEPTION);
        log.info("Customer returned successfull ");

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
            log.info("Customer deleted successfull {} ->",customer.getEmail());
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
        if(Objects.isNull(customer.getBasket())){
            Basket basket=new Basket();
            basketItem.setBasket(basket);
            basketItem.calculateBasketItemPrice();
            basket.addBasketItemToBasket(basketItem);
            customer.setBasket(basket);
            customerRepository.save(customer);
            log.info("Customer basket created and basket item added to basket successfull->{}",customer.getEmail());
            return new GeneralSuccessfullResponse("basket item added to basket  successfull");
        }
        basketItem.setBasket(customer.getBasket());
        basketItem.calculateBasketItemPrice();
        customer.getBasket().addBasketItemToBasket(basketItem);
        customerRepository.save(customer);
        log.info("basket item added to basket  successfull ->{}",customer.getEmail());
        return new GeneralSuccessfullResponse("basket item added to basket  successfull");
    }

    @Override
    public GeneralResponse getCustomerBasket(long customerId) {
        if(checkCustomerIsDeleted(customerId)) throw new CustomerDeletedException(CustomerResponseMessage.CUSTOMER_DELETED_BASKET_CANNOT_ACCESS_EXCEPTION);
        if(!customerRepository.existsById(customerId)) throw new CustomerIdCannotFountException();
        Basket customerBasketById=customerRepository.findById(customerId).get().getBasket();
        BasketResponse basketResponse =converterService.getBasketConverterService().basketToBasketResponse(customerBasketById);
        log.info("Returned basket response successfull.");
        return new GeneralDataResponse("Getting basket is successfull",true, basketResponse);
    }

    @Override
    public GeneralResponse getCustomerProduct(long customerId) {
        if(!customerRepository.existsById(customerId)) throw new CustomerIdCannotFountException();
        Basket basket = customerRepository.findById(customerId).get().getBasket();
        if(Objects.isNull(basket)) throw new CustomerBasketNullException();
        List<ProductResponse> products=basket.getItems().stream().map(basketItem-> converterService.getProductConverterService().productToProductResponse(basketItem.getProduct())).collect(Collectors.toList());
        log.info("getCustomerProduct : customer basket items :{} ",products);
        return new GeneralDataResponse("Getting products is successfully",true,products);
    }

    @Override
    public GeneralResponse getAllCustomer() {
        List<Customer> customers = customerRepository.getCustomerUnDeleted();
        List<CustomerResponse> customerResponses=customers.stream().map(customer -> converterService.getCustomerConverterService().customerToCustomerResponse(customer)).collect(Collectors.toList());
        log.info("get all customer : successfull returned all customer ");
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
