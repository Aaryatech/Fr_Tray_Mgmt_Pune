package com.ats.patna_fr_tray_mgmt.interfaces;

import com.ats.patna_fr_tray_mgmt.bean.ComplaintData;
import com.ats.patna_fr_tray_mgmt.bean.ComplaintDetail;
import com.ats.patna_fr_tray_mgmt.bean.FeedbackData;
import com.ats.patna_fr_tray_mgmt.bean.FeedbackDetail;
import com.ats.patna_fr_tray_mgmt.bean.FrHomeData;
import com.ats.patna_fr_tray_mgmt.bean.FrTrayReportData;
import com.ats.patna_fr_tray_mgmt.bean.Info;
import com.ats.patna_fr_tray_mgmt.bean.LoginData;
import com.ats.patna_fr_tray_mgmt.bean.MessageData;
import com.ats.patna_fr_tray_mgmt.bean.NoticeData;
import com.ats.patna_fr_tray_mgmt.bean.NotificationData;
import com.ats.patna_fr_tray_mgmt.bean.RouteWiseData;
import com.ats.patna_fr_tray_mgmt.bean.SuggestionData;
import com.ats.patna_fr_tray_mgmt.bean.SuggestionDetail;
import com.ats.patna_fr_tray_mgmt.bean.TrayDetails;
import com.ats.patna_fr_tray_mgmt.bean.TrayMgtDetailInTray;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by MAXADMIN on 29/1/2018.
 */

public interface InterfaceApi {

    @POST("loginFr")
    Call<LoginData> getLogin(@Query("frCode") String userMobile,
                             @Query("frPasswordKey") String password);

    @GET("showFrontEndMessage")
    Call<MessageData> getAllMessages();

    @GET("showLatestNews")
    Call<NoticeData> getAllNotices();

    @GET("communication/getAllNotification")
    Call<ArrayList<NotificationData>> getAllNotification();

    @POST("communication/getAllNotificationById")
    Call<ArrayList<NotificationData>> getAllNotificationById(@Query("notificationId") int notificationId);

    @POST("communication/getAllSuggHeadersByFrId")
    Call<ArrayList<SuggestionData>> getAllSuggestion(@Query("frId") int frId,
                                                     @Query("suggestionId") int suggestionId);

    @POST("communication/getAllSuggDetailsByFrId")
    Call<ArrayList<SuggestionDetail>> getAllSuggestionDetails(@Query("frId") int frId,
                                                              @Query("suggestionDetailId") int suggestionDetailId);

    @POST("communication/saveSuggestionDetail")
    Call<Info> saveSuggestionDetail(@Body SuggestionDetail suggestionDetail);

    @POST("communication/updateUserToken")
    Call<Info> updateFCMToken(@Query("isAdmin") int isAdmin,
                              @Query("userId") int userId,
                              @Query("token") String token);

    @POST("communication/getAllCompHeadersByFrId")
    Call<ArrayList<ComplaintData>> getAllComplaint(@Query("frId") int frId,
                                                   @Query("complaintId") int complaintId);

    @POST("communication/getAllComplDetailsByFrId")
    Call<ArrayList<ComplaintDetail>> getAllComplaintDetail(@Query("frId") int frId,
                                                           @Query("compDetailId") int compDetailId);

    @POST("communication/getAllFeedbackById")
    Call<ArrayList<FeedbackData>> getAllFeedback(@Query("feedbackId") int feedbackId);

    @POST("communication/getAllFeedbackDetailsByFrId")
    Call<ArrayList<FeedbackDetail>> getAllFeedbackDetail1(@Query("frId") int frId,
                                                          @Query("feedbackDetailId") int feedbackDetailId);

    @POST("communication/getAllFeedbackDetailById")
    Call<ArrayList<FeedbackDetail>> getAllFeedbackDetail(@Query("feedbackDetailId") int feedbackDetailId);

    @POST("communication/saveSuggestion")
    Call<Info> saveSuggestion(@Body SuggestionData suggestionData);

