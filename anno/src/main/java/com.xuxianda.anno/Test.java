package com.xuxianda.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Xianda Xu on 2017/08/10 17:16.
 */
public class Test {

    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(10);

        User u2 = new User();
        u2.setUserName("张三");

        User u3 = new User();
        u3.setEmail("11@qq.com,22@sina.com,33@163.com");

        String sql1 = query(u1);
        String sql2 = query(u2);
        String sql3 = query(u3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
    }

    private static String query(Object o){
        StringBuilder sb = new StringBuilder();
        //1获取到class
        Class clazz = o.getClass();
        boolean isExist = clazz.isAnnotationPresent(Table.class);
        if(!isExist){
            return null;
        }
        Table table = (Table)clazz.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("select * from ").append(tableName).append(" where 1=1");
        //遍历所有字段
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            //4.处理每个字段对应的sql
            boolean fExists = field.isAnnotationPresent(Column.class);
            if(!fExists){
                continue;
            }
            //4.1拿到字段名
            Column column = field.getAnnotation(Column.class);
            String columnValue = column.value();
            //4.2拿到字段的值
            String fieldName = field.getName();
            String getMethodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            Object fieldValue = null;
            try{
                Method method = clazz.getMethod(getMethodName);
                fieldValue = method.invoke(o);
                if(fieldValue ==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){
                    continue;
                }
                //4.3 拼接sql
                if(fieldValue instanceof Integer){
                    sb.append(" and ").append(columnValue).append("=").append(fieldValue);
                }
                if(fieldValue instanceof String){
                    String f = (String)fieldValue;
                    if(f.contains(",")){
                        String[] split = f.split(",");
                        sb.append(" and ").append(columnValue).append(" in(");
                        for(String temp : split){
                            sb.append("'").append(temp).append("',");
                        }
                        sb.deleteCharAt(sb.length()-1);
                        sb.append(")");
                    }else{
                        sb.append(" and ").append(columnValue).append("=").append("'").append(fieldValue).append("'");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
