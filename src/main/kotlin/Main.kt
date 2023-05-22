private const val INPUT_TIMES = "inputTimes"
private const val STEP_TIMES = "stepTimes"
private const val STEP = "step"

fun main(args: Array<String>) {
    val inputTimes = args.extractKey(key = INPUT_TIMES, default = 257)
    val stepTimes = args.extractKey(key = STEP_TIMES, default = 16)
    val step = args.extractKey(key = STEP, default = 10)

    val measurements = ReactionMeasurements()
    val correlationCounter = CorrelationCounter(measurements(inputTimes), stepTimes, step)

    println("Среднее значение скорости реакции оператора S=${correlationCounter.getAverage()} мс")
    println("Дисперсия скорости реакции оператора D=${correlationCounter.dispersion}")
    println("Значения автокорреляционной функции:")
    println(" i | r(i) ")
    println("-------|-----------------------")
    correlationCounter.autoCorrelation.forEachIndexed { index, value ->
        println(" $index | $value")
    }
}

private fun Array<String>.extractKey(key: String, default: Int): Int {
    val foundValue = find { it.contains(key) }
    return foundValue?.removePrefix("$key=")?.toInt() ?: default
}
