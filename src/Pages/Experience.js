import React from 'react';
import Slider from 'react-slick';
import '../Styles/Pages/experience.css';

const experienceData = [
  {
    title: "Full-stack Developer",
    company: "Prowess Enterprise",
    employementType: "Internship",
    date: "Mar 2024 - Jun 2024 · 4 mos",
    location: "Frisco, Texas, United States · Remote",
    description: "Building a supply chain management web application on blockchain network with front-end React and back-end Spring Boot frameworks.",
    skills: [
      "React.js",
      "HTML5",
      "Cascading Style Sheets (CSS)",
      "JavaScript",
      "Spring Boot",
      "Blockchain",
      "Network Administration",
      "Hyperledger Fabric"
    ]
  },
  {
    title: "Software Engineer (Machine Learning)",
    company: "Prowess Enterprise",
    employementType: "Internship",
    date: "Nov 2022 - Dec 2023 · 1 yr 2 mos",
    location: "Visakhapatnam, Andhra Pradesh, India · Remote",
    description: `• Successfully deployed machine learning models into production environments using Flask and FastAPI, ensuring seamless integration with existing systems.
    • Created and optimized ML models for tasks such as image classification, natural language processing using libraries like NumPy, Pandas, and Scikit-Learn.
    • Integrated models with APIs and databases for real-time data processing and decision-making.
    • Conducted exploratory data analysis (EDA) to uncover insights and patterns in datasets, influencing feature selection and model design.
    • Optimized machine learning algorithms for scalability, enabling efficient processing of large datasets and high-volume requests.
    • Conducted regular code reviews, ensuring high code quality, and providing constructive feedback to team members.
    • Proficient in using deployment tools such as Docker and Kubernetes to containerize and scale machine learning applications.
    • Utilized continuous integration and continuous deployment (CI/CD) pipelines for automated testing and deployment.
    • Maintained comprehensive documentation for machine learning models, algorithms, and deployment processes.
    • Enforced coding standards and conducted code refactoring to improve overall code quality and maintainability.
    • Stayed abreast of the latest advancements in Python, machine learning, and artificial intelligence through continuous learning and participation in industry conferences and workshops.`,
    skills: [
      "Python (Programming Language)",
      "Pandas",
      "NumPy",
      "Natural Language Processing (NLP)",
      "NLTK",
      "Flask",
      "FastAPI",
      "Scikit-Learn",
      "OpenCV",
      "Tensorflow"
    ]
  },
  {
    title: "Associate Software Engineer",
    company: "Alacriti Infosystem Pvt ltd",
    employementType: "Full-time",
    date: "Apr 2022 - Nov 2022 · 8 mos",
    location: "Hyderabad, Telangana, India · On-site",
    description: `• Served as a full-stack web application developer, where I played a pivotal role in crafting comprehensive web solutions.
    • My responsibilities encompassed the creation of websites designed to deliver efficient and resilient functionalities.
    • Developed Microservice to provide Restful API utilizing Spring Boot with various frameworks such as Hibernate and JPA.
    • Developed Web Based Application using Java and Implemented application-level persistence using JPA.
    • Working with different spring modules like Spring IOC (Dependency Injection), Spring MVC, Spring AOP, Spring Data (JDBC), Spring Boot, Securities.`,
    skills: [
      "Java",
      "SQL",
      "Object-Oriented Programming (OOP)",
      "Cascading Style Sheets (CSS)",
      "Git",
      "Spring Boot",
      "Angular",
      "HTML5",
      "JavaScript",
      "Thymeleaf",
      "Maven",
      "JSON",
      "JUnit",
      "IntelliJ",
      "Eclipse",
      "React.js"
    ]
  },
  {
    title: "Associate Software Engineer",
    company: "DXC Technology",
    employementType: "Full-time",
    date: "Jan 2021 - Apr 2022 · 1 yr 4 mos",
    location: "Bengaluru, Karnataka, India",
    description: `• Served as a Software Engineer (Java Full Stack Developer) manager and was responsible for application enhancement/maintenance in Java 8 using Spring MVC framework and JDBC at the database layer.
    • Backend solutions designed with Java 8/REST API architecture.
    • Prepare Java development structure for Eclipse and Maven.
    • Worked on various Spring modules (Spring MVC, DAO, ORM, and IOC) along with Hibernate as the back-end ORM tool for the persistence layer and mapping of POJOs.
    • Collaborated with development and operations teams to optimize infrastructure, enhance system reliability, and foster a DevOps culture.
    • Development of the interactive UIs for the front-end users using front-end technologies like HTML5, CSS3, React.JS, XML, and JavaScript.
    • Implemented AWS solutions using DynamoDB, EBS, EC2, S3, RDS, SNS, Cloud Watch, Elastic Load Balancer, and Auto scaling groups. Used AWS SDK to connect with Amazon SQS for bulk email processing.`,
    skills: [
      "Cloud Infrastructure",
      "Cloud Services",
      "Google Cloud Platform (GCP)",
      "Infrastructure as a Service (IaaS)",
      "Maven",
      "Unix",
      "Linux",
      "Amazon Web Services (AWS)",
      "Amazon EC2",
      "Amazon S3",
      "JSON",
      "XML",
      "REST APIs",
      "JavaScript",
      "Spring"
    ]
  },
  {
    title: "Full Stack Developer",
    company: "Prowess Enterprise",
    employementType: "Internship",
    date: "Dec 2019 - Dec 2020 · 1 yr 1 mo",
    location: "Visakhapatnam, Andhra Pradesh, India · On-site",
    description: `• Responsible for Coding using Java Servlets, and XML.
    • Designed and styled UI screens using HTML, JavaScript, and CSS.
    • Developed Web Services for interacting with Backend. Designed Solutions using Hibernate and JPA to interface with the relational database.
    • Developed the application using spring framework that leverages model view layer architecture, as well as Configured Dependency Injection.
    • Developed and designed Software Engineering Solutions using Agile Methodology.
    • Involved in the design, development, and support phases of SDLC.`,
    skills: [
      "Java",
      "HTML",
      "SQL",
      "Object-Oriented Programming (OOP)",
      "Git",
      "Spring Boot",
      "Windows",
      "Web Services"
    ]
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
  return (
    <div id='experience' >
      <h1>Work Experience</h1>
      <Slider  {...settings} className="experience-container" >
        {experienceData.map((experience, index) => (
          <div key={index} className="experience-card">
            <h1>{experience.company} · {experience.employementType}</h1>
            <h2>{experience.title}</h2>
            <p>{experience.date}</p>
            <p>{experience.location}</p>
            <p>{experience.description}</p>
            <ul className="experience-skills">
              {experience.skills.map((skill, idx) => (
                <li key={idx}>{skill}</li>
              ))}
            </ul>
          </div>
        ))}
      </Slider>
    </div>
  );
};

export default Experience;
