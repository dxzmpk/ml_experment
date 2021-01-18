import java.util.List;

public interface IDataLoader {
    boolean loadData(String filepath);


    public List<List<Float>> getTrain_data();
    public List<Float> getTrain_labels();

}
