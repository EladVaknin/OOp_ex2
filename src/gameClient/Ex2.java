package gameClient;

import Server.Game_Server_Ex2;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import api.*;

public class Ex2 implements Runnable {

    public static game_service game;
    private static dw_graph_algorithms _graph;
    private static MyFrame _win;
    private static gameClient.Arena arena;
    private static boolean firstRound = true;
    private static List<CL_Pokemon> pokList;
    private static long sleepTime = 100;
    private static int game_level, id;
    private static PriorityQueue<T> queue;
    // main function
    public static void main(String[] args) {
       if (args.length == 2) {
            id = Integer.parseInt(args[0]);
            game_level = Integer.parseInt(args[1]);
//           if (id!=312504301 || id != 209920693)
//               game.stopGame();
        }

       else {
            String ID = resultPanel.getId();
            id = Integer.parseInt(ID);
            String GAME_LEVEL = resultPanel.getGameLevel();
            game_level = Integer.parseInt(GAME_LEVEL);
        }
        game = Game_Server_Ex2.getServer(game_level);
        game.login(id);
        init(game);
        Thread client = new Thread(new Ex2());
        client.start();
    }

    /**
     * mian function of the game
     */
    @Override
    public void run() {

        game.startGame();
        while(game.isRunning()) {
            try {
                f5(game);
                moveAgents();
                _win.repaint();
                arena.setTime(game.timeToEnd());
                Thread.sleep(sleepTime);
                if(game_level == 5 || game_level == 6 || game_level == 7 || game_level == 8|| game_level == 11 ||
                        game_level == 13 || game_level == 15 || game_level == 16 || game_level == 17) sleepTime = 99;
                else if(game_level == 19) sleepTime = 100;
                else if(game_level == 21) sleepTime = 118;
                else sleepTime = 105;
            } catch (Exception e) { e.printStackTrace(); }
        }
        _win.setVisible(false);
        System.out.println(game.toString());
        game.stopGame();
        resultPanel end = new resultPanel();
        end.FinalR(game_level, arena.getMoves(), arena.getGrade());
        System.exit(0);

    }

    /**
     * refresh the game
     * @param game
     */
    private void f5(game_service game) {

        this.arena.setTime(game.timeToEnd());
        JsonParser jP = new JsonParser();
        JsonObject js = (JsonObject)jP.parse(game.toString());
        JsonObject gameJson = js.getAsJsonObject("GameServer");
        JsonElement grade = gameJson.get("grade");
        this.arena.setGrade(grade.getAsInt());
        JsonElement moves = gameJson.get("moves");
        this.arena.setMoves(moves.getAsInt());
        JsonElement level = gameJson.get("game_level");
        this.arena.setLevel(level.getAsInt());
    }

    /**
     * initialize agents for the first round
     * @param agents
     * @param pokemons
     */
    private static void initAgents(int agents, List<CL_Pokemon> pokemons) {
        if(_graph.isConnected()) {
            int howManyAgents = 0;
            for(CL_Pokemon pokemon : pokemons) {
                if(pokemon.getClosePokemon().size() > 1) {
                    if(pokemon.getType() < 0) {
                        game.addAgent(pokemon.get_edge().getSrc());
                        howManyAgents++;
                    }
                }
            }
            if(howManyAgents < agents) {
                for(int i = 0; i < agents - howManyAgents; ++i) {
                    int ind = i % pokemons.size();
                    int n = pokemons.get(ind).get_edge().getDest();
                    if (pokemons.get(ind).getType() < 0)
                        n = pokemons.get(ind).get_edge().getSrc();
                    game.addAgent(n);
                }
            }
        } else {
            List<List<node_data>> LNL = ((DWGraph_Algo) _graph).getLists();
            int counter = 1;
            for (int i = 0; i < LNL.size() - 1; ++i) {
                int node = LNL.get(i).get(0).getKey();
                game.addAgent(node);
                counter++;
            }

            while(counter < agents) {
                int max = 0;
                for(int i = 0; i < LNL.size(); i++) if(LNL.get(i).size() > max) max = i;
                for(int i = 0; i < agents - counter; ++i) {
                    int node = LNL.get(max).get(0).getKey();
                    game.addAgent(node);
                }
            }
        }

    }

    /**
     * initialize the game
     * @param game
     */
    private static void init(game_service game) {

        pokList = new ArrayList<>();
        pokList.add(new CL_Pokemon(null, 0, 0, 0, null));
        arena = new gameClient.Arena();
        _graph = new DWGraph_Algo(game.getGraph());
        directed_weighted_graph graph = _graph.getGraph();
        arena.setGraph(graph);
        String sP = game.getPokemons();
        ArrayList<CL_Pokemon> pokemon = gameClient.Arena.json2Pokemons(sP);
        arena.setPokemons(pokemon);
        for(edge_data edge : ((DWGraph_DS)graph).getE()) {
            geo_location src = graph.getNode(edge.getSrc()).getLocation();
            geo_location dest = graph.getNode(edge.getDest()).getLocation();
            double dist = graph.getNode(edge.getSrc()).getLocation().distance(dest);
            if(dist < (0.001) / 2) ((EdgeData) edge).setShort(true);
        }
        _win = new MyFrame("gameClient.Ex2");
        _win.setSize(1024, 650);
        try {
            String info = game.toString();
            JSONObject line = new JSONObject(info);
            JSONObject server = line.getJSONObject("GameServer");
            int numberOfAgents = server.getInt("agents");
            for(CL_Pokemon pkm : pokemon) gameClient.Arena.updateEdge(pkm, arena.getGraph());
            queue = new PriorityQueue<>(new Compare());
            whereToGo(pokemon);
            initAgents(numberOfAgents, pokemon);
            String agentsString = game.getAgents();
            List<CL_Agent> agents = gameClient.Arena.getAgents(agentsString, arena.getGraph());
            arena.setAgents(agents);
            _win.updateArena(arena);
            _win.setVisible(true);
        } catch (JSONException e) { e.printStackTrace(); }
    }

