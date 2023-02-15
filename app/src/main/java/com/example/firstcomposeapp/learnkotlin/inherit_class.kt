package com.example.firstcomposeapp.learnkotlin

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RangeRegulator(initialValue: Int, private val minValue: Int, private val maxValue: Int) :
    ReadWriteProperty<Any?, Int> {
    var fieldData = initialValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }

}


open class SmartDevice protected constructor(val name: String, val category: String) {
    var deviceStatus = "Online"
        protected set

    var isDeviceOn = deviceStatus == "on"

    open fun doIfDeviceOn(action: () -> Unit) {
        if (isDeviceOn) action.invoke()
    }

    open val deviceType = "Unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartLightDevice(name: String, category: String) : SmartDevice(name = name, category = category) {
    override val deviceType: String = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name is on, The brightness level is $brightnessLevel")
    }

    override fun turnOff() {
        doIfDeviceOn {
            super.turnOff()
            brightnessLevel = 0
            println("$name is off, The brightness level is $brightnessLevel")
        }
    }

    fun increaseBrightnessLevel() {
        doIfDeviceOn { brightnessLevel++ }

    }

    fun decreaseBrightnessLevel() {
        doIfDeviceOn { brightnessLevel-- }
    }
}

class SmartTVDevice(name: String, category: String) : SmartDevice(name, category) {

    override val deviceType: String = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    override fun turnOn() {
        super.turnOn()
        println("$name is turn on\n Speaker volume is $speakerVolume \n Channel is $channelNumber")

    }

    override fun turnOff() {
        doIfDeviceOn {
            super.turnOff()
            println("$name is turn off")
        }
    }

    fun increaseSpeakerVolume() {
        doIfDeviceOn {
            speakerVolume++
            println("Speaker volume increased to $speakerVolume")
        }

    }

    fun decreaseVolume() {
        doIfDeviceOn {
            speakerVolume--
            println("Speaker volume decrease to $speakerVolume")
        }
    }

    fun nextChannel() {
        doIfDeviceOn {
            channelNumber++
            println("Channel number increased to $channelNumber")
        }
    }

    fun previousChannel() {
        doIfDeviceOn {
            channelNumber--
            println("Channel number decrease to $channelNumber")
        }
    }
}

class SmartHome(val smartTVDevice: SmartTVDevice, val smartLightDevice: SmartLightDevice) {
    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTVDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTVDevice.turnOff()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTVDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTVDevice.nextChannel()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightnessLevel()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
    val smartHome = SmartHome(
        SmartTVDevice(name = "Android TV", category = "Entertainment"),
        SmartLightDevice(name = "Google light", category = "Utility")
    )

    smartHome.turnOnTv()
    smartHome.turnOnLight()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")
    println()

    smartHome.increaseTvVolume()
    smartHome.changeTvChannelToNext()

    smartHome.increaseLightBrightness()
    println()

    smartHome.turnOffAllDevices()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}.")
}