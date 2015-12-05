# BasketballPlayerTeams
Instructions on using this program. Run the program through the driver. You move left to right in this program. Begin by looking at the 'Places' column. You may either add your own cities (with latitude and longitudes), or you may load a pre- arranged list from the files provided. If you choose this option, first select 'File' -> 'Load' (ignoring any save requests if you would not like to save your data), and choose 'Places,' then choosing the cities.csv file. Repeat instructions for the players column (players.csv), and the teams column (teams2.csv). You may also do a shortcut by selecting 'File' -> 'Load' the 'OutputFile.bin' file, which will automatically load all three of the columns. If you would like to clear all of the lists, use the 'File' -> 'Clear All' option. You may not edit any of the pre-arranged items, but you may add your own items and have the ability to edit these. 

To graph people, select one or more people in the people column (using the shift key), and then select 'Graph' -> 'Pie Chart' or 'Map'. Pie chart will show a distribution of the ages of the people selected, and the Map will show the birth city of each person selected. Additionally you may pick a single team, and have the ability to graph every single member on that team during that year, as well as the team's headquarters location. 

We define degree of separation DS between players informally, as follows. 
DS (A, A) = 0
DS (A, B) = 1 if A and B played for the same team in the same year.
DS (A, C) = 2 if A and C did not play for the same team in the same year but there is some player B for which DS (A, B) = 1 and DS (B, C) = 1.
  Etc.
Even more informally, degree of separation simply means the number of steps it takes to get from one player to another, counting other players as steps if they played on the same team in the same year.

The 'Degree of Separation' button below the Players column allows for users to select two players. One player is the base case and the other is the target. We are to find the smallest degree of separation between two users, that is, the least number of steps it takes to get from one basketball player to another.

You may delete any items from any list. If you deleted a city, and player associated with that city will also be deleted, as well as any team associated with that team or city. Interface was designed using MVC design pattern.
