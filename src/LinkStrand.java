public class LinkStrand implements IDnaStrand {
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    private class Node {
        String myInfo;
        Node myNext;
        Node prev;
    
        public Node(String s) {
            myInfo = s;
            myNext = null;
            prev = null;
        }
    }

    public LinkStrand() {
        this("");
    }

    public LinkStrand(String s) {
        initialize(s);
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        Node newNode = new Node(source);
        myFirst = newNode;
        myLast = newNode;
        mySize = source.length();
        myAppends = 0;
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node newNode = new Node(dna);
        if (myLast != null) {
            myLast.myNext = newNode;
        } else {
            myFirst = newNode;
        }
        myLast = newNode;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        Node currNode = myLast;
        StringBuilder strBuilder = new StringBuilder(currNode.myInfo);
        IDnaStrand newStrand = new LinkStrand(strBuilder.reverse().toString());
    
        while (currNode != myFirst) {
            currNode = currNode.prev;
            strBuilder = new StringBuilder(currNode.myInfo);
            newStrand.append(strBuilder.reverse().toString());
        }
    
        return newStrand;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node current = myFirst;
        int currentIndex = 0;
        while (index >= currentIndex + current.myInfo.length()) {
            currentIndex += current.myInfo.length();
            current = current.myNext;
        }
        return current.myInfo.charAt(index - currentIndex);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = myFirst;
        while (current != null) {
            result.append(current.myInfo);
            current = current.myNext;
        }
        return result.toString();
    }
    
}
