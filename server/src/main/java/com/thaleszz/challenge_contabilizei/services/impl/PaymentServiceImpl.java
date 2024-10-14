package com.thaleszz.challenge_contabilizei.services.impl;

import com.thaleszz.challenge_contabilizei.models.tax.Tax;
import com.thaleszz.challenge_contabilizei.services.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Tax pay(Tax tax) {
        tax.setPaid(true);
        return tax;
    }

    @Override
    public List<Tax> pay(Collection<Tax> taxes) {
        return taxes.stream().peek(this::pay).toList();
    }
}
