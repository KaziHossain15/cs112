public class Problem2 {
    public static LLList convert_AtoL(ArrayList vals) {
        LLList converted = new LLList();
    
        for (int i = vals.length() - 1; i >= 0; i--) {
            Object item = vals.getItem(i);
            converted.addItem(item, 0);
        }
    
        return converted;
    }

    public static LLList convert_AtoL_v2(ArrayList vals) {
        LLList converted = new LLList();
    
        for (int i = 0; i < vals.length(); i++) {  // note the changes!
            Object item = vals.getItem(i);
            converted.addItem(item, i);    // note the change!
        }
    
        return converted;
    }

    public static ArrayList convert_LtoA(LLList vals) {
        ArrayList converted = new ArrayList(vals.length());
        
        Node trav = vals.head.next;
        int counter = 0;
        while (trav != null) {
            Object item = vals.getItem(trav);
            converted.addItem(item, counter); 
            trav = trav.next;
            counter += 1;
        }
        return converted;
    }
    

    public static void main(String[] args) {
        Integer[] valsArray = {3, 7, 8, 2, 1, 9};
        ArrayList vals = new ArrayList(valsArray);
        LLList converted = convert_AtoL(vals);
        System.out.println(converted);
    }
}