import React, { useState, useEffect } from 'react';
import { Link } from 'react-scroll';
import '../styles/components/header.css';

const Header = () => {
  const [darkMode, setDarkMode] = useState(false);

  useEffect(() => {
    document.body.classList.toggle('dark-mode', darkMode);
  }, [darkMode]);

  return (
    <header>
      <h1>Ashish</h1>
      <nav>
        <ul>
          <li><Link to="home" offset={-100} spy={true} smooth={true} duration={200}>Home</Link></li>
          <li><Link to="experience" offset={-100} spy={true} smooth={true} duration={200}>Experience</Link></li>
          <li><Link to="about" offset={-100} spy={true} smooth={true} duration={200}>About</Link></li>
          <li><Link to="skills" offset={-100} spy={true} smooth={true} duration={200}>Skills</Link></li>
          <li><Link to="projects" offset={-100} spy={true} smooth={true} duration={200}>Projects</Link></li>
          <li><Link to="contact" offset={-100} spy={true} smooth={true} duration={200}>Contact</Link></li>
        </ul>
      </nav>
      <button className='theme-button' onClick={() => setDarkMode(!darkMode)}>
        Switch to {darkMode ? 'Light' : 'Dark'} Mode
      </button>
    </header>
  );
};

export default Header;
