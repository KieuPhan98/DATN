package m07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import m07.entity.OrderForSuplierDetail;
import m07.repository.OrderForSupplyDetailRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderForSupplyDetailRepository orderForSupplyDetailRepository;
	
	public List<OrderForSuplierDetail> listAll() {
        return orderForSupplyDetailRepository.findAll();
    }
}
