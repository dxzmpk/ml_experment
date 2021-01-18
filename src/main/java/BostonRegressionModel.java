import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BostonRegressionModel implements IModel{

    List<Double> w;

    List<Double> grad;

    float learningRate = 0.001f;

    float iteration = 1000f;


    @Override
    public void train(List<List<Double>> train_data, List<Double> train_label) {

        int n_samples = train_data.size();
        int dim = train_data.get(0).size();
        List<Double> deltaW = new ArrayList<Double>(Collections.nCopies(dim, 0d));
        w = new ArrayList<Double>(Collections.nCopies(dim, 0d));
        grad = new ArrayList<>(dim);
        float loss;
        List<Double> value;
        // 遇到终止条件之前，做以下操作
        for (int i = 0; i < iteration; i++) {
            // 初始化每个Δwi为0
            deltaW = new ArrayList<Double>(Collections.nCopies(dim, 0d));
            value = matrix_vector_multiply(train_data, w);
            // wx
            for (int j = 0; j < dim; j++) {
                for (int sampleIndex = 0; sampleIndex < n_samples; sampleIndex++){
                    deltaW.set(j, deltaW.get(j) + learningRate*(train_label.get(sampleIndex) - value.get(sampleIndex))*train_data.get(sampleIndex).get(j));
                }
                // wj←wj+Δwj
                w.set(j, w.get(j) + deltaW.get(j));
            }
            if (i%100 == 0 || i <= 100) {
                loss = rmseLoss(value, train_label);
                System.out.println("iter = " + i + ", RMSE loss = " + loss);
            }
        }

    }

    @Override
    public List<Double> predict(List<List<Double>> test_data) {
        List<Double> value;
        value = matrix_vector_multiply(test_data, w);
        return value;
    }

    // calculate w.dot(x)
    private List<Double> matrix_vector_multiply(List<List<Double>> matrix, List<Double> vector) {
        List<Double> result = new ArrayList<>(matrix.size());
        for (List<Double> row: matrix) {
            double row_sum = 0;
            for (int i = 0; i < row.size(); i ++) {
                row_sum += row.get(i) * vector.get(i);
            }
            result.add(row_sum);
        }
        return result;
    }

    // rmse loss
    private float rmseLoss(List<Double> value, List<Double> target) {
        double sum = 0;
        for (int i = 0; i < value.size(); i ++) {
            double diff = value.get(i) - target.get(i);
            double sdiff = Math.abs(diff);
            sum += sdiff;
        }
        return (float) Math.sqrt(sum/value.size());
    }
}
