# student_management_system
this project use spring boot 
to run
1. Open this project in IntelliJ (or any java IDE)
2. Edit file /config/hibernate.cfg.xml (line 8 and line 9)
3. Sync maven project, load all the dependency of the maven project
4. Run SampleController.main
5. Done




#Rest API description
1. all the api will return you JSON
2. /student/all : get all students
3. /student : param id= (int), will give you a specified student with id
4. /student/find: param name=(string) , will give a list of students who had name match with your param
5. /student/add: with this api you will send a json to server(datatye ="application/json"), contain all information of a student you want to add, list of field have in file Student.java
6. /student/update: similar with api 4

bug: I still dont understand why hibernate not create id field in db with AI (auto increment) property, so at the begining, api 5 will not work
you must fix this by adding AI property for id field in DB manually (by phpmyadmin or mySql workbench) then it work perfectly
