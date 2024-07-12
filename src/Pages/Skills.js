import React from 'react';
import Slider from 'react-slick';
import '../Styles/Pages/skills.css';
import { FaCheck } from 'react-icons/fa';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const Skills = () => {
    const data = [
        {
            skillName: 'Frontend Development',
            skillSet: ['HTML', 'CSS', 'JavaScript', 'React', 'Bootstrap', 'Angular', 'TypeScript', 'Tailwind CSS']
        },
        {
            skillName: 'Backend Development',
            skillSet: ['Node.js', 'Express', 'Python', 'Django', 'Flask', 'Java', 'Spring Boot']
        },
        {
            skillName: 'Databases',
            skillSet: ['SQL', 'MySQL', 'SQLite', 'Firebase']
        },
        {
            skillName: 'Build Tools',
            skillSet: ['NPM', 'Yarn', 'Docker']
        },
        {
            skillName: 'Machine Learning',
            skillSet: ['TensorFlow', 'Keras', 'Scikit-learn', 'PyTorch', 'Pandas', 'NumPy', 'Matplotlib']
        },
        {
            skillName: 'AI',
            skillSet: ['Natural Language Processing', 'Computer Vision', 'Reinforcement Learning', 'Deep Learning', 'OpenCV', 'NLTK']
        },
        {
            skillName: 'Python',
            skillSet: ['Flask', 'Django', 'Pandas', 'NumPy', 'Matplotlib', 'SciPy', 'Scikit-learn', 'PyTorch', 'TensorFlow', 'FastAPI']
        },
        {
            skillName: 'Java',
            skillSet: ['Spring', 'Spring Boot', 'Hibernate', 'Apache Maven', 'Gradle', 'JUnit']
        },
        {
            skillName: 'Cloud',
            skillSet: ['AWS', 'Google Cloud Platform', 'Firebase', 'Docker', 'Kubernetes', 'Terraform']
        }
    ];

    const NextArrow = (props) => {
        const { onClick } = props;
        return (
            <div className="custom-arrow next" onClick={onClick}>
                &#9654; {/* You can replace this with an icon */}
            </div>
        );
    };

    const PrevArrow = (props) => {
        const { onClick } = props;
        return (
            <div className="custom-arrow prev" onClick={onClick}>
                &#9664; {/* You can replace this with an icon */}
            </div>
        );
    };

    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 1,
        nextArrow: <NextArrow />,
        prevArrow: <PrevArrow />,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: true
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

    return (
        <div id="skills">
            <h1>Skills Page</h1>
            <Slider {...settings} className="skills-container">
                {data.map((category, index) => (
                    <div key={index} className="card">
                        <h2>{category.skillName}</h2>
                        <ul className="skills-list">
                            {category.skillSet.map(skill => (
                                <li key={skill}><FaCheck /> {skill}</li>
                            ))}
                        </ul>
                    </div>
                ))}
            </Slider>
        </div>
    );
};

export default Skills;
