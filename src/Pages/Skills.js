import React from 'react';
import Slider from 'react-slick';
import '../styles/pages/skills.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import skillsData from '../data/skillsData';

const Skills = () => {
  
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 1,
      
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
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

    const VerifiedIcon = () => (
        <svg className="verified-icon" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" version="1.1" width="15" height="15" viewBox="0 0 256 256" xmlSpace="preserve">
            <defs>
            </defs>
            <g style={{ stroke: 'none', strokeWidth: 0, strokeDasharray: 'none', strokeLinecap: 'butt', strokeLinejoin: 'miter', strokeMiterlimit: 10, fill: 'none', fillRule: 'nonzero', opacity: 1 }} transform="translate(1.4065934065934016 1.4065934065934016) scale(2.81 2.81)" >
                <path d="M 80.122 30.345 L 80.122 30.345 L 80.122 30.345 c 5.264 -13.053 -7.87 -25.96 -20.829 -20.469 C 53.786 -3.076 35.372 -2.916 30.091 10.131 v 0 h 0 C 17.038 4.866 4.131 18.001 9.623 30.96 C -3.33 36.467 -3.169 54.881 9.878 60.162 h 0 v 0 c -5.264 13.053 7.87 25.96 20.829 20.469 c 5.507 12.952 23.921 12.792 29.202 -0.255 v 0 h 0 c 13.053 5.264 25.96 -7.87 20.469 -20.829 C 93.33 54.039 93.169 35.625 80.122 30.345 z M 39.662 63.79 L 23.357 47.756 l 5.609 -5.704 L 39.3 52.214 l 20.305 -22.631 l 5.955 5.343 L 39.662 63.79 z" style={{ stroke: 'none', strokeWidth: 1, strokeDasharray: 'none', strokeLinecap: 'butt', strokeLinejoin: 'miter', strokeMiterlimit: 10, fill: 'rgb(0,0,0)', fillRule: 'nonzero', opacity: 1 }} transform="matrix(1 0 0 1 0 0)" strokeLinecap="round" />
            </g>
        </svg>
    );

    return (
        <div id="skills">
            <h1>My Skillset</h1>
            <Slider {...settings} className="skills-container">
                {skillsData.map((category, index) => (
                    <div key={index} className="card">
                        <h2>{category.skillName}</h2>
                        <ul className="skills-list">
                            {category.skillSet.map(skill => (
                                <li key={skill}><VerifiedIcon  /> {skill}</li>
                            ))}
                        </ul>
                    </div>
                ))}
            </Slider>
        </div>
    );
};

export default Skills;
