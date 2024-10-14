package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.tax.Tax;

import java.util.Collection;
import java.util.List;

public interface PaymentService {

    Tax pay(Tax tax);

    List<Tax> pay(Collection<Tax> taxes);

}
