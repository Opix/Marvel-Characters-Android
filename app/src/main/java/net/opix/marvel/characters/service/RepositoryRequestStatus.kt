package net.opix.marvel.characters.service

sealed class RepositoryRequestStatus {
    object FETCHING : RepositoryRequestStatus()
    object COMPLETE : RepositoryRequestStatus()
    class Error(val error: Exception) : RepositoryRequestStatus()
}