import java.io.IOException
import java.util.*

class ReactionMeasurements {
    private var symbolsBase = arrayOf(
        "t", "/", "e", "i", "5", "v", "l", "x", "s",
    )
    private var sc = Scanner(System.`in`)
    private var s1: String? = null

    operator fun invoke(inputTimes: Int): List<Long> {
        return countReactionMeasurements(
            writeMeasurements(inputTimes)
        )
    }

    @Throws(IOException::class)
    private fun writeMeasurements(inputTimes: Int): List<TimeMeasurement> {
        val timeTimeMeasurements = mutableListOf<TimeMeasurement>()
        for (i in 0 until inputTimes) {
            val currentSymbolIndex = symbolsBase.indices.random()
            println("Ввод №$i: введите символ [${symbolsBase[currentSymbolIndex]}]")
            val startTime = Date().time
            val inputTime = measureInputTime(symbolsBase[currentSymbolIndex])
            println(" Скорость печати: ${(inputTime - startTime)} мс").also {
                timeTimeMeasurements.add(
                    TimeMeasurement(startTime, inputTime)
                )
            }
        }
        return timeTimeMeasurements
    }

    private fun countReactionMeasurements(timeTimeMeasurements: List<TimeMeasurement>): List<Long> {
        val measurements = mutableListOf<Long>()
        timeTimeMeasurements.forEach {
            measurements.add(it.startTime - it.inputTime)
        }
        return measurements
    }


    @Throws(IOException::class)
    private fun measureInputTime(s: String): Long {
        s1 = sc.nextLine()
        while (s != s1) {
            println("Символ введен неверно. Введите символ снова")
            s1 = sc.nextLine()
        }
        return Date().time
    }
}