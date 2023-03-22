package com.week.zumgnmarket.order.service;

import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.user.entity.User;

public interface OrderService {
	Order orderTicket(User user, Ticket ticket, int quantity);
}
