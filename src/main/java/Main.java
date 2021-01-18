public class Main {
    
    public static void main(String[] args){
        IDataLoader dataLoader = new DadaLoader();
        IModel model = new IrisClassifyModel();
        //dataLoader.loadData("D:\\archive\\test.csv");
        dataLoader.loadData("D:\\archive\\normalized_housing.csv");
        model.train(dataLoader.getTrain_data(), dataLoader.getTrain_labels());
    }
}
