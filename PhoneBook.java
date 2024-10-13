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
