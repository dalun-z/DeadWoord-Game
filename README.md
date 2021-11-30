Changelog:

UML class diagram updated.  Changes include:
1. Removed `Scorer` class
2. Split `GameBoard` class into `BoardView` and `Deadwood` classes, the latter of which will be our game controller
3. Removed `Bank` class
4. Added proper arrow relationships
5. Created `Reward` class to differentiate reward types

## Design Patterns
+ Singleton and State pattern
    * GameState
+ MVC pattern
    * Model
        - Area, Location, Player, Role, Scene, GameState
    * View
        - BoardView, LocationView
    * Controller
        - Deadwood
+ Composite pattern
    * Role
        - ExtraRole, StarringRole
+ Adapter pattern
    * parsingXML
