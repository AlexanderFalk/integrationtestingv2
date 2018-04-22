## Integration Testing Guidelines

I will be using the Bottom-Up approach, where the bottom level units are tested first and upper-level units step by step after that. This means, that I will be testing the database at first (the lower level) and the move to the UI (the higher level). 

We will start with the DataAccesor and the DBConnector - do we get a connection to the database at all? When the database connection has been confirmed, we will move to the UI (since the controller only consists of one method and no dependencies is required) and test whether the UI react as expected. 
  
Then we will test whether we can get a glass and frametype price from the database. 

Just before last we will check the pricecalculation, where the frameprice has been taken from the database, and lastly we check the Terminal-UI, where we provide it with automatically input from the system IO, and calculate with the frametype retrieved from the database. 
