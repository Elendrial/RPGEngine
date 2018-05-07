###To do list:

- Inventory support
- Quest/task support
- Image to level support (low priority)
- Possibly add support for tile/entity textures made from multiple images
- Improve texture handling
- Vastly improve saving system
- Rewrite guis to allow for easier formatting
    - Allow for all gui elements to have a background picture
    - Use a 'gui style' to set text justification, colour, font etc etc etc
    - Add an "onShow" method, similar to onLoad, maybe have guis hidden by default until show is called?
- Make a pokemon clone in it. I demand future self makes a pokemon clone
- Add an event for when a player does anything (allows for turnbased games)

### Notes:

- Entity and Tile textures are automatically loaded when they are added to their registries.
- Players are implemented entirely by the user, rather than partially by the engine (Allows for more control/multiple players etc)
- BaseEntity should *never* be directly extended, always use GridEntity/FreeEntity unless you want to completely overwrite all entity code.
- Tiles do not store their position, so the regular render function is never used by the base engine (it is still called), render(Graphics, Vector) is used for drawing instead. The same could be done with GridEntities, however as they are designed to be able to move, they store their own position as well as the grid storing it.