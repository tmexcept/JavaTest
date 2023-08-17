package kotlintest

import kotlin.properties.Delegates

interface BaseTest2 {
    val message: String
    fun printMessage()
    fun print()
}

class BaseImpl(val x: Int) : BaseTest2 {
    override val message = "BaseImpl: x = $x"
    override fun printMessage() {
        print(x)
    }

    override fun print() {
        println(message)
    }
}

class DerivedTest(b: BaseTest2) : BaseTest2 by b {
    // 在 b 的 `print` 实现中不会访问到这个属性
    override val message = "Message of Derived"

    override fun printMessage() {
        println("abc")
        print()
    }
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    val derived = DerivedTest(b)
    derived.print()
    println(derived.message)
    derived.printMessage()

    println("=========")
    println(lazyValue)
    println(lazyValue)

    println("=========")
    val user = User()
    user.name = "first"
    user.name = "second"
    user.lastName = "first"
    println(user.lastName)
    user.lastName = "second"
    println(user.lastName)


    var str : String? = null
    if(str != null) {
        println(str.length)
    }
}

val lazyValue: String by lazy {
    //默认情况下，对于 lazy 属性的求值是同步锁的（synchronized）
    println("computed!")// 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 后续调用 get() 只是返回记录的结果
    "Hello"
}

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
    var lastName: String by Delegates.vetoable("god") {
        prop, old, new ->

        println("$old -> $new")
        old > new
    }

}
//class MyClass {
//    var newName: Int = 0
//    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
//    var oldName: Int by this::newName
//}

//class ClassWithDelegate(var topLevelInt : Int) {
//    var anotherClassInt = 5
//}
//
//class MyClass(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
//    var delegatedToMember: Int by this::memberInt
//    var delegatedToTopLevel: Int by ::topLevelInt
//
//    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
//}
//var MyClass.extDelegated: Int by ::topLevelInt