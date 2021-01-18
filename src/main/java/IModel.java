import java.util.List;

public interface IModel {

    void train(List<List<Double>> train_data, List<Double> train_label);

    void predict(List<List<Double>> test_data);
}
