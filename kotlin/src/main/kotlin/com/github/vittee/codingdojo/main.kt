package com.github.vittee.codingdojo

fun main() {
    Quantizer<Int, String>().apply {
        mapOf(
            100 to "A",
            90 to "B",
            80 to "C",
            70 to "D",
            60 to "F"
        ).forEach {
            addThreshold(it.key, it.value)
        }

        val scores = (0..100 step 10).toList()
            .run {
                this + map { it + 1 }
            }
            .sorted()

        for (score in scores) {
            @Suppress("ComplexRedundantLet")
            quantize(score, "N/A").let { grade ->
                print("Score: $score, Grade: $grade\n")
            }
        }
    }
}