import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class NotesServerMain {
    public static void main(String[] args) {
        try {
            //intialize the rmiregistry
            LocateRegistry.createRegistry(5000);

            //enables the Notes Task
            NotesService notesService = new NotesServerImpl();
            Naming.rebind("rmi://localhost/NotesService", notesService);

            System.out.println("Journal is ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}