package utils;

import View.AlertBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ViewHandler {
    public ViewHandler() {

    }

    private static Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewHandler.class.getResource(path));
        Parent root =  loader.load();
        return root;
    }

    public static Parent getcheckIn() {
        try {
            return loadFXML("../View/CheckIn/checkIn.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Parent getrooms() {
        try {
            return loadFXML("../View/rooms/rooms.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Parent getbooking() {
        try {
            return loadFXML("../View/booking/booking.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Parent getCreateBooking() {
        try {
            return loadFXML("../View/createBooking/CreateBooking.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Parent getcheckOut() {
        try {
            return loadFXML("../View/checkOut/checkOut.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Parent getCheckedInDetails(){
        try {
            return loadFXML("../View/CheckIn/CheckedInDetails.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
