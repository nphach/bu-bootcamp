package module3;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactManager {
    public static void main(String[] args) {
        HashMap<String, Contact> contacts = new HashMap<>();

        // add contacts
        contacts.put("Nikki", new Contact("Nikki", "111-111-1111"));
        contacts.put("Nami", new Contact("Nami", "222-222-2222"));
        contacts.put("Viego", new Contact("Viego", "333-333-3333"));
        contacts.put("Alec", new Contact("Alec", "444-444-4444"));
        contacts.put("Bob", new Contact("Bob", "555-555-5555"));

        // look up contacts
        retrieveContact(contacts, "Nikki");
        retrieveContact(contacts, "Ada Lovelace");

        // sort and print contacts
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts ===");
        for (Contact s : sorted) {
            System.out.println(s);
        }
    }

    private static void retrieveContact(HashMap<String, Contact> contacts, String name) {
        Contact search = contacts.get(name);
        if (search != null) {
            System.out.println(search.toString());
        } else {
            System.out.println("Contact not found");
        }
    }
}