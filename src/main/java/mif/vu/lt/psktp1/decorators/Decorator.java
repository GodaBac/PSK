package mif.vu.lt.psktp1.decorators;

import javax.enterprise.context.Dependent;

@Dependent
public class Decorator implements AuthorDecorator{

    @Override
    public Integer DecoratedInt (Integer integer) {
        return integer;
    }
}
