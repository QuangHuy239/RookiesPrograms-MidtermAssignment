package com.nashtech.MyBikeShop.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.nashtech.MyBikeShop.DTO.OrderDTO;
import com.nashtech.MyBikeShop.entity.OrderEntity;
import com.nashtech.MyBikeShop.entity.PersonEntity;
import com.nashtech.MyBikeShop.entity.ProductEntity;
import com.nashtech.MyBikeShop.exception.JsonGetDataException;
import com.nashtech.MyBikeShop.exception.ObjectNotFoundException;
import com.nashtech.MyBikeShop.repository.OrderRepository;
import com.nashtech.MyBikeShop.services.OrderService;
import com.nashtech.MyBikeShop.services.PersonService;
import com.nashtech.MyBikeShop.services.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductService productService;

	@Autowired
	PersonService personService;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<OrderEntity> retrieveOrders() {
		return orderRepository.findAll();

	}

	public OrderEntity getOrders(int id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Could not find order with Id: " + id));

	}

	@Transactional
	public OrderEntity createOrder(OrderDTO orderDTO) {
//		Optional<OrderEntity> order = orderRepository.findById(orderDTO.getId());
//		if (order.isPresent()) {
//			throw new ObjectAlreadyExistException("There is a product with Id " + order.get().getId());
//		} else {
//		try {
			OrderEntity orderEntity = new OrderEntity(orderDTO);
			return orderEntity;
//			productService.updateProductQuantity(orderEntity.getProducts().getId(), orderEntity.getQuantity() * (-1));
//			orderEntity.setTimebought(LocalDateTime.now());
//			return orderRepository.save(orderEntity);
//		} catch (HttpMessageNotReadableException | JsonParseException | NullPointerException ex) {
//			throw new JsonGetDataException(ex.getMessage());
//		} 
	}

	@Transactional
	public void deleteOrder(int id) {
//		try {
//		OrderEntity orderEntity = getOrders(id);
//		if (!orderEntity.isStatus()) { // False = Not delivery yet
//			productService.updateProductQuantity(orderEntity.getProducts().getId(), orderEntity.getQuantity());
//		}
//		orderRepository.delete(orderEntity);
//		}catch (MethodArgumentTypeMismatchException | NumberFormatException ex) {
//			throw new JsonGetDataException(ex.getMessage());
//		}
	}

	public void updateOrder(OrderDTO orderDTO) {
//		try {
//			OrderEntity orderEntity = getOrders(orderDTO.getId());
//			int quantityChange = orderEntity.getQuantity() - orderDTO.getQuantity();
//			boolean checkProduct = (orderDTO.getProducts().getId().equals(orderEntity.getProducts().getId()));
//			if (!checkProduct) {
//				// Increase quantity of old Product
//				productService.updateProductQuantity(orderEntity.getProducts().getId(), orderEntity.getQuantity());
//				// Decrease quantity of new Product
//				productService.updateProductQuantity(orderDTO.getProducts().getId(), orderDTO.getQuantity() * (-1));
//			} else if (quantityChange != 0 && checkProduct) {
//				productService.updateProductQuantity(orderEntity.getProducts().getId(), quantityChange);
//			}
//			orderRepository.save(new OrderEntity(orderDTO));
//
//		} catch (HttpMessageNotReadableException | JsonParseException | NullPointerException ex) {
//			throw new JsonGetDataException(ex.getMessage());
//		} 
	}

	public List<OrderEntity> findOrderByCustomer(String email) {
		// PersonEntity person = personService.getPerson(email);
		return orderRepository.findByCustomersEmail(email);
	}

	public OrderEntity findOrderByProducts(String id) {
		return orderRepository.findByProductsId(id);
	}
}
