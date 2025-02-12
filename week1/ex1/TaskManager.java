package week1.ex1;

import java.util.Iterator;
import java.util.PriorityQueue;

class Task  {
    String id;
    String description;
    int priority;

    public Task(String id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }


}

class TaskManager {
    private PriorityQueue<Task> tasks = new PriorityQueue<>((a,b) -> b.priority - a.priority);

    public void addTask(String id, String description, int priority) {
        tasks.add(new Task(id, description, priority));
    }

    public void removeTask(String id) {
        if (!tasks.isEmpty()) {
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.id.equals(id)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }


    public Task getHighestPriorityTask() {
        return tasks.peek();
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("1", "Complete project report", 2);
        manager.addTask("2", "Prepare for meeting", 1);
        manager.addTask("3", "Update documentation", 3);

        System.out.println("Highest Priority Task: " + manager.getHighestPriorityTask().description);

        manager.removeTask("3");
        System.out.println("Highest Priority Task after removal: " + manager.getHighestPriorityTask().description);
        System.out.println("Highest Priority Task after removal: " + manager.getHighestPriorityTask().description);
    }
}
