package com.github.vittee.codingdojo

import kotlin.test.Test
import kotlin.test.expect

@Suppress("SpellCheckingInspection", "RemoveRedundantBackticks")
class QuantizerTest {
    private val quantizer = Quantizer<Int, String>().apply {
        mapOf(
            100 to "A",
            90 to "B",
            80 to "C",
            70 to "D",
            60 to "F"
        ).forEach {
            addThreshold(it.key, it.value)
        }
    }
    
    private infix fun Int.mustBe(x: String) {
        expect(x) { quantizer.quantize(this, "N/A") }
    }

    @Test
    fun `Quantize`() {
        100 mustBe "A"
        91 mustBe "A"
        90 mustBe "B"
        81 mustBe "B"
        80 mustBe "C"
        71 mustBe "C"
        70 mustBe "D"
        61 mustBe "D"
        60 mustBe "F"
        0 mustBe "F"
        101 mustBe "N/A"
    }
}