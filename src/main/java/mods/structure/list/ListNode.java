package mods.structure.list;

 public class ListNode {
     public int val;
     public ListNode next;

     ListNode() {}

     public ListNode(int val) {
         this.val = val;
     }

     public ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
     }

     public static ListNode generate(int[] input) {
         if(input.length == 0)
             return null;

         ListNode head = new ListNode(input[0]);
         ListNode former = head;
         for(int i = 1; i < input.length; ++i) {
             former.next = new ListNode(input[i]);
             former = former.next;
         }

         return head;
     }
 }