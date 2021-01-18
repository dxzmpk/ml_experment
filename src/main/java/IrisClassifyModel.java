import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IrisClassifyModel implements IModel{

    List<Float> w;

    List<Float> grad;

    float learningRate = 0.1f;

    float iteration = 1000;


    @Override
    public void train(List<List<Float>> train_data, List<Float> train_label) {

        int n_samples = train_data.size();
        int dim = train_data.get(0).size();
        List<Float> deltaW = new ArrayList<Float>(Collections.nCopies(dim, 0f));
        grad = new ArrayList<>(dim);
        float loss;
        List<Float> value;
        // 遇到终止条件之前，做以下操作
        for (int i = 0; i < iteration; i++) {
            // 初始化每个Δwi为0
            w = new ArrayList<Float>(Collections.nCopies(dim, 0f));
            value = matrix_vector_multiply(train_data, w);
            // wx
            for (int j = 0; j < deltaW.size(); j++) {
                for (int inside = 0; inside < value.size(); inside++){
                    deltaW.set(j, deltaW.get(j) + learningRate*(train_label.get(inside) - value.get(inside)));
                }
                // wj←wj+Δwj
                w.set(j, w.get(j) + deltaW.get(j));
            }
            loss = rmseLoss(value, train_label);
            if(i % 100 == 0) System.out.println("iter = " + i + ", RMSE loss = " + loss);
        }

    }

    @Override
    public void predict(List<List<Float>> test_data) {

    }

    // calculate w.dot(x)
    private List<Float> matrix_vector_multiply(List<List<Float>> matrix, List<Float> vector) {
        List<Float> result = new ArrayList<>(matrix.size());
        for (List<Float> row: matrix) {
            float row_sum = 0;
            for (int i = 0; i < row.size(); i ++) {
                row_sum += row.get(i) * vector.get(i);
            }
            result.add(row_sum);
        }
        return result;
    }

    // rmse loss
    private float rmseLoss(List<Float> value, List<Float> target) {
        float sum = 0;
        for (int i = 0; i < value.size(); i ++) {
            float diff = value.get(i) - target.get(i);
            float sdiff = diff*diff;
            sum += sdiff;
        }
        return (float) Math.sqrt(sum/value.size());
    }
}
