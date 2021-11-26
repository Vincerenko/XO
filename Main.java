package tictactoe;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Map<String, Integer> coordinateMap = new HashMap<>();
    private static final String input = "         ";
    private static String[] printArray = new String[9];
    private static final Scanner sc = new Scanner(System.in);
    private static String buffer = "O";

    public static void main(String[] args) {
       start();
    }

    private static void start(){
        print();
        while (checkDraw()) {
            System.out.println("Enter the coordinates:");
            String s = sc.nextLine();
            if (checkCorrectInput(s)) {
                secondPrint();
            }
        }
    }

    private static void secondStart() {
        System.out.println("Enter the coordinates:");
        String coordinate2 = sc.nextLine();
        checkCorrectInput(coordinate2);
        secondPrint();
    }

    private static boolean checkCorrectInput(String inputCoordinate) {
        if (chekFreeCell(inputCoordinate) && coordinateMap.containsKey(inputCoordinate) && buffer.equals("O")) {
            buffer = printArray[coordinateMap.get(inputCoordinate)] = "X";
            return true;
        }
        if (chekFreeCell(inputCoordinate) && coordinateMap.containsKey(inputCoordinate) && buffer.equals("X")) {
            buffer = printArray[coordinateMap.get(inputCoordinate)] = "O";
            return true;
        }
        return false;
    }

    private static boolean chekFreeCell(String inputCoord) {
        String[] s = inputCoord.split(" ");
        if (inputCoord.equals("one one") || inputCoord.equals("two two") || inputCoord.equals("three three")) {
            System.out.println("You should enter numbers!");
            return false;
        } else if (!s[0].equals("1") && !s[0].equals("2") && !s[0].equals("3")) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (!s[1].equals("1") && !s[1].equals("2") && !s[1].equals("3")) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (printArray[coordinateMap.get(inputCoord)].equals("X") || printArray[coordinateMap.get(inputCoord)].equals("O")) {
            System.out.println("This cell is occupied! Choose another one!");
            secondStart();
            return false;
        }
        return true;
    }

    private static void secondPrint() {
        System.out.println("---------");
        System.out.println("| " + printArray[0] + " " + printArray[1] + " " + printArray[2] + " |");
        System.out.println("| " + printArray[3] + " " + printArray[4] + " " + printArray[5] + " |");
        System.out.println("| " + printArray[6] + " " + printArray[7] + " " + printArray[8] + " |");
        System.out.println("---------");
    }

    private static void print() {
        printArray = input.split("");
        System.out.println("---------");
        System.out.println("| " + printArray[0] + " " + printArray[1] + " " + printArray[2] + " |");
        System.out.println("| " + printArray[3] + " " + printArray[4] + " " + printArray[5] + " |");
        System.out.println("| " + printArray[6] + " " + printArray[7] + " " + printArray[8] + " |");
        System.out.println("---------");
        initializationMap();
    }

    private static boolean checkDraw() {
        if (checkXWins() && check0Wins() && (checkImpossible(input) || chekCountOfXO(input))) {
            System.out.println("Impossible");
            return false;
        } else if (chekCountOfXO(input)) {
            System.out.println("Impossible");
            return false;
        } else if (!checkXWins() && !check0Wins()) {
            return true;
        } else if (checkXWins() && !check0Wins()) {
            System.out.println("X wins");
            return false;
        } else if (!checkXWins() && check0Wins()) {
            System.out.println("O wins");
            return false;
        } else if (!checkXWins() && !check0Wins() && checkImpossible(input)) {
            System.out.println("Game not finished");
            return true;
        }
        return true;
    }


    private static boolean checkXWins() {
        if (printArray[0].equals("X") && printArray[1].equals("X") && printArray[2].equals("X")) {
            return true;
        }
        if (printArray[3].equals("X") && printArray[4].equals("X") && printArray[5].equals("X")) {
            return true;
        }
        if (printArray[6].equals("X") && printArray[7].equals("X") && printArray[8].equals("X")) {
            return true;
        }
        if (printArray[0].equals("X") && printArray[3].equals("X") && printArray[6].equals("X")) {
            return true;
        }
        if (printArray[1].equals("X") && printArray[4].equals("X") && printArray[7].equals("X")) {
            return true;
        }
        if (printArray[2].equals("X") && printArray[5].equals("X") && printArray[8].equals("X")) {
            return true;
        }
        if (printArray[0].equals("X") && printArray[4].equals("X") && printArray[8].equals("X")) {
            return true;
        }
        if (printArray[2].equals("X") && printArray[4].equals("X") && printArray[6].equals("X")) {
            return true;
        }
        return false;
    }

    private static boolean check0Wins() {
        if (printArray[0].equals("O") && printArray[1].equals("O") && printArray[2].equals("O")) {
            return true;
        }
        if (printArray[3].equals("O") && printArray[4].equals("O") && printArray[5].equals("O")) {
            return true;
        }
        if (printArray[6].equals("O") && printArray[7].equals("O") && printArray[8].equals("O")) {
            return true;
        }
        if (printArray[0].equals("O") && printArray[3].equals("O") && printArray[6].equals("O")) {
            return true;
        }
        if (printArray[1].equals("O") && printArray[4].equals("O") && printArray[7].equals("O")) {
            return true;
        }
        if (printArray[2].equals("O") && printArray[5].equals("O") && printArray[8].equals("O")) {
            return true;
        }
        if (printArray[0].equals("O") && printArray[4].equals("O") && printArray[8].equals("O")) {
            return true;
        }
        if (printArray[2].equals("O") && printArray[4].equals("O") && printArray[6].equals("O")) {
            return true;
        }
        return false;
    }

    private static boolean checkImpossible(String input) {
        List<String> list = List.of(input.split(""));
        return list.stream().anyMatch(n -> n.equals(" "));
    }


    private static boolean chekCountOfXO(String input) {
        List<String> list = List.of(input.split(""));
        List<String> x = list.stream().filter(n -> n.equals("X")).collect(Collectors.toList());
        List<String> o = list.stream().filter(n -> n.equals("O")).collect(Collectors.toList());
        if (((x.size() - o.size()) == 2) || ((o.size() - x.size()) == 2)) {
            return true;
        }
        return false;
    }

    private static void initializationMap() {
        coordinateMap.put("1 1", 0);
        coordinateMap.put("1 2", 1);
        coordinateMap.put("1 3", 2);
        coordinateMap.put("2 1", 3);
        coordinateMap.put("2 2", 4);
        coordinateMap.put("2 3", 5);
        coordinateMap.put("3 1", 6);
        coordinateMap.put("3 2", 7);
        coordinateMap.put("3 3", 8);
    }
}
