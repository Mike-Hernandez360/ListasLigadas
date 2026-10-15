public class linkedList {
    private Node head;   // Primer nodo de la lista enlazada
    private Node tail;   // Último nodo de la lista enlazada
    private int length;  // Cantidad total de elementos en la lista

    // Clase interna que representa cada nodo individual de la lista
    class Node {
        int value; // Valor numérico almacenado en el nodo
        Node next; // Puntero que apunta al siguiente nodo en la lista

        // Constructor del nodo
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // Constructor principal de la lista enlazada: inicializa la lista con un primer nodo
    public linkedList(int value) {
        Node newNode = new Node(value);
        length = 1;
        head = newNode;
        tail = newNode;
    }

    // Imprime en consola el valor del nodo inicial (Head)
    public void getHead() {System.out.println("Head:" + head.value);}

    // Imprime en consola el valor del nodo final (Tail)
    public void getTail() {System.out.println("Tail:" + tail.value);}

    // Imprime en consola la longitud actual de la lista
    public void getLength() {System.out.println("Length:" + length);}

    // Agrega un nuevo nodo con el valor especificado al final de la lista
    public void append(int value) {
        if (length == 0) {
            Node newNode = new Node(value);
            length = 1;
            head = newNode;
            tail = newNode;
            return;
        }
        Node newNode = new Node(value);
        tail.next = newNode;
        tail = newNode;
        length++;
    }
    
    // Recorre y muestra en consola todos los valores de los nodos que conforman la lista
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    // Elimina y retorna el último nodo de la lista (remueve por el final)
    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    // Elimina y retorna el primer nodo de la lista (remueve por el inicio)
    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    // Busca y retorna el nodo ubicado en un índice específico (basado en 0)
    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // Actualiza el valor de un nodo en un índice específico. Retorna true si tiene éxito, false si falla.
    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    // Agrega un nuevo nodo con el valor dado al principio de la lista
    public boolean prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
        return true;
    }

    // Inserta un nuevo nodo con el valor dado en una posición (índice) arbitraria de la lista
    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) return prepend(value);
        if (index == length) return append(value);
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    // Elimina el nodo ubicado en un índice específico y lo retorna
    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();
        Node prev = get(index - 1);
        Node temp = prev.next;
        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }

    // Encuentra y retorna el nodo del medio (mitad) de la lista usando la técnica de dos punteros (lento y rápido)
    public Node nodeHalf() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Invierte por completo el orden de los elementos y los enlaces de la lista
    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node after = temp.next;
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    // Detecta si la lista enlazada contiene algún bucle o ciclo cerrado utilizando la estrategia de dos punteros
    public boolean isCyclic() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true; // Se encontraron, hay un ciclo
        }
        return false;
    }
}

