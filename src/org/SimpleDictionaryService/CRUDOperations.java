package org.SimpleDictionaryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @FunctionalInterface
    public interface Creating extends CRUDExecutable {
        int execute(Integer firstValue, Integer secondValue);
    }

    @FunctionalInterface
    public interface Reading extends CRUDExecutable {
        String execute(String strValue);
    }

    @FunctionalInterface
    public interface Updating extends CRUDExecutable {
        int execute(int firstValue);
    }

    @FunctionalInterface
    public interface Deleting extends CRUDExecutable {
        int execute(int firstValue);
    }

    protected List<String> inReflectionMethods = new ArrayList<>(Arrays.<String>asList());
    protected List<String> inReflectionFields = new ArrayList<>(Arrays.<String>asList("method"));

    protected final ReflectionHandler reflectionHandler;

    CRUDOperations(String... additional) {
        this.initSpecifications();
        this.reflectionHandler = new ReflectionHandler(this.getClass(), this, this.inReflectionFields, this.inReflectionMethods);
    }

    abstract void initSpecifications();

    public Object execute(Object... args) {
        return this.reflectionHandler.invokeLambdaMethod(inReflectionFields.get(0), args);
    }
}