public class BankTest {
    
}
class Banks{
    private Banks(){}
    private static Banks instance = null;
    public static synchronized Banks getInstance(){
        if(instance==null){
            instance = new Banks();
        }
        return instance;
    }
}