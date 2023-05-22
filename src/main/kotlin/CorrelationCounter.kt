import kotlin.math.pow

class CorrelationCounter(
    private val measurements: List<Long>,
    private val stepTimes: Int,
    private val step: Int,
) {
    private var average: Long = 0
    private var mx = 0.0

    fun getAverage(): Long {
        var result = 0
        for (i in measurements) {
            result += i.toInt()
        }
        average = (result / measurements.size).toLong()
        return average
    }

    val dispersion by lazy {
        mx = getMath(0, stepTimes)
        var disp = 0.0
        val p = 1.0 / stepTimes
        for (i in 0 until stepTimes)
            disp += p * (measurements[i] - mx).pow(2.0)
        disp
    }

    private fun getMath(fromValue: Int, toValue: Int): Double {
        mx = 0.0
        val p = 1.0 / (toValue - fromValue)
        for (i in fromValue until toValue)
            mx += measurements[i] * p
        return mx
    }

    val autoCorrelation: DoubleArray by lazy {
        val acc = DoubleArray(step)
        for (i in 0 until step) {
            var sum = 0.0
            for (j in 0 until (stepTimes - (i + 1)))
                sum += (measurements[j] - mx) * (measurements[j + (i + 1)] - mx)

            acc[i] = sum / ((stepTimes - (i + 1)).toDouble() * dispersion)
        }
        acc
    }
}