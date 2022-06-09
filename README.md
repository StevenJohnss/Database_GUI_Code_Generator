## Usage
This is a java desktop application where you can Dynamically generate a GUI for your database tables that handles Inserting, Updating, Deleting and Finding data.

## Run Project

### Requierments:
<ol>
<li> Must have jdk version 8 (https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html)</li>
<li> Must check that javac -version = 1.8</li>
<li> Must have Jdeveloper Version 12.2.1.2.0 to run the application (https://www.oracle.com/tools/downloads/jdev-v12212-downloads.html)</li>
</ol>

### Run App:
<ol>
<li> open the app through the file `test.jws` from Jdeveloper </li>
<li> Then Run the `MainFrame.java` file in `./CodeGenerator/src/pk1` </li>
<li> Enter the Database username and password and make sure that user has all the needed privileges to edit data and view the DB tables </li>
  <img src="https://user-images.githubusercontent.com/62972966/172756509-b6df9e2c-c7d8-4488-91d0-f068349deb8f.png"></img>
<li> Then Click Generate</li>
<li> you will encounter many popups that tells if the tables were found and the cloumns and such just click ok on all of them and continue</li>
<img src="https://user-images.githubusercontent.com/62972966/172757195-fe0240c7-b2bf-4d47-abb1-a25234b16e74.png"></img>
<li> Then the `.bat` file will run the compiling commands for the generated java files and if every this is running smothely you will see this image</li>
<img src="https://user-images.githubusercontent.com/62972966/172757652-0dba5a1f-2a26-4feb-aa6e-be909e1362d2.png"></img>
<li> Click Ok on the popup then generated gui containg the list of DB tables will appear </li>
<img src="https://user-images.githubusercontent.com/62972966/172758034-2fa0b224-161d-4512-a2ee-2b58f6af4e76.png"></img>
</ol>

### Using:
<ol>
  <li> The table name will be visiable as Buttons with the same name </li>
  <li> Click on any button to go to the next Page where you can do all the basic CRUD operations </li>
  <img src="https://user-images.githubusercontent.com/62972966/172758599-a1c974db-c95a-45e1-93d1-a2ea2c57584d.png"></img>
  <li> To Find a row or data searrch by ID </li>
<img src="https://user-images.githubusercontent.com/62972966/172758997-df43535d-c63f-4de5-84e2-0f71a1592fd4.png"></img>
<img src="https://user-images.githubusercontent.com/62972966/172759058-01cdc805-d431-40cd-92d8-9f88c94a2a70.png"></img>
<img src="https://user-images.githubusercontent.com/62972966/172759095-0dd6c446-b760-4414-9523-66e5b8a23109.png"></img>
<li> Deleting a Row is also by id </li>
<li> Updating a row is also by id </li>
<li>Adding a row according to which cloumns are Must not be empty or not according to the database </li>


</ol>
  

