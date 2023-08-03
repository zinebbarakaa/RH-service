package com.giantLink.RH.services;

import com.giantLink.RH.models.request.PayrollRequest;
import com.giantLink.RH.models.response.PayrollResponse;

import java.util.List;

public interface PayrollService {
    PayrollResponse createPayroll(PayrollRequest payrollRequest);

    PayrollResponse updatePayroll(Long payrollId, PayrollRequest payrollRequest);

    PayrollResponse getPayrollById(Long payrollId);

    List<PayrollResponse> getAllPayrolls();

}
