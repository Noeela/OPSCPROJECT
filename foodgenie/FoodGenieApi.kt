import com.example.foodgenie.FoodItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface FoodGenieApi {
    @GET("foods")
    fun getFoods(): Call<List<FoodItem>>

    @POST("order")
    fun placeOrder(@Body order: Map<String, Any>): Call<Void>
}
