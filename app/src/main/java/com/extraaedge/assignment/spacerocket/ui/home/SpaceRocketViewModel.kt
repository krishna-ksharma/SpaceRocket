package com.extraaedge.assignment.spacerocket.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceRocketViewModel @Inject constructor(private val repository: RocketRepository) : ViewModel() {
    private val _state = MutableLiveData<List<Rocket>>()
    val state: LiveData<List<Rocket>>
        get() = _state

    private val _selectedRocketItem = MutableLiveData<Rocket>()
    val selectedRocketItem: LiveData<Rocket>
        get() = _selectedRocketItem

    fun listRockets() {
        viewModelScope.launch {
            val rocketListResponse = repository.listRockets()
            if (rocketListResponse.isSuccessful) {
                _state.value = rocketListResponse.body();
            }
        }
    }

    fun setSelectedRocketItem(rocketResponseItem: Rocket) {
        _selectedRocketItem.value = rocketResponseItem
    }
}