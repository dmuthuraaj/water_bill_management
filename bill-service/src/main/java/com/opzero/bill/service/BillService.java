package com.opzero.bill.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opzero.bill.dto.request.BillAddRequest;
import com.opzero.bill.dto.request.BillUpdateRequest;
import com.opzero.bill.dto.response.BillDetailResponse;
import com.opzero.bill.dto.response.BillSummaryResponse;
import com.opzero.bill.mapper.BillMapper;
import com.opzero.bill.mongo.entity.Bill;
import com.opzero.bill.mongo.repository.BillRepository;
import com.opzero.core.exception.DuplicateResourceException;
import com.opzero.core.exception.ResourceNotExistException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    public boolean add(BillAddRequest request) {
        Optional<Bill> optionalBill = billRepository.findByReadingDate(request.getReadingDate());
        if (optionalBill.isPresent()) {
            throw new DuplicateResourceException("Bill with reading date already exist");
        }
        // Get Device Using grpc
        // Get Client using grpc
        Bill bill = new Bill();
        bill.setClientId(request.getClientId());
        bill.setRate(request.getRate());
        bill.setPreviousReading(request.getPreviousReading());
        bill.setCurrentReading(request.getCurrentReading());
        bill.setReadingDate(request.getReadingDate());
        bill.setStatus(request.getStatus());
        bill.setTotalBill(request.getTotalBill());
        bill.setDueDate(request.getDueDate());
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
