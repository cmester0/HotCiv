# HotCiv

## EtaCiv
* [ ]

## ZetaCiv
* [x] ~~There is no winner at the beginning of the game~~
* [x] ~~If red conquers blue city at round 1, then red wins the game~~
* [x] ~~If red conquers blue city at round 20, then red wins the game~~
* [x] ~~If red conquers blue city at round 21, then red does not win the game~~
* [x] ~~If a player wins 3 attacks after round 20, that player wins the game~~
* [x] ~~If a player wins 3 attacks before round 20, then there is no winner set~~

## EpsilonCiv
* [x] ~~Base attack of archer is 2~~
* [x] ~~Base attack of legion is 4~~
* [x] ~~Base attack of settler is 0~~
* [x] ~~Combined attack strength of a unit in a city is tripled~~
* [x] ~~Combined attack strength of a unit on a hill is doubled~~
* [x] ~~Base defence of archer is 3~~
* [x] ~~Base defence of legion is 2~~
* [x] ~~Base defence of settler is 3~~
* [x] ~~Combined defence of unit on a hill is doubled~~
* [x] ~~Combined defence of a unit in a city is tripled~~
* [x] ~~If the die roll is the same for attacker and defender, then the attacker wins if atkStrength > defStrength, if not he loses~~
* [x] ~~If a player wins 3 attacks, then that player wins the game~~
* [x] ~~Winning 3 battles does not win the game~~

## DeltaCiv

* [x] ~~Blue city at (4,5)~~
* [x] ~~Red city at (8,12)~~
* [x] ~~Mountain at (0,5)~~
* [x] ~~Hills at (1,4)~~
* [x] ~~Ocean at (8,6)~~
* [x] ~~Mountain at (7, 13)~~
* [x] ~~Forest at (9, 11)~~
* [x] ~~Plains at (5,1)~~
* [x] ~~Ocean at (0,0)~~
* [x] ~~Ocean at (15,15)~~
* [x] ~~Hills at (4,8)~~
* [x] ~~Forest at (8,13)~~
* [x] ~~There are 9 mountains~~
* [x] ~~There are 10 hills~~
* [x] ~~There are 12 forests~~

## GammaCiv

* [x] ~~Settler at (4,3) builds city => no unit at (4,3), but red city~~
* [x] ~~Settler at (4,3), moves right, builds city => no unit at (4,4), but red city~~
* [x] ~~Unit action at (0,0) does nothing~~
* [x] ~~Unit action at archer (2,2) does not produce a city~~
* [x] ~~Blue changes production to settler, waits 5 rounds, moves settler to (5,1), builds city => blue city at (5,1)~~
* [x] ~~Archer at (2,0) has 3 defensive strength~~
* [x] ~~Archer at (2,0), uses fortify, has 6 defensive strength~~
* [x] ~~Archer at (2,0), uses fortify, uses fortify, has 3 defensive strength~~

## BetaCiv

### Test-list
* [x] ~~After 0 rounds, age is -4000~~
* [x] ~~After 40 rounds, age is -1~~
* [x] ~~After 41 rounds, age is +1~~
* [x] ~~After 42 rounds, age is +50~~
* [x] ~~After 43 rounds, age is +100~~
* [x] ~~After 77 rounds, age is +1775~~
* [x] ~~After 83 rounds, age is +1905~~
* [x] ~~After 97 rounds, age is +1971~~
* [x] ~~No winner at beginning of the game~~
* [x] ~~Red archer moves (2,0) => (3,0) => (4,1) => red city at (4,1)~~
* [x] ~~Blue legion moves (3,2) => (2,1) => (1,1) => blue city at (1,1)~~
* [x] ~~Red archer moves (2,0) => (3,0) => (4,1) => red is winner~~
* [x] ~~Blue legion moves (3,2) => (2,1) => (1,1) => blue is winner~~
* [x] ~~Settlers cannot conquer cities~~
---
* [x] ~~Waiting does not cause a winner~~

## AlphaCiv features:
* Test system has been set up
* Basic environment set up

### Test-list:
* [x] ~~Red is the first player in turn~~
* [x] ~~Red's city is at (1,1)~~
* [x] ~~Blue's city is at (4,1)~~
* [x] ~~there is ocean at (1,0)~~
* [x] ~~There is a Mountain at (2,2)~~
* [x] ~~There is a Hill at (0,1)~~
* [x] ~~the starting year is 4000 BC~~
* [x] ~~one round => year 3900 BC~~
* [x] ~~two rounds => year 3800 BC~~
* [x] ~~three rounds => year 3700 BC~~
* [x] ~~Red wins in year 3000 BC~~
* [x] ~~Plains everywhere except (1,1), (4,1), (1,0), (2,2)~~
* [x] ~~cities' population size is always 1~~
* [x] ~~red production of city is 0 'production' at the beginning of the game~~
* [x] ~~red city produce 6 'production' after a round has ended~~
* [x] ~~Cities produce archers at the beginning of the game~~
* [x] ~~Changing production in red city to Settler, does change production type~~
* [x] ~~If red city production type is Archer, and we run two rounds, the difference should be 2~~
* [x] ~~If red city production type is Archer, and we run four rounds, the difference should be 4~~
* [x] ~~If red city production type is Settler, and we run four rounds, the difference should be 24~~
* [x] ~~If blue city production type is Settler, and red city production type is Archer, then the difference should be 4 for red city~~
* [x] ~~If red city production type is Settler, and blue city production type is Archer, then the difference should be 4 for blue city~~
* [x] ~~Two rounds Red producing 1 archer => 1 archer at (1,1)~~
* [x] ~~Changing red production to Settler => Red produces settler at (1,1) after 5 turns~~
* [x] ~~After 6 rounds Red producing 3 archer, and changes to Settler => archers at (1,1), (0,2) and (1,2)~~
* [x] ~~If red production is Legion, then after 3 rounds Red produces 1 Legion at (1,1)~~
* [x] ~~Red producing 6 archer => archers at (1,1), (0,1), (0,2), (1,2), (2,1), (2,0)~~
* [x] ~~Red producing 5 legions => legions at (1,1), (0,1), (0,2), (1,2), (2,1)~~
* [x] ~~Red producing 5 settler => settler at (1,1), (0,1), (0,2), (1,2), (2,1)~~
* [x] ~~Blue produce 1 archer at (4,1) after 2 turns~~
* [x] ~~Blue producing 6 archer => blue archer at (4,1), (3,2), (4,2), (5,2), (5,1), (5,0) and (4,0)~~
* [x] ~~Red has an archer at (2,0)~~
* [x] ~~Blue has a Legion at (3,2)~~
* [x] ~~Blue has a Settler at (4,3)~~
* [x] ~~Red can move initial archer one down~~
* [x] ~~Red cannot move nonexisting unit~~
* [x] ~~Red attempting to move archer actually moves the archer (and deletes old unit)~~
* [x] ~~Red cannot move Blue's units~~
* [x] ~~Red cannot move its own units when it is Blue's turn~~
* [x] ~~cannot move unit unto another unit~~
* [x] ~~units cannot move over mountain~~
* [x] ~~units cannot move over ocean~~
* [x] ~~units cannot move unto each other~~
* [x] ~~the attacker of a battle always wins~~
---
* [x] ~~After 40 rounds age is 0~~
* [x] ~~Winner is null at beginning~~