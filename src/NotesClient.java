import java.rmi.Naming;
import java.util.Scanner;

public class NotesClient {
    public static void main(String[] args) {
        try {
            NotesService notesService = (NotesService) Naming.lookup("rmi://localhost/NotesService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nOptions: create, read, update, delete, list, exit");
                System.out.print("Enter Option: ");
                String command = scanner.nextLine().trim();

                switch (command.toLowerCase()) {
                    case "create":
                        System.out.print("Topic: ");
                        String title = scanner.nextLine();
                        System.out.print("Context: ");
                        String content = scanner.nextLine();
                        try {
                            notesService.createNote(title, content);
                            System.out.println("Note created successfully.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case "read":
                        System.out.print("Topic: ");
                        title = scanner.nextLine();
                        System.out.println("Context: " + notesService.readNote(title));
                        break;

                    case "update":
                        System.out.print("Topic: ");
                        title = scanner.nextLine();
                        System.out.print("Updated Context: ");
                        String newContent = scanner.nextLine();
                        try {
                            notesService.updateNote(title, newContent);
                            System.out.println("Journal updated successfully.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case "delete":
                        System.out.print("Topic: ");
                        title = scanner.nextLine();
                        try {
                            notesService.deleteNote(title);
                            System.out.println("Note permanently deleted! ");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case "list":
                        System.out.println("Notes:");
                        for (String noteTitle : notesService.listNotes()) {
                            System.out.println(" - " + noteTitle);
                        }
                        break;

                    case "exit":
                        System.out.println("Closing");
                        System.exit(0);

                    default:
                        System.out.println("Option not valid, pick the ones listed!");
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}