    @POST("communication/deleteSuggestion")
    Call<Info> deleteSuggestion(@Query("suggestionId") int suggestionId);

    @POST("communication/saveComplaint")
    Call<Info> saveComplaint(@Body ComplaintData complaintData);

    @POST("communication/deleteComplaint")
    Call<Info> deleteComplaint(@Query("complaintId") int complaintId);

    @POST("communication/saveComplaintDetail")
    Call<Info> saveComplaintDetail(@Body ComplaintDetail complaintDetail);

    @POST("communication/saveFeedbackDetail")
    Call<Info> saveFeedbackDetail(@Body FeedbackDetail feedbackDetail);

    @Multipart
    @POST("photoUpload")
    Call<JSONObject> imageUpload(@Part MultipartBody.Part file,
                                 @Part("imageName") RequestBody name,
                                 @Part("type") RequestBody type);

    @POST("traymgt/getTrayDetailForTrayIn")
    Call<ArrayList<TrayDetails>> getTrayDetailsByFranchise(@Query("frId") int complaintId,
                                                           @Query("isSameDay") int isSameDay);

    @POST("traymgt/insertTrayInAndBalance")
    Call<Info> saveInTray(@Query("tranStatus1") int tranStatus1,
                          @Query("tranStatus2") int tranStatus2,
                          @Query("intrayBig") int intrayBig,
                          @Query("intraySmall") int intraySmall,
                          @Query("intrayLead") int intrayLead,
                          @Query("intrayExtra") int intrayExtra,
                          @Query("tranStatus3") int tranStatus3,
                          @Query("intrayBig1") int intrayBig1,
                          @Query("intraySmall1") int intraySmall1,
                          @Query("intrayLead1") int intrayLead1,
                          @Query("intrayExtra1") int intrayExtra1);

    @GET("traymgt/getServerDate")
    Call<Info> getServerDate();

    @POST("traymgt/getTrayMgtDetailList")
    Call<ArrayList<TrayDetails>> getTrayDetailReport(@Query("fromDate") String fromDate, @Query("toDate") String toDate, @Query("frId") int frId, @Query("IsDepositUsed") int IsDepositUsed);


//    @POST("traymgt/trayDetailByFrIdBySum")
//    Call<ArrayList<TrayDetails>> getBalTraySum(@Query("frId") int frId,
//                                               @Query("status") int status);

    @POST("traymgt/getTrayDetailForBalanceByFr")
    Call<ArrayList<TrayDetails>> getBalTraySum(@Query("frId") int frId,
                                               @Query("delStatus") int status);


    @POST("traymgt/trayDetailByFrIdAndStatus")
    Call<ArrayList<TrayDetails>> getBalTrayList(@Query("frId") int frId,
                                                @Query("status") int status);

    @POST("traymgt/updateTrayAndUpdateForApp")
    Call<Info> updateBalTray(@Query("tranDetailId") int tranDetailId,
                             @Query("balanceBig") int balanceBig,
                             @Query("balanceSmall") int balanceSmall,
                             @Query("balanceLead") int balanceLead,
                             @Query("trayStatus") int trayStatus
    );


    @POST("traymgt/saveTrayMgmtDeatilInTray")
    Call<TrayMgtDetailInTray> saveTrayMgtDetailInTray(@Body TrayMgtDetailInTray trayMgtDetailInTray);


    @POST("traymgt/getTrayMgmtTrayByFrId")
    Call<ArrayList<RouteWiseData>> getFrData(@Query("frId") int frId);

    @POST("traymgt/getTrayDetailByTrayDetId")
    Call<TrayDetails> receiveTray(@Query("tranDetId") int tranDetId);

    @POST("traymgt/getFrHomeData")
    Call<FrHomeData> getFrHomeData(@Query("frId") int frId,@Query("trayDate") String trayDate);

    @POST("traymgt/getFrTrayReportForLastEightDays")
    Call<ArrayList<FrTrayReportData>> getFrTrayReportData(@Query("frId") int frId, @Query("todaysDate") String todaysDate);

}
