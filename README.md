# Simulation

The project was developed to simulate the behavior of herbivores and predators.
Actions take place in a world of 15 by 15 cells. The map size can be changed by the user.

![image](https://github.com/Ruhalov/Simulation/assets/40489058/4e847647-c2f7-42ec-b5dd-ff3c9904baf5)

When the simulation starts, the following entities are randomly distributed on the map:
- 🟥 - **predator** hunts herbivores;
- 🟨 - **herbivores** eat grass;
- 🟢 - **grass**;
- 🟤 - **tree**;
- ⚫ - **rock**;
- ⬜ - **empty cell**.

Creatures can move on empty cells in 4 directions: up, down, left and right. Each turn, the creature's health points is reduced by 1.  
<pre>
⬜⬆️⬜  
⬅️🟥➡️  
⬜⬇️⬜ 
</pre>
The map coordinates are looped along the X and Y axes.  
<pre>
⬜⬜⬜     -------     ⬜⬜⬜  
⬜⬜🟨     Go right    🟨⬜⬜  
⬜⬜⬜     -------     ⬜⬜⬜
</pre>
<pre>
⬜🟨⬜     -------     ⬜⬜⬜  
⬜⬜⬜      Go up      ⬜⬜⬜  
⬜⬜⬜     -------     ⬜🟨⬜
</pre>
Creatures search for the shortest path to the nearest target using the [A* algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm) modified for a looped map.
<pre>
⬜⬜⬇️⬜⬜⬜
⬜⬜➡️➡️🟨⬜
⬜⬜⚫⬜⚫⬜
⬜⬜⬜⚫⬜⬜
⬜⬜🟥⬜⬜⬜
⬜⬜⬇️⬜⬜⬜
</pre>
When a predator eats a herbivore or a herbivore eats grass, their health points increase. If a creature has 0 health points, it dies.  
If no one eats grass for a long time, it turns into a tree.  
<pre>
⬜⬜⬜     -------     ⬜⬜⬜  
⬜🟢⬜    Not eaten    ⬜🟤⬜  
⬜⬜⬜     -------     ⬜⬜⬜
</pre>
After a few turns, the tree dies but leaves a sprout of grass nearby.  
<pre>
⬜⬜⬜     -------     ⬜⬜⬜  
⬜🟤⬜    Few turns    ⬜⬜⬜  
⬜⬜⬜     -------     ⬜🟢⬜
</pre>
If a creature accumulates enough health points, it creates a descendant of its kind.  
<pre>
⬜🟢⬜     -------     ⬜🟨⬜     -------     ⬜🟨🟨 
⬜🟨⬜       Eat       ⬜⬜⬜    reproduce?   ⬜⬜⬜   
⬜⬜⬜     -------     ⬜⬜⬜     -------     ⬜⬜⬜
</pre>
