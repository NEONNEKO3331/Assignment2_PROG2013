import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehicle {
    protected String vehicleNumber;
    protected String manufacturer;
    protected String model;
    protected double rentalRatePerDay;

    public Vehicle(String vehicleNumber, String manufacturer, String model, double rentalRatePerDay) {
        this.vehicleNumber = vehicleNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.rentalRatePerDay = rentalRatePerDay;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRentalRatePerDay() {
        return rentalRatePerDay;
    }

    public void setRentalRatePerDay(double rentalRatePerDay) {
        this.rentalRatePerDay = rentalRatePerDay;
    }

    public double calculateRentalCost(int days) {
        return rentalRatePerDay * days;
    }

    @Override
    public String toString() {
        return "Vehicle Number: " + vehicleNumber +
                "\nManufacturer: " + manufacturer +
                "\nModel: " + model +
                "\nRental Rate per Day: RM" + rentalRatePerDay;
    }
}

class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String vehicleNumber, String manufacturer, String model, double rentalRatePerDay, int numberOfDoors) {
        super(vehicleNumber, manufacturer, model, rentalRatePerDay);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (numberOfDoors > 2) {
            return super.calculateRentalCost(days) + 35 * days;
        }
        return super.calculateRentalCost(days);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nNumber of Doors: " + numberOfDoors;
    }
}

class Motorcycle extends Vehicle {
    private int engineCapacity;

    public Motorcycle(String vehicleNumber, String manufacturer, String model, double rentalRatePerDay,
            int engineCapacity) {
        super(vehicleNumber, manufacturer, model, rentalRatePerDay);
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int calculateTopSpeed() {
        return engineCapacity * 50;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (calculateTopSpeed() >= 300) {
            return super.calculateRentalCost(days) + 150 * days;
        }
        return super.calculateRentalCost(days);
    }

    @Override
    public String toString() {
        return super.toString() + "\nEngine Capacity: " + engineCapacity + "cc";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Predefined manufacturers and models for Cars
        String[] carManufacturers = { "Toyota", "Honda", "Ford", "Nissan" };
        String[][] carModels = {
                { "Corolla", "Camry" },
                { "Civic", "Accord" },
                { "Focus", "Mustang" },
                { "Sentra", "Altima" }
        };
        double carBaseRentalRate = 50.0;

        // Predefined manufacturers and models for Motorcycles
        String[] motorcycleManufacturers = { "Harley-Davidson", "Yamaha", "Suzuki", "Kawasaki" };
        String[][] motorcycleModels = {
                { "Street 500", "Sportster" },
                { "YZF-R3", "MT-07" },
                { "GSX-R750", "V-Strom 650" },
                { "Ninja 400", "Ninja 650" }
        };
        double motorcycleBaseRentalRate = 30.0;

        // Interactive menu
        int choice;
        do {
            System.out.println("========= Vehicle Rental System =========");
            System.out.println("1. Add Car");
            System.out.println("2. Add Motorcycle");
            System.out.println("3. View All Vehicles");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCar(scanner, vehicles, carManufacturers, carModels, carBaseRentalRate);
                    break;
                case 2:
                    addMotorcycle(scanner, vehicles, motorcycleManufacturers, motorcycleModels,
                            motorcycleBaseRentalRate);
                    break;
                case 3:
                    viewAllVehicles(scanner, vehicles);
                    break;
                case 4:
                    System.out.println("Exiting the Vehicle Rental System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void addCar(Scanner scanner, List<Vehicle> vehicles, String[] manufacturers, String[][] models,
            double baseRentalRate) {
        System.out.println("Adding a new car:");

        // Select manufacturer
        System.out.println("Select manufacturer:");
        for (int i = 0; i < manufacturers.length; i++) {
            System.out.println((i + 1) + ". " + manufacturers[i]);
        }
        int manufacturerChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (manufacturerChoice < 1 || manufacturerChoice > manufacturers.length) {
            System.out.println("Invalid manufacturer choice.");
            return;
        }

        String manufacturer = manufacturers[manufacturerChoice - 1];

        // Select model
        System.out.println("Select model:");
        String[] availableModels = models[manufacturerChoice - 1];
        for (int i = 0; i < availableModels.length; i++) {
            System.out.println((i + 1) + ". " + availableModels[i]);
        }
        int modelChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (modelChoice < 1 || modelChoice > availableModels.length) {
            System.out.println("Invalid model choice.");
            return;
        }

        String model = availableModels[modelChoice - 1];

        // Set rental rate per day with the fixed base rate for cars
        double rentalRatePerDay = baseRentalRate;

        Car car = new Car(manufacturer + "-" + model + "-" + vehicles.size(), manufacturer, model, rentalRatePerDay, 4);
        vehicles.add(car);
        System.out.println("Car added successfully!");
    }

    private static void addMotorcycle(Scanner scanner, List<Vehicle> vehicles, String[] manufacturers,
            String[][] models, double baseRentalRate) {
        System.out.println("Adding a new motorcycle:");

        // Select manufacturer
        System.out.println("Select manufacturer:");
        for (int i = 0; i < manufacturers.length; i++) {
            System.out.println((i + 1) + ". " + manufacturers[i]);
        }
        int manufacturerChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (manufacturerChoice < 1 || manufacturerChoice > manufacturers.length) {
            System.out.println("Invalid manufacturer choice.");
            return;
        }

        String manufacturer = manufacturers[manufacturerChoice - 1];

        // Select model
        System.out.println("Select model:");
        String[] availableModels = models[manufacturerChoice - 1];
        for (int i = 0; i < availableModels.length; i++) {
            System.out.println((i + 1) + ". " + availableModels[i]);
        }
        int modelChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (modelChoice < 1 || modelChoice > availableModels.length) {
            System.out.println("Invalid model choice.");
            return;
        }

        String model = availableModels[modelChoice - 1];

        // Set rental rate per day with the fixed base rate for motorcycles
        double rentalRatePerDay = baseRentalRate;

        Motorcycle motorcycle = new Motorcycle(manufacturer + "-" + model + "-" + vehicles.size(), manufacturer, model,
                rentalRatePerDay, 150);
        vehicles.add(motorcycle);
        System.out.println("Motorcycle added successfully!");
    }

    private static void viewAllVehicles(Scanner scanner, List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles to display.");
        } else {
            System.out.print("Enter rental days: ");
            int rentalDays = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.println("========= All Vehicles =========");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
                System.out
                        .println("Rental Cost (" + rentalDays + " days): RM" + vehicle.calculateRentalCost(rentalDays));
                if (vehicle instanceof Motorcycle) {
                    System.out.println("Top Speed: " + ((Motorcycle) vehicle).calculateTopSpeed() + " km/h");
                }
                System.out.println("------------------------");
            }
        }
    }
}
