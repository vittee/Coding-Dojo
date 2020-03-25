package com.github.vittee.codingdojo

@Suppress("SpellCheckingInspection", "MemberVisibilityCanBePrivate")
open class Quantizer<T, V> where T: Number, T: Comparable<T> {
    private val thresholds = mutableListOf<Threshold>()

    fun addThreshold(t: T, v: V) {
        Threshold(t,v).let(thresholds::add)
    }

    fun quantize(from: T, default: V): V = thresholds
        .sortedBy { it.threshold }
        .find { from <= it.threshold }?.value
        ?: default

    inner class Threshold(val threshold: T, val value: V) {
        override fun toString() = "[${javaClass.simpleName} t:$threshold, v:$value]"
    }
}