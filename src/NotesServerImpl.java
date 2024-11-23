import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotesServerImpl extends UnicastRemoteObject implements NotesService {
    private final HashMap<String, String> notes;

    protected NotesServerImpl() throws RemoteException {
        notes = new HashMap<>();
    }

    @Override
    public synchronized void createNote(String title, String content) throws RemoteException {
        if (!notes.containsKey(title)) {
            notes.put(title, content);
        } else {
            throw new RemoteException("Topic with this title already taken");
        }
    }

    @Override
    public synchronized String readNote(String title) throws RemoteException {
        return notes.getOrDefault(title, "Topic not fetched");
    }

    @Override
    public synchronized void updateNote(String title, String newContent) throws RemoteException {
        if (notes.containsKey(title)) {
            notes.put(title, newContent);
        } else {
            throw new RemoteException("Topic not fetched. No update.");
        }
    }

    @Override
    public synchronized void deleteNote(String title) throws RemoteException {
        if (notes.remove(title) == null) {
            throw new RemoteException("Topic not fetched. No deletion.");
        }
    }

    @Override
    public synchronized List<String> listNotes() throws RemoteException {
        return new ArrayList<>(notes.keySet());
    }
}