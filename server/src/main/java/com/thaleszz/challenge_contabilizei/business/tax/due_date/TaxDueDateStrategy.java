package com.thaleszz.challenge_contabilizei.business.tax.due_date;

import java.time.LocalDateTime;
import java.time.YearMonth;

public interface TaxDueDateStrategy {
    LocalDateTime calculate(YearMonth referenceDate);
}
