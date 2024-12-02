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
}

fun listDistance(left: List<Int>, right: List<Int>) {
    val leftList = left.sorted()
    val rightList = right.sorted()

    val itemDistance = leftList.zip(rightList) { x, y -> abs(x - y) }
    itemDistance.forEachIndexed { i, item ->
        println("The distance between ${leftList[i]} and ${rightList[i]} is $item")
    }

    val sumDistance = itemDistance.sum()
    println("\nThe distances between the lists is $sumDistance!")
}