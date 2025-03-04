import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class RoomScheduler {
    private Map<String, MeetingRoom> rooms = new HashMap<>();

   
    public void addMeetingRoom(MeetingRoom room) {
        rooms.put(room.getRoomId(), room);
        System.out.println("room is added: " + room.getRoomName() + ", id: " + room.getRoomId());
    }

   
    public String bookRoom(String roomId, EnumSet<RoomFeature> requiredFeatures) {
        MeetingRoom room = rooms.get(roomId);

        if (room != null && room.hasRequiredFeatures(requiredFeatures)) {
            return "room " + roomId + " is booked successfully.";
        } else {
            return "room " + roomId + " doesn't meet requirements.";
        }
    }

   
    public List<String> listAvailableRooms(EnumSet<RoomFeature> requiredFeatures) {
        List<String> availableRooms = new ArrayList<>();

        for (MeetingRoom room : rooms.values()) {
            if (room.hasRequiredFeatures(requiredFeatures)) {
                availableRooms.add(room.getRoomName());
            }
        }

        return availableRooms;
    }
}
