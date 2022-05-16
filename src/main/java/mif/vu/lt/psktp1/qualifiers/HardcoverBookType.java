package mif.vu.lt.psktp1.qualifiers;

import javax.enterprise.context.Dependent;

@Hardcover
@Dependent
public class HardcoverBookType implements BookTypeProcessor{

    @Override
    public String BookType() {
        return "Book type is Hardcover";
    }
}
