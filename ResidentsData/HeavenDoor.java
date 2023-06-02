/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class HeavenDoor {

    private String name;
    private ArrayList<Resident> residentList;
    private Scanner sc = new Scanner(System.in);

    public HeavenDoor(String name) {
        this.name = name;
        this.residentList = CSVreader.readResident(name);
    }

    public void viewResidentInfo() {
        System.out.println("Resident Information in " + name);
        String border = "+%s+%s+%s+%s+%s+%s+%s+%s+%s+%s+%s%n";
        String format = "| %2s | %-21s | %3s | %-6s | %-20s | %-17s | %-5s | %-5s | %-7s | %-9s | %-21s |%n";
        System.out.printf(border, "-".repeat(4), "-".repeat(23), "-".repeat(5), "-".repeat(8), "-".repeat(22), "-".repeat(19), "-".repeat(7), "-".repeat(7), "-".repeat(9), "-".repeat(11), "-".repeat(23) + "+");
        System.out.printf(format, "No", "Name", "Age", "Gender", "Stand", "Destructive Power", "Speed", "Range", "Stamina", "Precision", "Development Potential");
        System.out.printf(border, "-".repeat(4), "-".repeat(23), "-".repeat(5), "-".repeat(8), "-".repeat(22), "-".repeat(19), "-".repeat(7), "-".repeat(7), "-".repeat(9), "-".repeat(11), "-".repeat(23) + "+");
        for (int i = 0; i < residentList.size(); i++) {
            Resident resident = residentList.get(i);
            Stand stand = resident.getStand();
            if (stand != null) {
                System.out.printf(format, i + 1, resident.getName(), resident.getAge(), resident.getGender(), stand.getName(), stand.getDestructivePower(), stand.getSpeed(), stand.getRange(), stand.getStamina(), stand.getPrecision(), stand.getDevelopmentPotential());
            } else {
                System.out.printf(format, i + 1, resident.getName(), resident.getAge(), resident.getGender(), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
            }
        }
        System.out.printf(border, "-".repeat(4), "-".repeat(23), "-".repeat(5), "-".repeat(8), "-".repeat(22), "-".repeat(19), "-".repeat(7), "-".repeat(7), "-".repeat(9), "-".repeat(11), "-".repeat(23) + "+");
    }

    public void viewResidentProfile() {
        boolean getName = false;
        while (!getName) {
            System.out.print("Enter the resident's name: ");
            String resident_name = sc.nextLine();
            for (Resident resident : residentList) {
                if (resident.getName().equals(resident_name)) {
                    System.out.println(resident);
                    getName = true;
                    break;
                }
            }
            if (!getName) {
                System.out.println(resident_name + " is not exist. Please reenter a name.");
            }
        }
    }

    public void sort() {
        System.out.print("Enter the sorting order: ");
        String input_order = sc.nextLine();
        String[] sort_order = input_order.split(";");
        for (String string : sort_order) {
            String[] temp=string.split("\\s");
            for (String string1 : temp) {
                System.out.println(string1);
            }
            System.out.println(sort_order.length);
        }
    }
}