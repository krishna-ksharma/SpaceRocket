package com.extraaedge.assignment.spacerocket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceRocketViewModel @Inject constructor(private val repository: RocketRepository) :
    ViewModel() {
    private val _rockets = MutableLiveData<RocketResult<List<Rocket>>>()
    private val _selectedRocket = MutableLiveData<Rocket>()
    val rockets: LiveData<RocketResult<List<Rocket>>>
        get() = _rockets
    val selectedRocket: LiveData<Rocket>
        get() = _selectedRocket

    fun listRockets(hardRefresh: Boolean) {
        _rockets.value = RocketResult.InProgress()
        viewModelScope.launch {
            _rockets.value = repository.listRockets(hardRefresh)
        }
    }

    fun setSelectedRocketItem(rocketResponseItem: Rocket) {
        _selectedRocket.value = rocketResponseItem
    }
}