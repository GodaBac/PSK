package mif.vu.lt.psktp1.qualifiers;

import javax.enterprise.context.Dependent;

@Paperback
@Dependent
public class PaperbackBookType implements BookTypeProcessor{

    @Override
    public String BookType() {
        return "Book type is paperback";
    }
}
