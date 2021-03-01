package DAO;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class pe<T>{
    Class<T> tClass=null;
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType= (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        tClass= (Class) actualTypeArguments[0];
    }
    public  T  m(T id) {
        System.out.println(tClass);
        return id;
    }
}

class p extends pe<Integer>{
    public static void main(String[] args) {
        Integer m = new p().m(45);
        System.out.println(m);
    }
}
