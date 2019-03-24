package com.example.demo.repository;

public class ClassMethodName {

    public static void main(String[] args) {
	ClassMethodName demo = new ClassMethodName();
        demo.go();

    }
    public void go(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for(StackTraceElement s : stackTrace){
            System.out.println("类名：" + s.getClassName() + "  ,  java文件名：" + s.getFileName() + ",  当前方法名字：" + s.getMethodName() + "" + " , 当前代码是第几行：" + s.getLineNumber() + ", " );
        }
    }
}
