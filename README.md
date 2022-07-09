# Distributed Database Management System Project

For this project, I created a lightweight distributed database management system that integrates metadata management, data structure design, data storing and retrieval, database structures, analytics, and security, as well as a custom database structure for in-memory operations that incorporates arrays, hash maps, linked lists, and other data structures, as well as a custom file design for persistent storage.

Data structure concepts were used to build the databases, their administration system, and their security. Furthermore, the project includes an analytics engine capable of doing basic data analysis. Numerous requests from multiple users can be handled by the database. The database management system layer provides a command-line interface and manages multiple requests.

A basic distributed database system has been built using Java. The database may be accessed using the command-line interface. The database processes queries from two users, with SHA-256 hashing used for user authentication. Some of the fundamental SQL commands it handles include SELECT, CREATE, INSERT, and UPDATE. Tables, table metadata, and logs have all been stored as text files. Transaction processing, log management, analytics, ERD creation from plain text files, and SQL dump exports are all part of the project. For further information, please check the list below.

• Database Design

Our in-memory data structures include ArrayList and HashMap for storing and converting metadata for writing, analyzing, and logging the data.

• SQL queries and commands

Several methods have been developed to confirm and parse some basic SQL queries entered by users. First, the query is passed to validator methods, which perform checks such as whether the query contains the correct number of terms and spaces. A successful query will be passed to the specific query processor method so that an operation such as SELECT, INSERT, UPDATE, DELETE can be performed.

• Transaction Processing

An algorithm has been created to parse the transaction's query. When you select the option for write queries, the transaction is executed. For launching a transaction, I have used "start" instead of the basic format start transaction. This commit also uses "commit" for the word "transaction end". When the transaction is executed the record of it goes in the queryLog.txt file about the status of its processing.

• Log Management

The parameters for all three log files may be found in the Logs folder (i.e., general, event and query). For event logs, I have kept all the crash reports details and concurrent transaction crashes. These logs are independent of the other logs in the project. For general logs, I have captured the query execution time, state of the database, the number of tables existing in that database along with the number of records in each table at the time of query submission. These are specific to the database when a user uses a database it will fetch the number of tables and number of records in all these tables under the used database along with the users' details. For query logs, I have captured the user queries and the timestamp of query submission along with the detail of the database they are working on and username. The query logs will not only have the query details of the submitted queries but will also have the transaction details (if the tables are locked or unlocked) at that particular time.

• Analytics

An Analytics report is created using the DataAnalytics.java class file. It keeps track of how many operations were performed by each user on databases.

• SQL Dump Generator Implementation

It's used to create a SQL dump in the form of a .sql file for the database folder, which includes all of the database's tables. It will only generate the .sql file if the database name provided by the user is a legitimate database name and has a directory in the project; otherwise, an error will be displayed on the console.

• ERD (Entity Relationship Diagram)

To create an Entity Relationship Diagram for a specific database, we are using the class ERDiagram.java. The program generates an entity-relationship diagram for the database selected by the user and prints it to an ERDdemo.txt file. ERD.txt contains tables, columns, primary key attributes such as 'pk' and foreign key attributes such as 'fk', as well as relationships between tables.

• User Interface and Login Security

This module in the application deals with the user interface to onboard and perform operations on the system. This module includes Login, Registration, and Reset Password Options. It asks for options like username, password, and security answer.
