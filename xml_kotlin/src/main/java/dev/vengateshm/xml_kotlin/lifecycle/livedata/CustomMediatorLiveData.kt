package dev.vengateshm.xml_kotlin.lifecycle.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

data class User(val id: Int, val name: String)
data class Orders(val orderId: Int, val userId: Int, val amount: Double)
data class Address(val userId: Int, val city: String, val country: String)
data class CombinedData(val user: User?, val orders: Orders?, val address: Address?)

class CombinedLiveData(
    userLiveData: LiveData<User>,
    ordersLiveData: LiveData<Orders>,
    addressLiveData: LiveData<Address>,
) : MediatorLiveData<CombinedData>() {

    private var currentUser: User? = null
    private var currentOrders: Orders? = null
    private var currentAddress: Address? = null

    init {
        addSource(userLiveData) { user ->
            currentUser = user
            update()
        }
        addSource(ordersLiveData) { orders ->
            currentOrders = orders
            update()
        }
        addSource(addressLiveData) { address ->
            currentAddress = address
            update()
        }
    }

    private fun update() {
        value = CombinedData(currentUser, currentOrders, currentAddress)
    }
}
