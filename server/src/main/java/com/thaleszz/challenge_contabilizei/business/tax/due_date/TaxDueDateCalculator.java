package com.thaleszz.challenge_contabilizei.business.tax.due_date;

import java.time.LocalDateTime;
import java.time.YearMonth;

public class TaxDueDateCalculator implements TaxDueDateStrategy {
    @Override
    public LocalDateTime calculate(YearMonth referenceDate) {
        return referenceDate
                .plusMonths(1)
                .atEndOfMonth()
                .atTime(23, 59, 59);
    }
}
