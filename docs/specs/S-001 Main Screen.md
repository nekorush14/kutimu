# Main screen

## Screen image

|                Light theme                 |                    Dark theme                     |
|:------------------------------------------:|:-------------------------------------------------:|
| ![](/docs/assets/images/Main%20Screen.png) | ![](/docs/assets/images/Main%20Screen%20Dark.png) |

## Details

The main screen of this app.

It's the first thing your users see.  
It clearly shows them what they need to do today and prompts them to take action.

## Screen elements

| No. | Element                         | 	UI Description                                 | UI Component |
|-----|:--------------------------------|:------------------------------------------------|:-------------|
| 1   | Top bar                         | Show application name and theme changing button | TopAppBar    |
| 2   | Greetings message               | Display a time-appropriate greeting message.    | Text         |
| 3   | Pined habit status              | List of pinned habits                           | LazyColumn   |
| 4   | Remaining today's tasks message | Number of tasks remaining today                 | Text         |
| 5   | Actual remaining today's tasks  | A list of the actual tasks                      | LazyColum    |

## Features

1. Grans a habits
    * Display a list of habits from data base and only show PINED habit
    * Displays the progress of habit with icons
    * The icon have 2 types (Completed: filled check, Incomplete: outlined check)
    * If all of progress become complete, those habit will disappear from this list for current
      habit iteration

2. Grans an upcoming tasks
    * Display a list of today's incomplete tasks
    * Display the number of today's incomplete tasks by "Today %d tasks remaining"
    * Each list element show habit name which linked with it
    * If not linked with habit, it will not show its name
    * Each list element have a checkbox to mark as done
    * If all tasks which linked with currently showing habit is completed, outlined check icon
      change to filled check icon

3. Bottom navigation
    * Define 3 screens to bottom navigation bar
        * Home, All habits and All tasks
    * If user click the different icon which currently not selected, change the screen content

4. ~~Theme change (Common feature)~~
    * ~~If user click the upper right icon, the screen theme will change to inverted theme (light ->
      dark/dark -> light)~~
    * For some reason, this feature will postpone until design theme management will defined between
      device and app.

## On error occurred

* If error has occurred when user perform the action include retrieving the data from database, app
  will show a error message as Snackbar
    * Error message: "Something went to wrong. Please try later"
    * Retry button will show in snack bar
* When error occurred, the logging to logcat with ERROR level
