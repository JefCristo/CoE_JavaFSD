package week2.inventoryManagementSystem;

import java.util.logging.Logger;

public class InventoryLogger {
    private static  final Logger logger = Logger.getLogger(InventoryLogger.class.getName());

    public static void log(String message){
        logger.info(message);
    }
}
