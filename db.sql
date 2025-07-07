USE `project-java-web`;

INSERT INTO student values
                        (NULL, NULL, '2025-01-01', 'admin@gmail.com', 'Admin', '13579', '0135792468', 1, 0),
                        (NULL, NULL, '2025-02-02', 'studenttest@gmail.com', 'Học Viên Thử Nghiệm', '2468', '0246813579', 0, 0);

INSERT INTO course values
                       (NULL, NULL, 60, NULL, 'Giảng viên test 1', 'Khóa học test 1'),
                       (NULL, NULL, 90, NULL, 'Giảng viên test 2', 'Khóa học test 2');

INSERT INTO course (create_at, duration, image, instructor, name) VALUES
                                                                      ('2025-07-01 10:00:00', 24, 'https://upload.wikimedia.org/wikipedia/commons/7/74/Java_programming_language_logo.svg', 'Nguyen Van A', 'Lập trình Java cơ bản'),
                                                                      ('2025-07-02 09:30:00', 18, 'https://www.python.org/static/community_logos/python-logo-master-v3-TM.png', 'Tran Thi B', 'Python cho người mới bắt đầu'),
                                                                      ('2025-07-03 14:15:00', 30, 'https://upload.wikimedia.org/wikipedia/commons/a/a7/React-icon.svg', 'Le Van C', 'Phát triển Web với ReactJS'),
                                                                      ('2025-07-04 08:00:00', 20, 'https://upload.wikimedia.org/wikipedia/commons/8/87/Sql_data_base_with_logo.png', 'Pham Thi D', 'SQL và cơ sở dữ liệu'),
                                                                      ('2025-07-05 11:45:00', 15, 'https://git-scm.com/images/logos/downloads/Git-Icon-1788C.png', 'Nguyen Van E', 'Git & GitHub cơ bản'),
                                                                      ('2025-07-06 16:00:00', 36, 'https://spring.io/images/spring-logo-2019-627d69a7784d2490b8ec5b7e4f3edb7c.svg', 'Do Thi F', 'Spring Boot nâng cao'),
                                                                      ('2025-07-07 13:20:00', 12, 'https://cdn-icons-png.flaticon.com/512/919/919826.png', 'Hoang Van G', 'HTML & CSS từ A đến Z'),
                                                                      ('2025-07-08 10:40:00', 28, 'https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png', 'Tran Thi H', 'JavaScript nâng cao'),
                                                                      ('2025-07-09 17:10:00', 40, 'https://upload.wikimedia.org/wikipedia/commons/0/0a/Python.svg', 'Nguyen Van I', 'Phân tích dữ liệu với Python'),
                                                                      ('2025-07-10 09:00:00', 22, 'https://upload.wikimedia.org/wikipedia/commons/4/49/UX_UI_Design_icon.svg', 'Le Thi J', 'Thiết kế UI/UX cơ bản'),
                                                                      ('2025-07-11 15:00:00', 18, 'https://cdn-icons-png.flaticon.com/512/888/888878.png', 'Pham Van K', 'Kỹ thuật SEO Website'),
                                                                      ('2025-07-12 08:30:00', 25, 'https://upload.wikimedia.org/wikipedia/commons/7/74/Kotlin_Icon.png', 'Bui Thi L', 'Lập trình Android với Kotlin'),
                                                                      ('2025-07-13 11:20:00', 16, 'https://upload.wikimedia.org/wikipedia/commons/0/05/Scikit_learn_logo_small.svg', 'Nguyen Van M', 'Machine Learning cơ bản'),
                                                                      ('2025-07-14 14:50:00', 20, 'https://upload.wikimedia.org/wikipedia/commons/1/18/ISO_C%2B%2B_Logo.svg', 'Tran Van N', 'Lập trình C++ nâng cao'),
                                                                      ('2025-07-15 13:00:00', 30, 'https://nodejs.org/static/images/logo.svg', 'Do Thi O', 'NodeJS từ cơ bản đến nâng cao'),
                                                                      ('2025-07-16 09:30:00', 21, 'https://angular.io/assets/images/logos/angular/angular.svg', 'Le Van P', 'Angular framework thực chiến'),
                                                                      ('2025-07-17 16:10:00', 27, 'https://upload.wikimedia.org/wikipedia/commons/0/07/Systems_analysis.svg', 'Pham Thi Q', 'Phân tích hệ thống thông tin'),
                                                                      ('2025-07-18 10:00:00', 14, 'https://www.r-project.org/Rlogo.png', 'Nguyen Van R', 'Lập trình R cho thống kê'),
                                                                      ('2025-07-19 15:30:00', 19, 'https://upload.wikimedia.org/wikipedia/commons/1/10/Game_controller_Icon.svg', 'Tran Thi S', 'Thiết kế Game cơ bản'),
                                                                      ('2025-07-20 08:45:00', 33, 'https://upload.wikimedia.org/wikipedia/commons/5/58/Scrum_logo.svg', 'Bui Van T', 'Quản lý dự án Agile Scrum');


select * from student;
INSERT INTO student (id, create_at, dob, email, name, password, phone, role, sex, status) VALUES

                                                                                             (NULL, '2025-07-01 08:00:00', '2000-05-15', 'a.nguyen@example.com', 'Nguyen Van A', 'password123', '0909123456', true, true, true);


