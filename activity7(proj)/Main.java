import java.io.IOException;
class Main {
    public static void main(String[] args) throws IOException {
        UserInterface ui = new UserInterface();
        while(true){
            ui.interact();
        }
    }
}