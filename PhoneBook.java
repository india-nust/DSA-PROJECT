* head: Contact (initially NULL)

  insertContact(name, phoneNumber)
      if searchContact(name) is not null
          print "Contact already exists."
          return
      newContact = new Contact(name, phoneNumber)
      if head is null
          head = newContact
      else
          current = head
          while current.next is not null
              current = current.next
          current.next = newContact
      print "Contact added: " + name

  searchContact(name)
      current = head
      while current is not null
          if current.name (ignoring case) equals name
              return current
          current = current.next
      return null

  displayContacts()
      if head is null
          print "No contacts available."
      else
          current = head
          while current is not null
              print current
              current = current.next

  deleteContact(name)
      if head is null
          return false
      if head.name (ignoring case) equals name
          head = head.next
          display "Contact deleted: " + name
          return true
      current = head
      while current.next is not null
          if current.next.name (ignoring case) equals name
              current.next = current.next.next
              display "Contact deleted: " + name
              return true
          current = current.next
        display "Contact not found: " + name
      return false
  updateContact(name, newPhoneNumber)
      current = searchContact(name)
      if current is not null
          current.phoneNumber = newPhoneNumber
          display "Contact updated: " + name
          return true
      display Contact not found: " + name
      return false

  analyzeEfficiency()
      display"Efficiency Analysis of the Search Algorithm:"
      display  "The current search algorithm is a linear search with O(n) time complexity."
      display "Best Case: O(1) when the contact is the first one in the list."
      display "Average Case: O(n) as it scans through half of the contacts on average."
      display "Worst Case: O(n) when the contact is not found or is the last entry."
      display "As the number of contacts increases, the search time grows linearly."
      display "Potential improvements could include using a sorted list with binary search."

main()
  phonebook = new Phonebook
  scanner = new Scanner(System.in)
  choice = 0

  do
      display "--- Phonebook Menu ---"
      display "1. Insert Contact"
      display "2. Search Contact"
      display "3. Display All Contacts"
      display "4. Delete Contact"
      display "5. Update Contact"
      display "6. Analyze Search Efficiency"
      display "7. Exit"
      display "Enter your choice: "
      choice = scanner.nextInt()
      scanner.nextLine()

      switch (choice)
          case 1
              display "Enter name: "
              nameToInsert = scanner.nextLine()
              display "Enter phone number: "
              phoneToInsert = scanner.nextLine()
              phonebook.insertContact(nameToInsert, phoneToInsert)
          case 2
              display Enter name to search: "
              nameToSearch = scanner.nextLine()
              foundContact = phonebook.searchContact(nameToSearch)
              if foundContact is not null
                  display "Contact found: " + foundContact
              else
                  display "Contact not found."
          case 3
              phonebook.displayContacts()
          case 4
              display "Enter name to delete: "
              nameToDelete = scanner.nextLine()
              phonebook.deleteContact(nameToDelete)
          case 5
              display "Enter name to update: "
              nameToUpdate = scanner.nextLine()
              display Enter new phone number: "
              newPhoneNumber = scanner.nextLine()
              phonebook.updateContact(nameToUpdate, newPhoneNumber)
          case 6
              phonebook.analyzeEfficiency()
          case 7
              print
            display "Exiting the phonebook application."
          default
            ddisplay "Invalid choice. Please try again."

  while choice is not 7

  scanner.close() 
