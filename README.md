# Group-5-Project
Group 5 Project

PensieveSkeleton is code for Prog3. The video is based on this code

Pensieve_test is our work in progress code for the actual app. This is for the showcase but not for Prog3

## Server
The Pensieve server is hosted on Heroku and runs on Ruby on Rails. The server is used for phone-to-phone communication between family members and patients, and is used for account sign up, authorization, and message transfers.

**GitHub repository:** https://github.com/daltonboll/PensieveServer

**Heroku:** http://pensieve-server.herokuapp.com/

#### Server API documentation

Note: All API calls are expected to be sent and received in JSON.

**User Account Creation**

*Description:* Use this API call in order to create a new User account. A "family" account cannot be created without an existing "patient" account. 

*Method:* POST

*Route:* http://pensieve-server.herokuapp.com/api/users

*Fields:*

Key | Type | Required | Restrictions
---- | ---- | ---- | ----
name | string | Yes
email | string | Yes | Must be of the form x@y.domain; Must be unique
password | string | Yes
phone_number | string | Yes | Must be unique; Must be of the form 1112223333 or 111-222-3333 or (111) 222-3333 or (111)222-3333
role | string | Yes | "patient" or "family"
patient_phone_number | string | Only for "family" role | Must be of the form 1112223333 or 111-222-3333 or (111) 222-3333 or (111)222-3333; Must already belong to an existing "patient" role

*Example CURL Request for "patient" creation:* 

`curl -H "Content-Type: application/json" -X POST -d '{"name":"Bob", "role":"patient", "email":"bob@mail.com", "password":"password", "phone_number":"1112223333"}' http://pensieve-server.herokuapp.com/api/users`

*Example Server Response:*
```
{
    "status": 1,
    "user": {
        "id": 7,
        "name": "Bob",
        "email": "bob@mail.com",
        "password": "password",
        "role": "patient",
        "phone_number": "1112223333"
    }
}
```

**User Login**

**Getting User Information**

**Getting Information For All Members of a Family**

## Activity Overview
The database folder contains database related stuff so that even when you exit out of the app, the data will still exist

main screen = initial login screen

phoneListenerService & phoneToWatchService = deals with phone to watch connection

TaskListActivity = calls on FamilyMemberFragment

FamilyMemberFragment = main fragment for the list of activities

TaskPagerActivity = calls on TaskFragment. This allows us to swipe between multiple new tasks.

TaskFragment = deals with adding a new task

Tasks = class of tasks

TimePickerFragment = deals with time picker

TaskManager = deals with getting the data stored in the database.


## Authors

Dalton Boll

Graham Seyffert

David Koh

Chase Smith 

Casey Nguyen


## Demo Video


## Screenshots

<img src="Screenshot/Start.png" height="400" alt="Screenshot"/>
<img src="Screenshot/Task.png" height="400" alt="Screenshot"/>
<img src="Screenshot/Weekly_summary.png" height="400" alt="Screenshot"/>
<img src="Screenshot/Set_task.png" height="400" alt="Screenshot"/>

<img src="Screenshot/Reminder.png" height="200" alt="Screenshot"/>
<img src="Screenshot/Confirmation.png" height="200" alt="Screenshot"/>



## Acknowledgments
