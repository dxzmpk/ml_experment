import java.util.List;

public interface IDataLoader {
    boolean loadData(String filepath);


    public List<List<Double>> getTrain_data();
    public List<Double> getTrain_labels();

}
