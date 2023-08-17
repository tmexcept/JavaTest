package kotlintest


/**
 * 初始化块中的代码实际上会成为主构造函数的一部分。
 * 对主构造函数的委托发生在访问次构造函数的第一条语句时，
 * 因此所有初始化块与属性初始化器中的代码都会在次构造函数体之前执行。

    即使该类没有主构造函数，这种委托仍会隐式发生，并且仍会执行初始化块
 */
class Constructors {
    init {
        println("Constructors Init block")
    }

    constructor(i: Int) {
        println("Constructors Constructor $i")
    }
}

open class Polygon {
    open fun draw() {
        // some default polygon drawing method
    }
}

abstract class WildShape : Polygon() {
    // Classes that inherit WildShape need to provide their own
    // draw method instead of using the default on Polygon
    abstract override fun draw()
}
/**派生类初始化顺序 start*/
//sampleStart
open class Base(open val name: String) {

    init { println("init In Base $name") }

    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}

class Derived(name: String,
              val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it  $name") }) {

    init { println("init In Derived") }

    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }

    var stringRepresentation: String
        get() = name
        set(value) {
            setDataFromString(value) // 解析字符串内容, 并将解析得到的值赋给对应的其他属性
        }

    fun setDataFromString(value: String) {
//        name = value +"  "+name
    }
}

/**
 * 这意味着，基类构造函数执行时，派生类中声明或覆盖的属性都还没有初始化。
 * 在基类初始化逻辑中（直接或者通过另一个覆盖的 open 成员的实现间接）使用任何一个这种属性，
 * 都可能导致不正确的行为或运行时故障。 设计一个基类时，
 * 应该避免在构造函数、属性初始化器或者 init 块中使用 open 成员。
 */
class Derived2(override var name: String,
              val lastName: String
) : Base(name) {

    init { println("Initializing Derived2222") }

    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }

    var stringRepresentation: String
        get() = name
        set(value) {
            setDataFromString(value) // 解析字符串内容, 并将解析得到的值赋给对应的其他属性
        }

    fun setDataFromString(value: String) {
        name = value +"  "+name
    }
}
//sampleEnd
/**派生类初始化顺序 end*/

fun main() {
    Constructors(1)

//    var wildShape = WildShape(){
//
//    }

    println("********************************")

    val derived = Derived("derived", "姓什么")
    println(derived.stringRepresentation)

    derived.stringRepresentation = "what name is "
    println(derived.stringRepresentation)

    println("*************")
//    val derived2 = Derived2("derived2", "姓什么")

}