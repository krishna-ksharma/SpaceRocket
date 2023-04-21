package com.extraaedge.assignment.spacerocket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceRocketViewModel @Inject constructor(private val repository: RocketRepository) :
    ViewModel() {
    private val _rockets = MutableStateFlow<RocketResult>(RocketResult.Loading)
    private val _selectedRocket =
        MutableSharedFlow<Rocket>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val rockets = _rockets.asStateFlow()
    val selectedRocket: SharedFlow<Rocket>
        get() = _selectedRocket

    init {
        listRockets(false)
    }

    fun listRockets(hardRefresh: Boolean) {
        _rockets.value = RocketResult.Loading

        viewModelScope.launch {
            repository.listRockets(hardRefresh).collect {
                _rockets.value = it
            }
        }
    }

    fun setSelectedRocketItem(rocketResponseItem: Rocket) {
        viewModelScope.launch {
            _selectedRocket.emit(rocketResponseItem)
        }

    }
}