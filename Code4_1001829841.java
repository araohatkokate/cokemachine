/*
* Araohat Kokate 1001829841
 */
package code4_1001829841;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Code4_1001829841
{

    public static String displayMoney(int ch)
    {
        String dollars = String.valueOf(ch / 100);
        String cents = String.valueOf(ch % 100);
        return "$" + dollars + "." + (cents.length() == 1 ? "0" : "") + cents;
    }

    public static int CokeMenu(String b)
    {
        Scanner in = new Scanner(System.in);
        int choice;
        do
        {
            System.out.printf("\n%s\n", b);
            System.out.printf("%n0. Walk away%n1. Buy a Coke%n2. Restock Machine%n3. Add change%n4. Display Machine Info%n5. Update Machine Name%n6. Update Coke Price%n");
            System.out.print("Choice? ");
            try
            {
                choice = in.nextInt();
            }
            catch (Exception e)
            {
                choice = -1;
                in.nextLine();
            }
            if (choice < 0 || choice > 6)
            {
                System.out.println();
                System.out.println("Invalid menu choice. Please choose again ");
            }
        }
        while (choice < 0 || choice > 6);
        return choice;
    }

    public static void ReadFile(String takename, ArrayList<CokeMachine> newList)
    {
        File FH = new File(takename);
        Scanner infileread = null;
        try
        {
            infileread = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("%s file name does not exist...exiting\n", takename);
            System.exit(0);
        }

        while (infileread.hasNextLine())
        {
            String temp[] = infileread.nextLine().split("[|]");
            /* if (i > 0)
            {
                a[i] = String.valueOf(temp[i]);
            }
            else
            {
                a[i] = temp[i];
            }*/
            newList.add(new CokeMachine(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
        }

        /*  String store[] = infileread.nextLine().split("[ ]");
        for (String e : store)
        {
            newList.add(e);
        }*/
        infileread.close();
    }

    public static int MachineMenu(ArrayList<CokeMachine> machinenames)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Pick a Coke Machine\n\n");
        int choice, i = 0, k = 0;
        //System.out.println();
        //System.out.println("CSE 1325 CokeMachine");
        //System.out.printf("0. Walk away%n1. Buy a Coke%n2. Restock Machine%n3. Add change%n4. Display Machine Info%n5. Update Machine Name%n6. Update Coke Price%n");
        //System.out.print("Choice? ");
        //return choice;
        do
        {
            System.out.printf("%d. Exit\n", k);
            for (i = 0; i < machinenames.size(); i++)
            {
                System.out.printf("%d. %s\n", i + 1, machinenames.get(i).getMachineName());
            }
            System.out.printf("%d. Add new Machine\n", i + 1);
            System.out.print("Choice? ");
            try
            {
                choice = in.nextInt();
            }
            catch (Exception e)
            {
                choice = -1;
                in.nextLine();
            }
            if (choice < 0 || choice > (machinenames.size() + 1))
            {
                System.out.println();
                System.out.println("Invalid menu choice. Please choose again ");
            }
            /* else if(choice == (machinenames.size() + 1))
            {
                machinenames.add(new CokeMachine());
            }*/
        }
        while (choice < 0 || choice > (machinenames.size() + 1));
        return choice;
    }

    public static void OutputFile(String output, ArrayList<CokeMachine> machinenames1) throws FileNotFoundException
    {
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(output);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("unable to write output file");
            System.out.printf("Exception: %s\n\n", e.getMessage());
            throw (e);
        }
        for (CokeMachine a : machinenames1)
        {
            out.printf("%s|%d|%d|%d\n", a.getMachineName(), a.getCokePrice(), a.getChangeLevel(), a.getInventoryLevel());
        }
        out.close();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);
        CokeMachine MyCokeMachine = new CokeMachine("CSE 1325 CokeMachine", 50, 500, 100);
        //CokeMachine newCokeMachine = new CokeMachine();
        int input1, input, entered_payment = 0, enter_product = 0, temp = 0, enter_change = 0, setcokeprice = 0;
        String inputfilename = " ", outputfilename = " ", storename, new_machinename;
        ArrayList<CokeMachine> SetOfCokeMachines = new ArrayList<>();
        if (args.length != 2)
        {
            System.out.println("Missing command line parameters - - Usage : INPUTFILENAME= OUTPUTFILENAME=");
            System.exit(0);
        }
        else if (args.length == 2)
        {
            inputfilename = args[0].substring(args[0].indexOf('=') + 1);
            outputfilename = args[1].substring(args[1].indexOf('=') + 1);
        }
        ReadFile(inputfilename, SetOfCokeMachines);
        input1 = MachineMenu(SetOfCokeMachines);
        while (input1 != 0)
        {
            if (input1 == (SetOfCokeMachines.size() + 1))
            {
                SetOfCokeMachines.add(new CokeMachine());
                //input1 = MachineMenu(SetOfCokeMachines);//this wasn't here originally
            }
            else
            {
                //SetOfCokeMachines.set((input1-1), MyCokeMachine);//MyCokeMachine.setMachineName(String.valueOf(SetOfCokeMachines.get(input1-1)));
                MyCokeMachine = SetOfCokeMachines.get(input1 - 1);
                do
                {
                    //System.out.printf("\n%s\n", MyCokeMachine.getMachineName());
                    String a = MyCokeMachine.getMachineName();
                    input = CokeMenu(a);
                    switch (input)
                    {
                        case 0:
                            if (MyCokeMachine.getNumberOfCokesSold() > 0)
                            {
                                System.out.println("Bye - enjoy your Coke");
                            }
                            else
                            {
                                System.out.println("Are you sure you aren't really THIRSTY and need a coke?");
                                System.out.println();
                            }
                            break;
                        case 1:
                            if (MyCokeMachine.getInventoryLevel() == 0)
                            {
                                System.out.println("The Coke Machine is out of coke");
                            }
                            else
                            {

                                System.out.println("A Coke costs " + displayMoney(MyCokeMachine.getCokePrice()));
                                System.out.print("Insert payment ");
                                entered_payment = in.nextInt();
                                in.nextLine();
                                CokeMachine.ACTION var = MyCokeMachine.buyACoke(entered_payment);
                                switch (var)
                                {
                                    case DISPENSECHANGE:
                                        System.out.println("Here's your Coke and your change of " + displayMoney(MyCokeMachine.getChangeDispensed()));
                                        break;
                                    case INSUFFICIENTCHANGE:
                                        System.out.println("Unable to give change at this time...returning your payment");
                                        break;
                                    case INSUFFICIENTFUNDS:
                                        System.out.println("Insufficient payment...returning your payment");
                                        break;
                                    case EXACTPAYMENT:
                                        System.out.println("Thank you for exact payment.\nHere's your Coke");
                                        break;
                                    case NOINVENTORY:
                                        System.out.println("Unable to sell a Coke - call 1800WEDONTCARE to register a complaint...returning your payment");
                                        break;
                                    default:
                                        System.out.println("System not working");
                                        break;
                                }
                            }
                            break;
                        case 2:

                            System.out.print("How much product are you adding to the machine? ");
                            enter_product = in.nextInt();
                            in.nextLine();
                            if (MyCokeMachine.incrementInventoryLevel(enter_product))
                            {
                                System.out.println("Your machine has been restocked");
                                System.out.println("Your inventory level is " + MyCokeMachine.getInventoryLevel());
                                System.out.println();
                            }
                            else if (!(MyCokeMachine.incrementInventoryLevel(enter_product)))
                            {
                                System.out.println("You have exceeded your machine's max capacity - no inventory was added");
                                System.out.println("Your inventory level is " + MyCokeMachine.getInventoryLevel());
                                System.out.println();
                            }

                            break;
                        case 3:

                            System.out.print("How much change are you adding to the machine? ");
                            enter_change = in.nextInt();
                            in.nextLine();
                            System.out.println();
                            if (MyCokeMachine.incrementChangelevel(enter_change))
                            {
                                System.out.println("Your change level has been updated");
                                System.out.println("Your current change level is " + displayMoney(MyCokeMachine.getChangeLevel()) + " with a max capacity of " + displayMoney(MyCokeMachine.getMaxChangecapacity()));
                                System.out.println();
                            }
                            else if (!MyCokeMachine.incrementChangelevel(enter_change))
                            {
                                System.out.println("You exceeded your machine's max change capacity - no change added");
                                System.out.println("Your current change level is " + displayMoney(MyCokeMachine.getChangeLevel()) + " with a max capacity of " + displayMoney(MyCokeMachine.getMaxChangecapacity()));
                                System.out.println();
                            }
                            break;
                        case 4:
                            System.out.println(MyCokeMachine);
                            break;
                        case 5:
                            System.out.print("Enter a new machine name ");
                            new_machinename = in.nextLine();
                            MyCokeMachine.setMachineName(new_machinename);
                            System.out.println("Machine name has been updated");
                            break;
                        case 6:
                            System.out.println("Enter a new Coke price");
                            setcokeprice = in.nextInt();
                            in.nextLine();
                            MyCokeMachine.setCokePrice(setcokeprice);
                            System.out.println("Coke Price has been updated");
                            break;
                        default:
                            System.out.println("System not working");
                    }
                }
                while (input != 0);
            }
            input1 = MachineMenu(SetOfCokeMachines);
        }
        OutputFile(outputfilename, SetOfCokeMachines);
        System.out.printf("%d Coke(s) were sold today!\n", CokeMachine.getNumberOfCokesSold());
    }

}

