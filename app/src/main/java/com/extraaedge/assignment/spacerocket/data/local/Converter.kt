package com.extraaedge.assignment.spacerocket.data.local

import androidx.room.TypeConverter
import com.extraaedge.assignment.spacerocket.data.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun toEntityToHeight(entity: Height): String {
        val type = object : TypeToken<Height>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToHeight(string: String): Height {
        val type = object : TypeToken<Height>() {}.type
        return gson.fromJson<Height>(string, type)
    }

    @TypeConverter
    fun toEntityToEngines(entity: Engines): String {
        val type = object : TypeToken<Engines>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToEngines(string: String): Engines {
        val type = object : TypeToken<Engines>() {}.type
        return gson.fromJson<Engines>(string, type)
    }

    @TypeConverter
    fun toEntityToFirstStage(entity: FirstStage): String {
        val type = object : TypeToken<FirstStage>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToFirstStage(string: String): FirstStage {
        val type = object : TypeToken<FirstStage>() {}.type
        return gson.fromJson<FirstStage>(string, type)
    }

    @TypeConverter
    fun toEntityToLandingLegs(entity: LandingLegs): String {
        val type = object : TypeToken<LandingLegs>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToLandingLegs(string: String): LandingLegs {
        val type = object : TypeToken<LandingLegs>() {}.type
        return gson.fromJson<LandingLegs>(string, type)
    }

    @TypeConverter
    fun toEntityToMass(entity: Mass): String {
        val type = object : TypeToken<Mass>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToMass(string: String): Mass {
        val type = object : TypeToken<Mass>() {}.type
        return gson.fromJson<Mass>(string, type)
    }

    @TypeConverter
    fun toEntityToPayloadWeight(entity: PayloadWeight): String {
        val type = object : TypeToken<PayloadWeight>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToPayloadWeight(string: String): PayloadWeight {
        val type = object : TypeToken<PayloadWeight>() {}.type
        return gson.fromJson<PayloadWeight>(string, type)
    }

    @TypeConverter
    fun toEntityToSecondStage(entity: SecondStage): String {
        val type = object : TypeToken<SecondStage>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToSecondStage(string: String): SecondStage {
        val type = object : TypeToken<SecondStage>() {}.type
        return gson.fromJson<SecondStage>(string, type)
    }

    @TypeConverter
    fun toEntityToPayloads(entity: Payloads): String {
        val type = object : TypeToken<Payloads>() {}.type
        return gson.toJson(entity, type)
    }

    @TypeConverter
    fun fromStringToPayloads(string: String): Payloads {
        val type = object : TypeToken<Payloads>() {}.type
        return gson.fromJson<Payloads>(string, type)
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromPayloadWeightString(value: String): List<PayloadWeight>? {
        val type = object : TypeToken<List<PayloadWeight>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromPayloadWeightArrayList(list: List<PayloadWeight>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}