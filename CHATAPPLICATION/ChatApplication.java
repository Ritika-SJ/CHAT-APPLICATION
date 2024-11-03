import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Message class with timestamp support
class Message {
    private String content;
    private String sender;
    private LocalDateTime timestamp;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
        this.timestamp = LocalDateTime.now(); // Automatically set to current time
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
}

// User class
class User {
    private String userId;
    private String userName;
    private List<Message> chatHistory;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.chatHistory = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void sendMessage(String content, User receiver) {
        Message message = new Message(content, this.userName);
        receiver.receiveMessage(message);
        this.chatHistory.add(message);
    }

    public void receiveMessage(Message message) {
        this.chatHistory.add(message);
    }

    public void viewChatHistory() {
        System.out.println("Chat history for " + this.userName + ":");
        for (Message message : chatHistory) {
            System.out.println(message.getFormattedTimestamp() + " " + message.getSender() + ": " + message.getContent());
        }
    }
}

// Main class to simulate chat between two users
public class ChatApplication {
    public static void main(String[] args) {
        // Create two users: "Me" and "Prem"
        User me = new User("U1", "Me");
        User Prem = new User("U2", "Prem");

        Scanner scanner = new Scanner(System.in);

        // Simulating a conversation between Me and Prem
        String input;
        boolean chatting = true;

        while (chatting) {
            // Input for "Me"
            System.out.print("Me: ");
            input = scanner.nextLine();
            me.sendMessage(input, Prem);

            // Input for "prem"
            System.out.print("Prem: ");
            input = scanner.nextLine();
            Prem.sendMessage(input, me);

            // Ask if the user wants to continue chatting
            System.out.print("Do you want to continue chatting? (yes/no): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("no")) {
                chatting = false;
            }
        }

        // Displaying the full conversation history
        System.out.println("\n--- Full Conversation History ---\n");

        // Viewing chat history for "Me"
        me.viewChatHistory();

        System.out.println(); // For spacing

        // Viewing chat history for "Bob"
        Prem.viewChatHistory();

        scanner.close();
    }
}
