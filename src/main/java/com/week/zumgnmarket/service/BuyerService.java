package com.week.zumgnmarket.service;

import com.week.zumgnmarket.entity.Buyer;
import com.week.zumgnmarket.repository.BuyerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BuyerService {

    private final BuyerJpaRepository buyerJpaRepository;

    @Transactional(readOnly = true)
    public Buyer findBuyerById(Integer id) {
        return buyerJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다. idx: " + id));
    }
}
