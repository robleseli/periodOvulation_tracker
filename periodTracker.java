import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PeriodTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get the start date of the last period from the user
        System.out.print("Enter the start date of your last period (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        
        // Parse the start date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        
        // Calculate the average length of the menstrual cycle
        System.out.print("Enter the average length of your menstrual cycle (in days): ");
        int cycleLength = scanner.nextInt();
        
        // Get the length of the previous cycle
        System.out.print("Enter the length of your previous cycle (in days): ");
        int previousCycleLength = scanner.nextInt();
        
        // Calculate the average cycle length
        int averageCycleLength = (cycleLength + previousCycleLength) / 2;
        
        // Predict the next period and ovulation date
        LocalDate nextPeriodDate = startDate.plusDays(averageCycleLength);
        LocalDate ovulationDate = startDate.plusDays((averageCycleLength - 14) / 2); // Assuming ovulation occurs around day 14
        
        // Display the results
        System.out.println("Your next period is predicted to start on: " + nextPeriodDate.format(formatter));
        System.out.println("Your ovulation date is predicted to be: " + ovulationDate.format(formatter));
        
        scanner.close();
    }
}
