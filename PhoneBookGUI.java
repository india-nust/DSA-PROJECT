package phonebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Contact {
    String name;
    String phoneNumber;
    Contact next;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber;
    }
}

public class PhonebookGUI extends JFrame {
    private Contact head;
    private JTextArea contactListArea;
    private JTextField nameField;
    private JTextField phoneField;

    public PhonebookGUI() {
        setTitle("Phonebook Application");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout manager
        setLayout(new BorderLayout());

        // Input panel (top part)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        // Add buttons for actions
        JButton insertButton = new JButton("Insert Contact");
        JButton searchButton = new JButton("Search Contact");
        JButton deleteButton = new JButton("Delete Contact");
        JButton updateButton = new JButton("Update Contact");

        inputPanel.add(insertButton);
        inputPanel.add(searchButton);

        // Action panel (bottom part)
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2, 1));

        actionPanel.add(deleteButton);
        actionPanel.add(updateButton);

        // Contact display area (center part)
        contactListArea = new JTextArea();
        contactListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contactListArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Input validation for name (only letters)
        nameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Only allow letters (both uppercase and lowercase)
                if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_SPACE) {
                    e.consume(); // Ignore the key event if it's not a letter
                }
            }
        });

        // Input validation for phone (only digits)
        phoneField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Only allow digits
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignore the key event if it's not a digit
                }
            }
        });

        // Button action listeners
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertContact(nameField.getText(), phoneField.getText());
                displayContacts();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Contact found = searchContact(name);
                if (found != null) {
                    contactListArea.setText("Contact Found:\n" + found.toString());
                } else {
                    contactListArea.setText("Contact not found.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                boolean deleted = deleteContact(name);
                if (deleted) {
                    displayContacts();
                } else {
                    contactListArea.setText("Contact not found.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String newPhoneNumber = phoneField.getText();
                boolean updated = updateContact(name, newPhoneNumber);
                if (updated) {
                    displayContacts();
                } else {
                    contactListArea.setText("Contact not found.");
                }
            }
        });
    }

    // Insert a new contact
    public void insertContact(String name, String phoneNumber) {
        if (searchContact(name) != null) {
            contactListArea.setText("Contact already exists.");
            return;
        }

        Contact newContact = new Contact(name, phoneNumber);
        if (head == null) {
            head = newContact;
        } else {
            Contact current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newContact;
        }
        contactListArea.setText("Contact added: " + name);
    }

    // Search for a contact
    public Contact searchContact(String name) {
        Contact current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Display all contacts
    public void displayContacts() {
        if (head == null) {
            contactListArea.setText("No contacts available.");
        } else {
            Contact current = head;
            StringBuilder sb = new StringBuilder();
            while (current != null) {
                sb.append(current).append("\n");
                current = current.next;
            }
            contactListArea.setText(sb.toString());
        }
    }

    // Delete a contact
    public boolean deleteContact(String name) {
        if (head == null) return false;
        if (head.name.equalsIgnoreCase(name)) {
            head = head.next;
            contactListArea.setText("Contact deleted: " + name);
            return true;
        }
        Contact current = head;
        while (current.next != null) {
            if (current.next.name.equalsIgnoreCase(name)) {
                current.next = current.next.next;
                contactListArea.setText("Contact deleted: " + name);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Update a contact's phone number
    public boolean updateContact(String name, String newPhoneNumber) {
        Contact current = searchContact(name);
        if (current != null) {
            current.phoneNumber = newPhoneNumber;
            contactListArea.setText("Contact updated: " + name);
            return true;
        }
        return false;
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Run the GUI application in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PhonebookGUI().setVisible(true);  // Create the GUI and make it visible
            }
        });
    }
}
