import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListOrderComponent from "./component/order/ListOrderComponent";
import AddOrderComponent from "./component/order/AddOrderComponent";
import EditOrderComponent from "./component/order/EditOrderComponent";

function App() {
  return (
    <div className="container">
    <Router>
        <div className="col-md-6">
            <h1 className="text-center" style={style}>Order Application</h1>
            <Switch>
                <Route path="/" exact component={ListOrderComponent} />
                <Route path="/orders" component={ListOrderComponent} />
                <Route path="/add-order" component={AddOrderComponent} />
                <Route path="/edit-order" component={EditOrderComponent} />
            </Switch>
        </div>
    </Router>
</div>
  );
}

const style = {
  color: 'red',
  margin: '10px'
}

export default App;
