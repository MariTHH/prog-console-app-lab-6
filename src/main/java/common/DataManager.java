package common;

import common.network.CommandResult;
import common.network.Request;

public abstract class DataManager {
    // protected abstract Integer generateNextId();
    public abstract CommandResult add(Request<?> request);

    public abstract CommandResult addIfMax(Request<?> request);

    public abstract CommandResult addIfMin(Request<?> request);

    public abstract CommandResult show(Request<?> request);

    public abstract CommandResult clear(Request<?> request);
    public abstract CommandResult info(Request<?> request);
    public abstract CommandResult help(Request<?> request);
    public abstract CommandResult countEyeColor(Request<?> request);

    /**
     public abstract CommandResult filterGreaterThanExpelledStudents(Request<?> request);
     public abstract CommandResult removeAllByShouldBeExpelled(Request<?> request);
     public abstract CommandResult removeById(Request<?> request);
     public abstract CommandResult removeGreater(Request<?> request);
     public abstract CommandResult removeLower(Request<?> request);
     public abstract CommandResult update(Request<?> request);

     public void save() {}*/
}
