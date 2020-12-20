![image](https://user-images.githubusercontent.com/74238558/102718368-76f0aa00-42f0-11eb-8821-4b17269292ba.png)
# READEME - Pokemon Game :
### Authors: Elad Vaknin and Avigael Abitbol
This assignment  based on previous assignments in an object-oriented programming course and on the realization of a directed and weighted graph.
The assignment made up of two parts : 

**Part A** - Realization of a weighted and directed graph and algorithms which operate on the graph.

**Part B** - Build, design, interface in front of a server and solve the Pokemon game.

# The implements : Part A - 


-   **directed_weighted_graph**  (implemented by  **DWGraph_DS**) - an object which represents a directed weighted graph.
    
    -   **node_data**  (implemented by  **NodeData**) - an object displays the vertices in the graph and the actions that are performed on them. The vertex receives a key, tag, and data.
    -    **edge_data**  (implemented by  **EdgeData**) - an object displays the edges in the graph and the actions that are performed on them. Theedges receives a src, dest, and wieght.
-   **dw_graph_algorithms**  (implemented by  **DWGraph_Algo**) - an object that implements some basic graph algorithms with Dijkstra's and BFS algorithms.
    
-   The main reasons that i chose HashMap is because i saw that we need to do things in O(1) and we neet to work whit Collections , for example methods - add ,size,values,remove,getV and more.
   
    



#  Methods
### DWGraph_DS
In this class i implements the interfaces - directed_weighted_graph and  Serializable.

**node_data**

| Name |  Description|
|--|--|
| NodeData () |  Constructor|
| NodeData(int k) | Constructor that got a key   |
| getKey() | Returns the nodes key |
| getInfo() | Returns the nodes String |
| setInfo(String s) | Set the info nodes |
| getTag() | Returns the nodes tag |
| setTag(double t) |Set the tag nodes  |
| compareTo(node_info n) | comper between two nodes tag |

**edge_data**
| Name |  Description|
|--|--|
| edge_data()|  Constructor|
| setWeight(int dest, double w)| Set the weight of the edge
| connectEdge(int n, double w) | Connect between to nodes |
| hasNi(int n) |  Check if to nodes key have a neibers|
| getNi() | Returns a collection of the neibers |
|getWeight(int dest) | Returns the whight of edge |
| removeSrc()  |  Remove all the nodes in the Hashmap|
|  removeEdge(int n)| Remove a edge |


**DWGraph_DS**

| Name |  Description|
|--|--|
| DWGraph_DS ()|  Constructor|
| getNode(int key)| Returns a node with a key
| hasEdge(int node1, int node2) |Check if is an edge between two nodes |
| getEdge(int node1, int node2) |  Returns the weight of the edge|
| addNode(int key) | Add node to the graph |
|connect(int node1, int node2, double w) | Connect btween two nodes and init the wight of the edge |
| getV()  |  Returns a Collection representing all the nodes in the graph|
|  getV(int node_id)| Returns all the neibers of node id |
| removeNode(int key)| Remove a node from the graph |
| nodeSize()| Returns the number of nodes in the graph |
| edgeSize()| Returns the number of edges in the graph |
|getMC()  | Returns the number of actions performed on the graph |


### DWGraph_Algo

In this class i implements the interfaces -  dw_graph_algorithms.

| Name |  Description|
|--|--|
|DWGraph_Algo() | Constructors|
| init(weighted_graph g)|  Initialization the graph|
|getGraph() | Returns a pointer to the initialized graph
| copy()| Copy constructor - deep copy |
| isConnected()|  Checking connectivity of the graph|
| shortestPathDist(int src, int dest)| Returns the sort distance between src to dest whit Dijkstra's algorithm |
|shortestPath(int src, int dest)| Returns the way from src to dist whit Dijkstra's algorithm and whit a list |
| save(String file)  | Saves a graph to a file whit Json|
| load(String file) | Load a graph from a file whit Jason |


- In this part there are also classes that represent location -GeoLocatio and working with the server - game_service.


#  Part B - "The Pokemon Game".
 - In this part are the departments that implement the design (GUI, JPanel, JFarme).
And the player side facing the server in this game.
For the purpose of creating the GUI we implemented the myPanel and myFarme classes which represent and create a drawing of a graph, agent and Pokemon.

![image](https://user-images.githubusercontent.com/74238558/102718179-74418500-42ef-11eb-9300-1dfaf5bc71b4.png)

***classes*** : 
 
 - **Arena** - This class represents a multi Agents Arena which move on a graph - grabs Pokemons.
 - **CL_Agent** - This class represents the creation of an agent and its properties (speed, distance ..)
 - **CL_Pokemon** - This class represents the creation of an pokemons and its properties .
 - **MyFrame** - This class extends JFarame and represents GUI game on a graph.
 - **MyPanel** - This class extends JFarame and represents the creation of the graph, agent, and Pokemon (as icons) for the game.
 - **Ex2.java** - The main class , represents the initialization of the graph, running the game and the algorithm for maximum score.

##  "How to play ?" .
 first step : Enter your ID : 
 ![WhatsApp Image 2020-12-21 at 00 23 20](https://user-images.githubusercontent.com/74238558/102725969-889f7500-4323-11eb-97e5-b73edd0a4cbc.jpeg)

Second step : choose level:
![WhatsApp Image 2020-12-21 at 00 23 35](https://user-images.githubusercontent.com/74238558/102725987-ac62bb00-4323-11eb-9a39-806ee3426a7c.jpeg)

The game is runninig ! 
![WhatsApp Image 2020-12-21 at 00 24 32](https://user-images.githubusercontent.com/74238558/102726007-cf8d6a80-4323-11eb-8bab-06ee8d4b7fc6.jpeg)




##  "Results" :
| level|  moves| grade| 
|--|--|--|
| 0|283|147
| 1|610|585
| 2|283|269
| 3|605|897
| 4|282|224
| 5|599|660
| 6|600|410
| 7|600|410
| 8|299|85
| 9|556|585
| 10|546|597
| 11|599|2132
| 12|279|80
|13 |599|349
| 14|281|125
| 15|596|452
| 16|298|287
| 17|616|1009
| 18|282|40
| 19|566|363
| 20|281|187
| 21|615|381
| 22|403|292
| 23|679|1201






## Sources and links 

https://www.youtube.com/watch?v=4oRVMCRCvN0
https://www.youtube.com/watch?v=6uMFEM-napE
https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
https://en.wikipedia.org/wiki/Graphical_user_interface
https://www.oracle.com/technical-resources/articles/java/json.html
https://www.youtube.com/watch?v=6iZiqQZBQJY
https://stackoverflow.com/questions/3162909/method-to-save-networkx-graph-to-json-graph
https://javaee.github.io/jsonp/
https://www.youtube.com/watch?v=WNgov8avVUc&list=PLI1YQ3QGdy-Y5KxXuxGUXuh6idMfJIIbB&index=5&t=3032s
https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/package-summary.html
https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#paintComponent(java.awt.Graphics)
https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
