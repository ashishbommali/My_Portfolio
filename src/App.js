import React from 'react';
import './App.css';
import './theme.css'
import Home from './pages/Home';
import About from './pages/About';
import Projects from './pages/Projects';
import Experience from './pages/Experience';
import Contact from './pages/Contact';
import Header from './components/Header';
import Footer from './components/Footer';
import Skills from './pages/Skills';

function App() {
  return (
    <div className="App">
      <Header />
      <Home id="home" />
      <Experience id="experience" />
      <About id="about" />
      <Skills id="skills" />
      <Projects id="projects" />
      <Contact id="contact" />
      <Footer />
    </div>
  );
}

export default App;
