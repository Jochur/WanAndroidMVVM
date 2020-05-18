package com.grechur.collect;

import org.junit.Test;
import org.w3c.dom.Text;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        try {
            System.out.println(">>>>>>>>>>>testList>>>>>>>>>>>");
            new TestT().testList();
            System.out.println("<<<<<<<<<<<testList<<<<<<<<<<<\n");
            System.out.println(">>>>>>>>>>>testMap>>>>>>>>>>>");
            new TestT().testMap();
            System.out.println("<<<<<<<<<<<testMap<<<<<<<<<<<\n");
            System.out.println(">>>>>>>>>>>testClassA>>>>>>>>>>>");
            new TestT().testClassA();
            System.out.println("<<<<<<<<<<<testClassA<<<<<<<<<<<");
        }catch (Exception e){

        }

    }

    class  ClassA <T>{
        private T obj;
        public void setObject(T obj) {      this.obj = obj;  }
        public T getObject() {    return obj;   }

        /**
         * 获取T的实际类型
         */
        public void testClassA() throws NoSuchFieldException, SecurityException {
            System.out.print("getSuperclass:");
            System.out.println(this.getClass().getSuperclass().getName());
            System.out.print("getGenericSuperclass:");
            Type t = this.getClass().getGenericSuperclass();
            System.out.println(t);
            if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
                System.out.print("getActualTypeArguments:");
                for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                    System.out.print(t1 + ",");
                }
                System.out.println();
            }
        }
    }

    class TestT extends ClassA<String> {
        private List<String> list;
        private Map<String, Object> map;

        /***
         * 获取List中的泛型
         */
        public void testList() throws NoSuchFieldException, SecurityException {
            Type t = TestT.class.getDeclaredField("list").getGenericType();
            if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
                for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                    System.out.print(t1 + ",");
                }
                System.out.println();
            }
        }

        /***
         * 获取Map中的泛型
         */
        public void testMap() throws NoSuchFieldException, SecurityException {
            Type t = TestT.class.getDeclaredField("map").getGenericType();
            if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
                for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                    System.out.print(t1 + ",");
                }
                System.out.println();
            }
        }
    }
}