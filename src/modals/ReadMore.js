import React, { useEffect } from "react";
import '../styles/modals/readMore.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

const ReadMore = ({ experience, toggleModal }) => {
    
    // Add 'active-modal' class to body when component mounts
    useEffect(() => {
        document.body.classList.add('active-modal');

        // Remove 'active-modal' class from body when component unmounts
        return () => {
            document.body.classList.remove('active-modal');
        };
    }, []);
    
    if (!experience) return null;

    return (
        <div className="modal">
            <div className="overlay" onClick={toggleModal}></div>
            <div className="modal-content">
                <button className="close-modal" onClick={toggleModal}>
                    <FontAwesomeIcon icon={faTimes} />
                </button>
                <div className="modal-body">
                    <h1>{experience.company} · {experience.employementType}</h1>
                    <h2>{experience.title}</h2>
                    <p>{experience.date}</p>
                    <p>{experience.location}</p>
                    <ul className="experience-description">
                        {experience.description.map((line, idx) => (
                            <li key={idx}>{line}</li>
                        ))}
                    </ul>
                    <ul className="experience-skills">
                        {experience.skills.map((skill, idx) => (
                            <li key={idx}>{skill}</li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default ReadMore;
