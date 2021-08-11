package com.sithuhein.mm.domain.model


data class LaunchDetails(
    var id : String?,
    var mission_name : String?,
    var launch_date_local : String?,
    var launch_success : Boolean?,
    var launch_year : String?,
    var links : Links?,
    var rocket : Rocket?,
    var launch_site : LaunchSite?,
)