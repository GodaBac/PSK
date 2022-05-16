package mif.vu.lt.psktp1.alternatives;

import javax.enterprise.context.Dependent;

@Dependent
public class BookMessage implements Message{

    @Override
    public String WriteMessage() {
        return "Book created";
    }

    public BookMessage() {

    }
}
