package pl.pharmaway.prezentacjatrilac.network;

import pl.pharmaway.prezentacjatrilac.Constants;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface PrezentacjaApi {
    @GET("getData.php?lekarzType="+ Constants.LEKARZ_TYPE)
    Call<GetDataResponse> getData();

    @GET("version.php")
    Call<DataVersion> getDataVersion();

    @POST("send.php")
    @FormUrlEncoded
    Call<SendResponse> send(
            @Field("appId") int appId,
            @Field("createDate") String createDate,
            @Field("lekarzType") int lekarzType,
            @Field("agent") String agent,
            @Field("lekarz") String lekarz,
            @Field("timeInApp") String timeInApp,
            @Field("firstChoice") String firstChoice
    );
}
