
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderOrderManager from "./components/listers/OrderOrderCards"
import OrderOrderDetail from "./components/listers/OrderOrderDetail"

import ProductSearchView from "./components/ProductSearchView"
import ProductSearchViewDetail from "./components/ProductSearchViewDetail"
import DeliveryShippingManager from "./components/listers/DeliveryShippingCards"
import DeliveryShippingDetail from "./components/listers/DeliveryShippingDetail"

import InventoryInventoryManager from "./components/listers/InventoryInventoryCards"
import InventoryInventoryDetail from "./components/listers/InventoryInventoryDetail"

import CustomerCustomerManager from "./components/listers/CustomerCustomerCards"
import CustomerCustomerDetail from "./components/listers/CustomerCustomerDetail"


import OrderStatusView from "./components/OrderStatusView"
import OrderStatusViewDetail from "./components/OrderStatusViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders/orders',
                name: 'OrderOrderManager',
                component: OrderOrderManager
            },
            {
                path: '/orders/orders/:id',
                name: 'OrderOrderDetail',
                component: OrderOrderDetail
            },

            {
                path: '/orders/productSearches',
                name: 'ProductSearchView',
                component: ProductSearchView
            },
            {
                path: '/orders/productSearches/:id',
                name: 'ProductSearchViewDetail',
                component: ProductSearchViewDetail
            },
            {
                path: '/deliveries/shippings',
                name: 'DeliveryShippingManager',
                component: DeliveryShippingManager
            },
            {
                path: '/deliveries/shippings/:id',
                name: 'DeliveryShippingDetail',
                component: DeliveryShippingDetail
            },

            {
                path: '/inventories/inventories',
                name: 'InventoryInventoryManager',
                component: InventoryInventoryManager
            },
            {
                path: '/inventories/inventories/:id',
                name: 'InventoryInventoryDetail',
                component: InventoryInventoryDetail
            },

            {
                path: '/customers/customers',
                name: 'CustomerCustomerManager',
                component: CustomerCustomerManager
            },
            {
                path: '/customers/customers/:id',
                name: 'CustomerCustomerDetail',
                component: CustomerCustomerDetail
            },


            {
                path: '/dashboards/orderStatuses',
                name: 'OrderStatusView',
                component: OrderStatusView
            },
            {
                path: '/dashboards/orderStatuses/:id',
                name: 'OrderStatusViewDetail',
                component: OrderStatusViewDetail
            },


    ]
})
