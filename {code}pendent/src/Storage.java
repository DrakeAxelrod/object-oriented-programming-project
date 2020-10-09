import java.util.*;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Storage {

    private Employee employee = new Employee();
    private Customer customer = new Customer();
    private Input input = Input.getInstance();
    private Album album = new Album();
    private Inventory inventory = new Inventory();
    private Rental rental = new Rental();
    private Game game = new Game();
    private Rating rating = new Rating();
    private Message message=new Message();

    // "kind of" Storage

    private ArrayList<Album> albums = new ArrayList<>(Arrays.asList(
            new Album ("London Calling", "The Clash", 1980, 14.99, false, null),
            new Album ("Legend", "Bob Marley & The Wailers", 1984, 17.99, true, LocalDate.of( 2020 , 8 , 23 )),
            new Album ("The Dark Side of the Moon", "Pink Floyd", 1973, 24.99, false, null),
            new Album ("The Black Album", "Metallica", 1991, 19.99, true,LocalDate.of( 2020 , 8 , 23 )),
            new Album ("Blood Sugar Sex Magik", "Red Hot Chili Peppers", 1991, 18.99, false, null)));

    private ArrayList<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Bob", 1974, "1044 Randolph Street", 13457),
            new Employee("Jill", 1985, "3845 Rainbow Street", 14568),
            new Employee("Jack", 1934, "1453 Tilden Street", 16893),
            new Employee("Anna", 1959, "1854 Rose Avenue", 13578),
            new Employee("Sam", 1993, "1784 Sunrise Blvd", 12385),
            new Employee("Emanuel", 1992, "1039 Surfer's Paradise Lane", 12547)));

    private ArrayList<Game> games = new ArrayList<>(Arrays.asList(
            new Game( "Sonic: The Hedgehog", "Explore", 18.99, 1857, false, null),
            new Game( "Crash Bandicoot", "Racing", 17.59, 1957, false, null),
            new Game( "The Legend of Zelda", "Explore", 12.29, 1874, true, LocalDate.of(2020, 8, 20)),
            new Game ( "Prince of Persia", "Impossible", 15.39, 1984, false, null),
            new Game ( "Super Mario", "Classic", 18.99, 1999, false, null),
            new Game( "Street Fighter", "Fighting", 11.99, 1991, true, LocalDate.of(2020, 8, 20)),
            new Game( "Tekken", "Fighting", 17.99, 1932, false, null)));

    private ArrayList<Customer> customerList = new ArrayList<>(Arrays.asList(
            new Customer("Vernita", new Membership("Silver", 0)),
            new Customer("Navya", new Membership(null, 0)),
            new Customer("Drake", new Membership(null, 0)),
            new Customer("Altan", new Membership("Silver", 0)),
            new Customer("Karen", new Membership(null, 0)),
            new Customer("Axel", new Membership("Gold", 0))));

    private ArrayList<Rental> rentalHistory = new ArrayList<>(Arrays.asList());

    public ArrayList<Rental> getRentalHistory() {
        return rentalHistory;
    }

    public ArrayList<Employee> getEmployees(){return employees;}

    public ArrayList<Customer> getCustomers() {
        return customerList;
    }

    public ArrayList<Game> getGames(){
        return games;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(){
        this.customer = customer;
    }

    public void rentGame(){
        rental.rentGame(getGames());
    }

    public void returnGame() {
        String name = input.getInput("Hiya! What is your name, customer?  ");
        boolean contains = false;
        for (Customer customer : customerList) {
           if (customer.getName().equalsIgnoreCase(name)){
               contains = true;
               viewGames();
               Rental newTransaction = rental.returnGame(customer.getId(), getGames());
               getRentalHistory().add(newTransaction);
           }
        }
        if (!contains){
            System.out.println("That customer doesn't exist on our database, please try again.");
            returnGame();
        }
    }

    public void viewTransactions() {
        for (Rental rental : getRentalHistory()){
            System.out.println(rental);
        }
    }

    public void totalProfit(){
        double profit = rental.getRentalIncome();
        System.out.println("The total profit is " + profit + "SEK");
    }

    public void ratingAverage(){
        int average = 0;
        System.out.println("The average rating is " + average);
    }

    public void addCustomer(){
       this.customerList.add(customer.addCustomer());
       System.out.println(customerList.toString());
    }

    public void removeCustomer(){
       int removeId = input.getInt("Enter the ID of the customer you want to remove.\nID: ");
       this.customerList.removeIf(customer -> customer.getId().equals(removeId));
       viewCustomer();
   }

    public void viewCustomer(){
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
    }

   // public ArrayList<Membership> addMembership(){
   //     return this.customer.addMembership(getCustomers());
   // }
    public ArrayList<Membership> addMembership(){
        return this.customer.addMembership(getCustomers());
    }

    public ArrayList<Membership> upgradeMembership(){
        return this.customer.upgradeMembership(getCustomers());
    }

    public void addEmployee(){
        this.employees.add(employee.addEmployee());
    }
    public void removeEmployee() {
        String removeID = input.getInput("Enter the ID of the employee you want to remove."+input.EOL+"Employee ID: ");
        this.employees.removeIf(employee -> employee.getId().equals(removeID));
        System.out.println("Employee Removed"+input.EOL);
    }
    public void viewEmployee(){
        for (Employee employee : employees){
            System.out.println(employee.toString());
        }
    }

    public void addAlbum(){
        this.albums.add(album.addAlbum());
    }

    public void removeAlbum(){
        String removeID = input.getInput("Remove."+input.EOL+"Album ID: ");
        this.albums.removeIf(album -> album.getID().equals(removeID));
        System.out.println("Album Removed"+input.EOL);
    }


    public void rentAlbum(){
        rental.rentAlbum(getAlbums());
    }

    public void returnAlbum() {
        String name = input.getInput("Hiya! What is your name, customer?  ");
        boolean contains = false;
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name)){
                contains = true;
                viewGames();
                Rental newTransaction = rental.returnGame(customer.getId(), getGames());
                getRentalHistory().add(newTransaction);
            }
        }
        if (!contains){
            System.out.println("That customer doesn't exist on our database, please try again.");
            returnGame();
        }
    }

    public void viewAlbums(){
        albums.sort(Comparator.comparingInt(Album::getYear));
        Collections.reverse(albums);
        for (Album album : albums) {
            System.out.println(album.toString());
        }
    }

    public void searchAlbums(){
        int google = input.getInt("Album Search"+input.EOL+"Year: ");
        for (Album album : albums) {
            if (album.getYear() == google) {
                System.out.println(album.toString());
            }
        }
    }

    public void viewAlbumsByRating(){
        albums.sort(Comparator.comparingDouble(Album::getRating));
        Collections.reverse(albums);
        for (Album album : albums) {
            System.out.println(album.toString());
        }
    }

