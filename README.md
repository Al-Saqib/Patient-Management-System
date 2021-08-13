# Project for CPSC 210 Summer Term-2

## A patient management application for a diagnostic center

:arrow_forward: This is my first desktop application written in java, developed for CPSC-210 class at UBC.  

:arrow_forward: This application will be used for maintaining a patient database system, where the user will be able to 
add a new patient and view, update and delete patient information. 

:arrow_forward: The application will be used by diagnostic center management team. 

:arrow_forward: This project is of interest to me because my father is a doctor and I want to help streamline the patient 
management system in his diagnostic center so that he can focus more time on interacting with and helping his patients instead of 
being bogged down by trying to find patient information and history. 





## User Stories 

The following are the *user stories* for the application:

**User stories for phase 1**: 

- As a *user*, I want to be able to **add** a new patient to the patient database

- As a *user*, I want to be able **view** the patient records in the patient database

- As a *user*, I want to be able to **edit** the names of the patients in the database

- As a *user*, I want to be able to **remove** a patient from the patient database


**User stories for phase 2**: 

- As a *user*, I want be able to **save** the patient records to file. 

- As a *user*, I want to be able to **load** the patient records from the file. 

- As a *user*, I want to be **prompted to save** the patient records to file when I quit.  

- As a *user*, I want to have the **saved patient records already loaded** when I start the application. 


##Phase 4 Task 2:

- I have chosen to implement the map interface in my project.

- I use a HashMap<> to implement the map interface.

- PatientRecords class contains the primary implementation of this interface. 


##Phase 4 Task 3:

- I could have extracted 'submit' operations under one method and then called this method 
inside the OperationsPanel constructor, increasing cohesion and readability. 

- I could have also extracted the dimensional constrains inside Operations panel constructor 
under one method and called this method from inside the constructor, increasing cohesion and readability. 

- I could have moved operations related to resizing window under a method and then called this method inside 
the patient database graphical user interface constructor. This would have increased cohesion and readability. 

- I could have moved the methods related to user operations in patient database graphical user interface class to 
operations panel class and then called them inside patient database gui as required. This would have increased cohesion. 

- I could have extracted a method from 'command add/edit' methods that checks for user input validity inside patient database and 
patient database gui classes. This would have reduced coupling. 