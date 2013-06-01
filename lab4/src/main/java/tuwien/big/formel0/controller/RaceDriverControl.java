package tuwien.big.formel0.controller;

import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tuwien.big.formel0.picasa.RaceDriver;
import tuwien.big.formel0.picasa.RaceDriverLoader;

/**
 *
 * @author alex
 */
public class RaceDriverControl {

    private static List<RaceDriver> driverList = new ArrayList<RaceDriver>();

    public static void addRaceDriver(RaceDriver driver) {
        driverList.add(driver);
    }

    public static RaceDriver getRaceDriver(String name) {
        for (RaceDriver driver : driverList) {
            if (driver.getName().equals(name)) {
                return driver;
            }
        }
        return null;
    }

    public static List<RaceDriver> getRaceDriverList() {
        return driverList;
    }

    public static void updateRaceDriverList() {
        RaceDriverLoader loader = new RaceDriverLoader();

        try {
            driverList = loader.getRaceDrivers();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (ServiceException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static boolean isEmpty() {
        RaceDriverControl.updateRaceDriverList();
        if (getRaceDriverList().size() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
