import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class ListOrderComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            orders: [],
            message: null
        }
        this.deleteOrder = this.deleteOrder.bind(this);
        this.editOrder = this.editOrder.bind(this);
        this.addOrder = this.addOrder.bind(this);
        this.getOrders = this.getOrders.bind(this);
    }

    componentDidMount() {
        this.getOrders();
    }

    getOrders() {
        ApiService.getOrders()
            .then((res) => {
                //this.setState({orders: res.data.result})
                this.setState({orders: res.data})
            });
    }

    deleteOrder(orderItem) {
        ApiService.deleteOrder(orderItem)
           .then(res => {
               this.setState({message : 'Order deleted successfully.'});
               this.setState({orders: this.state.orders.filter(order => order.orderItem !== orderItem)});
           })

    }

    editOrder(id) {
        window.localStorage.setItem("orderItem", id);
        this.props.history.push('/edit-order');
    }

    addOrder() {
        window.localStorage.removeItem("orderItem");
        this.props.history.push('/add-order');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Order Details</h2>
                <button className="btn btn-danger" onClick={() => this.addOrder()}> Add Order</button>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            {/*<th className="hidden">Order Item</th>*/}
                            <th>Order Item</th>
                            <th>Customer Name</th>
                            <th>Order Date</th>
                            <th>Shipping Address</th>
                            <th>Total$</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.orders.map(
                                order =>
                                    <tr> <td>{order.orderItem}</td>
                                        <td>{order.customerName}</td>
                                        {/*<td>{order.orderDate}</td>*/}
                                        <td>{new Date(order.orderDate).getDate()+"/"+new Date(order.orderDate).getMonth()+"/"+new Date(order.orderDate).getFullYear()}</td>
                                        <td>{order.shippingAddress}</td>
                                        <td>{order.total}</td>
                                        <td>
                                            <button className="btn btn-success" onClick={() => this.deleteOrder(order.orderItem)}> Delete</button>
                                            <button className="btn btn-success" onClick={() => this.editOrder(order.orderItem)}> Edit</button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>

            </div>
        );
    }

}

export default ListOrderComponent;