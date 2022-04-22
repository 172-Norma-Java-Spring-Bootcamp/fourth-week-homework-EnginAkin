package com.tr.shopping.service.concretes;

import com.tr.shopping.core.constant.OrderConstant;
import com.tr.shopping.core.converter.concretes.ConverterService;
import com.tr.shopping.core.exception.*;
import com.tr.shopping.core.model.dto.CustomerPaymentVerifyDto;
import com.tr.shopping.core.model.dto.OrderItemDto;
import com.tr.shopping.core.model.response.OrderResponse;
import com.tr.shopping.core.response.GeneralDataResponse;
import com.tr.shopping.core.response.GeneralErrorResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.response.GeneralSuccessfullResponse;
import com.tr.shopping.entity.*;
import com.tr.shopping.repository.*;
import com.tr.shopping.service.abstracts.CustomerDiscountService;
import com.tr.shopping.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final CustomerRepository customerRepository;
    private final CustomerPaymentRepository customerPaymentRepository;
    private final BasketRepository basketRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemDetailRepository orderItemDetailRepository;
    private final BankServiceAdapter bankServiceAdapter;
    private final ConverterService converterService;
    private final CustomerDiscountService customerDiscountService;


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = GeneralException.class)
    @Override
    public GeneralResponse createOrder(OrderItemDto orderItemDto)  {
       if(checkCustomerNonExists(orderItemDto.getCustomerId())) throw new CustomerIdCannotFountException();
       if(checkBasketNonExists(orderItemDto.getBasketId())) throw new BasketIdCannotFoundException();
       if(!isBasketBelongCustomer(orderItemDto)) throw new CustomerBasketCannotFoundException();
       if(checkCustomerHasNoPayment(orderItemDto.getCustomerId())) throw new CustomerPaymentCannotFoundException();
        Basket basket = basketRepository.findById(orderItemDto.getBasketId()).get();
        Customer customer = customerRepository.findById(orderItemDto.getCustomerId()).get();
        CustomerPayment customerPayment=customerPaymentRepository.getCustomerPaymentByCustomerId(orderItemDto.getCustomerId());
        // payment status
        PaymentStatus paymentStatus=new PaymentStatus();
        paymentStatus.setName(OrderConstant.ORDER_STATUS_PENDING);

        // payment details
        PaymentDetail paymentDetail=new PaymentDetail();
        paymentDetail.setAmount(basket.getTotalPrice());
        paymentDetail.setProvider(OrderConstant.PAYMENT_PROVIDER_PAYPAL);
        paymentDetail.setStatus(paymentStatus);

        // order status
        OrderStatus orderStatus=new OrderStatus();
        orderStatus.setName(OrderConstant.ORDER_STATUS_PROCESS);

        // shippin method
        ShipMethod shipMethodUpfs=new ShipMethod();
        shipMethodUpfs.setName(OrderConstant.SHIP_METHOD_UPFS);

        OrderItem orderItem=new OrderItem();
        orderItem.setBasket(basket);

        OrderDetail orderDetail=new OrderDetail();
        // check if customer has a promotion
        if(checkCustomerHasPromotion(customer)){
            orderDetail.setTotalAmount(customerDiscountService.applyDiscount(customer.getCoupons().get(0),basket.getTotalPrice()));
        }else{// no discount customer
            orderDetail.setTotalAmount(basket.getTotalPrice());
        }
        orderDetail.setOrderStatus(orderStatus);
        orderDetail.setCreationDate(new Date());
        orderDetail.setCustomer(customer);
        orderDetail.setPaymentDetail(paymentDetail);
        orderDetail.setShipMethod(shipMethodUpfs);
        orderItem.setOrderDetail(orderDetail);

        orderItemRepository.save(orderItem);
        String verifyCode=UUID.randomUUID().toString();
        customerPayment.setPaymentVerifyCode(verifyCode);
        customerPaymentRepository.save(customerPayment); // send verify code for payment

        // create response
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderStatus("needed complete order with complete payment");
        orderResponse.setPaymentStatus("needed verify payment with code");
        orderResponse.setPaymentAmount(orderDetail.getTotalAmount());
        orderResponse.setTotalAmount(basket.getTotalPrice());
        orderResponse.setApplyedDiscount(basket.getTotalPrice().subtract(orderDetail.getTotalAmount()));
        return new GeneralDataResponse<>("successfull order create",true,orderResponse);
    }
    private boolean checkCustomerHasPromotion(Customer customer) {
        // check categori is equals product category
        return customer.getCoupons().size()>0 ;
    }

    @Override
    public GeneralResponse getCustomerOrderById(long customerId) {
        if(!checkCustomerHasOrderByCustomerId(customerId)) throw new OrderCannotFoundException();
        OrderDetail orderDetail=orderItemDetailRepository.getOrderDetailByCustomerId(customerId);
        return new GeneralDataResponse<>(converterService.getOrderDetailConverterService().orderDetailToOrderDetailResponse(orderDetail));
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = GeneralException.class)
    @Override
    public GeneralResponse verifyCustomerOrder(long customerId, CustomerPaymentVerifyDto customerPaymentVerifyDto) {
        // verify customer code , if verify customer code , change order status and payment status , not verify code throw error
        String verifyCustomerCode=customerPaymentVerifyDto.getVerifyCode();
        CustomerPayment customerPayment = customerPaymentRepository.getCustomerPaymentByCustomerId(customerId);
        if(!customerPayment.getPaymentVerifyCode().equals(verifyCustomerCode)) throw new VerifyCodeNotMatchException();

        // this senario like add payment ziraat bank and wait response
        if(bankServiceAdapter.addPayment(customerPayment.getAccountNo(),customerPayment.getExpiry(),customerPayment.getPaymentType(),customerPayment.getProvider())){
            OrderDetail orderDetailByCustomer = orderItemDetailRepository.getOrderDetailByCustomerId(customerId);
            orderDetailByCustomer.getOrderStatus().setName(OrderConstant.PAYMENT_STATUS_ACCEPT);
            orderDetailByCustomer.getPaymentDetail().getStatus().setName(OrderConstant.PAYMENT_STATUS_ACCEPT);
            orderItemDetailRepository.save(orderDetailByCustomer);
            // verified code ,  delete customer verify code in customer payment table
            customerPayment.setPaymentVerifyCode(null);
            customerPaymentRepository.save(customerPayment);
            return new GeneralSuccessfullResponse("Code verified.Payment Successfull");
        }
        return new GeneralErrorResponse("Payment unsuccessfull.!!.");
    }

    private boolean checkCustomerHasOrderByCustomerId(Long customerId) {
        return orderItemDetailRepository.existsOrderDetailByCustomerId(customerId);
    }
    private boolean checkBasketNonExists(Long basketId) {
        return !basketRepository.existsById(basketId);
    }
    private boolean isBasketBelongCustomer(OrderItemDto orderItemDto) {
        return customerRepository.findById(orderItemDto.getCustomerId()).get().getBasket().getId().equals(orderItemDto.getBasketId());
    }
    private boolean checkCustomerHasNoPayment(Long customerId) {
        return !customerPaymentRepository.existsCustomerPaymentByCustomerId(customerId);
    }
    private boolean checkCustomerNonExists(Long customerId) {
        return !customerRepository.existsById(customerId);
    }
}
