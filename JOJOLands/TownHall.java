/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JOJOLands;

import Graph.Location;

/**
 *
 * @author ASUS
 */
public class TownHall extends Location implements Action{

    public TownHall() {
        super("Town Hall");
    }

    public void action(TheWorld game) {
        displayMenu(game);
        String select = game.getSelection();
        System.out.println("=".repeat(70));
        switch (select.charAt(0)) {
            case '1':
                char loc_select = select.charAt(1);
                if ((loc_select - 'A' <= game.getMap().getEdge(game.getCurrentLocation()).size() - 1) && Character.isUpperCase(loc_select)) {
                    game.move(loc_select);
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                    action(game);
                }
                break;
            case '2':
                advanceToNextDay(game);
                break;
            case '3':
                game.saveGame();
                break;
            case '4':
                if (!game.getBackhistory().isEmpty()) {
                    game.back();
                } else if (game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if (game.getBackhistory().isEmpty() && game.getForwardhistory().isEmpty()) {
                    game.setExit(true);
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                }
                break;
            case '5':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.forward();
                } else if ((game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty())
                        || (!game.getBackhistory().isEmpty()) && game.getForwardhistory().isEmpty()) {
                    game.setExit(true);
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                }
                break;
            case '6':
                if (!game.getBackhistory().isEmpty() && !game.getForwardhistory().isEmpty()) {
                    game.setExit(true);
                } else {
                    System.out.println("Option "+select+" is not available. Please reselect.");
                }
                break;
            default:
                System.out.println("Option "+select+" is not available. Please reselect.");
        }
    }

    private void advanceToNextDay(TheWorld game) {
        game.setDay(game.getDay() + 1);
        game.displayDay(game.getDay());
    }

    private void displayMenu(TheWorld game) {
        System.out.println("Current Location: " + game.getCurrentLocation().getName());
        game.displayCurrentLocationOptions();
        int i = 2;
        System.out.printf("[%d] Advance to Next Day%n", i++);
        System.out.printf("[%d] Save Game%n", i++);
        if (!game.getBackhistory().isEmpty()) {
            System.out.printf("[%d] Back (%s)%n", i++, game.getBackhistory().peek().getName());
        }
        if (!game.getForwardhistory().isEmpty()) {
            System.out.printf("[%d] Forward (%s)%n", i++, game.getForwardhistory().peek().getName());
        }
        System.out.printf("[%d] Exit%n%n", i++);
    }
}
