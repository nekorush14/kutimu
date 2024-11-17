# All tasks Screen

## Screen image

|                    Light theme                    |                        Dark theme                        |
|:-------------------------------------------------:|:--------------------------------------------------------:|
| ![](/docs/assets/images/All%20tasks%20screen.png) | ![](/docs/assets/images/All%20tasks%20screen%20Dark.png) |

## Details

This screen displays a list of tasks associated with a particular habit.
You can manage your task by adding, editing.
Deleting a task will perform at register/edit sheet screen.

## Screen elements

| No. | Element                | UI Description                                   | UI Component         |
|-----|:-----------------------|:-------------------------------------------------|:---------------------|
| 1   | Top bar                | Show application name and theme changing button  | TopAppBar            |
| 2   | Task list              | Show a list of registered task                   | LazyColumn           |
| 3   | Task icon              | Category icon of task                            | Icon                 |
| 4   | Related habit name     | Show a habit name if user linked a habit         | Text                 |
| 5   | Task completion status | Show a task status complete or not as check mark | Checkbox             |
| 6   | Add new task button    | A button to register a new task                  | FloatingActionButton |

## Features

1. Grans all tasks
    * Display a list of tasks from database
    * Displays an icon indicating what type of task this task is as a leading icon
    * Complete a task by checking a box

2. Add/Edit habits
    * Register new task
    * If user click `task` button, it will show register form sheet (ref: FS-003:Task detail
      sheet)
    * If user click existing task list item, also it will show form sheet which has same view of
      register form with registered data
    * Delete will perform on form sheet

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
