package com.opzero.bill.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.opzero.bill.dto.request.BillAddRequest;
import com.opzero.bill.dto.request.BillUpdateRequest;
import com.opzero.bill.dto.response.BillDetailResponse;
import com.opzero.bill.dto.response.BillSummaryResponse;
import com.opzero.bill.mapper.BillMapper;
import com.opzero.bill.mongo.BillingDetails;
import com.opzero.bill.mongo.ConsumerDetails;
import com.opzero.bill.mongo.ConsumptionDetails;
import com.opzero.bill.mongo.PreviousMonthPayment;
import com.opzero.bill.mongo.entity.Bill;
import com.opzero.bill.mongo.repository.BillRepository;
import com.opzero.core.dto.BillStatus;
import com.opzero.core.exception.ResourceNotExistException;
import com.opzero.device.grpc.DeviceServiceGrpc;
import com.opzero.device.grpc.GetReportRequest;
import com.opzero.device.grpc.GetReportResponse;
import com.opzero.user.grpc.GetClientByIdRequest;
import com.opzero.user.grpc.GetClientResponse;
import com.opzero.user.grpc.UserServiceGrpc;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    private final DeviceServiceGrpc.DeviceServiceBlockingStub deviceServiceBlockingStub;

    private final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public boolean add(BillAddRequest request) {
        GetClientResponse clientResponse= userServiceBlockingStub.getClientById(GetClientByIdRequest.newBuilder().setId(request.getClientId()).build());
        if(clientResponse.getIsError()){
            throw new ResourceNotExistException(clientResponse.getError());
        }
        Bill bill = new Bill();
        bill.setClientId(request.getClientId());
        // bill.setBillNumber();
        bill.setStatus(BillStatus.PENDING);
        GetReportResponse reportResponse=deviceServiceBlockingStub.getReport(GetReportRequest.newBuilder().setDeviceId(clientResponse.getDeviceId()).setMonth(request.getMonth()).build());
        if(reportResponse.getCommon().getIsError()){
            throw new ResourceNotExistException(reportResponse.getCommon().getMessage());
        }
        Instant instant = Instant.ofEpochSecond(reportResponse.getReadingDate());
        LocalDate readingDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        bill.setReadingDate(readingDate);
        bill.setDueDate(readingDate.plusDays(30));

        BillingDetails billingDetails = new BillingDetails();
        billingDetails.setWaterCharges(reportResponse.getTotalLitresConsumed()*0.007);
        billingDetails.setMeterCharges(0);
        billingDetails.setTotalAmount(reportResponse.getTotalLitresConsumed()*0.007);
        bill.setBillingDetails(billingDetails);

        ConsumerDetails consumerDetails = new ConsumerDetails();
        consumerDetails.setName(clientResponse.getName());
        consumerDetails.setAddress(clientResponse.getAddress());
        // consumerDetails.setSubDivision(subDivision);
        consumerDetails.setRrNumber(clientResponse.getRrNumber());
        // consumerDetails.setConsumerNumber(consumerNumer);
        bill.setConsumerDetails(consumerDetails);

        ConsumptionDetails consumptionDetials = new ConsumptionDetails();
        // consumptionDetails.setPreviousReading();
        // consumptionDetails.setPresentReading();
        consumptionDetials.setConsumption(reportResponse.getTotalLitresConsumed());
        bill.setConsumptionDetails(consumptionDetials);

        PreviousMonthPayment previousMonthPayment = new PreviousMonthPayment();
        bill.setPreviousMonthPayment(previousMonthPayment);

        billRepository.save(bill);
        return true;
    }

    public List<BillSummaryResponse> getAll() {
        List<Bill> bills = billRepository.findAll();
        if (bills.isEmpty()) {
            throw new ResourceNotExistException("Bill list empty");
        }
        return bills.stream().map(BillMapper::toSummaryResponse).collect(Collectors.toList());
    }

    public BillDetailResponse getById(String id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        if (optionalBill.isEmpty()) {
            throw new ResourceNotExistException("Bill with id does not exist");
        }
        Bill bill = optionalBill.get();
        return BillMapper.toDetailResponse(bill);
    }

    public boolean update(String id, BillUpdateRequest request) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        if (optionalBill.isEmpty()) {
            throw new ResourceNotExistException("Bill with id does not exist");
        }
        Bill bill = optionalBill.get();
        bill.setStatus(request.getStatus());
        return true;
    }
}
