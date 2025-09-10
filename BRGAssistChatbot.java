import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BRGAssistChatbot {
    
    // Predefined responses
    private static Map<String, String[]> responses = new HashMap<>();
    
    static {
        responses.put("greeting", new String[]{"Hello! How can I help you?", "Hi there! Need any assistance?"});
        responses.put("goodbye", new String[]{"Goodbye! Have a great day!", "See you soon!"});
        responses.put("thanks", new String[]{"You're welcome!", "Glad I could help!"});
        responses.put("name", new String[]{"I'm BRG Assist, your service chatbot."});
        responses.put("help", new String[]{"Sure! Please tell me what you need help with."});
        responses.put("service", new String[]{"We offer 24/7 customer support, billing help, and more."});
        responses.put("billing", new String[]{"You can pay using credit card or UPI. If you're facing issues, contact our billing team."});
    }
    
    // Simple keyword-based intent detection
    private static String getBotResponse(String userInput) {
        String text = userInput.toLowerCase();

        if (text.contains("hello") || text.contains("hi") || text.contains("hey")) {
            return randomResponse("greeting");
        } else if (text.contains("bye")) {
            return randomResponse("goodbye");
        } else if (text.contains("thank")) {
            return randomResponse("thanks");
        } else if (text.contains("your name") || text.contains("who are you")) {
            return randomResponse("name");
        } else if (text.contains("help")) {
            return randomResponse("help");
        } else if (text.contains("service")) {
            return randomResponse("service");
        } else if (text.contains("billing") || text.contains("pay") || text.contains("payment")) {
            return randomResponse("billing");
        } else {
            return "I'm sorry, I didn't understand that. Could you rephrase?";
        }
    }
    
    private static String randomResponse(String intent) {
        String[] options = responses.get(intent);
        Random rand = new Random();
        return options[rand.nextInt(options.length)];
    }
    
    // GUI with Swing
    public static void main(String[] args) {
        JFrame frame = new JFrame("🤖 BRG Assist - Customer Service Chatbot");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(chatArea);
        
        JTextField entryField = new JTextField();
        JButton sendButton = new JButton("Send");
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(entryField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);
        
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        
        // Initial bot message
        chatArea.append("BRG Assist: Hi! I’m your customer service assistant. How can I help?\n\n");
        
        // Send message function
        ActionListener sendAction = e -> {
            String userText = entryField.getText().trim();
            if (!userText.isEmpty()) {
                chatArea.append("You: " + userText + "\n");
                String botReply = getBotResponse(userText);
                chatArea.append("BRG Assist: " + botReply + "\n\n");
                entryField.setText("");
            }
        };
        
        sendButton.addActionListener(sendAction);
        entryField.addActionListener(sendAction);
        
        frame.setVisible(true);
    }
}
