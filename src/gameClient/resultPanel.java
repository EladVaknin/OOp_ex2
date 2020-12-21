package gameClient;

// imports
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 * this class is the panel window that show the menu for the beggining and the end of te game.
 */
public class resultPanel {

    private static ImageIcon img1 = new ImageIcon("./Sources/vg_logo3.png");
    private static ImageIcon img2 = new ImageIcon("./Sources/empty.png");

    /**
     *@return - the id.
     */
    public static String getId () {
        return (String)JOptionPane.showInputDialog(
                null,
                "Please enter your ID: ",
                "Enter ID",
                JOptionPane.QUESTION_MESSAGE,
                img1,
                null,
                0
        );
    }

    /**
     * @return - the level choosen by the user
     */
    public static String getGameLevel () {
        String[] options ={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        return (String) JOptionPane.showInputDialog(
                null,
                "Please select level: ",
                "Select Level",
                JOptionPane.QUESTION_MESSAGE,
                img2,
                options,
                options[0]
        );
    }

    // end of the game
    private ImageIcon img = new ImageIcon("./Sources/end.jpg");

    /**
     * return the results
     * @param level
     * @param moves
     * @param grade
     */
    public void FinalR(int level, int moves, int grade) {
        JOptionPane.showMessageDialog(new JFrame(),
                "Results:\nLevel number: " + level + "\nNumber of moves: " + moves + "\nFinal grade: " + grade,
                "Results",
                JOptionPane.INFORMATION_MESSAGE,
                img);
    }
}