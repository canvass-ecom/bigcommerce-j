/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom.util;

import cnv.bigcom.BigCommerceClient;
import cnv.bigcom.model.Order;
import cnv.bigcom.model.Order.Item;
import cnv.bigcom.services.OrderService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class OrderMap {

    private Map<Long, CustomerOrders> orders = new HashMap<Long, CustomerOrders>();
    BigCommerceClient client;

    public OrderMap(BigCommerceClient client, List<Order> orders) {
        this.client = client;
        add(orders);
    }

    public Map<Long, CustomerOrders> getOrders() {
        return orders;
    }

    public void add(Order order) {
        CustomerOrders custOrder = orders.get(order.getCustomerId());
        if (custOrder == null) {
            custOrder = new CustomerOrders(order.getId(), order.getOrderDate());
            try {
                List<Order.Item> items = client.getService(OrderService.class).getOrderItems(order.getId());
                if (items != null && !items.isEmpty()) {
                    Item item = items.get(0);
                    custOrder.lastProductId = item.getProductId();
                }
            } catch (Exception e) {
                System.out.println("Error while loading order products: " + e);
            }
            orders.put(order.getCustomerId(), custOrder);
        }
        custOrder.totalOrders += 1;
        custOrder.totalSpent += order.getTotalPrice();
    }

    public final void add(List<Order> orders) {
        for (Order order : orders) {
            add(order);
        }
    }

    @Override
    public String toString() {
        return "OrderMap{" + "Unique Customer=" + orders.size() + '}';
    }

    public class CustomerOrders {

        int totalOrders;
        float totalSpent;
        Date lastOrderDate;
        long lastOrderId;
        long lastProductId;

        public CustomerOrders(long lastOrderId, Date lastOrderDate) {
            this.lastOrderDate = lastOrderDate;
            this.lastOrderId = lastOrderId;
        }

        public Date getLastOrderDate() {
            return lastOrderDate;
        }

        public int getTotalOrders() {
            return totalOrders;
        }

        public float getTotalSpent() {
            return totalSpent;
        }

        public long getLastOrderId() {
            return lastOrderId;
        }

        public long getLastProductId() {
            return lastProductId;
        }
    }
}
