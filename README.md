# Steps to configure Bookworm on local machine :

1. Clone the this git repository using the steps mentioned on the link below:
`https://help.github.com/articles/cloning-a-repository/.`
2. Once the repository has been cloned, import it as a `Maven Project` in the your IDE.
### Below are the steps to import the project in Eclipse:

 * Configure database:
 1. Run the `bookworm.sql` script.

 * Configure project:
 1. Open `Eclipse`.
 
 2. Select `File > Import > option`.
 
 3. Select Maven Projects Option. Click on Next Button. 
 
 4. Select Project location, where you have cloned this git repository.
 
 5. Click `Finish` Button.
 
 6. Now, you can see the maven project in eclipse.
 
 7. Right click on project to open context menu.
 
 8. Select `Run as` option.
 
 9. Then select `Maven Build`option. Maven will start building the project.
  
 10. An `Edit Configuration` window will open. In this, set `Goals` as `tomcat7:run`. Click on `Apply` and then `Run`.
 
 11. Once the server loads, hit the following URL : `http://localhost:8080/bookstoremanagement/home` in your browser.
 (assuming your server port is 8080, if not change it accordingly.)

And you are all set to `buy`, `search` and `sell` books!
 
 
 
