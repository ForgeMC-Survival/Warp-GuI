

# Diamond Bank  [![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
A Banking System to store Diamonds and to see top bal minecraft.

## Authors

* **[Graviton](https://github.com/Graviton1647)** - *Initial work* 

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## How to Use

1) Download the **[JAR](https://github.com/Graviton1647)** 
2) Adds it in /plugins
3) Run Server

## Explaining the add Command

If the user  does / add and has Diamonds or Diamond Blocks in inventory ans hotbar it uses the Diamonds stars by checking hotbar and uses them, oif they user does not have any in the hotbar it uses the Diamonds.

If the user does /add 6 and has 1+  Diamond block and no normal Diamonds it will use 1 block and give 2 Diamonds back as change.
Diamonds will always take priority over Diamond blocks

## Explaining the remove Command

if the user removes anything 9 or over it will start to convert to blocks for example 

/remove 11 will give you 1  Diamond block and 2 Diamonds

## Commands

1) /add {amount} [Adds Diamonds to the players account]
2) /remove {amount} [Remove Diamonds to the players account]
3) /bal [Tells the player the Balance of the account]
4) /baltop  [List of every users Balance from most to least]
4) /baltop {page} [List of every users Balance from most to least]

## Configs

Config.yml lets you edit the messags that shows to players on commands here is a exmaple

**"{prefix} You Added +{value} Diamonds to your bank."**

1) {prefix} [Shows the prefix of the command.]
2) {name} [Shows the name of the player or player's when in baltop.] 
3) {value} [Shows you how much of a item is being added or how much is in your bal.]
5) {datetime} [Date and time of the server in format {date,time}]
6) {page} [The Current page in baltop the user is looking at.]
3) {totalpages} [Total pages in baltop going off how many people have used the plugin.]

## Messages

1) addmessage [The Message that shows when a player adds a Diamond]
2) addconsolemessage [The Message that shows on the console when a player adds a Diamond]
3) removemessage [The Message that shows when a player removes a Diamond]
4) addconsolemessage [The Message that shows on the console when a player removes a Diamond]
5) removeconsolemessage [The Message that shows when a player adds a Diamond]
6) invalid [The Message that shows when the player trys to add or remove and they don;t have enough]
7) balance [The Message that shows when a player checks the balance]
8) prefix [The prefix of the command]
9) baltop1 [First line of /baltop]
9) baltop2 [Second line of /baltop]
10) baltop3 [Thrid line of /baltop]
11) baltop4 [Fourth lines of /baltop]


## Media

![alt text](https://i.imgur.com/eON8ZKq.png)

![alt text](https://i.imgur.com/AOIFl3v.png)

![alt text](https://i.imgur.com/rIQU6fz.png)

![alt text](https://i.imgur.com/n9YJHmO.png)
