package com.capstone.order_management.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.order_management.entities.OrderDetailsItems;
import com.capstone.order_management.entities.OrderDetailsItemsHistory;
import com.capstone.order_management.entities.OrderHistory;
import com.capstone.order_management.entities.OrderMaster;
import com.capstone.order_management.entities.pojos.CreateOrderRequest;
import com.capstone.order_management.entities.pojos.CreateOrderRequestItems;
import com.capstone.order_management.entities.pojos.OrderDetailsItemHistoryPojo;
import com.capstone.order_management.entities.pojos.OrderHistoryPojo;
import com.capstone.order_management.entities.pojos.OrderItemsPojo;
import com.capstone.order_management.entities.pojos.OrdersDataPojo;
import com.capstone.order_management.entities.pojos.UpdateItemsPojo;
import com.capstone.order_management.entities.pojos.UpdateOrderRequest;
import com.capstone.order_management.repository.OrderDetailsItemRepository;
import com.capstone.order_management.repository.OrderDetailsItemsHistoryRepo;
import com.capstone.order_management.repository.OrderHistoryRepo;
import com.capstone.order_management.repository.OrderMasterRepo;
import com.capstone.order_management.service.OrderManagementService;
import com.capstone.order_management.wrapper.CreateOrderRequestWrapper;
import com.capstone.order_management.wrapper.OrderDataWrapper;
import com.capstone.order_management.wrapper.OrderDetailsItemsHistoryWrapper;
import com.capstone.order_management.wrapper.OrderHistoryResponsePojo;
import com.capstone.order_management.wrapper.OrderLineItemsWrapper;
import com.capstone.order_management.wrapper.OrderMasterResponsePojo;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

	@Autowired
	OrderHistoryRepo orderHistoryRepo;

	@Autowired
	OrderMasterRepo orderMasterRepo;

	@Autowired
	OrderDetailsItemRepository orderDetailsItemRepo;

	@Autowired
	OrderDetailsItemsHistoryRepo orderDetailsItemsHistoryRepo;

	@Override
	public OrderHistoryResponsePojo getOrderDetailsById(Integer orderId) {
		// TODO Auto-generated method stub

		OrderHistoryResponsePojo response = new OrderHistoryResponsePojo();
		try {
			List<OrderHistory> orderDetailsList = orderHistoryRepo.findByOrderId(orderId);
			response.setStatusCode(200);
			response.setStatusMessage("SUCCESS");
			response.setOrderDetailList(orderDetailsList);
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setStatusMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public OrderMasterResponsePojo getOrderMaster(Integer orderId) {
		// TODO Auto-generated method stub
		OrderMasterResponsePojo response = new OrderMasterResponsePojo();

		try {
			OrderMaster orderMaster = orderMasterRepo.findByOrderId(orderId);
			response.setStatusCode(200);
			response.setStatusMessage("SUCCESS");
			response.setOrderMaster(orderMaster);
		} catch (Exception e) {
			response.setStatusCode(-1);
			response.setStatusMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@Override
	public String createOrder(CreateOrderRequestWrapper request) {
		// TODO Auto-generated method stub

		CreateOrderRequest req = request.getMessage();
		System.out.println("Creating Order for request -> {" + request.toString() + "}");

		if (req.getShopId() != null) {
			if (req.getUserType() != null) {
				if (req.getCreateOrderRequestItemList() != null && req.getCreateOrderRequestItemList().size() > 0) {
					OrderMaster orderMaster = new OrderMaster();
					orderMaster.setOrderDate(new Date());
					orderMaster.setShopId(req.getShopId());

					OrderMaster savedMaster = orderMasterRepo.save(orderMaster);
					System.out.println("Saved in Order Master");
					for (CreateOrderRequestItems c : req.getCreateOrderRequestItemList()) {
						System.out.println("Saving line Item -> {" + c.toString() + "}");
						OrderDetailsItems orderItem = new OrderDetailsItems();
						orderItem.setDistId(c.getDistId());
						orderItem.setInvId(c.getInvId());
						orderItem.setInvName(c.getInvName());
						orderItem.setOrderId(savedMaster.getOrderId());
						orderItem.setQuantity(c.getQuantity());
						orderItem.setStatusId(c.getStatusId());
						orderDetailsItemRepo.save(orderItem);
						System.out.println("Saved Line Item");

						OrderDetailsItemsHistory historyEntity = new OrderDetailsItemsHistory();
						historyEntity.setComments(c.getOrderDescription());
						historyEntity.setInvId(c.getInvId());
						historyEntity.setUpdatedBy(req.getUpdatedBy());
						historyEntity.setOrderId(savedMaster.getOrderId());
						historyEntity.setStatusId(1);
						historyEntity.setUpdateDate(new Date());
						System.out.println("Saving Line History ->{" + historyEntity.toString() + "}");
						orderDetailsItemsHistoryRepo.save(historyEntity);
						System.out.println("Saved Line History");
					}

//					OrderHistory orderHistory = new OrderHistory();
//					orderHistory.setComment(req.getComment());
//					orderHistory.setLastUpdateDate(new Date());
//					orderHistory.setOrderId(savedMaster.getOrderId());
//					orderHistory.setStatusId(1);
//					orderHistory.setUpdatedBy(req.getUserId());
//					
//					orderHistoryRepo.save(orderHistory);

					return savedMaster.getOrderId() + "";

				} else {
					return "0-Items list can not be empty";
				}
			} else {
				return "0-User Type not found";
			}
		} else {
			return "0-User Id not found";
		}
	}

	@Override
	public String updateOrder(UpdateOrderRequest request) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Updating Order -> {" + request.toString() + "}");
			OrderHistory history = new OrderHistory();
			history.setComment(request.getComment());
			history.setLastUpdateDate(new Date());
			history.setOrderId(request.getOrderId());
			history.setStatusId(request.getStatusId());
			history.setUpdatedBy(request.getUserId());

			orderHistoryRepo.save(history);

			System.out.println("Updating Order master");
			OrderMaster om = orderMasterRepo.findByOrderId(request.getOrderId());
//			om.setStatusId(request.getStatusId());
			orderMasterRepo.save(om);

			return "SUCCESS";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getLocalizedMessage();
		}

	}

	@Override
	public OrderDataWrapper getOrdersForUser(String userType, String workplaceId) {
		// TODO Auto-generated method stub
		OrderDataWrapper response = new OrderDataWrapper();
		response.setMessage(new ArrayList<>());
		response.setStatus("SUCCESS");
		response.setStatusCode(200);

		if (userType.equalsIgnoreCase("d")) {
			// distributor user
			System.out.println("Getting orders for DISTRIBUTOR with workplaceid -> {" + workplaceId + "}");
			Map<Integer, List<OrderDetailsItems>> data = new HashMap<>();
			List<OrderDetailsItems> detailItemList = orderDetailsItemRepo.findByDistId(Integer.parseInt(workplaceId));
			List<Integer> orderIds = new ArrayList<>();
			if (detailItemList != null && detailItemList.size() > 0) {
				for (OrderDetailsItems o : detailItemList) {
					if (data.containsKey(o.getOrderId())) {
						data.get(o.getOrderId()).add(o);
					} else {
						data.put(o.getOrderId(), new ArrayList<>());
						orderIds.add(o.getOrderId());
						data.get(o.getOrderId()).add(o);
					}
				}
				for (Integer i : orderIds) {
					OrdersDataPojo p = generateOrderResponse(orderMasterRepo.findByOrderId(i), data.get(i),
							orderHistoryRepo.findByOrderId(i));
					response.getMessage().add(p);
				}
			}
		} else {
			// shop user
			System.out.println("Getting Orders for SHOP user with workplace ID -> {" + workplaceId + "}");
			List<OrderMaster> orderMasterList = orderMasterRepo.findByShopId(Integer.parseInt(workplaceId));
			if (orderMasterList != null && orderMasterList.size() > 0) {
				for (OrderMaster o : orderMasterList) {
					OrdersDataPojo p = generateOrderResponse(o, orderDetailsItemRepo.findByOrderId(o.getOrderId()),
							orderHistoryRepo.findByOrderId(o.getOrderId()));

					response.getMessage().add(p);
				}

			}

		}
		System.out.println("Returning Orders -> {\n" + response.toString() + "\n}");
		return response;
	}

	public OrdersDataPojo generateOrderResponse(OrderMaster om, List<OrderDetailsItems> items,
			List<OrderHistory> history) {

		System.out.println("Generating response pojo for -> OM -{" + om.toString() + "}\n items - {" + items.toString()
				+ "}\n history - {" + history + "}");
		OrdersDataPojo response = new OrdersDataPojo();

		List<OrderItemsPojo> itemsList = new ArrayList<>();
		List<OrderHistoryPojo> historyList = new ArrayList<>();

		for (OrderDetailsItems i : items) {
			OrderItemsPojo p = new OrderItemsPojo();
			p.setDistId(i.getDistId());
			p.setId(i.getId());
			p.setInvId(i.getInvId());
			p.setOrderId(i.getOrderId());
			p.setQuantity(i.getQuantity());
			p.setInvName(i.getInvName());
			p.setStatusId(i.getStatusId());
			itemsList.add(p);
		}

		for (OrderHistory h : history) {
			OrderHistoryPojo hist = new OrderHistoryPojo();
			hist.setComment(h.getComment());
			hist.setId(h.getId());
			hist.setLastUpdateDate(h.getLastUpdateDate());
			hist.setOrderId(h.getOrderId());
			hist.setStatusId(h.getStatusId());
			hist.setUpdatedBy(h.getUpdatedBy());
			historyList.add(hist);
		}

//		response.setHistory(historyList);
		response.setItems(itemsList);
		response.setOrderDate(om.getOrderDate());
		response.setOrderId(om.getOrderId());
		response.setShopId(om.getShopId());
//		response.setStatusId(om.getStatusId());

		return response;
	}

	@Override
	public OrderLineItemsWrapper getOrderLineItems(String orderId, String userId, String userType) {
		// TODO Auto-generated method stub
		OrderLineItemsWrapper wrapper = new OrderLineItemsWrapper();
		System.out
				.println("Getting line items for order " + orderId + " for user +" + userId + " and type " + userType);

		System.out.println("Sending line items -> {" + wrapper.toString() + "}");
		return wrapper;
	}

	@Override
	public String updateOrderItems(List<UpdateItemsPojo> request) {
		// TODO Auto-generated method stub
		System.out.println("Updating Order Line Items");
		for (UpdateItemsPojo u : request) {
			System.out.println("Updating item -> {" + u.toString() + "}");
			OrderDetailsItems i = orderDetailsItemRepo.findByOrderIdAndInvIdAndDistId(u.getOrderId(), u.getInvId(),
					u.getDistId());
			i.setStatusId(u.getStatusId());
			orderDetailsItemRepo.save(i);
			System.out.println("Item Updated -> {" + i.toString() + "}");
			OrderDetailsItemsHistory h = new OrderDetailsItemsHistory();
			h.setComments(u.getComments());
			h.setInvId(u.getInvId());
			h.setOrderId(u.getOrderId());
			h.setStatusId(u.getStatusId());
			h.setUpdatedBy(u.getUpdatedBy());
			h.setUpdateDate(new Date());
			System.out.println("Adding to item history -> {" + h.toString() + "}");
			orderDetailsItemsHistoryRepo.save(h);
			System.out.println("History Item saved -> {" + h.toString() + "}");

		}
		System.out.println("Update Order Items Successful");

		return "SUCCESS";
	}

	@Override
	public OrderDetailsItemsHistoryWrapper getItemHistory(String invId, String orderId) {
		// TODO Auto-generated method stub
		OrderDetailsItemsHistoryWrapper wrapper = new OrderDetailsItemsHistoryWrapper();
		System.out.println("Getting history for item " + invId + " and orderId " + orderId);
		List<OrderDetailsItemsHistory> historyList = orderDetailsItemsHistoryRepo
				.findAllByInvIdAndOrderId(Integer.parseInt(invId), Integer.parseInt(orderId));
		List<OrderDetailsItemHistoryPojo> listPojo = new ArrayList<>();
		for(OrderDetailsItemsHistory h : historyList) {
			OrderDetailsItemHistoryPojo o = new OrderDetailsItemHistoryPojo();
			o.setComments(h.getComments());
			o.setInvId(h.getInvId());
			o.setOrderId(h.getOrderId());
			o.setStatusId(h.getStatusId());
			o.setUpdateDate(h.getUpdateDate());
			o.setUpdatedBy(h.getUpdatedBy());
			listPojo.add(o);
		}
		wrapper.setMessage(listPojo);
		System.out.println("Sending wrapper response -> {"+wrapper.toString()+"}");
		return wrapper;
	}

}
