# HotCiv

## DeltaCiv

* [ ] Blue city at (4,5)
* [ ] Red city at (8,12)
* [ ] Mountain at (0,5)
* [ ] Hills at (1,4)
* [ ] Ocean at (8,6)
* [ ] Hills at (7, 13)
* [ ] Forest at (9, 11)
* [ ] Plains at (5,1)
* [ ] Ocean at (0,0)
* [ ] Ocean at (15,15)
* [ ] Hills at (4,8)
* [ ] Forest at (11,8)
* [ ] There are 9 mountains
* [ ] There are 10 hills
* [ ] There are 12 forests

## GammaCiv

* [ ] Settler at (4,3) builds city => no unit at (4,3), but red city
* [ ] Settler at (4,3), moves right, builds city => no unit at (4,4), but red city
* [ ] Blue changes production to settler, waits 5 rounds, moves settler to (5,1), builds city => blue city at (5,1)
* [ ] Archer has 3 defensive strength
* [ ] Archer, uses fortify, has 6 defensive strength
* [ ] Archer, uses fortify, uses fortify, has 3 defensive strength

## BetaCiv

### Test-list
* [ ] After 38 rounds,  age is -100
* [ ] After 39 rounds,  age is -1
* [ ] After 40 rounds,  age is +1
* [ ] After 41 rounds,  age is +50
* [ ] After 42 rounds,  age is +100
* [ ] After 85 rounds,  age is +1775
* [ ] After 94 rounds,  age is +1905
* [ ] After 108 rounds, age is +1971
* [ ] After 109 rounds, age is +1972
* [ ] Winner is null at beginning
* [ ] Red archer moves (2,0) => (3,0) => (4,1) => red is winner
* [ ] Blue legion moves (3,2) => (2,1) => (1,1) => blue is winner

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
* [x] ~~Red producing 6 archer => archers at (1,1), (0,2), (1,2), (2,1), (2,0) and (0,0)~~
* [x] ~~Red producing 5 legions => legions at (1,1), (0,2), (1,2), (2,1), and (2,0)~~
* [x] ~~Red producing 5 settler => settler at (1,1), (0,2), (1,2), (2,1), and (2,0)~~
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
* [ ] ...