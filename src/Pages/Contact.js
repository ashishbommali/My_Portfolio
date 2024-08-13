import React, { useRef, useState } from 'react';
import '../styles/pages/contact.css';
import {
  FaEnvelope, FaLinkedin, FaGithub, FaPhone, FaLandmark
} from 'react-icons/fa';
import emailjs from '@emailjs/browser';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const Contact = () => {
  const form = useRef();
  const [notification, setNotification] = useState('');
  const [notificationType, setNotificationType] = useState(''); // 'success' or 'error'

  const sendEmail = (e) => {
    e.preventDefault();

    emailjs
      .sendForm(
        process.env.REACT_APP_EMAILJS_SERVICE_ID,
        process.env.REACT_APP_EMAILJS_TEMPLATE_ID,
        form.current,
        process.env.REACT_APP_EMAILJS_PUBLIC_KEY
      )
      .then(
        () => {
          toast.success('Message sent successfully!');
          // setNotification('Message sent successfully!');
          // setNotificationType('success');
          // setTimeout(() => setNotification(''), 3000); // Clear after 3 seconds
        },
        (error) => {
          toast.error('Failed to send message. Please try again.');
          // setNotification('Failed to send message. Please try again.');
          // setNotificationType('error');
          // setTimeout(() => setNotification(''), 3000); // Clear after 3 seconds
        }
      );
  };

  return (
    <div id="contact">
      <ToastContainer />
      <div className="contact-container">
        {notification && (
          <div className={`notification ${notificationType}`}>
            {notification}
          </div>
        )}
        <div className="contact-form">
          <h2>Write me a Message</h2>
          <form ref={form} onSubmit={sendEmail}>
            <input type="text" placeholder="Name" name="user_name" required />
            <input type="tel" id="phone" placeholder="Phone" name="user_phone" required />
            <input type="email" placeholder="Email" name="user_email" required />
            <input type="text" id="company" placeholder="Company Name" name="user_company" required />
            <textarea placeholder="Message" name="message" required></textarea>
            <input type="submit" value="Send" />
          </form>
        </div>
        <div className="contact-details">
          <h2>Contact Me</h2>
          <p><strong>Email:</strong> <a href="mailto:ashishbommali@gmail.com" target="_blank" rel="noopener noreferrer"><FaEnvelope /></a></p>
          <p><strong>LinkedIn:</strong> <a href="https://www.linkedin.com/in/ashish-bommali-5a8686170" target="_blank" rel="noopener noreferrer"><FaLinkedin /></a></p>
          <p><strong>GitHub:</strong> <a href="https://github.com/ashishbommali" target="_blank" rel="noopener noreferrer"><FaGithub /></a></p>
          <p><strong>Phone <FaPhone />:</strong> +1 6822727437</p>
          <p><strong>Address <FaLandmark />:</strong> Apt #280 513, 513 summit ave, Meadow run, Arlington, Tx - 76013</p>
        </div>
      </div>
    </div>
  );
};

export default Contact;
