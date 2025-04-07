package dev.vengateshm.home.comm

import android.content.Context
import androidx.navigation.NavController
import dev.vengateshm.appcore.comm.CommData
import dev.vengateshm.appcore.comm.CommPath
import dev.vengateshm.appcore.comm.CommProcessor
import dev.vengateshm.appcore.comm.CommType
import dev.vengateshm.home.HomeRequestCode
import dev.vengateshm.home.repository.BannerRepository
import dev.vengateshm.home.repository.FeatureRepository

class HomeFacade(private val context: Context) : CommProcessor {

    private val bannerRepository = BannerRepository.create(context)
    private val featureRepository = FeatureRepository.create(context)

    override fun doNavigation(
        navController: NavController,
        data: CommData,
    ) {

    }

    override suspend fun processRequest(data: CommData): CommData? {
        if (data.destinationPath != CommPath.HOME) return null
        if (data.requestType != CommType.GET_DATA) return null

        val code = data.requestCode
        if (data.originatePath == CommPath.MAIN && code == HomeRequestCode.BANNER.name)
            return CommData(
                originatePath = CommPath.HOME,
                destinationPath = CommPath.MAIN,
                requestCode = HomeRequestCode.BANNER.name,
                requestType = CommType.GET_DATA,
                data = bannerRepository.getBanners(),
            )
        return null
    }
}