package blank.djaja_works.API;

import java.util.List;

import blank.djaja_works.models.Akun;
import blank.djaja_works.models.Investment;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {
    /*Account Start*/
    @GET("Account_Api/index_get")
    Call<List<Akun>> getUserLogin(@Query("email") String username, @Query("password") String password);

    @FormUrlEncoded
    @POST("Account_Api/index_post")
    Call<Akun> postAkun(@Field("email") String email,
                           @Field("password") String password,
                           @Field("nama_lengkap") String nL,
                           @Field("usia") int umur,
                           @Field("jk") String jk,
                           @Field("n_ktp") String n_ktp,
                           @Field("n_rek") String n_rek);

    @FormUrlEncoded
    @PUT("Account_Api/index_put")
    Call<Akun> putAkun(@Field("email") String email,
                           @Field("password") String password,
                           @Field("nama_lengkap") String nL,
                           @Field("usia") int umur,
                           @Field("jk") String jk,
                           @Field("n_ktp") String n_ktp,
                           @Field("n_rek") String n_rek);
    /*Account End*/

    /*InvestList*/
    @GET("Peminjam_Api")
    Call<List<Investment>> getInvestList();

    @FormUrlEncoded
    @DELETE("Peminjam_Api")
    Call<List<Investment>> deleteSpam(@Query("id_calon") int kode);
}
