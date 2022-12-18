CREATE TABLE Admin (
   id INT AUTO_INCREMENT,
   Email varchar(100) NOT NULL,
   password varchar(20) NOT NULL,
   roleId INT,
   PRIMARY KEY (id, roleId),
   CONSTRAINT FK_ADMIN_ROLE FOREIGN KEY (roleId)
       REFERENCES UserRole(id)
);

CREATE TABLE UserRole(
     id INT AUTO_INCREMENT PRIMARY KEY,
     roleName varchar(50) NOT NULL
);

CREATE TABLE Student(
  id INT AUTO_INCREMENT,
  collegeId VARCHAR(10) NOT NULL UNIQUE,
  name varchar(50) NOT NULL,
  Email varchar(100) NOT NULL UNIQUE,
  password varchar(20) NOT NULL,
  roleId INT,
  primary key(id,roleId),
  CONSTRAINT FK_STUDENT_ROLE FOREIGN KEY (roleId)
                    REFERENCES userrole(id)
);

CREATE TABLE Lecturer(
     id INT AUTO_INCREMENT,
     name varchar(50) NOT NULL,
     Email varchar(100) NOT NULL UNIQUE,
     password varchar(20) NOT NULL,
     roleId INT,
     primary key(id,roleId),
     CONSTRAINT FK_LECTURER_ROLE FOREIGN KEY (roleId)
         REFERENCES userrole(id)
);

CREATE TABLE Course(
  courseId varchar(5),
  subjectId varchar(4) not null,
  courseNumber INT,
  title varchar(50) not null,
  numOfCredits INT,
  lecturerId INT,
  PRIMARY KEY (courseId),
  CONSTRAINT FK_COURSE_LECTURER FOREIGN KEY (lecturerId)
                   REFERENCES Lecturer(id)
);

CREATE TABLE Enrollment(
    collegeId VARCHAR(10) NOT NULL UNIQUE,
    courseId varchar(5),
    dateRegistered date,
    grade CHAR(1),
    PRIMARY KEY (courseId,collegeId),
    CONSTRAINT FK_COURSE_ENROLLMENT FOREIGN KEY (collegeId)
        REFERENCES Student(id),
    CONSTRAINT FK_STUDENT_ENROLLMENT FOREIGN KEY (courseId)
        REFERENCES Course(courseId)
);

-- Exams
CREATE TABLE Exam(
    examId INT AUTO_INCREMENT,
    lecturerEmail VARCHAR(50) NOT NULL ,
    duration TIME,
    primary key (examId,lecturerEmail)
);

CREATE TABLE Question(
    questionId int,
    question varchar(4000),
    questionType bit default 0, -- 0 for single choice 1 for multi-choice
    choiceA varchar(1000),
    choiceB varchar(1000),
    choiceC varchar(1000),
    choiceD varchar(1000)
);

CREATE TABLE QuestionAnswers(
    answerId int,
    questionId int,
    answer varchar(5),
    primary key (answerId),
    CONSTRAINT FK_QUESTION FOREIGN KEY (questionId)
                            REFERENCES Question(questionId)
);

-- table that have student exam grade
CREATE TABLE ExamLog(
    id int,
    studentName varchar(50),
    collegeId VARCHAR(10),
    examName varchar(50),
    score double default null,
    summited bit default 0,
    CONSTRAINT PK_EXAM_LOG primary key (studentName,examName)
);


INSERT INTO Admin(EMAIL, PASSWORD, ROLEID) Values("admin","root",1);

INSERT INTO Student(name,collegeId,Email,password,roleId)
VALUES ("Ahmed Khaled","1111","A.K@fci.com","1234",2);
INSERT INTO Student(name,collegeId,Email,password,roleId)
VALUES ("Yara Mohammed","2222","Y.M@fci.com","12345",2);
INSERT INTO Student(name,collegeId,Email,password,roleId)
VALUES ("Mostafa Fathy","3333","M.F@fci.com","123456",2);

INSERT INTO Lecturer (name, Email, password, roleId) values ("may amr","may.amr@fci.com","may123",3);


INSERT INTO UserRole (roleName) VALUES ("admin");
INSERT INTO UserRole (roleName) VALUES ("student");
INSERT INTO UserRole (roleName) VALUES ("lecturer");

# SELECT * FROM UserRole;
# SELECT * FROM Users;
# SELECT * FROM Users WHERE userName = "admin" and password = "root@123";


drop table UserRole;
drop table admin;
drop table student;
drop table lecturer;