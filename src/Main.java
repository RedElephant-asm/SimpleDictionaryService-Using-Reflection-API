import org.SimpleDictionaryService.CRUDOperations;

public class Main {
    public static void main(String[] args){
        System.out.println(CRUDOperations.CREATE.execute(5, 96));
        System.out.println(CRUDOperations.READ.execute("Some text"));
        System.out.println(CRUDOperations.UPDATE.execute(53));
        System.out.println(CRUDOperations.DELETE.execute(29));
    }
}
