package Assignment02_DataStructures;

public class Hashtable {

    private int num_buckets = 252514;
    private HashNode[] buckets;
    private int size;

    /*
     * Creation of HashNode classes for storing each individual key-value
     * element for what is stored in the buckets LinkedList of HashNodes
     */
    public class HashNode {

        public String key;
        public String value;
        public HashNode next;

        public HashNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            size=0;
        }

        /**
         * Get the value from a key in the bucket
         * @return the value with respect to a key
         */
        String getValue() {
            return value;
        }
    }

    Hashtable() {
        buckets = new HashNode[num_buckets];
        size=0;
    }


    /**
     * Gets the bucket in the LinkedList at certain index
     * using the hashCode function for a string type
     *
     * @param key the string to receive its hashCode from
     * @return the index value of that key's location
     */
    private int getBucket(String key) {

//        int hashCode = key.hashCode();
        int hashCode = Math.abs(key.hashCode());
        int hash_Bucket = (hashCode % num_buckets);
        return hash_Bucket;
    }

    /**
     * Checks if a key exists in the LinkedList or not
     *
     * @param key the string-type value being checked
     * @return true if present; false otherwise
     */
    boolean containsKey(String key) {
        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        while (node != null) {
            if (node.key.equals(key))
                return true;
        }
        return false;
    }


    /**
     * Retrieve the value respective to the key as the argument
     *
     * @param key the String argument that is being retrieved
     * @return the value of the node with respect to key if exists; null otherwise
     */
    public String get(String key) {
        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        while(node != null) {
            if(node.key.equals(key)) {
//                node = node.next;
                return node.value;
            }
            node = node.next;
        }

        return null;
    }


    /**
     * Insert a key-value node into the LinkedList of Nodes
     * Create a new Node afterwards whenever needed if none null
     *
     * @param key the key to be added to the node in the List
     * @param value the value to be added to the node in the List
     */
    void put(String key, String value) {

        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
//        if(bucket_id >= size)
//            increaseSize();
//        if(bucket_id >= buckets.length)
//            increaseSize();


        while(node!=null){
            if(node.key.equals(key)) {
                node.key = key;
                node.value = value;
                return;
            }
//            if(!node.equals(null))
//                node.value = value;
            node = node.next;
        }
        size++;

        HashNode new_node = new HashNode(key, value);
        new_node.next = buckets[bucket_id];
        buckets[bucket_id] = new_node;


        // Realized I did not need the lambda time test
        // Nor did I need to increase the size of the new array to store more nodes


//        if((1.0*size / 1.0*num_buckets)>0.7){
//            increaseSize();
//        }
//        HashNode new_node = new HashNode(key, value);
//        new_node.next = node;
//        buckets[bucket_id] = new_node;
//
//        if((1.0*size / 1.0*num_buckets)>=0.5){
////            HashNode new_node = new HashNode(key, value);
//            HashNode temp_node = node;
//            num_buckets*=2;
////            HashNode[]new_Buckets = new HashNode[buckets.length*2];
//            this.size=0;
//
//
//                while(temp_node!=null){
//                    put(node.key, node.value);
//                    node = node.next;
//                }
//
//            new_node.next = buckets[bucket_id];
//            buckets[bucket_id] = new_node;
//        }
    }


    /**
     * Remove a key in the LinkedList
     *
     * @param key the key in the node to be removed
     * @return the value of the node that is removed; null if key not found
     */
    String remove(String key) {

        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        HashNode last = null;
        while(node.next != null && !node.key.equals(key)) {

            last = node;
            node = node.next;
        }
        if(node.key.equals(key)){

            if(node==null)
                return null;
            if(last==null)
                buckets[bucket_id] = node.next;
            else{
                last.next = node.next;
                size--;
            }
        }

        return node.value;
    }


    /**
     * Retrieve the size value that counts the number of node in LinkedList
     * @return size
     */
    int getSize(){
        return size;
    }


    /**
     * Check if the LinkedList size is empty or not
     * @return true if empty; false otherwise
     */
    boolean isEmpty(){
        return(size==0);
    }

}

