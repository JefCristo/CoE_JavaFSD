package week1.ex6;


public class LinkedList
{

    static class Node {
        int val;
        Node next ;

        Node(){

        }

        Node( int val ){
            this.val = val;
        }

        Node(Node next , int val ){
            this.val = val;
            this.next = null;
        }
    }


    public static boolean hasCycle(Node head){
        if(head.next == null || head.next.next == null){
            return false;
        }
        Node slow = head;
        Node fast = head;

        while(fast != null &&  fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        /*
        Create a LinkedList
        with a cycle
         */
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        /*




        Forms a cycle connecting 0 and 3
        0->1->2->3->..->0
         */
        head.next.next.next = head.next;

//        function to detect cycle is present or not
        boolean hasCycle = hasCycle(head);
        System.out.println(hasCycle);



        /*
        Create a LinkedList
        without a cycle
         */
        Node head2 = new Node(0);
        head2.next = new Node(1);
        head2.next.next = new Node(2);
        head2.next.next.next = new Node(3);

        System.out.println(hasCycle(head2));
//        output : false


    }


}
