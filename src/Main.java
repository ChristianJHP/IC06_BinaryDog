import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner sc = new Scanner(System.in);

        String choice, name, breed;
        int age, count = 0;

        Dog[] dogPound = new Dog[10];

        // Reading from binary file
        File binaryFile = new File("Dogs.dat");
        // Check to see if the file exists AND non-zero Size
        System.out.println("Previously saved dogs from binary file");
        if (binaryFile.exists() && binaryFile.length() > 1L)
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                // Read array into dogPound
                // readObject returns Object datatype
                // Dog[] = Object
                dogPound = (Dog[]) fileReader.readObject();
                // Loop through the array and print objects
                while (dogPound[count]!=null)
                {
                    System.out.println(dogPound[count++]);
                }

                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: "+e.getMessage());
            }
        }
        else
            System.out.println("[None, please enter new dog data]");
        //ObjectInputStream

        do
        {
            System.out.println("Please enter dog's name (or \"quit\" to exit)");
            name=sc.nextLine();
            if (name.equalsIgnoreCase("quit"))
                break;
            System.out.println("Please enter dog's breed ");
            breed=sc.nextLine();
            System.out.println("Please enter dog's age ");
            age=sc.nextInt();

            // Create new dog object\
            // Create new dog, add to array, increment count
            // Postfix operator = count++
            dogPound[count++] = new Dog(name, breed, age);


            // Get rid of \n
            sc.nextLine();

        } while (true);

        // After user enters quit, write the dogPound array to the binary file
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
