
fun main() {
    val reports: List<List<Int>> = readInput("Day02").map { it.split(" ").map { it.toInt() } }
    val safeReportsCount = reports.count { it.isReportSafe() }
    println("safe reports : $safeReportsCount")

    val newSafeReportsCount = reports.count { report ->
        report.isReportSafe() || report.safeWithRelaxedRule()
    }
    println("new safe reports : $newSafeReportsCount")
}

fun List<Int>.isReportSafe(): Boolean {
    val differences = zipWithNext { a, b -> b - a }
    return differences.all { it in 1..3 } || differences.all { it in -3..-1 }
}

fun List<Int>.safeWithRelaxedRule(): Boolean = indices.any { index ->
    val modifiedReport = filterIndexed { i, _ -> i != index }
    modifiedReport.isReportSafe()
}
