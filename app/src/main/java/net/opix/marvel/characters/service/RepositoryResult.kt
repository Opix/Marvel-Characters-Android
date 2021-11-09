package net.opix.marvel.characters.service

data class RepositoryResult<T>(
    val data: T,
    val requestStatus: RepositoryRequestStatus
)