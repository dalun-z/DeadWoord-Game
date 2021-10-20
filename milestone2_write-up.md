Team #10: David Vicklund, Alyssa Vecht, Dalun Zhang


##SOLID Design Principles:

Our design patterns are starting from the ‘Player’ class, and then extending the rest of the classes depending on what kind of feature we want the ‘Player’ class to have. As we know, Deadwood is a player-based game. Every single move or action of the player will affect the end game result. Since Players are the main characters of the game, we decided to start designing from the ‘Player’ class.

For example, we created a new ‘Role’ class for player because player need to select roles during the game, and there are different roles too, so we created 2 subclass for ‘Role’ class, so that every time when the player decide to select a role, we only need to call ‘Role’ class once.  This adheres to the Single Responsibility Principle, as each role type serves a separate, singular purpose.  It also is an example of the Liskov Substitution Principle, as each subclass is interchangeable for the parent class at any given time.  

The overall design also follows the Interface Segregation Principle, since the only class that depends upon an interface will use all methods within that interface.  The Role interface provides all common methods, while each subclass has a slightly different implementation of the setComplete function, based on the different rewards for each subtype.  

As we are currently uninstructed in design patterns, we are unable to sufficiently describe any we have used in our design thus far, but will update this document when appropriately informed.
