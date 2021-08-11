package com.sithuhein.mm.data.mapper

import com.sithuhein.mm.data.LaunchDetailsQuery
import com.sithuhein.mm.data.LaunchesPastListQuery
import com.sithuhein.mm.domain.model.*

object ModelMapper {

    private fun convert(launchesPast: LaunchesPastListQuery.LaunchesPast): LaunchesPast {
        return LaunchesPast(
            id = launchesPast.id,
            launch_date_local = launchesPast.launch_date_local.toString(),
            mission_name = launchesPast.mission_name,
            links = Links(
                article_link = launchesPast.links?.article_link,
                mission_patch = launchesPast.links?.mission_patch,
                wikipedia = launchesPast.links?.wikipedia
            ),
        )
    }

    fun convert(launchesPastList : List<LaunchesPastListQuery.LaunchesPast?>) : List<LaunchesPast>{
        return launchesPastList.map { launchesPast ->
            launchesPast?.let {  convert(launchesPast) }!!
        }
    }

    fun convert(launchDetails : LaunchDetailsQuery.Launch) : LaunchDetails {
        return LaunchDetails(
            id = launchDetails.id,
            mission_name = launchDetails.mission_name,
            launch_date_local = launchDetails.launch_date_local.toString(),
            launch_success = launchDetails.launch_success,
            launch_year = launchDetails.launch_year,
            links = Links(
                wikipedia = launchDetails.links?.wikipedia,
                article_link = launchDetails.links?.article_link,
                mission_patch = launchDetails.links?.mission_patch
            ),
            rocket = Rocket(
                rocket_name = launchDetails.rocket?.rocket_name,
                rocket_type = launchDetails.rocket?.rocket_type,
            ),
            launch_site = LaunchSite(
                site_id = launchDetails.launch_site?.site_id,
                site_name = launchDetails.launch_site?.site_name,
            )
        )
    }


}


















