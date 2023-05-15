import java.io.IOException
import java.util.*
import kotlin.math.pow

class InputOutput {
    private var symbolsBase = arrayOf(
        "t", "/", "e", "i", "5", "v", "l", "x", "s",
    )
    var n = 234
    private var timeIn = LongArray(n)
    private var timeOut = LongArray(n)
    var speed = LongArray(timeIn.size)
    lateinit var autoCorrelation: DoubleArray
    private var sc = Scanner(System.`in`)
    private var s1: String? = null
    private var average: Long = 0
    private var disp = 0.0
    private var mx = 0.0

    @Throws(IOException::class)
    fun start() {
        var i = 0
        while (i < n) {
            val numOut = (Math.random() * 9).toInt()
            println("Ввод №$i: введите символ [${symbolsBase[numOut]}]")
            timeOut[i] = Date().time
            val timeIn = timeIn(i, symbolsBase[numOut])
            println(" Скорость печати: ${(timeIn - timeOut[i])} мс")
            i++
        }
        i = 0
        while (i < speed.size) {
            speed[i] = timeIn[i] - timeOut[i]
            i++
        }
    }
    init {
        start()
    }

    @Throws(IOException::class)
    fun timeIn(i: Int, s: String): Long {
        s1 = sc.nextLine()
        while (s != s1) {
            println("Символ введен неверно. Введите символ снова")
            s1 = sc.nextLine()
        }
        timeIn[i] = Date().time
        return timeIn[i]
    }

    fun getAverage(): Long {
        var result = 0
        for (i in speed) {
            result += i.toInt()
        }
        average = (result / speed.size).toLong()
        return average
    }

    private fun getMath(fromValue: Int, toValue: Int): Double {
        mx = 0.0
        val p = 1.0 / (toValue - fromValue)
        for (i in fromValue until toValue)
            mx += speed[i] * p
        return mx
    }

    fun getDisp(fromValue: Int, toValue: Int): Double {
        mx = getMath(fromValue, toValue)
        disp = 0.0
        val p = 1.0 / (toValue - fromValue)
        for (i in fromValue until toValue)
            disp += p * (speed[i] - mx).pow(2.0)
        return disp
    }

    fun autoCorrelation(
        speed: LongArray,
        fromValue: Int,
        toValue: Int,
        step: Int
    ): DoubleArray {
        autoCorrelation = DoubleArray(step)
        disp = getDisp(fromValue, toValue)
        for (i in 0 until step) {
            var sum = 0.0
            for (j in fromValue until (toValue - (i + 1)))
                sum += (speed[j] - mx) * (speed[j + (i + 1)] - mx)

            autoCorrelation[i] = sum / ((toValue - fromValue - (i + 1)) * disp)
        }
        return autoCorrelation
    }
}