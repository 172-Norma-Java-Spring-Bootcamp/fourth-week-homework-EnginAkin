package com.tr.shopping.service.concretes;

import com.tr.shopping.entity.Basket;
import com.tr.shopping.repository.BasketRepository;
import com.tr.shopping.service.abstracts.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    @Override
    public Basket getById(Long id) {
        return basketRepository.findById(id).get();
    }
}
