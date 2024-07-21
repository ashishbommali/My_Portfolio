// src/Pages/Contact.js
import React from 'react';
import '../styles/pages/contact.css';
import {
  FaEnvelope, FaLinkedin, FaGithub, FaPhone, FaLandmark
} from 'react-icons/fa';

const Contact = () => {
  return (
    <div id="contact">
      <div className="contact-container">
        <div className="contact-form">
          <h2>Send a Message</h2>
          <form>

            <input type="text" id="name" placeholder='Name' name="name" required />


            <input type="tel" id="phone" placeholder='Phone' name="phone" required />


            <input type="email" id="email" placeholder='Email' name="email" required />


            <input type="text" id="company" placeholder='Company Name' name="company" required />


            <input type="text" id="address" placeholder='Address' name="address" required />

            <textarea id="message" placeholder='Message' name="message" required></textarea>

            <button type="submit">Send</button>
          </form>
        </div>
        <div className="contact-details">
          <h2>Contact Me</h2>
          <p><strong>Email:</strong> <a href="mailto:ashishbommali@gmail.com" target="_blank" rel="noopener noreferrer"><FaEnvelope /></a></p>
          <p><strong>linkedin:</strong><a href="https://www.linkedin.com/in/ashish-bommali-5a8686170" target="_blank" rel="noopener noreferrer"><FaLinkedin /></a></p>
          <p><strong>GitHub:</strong> <a href="https://github.com/ashishbommali" target="_blank" rel="noopener noreferrer"><FaGithub /></a></p>
          <p><strong>Phone <FaPhone />:</strong> +1 6822727437</p>
          <p><strong>Address <FaLandmark />:</strong> Apt #280 513, 513 summit ave, Meadow run, Arlington, Tx - 76013</p>
        </div>
      </div>
    </div>
  );
};

export default Contact;