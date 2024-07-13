import React from 'react';
import { Link } from 'react-scroll';
import '../Styles/Components/header.css';

const Header = () => {
  return (
    <header>
      <h1>Ashish</h1>
      <nav>
        <ul>
          <li><Link to="home" offset={-100} spy={true} smooth={true} duration={500}>Home</Link></li>
          <li><Link to="about" offset={-100} spy={true} smooth={true} duration={500}>About</Link></li>
          <li><Link to="experience" offset={-100} spy={true} smooth={true} duration={500}>Experience</Link></li>
          <li><Link to="skills" offset={-100} spy={true} smooth={true} duration={500}>Skills</Link></li>
          <li><Link to="projects" offset={-100} spy={true} smooth={true} duration={500}>Projects</Link></li>
          <li><Link to="contact" offset={-100} spy={true} smooth={true} duration={500}>Contact</Link></li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
