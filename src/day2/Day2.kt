package day2

import java.io.File

fun main() {
    val input = File("src/day2/input.txt").absoluteFile.readLines()

    val reports = mutableListOf<List<Int>>()
    input.map { line -> reports.add(line.split(" ").map { it.toInt() }) }

    checkSafety(reports)
}

fun checkSafety(lines: MutableList<List<Int>>) {
    var safeReports: Int = 0
    var dampenedReports: Int = 0

    lines.forEach { line ->
        if (line.isSafe()) {
            safeReports++
        }

        if (line.indices.any { index ->
                line
                    .toMutableList()
                    .apply { removeAt(index) }
                    .isSafe()
            }) {
            dampenedReports++
        }
    }

    println("Only $safeReports reports were safe originally.")
    println("Now $dampenedReports reports are safe with the Problem Dampener!")
}

fun List<Int>.isSafe(): Boolean {
    val differences = this.zipWithNext { x, y -> y - x }

    return differences.all { it in 1..3 } || differences.all { it in -3..-1 }
}