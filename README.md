# ood_project

## running environment:

**IDE:** Intellij  IDEA, Android Studio

**Database:** Mysql

**Backend framework:** Spring boot

**Frontend framework:** layui

## operation guide:

1. download the project from github

    2. database: 

    mysql database is required for running the project. use script.sql in "/backend" to generate the database tables.

    3. open Intellij IDEA to open the project, wait the gradle do the rest for you

    4. connect your IDEA with your mysql database

    5. modify the file: application.properties in "/backend/src/main/resources". update the username and password with your mysql account.

    6. run the project (main entry is in "\backend\src\main\java\com\gwu\backend")

    7. open the browser and type the address: "localhost:8080/"

    8. press F12 to open the developer tools of the browser and change the web view into mobile view
