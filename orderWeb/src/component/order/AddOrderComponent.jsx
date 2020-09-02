import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class AddOrderComponent extends Component{

    constructor(props){
        super(props);
        this.state ={
            customerName: '',
            orderDate: '',
            shippingAddress: '',
            total: '',
            message: null
        }
        this.saveOrder = this.saveOrder.bind(this);
    }

    saveOrder = (e) => {
        e.preventDefault();
        let order = {customerName: this.state.customerName, orderDate: this.state.orderDate, shippingAddress: this.state.shippingAddress, total: this.state.total};
        ApiService.addOrder(order)
            .then(res => {
                this.setState({message : 'Order added successfully.'});
                this.props.history.push('/orders');
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    render() {
        return(
            <div>
                <h2 className="text-center">Add Order</h2>
                <form>
                <div className="form-group">
                    <label>Customer Name:</label>
                    <input type="text" placeholder="customerName" name="customerName" className="form-control" value={this.state.customerName} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label>Order Date:</label>
                    <input type="text" placeholder="orderDate" name="orderDate" className="form-control" value={this.state.orderDate} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label>Shippng Address:</label>
                    <input placeholder="Shipping Address" name="shippingAddress" className="form-control" value={this.state.shippingAddress} onChange={this.onChange}/>
                </div>

                <div className="form-group">
                    <label>Total:</label>
                    <input placeholder="Total" name="total" className="form-control" value={this.state.total} onChange={this.onChange}/>
                </div>
                <button className="btn btn-success" onClick={this.saveOrder}>Save</button>
            </form>
    </div>
        );
    }
}

export default AddOrderComponent;