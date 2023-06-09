/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResidentsData;

import java.util.ArrayList;
import java.util.List;
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
        String format = "| %2s | %-21s | %3s | %-6s | %-22s | %-17s | %-8s | %-8s | %-8s | %-9s | %-21s |%n";
        System.out.printf(border, "-".repeat(4), "-".repeat(23), "-".repeat(5), "-".repeat(8), "-".repeat(24),
                "-".repeat(19), "-".repeat(10), "-".repeat(10), "-".repeat(10), "-".repeat(11), "-".repeat(23) + "+");
        System.out.printf(format, "No", "Name", "Age", "Gender", "Stand", "Destructive Power", "Speed", "Range",
                "Stamina", "Precision", "Development Potential");
        System.out.printf(border, "-".repeat(4), "-".repeat(23), "-".repeat(5), "-".repeat(8), "-".repeat(24),
                "-".repeat(19), "-".repeat(10), "-".repeat(10), "-".repeat(10), "-".repeat(11), "-".repeat(23) + "+");
        for (int i = 0; i < residentList.size(); i++) {
            Resident resident = residentList.get(i);
            Stand stand = resident.getStand();
            if (stand != null) {
                System.out.printf(format, i + 1, resident.getName(), resident.getAge(), resident.getGender(),
                        stand.getName(), stand.getDestructivePower(), stand.getSpeed(), stand.getRange(),
                        stand.getStamina(), stand.getPrecision(), stand.getDevelopmentPotential());
            } else {
                System.out.printf(format, i + 1, resident.getName(), resident.getAge(), resident.getGender(), "N/A",
                        "Null", "Null", "Null", "Null", "Null", "Null");
            }
        }
        System.out.printf(border, "-".repeat(4), "-".repeat(23), "-".repeat(5), "-".repeat(8), "-".repeat(24),
                "-".repeat(19), "-".repeat(10), "-".repeat(10), "-".repeat(10), "-".repeat(11), "-".repeat(23) + "+");

    }

    public void Innermenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("[1] View Resident’s Profile");
            System.out.println("[2] Sort");
            System.out.println("[3] Exit\n");

            System.out.print("Select: ");
            String select = sc.nextLine();
            if (select == "" || select.matches("\\s+")) {
                System.out.println("Invalid input. Please reselect.");
                continue;
            }
            switch (select) {
                case "1":
                    viewResidentProfile();
                    break;
                case "2":
                    sort();
                    break;
                case "3":
                    exit = true;
                    System.out.println("=".repeat(70));
                    return;
                default:
                    System.out.println("Option " + select + " is not available. Please reselect.");
            }
        }
    }

    public void viewResidentProfile() {
        boolean getName = false;
        while (!getName) {
            System.out.print("Enter the resident's name: ");
            String resident_name = sc.nextLine();
            if (resident_name == "" || resident_name.matches("\\s+")) {
                System.out.println("Invalid input. Please reselect.");
                continue;
            }
            for (Resident resident : residentList) {
                if (resident.getName().equals(resident_name)) {
                    System.out.println(resident);
                    resident.viewOrderHistory();
                    getName = true;
                    break;
                }
            }
            if (!getName) {
                System.out.println(resident_name + " is not exist. Please reenter a name.");
            }
        }
    }

    public ArrayList<Resident> getResidentList() {
        return residentList;
    }

    public void sort() {
        String input_order;
        while (true) {
            System.out.print("Enter the sorting order (Field Name (ASC/DESC);): ");
            input_order = sc.nextLine();
            if (input_order.matches("(.+) \\((.+)\\);")) {
                break;
            }
            System.out.println("Wrong input format. Please try again. ");
        }
        String[] sort_order = input_order.split(";");

        // Create a list of sort criteria
        List<SortCriteria> criteriaList = new ArrayList<>();
        for (String criteria : sort_order) {
            String[] tokens = criteria.trim().split("\\s*\\(\\s*|\\s*\\)\\s*");
            String field = tokens[0].split("\\(")[0].toLowerCase();
            String sortOrder = tokens[1].toLowerCase();
            criteriaList.add(new SortCriteria(field, sortOrder));
        }

        // Sort the residentList based on the sorting criteria using Quick Sort
        quickSort(residentList, new ResidentComparator(criteriaList));

        // Display the sorted resident information
        viewResidentInfo();
    }

    private void quickSort(List<Resident> list, ResidentComparator comparator) {
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private void quickSort(List<Resident> list, int low, int high, ResidentComparator comparator) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, comparator);
            quickSort(list, low, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, high, comparator);
        }
    }

    private int partition(List<Resident> list, int low, int high, ResidentComparator comparator) {
        Resident pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<Resident> list, int i, int j) {
        Resident temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
