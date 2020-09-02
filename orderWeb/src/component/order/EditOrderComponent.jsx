import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class EditOrderComponent extends Component {

    constructor(props){
        super(props);
        this.state ={
            orderItem: '',
            customerName: '',
            orderDate: '',
            shippingAddress: '',
            total: '',
        }
        this.saveOrder = this.saveOrder.bind(this);
        this.loadOrder = this.loadOrder.bind(this);
    }

    componentDidMount() {
        this.loadOrder();
    }

    loadOrder() {
        ApiService.getOrderItem(window.localStorage.getItem("orderItem"))
            .then((res) => {
                let order = res.data.result;
                this.setState({
                    orderItem: order.orderItem,
                    customerName: order.customerName,
                    orderDate: order.orderDate,
                    shippingAddress: order.shippingAddress,
                    total: order.total,
                })
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

        saveOrder = (e) => {
        e.preventDefault();
        let order = {orderItem: this.state.orderItem, customerName: this.state.customerName, orderDate: this.state.orderDate, shippingAddress: this.state.shippingAddress, total: this.state.total};
        ApiService.editOrder(order)
            .then(res => {
                this.setState({message : 'Order added successfully.'});
                this.props.history.push('/orders');
            });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Edit Order</h2>
                <form>

                    <div className="form-group">
                        <label>Customer Name:</label>
                        <input type="text" placeholder="customerName" name="customerName" className="form-control" readonly="true" defaultValue={this.state.customerName}/>
                    </div>

                    <div className="form-group">
                        <label>Order Date:</label>
                        <input placeholder="Order Date" name="orderDate" className="form-control" value={this.state.orderDate} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Shipping Address:</label>
                        <input placeholder="Shipping Address" name="shippingAddress" className="form-control" value={this.state.shippingAddress} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Total:</label>
                        <input type="number" placeholder="total" name="total" className="form-control" value={this.state.total} onChange={this.onChange}/>
                    </div>

                    <button className="btn btn-success" onClick={this.saveOrder}>Save</button>
                </form>
            </div>
        );
    }
}

export default EditOrderComponent;
