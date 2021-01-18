import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    
    public static void main(String[] args){
        IDataLoader dataLoader = new DadaLoader();
        IModel model = new BostonRegressionModel();
        Path path =  Paths.get("dataset");
        path = path.resolve("normalized_housing.csv");
        //dataLoader.loadData("D:\\archive\\test.csv");
        dataLoader.loadData(path.toAbsolutePath().toString());
        model.train(dataLoader.getTrain_data(), dataLoader.getTrain_labels());
    }
}
