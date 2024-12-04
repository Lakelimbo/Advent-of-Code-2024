package day4

import java.io.File

fun main() {
    val input = File("src/day4/input.txt").absoluteFile.readText().trim()

    findMatrix(input, "XMAS")
}

fun findMatrix(input: String, word: String) {
    val grid = input.lines()
    val directions = listOf(
        0 to 1,     // horizontal-right
        0 to -1,    // horizontal-left
        1 to 0,     // vertical-down
        -1 to 0,    // vertical-up
        1 to 1,     // diagonal-down-right
        1 to -1,    // diagonal-down-left
        -1 to 1,    // diagonal-up-right
        -1 to -1,   // diagonal-up-left
    )

    var count = 0
    var crossCount = 0

    for (row in grid.indices) {
        for (col in grid[row].indices) {
            directions.forEach { direction ->
                if (doesMatch(grid, row, col, direction, word)) {
                    count++
                }
            }

            if (grid[row][col] == 'A' && doesMatchCross(grid, row, col, word)) {
                crossCount++
            }
        }
    }

    println("There are $count of $word, and $crossCount in the X shape!")
}

fun doesMatch(grid: List<String>, row: Int, col: Int, direction: Pair<Int, Int>, word: String): Boolean {
    val rows = grid.size
    val cols = grid[0].length
    for (i in word.indices) {
        val rowNew = row + i * direction.first
        val colNew = col + i * direction.second

        if (
            rowNew !in 0..<rows ||
            colNew !in 0..<cols ||
            grid[rowNew][colNew] != word[i]
        ) {
            return false
        }
    }
    return true
}

fun doesMatchCross(grid: List<String>, row: Int, col: Int, word: String): Boolean {
    val rows = grid.size
    val cols = grid[0].length

    if (row - 1 < 0 || row + 1 >= rows || col - 1 < 0 || col + 1 >= cols) {
        return false
    }

    // will check the diagonals
    val topLeft = "${grid[row - 1][col - 1]}${grid[row][col]}${grid[row + 1][col + 1]}"
    val topRight = "${grid[row - 1][col + 1]}${grid[row][col]}${grid[row + 1][col - 1]}"

    return (
            topLeft == word.substring(1) ||
                    topLeft.reversed() == word.substring(1)) &&
            (topRight == word.substring(1) ||
                    topRight.reversed() == word.substring(1))
}