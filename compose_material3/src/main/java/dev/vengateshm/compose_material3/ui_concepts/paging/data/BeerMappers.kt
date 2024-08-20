package dev.vengateshm.compose_material3.ui_concepts.paging.data

import dev.vengateshm.compose_material3.ui_concepts.paging.data.local.BreweryEntity
import dev.vengateshm.compose_material3.ui_concepts.paging.data.remote.BreweryDto
import dev.vengateshm.compose_material3.ui_concepts.paging.domain.Brewery

fun BreweryDto.toEntity(): BreweryEntity {
    return BreweryEntity(
        id = this.id,
        name = this.name,
        type = this.brewery_type,
        address1 = this.address_1,
        address2 = this.address_2,
        address3 = this.address_3,
        city = this.city,
        stateProvince = this.state_province,
        postalCode = this.postal_code,
        country = this.country,
        longitude = this.longitude,
        latitude = this.latitude,
        phone = this.phone,
        websiteUrl = this.website_url,
        state = this.state,
        street = this.street
    )
}

fun BreweryEntity.toBrewery(): Brewery {
    return Brewery(
        id = this.id,
        name = this.name,
        type = this.type,
        address1 = this.address1.orEmpty(),
        address2 = this.address2.orEmpty(),
        address3 = this.address3.orEmpty(),
        city = this.city,
        stateProvince = this.stateProvince.orEmpty(),
        postalCode = this.postalCode,
        country = this.country,
        longitude = this.longitude.orEmpty(),
        latitude = this.latitude.orEmpty(),
        phone = this.phone.orEmpty(),
        websiteUrl = this.websiteUrl.orEmpty(),
        state = this.state.orEmpty(),
        street = this.street.orEmpty()
    )
}