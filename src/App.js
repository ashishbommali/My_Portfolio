import React from 'react';
import './App.css';
import Home from './Pages/Home';
import About from './Pages/About';
import Projects from './Pages/Projects';
import Experience from './Pages/Experience';
import Contact from './Pages/Contact';
import Header from './Components/Header';
import Footer from './Components/Footer';
import Skills from './Pages/Skills';

function App() {
  return (
    <div className="App">
      <Header />
      <Home id="home" />
      <About id="about" />
      <Projects id="projects" />
      <Experience id="experience" />
      <Skills id="skills"/>
      <Contact id="contact" />
      <Footer />
    </div>
  );
}

export default App;
