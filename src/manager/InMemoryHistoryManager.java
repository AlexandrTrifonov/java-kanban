package manager;

import interfaces.HistoryManager;
import tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class InMemoryHistoryManager implements HistoryManager {

    HashMap<Integer, Node> idHM = new HashMap<>();

    @Override
    public List<Task> getHistory() {
        return customLinkedList.getTasks();
    }

    @Override
    public void add(Task task) {
    //    if (!(idHM.isEmpty())) {
        if(idHM.containsKey(task.getId())) {
            for(Integer key : idHM.keySet())
            {
                if (key == task.getId()) {
                    customLinkedList.removeNode(idHM.get(task.getId()));
                }
            }
        }
        customLinkedList.linkLast(task);
    }
    @Override
    public void remove(int id) {
        if(idHM.containsKey(id)) {
            customLinkedList.removeNode(idHM.remove(id));
    //        customLinkedList.removeNode(idHM.get(id));
    //        idHM.remove(id);
        }
    }

    CustomLinkedList<Task> customLinkedList = new CustomLinkedList();
    public class CustomLinkedList<T> {

        private Node head;
        private Node tail;

        void linkLast(Task task){
            final Node oldNode = tail;
            final Node newNode = new Node(oldNode, task, null);
            tail = newNode;
            if (oldNode == null) {
                head = newNode;
            }
            else {
                oldNode.next = newNode;
            }
            idHM.put(task.getId(), newNode);
        }
       public List<Task> getTasks() {
            List<Task> historyList = new ArrayList<>();
            Node tNode = head;
            while (tNode != null) {
                historyList.add(tNode.task);
                tNode = tNode.next;
            }
            return historyList;
        }
       public void removeNode(Node node) {

            if (node != null) {

                final Node next = node.next;
                final Node prev = node.prev;

                if (prev == null) {
                    head = next;
                } else {
                    prev.next = next;
                    node.prev = null;
                }

                if (next == null) {
                    tail = prev;
                } else {
                    next.prev = prev;
                    node.next = null;
                }
    //            node.task = null;
            }
       }
    }
}
