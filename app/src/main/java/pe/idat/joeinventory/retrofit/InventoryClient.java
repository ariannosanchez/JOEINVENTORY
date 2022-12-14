package pe.idat.joeinventory.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InventoryClient {
    private static final String BASE_URL = "http://192.168.18.136:9000/api/";
//    private static final String BASE_URL = "https://api-joi-inventory2.onrender.com";
    public static InventoryServicio INSTANCE;

    public InventoryClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INSTANCE = retrofit.create(InventoryServicio.class);
    }

    public static InventoryServicio getINSTANCE(){
        return INSTANCE;
    }

}
