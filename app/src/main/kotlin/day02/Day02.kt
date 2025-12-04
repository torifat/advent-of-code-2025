package day02

import java.io.File
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun main(args: Array<String>) {
    val lines = if (args.isNotEmpty()) {
        File(args[0]).readLines()
    } else {
        // Read from file next to this source file
        object {}.javaClass.getResourceAsStream("input.txt")?.bufferedReader()?.readLines()
            ?: error("Could not find input.txt")
    }

    val uniqueNumber = ArrayList<Long>()
    val prefixSums = ArrayList<Long>()

    (1..99999)
        .map({ it * 10.0.pow(floor(log10(it.toDouble())) + 1).toLong() + it })
        .fold(0L) { acc, x ->
            uniqueNumber.add(x)
            prefixSums.add(acc + x)
            acc + x
        }

    fun getSumUpTo(limit: Long): Long {
        val cursor = uniqueNumber.binarySearch(limit)
        val index = if (cursor >= 0) cursor else (-cursor - 1) - 1
        return if (index >= 0) prefixSums[index] else 0
    }

    lines.map { line -> solve(line, ::getSumUpTo) }.also { println(it) }
}

fun solve(line: String, getSumUpTo: (Long) -> Long): Long {
    return line.split(",").fold(0) { acc, range ->
        val (from, to) = range.split("-").map { it.toLong() }
        acc + (getSumUpTo(to) - getSumUpTo(from - 1))
    }
}