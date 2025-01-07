package dev.vengateshm.compose_material3.di.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class PaymentGatewayModule {
    @Binds
    abstract fun bindPaymentGateway(stripePaymentGateway: StripePaymentGateway): PaymentGateway
}