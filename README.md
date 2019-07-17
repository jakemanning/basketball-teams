# BasketballPlayerTeams

This java app parses details about NBA players from included csv files: [cities.csv](https://github.com/jakemanning/basketball-teams/blob/master/Project5/cities.csv), [players.csv](https://github.com/jakemanning/basketball-teams/blob/master/Project5/players.csv), and [teams2.csv](https://github.com/jakemanning/basketball-teams/blob/master/Project5/teams2.csv)

Interface was designed using Java Swing and the MVC pattern.

### CSV files
- cities.csv: A list of cities and states and their coordinates.
- players.csv: A list of NBA basketball players with name, DOB, hometown, and Date of death (if relevant)
- teams2.csv: A list of NBA basketball teams with team name, location, and the list of players that were on the team in a given year 

The files had quite a few errors and had to be 'cleaned' when parsing. 

### App
![Homescreen](https://github.com/jakemanning/basketball-teams/blob/master/demo-pictures/Screen%20Shot%202019-07-16%20at%2010.18.43%20PM.png)

We had to be able to load data from csv files, as well as being able to add/edit/delete any data we needed. The app also had to save the current list at any given time to either csv files or a single binary file. 

The app had to be able to graph a pie chart with the distribution of ages of the players you select, using joda time to handle datetimes.

![Pie chart](https://github.com/jakemanning/basketball-teams/blob/master/demo-pictures/Screen%20Shot%202019-07-16%20at%2010.30.04%20PM.png)

Another requirement was the ability to map out the locations of a teams hometown vs the team location (example below).
We had a list of players and their associated hometown coordinates, the issue was to actually create a map using these coordinates. Our solution was to download an empty map of the US, and manually test several known locations on the map (the greater variance in pixel position the better) by approximating the position with our mouse and comparing the pixel values, thereby creating a linear approximation for the locations.
![Team map](https://github.com/jakemanning/basketball-teams/blob/master/demo-pictures/Screen%20Shot%202019-07-16%20at%2010.42.08%20PM.png)

Finally, the app had to determine the degree of separation between players. 
```
We define degree of separation DS between players informally, as follows. 
DS (A, A) = 0
DS (A, B) = 1 if A and B played for the same team in the same year.
DS (A, C) = 2 if A and C did not play for the same team in the same year but there is some player B for which DS (A, B) = 1 and DS (B, C) = 1.
  Etc.
Even more informally, degree of separation simply means the number of steps it takes to get from one player to another, counting other players as steps if they played on the same team in the same year.
```
Finding the shortest degree of separation between Danny Ferry and Sundiata Gaines:
![Degree of Separation](https://github.com/jakemanning/basketball-teams/blob/master/demo-pictures/Screen%20Shot%202019-07-16%20at%2010.54.04%20PM.png)

The app calculates all shortest paths between players. As you can see below, the DOS between these players is 3.

![DOS of 3](https://github.com/jakemanning/basketball-teams/blob/master/demo-pictures/Screen%20Shot%202019-07-16%20at%2010.54.10%20PM.png)
