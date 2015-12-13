package zapata.com.imagesearching.DataFactory;

import zapata.com.imagesearching.DataFactory.Cloud.ImageSearchServerFactory;
import zapata.com.imagesearching.DataFactory.Database.ImageSearchDatabaseFactory;

/**
 * Created by lenovo on 12/9/2015.
 */
public class ImageSearchFactory {

    public enum FactoryType{
        DatabaseFactory,
        ServerFactory
    }

    public static SearchFactory getFactory(FactoryType factoryType){
        if(factoryType != null && factoryType == FactoryType.DatabaseFactory){
            return new ImageSearchDatabaseFactory();
        }
        else{
            return new ImageSearchServerFactory();
        }
    }

}
