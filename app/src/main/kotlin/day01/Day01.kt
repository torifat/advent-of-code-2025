package day01

import java.io.File

fun main(args: Array<String>) {
    val lines = if (args.isNotEmpty()) {
        File(args[0]).readLines()
    } else {
        // Read from file next to this source file
        object {}.javaClass.getResourceAsStream("input.txt")?.bufferedReader()?.readLines()
            ?: error("Could not find input.txt")
    }

    val part1 = solvePart1(lines)
    println("Part 1: $part1")
}

data class State(val pointer: Int, val noOfZeroes: Int)

fun solvePart1(lines: List<String>): Any {
    return lines.fold(State(50, 0)) { (pointer, noOfZeroes), command ->
        val multiplier = if (command.first() == 'R') 1 else -1
        val newVal = (pointer + multiplier * command.drop(1).toInt()) % 100
        State(newVal, noOfZeroes + if (newVal == 0) 1 else 0)
            // .also { println("$command -> $it") }
    }
}