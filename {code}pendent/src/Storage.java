import java.lang.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Storage {

    //opening component classes
    private Employee employee = new Employee();
    private Customer customer = new Customer();
    private Helper helper = new Helper();
    private Album album = new Album();
    private Rental rental = new Rental();
    private Game game = new Game();

    // "kind of" Storage
    private ArrayList<Album> albums = new ArrayList<>(Arrays.asList(
            new Album ("London Calling", "The Clash", 1980, 14.99),
            new Album ("Legend", "Bob Marley & The Wailers", 1984, 17.99),
            new Album ("The Dark Side of the Moon", "Pink Floyd", 1973, 24.99),
            new Album ("The Black Album", "Metallica", 1991, 19.99),
            new Album ("Blood Sugar Sex Magik", "Red Hot Chili Peppers", 1991, 18.99)));

    private ArrayList<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Bob", 1974, "1044 Randolph Street", 13457),
            new Employee("Jill", 1985, "3845 Rainbow Street", 14568),
            new Employee("Jack", 1934, "1453 Tilden Street", 16893),
            new Employee("Anna", 1959, "1854 Rose Avenue", 13578),
            new Employee("Sam", 1993, "1784 Sunrise Blvd", 12385),
            new Employee("Emanuel", 1992, "1039 Surfer's Paradise Lane", 12547)));

    private List<Game> games = new ArrayList<>(Arrays.asList(
            new Game( "Sonic: The Hedgehog", "Explore", 23, false),
            new Game( "Crash Bandicoot", "Racing", 24, false),
            new Game( "The Legend of Zelda", "Explore", 51, true),
            new Game ( "Prince of Persia", "Impossible", 33, false),
            new Game ( "Super Mario", "Classic", 32, false),
            new Game( "Street Fighter", "Fighting", 54, false),
            new Game( "Tekken", "Fighting", 29, false)));

    private ArrayList<Customer> customerList = new ArrayList<>(Arrays.asList(
            new Customer(1,"Vernita", "Silver"),
            new Customer(2,"Navya"),
            new Customer(3,"Drake"),
            new Customer(4,"Altan"),
            new Customer(666,"Karen"),
            new Customer(5,"Axel")));

    public ArrayList<Employee> getEmployees(){return employees;}
    public ArrayList<Customer> getCustomers() {
        return customerList;
    }

    public List<Game> getGames(){
        return games;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(){
        this.customer = customer;
    }
    //Game
    public void rentGame(){
        rental.rentGame();
    }
    //Customer
    //--------------------------------------------------------------------------//

    protected void addCustomer(){
       this.customerList.add(customer.addCustomer());
       System.out.println(customerList.toString());
    }

    protected void removeCustomer(){
       int removeId = helper.getInt("Enter the ID of the customer you want to remove.\nID: ");
       this.customerList.removeIf(customer -> customer.getCustomerId() == removeId);
       viewCustomer();
   }

     protected void viewCustomer(){
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
    }

    protected ArrayList<Membership> addMembership(){
        return this.customer.addMembership();
    }


    //--------------------------------------------------------------------------//
    protected void addEmployee(){
        this.employees.add(employee.addEmployee());
    }
    protected void removeEmployee() {
        String removeID = helper.getInput("Enter the ID of the employee you want to remove.\nEmployee ID: ");
        this.employees.removeIf(employee -> employee.getId().equals(removeID));
        System.out.println("Employee Removed\n");
    }
    protected void viewEmployee(){
        for (Employee employee : employees){
            System.out.println(employee.toString());
        }
    }
    //--------------------------------------------------------------------------//


    protected void addAlbum(){
        this.albums.add(album.addAlbum());
    }
    protected void removeAlbum(){
        String removeID = helper.getInput("Remove.\nAlbum ID: ");
        this.albums.removeIf(album -> album.getID().equals(removeID));
        System.out.println("Album Removed\n");
    }
    protected void rentAlbum(){
        String rental = helper.getInput("Rent\nAlbum ID: ");
        for (Album album : albums) {
            if (album.getID().equals(rental)) {
                album.setRentStatus(true);
                album.setRentedDate(LocalDate.now());
                System.out.println(">> "+ album.getTitle() + " by "+ album.getArtist()+ " - Rented");
            }
        }
    }
    protected void returnAlbum(){ // still needs to do calculation of price
        String rental = helper.getInput("Return\nAlbum ID: ");
        // int days = helper.getInt("Number of days rented: "); for hard day entry to calculate cost
        for (Album album : albums) {
            if (album.getID().equals(rental)) {
                long daysRented = DAYS.between(album.getRentedDate(),/* set to hard date for testing purposes*/ LocalDate.of(2020,10,5));
                double cost = album.getDailyRent() * daysRented;
                album.setRentStatus(false);
                album.setRentedDate(null);
                System.out.println(">> "+ album.getTitle() + " by "+ album.getArtist() + "Total Cost: " + cost + " SEK - Returned");
            }
        }
    }
    protected void viewAlbums(){
        for (Album album : albums) {
            System.out.println(album.toString());
        }
    }
    protected void searchAlbums(){
        int google = helper.getInt("Album Search\nYear: ");
        for (Album album : albums) {
            if (album.getYear() == google) {
                System.out.println(album.toString());
            }
        }
    }

    //--------------------------------------------------------------------------//

// Games
public void addNewGame() {
    int countArray = games.size();
    System.out.print("Title:  ");
    String newGameTitle = helper.input.nextLine();

    System.out.print("Genre:  ");
    String newGameGenre = helper.input.nextLine();

    System.out.print("Daily Rent Fee:  ");
    double newGameRentCost = helper.input.nextDouble();
    helper.input.nextLine();

    games.add( new Game(newGameTitle, newGameGenre, newGameRentCost));
    System.out.println("Game Added Successfully : " + games.toString());

    System.out.println("1) Add another game" + "\n" + "2) View all games" + "\n" + "3) Employee Menu");
    int userChoice = helper.input.nextInt();
    if (userChoice == 1) {
        addNewGame();
    } else if (userChoice == 2) {
        viewAll();
    } // else employeeMenu();

}
    public void removeGame() {
        System.out.println("Which game should be removed? ID:");
        String gameId = helper.input.nextLine();
        boolean contains = false;
        if (games.contains(gameId)) {
            contains = true;
            System.out.println("Are you sure you want to remove this game from the directory?" + "\n" + games.toString() + "\n" + "(Y/N)");
            String doubleCheck = helper.input.nextLine();
            if (doubleCheck.equalsIgnoreCase("y")) {
                games.remove(gameId);
                System.out.println("Game removed");
            } else {
                System.out.println("Okay, no problem. ");
//                dartController.menus.employeeMenu();
            }
        } else {
            System.out.println("That game doesn't seem to be in the directory.");
        }
        viewAll();
        //  System.out.println("Game has to be returned before it can be removed from the system.\n");
        // if (!contains) System.out.println("Couldn't find that game. Please make sure you enter the correct ID.\n");
        //  menus.employeeMenu();
    }

    public void viewAll(){
        System.out.println("Games:" + "\n");
        for (Game game : games) {
            System.out.println(game.toString());

        }
        System.out.println("1) Back to Employee Menu " + "\n" + "2) Back to Main Menu");
        Scanner userChoice = new Scanner(System.in);
        int whereTo = userChoice.nextInt();

        if (whereTo == 1){
            // menus.employeeMenu();
        } // else menus.mainMenu();
    }
}