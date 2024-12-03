
fun main() {
    val reports = readInput("Day02").map { line -> line.split(" ").map { it.toInt() } }
    val safeReportsCount = reports.count { it.isSafe() }
    println("safe reports : $safeReportsCount")
    val newSafeReportsCount = reports.count { report ->
        report.isSafe() || report.isSafeWithRelaxedRule()
    }
    println("new safe reports : $newSafeReportsCount")
}

private fun List<Int>.isSafe(): Boolean {
    val differences = zipWithNext { a, b -> b - a }
    return differences.all { it in 1..3 } || differences.all { it in -3..-1 }
}

private fun List<Int>.isSafeWithRelaxedRule(): Boolean = indices.any { index ->
    val reportWithOneResultOut = filterIndexed { i, _ -> i != index }
    reportWithOneResultOut.isSafe()
}
