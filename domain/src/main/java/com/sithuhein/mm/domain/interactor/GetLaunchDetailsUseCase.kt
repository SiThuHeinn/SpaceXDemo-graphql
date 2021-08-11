package com.sithuhein.mm.domain.interactor

import com.sithuhein.mm.domain.repository.LaunchesListDataRepository
import javax.inject.Inject

class GetLaunchDetailsUseCase @Inject constructor(
    val repository: LaunchesListDataRepository
) {
    suspend operator fun invoke(id : String) = repository.fetchLaunchDetails(id)
}