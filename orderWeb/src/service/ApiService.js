import axios from 'axios';

const ORDER_API_BASE_URL = 'http://localhost:9090/order/orders';

class ApiService {

    getOrders() {
        return axios.get(ORDER_API_BASE_URL);
    }

    getOrderItem(orderItem) {
        return axios.get(ORDER_API_BASE_URL + '/' + orderItem);
    }

    deleteOrder(orderItem) {
        return axios.delete(ORDER_API_BASE_URL + '/' + orderItem);
    }

    addOrder(order) {
        return axios.post(""+ORDER_API_BASE_URL, order);
    }

    editOrder(order) {
        return axios.put(ORDER_API_BASE_URL + '/' + order.orderItem, order);
    }

}

export default new ApiService();