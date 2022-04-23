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
import com.tr.shopping.repository.OrderDetailRepository;
import com.tr.shopping.service.abstracts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerService customerService;
    private final CustomerPaymentService customerPaymentService;
    private final BasketService basketService;
    private final OrderItemService orderItemService;
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
        Basket basket = basketService.getById(orderItemDto.getBasketId());
        Customer customer = customerService.getCustomerById(orderItemDto.getCustomerId()).getData();
        CustomerPayment customerPayment=customerPaymentService.getCustomerPaymentByCustomerId(orderItemDto.getCustomerId());
        // payment status
        PaymentStatus paymentStatus=new PaymentStatus();
        paymentStatus.setName(OrderConstant.ORDER_STATUS_PENDING);
        log.info("create order : payment status created  : {}",paymentStatus.getName());

        // payment details
        PaymentDetail paymentDetail=new PaymentDetail();
        paymentDetail.setAmount(basket.getTotalPrice());
        paymentDetail.setProvider(OrderConstant.PAYMENT_PROVIDER_PAYPAL);
        paymentDetail.setStatus(paymentStatus);
        log.info("create order : payment detail created  payment provider : {} - payment amount -> {}",paymentDetail.getProvider(),paymentDetail.getAmount());


        // order status
        OrderStatus orderStatus=new OrderStatus();
        orderStatus.setName(OrderConstant.ORDER_STATUS_PROCESS);
        log.info("create order : order status created order status  : {}",orderStatus.getName());

        // shippin method
        ShipMethod shipMethodUpfs=new ShipMethod();
        shipMethodUpfs.setName(OrderConstant.SHIP_METHOD_UPFS);
        log.info("create order : shipping method created   : {}",shipMethodUpfs.getName());

        OrderItem orderItem=new OrderItem();
        orderItem.setBasket(basket);

        OrderDetail orderDetail=new OrderDetail();
        if(checkCustomerHasPromotion(customer)){// check if customer has a promotion
            orderDetail.setTotalAmount(customerDiscountService.applyDiscount(customer.getCoupons().get(0),basket.getTotalPrice()));
            orderDetail.getPaymentDetail().setAmount(customerDiscountService.applyDiscount(customer.getCoupons().get(0),basket.getTotalPrice()));
            log.info("create order : change payment detail and order detail amount -> {}",paymentDetail.getAmount());

        }else{
            orderDetail.setTotalAmount(basket.getTotalPrice());
        }
        orderDetail.setOrderStatus(orderStatus);
        orderDetail.setCreationDate(new Date());
        orderDetail.setCustomer(customer);
        orderDetail.setPaymentDetail(paymentDetail);
        orderDetail.setShipMethod(shipMethodUpfs);
        orderItem.setOrderDetail(orderDetail);

        orderItemService.save(orderItem);
        log.info("order item created successfull");
        String verifyCode=UUID.randomUUID().toString();
        customerPayment.setPaymentVerifyCode(verifyCode);
        customerPaymentService.save(customerPayment); // send verify code for payment
        log.info("create payment code and send verify code for payment verified ->{} ",verifyCode);

        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderStatus("needed complete order with complete payment");
        orderResponse.setPaymentStatus("needed verify payment with code");
        orderResponse.setPaymentAmount(orderDetail.getTotalAmount());
        orderResponse.setTotalAmount(basket.getTotalPrice());
        orderResponse.setApplyedDiscount(basket.getTotalPrice().subtract(orderDetail.getTotalAmount()));
        return new GeneralDataResponse<>("successfull order create",true,orderResponse);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = GeneralException.class)
    @Override
    public GeneralResponse verifyCustomerOrder(long customerId, CustomerPaymentVerifyDto customerPaymentVerifyDto) {
        String verifyCustomerCode=customerPaymentVerifyDto.getVerifyCode();
        CustomerPayment customerPayment = customerPaymentService.getCustomerPaymentByCustomerId(customerId);
        if(!customerPayment.getPaymentVerifyCode().equals(verifyCustomerCode)) throw new VerifyCodeNotMatchException();
        log.info("verifyCustomerOrder: verify accepted :{}",customerPaymentVerifyDto.getVerifyCode());
        // this senario like add payment ziraat bank and wait response
        if(bankServiceAdapter.addPayment(customerPayment.getAccountNo(),customerPayment.getExpiry(),customerPayment.getPaymentType(),customerPayment.getProvider())){
            OrderDetail orderDetailByCustomer = orderDetailRepository.getOrderDetailByCustomerId(customerId);
            orderDetailByCustomer.getOrderStatus().setName(OrderConstant.PAYMENT_STATUS_ACCEPT);
            orderDetailByCustomer.getPaymentDetail().getStatus().setName(OrderConstant.PAYMENT_STATUS_ACCEPT);
            orderDetailRepository.save(orderDetailByCustomer);
            customerPayment.setPaymentVerifyCode(null); // delete verify code customer payment table
            customerPaymentService.save(customerPayment);
            log.info("verifyCustomerOrder: verify accepted  and bank returned true for payment");
            return new GeneralSuccessfullResponse("Code verified.Payment Successfull");
        }
        log.info("verifyCustomerOrder: verify accepted  but bank returned false for payment");

        return new GeneralErrorResponse("Payment unsuccessfull.!!.");
    }


    @Override
    public GeneralResponse getCustomerOrderById(long customerId) {
        if(!checkCustomerHasOrderByCustomerId(customerId)) throw new OrderCannotFoundException();
        OrderDetail orderDetail=orderDetailRepository.getOrderDetailByCustomerId(customerId);
        log.info("getCustomerOrderById : Customer order returned successfull : {}",orderDetail.getOrderStatus());
        return new GeneralDataResponse<>(converterService.getOrderDetailConverterService().orderDetailToOrderDetailResponse(orderDetail));
    }


    private boolean checkCustomerHasPromotion(Customer customer) {
        log.info("checkCustomerHasPromotion : -> Customer coupon size  : {customer.getCoupons().size()>0}");
        return customer.getCoupons().size()>0 ;
    }
    private boolean checkCustomerHasOrderByCustomerId(Long customerId) {
        return Objects.isNull(orderDetailRepository.getOrderDetailByCustomerId(customerId));
    }
    private boolean checkBasketNonExists(Long basketId) {
        return Objects.isNull(basketService.getById(basketId));
    }
    private boolean isBasketBelongCustomer(OrderItemDto orderItemDto) {
        return customerService.getCustomerById(orderItemDto.getCustomerId()).getData().getBasket().getId().equals(orderItemDto.getBasketId());
    }
    private boolean checkCustomerHasNoPayment(Long customerId) {
        return Objects.isNull(customerPaymentService.getCustomerPaymentByCustomerId(customerId));
    }

    private boolean checkCustomerNonExists(Long customerId) {
        return !customerService.getCustomerById(customerId).getIsSuccessful();
    }
}
