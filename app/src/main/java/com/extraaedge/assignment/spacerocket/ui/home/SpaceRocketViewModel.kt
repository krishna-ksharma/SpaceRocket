package com.extraaedge.assignment.spacerocket.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extraaedge.assignment.spacerocket.data.ApiResult
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceRocketViewModel @Inject constructor(private val repository: RocketRepository) :
    ViewModel() {
    private val _rockets = MutableLiveData<ApiResult<List<Rocket>>>()
    private val _selectedRocket = MutableLiveData<Rocket>()
    val rockets: LiveData<ApiResult<List<Rocket>>>
        get() = _rockets
    val selectedRocket: LiveData<Rocket>
        get() = _selectedRocket

    init {
        listRockets()
    }

    fun listRockets() {
        viewModelScope.launch {
            _rockets.value = repository.listRockets()
        }
    }

    fun setSelectedRocketItem(rocketResponseItem: Rocket) {
        _selectedRocket.value = rocketResponseItem
    }
}