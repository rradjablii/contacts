import React, { useEffect, useState } from "react";
import axios from "axios";

const ContactForm = () => {
  const [contact, setContact] = useState({
    name: "",
    email: "",
    phone: "",
  });

  const [contacts, setContacts] = useState<Contact[]>([]);

  interface Contact {
    name: string;
    email: string;
    phone: string;
  }

  function fetchData() {
    axios
      .get("http://localhost:8080/contacts") // Spring Boot backend URL
      .then((response) => {
        setContacts(response.data); // Set the fetched data to state
      });
  }

  useEffect(() => {
    fetchData();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setContact({ ...contact, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/contacts",
        contact
      );
      if (response.data == "") alert("already used creditentials");
      else {
        console.log("Contact added:", response.data);
        setContact({
          name: "",
          phone: "",
          email: "",
        });
      }
    } catch (error) {
      console.error(contact);
    }
    fetchData();
  };

  return (
    <div className="main">
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={contact.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={contact.email}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Phone Number:</label>
          <input
            type="text"
            name="phone"
            value={contact.phone}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Add Contact</button>
      </form>

      <div className="contactList">
        <h1>Users List</h1>
        <ul>
          {contacts.map((contact, index) => (
            <li key={index}>
              {contact.name} - {contact.phone} - {contact.email}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ContactForm;
