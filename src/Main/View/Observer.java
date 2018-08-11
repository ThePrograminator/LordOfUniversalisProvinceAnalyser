package Main.View;

import SearchObjects.FinderHandler;

public abstract class Observer
{
    protected FinderHandler finderHandler;
    public abstract void update();
}
