# Armada Book Forceop

Armada book forceop is a mod that can help elevate your permission level from gamemode creative to OP
This mod is for the minecraft version 1.17
**You need creative for this mod to work**

## How does this mod work?
when you are in creative, this mod lets you generate force-op books.
When read by an admin, these books will force the admin to run any command you set while generating them.

## Here is a simple tutorial on how to install and use the mod.
- Before using this mod, you have to download the [Fabric Loader](https://fabricmc.net/use/) and [Fabric api](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
- After downloading the fabric loader and fabric api, download the mod [Here](https://github.com/Saturn5Vfive/ArmadaForceop/raw/master/latestbuild/forceop-1.0.0.jar)
- Place the mod inside your mods folder in minecraft
- Launch minecraft on the fabric 1.17 profile
- Go into a game that you have creative on
- typing .w or .write in the chat is the prefix for the command
here is the format for the command .w (name of the book) (text inside the book) (command you want them to run) ((optional) author). 
 The author argument is optional and will be ignored if none is provided. 
 **You need to replace all the spaces in the book you want to generate with dashes -**
 
 For Example:
  *.write My-Example-Book Hello-Cool-Reader /say-i-am-a-silly-chicken*
  
  would make a book that has the title "My Example Book" with the text "Hello Cool Reader" inside, 
  
 and when you flip the page it makes you run "/say i am a silly chicken"
  
  Another Example:
  *.w My-Epic-Book Please-Read-The-Whole-Thing! /op-@a TheDuperTrooper*
  
  That would return a book with the title "My Epic Book" with the text inside 
  
 "Please Read The Whole Thing!" that runs the command "/op @a" when the admin reads it, 
 
 however this book would be signed by TheDuperTrooper because of that optional arguement at the end

