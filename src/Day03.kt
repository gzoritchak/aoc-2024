
fun main() {
    val mulPattern  = """mul\((\d+),(\d+)\)"""
    val doPattern   = """do\(\)"""
    val dontPattern = """don't\(\)"""

    val mulRegex    = mulPattern.toRegex()
    val doRegex     = doPattern.toRegex()
    val dontRegex   = dontPattern.toRegex()
    val allRegex    = ("$mulPattern|$doPattern|$dontPattern").toRegex()

    val lines = parse()
    val part1  = lines
        .flatMap { line -> mulRegex.findAll(line) }
        .sumOf { matchResult ->
            val x = matchResult.groupValues[1].toInt()
            val y = matchResult.groupValues[2].toInt()
            x * y
        }
    println(part1)

    var enabled = true
    var sum = 0
    lines
        .flatMap { line -> allRegex.findAll(line) }
        .map { it.value }
        .forEach {
            when {
                doRegex.matches(it)     -> { enabled = true }
                dontRegex.matches(it)   -> { enabled = false }
                enabled -> {
                    val match = mulRegex.find(it)!!
                    val x = match.groupValues[1].toInt()
                    val y = match.groupValues[2].toInt()
                    sum += x * y
                }
            }
        }
    println(sum)
}

private fun parse() = readInput("Day03")
