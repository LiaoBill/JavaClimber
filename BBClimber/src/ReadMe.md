### BiliBili Climber ###

## Description ##
This climber is for getting all comments and save them to local disk path "D:/XClimbers/BiliBiliClimber" for quick view and search.

## Implementation ##
First, Getting avObject filled with normal video av number, name and description.

Second, Getting comment page counts with a request to bilibili api url with page number set to "1".

Third, use gotten page counts to fill all comments into avObject's comments data structure.

Finally, write the whole avObject as a json object to local disk path.
