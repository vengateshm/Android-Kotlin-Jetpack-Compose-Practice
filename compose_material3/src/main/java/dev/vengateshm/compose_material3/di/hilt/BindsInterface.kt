package dev.vengateshm.compose_material3.di.hilt

import android.util.Log
import javax.inject.Inject

interface PaymentGateway {
    fun pay(amount: Double)
}

class StripePaymentGateway @Inject constructor() : PaymentGateway {
    private val tag = "StripePaymentGateway"
    override fun pay(amount: Double) {
        val message = "Paid $amount using Stripe"
        Log.d(tag, message)
    }
}