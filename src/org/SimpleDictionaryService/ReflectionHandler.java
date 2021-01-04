package org.SimpleDictionaryService;

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
import java.util.stream.Stream;

public class ReflectionHandler{

    private final List<Field> fields = new ArrayList<>();
    private final List<Method> methods = new ArrayList<>();
    private Object context;
    private Class<?> contextType;

    public ReflectionHandler(Class<?> contextType, Object context, List<String> fieldNames, List<String> methodNames){
        this.contextType = contextType;
        this.context = context;
        this.initializeUsedFields(fieldNames);
        this.initializeUsedMethods(methodNames);
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
                throw new NoSuchMethodException();
            }
        }catch (NoSuchMethodException exception){
            exception.printStackTrace();
        }
        return responseMethod.get();
    }

    public Object invokeMethod(String methodName, Object... args){
        try {
            return this.findMethod(methodName).invoke(context, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    public Object invokeLambdaMethod(String fieldName, Object... args){
        try {
            Field field = this.findField(fieldName);
            return field.getType().getDeclaredMethods()[0].invoke(field.get(context), args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    private void initializeUsedFields(List<String> fieldNames){
        fieldNames.stream()
                .forEach(checkedFieldName -> {
                    Optional<Field> requiredField = Arrays.stream(this.contextType.getFields())
                            .filter(field -> field.getName().equals(checkedFieldName))
                            .findAny();
                    try {
                        if(requiredField.isPresent()){
                            this.fields.add(requiredField.get());
                        }else throw new UnknownFieldException(checkedFieldName, OccurrenceTime.ON_INIT);
                    }catch (UnknownFieldException exception){
                        exception.printStackTrace();
                    }
                });
    }

    private void initializeUsedMethods(List<String> methodNames){
        methodNames.stream()
                .forEach(checkedMethodName -> {
                    Optional<Method> requiredMethod = Arrays.stream(this.contextType.getMethods())
                            .filter(method -> method.getName().equals(checkedMethodName))
                            .findAny();
                    try {
                        if(requiredMethod.isPresent()){
                            this.methods.add(requiredMethod.get());
                        }else throw new UnknownMethodException(checkedMethodName, OccurrenceTime.ON_INIT);
                    }catch (UnknownMethodException exception){
                        exception.printStackTrace();
                    }
                });
    }
}