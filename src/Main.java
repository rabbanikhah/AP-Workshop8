import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Manager manager = new Manager();
        Scanner in = new Scanner(System.in);
        Scanner in1 = new Scanner(System.in);
        int counter = 0; // it counts the number of exports to create a different file every time and prevent overwriting files

        String command = "-1";

        while (!command.equals("5")){
            System.out.println("************************************************************");
            System.out.println("Main Menu");
            System.out.println("1. add");
            System.out.println("2. remove");
            System.out.println("3. notes");
            System.out.println("4. export");
            System.out.println("5. exit");

            command = in1.nextLine();

            if (command.equals("1")){
                String topic,note;
                System.out.println("You can return to the main menu by typing \"###\"");
                System.out.print("Write your topic: ");
                topic = in1.nextLine();
                if (topic.contains("###")){
                    continue;
                }
                System.out.print("Write your note: ");
                note = in1.nextLine();
                if (note.contains("###")){
                    continue;
                }
                manager.add_note(topic, note);
            }

            else if (command.equals("2")){
                manager.show_list_of_notes_from_file();
                System.out.println("Enter the index: (You can return to the main menu by inserting an index less than 1)");

                try {
                    int index = in.nextInt();
                    if (index < 1){
                        continue;
                    }
                    manager.remove(index);
                }catch (InputMismatchException e){
                    in.nextLine();
                    System.out.println("invalid input! Please try again");
                }
            }

            else if (command.equals("3")){
                manager.show_list_of_notes_from_file();
                System.out.println("Enter the index: (You can return to the main menu by inserting an index less than 1)");
                try {
                    int index = in.nextInt();
                    if (index < 1){
                        continue;
                    }
                    manager.show_Notes(index);
                }catch (InputMismatchException e){
                    in.nextLine();
                    System.out.println("invalid input! Please try again");
                }

            }

            else if (command.equals("4")){
                manager.show_list_of_notes_from_file();
                System.out.println("Enter the index: (You can return to the main menu by inserting an index less than 1)");
                try {
                    int index = in.nextInt();
                    if (index < 1){
                        continue;
                    }
                    manager.export(index,counter);
                    counter++;
                }catch (InputMismatchException e){
                    in.nextLine();
                    System.out.println("invalid input! Please try again");
                }
            }
            else if (command.equals("5")){
                System.exit(0);
            }
            else
                System.out.println("Invalid input. Please try again");
        }
        manager.add_note("test1", "this is just a test 1");
        manager.add_note("test2", "this is just a test 2");
        manager.add_note("test3", "this is just a test 3");

        for (Note i: manager.get_previous_notes_from_file("notes.txt")){
            System.out.println(i.getTopic());
        }
        System.out.println("after_removing");
        // removing the second note
        manager.remove(1);
        for (Note i: manager.get_previous_notes_from_file("notes.txt")){
            System.out.println(i.getTopic());
        }
        // the third and second note has been removed
    }
}