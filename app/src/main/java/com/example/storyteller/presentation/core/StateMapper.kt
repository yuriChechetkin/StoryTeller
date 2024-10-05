package com.example.storyteller.presentation.core

interface StateMapper<DomainState, UIState> {

    fun mapState(domainState: DomainState): UIState
}