package day3

import java.io.File

fun main() {
    val input = File("src/day3/input.txt").absoluteFile.readText()

    val muls = input.findMuls()
    multiplyAndAdd(muls)
}

fun String.findMuls(): List<Pair<String, Boolean>> {
    // will capture the muls with the commands for part 2 already
    val regex = """\bdo\(\)|\bdon't\(\)|\bmul\(\d{1,3},\d{1,3}\)""".toRegex()
    val matches = regex.findAll(this)

    val commands = mutableListOf<Pair<String, Boolean>>()
    var isEnabled = true

    matches.forEach { match ->
        when (match.value) {
            "do()" -> isEnabled = true
            "don't()" -> isEnabled = false
            else -> if (match.value.startsWith("mul")) {
                commands.add(match.value to isEnabled)
            }
        }
    }

    return commands
}

fun multiplyAndAdd(items: List<Pair<String, Boolean>>) {
    var total = 0
    var totalConditional = 0

    items.forEach { (item, isEnabled) ->
        val numbers = """\d+"""
            .toRegex()
            .findAll(item)
            .map { it.value.toInt() }
            .toList()

        if (numbers.size == 2) {
            val product = numbers.reduce { left, right -> left * right }
            total += product

            if (isEnabled) {
                totalConditional += product
            }
        }
    }

    println("The total of uncorrupted muls is $total")
    println("And the total with conditional commands is $totalConditional")
}