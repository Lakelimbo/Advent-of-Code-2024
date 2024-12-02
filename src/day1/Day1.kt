package day1

import java.io.File
import kotlin.math.abs

fun main() {
    val lists = File("src/day1/lists.txt").absoluteFile.readText()

    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    lists.lines().forEach { line ->
        val numbers = line.split("   ").map { it.trim().toInt() }

        if (numbers.size >= 2) {
            leftList.add(numbers[0])
            rightList.add(numbers[1])
        }
    }

    listDistance(leftList, rightList)
    similarityScore(leftList, rightList)
}

fun listDistance(left: List<Int>, right: List<Int>) {
    val leftList = left.sorted()
    val rightList = right.sorted()

    val itemDistance = leftList.zip(rightList) { x, y -> abs(x - y) }

    val sumDistance = itemDistance.sum()
    println("The distances between the lists is $sumDistance")
}

fun similarityScore(left: List<Int>, right: List<Int>) {
    val rightList = right.groupingBy { it }.eachCount()

    val similarity = left.sumOf { number ->
        number * (rightList[number] ?: 0)
    }

    println("The similarity score is $similarity")
}