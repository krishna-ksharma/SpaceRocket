package com.extraaedge.assignment.spacerocket.data

import com.extraaedge.assignment.spacerocket.data.model.Rocket

sealed interface RocketResult {
    object Loading : RocketResult
    class Success(val data: List<Rocket>) : RocketResult
    class Error(val message: String?) : RocketResult
}