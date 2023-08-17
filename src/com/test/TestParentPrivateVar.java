package com.test;

public class TestParentPrivateVar {

    public class VariableNumber{

    }

    public class Parent{
        private VariableNumber variableNumber = new VariableNumber();

        public Parent() {
            System.out.println("parent() variableNumber="+variableNumber);
        }

        public VariableNumber getVariableNumber(){
            System.out.println("parent variableNumber="+variableNumber);
            return variableNumber;
        }
    }

    public class Child extends Parent {

        private VariableNumber variableNumber = new VariableNumber();

        public Child() {
            System.out.println("Child() variableNumber="+variableNumber);
        }

        @Override
        public VariableNumber getVariableNumber(){//会覆盖父类方法，且取到自己的私有变量
            System.out.println("Child variableNumber="+variableNumber);
            return variableNumber;
        }
    }

    public void testChildPrivate(){
        Child child = new Child();

        System.out.println("child.getVariableNumber()="+child.getVariableNumber());
    }


    public static void main(String[] args) {
//        Child child = new Child();//此处Child必须是【static class Child】，非静态类会报错，无法编译，

        TestParentPrivateVar privateVar = new TestParentPrivateVar();
        privateVar.testChildPrivate();
    }


}
