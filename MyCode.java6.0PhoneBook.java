import java.util.Scanner;
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

public class Phonebook {
    private Contact head;

    public Phonebook() {
        head = null;
    }

    public void insertContact(String name, String phoneNumber) {
        if (searchContact(name) != null) {
            System.out.println("Contact already exists.");
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
        System.out.println("Contact added: " + name);
    }

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

    public void displayContacts() {
        if (head == null) {
            System.out.println("No contacts available.");
        } else {
            Contact current = head;
            while (current != null) {
                System.out.println(current);
                current = current.next; 
            }
        }
    }

    
    public boolean deleteContact(String name) {
        if (head == null) return false; 
        if (head.name.equalsIgnoreCase(name)) { 
            head = head.next; 
            System.out.println("Contact deleted: " + name);
            return true;
        }
        Contact current = head;
        while (current.next != null) {
            if (current.next.name.equalsIgnoreCase(name)) {
                current.next = current.next.next; 
                System.out.println("Contact deleted: " + name);
                return true;
            }
            current = current.next; 
        }
        System.out.println("Contact not found: " + name);
        return false;
    }

    public boolean updateContact(String name, String newPhoneNumber) {
        Contact current = searchContact(name);
        if (current != null) {
            current.phoneNumber = newPhoneNumber; 
            System.out.println("Contact updated: " + name);
            return true;
        }
        System.out.println("Contact not found: " + name);
        return false;
    }
    public void analyzeEfficiency() {
        System.out.println("Efficiency Analysis of the Search Algorithm:");
        System.out.println("The current search algorithm is a linear search with O(n) time complexity.");
        System.out.println("Best Case: O(1) when the contact is the first one in the list.");
        System.out.println("Average Case: O(n) as it scans through half of the contacts on average.");
        System.out.println("Worst Case: O(n) when the contact is not found or is the last entry.");
        System.out.println("As the number of contacts increases, the search time grows linearly.");
        System.out.println("Potential improvements could include using a sorted list with binary search.");
    }

    
    
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\\n--- Phonebook Menu ---");
            System.out.println("1. Insert Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Delete Contact");
            System.out.println("5. Update Contact");
            System.out.println("6. Analyze Search Efficiency");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String nameToInsert = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneToInsert = scanner.nextLine();
                    phonebook.insertContact(nameToInsert, phoneToInsert);
                    break;

                case 2:
                    System.out.print("Enter name to search: ");
                    String nameToSearch = scanner.nextLine();
                    Contact foundContact = phonebook.searchContact(nameToSearch);
                    if (foundContact != null) {
                        System.out.println("Contact found: " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    phonebook.displayContacts();
                    break;

                case 4:
                    System.out.print("Enter name to delete: ");
                    String nameToDelete = scanner.nextLine();
                    phonebook.deleteContact(nameToDelete);
                    break;

                case 5:
                    System.out.print("Enter name to update: ");
                    String nameToUpdate = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    phonebook.updateContact(nameToUpdate, newPhoneNumber);
                    break;

                case 6:
                    phonebook.analyzeEfficiency();
                    break;

                case 7:
                    System.out.println("Exiting the phonebook application.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7); 

        scanner.close(); 
    }
}