package com.sonu.karvytest.network;

import com.sonu.karvytest.EmployeeListModel;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Sonu Sinha on 21/09/2020.
 */
public interface ServicesAPI {
    @GET(APIConstants.EndPoints.GET_EMPLOYEE_LIST)
    Single<EmployeeListModel> getEmployeeList();

}