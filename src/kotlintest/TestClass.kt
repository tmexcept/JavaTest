package kotlintest

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {

        println("Second initializer block that prints ${name.length}")
    }
    val customerKey = name.toUpperCase().also(::println)

    init {
        println("Init block")
    }

    constructor(i: Int) : this("test constructor") {
        println("Constructor")
    }
}

val stringPlus: (String, String) -> String = String::plus
val intPlus: Int.(Int) -> Int = Int::plus

class MyClass {
    companion object { }  // 将被称为 "Companion"
}

fun MyClass.Companion.foo() {
    println("伴随对象的扩展函数")
}

val MyClass.Companion.no: Int
    get() = 10

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者——forEach 循环
        print(it)
    }
    print(" done with implicit label")
}

fun main(args: Array<String>) {
    println("no:${MyClass.no}")
    MyClass.foo()

    foo()


    //由于 JVM 对 -128 到 127 的整数（Integer）应用了内存优化，因此，
    // a 的所有可空引用实际上都是同一对象。但是没有对 b 应用内存优化，所以它们是不同对象
    //todo  结构相等【==】返回true
    //todo  疑问？ 此处a<128的时候 引用相等【===】返回 true
    //todo  疑问？ 此处a>=128的时候 引用相等【===】返回 false
    val a: Int = 128
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA == anotherBoxedA)
    println(boxedA === anotherBoxedA)
    println(boxedA!!.equals(anotherBoxedA))
    println(boxedA)
    println(anotherBoxedA)
// 输出什么内容?
//    作者：朱涛的自习室
//    链接：https://juejin.cn/post/6844904191098355719

//    val aa: Int = 1 // 一个装箱的 Int (java.lang.Integer)
//    val b: Long? = aa // 隐式转换产生一个装箱的 Long (java.lang.Long)
//    print(b == aa) // 惊！这将输出“false”鉴于 Long 的 equals() 会检测另一个是否也为 Long

    when (a) {
        0, 1 -> print("x == 0 or x == 1")
//        else -> print("otherwise")
    }

//    InitOrderDemo("hello")
//    InitOrderDemo(5)

//    Derived("Derived", "baseName")


//    val stringPlus: (String, String) -> String = String::plus
//    val intPlus: Int.(Int) -> Int = Int::plus
    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "world!"))
    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // 类扩展调用

    val items = listOf(1, 2, 3, 4, 5)
// Lambdas 表达式是花括号括起来的代码块。
    items.fold(0, {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    })
// lambda 表达式的参数类型是可选的，如果能够推断出来的话：
    val joinedToString = items.fold("Elements:", { acc, i ->
        val result = acc + " " + i
        println(result)
        result
    })
    println("joinedToString = "+joinedToString)
// 函数引用也可以用于高阶函数调用：
    val product = items.fold(1, Int::times)
    println("product = "+product)

    //**********函数类型实例化**********//
    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
    val twoParameters: (String, Int) -> String = repeatFun // OK
    fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }
    val result = runTransformation(repeatFun) // OK
    println("result = "+result)//结果是result = hellohellohello
    val result2 = runTransformation(twoParameters) //
    println("result2 = "+result2)//结果是result2 = hellohellohello
    println("===================")

    testPerson()
}


data class Person(val name: String) {
    var age: Int = 0
}

fun testPerson(){
    println("===================")
    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20
    println("person1 == person2: ${person1 == person2}")
    println("person1 with age ${person1.age}: ${person1}")
    println("person2 with age ${person2.age}: ${person2}")
}