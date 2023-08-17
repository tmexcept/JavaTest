package kotlintest

/**
 * 伴生对象内的成员相当于 Java 中的静态成员，其生命周期伴随类始终，
 * 在伴生对象内部可以定义变量和函数，这些变量和函数可以直接用类名引用。
 * 对于伴生对象扩展函数，有两种形式，一种是在类内扩展，一种是在类外扩展，
 * 这两种形式扩展后的函数互不影响（甚至名称都可以相同），即使名称相同，它们也完全是两个不同的函数，并且有以下特点：

（1）可以同名，它们是两个独立的函数，互不影响；
（2）当类内扩展的伴随对象函数和类外扩展的伴随对象同名时，类内的其它函数优先引用类内扩展的伴随对象函数，
    即对于类内其它成员函数来说，类内扩展屏蔽类外扩展；
（3）类内扩展的伴随对象函数只能被类内的函数引用，不能被类外的函数和伴随对象内的函数引用；
（4）类外扩展的伴随对象函数可以被伴随对象内的函数引用，；
 */
class ClassExtendMethod {
    companion object {
        val ClassExtendMethodField1: Int = 1
        var ClassExtendMethodField2 = "this is ClassExtendMethodField2"
        fun companionFun1() {
            println("this is 1st companion function.")
            foo()
        }
        fun companionFun2() {
            println("this is 2st companion function.")
            companionFun1()
        }
    }
    fun ClassExtendMethod.Companion.foo() {
        println("伴随对象的扩展函数（内部）")
    }
    fun test2() {
        ClassExtendMethod.foo()
    }
    init {
        test2()
    }

    fun showMethod(show : Boolean, openMethod:()->Unit) {
        println("showMethod 内部打印 println")
        openMethod()//此处不调用，则外部调用 showMethod 方法的时候，大括号中的内容不运行
    }
}
val ClassExtendMethod.Companion.no: Int
    get() = 10

fun ClassExtendMethod.Companion.foo() {
    println("foo 伴随对象外部扩展函数")
}
fun main(args: Array<String>) {
//    println("no:${ClassExtendMethod.no}")
//    println("field1:${ClassExtendMethod.ClassExtendMethodField1}")
//    println("field2:${ClassExtendMethod.ClassExtendMethodField2}")
//    ClassExtendMethod.foo()
//    ClassExtendMethod.companionFun2()
//
//
//    println()
//
//    val method = ClassExtendMethod()
//    method.showMethod(true){
//        println("ClassExtendMethod  外部调用")
//    }
//
//
//    println()

    C().caller(D())   // 输出 "D.foo in C"
    C1().caller(D())  // 输出 "D.foo in C1" —— 分发接收者虚拟解析
    C().caller(D1())  // 输出 "D.foo in C" —— 扩展接收者静态解析
    C1().caller(D1())  // 输出 "D.foo in C1" —— 扩展接收者静态解析
}

/**
 * 以成员的形式定义的扩展函数, 可以声明为 open , 而且可以在子类中覆盖.
 * 也就是说, 在这类扩展函数的派发过程中, 针对分发接受者是虚拟的(virtual),
 * 但针对扩展接受者仍然是静态的。
 */
open class D {
}

class D1 : D() {
}

open class C {
    open fun D.foo() {
        println("D.foo in C")
    }

    open fun D1.foo() {
        println("D1.foo in C")
    }

    fun caller(d: D) {
        d.foo()   // 调用扩展函数
    }
}

class C1 : C() {
    override fun D.foo() {
        println("D.foo in C1")
    }

    override fun D1.foo() {
        println("D1.foo in C1")
    }
}
