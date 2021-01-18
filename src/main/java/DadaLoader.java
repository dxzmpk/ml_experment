import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DadaLoader implements IDataLoader{

    private List<List<Double>> train_data;

    private List<Double> train_labels;

    @Override
    public boolean loadData(String filepath) {
        train_data = new ArrayList<>();
        train_labels = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath), StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] sample = line.trim().split(",");
                List<Double> temp = new ArrayList<>();
                for(int i = 0; i < sample.length-1; i++){
                    temp.add(Double.parseDouble(sample[i]));
                }
                train_data.add(temp);
                train_labels.add(Double.parseDouble(sample[sample.length-1]));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public List<List<Double>> getTrain_data() {
        return train_data;
    }
    @Override
    public List<Double> getTrain_labels() {
        return train_labels;
    }
}
