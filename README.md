# HotCiv

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
* [ ] units cannot move over mountain
* [ ] units cannot move over ocean
* [ ] Red cannot move Blue's units
* [ ] after Red it is Blue that is in turn
* [ ] Red's unit attack and destroy Blue's unit
* [ ] the attacker of a battle always wins
* [ ] units cannot move unto each other
* [ ] cannot build an Archer with <10 production
* [ ] building an Archer with >=10 production produces an Archer and deducts 10 production
* [ ] cannot build a Legion with <15 production
* [ ] building a Legion with >=15 production produces a Legion and deducts 15 production
* [ ] cannot build a Settler with <30 production
* [ ] building a Settler with >=30 production produces a Settler and deducts 30 production
* [ ] Red has an archer at (2,0)
* [ ] Blue has a Legion at (3,2)
* [ ] Blue has a Settler at (4,3)
* [ ] Red producing 5 archer => archers at (1,1), (2,0), (2,1), (1,3), and (0,3)
* [ ] Production of red city (1,1) is 6 'production' after a round has ended
* [ ] Red city produces a unit, does not remove production from blue city
* [ ] ...