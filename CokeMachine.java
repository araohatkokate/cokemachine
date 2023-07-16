/*
 * Araohat Kokate 1001829841
 */
package code4_1001829841;

public class CokeMachine
{

    private String machineName;
    private int changeLevel;
    private int maxChangeCapacity = 5000;
    private int inventoryLevel;
    private int maxInventoryCapacity = 100;
    private int CokePrice;
    private int changeDispensed;
    private static int numberOfCokesSold = 0;

    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT, NOINVENTORY
    }

    public CokeMachine(String name, int cost, int change, int inventory)
    {
        machineName = name;
        CokePrice = cost;
        changeLevel = change;
        inventoryLevel = inventory;
    }

    public CokeMachine()
    {
        machineName = "New Machine";
        CokePrice = 50;
        changeLevel = 500;
        inventoryLevel = 100;
    }

    public String getMachineName()
    {
        return machineName;
    }

    public int getChangeLevel()
    {
        return changeLevel;
    }

    public int getMaxChangecapacity()
    {
        return maxChangeCapacity;
    }

    public int getInventoryLevel()
    {
        return inventoryLevel;
    }

    public int getMaxInventoryLevel()
    {
        return maxInventoryCapacity;
    }

    public int getCokePrice()
    {
        return CokePrice;
    }

    public int getChangeDispensed()
    {
        return changeDispensed;
    }

    public static int getNumberOfCokesSold()
    {
        return numberOfCokesSold;
    }

    public void setMachineName(String s)
    {
        machineName = s;
    }

    public void setCokePrice(int a)
    {
        CokePrice = a;
    }

    public boolean incrementInventoryLevel(int amounttoAdd)
    {
        boolean flag = false;
        if (inventoryLevel + amounttoAdd <= maxInventoryCapacity)
        {
            inventoryLevel = inventoryLevel + amounttoAdd;
            flag = true;
        }

        else if (inventoryLevel + amounttoAdd > maxInventoryCapacity)
        {
            flag = false;
        }
        return flag;
    }

    public boolean incrementChangelevel(int amounttoAdd)
    {
        boolean flag = false;
        if (changeLevel + amounttoAdd <= maxChangeCapacity)
        {
            changeLevel = changeLevel + amounttoAdd;
            flag = true;
        }

        else if (changeLevel + amounttoAdd > maxChangeCapacity)
        {
            flag = false;
        }
        return flag;
    }

    public String toString()
    {
        return String.format("Machine Name \t\t\t%s\nCurrent Inventory Level\t%d\nCurrent Change Level\t\t%d\nLast Change Dispensed\t\t%d\nInventory Capacity\t\t%d\nChange Capacity\t\t\t%d\nCoke Price\t\t\t%d\nCokes Sold\t\t\t%d", machineName, inventoryLevel, changeLevel, changeDispensed, maxInventoryCapacity, maxChangeCapacity, CokePrice, numberOfCokesSold);
    }

    public ACTION buyACoke(int payment)
    {
        int return_change = 0;
        ACTION var = ACTION.DISPENSECHANGE;
        if (payment > CokePrice && !(changeLevel == maxChangeCapacity))
        {
            if (changeLevel >= (payment - CokePrice))
            {
                var = ACTION.DISPENSECHANGE;
                changeDispensed = payment - CokePrice;
                inventoryLevel = inventoryLevel - 1;
                changeLevel = (changeLevel - changeDispensed) + payment;
                numberOfCokesSold++;
            }
            else
            {
                var = ACTION.INSUFFICIENTCHANGE;
            }
        }
        else if (payment == CokePrice && !(changeLevel == maxChangeCapacity))
        {
            var = ACTION.EXACTPAYMENT;
            inventoryLevel = inventoryLevel - 1;
            changeLevel = changeLevel + payment;
            numberOfCokesSold++;
        }
        else if (payment < CokePrice && !(changeLevel == maxChangeCapacity))
        {
            var = ACTION.INSUFFICIENTFUNDS;
        }
        else if (changeLevel == maxChangeCapacity)
        {
            var = ACTION.NOINVENTORY;
        }
        return var;
    }

}