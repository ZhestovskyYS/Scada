fun main(args: Array<String>) {
    val io = InputOutput()
    println("Среднее значение скорости реакции оператора S=${io.getAverage()} мс")
    println("Дисперсия скорости реакции оператора D=${io.getDisp(0, io.n)}")
    io.autoCorrelation(io.speed, 0, 15, 10)
    println("Значения автокорреляционной функции:")
    println(" i | r(i) ")
    println("-------|-----------------------")
    for (i in io.autoCorrelation.indices) {
        println(" $i | ${io.autoCorrelation[i]}")
    }
}
