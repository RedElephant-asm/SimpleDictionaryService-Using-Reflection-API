package org.SimpleDictionaryService.crud;

import org.SimpleDictionaryService.handlers.ReflectionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Deprecated
public enum CRUDOperations{

    CREATE  (){
        public final Creating method = (Integer firstValue, Integer secondValue) -> {
            return firstValue + secondValue;
        };

        @Override
        protected void initSpecifications() {
            this.inReflectionFields.addAll(Arrays.asList());
            this.inReflectionMethods.addAll(Arrays.asList());
        }
    },
    READ    (){
        public final Reading method = (String strValue) -> {
            return "READ : " + strValue;
        };

        @Override
        void initSpecifications() {
            this.inReflectionFields.addAll(Arrays.asList());
            this.inReflectionMethods.addAll(Arrays.asList());
        }
    },
    UPDATE  (){
        public final Updating method = (int firstValue) -> {
            return firstValue + 1;
        };

        @Override
        void initSpecifications() {
            this.inReflectionFields.addAll(Arrays.asList());
            this.inReflectionMethods.addAll(Arrays.asList());
        }
    },
    DELETE  (){
        public final Deleting method = (int firstValue) -> {
            return firstValue - 2;
        };

        @Override
        void initSpecifications() {
            this.inReflectionFields.addAll(Arrays.asList());
            this.inReflectionMethods.addAll(Arrays.asList());
        }
    };

    protected List<String> inReflectionMethods = new ArrayList<>(Arrays.<String>asList());
    protected List<String> inReflectionFields = new ArrayList<>(Arrays.<String>asList("method"));

    protected final ReflectionHandler reflectionHandler;

    CRUDOperations() {
        this.initSpecifications();
        this.reflectionHandler = new ReflectionHandler<>(this.getClass());
        this.reflectionHandler.initializeUsedFields(inReflectionFields);
        this.reflectionHandler.initializeUsedMethods(inReflectionMethods);
    }

    abstract void initSpecifications();

    public Object execute(Object... args) {
        return this.reflectionHandler.invokeLambdaMethod(this, inReflectionFields.get(0), args);
    }
}