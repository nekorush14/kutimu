# All habit Screen

## Screen image

|                    Light theme                     |                        Dark theme                         |
|:--------------------------------------------------:|:---------------------------------------------------------:|
| ![](/docs/assets/images/All%20habits%20screen.png) | ![](/docs/assets/images/All%20habits%20screen%20Dark.png) |

## Details

This screen displays a list of registered habits.
You can manage your habits by adding, editing, etc.
Deleting a habit will perform at register/edit sheet screen.

## Screen elements

| No. | Element              | UI Description                                                                           | UI Component         |
|-----|:---------------------|:-----------------------------------------------------------------------------------------|:---------------------|
| 1   | Top bar              | Show application name and theme changing button                                          | TopAppBar            |
| 2   | Habit list           | Show a list of registered habits                                                         | LazyColumn with Card |
| 3   | Habit icon           | Category icon of habit                                                                   | Icon                 |
| 4   | Habit progress       | Shows the progress of your habits. The icon shows your routine has been performed or not | Icon                 |
| 5   | Pined status         | Status icon indicating whether pinned to Main Screen                                     | Icon                 |
| 5   | Add new habit button | A button to register a new habit                                                         | FloatingActionButton |

## Features

1. Grans all habits
    * Display a list of habits from database
    * Displays the progress of habit with icons
    * The icon have 2 types (Completed: filled check, Incomplete: outlined check)
    * If all of progress become complete, those habit card color will change to gray to mark as completed in this iteration

2. Add/Edit habits
    * Register new habits
    * If user click `habit` button, it will show register form sheet (ref: FS-002:Habit detail sheet)
    * If user click existing habit card, also it will show form sheet which has same view of register form with registered data
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
