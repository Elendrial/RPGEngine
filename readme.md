###To do list:

- Inventory support
- Quest/task support
- Image to level support (low priority)
- Possibly add support for tile/entity textures made from multiple images
- Improve texture handling
- Vastly improve saving system
- Add "grid entity" (must stay on the grid)
- Add onInteraction

    
### To Do for V2:
###### These will be done after the engine works
- Go through and rewrite almost everything? (Maybe as a v2 once it's working)
    - Remove almost all cloning, its not actually needed (like with the registries)
    - Clean code-base and make it work more coherently
- Rewrite guis to allow for easier formatting
    - Allow for all gui elements to have a background picture
    - Use a 'gui style' to set text justification, colour, font etc etc etc
    - Add an "onShow" method, similar to onLoad, maybe have guis hidden by default until show is called?
- Make a pokemon clone in it. I demand future self makes a pokemon clone
- Add an event for when a player does anything (allows for turnbased games)
- Easy keybinds
- Change TexturedObject to an interface