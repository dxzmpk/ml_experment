import java.util.List;

public interface IModel {

    void train(List<List<Float>> train_data, List<Float> train_label);

    void predict(List<List<Float>> test_data);
}
