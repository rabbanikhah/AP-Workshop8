import java.io.*;
import java.sql.SQLOutput;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.*;

public class Manager {
    private ArrayList<Note> notes = new ArrayList<>();

    public Manager() {
        //getting the previous notes from the "notes.txt" file
        if (get_previous_notes_from_file("notes.txt") != null){
            notes = get_previous_notes_from_file("notes.txt");
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
    public void export(int index, int counter){
        this.notes = get_previous_notes_from_file("notes.txt");
        FileWriter fileWriter = null;
        PrintWriter out = null;
        if (index < 1 || index > notes.size()){
            System.out.println("invalid index. please try again");
            return;
        }
        try {
            fileWriter = new FileWriter("exportedfile" + counter + ".txt");
            out = new PrintWriter(fileWriter);
            Note n = notes.get(index - 1);
            out.println(n.getTopic());
            out.println(n.getDate());
            out.println(n.getText());
            System.out.println("exported successfully. you can find it in " + "exportedfile" + counter + ".txt");
            out.close();
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("export was not successful. please try again");
        }
    }
    public void add_note(String topic, String text){
        if (check_for_repetitive_topic(topic))
            return;
        Note note = new Note(topic,return_current_date(),text);
        notes.add(note);
        //Writing the new arraylist of notes into the "notes.txt" file
        write_into_file("notes.txt");
        //printing a message
        System.out.println("A new note has been added");
    }
    public void remove(int index){
        this.notes = get_previous_notes_from_file("notes.txt");
        if (index <= 0 || index > notes.size()){
            System.out.println("invalid index! please try again");
            return;
        }
        this.notes.remove(index - 1);
        write_into_file("notes.txt");
        //printing a message
        System.out.println("the note has been removed");
        //A list of notes should be displayed from main function using "show_list_of_notes_from_file()"
    }
    public void show_Notes(int index){
        this.notes = get_previous_notes_from_file("notes.txt");
        if (index > notes.size() || index < 1)
            System.out.println("Invalid index! Please try again");
        else
            System.out.println(this.notes.get(index - 1).getText());
    }
    private boolean check_for_repetitive_topic(String topic){
        //checking if the topic s repetitive or not
        for (Note i: notes){
            if (i.getTopic().equals(topic)){
                System.out.println("repetitive topic!");
                System.out.println("Please try again with a different topic");
                return true;
            }
        }
        return false;
    }
    private String return_current_date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    public ArrayList<Note> get_previous_notes_from_file(String fileName){
        File file = new File(fileName);
        ArrayList<Note> noteArrayList = new ArrayList<>();
        if (file.length() > 0){
            try {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object o;
                if ((o = ois.readObject()) != null){
                    noteArrayList = (ArrayList<Note>) o;
                }
                fis.close();
                ois.close();
                return noteArrayList;
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    private void write_into_file(String fileName){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.notes);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void show_list_of_notes_from_file(){
        ArrayList<Note> noteArrayList = get_previous_notes_from_file("notes.txt");
        for (int i = 0; i < noteArrayList.size(); i++) {
            System.out.println(i+1 + ". " + noteArrayList.get(i).getTopic() + "\t" + noteArrayList.get(i).getDate());
        }
    }
}
