package view;

import java.util.*;

public class TextMenu {
    private final Map<String, Command> commands;
    public TextMenu(){ this.commands = new HashMap<>(); }

    public void addCommand(Command command){ commands.put(command.getKey(), command); }

    private void printMenu(){
        for (Command command: commands.values()){
            String line = String.format("%5s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public Collection<Command> getCommands() {  return commands.values(); }

    @SuppressWarnings("InfiniteLoopStatement")
    public void show(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            printMenu();
            System.out.print("\nInput the option: ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if (command == null){
                System.out.println("Invalid option!");
                continue;
            }
            command.execute();
        }
    }
}
