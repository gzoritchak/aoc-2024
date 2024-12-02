import kotlin.math.absoluteValue

fun main() {
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    val regex = Regex("\\s+")
    readInput("Day01")
        .forEach {
            val items = it.split(regex)
            list1.add(items[0].toInt())
            list2.add(items[1].toInt())
        }
    println(part1(list1, list2))
    println(part2(list1, list2))
}

private fun part2(
    list1: MutableList<Int>,
    list2: MutableList<Int>,
): Int {
    var ret = 0
    list1.forEach { i ->
        ret += i * list2.count { it == i }
    }
    return ret
}

private fun part1(
    list1: MutableList<Int>,
    list2: MutableList<Int>,
): Int {
    list1.sort()
    list2.sort()
    val zipped = list1.zip(list2)
    val ret = zipped.fold(0) { acc, pair ->
        acc + (pair.first - pair.second).absoluteValue
    }
    return ret
}
