package com.sithuhein.mm.domain.interactor

import com.sithuhein.mm.domain.repository.LaunchesListDataRepository
import javax.inject.Inject

class GetLaunchesPastListUseCase @Inject constructor(
    val repository: LaunchesListDataRepository
) {
    suspend operator fun invoke() = repository.fetchLaunchesPastList()
}