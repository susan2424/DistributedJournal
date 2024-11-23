import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NotesService extends Remote {
    void createNote(String title, String content) throws RemoteException;
    String readNote(String title) throws RemoteException;
    void updateNote(String title, String newContent) throws RemoteException;
    void deleteNote(String title) throws RemoteException;
    List<String> listNotes() throws RemoteException;
}