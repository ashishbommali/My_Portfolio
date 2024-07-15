import React, { useState } from 'react';
import Slider from 'react-slick';
import '../styles/pages/experience.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import experienceData from '../data/experienceData';
import ReadMore from "../modals/ReadMore";

const NextArrow = (props) => {
  const { onClick } = props;
  return (
    <div className="custom-arrow next" onClick={onClick}>
      &#9654;
    </div>
  );
};

const PrevArrow = (props) => {
  const { onClick } = props;
  return (
    <div className="custom-arrow prev" onClick={onClick}>
      &#9664;
    </div>
  );
};

const settings = {
  dots: false,
  infinite: true,
  speed: 500,
  slidesToShow: 1,
  slidesToScroll: 1,
  nextArrow: <NextArrow />,
  prevArrow: <PrevArrow />,
  responsive: [
    {
      breakpoint: 1024,
      settings: {
        slidesToShow: 1,
        slidesToScroll: 1,
        infinite: true,
        dots: false
      }
    },
    {
      breakpoint: 600,
      settings: {
        slidesToShow: 1,
        slidesToScroll: 1
      }
    }
  ]
};

const Experience = () => {
  const [modal, setModal] = useState(false);
  const [selectedExperience, setSelectedExperience] = useState(null);

  const toggleModal = (experience) => {
    setSelectedExperience(experience);
    setModal(!modal);
  };

  return (
    <div id='experience'>
      <h1>Work Experience</h1>
      <Slider {...settings} className="experience-container">
        {experienceData.map((experience, index) => (
          <div key={index} className="experience-card">
            <h1>{experience.company} · {experience.employementType}</h1>
            <h2>{experience.title}</h2>
            <p>{experience.date}</p>
            <p>{experience.location}</p>

            <button className='read-more-button' onClick={() => toggleModal(experience)}>Read More</button>
          </div>
        ))}
      </Slider>
      {modal && <ReadMore experience={selectedExperience} toggleModal={toggleModal} />}
    </div>
  );
};

export default Experience;
