# Devin's Personal Project

## My Anime List

User Stories for the **Anime List** App:
- As a user, I want to be able to **add** an anime to my anime list
- As a user, I want to be able to **view** all animes with information in my anime list
- As a user, I want to be able to **mark** an anime as I have watched it.
- As a user, I want to be able to **remove** an anime from my anime list if I don't like it.
- As a user, I want to be reminded to **save** my anime list to file or not when I select the quit option from the anime list app menu
- As a user, I want to be able to have the option to **load** my anime list from a file when I start the anime list app

**Proposal**

I want to design an app for anime fans to check the anime they are interested in. It provides the list of animes that they want to watch or are currently watching. They can modify the list by marking the ones that have just been watched, or if they don't like it, they can remove them from the list.

**Citation**

Some codes are based on the supplied Workroom example from CPSC 210.

(JsonSerializationDemo, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

# Instructions

- You can generate the first required event related to adding Xs to a Y by adding an anime to the anime list
- You can generate the second required event related to adding Xs to a Y by removing an anime from the anime list
- You can locate my visual component by checking out the main window
- You can save the state of my application by clicking the save button
- You can reload the state of my application by clicking the load button

## Event logs

- Event log "A New Anime [name] Has Been Added With An Anime Date." will occur when the user adds a new anime to the list.
- Event log "Anime [name] Has Been Removed." will occur when the user removes an anime from the list.
- Event log "Anime [name]'s Status Has Changed." will occur when the user changes the status of an anime.

## Reflections
- If I knew from the beginning that we were going to use our model with a GUI, I would change and write my method in a way that would be better usable from a GUI perspective, rather than writing the method in a way of using it from the main java control stage perspective.
  For refactoring, I would spend more time on organizing the JPanel and Jbutton parts. Use new classes to make each button function individually instead of putting them all in a GUI class, to make them more cohesive and less coupling.
