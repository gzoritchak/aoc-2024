fun main() {
    val grid = readInput("Day04")
    println("Total occurrences of XMAS: ${countOccurrences(grid, "XMAS")}")
    println("Total occurrences of X-MAS: ${countXMAS(grid)}")
}


fun countOccurrences(grid: List<String>, word: String): Int {
    val rows = grid.size
    val cols = grid[0].length
    var count = 0

    val directions = listOf(
        Pair(0, 1),
        Pair(0, -1),
        Pair(1, 0),
        Pair(-1, 0),
        Pair(1, 1),
        Pair(1, -1),
        Pair(-1, 1),
        Pair(-1, -1)
    )

    fun checkDirection(row: Int, col: Int, dirRow: Int, dirCol: Int): Boolean {
        for (i in word.indices) {
            val newRow = row + i * dirRow
            val newCol = col + i * dirCol
            if (newRow !in 0 until rows || newCol !in 0 until cols || grid[newRow][newCol] != word[i])
                return false
        }
        return true
    }

    for (row in 0 until rows)
        for (col in 0 until cols)
            for ((dirRow, dirCol) in directions)
                if (checkDirection(row, col, dirRow, dirCol)) count++

    return count
}


fun countXMAS(grid: List<String>): Int {
    val rows = grid.size
    val cols = grid[0].length
    val patterns = listOf("MAS", "SAM")
    var count = 0

    fun isXMAS(row: Int, col: Int): Boolean {
        if (row !in 1 until rows - 1 || col !in 1 until cols - 1) return false
        val topLeft = "${grid[row - 1][col - 1]}${grid[row][col]}${grid[row + 1][col + 1]}"
        val topRight = "${grid[row - 1][col + 1]}${grid[row][col]}${grid[row + 1][col - 1]}"
        return (topLeft in patterns && topRight in patterns)
    }

    for (row in 1 until rows - 1)
        for (col in 1 until cols - 1)
            if (isXMAS(row, col)) count++

    return count
}