    /**
     * Moving agents to nodes by useful way
     */
    private static void moveAgents() {

        String agents = game.move();
        List<CL_Agent> agentsList = gameClient.Arena.getAgents(agents, arena.getGraph());
        String stringPokemons = game.getPokemons();
        List<CL_Pokemon> pokemons = gameClient.Arena.json2Pokemons(stringPokemons);
        arena.setAgents(agentsList);
        for(CL_Pokemon pokemon : pokemons) gameClient.Arena.updateEdge(pokemon, arena.getGraph());
        boolean flag = false;
        for(CL_Agent agent : agentsList) {
            gameClient.Arena.updateEdgeForAgent(agent, arena.getGraph());
            if(agent.getPath() == null) {
                flag = true;
                break;
            }
        }
        if(flag || firstRound) {
            arena.setPokemons(pokemons);
            for(CL_Agent agent : agentsList) for(CL_Pokemon pkm : pokemons) queue.add(new T(agent, pkm, _graph));
            firstRound = false;
            nextOne();
        }

        for(CL_Agent agent : agentsList) {
            if(agent.get_curr_pokemon() != pokList) {
                int dest = nextNode(agent);
                game.chooseNextEdge(agent.getID(), dest);
                if((game_level == 3 && agent.getSrcNode() == 8) || (game_level == 3 && agent.getSrcNode() == 9) ||
                        (game_level == 1 && agent.getSrcNode() == 8) || (game_level == 1 && agent.getSrcNode() == 9 ||
                        (game_level == 17 && agent.getSrcNode() == 0) || (game_level == 17 && agent.getSrcNode() == 1)))
                    sleepTime = 75;
                else if((game_level == 23 && agent.getSrcNode() == 21) || (game_level == 23 && agent.getSrcNode() == 32)) {
                    sleepTime = 40;
                }
                else if((game_level == 19 && agent.getSrcNode() == 0) || (game_level == 19 && agent.getSrcNode() == 22) ||
                        (game_level == 21 && agent.getSrcNode() == 40) || (game_level == 21 && agent.getSrcNode() == 41)) {
                    sleepTime = 50;
                }
                if((game_level == 21 && agent.getSrcNode() == 21) || (game_level == 21 && agent.getSrcNode() == 32) ||
                        (game_level == 22 && agent.getSrcNode() == 21) || (game_level == 22 && agent.getSrcNode() == 32)) {
                    sleepTime = 25;
                }
            }
        }
    }

    /**
     * find to the closest pokemon
     * @param pokemons
     */
    private static void whereToGo(List<CL_Pokemon> pokemons) {
        for(CL_Pokemon pk1 : pokemons) {
            for(CL_Pokemon pk2 : pokemons) {
                double dist = pk1.getLocation().distance(pk2.getLocation());
                if (dist < 0.006) {
                    pk1.getClosePokemon().add(pk2);
                    pk2.getClosePokemon().add(pk1);
                }
            }
        }
    }

    /**
     * agent to pokemon
     */
    private static void nextOne() {
        while(!queue.isEmpty()) {
            T pop = queue.iterator().next();
            if(pop.getPokemon().getNxt() == null && pop.getAgent().get_curr_pokemon() == null) {
                pop.getAgent().set_curr_pokemon(pop.getPokemon());
                pop.getPokemon().setNxt(pop.getAgent());
                node_data n = arena.getGraph().getNode(pop.getPokemon().get_edge().getDest());
                pop.getAgent().setPath(_graph.shortestPath(pop.getAgent().getSrcNode(), pop.getPokemon().get_edge().getSrc()), n);
            }
            queue.poll();
        }
    }

    /**
     * choosing next node.
     * @param agent
     * @return  id of the node
     */
    private static int nextNode(CL_Agent agent) {
        if(agent.getPath().isEmpty()) return -1;
        if(agent.getPath().size() == 1) {
            edge_data ed = arena.getGraph().getEdge(agent.getSrcNode(), agent.getPath().get(0).getKey());
            if(((EdgeData) ed).getShort()) sleepTime = 30;
            if((agent.getSpeed() >= 5) && (ed.getWeight() < 2)) sleepTime = 30;
            return agent.getPath().get(0).getKey();
        }
        agent.setNextNode(agent.getPath().get(1).getKey());
        return agent.getPath().get(1).getKey();
    }

    /**
     * this class is for compare
     */
    private static class Compare implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) { return (int) (o1.getDist() - o2.getDist()); }
    }

    private static class T {

        // agent
        private CL_Agent agent;
        private CL_Pokemon pokemon;
        private double dist;
        // constructor
        public T(CL_Agent agent, CL_Pokemon pokemon, dw_graph_algorithms _graph) {
            this.agent = agent;
            this.pokemon = pokemon;
            this.dist = _graph.shortestPathDist(agent.getSrcNode(), pokemon.get_edge().getSrc());
        }

        public double getDist() { return this.dist; }
        public CL_Pokemon getPokemon() { return this.pokemon; }
        public CL_Agent getAgent() { return this.agent; }

    }
}
