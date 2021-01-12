package org.SimpleDictionaryService.handlers;

import org.SimpleDictionaryService.throwable.OccurrenceTime;
import org.SimpleDictionaryService.throwable.UnknownFieldException;
import org.SimpleDictionaryService.throwable.UnknownMethodException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReflectionHandler<T>{

    private final List<Field> fields = new ArrayList<>();
    private final List<Method> methods = new ArrayList<>();
    private final Class<T> contextType;

    public ReflectionHandler(Class<T> contextType){
        this.contextType = contextType;
    }

    public Field findField(String fieldName){
        Optional<Field> responseField = fields.stream()
                .filter(field -> field.getName().equals(fieldName))
                .findAny();
        try {
            if (responseField.isPresent()){
                return responseField.get();
            }else throw new UnknownFieldException(fieldName, OccurrenceTime.ON_USE);
        }catch (UnknownFieldException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public Field findLambdaMethod(String fieldName){
        return this.findField(fieldName);
    }

    public Method findMethod(String methodName){
        Optional<Method> responseMethod = ((ArrayList<Method>) methods).stream()
                .filter(method -> method.getName().equals(methodName))
                .findAny();
        try {
            if(responseMethod.isPresent()){
                return responseMethod.get();
            }else{
                throw new UnknownMethodException(methodName, OccurrenceTime.ON_USE);
            }
        }catch (UnknownMethodException exception){
            exception.printStackTrace();
        }
        return responseMethod.get();
    }

    public Object invokeMethod(T context, String methodName, Object... args){
        try {
            return this.findMethod(methodName).invoke(context, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    public Object invokeLambdaMethod(T context, String fieldName, Object... args){
        try {
            Field field = this.findField(fieldName);
            return field.getType().getDeclaredMethods()[0].invoke(field.get(context), args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    public void initializeUsedFields(List<String> fieldNames){
        fieldNames.stream()
                .forEach(checkedFieldName -> {
                    Optional<Field> requiredField = Arrays.stream(this.contextType.getDeclaredFields())
                            .filter(field -> field.getName().equals(checkedFieldName))
                            .findAny();
                    try {
                        if(requiredField.isPresent()){
                            // Only for enum variable(inner class)!
                            // requiredField.get().setAccessible(true);
                            this.fields.add(requiredField.get());
                        }else throw new UnknownFieldException(checkedFieldName, OccurrenceTime.ON_INIT);
                    }catch (UnknownFieldException exception){
                        exception.printStackTrace();
                    }
                });
    }

    public void initializeUsedMethods(List<String> methodNames){
        methodNames.stream()
                .forEach(checkedMethodName -> {
                    Optional<Method> requiredMethod = Arrays.stream(this.contextType.getMethods())
                            .filter(method -> method.getName().equals(checkedMethodName))
                            .findAny();
                    try {
                        if(requiredMethod.isPresent()){
                            // Only for enum variable(inner class)!
                            // requiredMethod.get().setAccessible(true);
                            this.methods.add(requiredMethod.get());
                        }else throw new UnknownMethodException(checkedMethodName, OccurrenceTime.ON_INIT);
                    }catch (UnknownMethodException exception){
                        exception.printStackTrace();
                    }
                });
    }
}