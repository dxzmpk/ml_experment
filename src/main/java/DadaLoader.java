import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DadaLoader implements IDataLoader{

    private List<List<Float>> train_data;

    private List<Float> train_labels;

    @Override
    public boolean loadData(String filepath) {
        train_data = new ArrayList<>();
        train_labels = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath), StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] sample = line.split("\t");
                List<Float> temp = new ArrayList<>();
                for(int i = 0; i < sample.length-1; i++){
                    temp.set(i, Float.parseFloat(sample[i]));
                }
                train_data.add(temp);
                train_labels.add(Float.parseFloat(sample[sample.length-1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public List<List<Float>> getTrain_data() {
        return train_data;
    }
    @Override
    public List<Float> getTrain_labels() {
        return train_labels;
    }
}