public void addNewGame() {
    int countArray = games.size();
    System.out.print("Title:  ");
    String newGameTitle = input.input.nextLine();

    System.out.print("Genre:  ");
    String newGameGenre = input.input.nextLine();

    System.out.print("Year:  ");
    int newGameYear = input.input.nextInt();

    System.out.print("Daily Rent Fee:  ");
    double newGameRentCost = input.input.nextDouble();
    input.input.nextLine();

    games.add( new Game(newGameTitle, newGameGenre, newGameRentCost, newGameYear));
    System.out.println("Game Added Successfully : " + games.toString());

    System.out.println("1) Add another game" + input.EOL + "2) View all games" + input.EOL + "3) Employee Menu");
    int userChoice = input.input.nextInt();
    if (userChoice == 1) {
        addNewGame();
    } else if (userChoice == 2) {
        viewGames();
    }
}

    public void removeGame() {
        System.out.println("Which game should be removed? ID:");
        String gameId = input.input.nextLine();
        boolean contains = false;
        if (games.contains(gameId)) {
            contains = true;
            System.out.println("Are you sure you want to remove this game from the directory?" + input.EOL + games.toString() + input.EOL + "(Y/N)");
            String doubleCheck = input.input.nextLine();
            if (doubleCheck.equalsIgnoreCase("y")) {
                games.remove(gameId);
                System.out.println("Game removed");
            } else {
                System.out.println("Okay, no problem. ");
            }
        } else {
            System.out.println("That game doesn't seem to be in the directory.");
        }
        viewGames();
    }

    public void viewGames() {
        games.sort(Comparator.comparingInt(Game::getYear));
        Collections.reverse(games);
        for (Game game : games) {
            System.out.println(game.toString());
        }
    }
    public void searchGames(){
        String google = input.getInput("Game Search"+input.EOL+"Genre: ");
        for (Game game : games) {
            if (game.getGenre().equalsIgnoreCase(google)) {
                System.out.println(game.toString());
            }
        }
    }

    public void viewGamesByRating(){
        games.sort(Comparator.comparingDouble(Game::getRating));
        Collections.reverse(games);
        for (Game game : games) {
            System.out.println(game.toString());
        }
    }


    private ArrayList<Message> customerMessages=new ArrayList<>();


    public void sendMessage() {
        viewCustomer();
        String recipientId= input.getInput(input.EOL+"enter the customer ID of the person you want to send message to:  ");
        for (Customer customer : customerList) {
            if (customer.getId().equalsIgnoreCase(recipientId)) {
                String senderID = input.getInput("Type your ID: ");
                if (customer.getId().equalsIgnoreCase(senderID)) {

                    String senderName = input.getInput("Type your Name: ");
                    String subject = input.getInput("Type your Title: ");
                    String body = input.getInput("Type your message: ");
                    Message newMessage = new Message(subject, body, senderID, senderName);
                    System.out.println("Your message has been sent.");

                    customer.getInbox().add(newMessage);
                }
            }
        }
    }

    public void viewMessages() {
        String name = input.getInput("Type your name to view your inbox: ");
        for (Customer reader : customerList) {
            if (reader.getName().equalsIgnoreCase(name) && reader.getInbox().size() != 0) {
                Collections.reverse(reader.getInbox());
                System.out.print(input.EOL + ">> List of messages in order received <<");
                customer.viewMessages(reader);
                customer.getReadStatus();

                Collections.reverse(reader.getInbox());
            }else if (reader.getName().equalsIgnoreCase(name) && reader.getInbox().size() == 0){
                System.out.println(input.EOL + "No messages to view.");
            }
        }
    }

    public void removeMessages(){
        viewMessages();
        String removeMessage=input.getInput("Enter the message ID you want to delete: ");
        for (Customer customer: customerList) {
            customer.getInbox().removeIf(message -> message.getMessageId().equalsIgnoreCase(removeMessage));
        }
        System.out.println("The message has been deleted.");
        viewMessages();
    }
}